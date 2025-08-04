package br.com.victor.picpay_simplificado.repository;

import br.com.victor.picpay_simplificado.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
