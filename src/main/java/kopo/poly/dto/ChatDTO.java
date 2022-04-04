package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class ChatDTO {

	private String roomKey = "";
	private String userNm = "";
	private String msg = "";
	private String dateTime = "";

}

