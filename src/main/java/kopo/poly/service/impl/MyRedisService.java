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
@Service("MyRedisService")
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
    public int saveRedisList() throws Exception {

        log.info(this.getClass().getName() + ".saveRedisList Start!");

        String redisKey = "myRedis_List";

        List<RedisDTO> pList = new LinkedList<>();

        for (int i = 0; i < 10; i++) {

            RedisDTO pDTO = new RedisDTO();
            pDTO.setText(i + "번째 데이터입니다.");

            pList.add(pDTO);
            pDTO = null;
        }

        int res = myRedisMapper.saveRedisList(redisKey, pList);

        log.info(this.getClass().getName() + ".saveRedisList End!");

        return res;
    }

    @Override
    public List<String> getRedisList() throws Exception {

        log.info(this.getClass().getName() + ".getRedisList Start!");

        String redisKey = "myRedis_List";

        List<String> rList = myRedisMapper.getRedisList(redisKey);

        if (rList == null) {
            rList = new LinkedList<>();

        }

        log.info(this.getClass().getName() + ".getRedisList End!");

        return rList;
    }

    @Override
    public int saveRedisListJSON() throws Exception {
        log.info(this.getClass().getName() + ".saveRedisListJSON Start!");

        String redisKey = "myRedis_List_JSON";

        List<RedisDTO> pList = new LinkedList<>();

        for (int i = 0; i < 10; i++) {

            RedisDTO pDTO = new RedisDTO();
            pDTO.setText(i + "번째 데이터입니다.");
            pDTO.setName("이협건[" + i + "]");
            pDTO.setAddr("서울");
            pDTO.setEmail("hglee67@kopo.ac.kr");

            pList.add(pDTO);
            pDTO = null;
        }

        int res = myRedisMapper.saveRedisListJSON(redisKey, pList);

        log.info(this.getClass().getName() + ".saveRedisListJSON End!");

        return res;
    }

    @Override
    public List<RedisDTO> getRedisListJSON() throws Exception {

        log.info(this.getClass().getName() + ".getRedisListJSON Start!");

        String redisKey = "myRedis_List_JSON";

        List<RedisDTO> rList = myRedisMapper.getRedisListJSON(redisKey);

        if (rList == null) {
            rList = new LinkedList<>();

        }

        log.info(this.getClass().getName() + ".getRedisListJSON End!");

        return rList;
    }

    @Override
    public int saveRedisListJSONRamda() throws Exception {
        log.info(this.getClass().getName() + ".saveRedisListJSONRamda Start!");

        String redisKey = "myRedis_List_JSON_Ramda";

        List<RedisDTO> pList = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {

            RedisDTO pDTO = new RedisDTO();
            pDTO.setText(i + "번째 데이터입니다.");
            pDTO.setName("이협건[" + i + "]");
            pDTO.setAddr("서울");
            pDTO.setEmail("hglee67@kopo.ac.kr");

            pList.add(pDTO);
            pDTO = null;
        }

        int res = myRedisMapper.saveRedisListJSONRamda(redisKey, pList);

        log.info(this.getClass().getName() + ".saveRedisListJSONRamda End!");

        return res;
    }

    @Override
    public List<RedisDTO> getRedisListJSONRamda() throws Exception {

        log.info(this.getClass().getName() + ".getRedisListJSONRamda Start!");

        String redisKey = "myRedis_List_JSON_Ramda";

        List<RedisDTO> rList = myRedisMapper.getRedisListJSON(redisKey);

        if (rList == null) {
            rList = new LinkedList<>();

        }

        log.info(this.getClass().getName() + ".getRedisListJSONRamda End!");

        return rList;
    }

    @Override
    public int saveRedisHash() throws Exception {

        log.info(this.getClass().getName() + ".saveRedisHash Start!");

        String redisKey = "myRedis_Hash";

        RedisDTO pDTO = new RedisDTO();
        pDTO.setName("이협건");
        pDTO.setAddr("서울");
        pDTO.setEmail("hglee67@kopo.ac.kr");

        int res = myRedisMapper.saveRedisHash(redisKey, pDTO);

        log.info(this.getClass().getName() + ".saveRedisHash End!");

        return res;
    }

    @Override
    public RedisDTO getRedisHash() throws Exception {

        log.info(this.getClass().getName() + ".getRedisHash Start!");

        String redisKey = "myRedis_Hash";

        RedisDTO rDTO = myRedisMapper.getRedisHash(redisKey);

        if (rDTO == null) {
            rDTO = new RedisDTO();
        }

        log.info(this.getClass().getName() + ".getRedisHash End!");

        return rDTO;
    }

    @Override
    public int saveRedisSetJSONRamda() throws Exception {

        log.info(this.getClass().getName() + ".saveRedisSetJSONRamda Start!");

        String redisKey = "myRedis_Set_JSON";

        Set<RedisDTO> pSet = new HashSet<>();

        for (int i = 0; i < 10; i++) {

            RedisDTO pDTO = new RedisDTO();
            pDTO.setText(i + "번째 데이터입니다.");
            pDTO.setName("이협건[" + i + "]");
            pDTO.setAddr("서울");
            pDTO.setEmail("hglee67@kopo.ac.kr");

            pSet.add(pDTO);
            pDTO = null;
        }

        int res = myRedisMapper.saveRedisSetJSONRamda(redisKey, pSet);

        log.info(this.getClass().getName() + ".saveRedisSetJSONRamda End!");

        return res;
    }

    @Override
    public Set<RedisDTO> getRedisSetJSONRamda() throws Exception {

        log.info(this.getClass().getName() + ".getRedisSetJSONRamda Start!");

        String redisKey = "myRedis_Set_JSON";

        Set<RedisDTO> rSet = myRedisMapper.getRedisSetJSONRamda(redisKey);

        if (rSet == null) {
            rSet = new HashSet<>();

        }

        log.info(this.getClass().getName() + ".getRedisSetJSONRamda End!");

        return rSet;
    }

    @Override
    public int saveRedisZSetJSON() throws Exception {
        log.info(this.getClass().getName() + ".saveRedisZSetJSON Start!");

        String redisKey = "myRedis_Zset_JSON";

        List<RedisDTO> pList = new LinkedList<>();

        for (int i = 0; i < 10; i++) {

            RedisDTO pDTO = new RedisDTO();
            pDTO.setText(i + "번째 데이터입니다.");
            pDTO.setName("이협건[" + i + "]");
            pDTO.setAddr("서울");
            pDTO.setEmail("hglee67@kopo.ac.kr");

            pList.add(pDTO);
            pDTO = null;
        }

        int res = myRedisMapper.saveRedisZSetJSON(redisKey, pList);

        log.info(this.getClass().getName() + ".saveRedisZSetJSON End!");

        return res;
    }

    @Override
    public Set<RedisDTO> getRedisZSetJSON() throws Exception {

        log.info(this.getClass().getName() + ".getRedisZSetJSON Start!");

        String redisKey = "myRedis_Zset_JSON";

        Set<RedisDTO> rSet = myRedisMapper.getRedisZSetJSON(redisKey);

        if (rSet == null) {
            rSet = new HashSet<>();

        }

        log.info(this.getClass().getName() + ".getRedisZSetJSON End!");

        return rSet;
    }

    @Override
    public boolean deleteDataJSON() throws Exception {

        log.info(this.getClass().getName() + ".deleteDate Start!");

        String redisKey = "myRedis_Zset_JSON";

        boolean res = myRedisMapper.deleteDataJSON(redisKey);

        log.info(this.getClass().getName() + ".deleteDate End!");

        return res;
    }

    @Override
    public boolean deleteDataString() throws Exception {

        log.info(this.getClass().getName() + ".deleteDataString Start!");

        String redisKey = "myRedis_Hash";

        boolean res = myRedisMapper.deleteDataJSON(redisKey);

        log.info(this.getClass().getName() + ".deleteDataString End!");

        return res;
    }
}
