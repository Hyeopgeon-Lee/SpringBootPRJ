package kopo.poly.controller;

import kopo.poly.dto.RedisDTO;
import kopo.poly.service.IMyRedisService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

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

        log.info(this.getClass().getName() + ".saveRedisStringJSON End!");

        return rDTO;
    }


    /**
     * List타입에 여러 문자열로 저장하기(동기화)
     */
    @GetMapping(value = "redis/saveRedisList")
    public String saveRedisList() throws Exception {

        log.info(this.getClass().getName() + ".saveRedisList Start!");

        // 수집 결과 출력
        String msg;

        int res = myRedisService.saveRedisList();

        if (res == 1) {
            msg = "success";

        } else {
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".saveRedisList End!");

        return msg;
    }

    /**
     * List타입에 여러 문자열로 저장된 데이터 가져오기
     */
    @GetMapping(value = "redis/getRedisList")
    public List<String> getRedisList() throws Exception {

        log.info(this.getClass().getName() + ".getRedisList Start!");

        List<String> rList = myRedisService.getRedisList();

        log.info(this.getClass().getName() + ".getRedisList End!");

        return rList;
    }


    /**
     * List타입에 JSON 형태로 저장하기(동기화)
     */
    @GetMapping(value = "redis/saveRedisListJSON")
    public String saveRedisListJSON() throws Exception {

        log.info(this.getClass().getName() + ".saveRedisListJSON Start!");

        // 수집 결과 출력
        String msg;

        int res = myRedisService.saveRedisListJSON();

        if (res == 1) {
            msg = "success";

        } else {
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".saveRedisListJSON End!");

        return msg;
    }

    /**
     * List타입에 JSON 형태로 저장된 데이터 가져오기
     */
    @GetMapping(value = "redis/getRedisListJSON")
    public List<RedisDTO> getRedisListJSON() throws Exception {

        log.info(this.getClass().getName() + ".getRedisListJSON Start!");

        List<RedisDTO> rList = myRedisService.getRedisListJSON();

        log.info(this.getClass().getName() + ".getRedisListJSON End!");

        return rList;
    }

    /**
     * List타입에 JSON 형태로 람다식을 이용하여 저장하기(비동기화)
     */
    @GetMapping(value = "redis/saveRedisListJSONRamda")
    public String saveRedisListJSONRamda() throws Exception {

        log.info(this.getClass().getName() + ".saveRedisListJSONRamda Start!");

        // 수집 결과 출력
        String msg;

        int res = myRedisService.saveRedisListJSONRamda();

        if (res == 1) {
            msg = "success";

        } else {
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".saveRedisListJSONRamda End!");

        return msg;
    }

    /**
     * List타입에 JSON 형태로 저장된 데이터 가져오기
     */
    @GetMapping(value = "redis/getRedisListJSONRamda")
    public List<RedisDTO> getRedisListJSONRamda() throws Exception {

        log.info(this.getClass().getName() + ".getRedisListJSONRamda Start!");

        List<RedisDTO> rList = myRedisService.getRedisListJSONRamda();

        log.info(this.getClass().getName() + ".getRedisListJSONRamda End!");

        return rList;
    }

    /**
     * Hash 타입에 문자열 형태로 저장하기
     */
    @GetMapping(value = "redis/saveRedisHash")
    public String saveRedisHash() throws Exception {

        log.info(this.getClass().getName() + ".saveRedisHash Start!");

        // 수집 결과 출력
        String msg;

        int res = myRedisService.saveRedisHash();

        if (res == 1) {
            msg = "success";

        } else {
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".saveRedisHash End!");

        return msg;
    }

    /**
     * Hash 타입에 문자열 형태로 저장된 값 가져오기
     */
    @GetMapping(value = "redis/getRedisHash")
    public RedisDTO getRedisHash() throws Exception {

        log.info(this.getClass().getName() + ".getRedisHash Start!");

        RedisDTO rDTO = myRedisService.getRedisHash();

        log.info(this.getClass().getName() + ".getRedisHash End!");

        return rDTO;
    }

    /**
     * Set타입에 JSON 형태로 람다식을 이용하여 저장하기
     */
    @GetMapping(value = "redis/saveRedisSetJSONRamda")
    public String saveRedisSetJSONRamda() throws Exception {

        log.info(this.getClass().getName() + ".saveRedisSetJSONRamda Start!");

        // 수집 결과 출력
        String msg;

        int res = myRedisService.saveRedisSetJSONRamda();

        if (res == 1) {
            msg = "success";

        } else {
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".saveRedisSetJSONRamda End!");

        return msg;
    }

    /**
     * Set타입에 JSON 형태로 람다식을 이용하여 저장된 값 가져오기
     */
    @GetMapping(value = "redis/getRedisSetJSONRamda")
    public Set<RedisDTO> getRedisSetJSONRamda() throws Exception {

        log.info(this.getClass().getName() + ".getRedisSetJSONRamda Start!");

        Set<RedisDTO> rSet = myRedisService.getRedisSetJSONRamda();

        log.info(this.getClass().getName() + ".getRedisSetJSONRamda End!");

        return rSet;
    }

    /**
     * ZSet타입에 JSON 형태로 저장하기
     */
    @GetMapping(value = "redis/saveRedisZSetJSON")
    public String saveRedisZSetJSON() throws Exception {

        log.info(this.getClass().getName() + ".saveRedisZSetJSON Start!");

        // 수집 결과 출력
        String msg;

        int res = myRedisService.saveRedisZSetJSON();

        if (res == 1) {
            msg = "success";

        } else {
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".saveRedisZSetJSON End!");

        return msg;
    }

    /**
     * ZSet타입에 JSON 형태로 저장된 값 가져오기
     */
    @GetMapping(value = "redis/getRedisZSetJSON")
    public Set<RedisDTO> getRedisZSetJSON() throws Exception {

        log.info(this.getClass().getName() + ".getRedisZSetJSON Start!");

        Set<RedisDTO> rSet = myRedisService.getRedisZSetJSON();

        log.info(this.getClass().getName() + ".getRedisZSetJSON End!");

        return rSet;
    }

    /**
     * RedisDB 데이터 삭제하기
     */
    @GetMapping(value = "redis/deleteDataJSON")
    public boolean deleteDataJSON() throws Exception {

        log.info(this.getClass().getName() + ".deleteDataJSON Start!");

        boolean res = myRedisService.deleteDataJSON();

        log.info(this.getClass().getName() + ".deleteDataJSON End!");

        return res;
    }

    /**
     * RedisDB 데이터 삭제하기
     */
    @GetMapping(value = "redis/deleteDataString")
    public boolean deleteDataString() throws Exception {

        log.info(this.getClass().getName() + ".deleteDataString Start!");

        boolean res = myRedisService.deleteDataString();

        log.info(this.getClass().getName() + ".deleteDataString End!");

        return res;
    }

}
