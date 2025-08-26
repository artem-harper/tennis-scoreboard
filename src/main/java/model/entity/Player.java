package model.entity;

import jakarta.persistence.*;
import lombok.*;


@ToString
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "players", indexes = @Index(name = "name_index", columnList = "name"))

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(nullable = false, unique = true)
    private String name;
}
