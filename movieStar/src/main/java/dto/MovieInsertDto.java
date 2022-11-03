package dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieInsertDto {

    private String title;
    private String img;


}
