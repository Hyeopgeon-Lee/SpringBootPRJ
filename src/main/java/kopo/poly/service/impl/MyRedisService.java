package kopo.poly.service.impl;

import kopo.poly.dto.RedisDTO;
import kopo.poly.persistance.redis.IMyRedisMapper;
import kopo.poly.service.IMyRedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class MyRedisService implements IMyRedisService {

    private final IMyRedisMapper myRedisMapper;

    @Override
    public RedisDTO saveString(RedisDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".saveString Start!");

        // 저장할 RedisDB 키
        String redisKey = "myRedis_String";

        // 저장 결과
        RedisDTO rDTO = null;

        int res = myRedisMapper.saveString(redisKey, pDTO);

        if (res == 1) { // Redis 저장이 성공하면, 저장된 데이터 가져오기
            rDTO = myRedisMapper.getString(redisKey);

        } else {
            log.info("Redis 저장 실패!!");
            new Exception("Redis 저장 실패!!");

        }

        log.info(this.getClass().getName() + ".saveString End!");

        return rDTO;
    }

    @Override
    public RedisDTO saveStringJSON(RedisDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".saveStringJSON Start!");

        // 저장할 RedisDB 키
        String redisKey = "myRedis_String_JSON";

        // 저장 결과
        RedisDTO rDTO = null;

        int res = myRedisMapper.saveStringJSON(redisKey, pDTO);

        if (res == 1) { // Redis 저장이 성공하면, 저장된 데이터 가져오기
            rDTO = myRedisMapper.getStringJSON(redisKey);

        } else {
            log.info("Redis 저장 실패!!");
            new Exception("Redis 저장 실패!!");

        }
        log.info(this.getClass().getName() + ".saveStringJSON End!");

        return rDTO;
    }

    @Override
    public RedisDTO saveList(RedisDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".saveList Start!");

        // 저장할 RedisDB 키
        String redisKey = "myRedis_List";

        // 저장 결과
        RedisDTO rDTO = null;

        int res = myRedisMapper.saveList(redisKey, pDTO);

        if (res == 1) { // Redis 저장이 성공하면, 저장된 데이터 가져오기
            rDTO = myRedisMapper.getList(redisKey);

        } else {
            log.info("Redis 저장 실패!!");
            new Exception("Redis 저장 실패!!");

        }

        log.info(this.getClass().getName() + ".saveList End!");

        return rDTO;
    }

    @Override
    public List<RedisDTO> saveListJSON(List<RedisDTO> pList) throws Exception {

        log.info(this.getClass().getName() + ".saveListJSON Start!");

        // 저장할 RedisDB 키
        String redisKey = "myRedis_List_JSON";

        // 저장 결과
        List<RedisDTO> rList = null;

        int res = myRedisMapper.saveListJSON(redisKey, pList);

        if (res == 1) { // Redis 저장이 성공하면, 저장된 데이터 가져오기
            rList = myRedisMapper.getListJSON(redisKey);

        } else {
            log.info("Redis 저장 실패!!");
            new Exception("Redis 저장 실패!!");

        }

        log.info(this.getClass().getName() + ".saveListJSON End!");

        return rList;
    }

    @Override
    public RedisDTO saveHash(RedisDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".saveHash Start!");

        // 저장할 RedisDB 키
        String redisKey = "myRedis_Hash";

        // 저장 결과
        RedisDTO rDTO = null;

        int res = myRedisMapper.saveHash(redisKey, pDTO);

        if (res == 1) { // Redis 저장이 성공하면, 저장된 데이터 가져오기
            rDTO = myRedisMapper.getHash(redisKey);

        } else {
            log.info("Redis 저장 실패!!");
            new Exception("Redis 저장 실패!!");

        }

        log.info(this.getClass().getName() + ".saveHash End!");

        return rDTO;
    }

    @Override
    public Set<RedisDTO> saveSetJSON(List<RedisDTO> pList) throws Exception {

        log.info(this.getClass().getName() + ".saveSetJSON Start!");

        // 저장할 RedisDB 키
        String redisKey = "myRedis_Set_JSON";

        // 저장 결과
        Set<RedisDTO> rSet = null;

        int res = myRedisMapper.saveSetJSON(redisKey, pList);

        if (res == 1) { // Redis 저장이 성공하면, 저장된 데이터 가져오기
            rSet = myRedisMapper.getSetJSON(redisKey);

        } else {
            log.info("Redis 저장 실패!!");
            new Exception("Redis 저장 실패!!");

        }

        log.info(this.getClass().getName() + ".saveSetJSON End!");

        return rSet;
    }

    @Override
    public Set<RedisDTO> saveZSetJSON(List<RedisDTO> pList) throws Exception {

        log.info(this.getClass().getName() + ".saveZSetJSON Start!");

        // 저장할 RedisDB 키
        String redisKey = "myRedis_ZSet_JSON";

        // 저장 결과
        Set<RedisDTO> rSet = null;

        int res = myRedisMapper.saveZSetJSON(redisKey, pList);

        if (res == 1) { // Redis 저장이 성공하면, 저장된 데이터 가져오기
            rSet = myRedisMapper.getZSetJSON(redisKey);

        } else {
            log.info("Redis 저장 실패!!");
            new Exception("Redis 저장 실패!!");

        }

        log.info(this.getClass().getName() + ".saveZSetJSON End!");

        return rSet;
    }

}
