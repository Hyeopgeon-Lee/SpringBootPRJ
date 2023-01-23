package kopo.poly.service;

import kopo.poly.dto.RedisDTO;

import java.util.List;
import java.util.Set;

public interface IMyRedisService {

    /**
     * String 타입 저장 및 가져오기
     */
    RedisDTO saveString(RedisDTO pDTO) throws Exception;

    /**
     * String 타입에 JSON 형태로 저장하기
     */
    RedisDTO saveStringJSON(RedisDTO pDTO) throws Exception;

    /**
     * List타입에 여러 문자열로 저장하기
     */
    RedisDTO saveList(RedisDTO pDTO) throws Exception;

    /**
     * List타입에 JSON 형태로 저장하기
     */
    List<RedisDTO> saveListJSON(List<RedisDTO> pList) throws Exception;

    /**
     * Hash 타입에 문자열 형태로 저장하기
     */
    RedisDTO saveHash(RedisDTO pDTO) throws Exception;

    /**
     * Set타입에 JSON 형태로 저장하기
     */
    Set<RedisDTO> saveSetJSON(List<RedisDTO> pList) throws Exception;

    /**
     * ZSet타입에 JSON 형태로 저장하기
     */
    Set<RedisDTO> saveZSetJSON(List<RedisDTO> pList) throws Exception;


}
