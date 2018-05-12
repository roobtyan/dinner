package service.impl;

import dao.IOrdersDao;
import entity.Orders;
import factory.BeanFactory;
import service.IOrdersService;
import vo.ViewOrdersVo;

import java.util.List;

/**
 * @author 订单接口实现
 */
public class OrderService implements IOrdersService {

    /**
     * 通过工厂创建对象
     */
    private IOrdersDao ordersDao = BeanFactory.getInstance("ordersDao", IOrdersDao.class);

    /**
     * 插入数据
     *
     * @param orders
     */
    @Override
    public void insert(Orders orders) {
        try {
            ordersDao.insert(orders);
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
        try {
            return ordersDao.selectAll();
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
        try {
            ordersDao.update(id, status);
        } catch (Exception e) {
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
        try {
            return ordersDao.countOrders();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过视图查询所有的订单
     *
     * @return
     */
    @Override
    public List<ViewOrdersVo> selectView() {
        try {
            return ordersDao.selectView();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
