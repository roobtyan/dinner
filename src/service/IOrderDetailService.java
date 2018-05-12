package service;

import entity.OrderDetail;
import entity.view.ViewOrders;

import java.util.List;

/**
 * @author
 * 订单详细
 */
public interface IOrderDetailService {
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
    void update(int order_id, int status);

    /**
     * 通过id查询订单详情
     * @param orderId
     * @return
     */
    List<ViewOrders> selectByOrderId(int orderId);
}
