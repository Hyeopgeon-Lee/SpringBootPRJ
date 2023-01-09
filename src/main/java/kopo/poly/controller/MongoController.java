package kopo.poly.controller;

import kopo.poly.dto.MongoDTO;
import kopo.poly.dto.MsgDTO;
import kopo.poly.service.IMongoService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequestMapping(value = "/mongo")
@RequiredArgsConstructor
@RestController
public class MongoController {

    private final IMongoService mongoService;

    @PostMapping(value = "test")
    public MsgDTO test(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + ".test Start!");

        int res = 0;
        String msg = ""; // 저장 결과 메시지

        String userName = CmmUtil.nvl(request.getParameter("userName")); // 이름
        String addr = CmmUtil.nvl(request.getParameter("addr")); // 주소
        String email = CmmUtil.nvl(request.getParameter("email")); // 이메일

        /*
         * ####################################################################################
         * 반드시, 값을 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함 반드시 작성할 것
         * ####################################################################################
         */
        log.info("userName: " + userName);
        log.info("addr : " + addr);
        log.info("email : " + email);

        // 데이터 저장하기 위해 DTO에 저장하기
        MongoDTO pDTO = new MongoDTO();
        pDTO.setUserName(userName);
        pDTO.setAddr(addr);
        pDTO.setEmail(email);

        if (userName.length() > 0) { // 이름은 필수 저장
            res = mongoService.mongoTest(pDTO);

        }

        if (res == 1) {
            msg = "저장 성공하였습니다.";

        } else {
            msg = "저장 실패하였습니다.";
        }
        MsgDTO dto = new MsgDTO();
        dto.setResult(res);
        dto.setMsg(msg);

        log.info(this.getClass().getName() + ".test End!");

        return dto;
    }
}

