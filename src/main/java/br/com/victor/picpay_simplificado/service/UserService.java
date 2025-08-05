package br.com.victor.picpay_simplificado.service;

import br.com.victor.picpay_simplificado.dto.UserRequestDto;
import br.com.victor.picpay_simplificado.dto.UserResponseDto;
import br.com.victor.picpay_simplificado.entity.User;
import br.com.victor.picpay_simplificado.exception.UserNotFoundException;
import br.com.victor.picpay_simplificado.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto createUser(UserRequestDto dto) {
        User userToSave = dto.toUser();
        User savedUser = userRepository.save(userToSave);
        return UserResponseDto.fromUser(savedUser);
    }

    public UserResponseDto getUserById(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
        return UserResponseDto.fromUser(user);
    }

    public UserResponseDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email: " + email + " not found."));
        return UserResponseDto.fromUser(user);
    }
}
