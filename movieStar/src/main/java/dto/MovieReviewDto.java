package dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class MovieReviewDto {

    private String title;
    private String Review;
    private int star;
    private String name;

    public MovieReviewDto(String title, String Review, int star, String name) {
        this.title = title;
        this.Review = Review;
        this.star = star;
        this.name = name;
    }
}
