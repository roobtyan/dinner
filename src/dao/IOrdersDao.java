package dao;

import entity.Orders;
import entity.view.ViewOrders;
import vo.ViewOrdersVo;

import java.util.List;

/**
 * 订餐信息Dao接口
 * @author 言立慧
 */
public interface IOrdersDao {
    /**
     * 插入数据
     * @param orders
     */
    void insert(Orders orders);

    /**
     * 选择所有的订餐信息
     * @return
     */
    List<Orders> selectAll();

    /**
     * 更新订餐信息
     * @param id 订餐id
     * @param status    订餐状态
     */
    void update(int id,int status);

    /**
     * 计算所有订餐的数量
     * @return
     */
    int countOrders();

    /**
     * 通过视图查询所有的订单信息（后台）
     * @return
     */
    List<ViewOrdersVo> selectView();
}
