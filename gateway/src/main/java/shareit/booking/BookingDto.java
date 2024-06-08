package shareit.booking;

import lombok.*;
import lombok.experimental.FieldDefaults;
import shareit.item.ItemDto;
import shareit.user.UserDto;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingDto {

     Long id;
     LocalDateTime start;
     LocalDateTime end;
     ItemDto item;
     UserDto booker;
     BookingStatus status;
     Long itemId;
}
