package br.com.victor.picpay_simplificado.repository;

import br.com.victor.picpay_simplificado.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
