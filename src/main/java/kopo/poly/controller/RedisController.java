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

        // Java 8부터 제공되는 Optional 활용하여 NPE(Null Pointer Exception) 처리
        RedisDTO rDTO = Optional.ofNullable(myRedisService.saveString(pDTO))
                .orElseGet(RedisDTO::new);

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

        // Java 8부터 제공되는 Optional 활용하여 NPE(Null Pointer Exception) 처리
        RedisDTO rDTO = Optional.ofNullable(myRedisService.saveStringJSON(pDTO))
                .orElseGet(RedisDTO::new);

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

        // Java 8부터 제공되는 Optional 활용하여 NPE(Null Pointer Exception) 처리
        RedisDTO rDTO = Optional.ofNullable(myRedisService.saveList(pDTO))
                .orElseGet(RedisDTO::new);

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

        // Java 8부터 제공되는 Optional 활용하여 NPE(Null Pointer Exception) 처리
        List<RedisDTO> rList = Optional.ofNullable(myRedisService.saveListJSON(pList))
                .orElseGet(ArrayList::new);

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

        // Java 8부터 제공되는 Optional 활용하여 NPE(Null Pointer Exception) 처리
        RedisDTO rDTO = Optional.ofNullable(myRedisService.saveHash(pDTO))
                .orElseGet(RedisDTO::new);

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

        // 중복된 데이터를 입력받을 수 있으며, Redis의 Set 구조가 중복제거하는지 테스트하기 위해
        // 중복된 데이터가 저장가능한 List 구조로 데이터를 저장함
        List<RedisDTO> pList = new ArrayList<>();

        for (int i = 0; i < name.length; i++) {
            RedisDTO pDTO = new RedisDTO();
            pDTO.setName(name[i]);
            pDTO.setEmail(email[i]);
            pDTO.setAddr(addr[i]);

            pList.add(pDTO); // 입력받는 값을 저장하기

            pDTO = null;

        }

        // Java 8부터 제공되는 Optional 활용하여 NPE(Null Pointer Exception) 처리
        Set<RedisDTO> rSet = Optional.ofNullable(myRedisService.saveSetJSON(pList))
                .orElseGet(HashSet::new);

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

        // 중복된 데이터를 입력받을 수 있으며, Redis의 Set 구조가 중복제거하는지 테스트하기 위해
        // 중복된 데이터가 저장가능한 List 구조로 데이터를 저장함
        List<RedisDTO> pList = new ArrayList<>();

        for (int i = 0; i < name.length; i++) {
            RedisDTO pDTO = new RedisDTO();
            pDTO.setOrder(Float.parseFloat(order[i]));
            pDTO.setName(name[i]);
            pDTO.setEmail(email[i]);
            pDTO.setAddr(addr[i]);

            pList.add(pDTO); // 입력받는 값을 저장하기

            pDTO = null;

        }

        // Java 8부터 제공되는 Optional 활용하여 NPE(Null Pointer Exception) 처리
        Set<RedisDTO> rSet = Optional.ofNullable(myRedisService.saveZSetJSON(pList))
                .orElseGet(HashSet::new);

        log.info(this.getClass().getName() + ".saveZSetJSON End!");

        return rSet;
    }
}
