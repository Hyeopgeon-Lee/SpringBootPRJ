package kopo.poly.persistance.mongodb.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import kopo.poly.dto.MongoDTO;
import kopo.poly.persistance.mongodb.AbstractMongoDBComon;
import kopo.poly.persistance.mongodb.IMongoMapper;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component("MongoMapper")
public class MongoMapper extends AbstractMongoDBComon implements IMongoMapper {

    @Override
    public int insertData(MongoDTO pDTO, String colNm) throws Exception {

        log.info(this.getClass().getName() + ".insertData Start!");

        int res = 0;

        // 데이터를 저장할 컬렉션 생성
        super.createCollection(colNm);

        // 저장할 컬렉션 객체 생성
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // DTO를 Map 데이터타입으로 변경 한 뒤, 변경된 Map 데이터타입을 Document로 변경하기
        col.insertOne(new Document(new ObjectMapper().convertValue(pDTO, Map.class)));

        res = 1;

        log.info(this.getClass().getName() + ".insertData End!");

        return res;
    }
}

