package dao.impl;

import dao.IOrdersDao;
import entity.Orders;
import entity.view.ViewOrders;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JdbcUtils;
import vo.ViewOrdersVo;

import java.util.List;

/**
 * 订餐实现Dao
 *
 * @author
 */
public class OrdersDao implements IOrdersDao {
    /**
     * 插入数据
     *
     * @param orders
     */
    @Override
    public void insert(Orders orders) {
        String sql = "insert into orders(id,table_id,order_date,total_price) values(?,?,?,?)";
        int id = countOrders();
        try {
            JdbcUtils.getQueryRunner().update(sql, (id + 1),
                    orders.getTable_id(), orders.getOrder_date(), orders.getTotal_price());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 选择所有的订餐信息
     *
     * @return
     */
    @Override
    public List<Orders> selectAll() {
        String sql = "select * from orders";
        try {
            return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<>(Orders.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 更新订餐信息
     *
     * @param id     订餐id
     * @param status 订餐状态
     */
    @Override
    public void update(int id, int status) {
        String sql = "update orders set order_status = ? where id = ?";
        try {
            JdbcUtils.getQueryRunner().update(sql,status,id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 计算所有订餐的数量
     *
     * @return
     */
    @Override
    public int countOrders() {
        String sql = "select count(*) from orders";
        try {
            Long num = JdbcUtils.getQueryRunner().query(sql, new ScalarHandler<Long>());
            return num.intValue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 通过视图查询所有的订单信息（后台）
     *
     * @return
     */
    @Override
    public List<ViewOrdersVo> selectView() {
        String sql = "select * from view_orders";
        try {
            return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<>(ViewOrdersVo.class));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
