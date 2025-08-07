package br.com.victor.picpay_simplificado.controller;

import br.com.victor.picpay_simplificado.dto.DepositRequestDto;
import br.com.victor.picpay_simplificado.dto.WalletRequestDto;
import br.com.victor.picpay_simplificado.dto.WalletResponseDto;
import br.com.victor.picpay_simplificado.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

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

    @PutMapping("/{walletId}/deposit")
    public ResponseEntity<WalletResponseDto> deposit(@PathVariable("walletId") UUID walletId,
                                                     @RequestBody DepositRequestDto dto) {
        WalletResponseDto responseDto = walletService.deposit(walletId, dto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<WalletResponseDto> getWalletById(@PathVariable("walletId") UUID walletId) {
        WalletResponseDto responseDto = walletService.getWalletById(walletId);
        return ResponseEntity.ok(responseDto);
    }
}
