package kopo.poly.persistance.redis.impl;

import kopo.poly.dto.MelonDTO;
import kopo.poly.persistance.redis.IMelonCacheMapper;
import kopo.poly.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component("MelonCacheMapper")
public class MelonCacheMapper implements IMelonCacheMapper {

    public final RedisTemplate<String, Object> redisDB;

    public MelonCacheMapper(RedisTemplate<String, Object> redisDB) {
        this.redisDB = redisDB;
    }

    @Override
    public int insertSong(List<MelonDTO> pList, String redisKey) throws Exception {

        log.info(this.getClass().getName() + ".insertSong Start!");

        int res = 0;

        // Redis에 저장될 키
        String key = "MELON_" + DateUtil.getDateTime("yyyyMMdd");

        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(MelonDTO.class));

        // 람다식으로 데이터 저장하기
        pList.forEach(melon -> redisDB.opsForList().leftPush(key, melon));

        // 저장된 데이터는 1시간동안 보관하기
        redisDB.expire(key, 1, TimeUnit.HOURS);

        res = 1;

        log.info(this.getClass().getName() + ".insertSong End!");

        return res;
    }

    @Override
    public boolean getExistKey(String key) throws Exception {

        // 저장된 키가 존재한다면...
        if (redisDB.hasKey(key)) {
            return true;

        } else {
            return false;

        }
    }

    @Override
    public List<MelonDTO> getSongList(String key) throws Exception {

        log.info(this.getClass().getName() + ".getSongList Start!");

        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(MelonDTO.class));

        List<MelonDTO> rList = null;

        // 저장된 키가 존재한다면...
        if (redisDB.hasKey(key)) {
            rList = (List) redisDB.opsForList().range(key, 0, -1);
        }

        log.info(this.getClass().getName() + ".getSongList End!");

        return rList;
    }
}

