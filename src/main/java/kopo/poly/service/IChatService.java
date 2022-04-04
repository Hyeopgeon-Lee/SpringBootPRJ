package kopo.poly.service;

import kopo.poly.dto.ChatDTO;

import java.util.List;
import java.util.Set;


public interface IChatService {

	// 채팅 룸 리스트 가져오기
	Set<String> getRoomList() throws Exception;

	// 채팅 대화 저장하기
	List<ChatDTO> insertChat(ChatDTO pDTO) throws Exception;

	// 채팅 대화 가져오기
	List<ChatDTO> getChat(ChatDTO pDTO) throws Exception;

}

