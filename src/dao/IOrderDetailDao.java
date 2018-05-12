package dao;

import entity.OrderDetail;
import entity.view.ViewFood;
import entity.view.ViewOrders;

import java.util.List;

/**
 * @author 言立慧
 * 描述：订单详情Dao接口
 */
public interface IOrderDetailDao {
    /**
     * 插入数据
     * @param orderDetail
     */
    void insert(OrderDetail orderDetail);

    /**
     * 更新数据
     * @param order_id
     * @param status
     */
    void update(int order_id,int status);

    /**
     * 通过订餐id查询详细信息
     * @param order_id
     * @return
     */
    List<ViewOrders> selectByOrderId(int order_id);
}
