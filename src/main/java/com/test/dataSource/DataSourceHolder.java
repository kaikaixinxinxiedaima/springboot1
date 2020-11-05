package com.test.dataSource;

import com.test.annotation.MoreDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DataSourceHolder {
    private static final ThreadLocal<String> dataSources = new ThreadLocal<>();

    /**
     * 设置数据源
     */
    public static void setDataSourceKey(String customType) {
        dataSources.set(customType);
    }

    /**
     * 获取数据源
     */
    public static String getDataSourceKey() {
        return (String) dataSources.get();
    }

    /**
     * 清空数据源
     */
    public static void clearDataSourceKey() {
        dataSources.remove();
    }
}
