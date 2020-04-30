package com.test.controller;


import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate;
import com.test.entity.Book;
import com.test.es.BookRepository;
import com.test.es.EsBook;
import com.test.service.BookService;
import com.test.util.Html2TextUtils;
import lombok.Data;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * es测试
 */
@Controller
@RequestMapping("/searchBook")
public class EsTestController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookService service;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping("/index")
    public String index(){
        return "es/es_test";
    }

    @PostMapping("/search")
    @ResponseBody
    public Map search(@RequestBody Param param){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Map<String, Object> map = new HashMap<>();

        String type = param.getType();
        if(type.equalsIgnoreCase("mysql")){
            List<Book> bookList = service.findByKeyWord(param.getKeyword());
            map.put("lits", bookList);
        }else if(type.equalsIgnoreCase("es")){
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
            queryBuilder.should(QueryBuilders.matchPhraseQuery("bookName", param.getKeyword()));
            queryBuilder.should(QueryBuilders.matchPhraseQuery("bookContent", param.getKeyword()));

            String s = queryBuilder.toString();
            System.out.println("查询语句："+s);

            Page<EsBook> esBooks = (Page<EsBook>)bookRepository.search(queryBuilder);
            List<EsBook> bookList = esBooks.getContent();
            map.put("lits", bookList);
        }


        stopWatch.stop();
        long millis = stopWatch.getTotalTimeMillis();
        map.put("searchTime", millis);
        return map;
    }

    @PostMapping("/searchHit")
    @ResponseBody
    public Map searchHit(@RequestBody Param param){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Map<String, Object> map = new HashMap<>();

        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.should(QueryBuilders.matchPhraseQuery("bookName", param.getKeyword()))
                    .should(QueryBuilders.queryStringQuery("\""+param.getKeyword()+"\"").defaultField("bookContent"));

        NativeSearchQuery nativeSearchQuery=new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withHighlightFields(new HighlightBuilder.Field("bookContent"),new HighlightBuilder.Field("bookName"))
                .withHighlightBuilder(new HighlightBuilder().preTags("<span style='color:red'>").postTags("</span>")).build();

        String s = queryBuilder.toString();
        System.out.println("查询语句："+s);


        AggregatedPage<EsBook> esBooks = elasticsearchTemplate.queryForPage(nativeSearchQuery, EsBook.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                List<EsBook> esBookList = new LinkedList<EsBook>();
                SearchHits hits = response.getHits();
                for (SearchHit searchHit : hits) {
                    if (hits.getHits().length <= 0) {
                        return null;
                    }
                    Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
                    String bookName= (String) sourceAsMap.get("bookName");
                    String bookContent= (String) sourceAsMap.get("bookContent");
                    String author= (String) sourceAsMap.get("author");

                    System.out.println(bookName);
                    System.out.println(bookContent);

                    EsBook esBook = new EsBook();

                    HighlightField contentHighlightField = searchHit.getHighlightFields().get("bookContent");
                    if(contentHighlightField==null){
                        esBook.setBookContent(bookContent);
                    }else{
                        String highLightMessage = searchHit.getHighlightFields().get("bookContent").fragments()[0].toString();
                        esBook.setBookContent(highLightMessage);
//                        esBook.setBookContent(Html2TextUtils.stripHtml(highLightMessage).replaceAll("_",""));
                    }

                    HighlightField nameHighlightField = searchHit.getHighlightFields().get("bookName");
                    if(nameHighlightField==null){
                        esBook.setBookName(bookName);
                    }else{
                        esBook.setBookName(searchHit.getHighlightFields().get("bookName").fragments()[0].toString());
                    }

                    esBook.setAuthor(author);

                    esBookList.add(esBook);
                }
                if (esBookList.size() > 0) {
                    return new AggregatedPageImpl<T>((List<T>) esBookList);
                }
                return null;

            }

            @Override
            public <T> T mapSearchHit(SearchHit searchHit, Class<T> aClass) {
                return null;
            }
        });

        if(esBooks != null){
            List<EsBook> bookList = esBooks.getContent();
            map.put("lits", bookList);
        }

        stopWatch.stop();
        long millis = stopWatch.getTotalTimeMillis();
        map.put("searchTime", millis);
        return map;
    }


    @Data
    public static class Param{
        private String type;

        private String keyword;

    }

}
