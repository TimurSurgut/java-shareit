package ru.practicum.shareit.item.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.request.model.ItemRequest;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemDto {

     Long id;
     String name;
     String description;
     Boolean available;
     ItemRequest request;
}