package kopo.poly.persistance.redis.impl;

import kopo.poly.dto.MovieDTO;
import kopo.poly.persistance.redis.IMovieMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Component
public class MovieMapper implements IMovieMapper {

    public final RedisTemplate<String, Object> redisDB;

    /**
     * 수집 및 조회 요청시 1시간씩 유효시간 연장하기
     *
     * @param redisKey 저장된 키 이름
     * @return 유효시간 설정 성공여부
     */
    private boolean setTimeOutHour(String redisKey) throws Exception {
        log.info(this.getClass().getName() + ".setTimeOutHour Start!");
        return redisDB.expire(redisKey, 1, TimeUnit.HOURS);
    }

    @Override
    public int insertMovie(MovieDTO pDTO, String redisKey) throws Exception {
        log.info(this.getClass().getName() + ".insertMovie Start!");

        int res = 0;

        // redisDB의 키의 데이터 타입을 String으로 정의(항상 String으로 설정함)
        redisDB.setKeySerializer(new StringRedisSerializer()); // String 타입

        // MovieDTO에 저장된 데이터를 자동으로 JSON으로 변경하기
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(MovieDTO.class));

        // 오름차순으로 저장하기
        redisDB.opsForList().rightPush(redisKey, pDTO);

        // 데이터 유효시간 1시간 연장하기
        this.setTimeOutHour(redisKey);

        res = 1;

        log.info(this.getClass().getName() + ".insertMovie End!");

        return res;
    }

    @Override
    public boolean getExistKey(String redisKey) throws Exception {
        log.info(this.getClass().getName() + ".getExistKey Start!");

        // 저장된 키가 존재한다면...
        if (redisDB.hasKey(redisKey)) {
            return true;

        } else {
            return false;

        }
    }

    @Override
    public List<MovieDTO> getMovieList(String redisKey) throws Exception {
        log.info(this.getClass().getName() + ".getMovieList Start!");

        // 결과 값 저장할 객체
        List<MovieDTO> rList = null;

        // redisDB의 키의 데이터 타입을 String으로 정의(항상 String으로 설정함)
        redisDB.setKeySerializer(new StringRedisSerializer()); // String 타입

        // RedisDTO에 저장된 데이터를 자동으로 JSON으로 변경하기
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(MovieDTO.class));

        if (redisDB.hasKey(redisKey)) {
            rList = (List) redisDB.opsForList().range(redisKey, 0, -1);

            // 데이터 유효시간 1시간 연장하기
            this.setTimeOutHour(redisKey);

        }

        log.info(this.getClass().getName() + ".getMovieList End!");

        return rList;
    }
}
