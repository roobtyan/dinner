package service;

import entity.Food;
import entity.view.ViewFood;
import utils.PageBean;

import java.util.List;

/**
 * 菜品：业务逻辑接口实现
 */
public interface IFoodService {
    /**
     * 通过分页参数查询所有的菜品信息
     * @param pageBean
     * @return
     */
    void findAll(PageBean pageBean);

    /**
     * 通过id查询菜品信息
     * @param foodId
     * @return
     */
    Food findById(int foodId);

    /**
     * 添加菜品
     */
    void add(Food food);

    /**
     * 删除菜品
     */
    void delete(int id);

    /**
     * 修改菜品
     */
    void update(Food food);

    /**
     * 查询菜品
     */
    List<ViewFood> findByName(String foodName);

    /**
     * 查询所有的菜品信息：无条件
     * @return
     */
    List<ViewFood> findByNoOpt();

    /**
     * 通过视图查询菜品信息
     * @param id
     * @return
     */
    ViewFood findByView(int id);
}
