package ru.practicum.shareit.booking.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.booking.model.BookingStatus;
import ru.practicum.shareit.item.dto.ItemDto;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingLiteDto {

     Long id;
     LocalDateTime start;
     LocalDateTime end;
     ItemDto item;
     Long bookerId;
     BookingStatus status;
     Long itemId;
}