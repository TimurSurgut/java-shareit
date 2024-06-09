package shareit.item;

import lombok.*;
import lombok.experimental.FieldDefaults;
import shareit.item.ItemDto;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentDto {

     Long id;
     String authorName;
     ItemDto item;
     String text;
     LocalDateTime created;
}
