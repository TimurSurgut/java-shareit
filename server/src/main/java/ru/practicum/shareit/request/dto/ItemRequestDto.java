package ru.practicum.shareit.request.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.item.dto.RequestItemDto;
import ru.practicum.shareit.user.dto.UserDto;


import java.time.LocalDateTime;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRequestDto {

     Long id;
     String description;
     UserDto requester;
     LocalDateTime created;
     Collection<RequestItemDto> items;
}