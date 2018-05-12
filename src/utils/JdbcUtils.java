package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;

/**
 * JDBC工具类
 */
public class JdbcUtils {
    private static DataSource dataSource;
    static {
        dataSource = new ComboPooledDataSource();
        System.out.println("=====>数据库连接池启动");
    }

    //获取dataSource
    public DataSource getDataSource(){
        return dataSource;
    }

    //创建DbUtils常用工具类
    public static QueryRunner getQueryRunner(){
        return new QueryRunner(dataSource);
    }
}
