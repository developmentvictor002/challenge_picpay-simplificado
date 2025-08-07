package br.com.victor.picpay_simplificado.service;

import br.com.victor.picpay_simplificado.dto.DepositRequestDto;
import br.com.victor.picpay_simplificado.dto.UserSummaryDto;
import br.com.victor.picpay_simplificado.dto.WalletRequestDto;
import br.com.victor.picpay_simplificado.dto.WalletResponseDto;
import br.com.victor.picpay_simplificado.entity.User;
import br.com.victor.picpay_simplificado.entity.Wallet;
import br.com.victor.picpay_simplificado.exception.InsufficientBalanceException;
import br.com.victor.picpay_simplificado.exception.InvalidDepositAmountException;
import br.com.victor.picpay_simplificado.exception.WalletNotFoundException;
import br.com.victor.picpay_simplificado.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final UserService userService;

    public WalletService(WalletRepository walletRepository, UserService userService) {
        this.walletRepository = walletRepository;
        this.userService = userService;
    }

    public WalletResponseDto createWallet(WalletRequestDto dto) {
        UUID userId = dto.userId();
        User user = userService.getUserEntityById(userId);

        Wallet wallet = new Wallet();
        wallet.setHolder(user);

        Wallet savedWallet = walletRepository.save(wallet);

        return toWalletResponseDto(savedWallet);
    }

    public WalletResponseDto deposit(UUID walletId, DepositRequestDto requestDto) {
        Wallet wallet = getWalletWithValidations(walletId, requestDto);

        wallet.setBalance(wallet.getBalance().add(requestDto.amount()));
        walletRepository.save(wallet);

        return toWalletResponseDto(wallet);
    }

    public WalletResponseDto getWalletById(UUID walletId) {
        Wallet wallet = getWalletEntityById(walletId);
        return toWalletResponseDto(wallet);
    }

    public void transferAmount(Wallet sender, Wallet receiver, BigDecimal value) {
        if(sender.getBalance().compareTo(value) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        sender.setBalance(sender.getBalance().subtract(value));
        receiver.setBalance(receiver.getBalance().add(value));

        walletRepository.save(sender);
        walletRepository.save(receiver);
    }

    protected Wallet getWalletEntityById(UUID walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found: " + walletId));
    }

    private Wallet getWalletWithValidations(UUID walletId, DepositRequestDto requestDto) {
        Wallet wallet = getWalletEntityById(walletId);

        if (requestDto.amount() == null || requestDto.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidDepositAmountException("Deposit amount must be positive.");
        }
        return wallet;
    }

    protected WalletResponseDto toWalletResponseDto(Wallet wallet) {
        User holder = wallet.getHolder();
        UserSummaryDto holderDto = new UserSummaryDto(
                holder.getUserId(), holder.getFullName(), holder.getEmail()
        );
        return new WalletResponseDto(
                wallet.getWalletId(),
                wallet.getBalance(),
                holderDto
        );
    }

}
