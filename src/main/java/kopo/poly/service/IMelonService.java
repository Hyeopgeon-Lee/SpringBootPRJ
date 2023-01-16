package kopo.poly.service;

import kopo.poly.dto.MelonDTO;

import java.util.List;

public interface IMelonService {

    /**
     * 멜론 노래 리스트 저장하기
     */
    int collectMelonSong() throws Exception;

    /**
     * 오늘 수집된 멜론 노래리스트 가져오기
     */
    List<MelonDTO> getSongList() throws Exception;

    /**
     * 멜론 가수별 노래 수 가져오기
     */
    List<MelonDTO> getSingerSongCnt() throws Exception;

    /**
     * 가수의 노래 가져오기
     *
     * @return 노래 리스트
     */
    List<MelonDTO> getSingerSong(MelonDTO pDTO) throws Exception;

    /**
     * 수집된 멜론 차트 저장된 MongoDB 컬렉션 삭제하기
     */
    int dropCollection() throws Exception;

    /**
     * 멜론 노래 리스트 한번에 저장하기
     */
    List<MelonDTO> insertManyField() throws Exception;

    /**
     * singer 필드의 값인 방탄소년단을 BTS로 변경하기
     */
    List<MelonDTO> updateField(MelonDTO pDTO) throws Exception;

    /**
     * BTS 노래마다 nickname 필드를 추가하고,
     * 그 필드에 BTS 저장하기
     */
    List<MelonDTO> updateAddField(MelonDTO pDTO) throws Exception;

    /**
     * BTS 노래에 member 필드 추가하고,
     * 그 member 필드에 BTS 멤버 이름들을 List로 저장하기
     */
    List<MelonDTO> updateAddListField(MelonDTO pDTO) throws Exception;

    /**
     * 가수이름 수정 및 addData 필드 추가하기
     */
    List<MelonDTO> updateFieldAndAddField(MelonDTO pDTO) throws Exception;

    /**
     * BTS 노래 삭제하기
     */
    public List<MelonDTO> deleteDocument(MelonDTO pDTO) throws Exception;

}
