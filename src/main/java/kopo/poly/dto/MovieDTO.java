package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class MovieDTO {

    private String collect_time; // 수집시간
    private String movie_rank; // 영화순위
    private String movie_nm; // 영화제목
    private String movie_reserve; // 예매율
    private String score; // 영화평점
    private String open_day; // 개봉일

}

