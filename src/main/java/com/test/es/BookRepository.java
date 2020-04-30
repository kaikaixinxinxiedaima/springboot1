package com.test.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

public interface BookRepository extends ElasticsearchRepository<EsBook, Integer> {

}
