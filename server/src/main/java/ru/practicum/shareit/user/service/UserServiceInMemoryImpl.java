package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.practicum.shareit.exception.EmptyFieldException;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.mapper.UserMapper;
import ru.practicum.shareit.user.repository.inmemmory.UserRepository;


import java.util.*;
import java.util.stream.Collectors;

import static ru.practicum.shareit.user.dto.mapper.UserMapper.*;

@Slf4j
@RequiredArgsConstructor
public class UserServiceInMemoryImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto create(UserDto userDto) {
        if (userDto.getEmail() == null) {
            throw new EmptyFieldException("Адрес электронной почты пуст");
        }
        log.debug("Создание пользователя : {}", userDto);
        return toUserDto(userRepository.create(toUser(userDto)));
    }

    @Override
    public UserDto getById(long id) {
        checkingId(id);
        log.debug("Получение доступа к пользователю с Id: {}", id);
        return toUserDto(userRepository.getById(id));
    }

    @Override
    public Collection<UserDto> getAll() {
        log.debug("Получение доступа ко всем пользователям");
        return userRepository.getAll().stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto update(UserDto userDto) {
        checkingId(userDto.getId());
        log.debug("Обновление пользователя: {}", userDto);
        return toUserDto(userRepository
                .update(toUserUpdate(userDto, userRepository.getById(userDto.getId()))));
    }

    @Override
    public void delete(long id) {
        checkingId(id);
        log.debug("Удаление пользователя с id: {}", id);
        userRepository.delete(id);
    }

    private void checkingId(long id) {
        if (userRepository.getById(id) == null) {
            throw new EntityNotFoundException("Нет пользователя с id: " + id);
        }
    }
}