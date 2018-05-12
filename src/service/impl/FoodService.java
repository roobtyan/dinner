package service.impl;

import dao.IFoodDao;
import entity.Food;
import entity.view.ViewFood;
import factory.BeanFactory;
import service.IFoodService;
import utils.PageBean;

import java.util.List;

/**
 * 菜品：业务逻辑层实现类
 */
public class FoodService implements IFoodService {
    //通过工厂创建dao类
    IFoodDao foodDao = BeanFactory.getInstance("foodDao", IFoodDao.class);

    /**
     * 通过分页参数查询所有的菜品信息
     *
     * @param pageBean
     * @return
     */
    @Override
    public void findAll(PageBean pageBean) {
        try {
            //分页对象中已经包括返回的对象
            foodDao.getAll(pageBean);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过id查询菜品信息
     *
     * @param foodId
     * @return
     */
    @Override
    public Food findById(int foodId) {
        try {
            return foodDao.findById(foodId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加菜品
     *
     * @param food
     */
    @Override
    public void add(Food food) {
        try {
            foodDao.add(food);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除菜品
     *
     * @param id
     */
    @Override
    public void delete(int id) {
        try {
            foodDao.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改菜品
     *
     * @param food
     */
    @Override
    public void update(Food food) {
        try {
            foodDao.update(food);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询菜品
     *
     * @param foodName
     */
    @Override
    public List<ViewFood> findByName(String foodName) {
        try {
            return foodDao.find(foodName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询所有的菜品信息：无条件
     *
     * @return
     */
    @Override
    public List<ViewFood> findByNoOpt() {
        try {
            return foodDao.findAllWithoutOpt();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过视图查询菜品信息
     *
     * @param id
     * @return
     */
    @Override
    public ViewFood findByView(int id) {
        try {
            return foodDao.findByView(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
