package br.com.victor.picpay_simplificado.service;

import br.com.victor.picpay_simplificado.dto.TransferRequestDto;
import br.com.victor.picpay_simplificado.dto.TransferResponseDto;
import br.com.victor.picpay_simplificado.entity.Transaction;
import br.com.victor.picpay_simplificado.entity.Wallet;
import br.com.victor.picpay_simplificado.enums.UserType;
import br.com.victor.picpay_simplificado.exception.InsufficientBalanceException;
import br.com.victor.picpay_simplificado.exception.InvalidTransferException;
import br.com.victor.picpay_simplificado.exception.MerchantCannotTransferException;
import br.com.victor.picpay_simplificado.exception.UnauthorizedTransferException;
import br.com.victor.picpay_simplificado.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletService walletService;
    private final AuthorizationService authorizationService;

    public TransactionService(TransactionRepository transactionRepository, WalletService walletService, AuthorizationService authorizationService) {
        this.transactionRepository = transactionRepository;
        this.walletService = walletService;
        this.authorizationService = authorizationService;
    }

    @Transactional
    public TransferResponseDto transfer(TransferRequestDto dto) {
        Wallet senderWallet = walletService.getWalletEntityById(dto.senderWalletId());
        Wallet receiverWallet = walletService.getWalletEntityById(dto.receiverWalletId());

        if(senderWallet.getHolder().getType() == UserType.MERCHANT) {
            throw new MerchantCannotTransferException("Merchants cannot send money");
        }

        if(senderWallet.getBalance().compareTo(dto.value()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance for this transaction");
        }

        if(!authorizationService.isAuthorized()) {
            throw new UnauthorizedTransferException("Transfer not authorized by external service");
        }

        if(senderWallet.getWalletId().equals(receiverWallet.getWalletId())) {
                throw new InvalidTransferException("Sender and receiver wallets must be different");
        }

        walletService.transferAmount(senderWallet, receiverWallet, dto.value());

        Transaction transaction = new Transaction();
        transaction.setSender(senderWallet.getHolder());
        transaction.setReceiver(receiverWallet.getHolder());
        transaction.setValue(dto.value());

        transactionRepository.save(transaction);

        return new TransferResponseDto(
          senderWallet.getWalletId(),
          receiverWallet.getWalletId(),
          dto.value()
        );
    }
}
