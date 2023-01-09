package kopo.poly.service.impl;

import kopo.poly.dto.MongoDTO;
import kopo.poly.persistance.mongodb.IMongoMapper;
import kopo.poly.service.IMongoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MongoService implements IMongoService {

    private final IMongoMapper mongoMapper; // MongoDB에 저장할 Mapper

    @Override
    public int mongoTest(MongoDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".mongoTest Start!");

        // 생성할 컬렉션명
        String colNm = "MONGODB_TEST";

        // MongoDB에 데이터저장하기
        int res = mongoMapper.insertData(pDTO, colNm);

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".mongoTest End!");

        return res;
    }


}
