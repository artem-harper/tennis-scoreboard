package entity;

import jakarta.persistence.*;
import lombok.*;


@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "players", indexes = @Index(name = "name_index", columnList = "name"))

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false, unique = true)
    private String name;
}
