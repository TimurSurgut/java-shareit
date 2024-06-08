package shareit.item;

import lombok.*;
import lombok.experimental.FieldDefaults;
import shareit.booking.BookingLiteDto;
import shareit.request.ItemRequestDto;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemDto {

     Long id;
     String name;
     String description;
     Boolean available;
     List<ItemRequestDto> requests;
     BookingLiteDto nextBooking;
     BookingLiteDto lastBooking;
     Long ownerId;
     List<CommentDto> comments;
     Long requestId;
}
