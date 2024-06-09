package shareit.item;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestItemDto {

    Long id;
    String name;
    Long ownerId;
    String description;
    Boolean available;
    Long requestId;
}
