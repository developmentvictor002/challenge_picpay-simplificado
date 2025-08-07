package br.com.victor.picpay_simplificado.controller;

import br.com.victor.picpay_simplificado.dto.TransferRequestDto;
import br.com.victor.picpay_simplificado.dto.TransferResponseDto;
import br.com.victor.picpay_simplificado.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/transactions")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponseDto> transfer(@Valid @RequestBody TransferRequestDto dto) {
        TransferResponseDto responseDto = transactionService.transfer(dto);
        return ResponseEntity.ok(responseDto);
    }
}
