package com.test.dataSource;

import com.test.annotation.MoreDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * 决定使用哪个数据源之前需要把多个数据源的信息以及默认数据源信息配置好
     *
     * @param defaultTargetDataSource 默认数据源
     * @param targetDataSources       目标数据源
     */
//    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
//        super.setDefaultTargetDataSource(defaultTargetDataSource);
//        super.setTargetDataSources(targetDataSources);
//        super.afterPropertiesSet();
//    }


    @Override
    protected Object determineCurrentLookupKey() {
        String key = DataSourceHolder.getDataSourceKey();
        if (StringUtils.isBlank(key)) {
            return MoreDataSource.master;
        }
        return key;
    }
}
