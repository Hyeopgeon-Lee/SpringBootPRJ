package kopo.poly.controller;

import kopo.poly.dto.MelonDTO;
import kopo.poly.dto.MsgDTO;
import kopo.poly.service.IMelonService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequestMapping(value = "/melon")
@RequiredArgsConstructor
@RestController
public class MelonController {

    private final IMelonService melonService;

    /**
     * 멜론 노래 리스트 저장하기
     */
    @PostMapping(value = "collectMelonSong")
    public MsgDTO collectMelonSong() throws Exception {

        log.info(this.getClass().getName() + ".collectMelonSong Start!");

        // 수집 결과 출력
        String msg = "";

        int res = melonService.collectMelonSong();

        if (res == 1) {
            msg = "멜론차트 수집 성공!";

        } else {
            msg = "멜론차트 수집 실패!";
        }

        // 전달할 결과 구조 만들기
        MsgDTO dto = new MsgDTO();
        dto.setResult(res);
        dto.setMsg(msg);

        log.info(this.getClass().getName() + ".collectMelonSong End!");

        return dto;
    }

    /**
     * 오늘 수집된 멜론 노래리스트 가져오기
     */
    @PostMapping(value = "getSongList")
    public List<MelonDTO> getSongList() throws Exception {

        log.info(this.getClass().getName() + ".getSongList Start!");

        List<MelonDTO> rList = melonService.getSongList();

        log.info(this.getClass().getName() + ".getSongList End!");

        return rList;
    }

    /**
     * 가수별 수집된 노래의 수 가져오기
     */
    @PostMapping(value = "getSingerSongCnt")
    public List<MelonDTO> getSingerSongCnt()
            throws Exception {

        log.info(this.getClass().getName() + ".getSingerSongCnt Start!");

        List<MelonDTO> rList = melonService.getSingerSongCnt();

        log.info(this.getClass().getName() + ".getSingerSongCnt End!");

        return rList;
    }

    /**
     * 가수별 수집된 노래의 수 가져오기
     */
    @PostMapping(value = "getSingerSong")
    public List<MelonDTO> getSingerSong(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + ".getSingerSong Start!");

        String singer = CmmUtil.nvl(request.getParameter("singer")); // 제목

        /*
         * ####################################################################################
         * 반드시, 값을 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함 반드시 작성할 것
         * ####################################################################################
         */
        log.info("singer : " + singer);

        MelonDTO pDTO = new MelonDTO();
        pDTO.setSinger(singer);

        List<MelonDTO> rList = melonService.getSingerSong(pDTO);

        log.info(this.getClass().getName() + ".getSingerSong End!");

        return rList;
    }

    /**
     * 멜론 노래 리스트 저장하기
     */
    @PostMapping(value = "collectMelonSongMany")
    public String collectMelonSongMany() throws Exception {

        log.info(this.getClass().getName() + ".collectMelonSongMany Start!");

        // 수집 결과 출력
        String msg;

        int res = melonService.collectMelonSongMany();

        if (res == 1) {
            msg = "success";

        } else {
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".collectMelonSongMany End!");

        return msg;
    }

    /**
     * 가수 이름이 방탄소년단을 BTS로 변경하기
     */
    @GetMapping(value = "updateBTSName")
    public String updateBTSName() throws Exception {

        log.info(this.getClass().getName() + ".updateBTSName Start!");

        // 결과 출력
        String msg;

        int res = melonService.updateBTSName();

        if (res == 1) {
            msg = "success";

        } else {
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".updateBTSName End!");

        return msg;
    }

    /**
     * 가수 이름이 방탄소년단을 BTS로 변경하기
     */
    @GetMapping(value = "btsAddNickname")
    public String btsAddField() throws Exception {

        log.info(this.getClass().getName() + ".btsAddNickname Start!");

        // 결과 출력
        String msg;

        int res = melonService.updateAddBTSNickname();

        if (res == 1) {
            msg = "success";

        } else {
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".btsAddNickname End!");

        return msg;
    }

    /**
     * 가수 이름이 방탄소년단을 BTS로 변경하기
     */
    @GetMapping(value = "btsAddMember")
    public String btsAddMember() throws Exception {

        log.info(this.getClass().getName() + ".btsAddMember Start!");

        // 결과 출력
        String msg;

        int res = melonService.updateAddBTSMember();

        if (res == 1) {
            msg = "success";

        } else {
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".btsAddMember End!");

        return msg;
    }

    /**
     * 가수 이름이 방탄소년단을 BTS로 변경하기
     */
    @GetMapping(value = "updateManySong")
    public String updateManySong() throws Exception {

        log.info(this.getClass().getName() + ".updateManySong Start!");

        // 결과 출력
        String msg;

        int res = melonService.updateManySong();

        if (res == 1) {
            msg = "success";

        } else {
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".updateManySong End!");

        return msg;
    }

    /**
     * 가수 이름이 방탄소년단인 노래 삭제하기
     */
    @GetMapping(value = "deleteBTSSong")
    public String deleteBTSSong() throws Exception {

        log.info(this.getClass().getName() + ".deleteBTSSong Start!");

        // 결과 출력
        String msg;

        int res = melonService.deleteBTSSong();

        if (res == 1) {
            msg = "success";

        } else {
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".deleteBTSSong End!");

        return msg;
    }

}
