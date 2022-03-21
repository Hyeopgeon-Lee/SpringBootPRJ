package kopo.poly.persistance.redis;

import kopo.poly.dto.MelonDTO;

import java.util.List;

public interface IMelonCacheMapper {

    /**
     * 멜론 노래 리스트 저장하기
     *
     * @param pList 저장할 데이터
     * @return 저장 결과
     */
    int insertSong(List<MelonDTO> pList) throws Exception;

    /**
     * 멜론 노래 키 정보 존재여부 체크하기
     *
     * @param key 저장된 키 이름
     * @return key존재여부
     */
    boolean getExistKey(String key) throws Exception;

    /**
     * 오늘 수집된 멜론 노래리스트 가져오기
     *
     * @param key 저장된 키 이름
     * @return 노래 리스트
     */
    List<Object> getSongList(String key) throws Exception;


}
