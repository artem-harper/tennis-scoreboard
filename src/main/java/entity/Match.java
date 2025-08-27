package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@ToString
@EqualsAndHashCode()
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "matches")

public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Player player1;

    @ManyToOne
    private Player player2;

    @ManyToOne
    private Player winner;


}
