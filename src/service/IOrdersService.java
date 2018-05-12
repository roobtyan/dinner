package service;

import entity.Orders;
import vo.ViewOrdersVo;

import java.util.List;

/**
 * @author 言立慧
 * 订单服务接口
 */
public interface IOrdersService {
    /**
     * 插入数据
     * @param orders
     */
    void insert(Orders orders);

    /**
     * 选择所有的订单
     * @return
     */
    List<Orders> selectAll();

    /**
     * 更新订单状态（根据id）
     * @param id
     * @param status
     */
    void update(int id, int status);

    /**
     * 计算总数
     * @return
     */
    int countOrders();

    /**
     * 通过视图查询所有的订单
     * @return
     */
    List<ViewOrdersVo> selectView();
}
