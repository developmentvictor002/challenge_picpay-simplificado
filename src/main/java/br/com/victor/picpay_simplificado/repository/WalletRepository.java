package br.com.victor.picpay_simplificado.repository;

import br.com.victor.picpay_simplificado.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
}
