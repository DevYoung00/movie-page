package dto;

import com.example.movieStar.domain.entity.MovieStarEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieStarDto  {
    private int star;
    private String title;
    private String subtitle;
    private String searchTitle;

    @Builder
    public MovieStarDto(String title, String subtitle, String searchTitle, int star){
        this.title = title;
        this.subtitle = subtitle;
        this.star = star;
        this.searchTitle = searchTitle;
    }

    public MovieStarEntity toEntity(){
        return MovieStarEntity.builder()
                .title(title)
                .subtitle(subtitle)
                .star(star)
                .searchTitle(searchTitle)
                .build();
    }
}
