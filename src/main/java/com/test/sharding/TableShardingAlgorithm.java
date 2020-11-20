package com.test.sharding;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TableShardingAlgorithm implements PreciseShardingAlgorithm<String>, RangeShardingAlgorithm<String>{

    //表名的处理
    public static int randTable(int value)
     {
         String s = String.valueOf(value);
         if(Integer.valueOf(s.substring(s.length() -1)) < 5){
             return 2;
         }else {
             return 3;
         }
     }

    DateTimeFormatter formFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter toFormatter   = DateTimeFormatter.ofPattern("yyyyMM");

    /**
     * @author  fanchunying
     * @create  2020/11/20 18:14
     * @desc 逻辑待完善
     * @return
     **/
    public String doSharding(Collection collection, PreciseShardingValue preciseShardingValue) {
        return preciseShardingValue.getLogicTableName() + Integer.valueOf(randTable((Integer) preciseShardingValue.getValue()));
    }

    public Collection<String> doSharding(Collection collection, RangeShardingValue rangeShardingValue) {
        List<String> list = new ArrayList<String>();
//        Range<String> ranges = rangeShardingValue.getValueRange();
//        LocalDateTime startTime = LocalDateTime.parse(ranges.lowerEndpoint(), formFormatter);
//        LocalDateTime endTime = LocalDateTime.parse(ranges.upperEndpoint(), formFormatter);
//        while (startTime.isBefore(endTime)) {
//            list.add(rangeShardingValue.getLogicTableName() + "_" + startTime.format(toFormatter));
//            startTime = startTime.plusMonths(1);
//        }

        list.add(rangeShardingValue.getLogicTableName());
        return list;
    }

    public static void main(String[] args) {
        int i = randTable(14);
        System.out.println(i);
    }
}