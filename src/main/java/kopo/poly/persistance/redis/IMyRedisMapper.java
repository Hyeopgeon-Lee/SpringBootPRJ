package kopo.poly.persistance.redis;

import kopo.poly.dto.RedisDTO;

import java.util.List;
import java.util.Set;

public interface IMyRedisMapper {

    /**
     * String 타입 저장하기
     *
     * @param redisKey Redis저장 키
     * @param pDTO     저장할 정보
     * @return 저장 성공 여부
     */
    int saveString(String redisKey, RedisDTO pDTO) throws Exception;

    /**
     * String 타입 가져오기
     *
     * @param redisKey 가져올 RedisKey
     * @return 결과 값
     */
    RedisDTO getString(String redisKey) throws Exception;

    /**
     * String 타입에 JSON 형태로 저장하기
     *
     * @param redisKey Redis저장 키
     * @param pDTO     저장할 정보
     * @return 결과 값
     */
    int saveStringJSON(String redisKey, RedisDTO pDTO) throws Exception;

    /**
     * String 타입에 JSON 형태로 저장된 데이터 가져오기
     *
     * @param redisKey 가져올 RedisKey
     * @return 결과 값
     */
    RedisDTO getStringJSON(String redisKey) throws Exception;

    /**
     * List타입에 여러 문자열로 저장하기(동기화)
     *
     * @param redisKey Redis저장 키
     * @param pList    저장할 정보들
     * @return 저장 성공 여부
     */
    int saveList(String redisKey, RedisDTO pDTO) throws Exception;

    /**
     * List타입에 여러 문자열로 저장된 데이터 가져오기
     *
     * @param redisKey 가져올 RedisKey
     * @return 결과 값
     */
    RedisDTO getList(String redisKey) throws Exception;

    /**
     * List타입에 JSON 형태로 저장하기(동기화)
     *
     * @param redisKey Redis저장 키
     * @param pList    저장할 정보들
     * @return 저장 성공 여부
     */
    int saveRedisListJSON(String redisKey, List<RedisDTO> pList) throws Exception;

    /**
     * List타입에 JSON 형태로 저장된 데이터 가져오기
     *
     * @param redisKey 가져올 RedisKey
     * @return 결과 값
     */
    List<RedisDTO> getRedisListJSON(String redisKey) throws Exception;

    /**
     * List타입에 JSON 형태로 람다식을 이용하여 저장하기(비동기화)
     *
     * @param redisKey Redis저장 키
     * @param pList    저장할 정보들
     * @return 저장 성공 여부
     */
    int saveRedisListJSONRamda(String redisKey, List<RedisDTO> pList) throws Exception;

    /**
     * Hash 타입에 문자열 형태로 저장하기
     *
     * @param redisKey Redis저장 키
     * @param pDTO     저장할 정보들
     * @return 저장 성공 여부
     */
    int saveRedisHash(String redisKey, RedisDTO pDTO) throws Exception;

    /**
     * Hash 타입에 문자열 형태로 저장된 값 가져오기
     *
     * @param redisKey 가져올 RedisKey
     * @return 결과 값
     */
    RedisDTO getRedisHash(String redisKey) throws Exception;

    /**
     * Set타입에 JSON 형태로 람다식을 이용하여 저장하기
     *
     * @param redisKey Redis저장 키
     * @param pSet     저장할 정보들
     * @return 저장 성공 여부
     */
    int saveRedisSetJSONRamda(String redisKey, Set<RedisDTO> pSet) throws Exception;

    /**
     * Set타입에 JSON 형태로 람다식을 이용하여 저장된 값 가져오기
     *
     * @param redisKey 가져올 RedisKey
     * @return 결과 값
     */
    Set<RedisDTO> getRedisSetJSONRamda(String redisKey) throws Exception;

    /**
     * ZSet타입에 JSON 형태로 저장하기
     *
     * @param redisKey Redis저장 키
     * @param pList    저장할 정보들
     * @return 저장 성공 여부
     */
    int saveRedisZSetJSON(String redisKey, List<RedisDTO> pList) throws Exception;

    /**
     * ZSet타입에 JSON 형태로 저장된 값 가져오기
     *
     * @param redisKey 가져올 RedisKey
     * @return 결과 값
     */
    Set<RedisDTO> getRedisZSetJSON(String redisKey) throws Exception;

    /**
     * Redis에 JSON 구조로 저장된 데이터 삭제하기
     *
     * @param redisKey 삭제할 RedisKey
     * @return 결과 값
     */
    boolean deleteDataJSON(String redisKey) throws Exception;

    /**
     * Redis에 String 구조로 저장된 데이터 삭제하기
     *
     * @param redisKey 삭제할 RedisKey
     * @return 결과 값
     */
    boolean deleteDataString(String redisKey) throws Exception;
}
