package com.atguigu.springcloud.es.controller;


import com.atguigu.springcloud.es.dao.EsRepository;
import com.atguigu.springcloud.es.demain.CoursePub;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.search.MatchQuery;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EsController {

    @Autowired
    ElasticsearchTemplate elasticSearchTemplate;
    @Autowired
    EsRepository esRepository;

    @RequestMapping("testIndex")
    public String testIndex(){
        elasticSearchTemplate.createIndex(CoursePub.class);
        return "index";
    }
    @RequestMapping("testInsert")
    public String testInsert(){
        CoursePub coursePub = new CoursePub();
        coursePub.setId("6");
        coursePub.setDescription("ta哭啊");
        coursePub.setName("zhangsan");
        coursePub.setPrice(100D);
        esRepository.save(coursePub);

        coursePub.setId("7");
        coursePub.setDescription("ta哭啊");
        coursePub.setName("zhangsan");
        coursePub.setPrice(200D);
        esRepository.save(coursePub);

        coursePub.setId("8");
        coursePub.setDescription("ta哭啊");
        coursePub.setName("zhangsan");
        coursePub.setPrice(300D);
        esRepository.save(coursePub);
        return "testInsert";
    }

    @RequestMapping("search")
    public List<CoursePub> search(){
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
//        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery()).build();
//        List<CoursePub> coursePubs = elasticSearchTemplate.queryForList(searchQuery, CoursePub.class);

        //设置高亮的字段 针对 商品的名称进行高亮
        nativeSearchQueryBuilder.withHighlightFields(new HighlightBuilder.Field("description"));
        //设置前缀 和 后缀
        nativeSearchQueryBuilder.withHighlightBuilder(new HighlightBuilder().preTags("<em style=\"color:red\">").postTags("</em>"));

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").from(200D,true).to(300D,true));
        boolQueryBuilder.filter(QueryBuilders.termQuery("id","4"));

        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.withQuery(QueryBuilders.multiMatchQuery("好","description")).build();

        searchQuery = nativeSearchQueryBuilder.withFilter(boolQueryBuilder).build();
        List<CoursePub> coursePubs = elasticSearchTemplate.queryForList(searchQuery, CoursePub.class);





        for (CoursePub coursePub : coursePubs) {
            System.out.println(coursePub);
        }
        return coursePubs;
    }
}
