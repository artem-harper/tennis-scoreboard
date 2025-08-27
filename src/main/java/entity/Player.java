package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@ToString
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@Table(name = "players", indexes = @Index(name = "name_index", columnList = "name"))

public class Player implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;
}
