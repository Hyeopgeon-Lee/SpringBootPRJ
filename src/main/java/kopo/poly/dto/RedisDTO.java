package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class RedisDTO {

    private String name = ""; // 이름
    private String email = ""; // 이메일
    private String addr = ""; // 주소
    private String test_text = ""; // 테스트 문구
}
