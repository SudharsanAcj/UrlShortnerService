package com.utills.urlShortner.service;

import com.utills.urlShortner.data.entity.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class CounterService {

    @Autowired
    private MongoOperations mongoOperations;

    private static final int BATCH_SIZE = 100;

    public long getNextSequence(String counterName) {
        Query query = new Query(Criteria.where("_id").is(counterName));
        Update update = new Update().inc("count", BATCH_SIZE);

        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);
        Counter counter = mongoOperations.findAndModify(query, update, options, Counter.class);

        return counter.getCount();
    }
}
