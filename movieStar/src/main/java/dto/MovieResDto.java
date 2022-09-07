package dto;


import lombok.*;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class MovieResDto {
    private int display;
    private List<Item> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter @Setter
    public static class Item {
        public String title;
        public String link;
        public String image;
        public String subtitle;
        public Date pubDate;
        public String director;
        public String actor;
        public float userRating;
    }
}