package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieReqDto {
    // 지역 검색 요청 변수에 대한 변수 생성
    private String query = "";  // 검색을 원하는 질의. UTF-8 인코딩이다.

    private int display = 1;  // 검색 결과 출력 건수를 지정한다. 최대 100까지 가능하다.

    private int  start = 1;  // 검색의 시작 위치를 지정할 수 있다. 최대 1000까지 가능하다

    private String genre = ""; //장르

    public MultiValueMap<String, String> toMultiValueMap() {
        var map = new LinkedMultiValueMap<String, String>();

        map.add("query", query);
        map.add("display", String.valueOf(display));
        map.add("start", String.valueOf(start));
        map.add("start", String.valueOf(genre));

        return map;

    }
}