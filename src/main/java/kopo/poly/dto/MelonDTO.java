package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class MelonDTO {

    String collectTime; // 수집 시간
    String song; // 노래 제목
    String singer; // 가수

}

