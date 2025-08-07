package br.com.victor.picpay_simplificado.controller;

import br.com.victor.picpay_simplificado.dto.WalletRequestDto;
import br.com.victor.picpay_simplificado.dto.WalletResponseDto;
import br.com.victor.picpay_simplificado.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<WalletResponseDto> createWallet(@RequestBody WalletRequestDto dto) {
        WalletResponseDto responseDto = walletService.createWallet(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.walletId())
                .toUri();
        return ResponseEntity.created(location).body(responseDto);
    }
}
