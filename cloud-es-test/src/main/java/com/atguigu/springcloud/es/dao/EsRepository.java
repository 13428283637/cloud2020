package com.atguigu.springcloud.es.dao;

import com.atguigu.springcloud.es.demain.CoursePub;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsRepository extends ElasticsearchRepository<CoursePub,String> {
}
