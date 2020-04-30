package com.test.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * es使用
 */

@Data
@Document(indexName = "book", type = "_doc",
        useServerConfiguration = true, createIndex = false)
public class EsBook implements Serializable{

    @Id
    private Integer id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String bookName;

    @Field(type = FieldType.Text, analyzer = "ik_max_word" , searchAnalyzer = "ik_max_word")
    private String bookContent;

    @Field(type = FieldType.Text, analyzer = "ik_max_word" , searchAnalyzer = "ik_max_word")
    private String author;

    @Field(type = FieldType.Date, format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
    private Date createTime;

    @Field(type = FieldType.Date, format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
    private Date updateTime;

}
