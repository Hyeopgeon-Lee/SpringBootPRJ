package kopo.poly.service.impl;

import kopo.poly.dto.RedisDTO;
import kopo.poly.persistance.redis.IMyRedisMapper;
import kopo.poly.service.IMyRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service("MyRedisService")
public class MyRedisService implements IMyRedisService {

    @Resource(name = "MyRedisMapper")
    private IMyRedisMapper myRedisMapper;


    @Override
    public int saveRedisString() throws Exception {
        log.info(this.getClass().getName() + ".saveRedisString Start!");

        String redisKey = "myRedis_String";

        RedisDTO pDTO = new RedisDTO();
        pDTO.setTest_text("난 String타입으로 저장할 일반 문자열이다.");

        int res = myRedisMapper.saveRedisString(redisKey, pDTO);


        log.info(this.getClass().getName() + ".saveRedisString End!");

        return res;
    }

    @Override
    public RedisDTO getRedisString() throws Exception {
        log.info(this.getClass().getName() + ".getRedisString Start!");

        String redisKey = "myRedis_String";

        RedisDTO rDTO = myRedisMapper.getRedisString(redisKey);

        if (rDTO == null) {
            rDTO = new RedisDTO();
        }

        log.info(this.getClass().getName() + ".getRedisString End!");

        return rDTO;
    }

    @Override
    public int saveRedisStringJSON() throws Exception {
        log.info(this.getClass().getName() + ".saveRedisStringJSON Start!");

        String redisKey = "myRedis_String_JSON";

        RedisDTO pDTO = new RedisDTO();
        pDTO.setTest_text("난 String타입에 JSON 구조로 저장할 일반 문자열이다.");
        pDTO.setName("이협건");
        pDTO.setAddr("서울");
        pDTO.setEmail("hglee67@kopo.ac.kr");

        int res = myRedisMapper.saveRedisStringJSON(redisKey, pDTO);

        log.info(this.getClass().getName() + ".saveRedisStringJSON End!");

        return res;
    }

    @Override
    public RedisDTO getRedisStringJSON() throws Exception {

        log.info(this.getClass().getName() + ".getRedisStringJSON Start!");

        String redisKey = "myRedis_String_JSON";

        RedisDTO rDTO = myRedisMapper.getRedisStringJSON(redisKey);

        if (rDTO == null) {
            rDTO = new RedisDTO();
        }

        log.info(this.getClass().getName() + ".getRedisStringJSON End!");

        return rDTO;
    }

    @Override
    public int saveRedisList() throws Exception {

        log.info(this.getClass().getName() + ".saveRedisList Start!");

        String redisKey = "myRedis_List";

        List<RedisDTO> pList = new LinkedList<>();

        for (int i = 0; i < 10; i++) {

            RedisDTO pDTO = new RedisDTO();
            pDTO.setTest_text(i + "번째 데이터입니다.");

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
            pDTO.setTest_text(i + "번째 데이터입니다.");
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
            pDTO.setTest_text(i + "번째 데이터입니다.");
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
            pDTO.setTest_text(i + "번째 데이터입니다.");
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
            pDTO.setTest_text(i + "번째 데이터입니다.");
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
