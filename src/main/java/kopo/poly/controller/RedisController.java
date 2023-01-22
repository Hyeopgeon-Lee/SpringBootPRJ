package kopo.poly.controller;

import kopo.poly.dto.RedisDTO;
import kopo.poly.service.IMyRedisService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@RequestMapping(value = "/redis")
@RequiredArgsConstructor
@RestController
public class RedisController {

    private final IMyRedisService myRedisService;

    /**
     * Redis 문자열 저장 실습
     */
    @PostMapping(value = "saveString")
    public RedisDTO saveString(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + ".saveString Start!");

        String text = CmmUtil.nvl(request.getParameter("text")); // 저장할 문자열

        /*
         * ####################################################################################
         * 반드시, 값을 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함 반드시 작성할 것
         * ####################################################################################
         */
        log.info("text : " + text);

        RedisDTO pDTO = new RedisDTO();
        pDTO.setText(text);

        RedisDTO rDTO = myRedisService.saveString(pDTO);

        if (rDTO == null) {
            rDTO = new RedisDTO();

        }

        log.info(this.getClass().getName() + ".saveString End!");

        return rDTO;
    }


    /**
     * Redis 문자열을 JSON으로 저장 실습
     */
    @PostMapping(value = "saveStringJSON")
    public RedisDTO saveStringJSON(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + ".saveStringJSON Start!");

        String name = CmmUtil.nvl(request.getParameter("name")); // 이름
        String email = CmmUtil.nvl(request.getParameter("email")); // 이메일
        String addr = CmmUtil.nvl(request.getParameter("addr")); // 주소

        /*
         * ####################################################################################
         * 반드시, 값을 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함 반드시 작성할 것
         * ####################################################################################
         */
        log.info("name : " + name);
        log.info("email : " + email);
        log.info("addr : " + addr);

        RedisDTO pDTO = new RedisDTO();
        pDTO.setName(name);
        pDTO.setEmail(email);
        pDTO.setAddr(addr);

        RedisDTO rDTO = myRedisService.saveStringJSON(pDTO);

        if (rDTO == null) {
            rDTO = new RedisDTO();

        }

        log.info(this.getClass().getName() + ".saveStringJSON End!");

        return rDTO;
    }


    /**
     * List타입에 여러 문자열로 저장하기(동기화)
     */
    @PostMapping(value = "saveList")
    public RedisDTO saveList(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + ".saveList Start!");

        // 가수 그룹 이름은 여러명 입력될 수 있기에 배열로 받음
        // 배열로 받는 방법 : <input type="text" name="text" />의 name 속성 값이 동일하면 배열로 받아짐
        String[] text = request.getParameterValues("text");

        // String[] 구조를 List<String> 구조로 변환하기
        List<String> texts = Arrays.asList(text);

        RedisDTO pDTO = new RedisDTO();
        pDTO.setTexts(texts);

        RedisDTO rDTO = myRedisService.saveList(pDTO);

        if (rDTO == null) {
            rDTO = new RedisDTO();

        }

        log.info(this.getClass().getName() + ".saveList End!");

        return rDTO;
    }


    /**
     * List타입에 JSON 형태로 저장하기(동기화)
     */
    @PostMapping(value = "saveListJSON")
    public List<RedisDTO> saveListJSON(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + "saveListJSON. Start!");

        // 배열로 받는 방법 : <input type="text" name="name" />의 name 속성 값이 동일하면 배열로 받아짐
        String[] name = request.getParameterValues("name");
        String[] email = request.getParameterValues("email");
        String[] addr = request.getParameterValues("addr");

        List<RedisDTO> pList = new ArrayList<>();

        for (int i = 0; i < name.length; i++) {
            RedisDTO pDTO = new RedisDTO();
            pDTO.setName(name[i]);
            pDTO.setEmail(email[i]);
            pDTO.setAddr(addr[i]);

            pList.add(pDTO); // 입력받는 값을 저장하기

            pDTO = null;

        }

        List<RedisDTO> rList = myRedisService.saveListJSON(pList);

        if (rList == null) {
            rList = new ArrayList<>();

        }

        log.info(this.getClass().getName() + ".saveListJSON End!");

        return rList;
    }

    /**
     * Hash 타입에 문자열 형태로 저장하기
     */
    @PostMapping(value = "saveHash")
    public RedisDTO saveHash(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + ".saveHash Start!");

        String name = CmmUtil.nvl(request.getParameter("name")); // 이름
        String email = CmmUtil.nvl(request.getParameter("email")); // 이메일
        String addr = CmmUtil.nvl(request.getParameter("addr")); // 주소

        /*
         * ####################################################################################
         * 반드시, 값을 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함 반드시 작성할 것
         * ####################################################################################
         */
        log.info("name : " + name);
        log.info("email : " + email);
        log.info("addr : " + addr);

        RedisDTO pDTO = new RedisDTO();
        pDTO.setName(name);
        pDTO.setEmail(email);
        pDTO.setAddr(addr);

        RedisDTO rDTO = myRedisService.saveHash(pDTO);

        if (rDTO == null) {
            rDTO = new RedisDTO();

        }

        log.info(this.getClass().getName() + ".saveHash End!");

        return rDTO;
    }

    /**
     * Set타입에 JSON 형태로 람다식을 이용하여 저장하기
     */
    @PostMapping(value = "saveSetJSON")
    public Set<RedisDTO> saveSetJSON(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + ".saveSetJSON Start!");

        // 배열로 받는 방법 : <input type="text" name="name" />의 name 속성 값이 동일하면 배열로 받아짐
        String[] name = request.getParameterValues("name");
        String[] email = request.getParameterValues("email");
        String[] addr = request.getParameterValues("addr");

        Set<RedisDTO> pSet = new HashSet<>();

        for (int i = 0; i < name.length; i++) {
            RedisDTO pDTO = new RedisDTO();
            pDTO.setName(name[i]);
            pDTO.setEmail(email[i]);
            pDTO.setAddr(addr[i]);

            pSet.add(pDTO); // 입력받는 값을 저장하기

            pDTO = null;

        }

        Set<RedisDTO> rSet = myRedisService.saveSetJSON(pSet);

        if (rSet == null) {
            rSet = new HashSet<>();

        }

        log.info(this.getClass().getName() + ".saveSetJSON End!");

        return rSet;
    }

    /**
     * ZSet타입에 JSON 형태로 저장하기
     */
    @PostMapping(value = "saveZSetJSON")
    public Set<RedisDTO> saveRedisZSetJSON(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + ".saveZSetJSON Start!");

        // 배열로 받는 방법 : <input type="text" name="order" />의 name 속성 값이 동일하면 배열로 받아짐
        String[] order = request.getParameterValues("order");
        String[] name = request.getParameterValues("name");
        String[] email = request.getParameterValues("email");
        String[] addr = request.getParameterValues("addr");

        Set<RedisDTO> pSet = new HashSet<>();

        for (int i = 0; i < name.length; i++) {
            RedisDTO pDTO = new RedisDTO();
            pDTO.setOrder(Float.parseFloat(order[i]));
            pDTO.setName(name[i]);
            pDTO.setEmail(email[i]);
            pDTO.setAddr(addr[i]);

            pSet.add(pDTO); // 입력받는 값을 저장하기

            pDTO = null;

        }

        Set<RedisDTO> rSet = myRedisService.saveZSetJSON(pSet);

        if (rSet == null) {
            rSet = new HashSet<>();

        }

        log.info(this.getClass().getName() + ".saveZSetJSON End!");

        return rSet;
    }
}
