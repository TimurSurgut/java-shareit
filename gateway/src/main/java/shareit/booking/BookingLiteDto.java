package shareit.booking;

import lombok.*;
import lombok.experimental.FieldDefaults;
import shareit.item.ItemDto;

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
