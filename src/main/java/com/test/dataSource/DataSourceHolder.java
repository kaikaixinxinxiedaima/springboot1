package com.test.dataSource;

import com.test.annotation.MoreDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DataSourceHolder {
    private static final ThreadLocal<String> dataSources = new ThreadLocal<>();

    public static void setDataSourceKey(String customType) {
        dataSources.set(customType);
    }

    public static String getDataSourceKey() {
        return (String) dataSources.get();
    }

    public static void clearDataSourceKey() {
        dataSources.remove();
    }
}
