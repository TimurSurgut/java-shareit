package ru.practicum.shareit.request.model;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.user.model.User;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "requests")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRequest {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
      long id;
      String description;
     @ManyToOne
     @JoinColumn(name = "requestor_id")
      User requestor;

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          ItemRequest that = (ItemRequest) o;
          return id == that.id;
     }

     @Override
     public int hashCode() {
          return Objects.hash(id);
     }
}