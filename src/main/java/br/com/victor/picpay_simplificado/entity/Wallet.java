package br.com.victor.picpay_simplificado.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_wallets")
public class Wallet {

    @Id
    @Column(name = "wallet_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID walletId;

    @Column(name = "balance")
    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User holder;
}
