//package com.test.sharding;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;
//
//import java.util.Properties;
//import java.util.concurrent.atomic.AtomicLong;
//
//public class SimpleShardingKeyGenerator implements ShardingKeyGenerator {
//
//    private AtomicLong atomic = new AtomicLong(0);
//    @Getter
//    @Setter
//    private Properties properties = new Properties();
//
//    @Override
//    public Comparable<?> generateKey() {
//        return atomic.incrementAndGet();
//    }
//
//    @Override
//    public String getType() {
//    	//声明类型
//        return "SIMPLE";
//    }
//}