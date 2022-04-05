package kopo.poly.controller;

import kopo.poly.dto.MovieDTO;
import kopo.poly.service.IMovieService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class MovieController {

    @Resource(name = "MovieService")
    private IMovieService movieService;

    /**
     * CGV 영화 순위 가져오기
     */
    @PostMapping(value = "rank/getMovie")
    @ResponseBody
    public List<MovieDTO> getMovie(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + ".getMovie start!");

        List<MovieDTO> rList = null;

        // 음성 명령
        String send_msg = CmmUtil.nvl(request.getParameter("send_msg"));

        log.info("send_msg : " + send_msg);

        // 영화와 비슷한 단어가 존재하면 CGV 영화 순위 가져오기 수행
        if ((send_msg.contains("영화")) || (send_msg.contains("영하")) || (send_msg.contains("연하"))
                || (send_msg.contains("연화"))) {

            rList = movieService.getMovieRank();

            if (rList == null) {
                rList = new ArrayList<>();

            }
        }

        log.info(this.getClass().getName() + ".getMovie end!");

        return rList;
    }

}
