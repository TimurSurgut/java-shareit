package ru.practicum.shareit.request.model;


import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "requests")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRequest {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
      long id;
      String description;
     @ManyToOne
     @JoinColumn(name = "requester_id")
      User requester;
     @Column(name = "creation_date")
      LocalDateTime creationDate;
     @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
     List<Item> responsesToRequest;

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