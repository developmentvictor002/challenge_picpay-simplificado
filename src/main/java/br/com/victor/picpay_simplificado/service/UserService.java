package br.com.victor.picpay_simplificado.service;

import br.com.victor.picpay_simplificado.dto.UserRequestDto;
import br.com.victor.picpay_simplificado.dto.UserResponseDto;
import br.com.victor.picpay_simplificado.entity.User;
import br.com.victor.picpay_simplificado.repository.UserRepository;
import org.springframework.stereotype.Service;

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
}
