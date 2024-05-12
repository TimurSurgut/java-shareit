package ru.practicum.shareit.item.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.model.User;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Item {

     long id;
     String name;
     String description;
     Boolean available;
     ItemRequest request;
     User owner;

}