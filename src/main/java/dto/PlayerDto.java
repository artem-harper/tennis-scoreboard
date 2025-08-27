package dto;

import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor
@Builder
@ToString
@Getter
public class PlayerDto {
    private Integer id;
    private String name;
}
