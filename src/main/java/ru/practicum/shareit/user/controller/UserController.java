package ru.practicum.shareit.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserService;

import javax.validation.Valid;
import java.util.Collection;


@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public Collection<UserDto> getAllUsers() {
        log.debug("Получение доступа ко всем пользователям");
        return userService.getAll();
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable long userId) {
        log.debug("Получение доступа к пользователю с id: {}", userId);
        return userService.getById(userId);
    }

    @PostMapping()
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        log.debug("Создание пользователя: {}", userDto);
        return userService.create(userDto);
    }

    @PatchMapping("/{userId}")
    public UserDto updateUser(@PathVariable long userId, @Valid @RequestBody UserDto userDto) {
        log.debug("Обновление пользователя с id: {}", userId);
        userDto.setId(userId);
        return userService.update(userDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        log.debug("Удаление пользователя с id : {}", userId);
        userService.delete(userId);
    }
}