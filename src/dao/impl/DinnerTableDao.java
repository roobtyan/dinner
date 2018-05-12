package dao.impl;

import dao.IDinnerTableDao;
import entity.DinnerTable;
import entity.TableStatus;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JdbcUtils;

import java.util.ArrayList;
import java.util.List;

public class DinnerTableDao implements IDinnerTableDao {
    /**
     * 查询所有的餐桌信息
     */
    @Override
    public List<DinnerTable> findAll() {
        String sql = "select * from dinner_table";
        try {
            return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<>(DinnerTable.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 更新餐桌信息
     *
     * @param dinnerTableInfo
     */
    @Override
    public void setDinnerTableInfo(DinnerTable dinnerTableInfo) {
        //通过id查询餐桌
        //DinnerTable dinnerTable = findById(dinnerTableInfo.getId());

        //用list接收需要更新的参数
        List<Object> list = new ArrayList<>();
        //通过比对信息更新餐桌信息
        StringBuffer buffer = new StringBuffer();
        buffer.append("update dinner_table ");

        //如果餐桌状态发生了变化
        buffer.append(" set table_status = ? ");
        list.add(dinnerTableInfo.getTable_status());

        //如果餐桌订餐时间发生了变化
        if (dinnerTableInfo.getOrderDate() != null) {
            buffer.append(" and orederDate = ? ");
            list.add(dinnerTableInfo.getOrderDate());
        }
        //最终查询的id
        buffer.append(" where id = ? ");
        list.add(dinnerTableInfo.getId());

        //接收数据
        try {
            JdbcUtils.getQueryRunner().update(buffer.toString(), list.toArray());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * 添加新的餐桌
     *
     * @param tableName
     */
    @Override
    public void addDinnerTable(String tableName) {
        String sql = "insert into dinner_table(tableName) values(?)";
        try {
            JdbcUtils.getQueryRunner().update(sql, tableName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除餐桌:通过id
     *
     * @param id
     */
    @Override
    public void deleteDinnerTable(int id) {
        String sql = "delete from dinner_table where id = ?";
        try {
            JdbcUtils.getQueryRunner().update(sql, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 搜索餐桌
     *
     * @param tableName
     */
    @Override
    public List<DinnerTable> findDinnerTable(String tableName) {
        String sql = "select * from dinner_table where tableName like ?";
        try {
            return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<>(DinnerTable.class), "%" + tableName + "%");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过餐桌的预订状态查询餐桌
     *
     * @param tableStatus
     * @return
     */
    @Override
    public List<DinnerTable> findByStatus(TableStatus tableStatus) {
        String sql = "select * from dinner_table where table_status = ?";
        try {
            //tableStatus.ordinal()返回下标0,1
            return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<>(DinnerTable.class), tableStatus.ordinal());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过餐桌的id查询餐桌
     *
     * @param id
     * @return
     */
    @Override
    public DinnerTable findById(int id) {
        String sql = "select * from dinner_table where id = ?";
        try {
            return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<>(DinnerTable.class), id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
