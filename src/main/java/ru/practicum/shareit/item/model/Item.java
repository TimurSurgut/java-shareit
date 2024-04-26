package ru.practicum.shareit.item.model;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.model.User;

@Data
@Builder
public class Item {

    private long id;
    private String name;
    private String description;
    private Boolean available;
    private ItemRequest request;
    private User owner;

}