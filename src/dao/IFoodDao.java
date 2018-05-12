package dao;

import entity.Food;
import entity.view.ViewFood;
import utils.PageBean;

import java.util.List;

/**
 * 菜品dao层接口设计
 */
public interface IFoodDao {
    /**
     * 分页且按照条件查询菜品
     */
    void getAll(PageBean<Food> pageBean);

    /**
     * 按照条件统计总记录数
     */
    int getTotalCount(PageBean<Food> pageBean);

    /**
     * 按照id查询菜品
     */
    Food findById(int id);

    /**
     * 增加菜品
     */
    void add(Food food);

    /**
     * 删除菜品
     */
    void deleteById(int id);

    /**
     * 更新菜品信息
     */
    void update(Food food);

    /**
     * 查询菜品
     */
    List<ViewFood> find(String foodName);

    /**
     * 无条件查询food，通过视图
     * @return
     */
    List<ViewFood> findAllWithoutOpt();

    /**
     * 通过视图查询数据
     * @param id
     * @return
     */
    ViewFood findByView(int id);
}
