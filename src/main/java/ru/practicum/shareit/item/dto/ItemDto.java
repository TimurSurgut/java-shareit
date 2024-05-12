package ru.practicum.shareit.item.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.booking.dto.BookingLiteDto;
import ru.practicum.shareit.item.dto.comment.CommentDto;
import ru.practicum.shareit.request.model.ItemRequest;

import java.util.List;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemDto {

      Long id;
      String name;
      String description;
      Boolean available;
      List<ItemRequest> requests;
      BookingLiteDto nextBooking;
      BookingLiteDto lastBooking;
      Long ownerId;
      List<CommentDto> comments;
}