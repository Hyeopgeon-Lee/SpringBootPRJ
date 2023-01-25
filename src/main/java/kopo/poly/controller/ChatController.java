package kopo.poly.controller;

import kopo.poly.dto.ChatDTO;
import kopo.poly.service.IChatService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Slf4j
@RequestMapping(value = "/chat")
@RequiredArgsConstructor
@Controller
public class ChatController {

    private final IChatService chatService;

    /**
     * 채팅방 초기화면
     */
    @GetMapping(value = "index")
    public String index() {

        log.info(this.getClass().getName() + ".index Start!");

        log.info(this.getClass().getName() + ".index End!");

        return "chat/index";
    }

    /**
     * 채팅방 입장
     */
    @PostMapping(value = "intro")
    public String intro(HttpServletRequest request, HttpSession session)
            throws Exception {

        log.info(this.getClass().getName() + ".intro Start!");

        // 기존 세션에 저장된 값 삭제하기
        session.setAttribute("SS_ROOM_NAME", "");
        session.setAttribute("SS_USER_NAME", "");

        String room_name = CmmUtil.nvl(request.getParameter("room_name"));
        String userNm = CmmUtil.nvl(request.getParameter("user_name"));

        log.info("userNm : " + userNm);
        log.info("room_name : " + room_name);

        // 세션에 값 저장하기
        session.setAttribute("SS_ROOM_NAME", room_name);
        session.setAttribute("SS_USER_NAME", userNm);

        // 입장 환영 멘트 저장하기
        ChatDTO pDTO = new ChatDTO();

        pDTO.setRoomKey("Chat_" + room_name);
        pDTO.setUserNm("관리자.");
        pDTO.setMsg(userNm + "님! [" + room_name + "] 채팅방 입장을 환영합니다.");
        pDTO.setDateTime(DateUtil.getDateTime("yyyy.MM.dd hh:mm:ss"));

        // 채팅 멘트 저장하기
        chatService.insertChat(pDTO);

        log.info(this.getClass().getName() + ".intro End!");

        return "chat/intro";
    }

    /**
     * 채팅방 전체 리스트가져오기
     */
    @PostMapping(value = "roomList")
    @ResponseBody
    public Set<String> roomList() throws Exception {

        log.info(this.getClass().getName() + ".roomList Start!");

        // Java 8부터 제공되는 Optional 활용하여 NPE(Null Pointer Exception) 처리
        Set<String> rSet = Optional.ofNullable(chatService.getRoomList())
                .orElseGet(HashSet::new);

        log.info(this.getClass().getName() + ".roomList End!");

        return rSet;
    }

    /**
     * 음성대화 저장
     */
    @PostMapping(value = "msg")
    @ResponseBody
    public List<ChatDTO> msg(HttpServletRequest request, HttpSession session)
            throws Exception {

        log.info(this.getClass().getName() + ".msg Start!");

        String room_name = CmmUtil.nvl((String) session.getAttribute("SS_ROOM_NAME"));
        String userNm = CmmUtil.nvl((String) session.getAttribute("SS_USER_NAME"));

        String msg = CmmUtil.nvl(request.getParameter("send_msg"));

        log.info("userNm : " + userNm);
        log.info("room_name : " + room_name);
        log.info("msg : " + msg);

        List<ChatDTO> rList = null;

        // 음성 메시지가 존재하는 경우에만 대화가져오기
        if (msg.length() > 0) {
            ChatDTO pDTO = new ChatDTO();

            pDTO.setRoomKey("Chat_" + room_name);
            pDTO.setUserNm(userNm);
            pDTO.setMsg(msg);
            pDTO.setDateTime(DateUtil.getDateTime("yyyy.MM.dd hh:mm:ss"));

            // Java 8부터 제공되는 Optional 활용하여 NPE(Null Pointer Exception) 처리
            rList = Optional.ofNullable(chatService.insertChat(pDTO))
                    .orElseGet(ArrayList::new);

            pDTO = null;

        }

        log.info(this.getClass().getName() + ".msg End!");

        return rList;
    }

    /**
     * 음성대화 가져오기
     */
    @PostMapping(value = "getMsg")
    @ResponseBody
    public List<ChatDTO> getMsg(HttpSession session)
            throws Exception {

        log.info(this.getClass().getName() + ".getMsg Start!");

        String room_name = CmmUtil.nvl((String) session.getAttribute("SS_ROOM_NAME"));

        log.info("room_name : " + room_name);

        ChatDTO pDTO = new ChatDTO();

        pDTO.setRoomKey("Chat_" + room_name);

        // Java 8부터 제공되는 Optional 활용하여 NPE(Null Pointer Exception) 처리
        List<ChatDTO> rList = Optional.ofNullable(chatService.getChat(pDTO))
                .orElseGet(ArrayList::new);

        pDTO = null;

        log.info(this.getClass().getName() + ".getMsg End!");

        return rList;
    }

}
