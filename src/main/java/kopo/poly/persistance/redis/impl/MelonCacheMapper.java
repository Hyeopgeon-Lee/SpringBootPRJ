package kopo.poly.persistance.redis.impl;

import kopo.poly.dto.MelonDTO;
import kopo.poly.persistance.redis.IMelonCacheMapper;
import kopo.poly.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component("MelonCacheMapper")
public class MelonCacheMapper implements IMelonCacheMapper {

    @Autowired
    public RedisTemplate<String, Object> redisDB;

    @Override
    public int insertSong(List<MelonDTO> pList) throws Exception {

        log.info(this.getClass().getName() + ".insertSong Start!");

        int res = 0;

        // Redis에 저장될 키
        String key = "MELON_" + DateUtil.getDateTime("yyyyMMdd");

        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(MelonDTO.class));

        for (MelonDTO pDTO : pList) {
            if (pDTO == null) {
                pDTO = new MelonDTO();

            }

            redisDB.opsForList().leftPush(key, pDTO);

        }

        // 저장된 데이터는 하루동안 보관하기
        redisDB.expire(key, 1, TimeUnit.DAYS);

        res = 1;

        log.info(this.getClass().getName() + ".insertSong End!");

        return res;
    }

    @Override
    public boolean getExistKey(String key) throws Exception {

        // 저장된 키가 존재한다면...
        if (redisDB.hasKey(key)) {
            return true;

        }{
            return false;

        }
    }

    @Override
    public List<Object> getSongList(String key) throws Exception {

        log.info(this.getClass().getName() + ".getSongList Start!");

        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(MelonDTO.class));

        List<Object> rList = null;

        // 저장된 키가 존재한다면...
        if (redisDB.hasKey(key)) {
            rList = redisDB.opsForList().range(key, 0, -1);
        }

        log.info(this.getClass().getName() + ".getSongList End!");

        return rList;
    }
}
