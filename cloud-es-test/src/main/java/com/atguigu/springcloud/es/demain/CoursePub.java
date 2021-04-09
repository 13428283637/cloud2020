package com.atguigu.springcloud.es.demain;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.util.Date;

//@Documented
@Data
@Document(indexName = "coursepub", type = "doc")
public class CoursePub implements Serializable {
    private static final long serialVersionUID = -916357110051689487L;
    private String id;
    private String name;
    private String users;
    private String mt;
    private String st;
    private String grade;
    private String studymodel;
    private String teachmode;
    private String description;
    private String pic;//图片
    private Date timestamp;//时间戳
    private String charge;
    private String valid;
    private String qq;
    private Double price;
    private Double price_old;
    private String expires;
    private String teachplan;//课程计划
    private String pubTime;//课程发布时间
}
