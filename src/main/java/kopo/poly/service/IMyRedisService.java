package kopo.poly.service;

import kopo.poly.dto.RedisDTO;

import java.util.List;
import java.util.Set;

public interface IMyRedisService {

    /**
     * String 타입 저장하기
     */
    int saveRedisString() throws Exception;

    /**
     * String 타입 가져오기
     */
    RedisDTO getRedisString() throws Exception;

    /**
     * String 타입에 JSON 형태로 저장하기
     */
    int saveRedisStringJSON() throws Exception;

    /**
     * String 타입에 JSON 형태로 저장된 값 가져오기
     */
    RedisDTO getRedisStringJSON() throws Exception;

    /**
     * List타입에 여러 문자열로 저장하기(동기화)
     */
    int saveRedisList() throws Exception;

    /**
     * List타입에 여러 문자열로 저장된 데이터 가져오기
     */
    List<String> getRedisList() throws Exception;

    /**
     * List타입에 JSON 형태로 저장하기(동기화)
     */
    int saveRedisListJSON() throws Exception;

    /**
     * List타입에 JSON 형태로 저장된 데이터 가져오기
     */
    List<RedisDTO> getRedisListJSON() throws Exception;

    /**
     * List타입에 JSON 형태로 람다식을 이용하여 저장하기(비동기화)
     */
    int saveRedisListJSONRamda() throws Exception;

    /**
     * List타입에 JSON 형태로 저장된 데이터 가져오기
     * 
     * 람다식 저장된 Redis키 값이 달라서 함수 별도로 만듬
     * 매퍼 호출은 앞서 만든 getRedisListJSON 호출함
     */
    List<RedisDTO> getRedisListJSONRamda() throws Exception;

    /**
     * Hash 타입에 문자열 형태로 저장하기
     */
    int saveRedisHash() throws Exception;

    /**
     * Hash 타입에 문자열 형태로 저장된 값 가져오기
     */
    RedisDTO getRedisHash() throws Exception;

    /**
     * Set타입에 JSON 형태로 람다식을 이용하여 저장하기
     */
    int saveRedisSetJSONRamda() throws Exception;

    /**
     * Set타입에 JSON 형태로 람다식을 이용하여 저장된 값 가져오기
     */
    Set<RedisDTO> getRedisSetJSONRamda() throws Exception;

    /**
     * ZSet타입에 JSON 형태로 저장하기
     */
    int saveRedisZSetJSON() throws Exception;

    /**
     * ZSet타입에 JSON 형태로 저장된 값 가져오기
     */
    Set<RedisDTO> getRedisZSetJSON() throws Exception;

    /**
     * Redis에 JSON 구조로 저장된 데이터 삭제하기
     */
    boolean deleteDataJSON() throws Exception;

    /**
     * Redis에 String 구조로 저장된 데이터 삭제하기
     */
    boolean deleteDataString() throws Exception;

}
