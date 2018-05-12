package service.impl;

import dao.IOrderDetailDao;
import entity.OrderDetail;
import entity.view.ViewOrders;
import factory.BeanFactory;
import service.IOrderDetailService;

import java.util.List;

public class OrderDetailService implements IOrderDetailService {
    /**
     * 通过工厂创建类
     */
    private IOrderDetailDao orderDetailDao = BeanFactory.getInstance("orderDetailDao", IOrderDetailDao.class);

    /**
     * 插入数据
     *
     * @param orderDetail
     */
    @Override
    public void insert(OrderDetail orderDetail) {
        try {
            orderDetailDao.insert(orderDetail);
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
        try {
            orderDetailDao.update(order_id, status);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过id查询订单详情
     *
     * @param orderId
     * @return
     */
    @Override
    public List<ViewOrders> selectByOrderId(int orderId) {
        try {
            return orderDetailDao.selectByOrderId(orderId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
