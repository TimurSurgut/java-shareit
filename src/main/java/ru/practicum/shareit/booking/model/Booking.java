package ru.practicum.shareit.booking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "bookings")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking {
     private final String dateFormate = "yyyy-MM-dd'T'HH:mm:ss";

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     long id;
     @Column(name = "start_date")
     @JsonFormat(pattern = dateFormate)
     LocalDateTime start;
     @Column(name = "end_date")
     @JsonFormat(pattern = dateFormate)
     LocalDateTime end;
     @ManyToOne
     @JoinColumn(name = "item_id")
     Item item;
     @ManyToOne
     @JoinColumn(name = "booker_id")
     User booker;
     @Enumerated(EnumType.STRING)
     @Column(name = "status")
     BookingStatus status;

     public Long bookerId() {
          return booker != null ? booker.getId() : null;
     }

     public Long itemId() {
          return item != null ? item.getId() : null;
     }

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          Booking booking = (Booking) o;
          return id == booking.id;
     }

     @Override
     public int hashCode() {
          return Objects.hash(id);
     }
}