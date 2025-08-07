package br.com.victor.picpay_simplificado.service;

import br.com.victor.picpay_simplificado.dto.UserSummaryDto;
import br.com.victor.picpay_simplificado.dto.WalletRequestDto;
import br.com.victor.picpay_simplificado.dto.WalletResponseDto;
import br.com.victor.picpay_simplificado.entity.User;
import br.com.victor.picpay_simplificado.entity.Wallet;
import br.com.victor.picpay_simplificado.repository.WalletRepository;
import org.springframework.stereotype.Service;

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

        UserSummaryDto holderDto = new UserSummaryDto(
                user.getUserId(), user.getFullName(), user.getEmail()
        );

        return new WalletResponseDto(
                savedWallet.getWalletId(),
                savedWallet.getBalance(),
                holderDto
        );
    }
}
