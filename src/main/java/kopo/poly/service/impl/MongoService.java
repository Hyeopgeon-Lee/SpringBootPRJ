package kopo.poly.service.impl;

import kopo.poly.dto.MongoDTO;
import kopo.poly.persistance.mongodb.IMongoMapper;
import kopo.poly.service.IMongoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("MongoService")
public class MongoService implements IMongoService {

    @Resource(name = "MongoMapper")
    private IMongoMapper mongoMapper; // MongoDB에 저장할 Mapper

    @Override
    public void mongoTest() throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".mongoTest Start!");

        // 생성할 컬렉션명
        String colNm = "MONGODB_TEST";

        MongoDTO pDTO = new MongoDTO();
        pDTO.setUser_nm("이협건");
        pDTO.setAddr("서울");
        pDTO.setEmail("hglee67@kopo.ac.kr");

        // MongoDB에 데이터저장하기
        mongoMapper.insertData(pDTO, colNm);

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".mongoTest End!");

    }


}
