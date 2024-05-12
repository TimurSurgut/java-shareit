package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.EmailIsAlreadyRegisteredException;
import ru.practicum.shareit.exception.EmptyFieldException;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Collection;
import java.util.stream.Collectors;

import static ru.practicum.shareit.user.dto.mapper.UserMapper.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceDbImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto create(UserDto userDto) {
        if (userDto.getEmail() == null) {
            throw new EmptyFieldException("Адрес электронной почты пуст");
        }
        User user;
        try{user=userRepository.save(toUser(userDto));}
        catch (DataIntegrityViolationException e){
throw new EmailIsAlreadyRegisteredException("Пользователь с таким адресом электронной почты уже существует!");
        }
        return toUserDto(user);
            }

    @Override
    public UserDto getById(long id) {
        log.debug("Getting user by Id: {}", id);
        User userFromRep = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Нет пользователя с id: " + id));
        return toUserDto(userFromRep);
    }

    @Override
    public Collection<UserDto> getAll() {
        log.debug("Получение доступа ко всем пользователям");
        return userRepository.findAll().stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto update(UserDto userDto) {
        log.debug("Обновление пользователя: {}", userDto);
        User userToUpdate = toUserUpdate(userDto, userRepository.findById(userDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Нет пользователя с id: " + userDto.getId())));
        userRepository.save(userToUpdate);
        return toUserDto(userToUpdate);
    }

    @Override
    public void delete(long id) {
        User userFromDb = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Нет пользователя с id: " + id));
        log.debug("Удаление пользователя с id: {}", id);
        userRepository.deleteById(userFromDb.getId());
    }
}