package kopo.poly.service.impl;

import kopo.poly.dto.MelonDTO;
import kopo.poly.persistance.mongodb.IMelonMapper;
import kopo.poly.persistance.redis.IMelonCacheMapper;
import kopo.poly.service.IMelonService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service("MelonService")
public class MelonService implements IMelonService {

    @Resource(name = "MelonMapper")
    private IMelonMapper melonMapper; // MongoDB에 저장할 Mapper

    @Resource(name = "MelonCacheMapper")
    private IMelonCacheMapper melonCacheMapper; // redisDB에 저장할 Mapper

    @Override
    public int collectMelonSong() throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".collectMelonRank Start!");

        int res = 0;

        List<MelonDTO> pList = new LinkedList<>();

        // 멜론 Top100 중 50위까지 정보 가져오는 페이지
        String url = "https://www.melon.com/chart/index.htm";

        // JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML소스 저장할 변수
        Document doc = Jsoup.connect(url).get();

        // <div class="service_list_song"> 이 태그 내에서 있는 HTML소스만 element에 저장됨
        Elements element = doc.select("div.service_list_song");

        // Iterator을 사용하여 멜론차트 정보를 가져오기
        // 멜론 50위까지 차크

        for (Element songInfo : element.select("div.wrap_song_info")) {

            // 크롤링을 통해 데이터 저장하기
            String song = CmmUtil.nvl(songInfo.select("div.ellipsis.rank01 a").text()); // 노래
            String singer = CmmUtil.nvl(songInfo.select("div.ellipsis.rank02 a").eq(0).text()); // 가수

            log.info("song : " + song);
            log.info("singer : " + singer);

            // 가수와 노래 정보가 모두 수집되었다면, 저장함
            if ((song.length() > 0) && (singer.length() > 0)) {

                MelonDTO pDTO = new MelonDTO();
                pDTO.setCollectTime(DateUtil.getDateTime("yyyyMMddhhmmss"));
                pDTO.setSong(song);
                pDTO.setSinger(singer);

                // 한번에 여러개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
                pList.add(pDTO);

            }
        }

        // 생성할 컬렉션명
        String colNm = "MELON_" + DateUtil.getDateTime("yyyyMMdd");

        // MongoDB에 데이터저장하기
        res = melonMapper.insertSong(pList, colNm);

        // RedisDB에 데이터저장하기
        res = melonCacheMapper.insertSong(pList);

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".collectMelonSong End!");

        return res;
    }

    @Override
    public List<MelonDTO> getSongList() throws Exception {

        log.info(this.getClass().getName() + ".getSongList Start!");

        // MongoDB에 저장된 컬렉션 이름
        String colNm = "MELON_" + DateUtil.getDateTime("yyyyMMdd");

        List<MelonDTO> rList = new LinkedList<>();

        // RedisDB에 저장되어 있는지 확인하기(Key이름은 MongoDB 컬렉션 이름과 동일하게 사용함)
        if (melonCacheMapper.getExistKey(colNm)){
            for (Object rDTO: melonCacheMapper.getSongList(colNm)){
                rList.add((MelonDTO) rDTO);

            }

        }else{
            rList = melonMapper.getSongList(colNm);

        }

        if (rList == null) {
            rList = new LinkedList<>();
        }

        log.info(this.getClass().getName() + ".getSongList End!");

        return rList;
    }

    @Override
    public List<Map<String, Object>> getSingerSongCnt() throws Exception {

        log.info(this.getClass().getName() + ".getSingerSongCnt Start!");

        String colNm = "MELON_" + DateUtil.getDateTime("yyyyMMdd");

        List<Map<String, Object>> rList = melonMapper.getSingerSongCnt(colNm);

        if (rList == null) {
            rList = new LinkedList<>();
        }

        log.info(this.getClass().getName() + ".getSingerSongCnt End!");

        return rList;
    }

}
