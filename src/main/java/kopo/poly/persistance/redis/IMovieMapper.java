package kopo.poly.persistance.redis;

import kopo.poly.dto.MovieDTO;

import java.util.List;

public interface IMovieMapper {

    /**
     * CGV 영화 정보 저장하기
     *
     * @param pDTO     저장할 데이터
     * @param redisKey 저장할 키
     * @return 저장 결과
     */
    int insertMovie(MovieDTO pDTO, String redisKey) throws Exception;

    /**
     * 수집된 영화 정보 존재여부 체크하기
     *
     * @param redisKey 저장된 키 이름
     * @return key존재여부
     */
    boolean getExistKey(String redisKey) throws Exception;

    /**
     * 1시간 이내 수집 및 호출된 영화 정보가져오기
     *
     * @param redisKey 저장된 키 이름
     * @return 영화정보
     */
    List<MovieDTO> getMovieList(String redisKey) throws Exception;

}
