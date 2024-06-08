package shareit.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import shareit.item.RequestItemDto;
import shareit.user.UserDto;

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
