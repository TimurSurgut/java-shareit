package ru.practicum.shareit.user.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.model.Item;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Table(name = "users")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
      long id;
      String name;
     @Column(unique = true)
      String email;

     @OneToMany(mappedBy = "owner")
     List<Item> items;

     @OneToMany(mappedBy = "booker", cascade = CascadeType.ALL)
      List<Booking> bookings;

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          User user = (User) o;
          return id == user.id;
     }

     @Override
     public int hashCode() {
          return Objects.hash(id);
     }
}