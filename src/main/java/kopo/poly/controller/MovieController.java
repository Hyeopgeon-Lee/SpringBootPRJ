package kopo.poly.controller;

import kopo.poly.dto.MovieDTO;
import kopo.poly.service.IMovieService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping(value = "/rank")
@RequiredArgsConstructor
@RestController
public class MovieController {

    private final IMovieService movieService;

    /**
     * CGV 영화 순위 가져오기
     */
    @PostMapping(value = "getMovie")
    public List<MovieDTO> getMovie(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + ".getMovie start!");

        List<MovieDTO> rList = null;

        // 음성 명령
        String send_msg = CmmUtil.nvl(request.getParameter("send_msg"));

        log.info("send_msg : " + send_msg);

        // 영화와 비슷한 단어가 존재하면 CGV 영화 순위 가져오기 수행
        if ((send_msg.contains("영화")) || (send_msg.contains("영하")) || (send_msg.contains("연하"))
                || (send_msg.contains("연화"))) {

            // Java 8부터 제공되는 Optional 활용하여 NPE(Null Pointer Exception) 처리
            rList = Optional.ofNullable(movieService.getMovieRank()).orElseGet(ArrayList::new);

        }

        log.info(this.getClass().getName() + ".getMovie end!");

        return rList;
    }
}
