package kopo.poly.service;

import kopo.poly.dto.MovieDTO;

import java.util.List;

public interface IMovieService {

    /**
     * 영화 정보가져오기
     */
    List<MovieDTO> getMovieRank() throws Exception;
}
