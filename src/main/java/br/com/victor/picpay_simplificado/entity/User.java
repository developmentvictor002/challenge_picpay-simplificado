package br.com.victor.picpay_simplificado.entity;

import br.com.victor.picpay_simplificado.enums.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(name = "name", nullable = false)
    private String fullName;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType type;

    public User(String fullName, String cpf, String email, String password, UserType type) {
        this.fullName = fullName;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.type = type;
    }

}
