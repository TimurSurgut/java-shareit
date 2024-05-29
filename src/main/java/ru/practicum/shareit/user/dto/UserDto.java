package ru.practicum.shareit.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

     Long id;
     String name;
    @Email(message = "Неверный адрес электронной почты")
     String email;
}