package dao.impl;

import dao.IOrderDetailDao;
import entity.OrderDetail;
import entity.view.ViewOrders;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JdbcUtils;

import java.util.List;

public class OrderDetailDao implements IOrderDetailDao {
    /**
     * 插入数据
     *
     * @param orderDetail
     */
    @Override
    public void insert(OrderDetail orderDetail) {
        String sql = "insert into order_detail(order_id,food_id,food_count) values(?,?,?)";
        try {
            JdbcUtils.getQueryRunner().update(sql, orderDetail.getOrder_id(), orderDetail.getFood_id(), orderDetail.getFood_count());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 更新数据
     *
     * @param order_id
     * @param status
     */
    @Override
    public void update(int order_id, int status) {
        String sql = "update order_detail set order_status=? where order_id=?";
        try {
            JdbcUtils.getQueryRunner().update(sql, status, order_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过订餐id查询详细信息
     *
     * @param order_id
     * @return
     */
    @Override
    public List<ViewOrders> selectByOrderId(int order_id) {
        String sql = "select * from view_foods where id = ?";
        try {
            return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<>(ViewOrders.class), order_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
