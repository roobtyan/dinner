package service.impl;

import dao.IFoodTypeDao;
import entity.FoodType;
import factory.BeanFactory;
import service.IFoodTypeService;

import java.util.List;

public class FoodTypeService implements IFoodTypeService {

    //创建对象
//    private IFoodTypeDao foodTypeDao = new FoodTypeDao();
    //使用创建对象工厂类进行对象的创建
    private IFoodTypeDao foodTypeDao = BeanFactory.getInstance("foodTypeDao",IFoodTypeDao.class);
    /**
     * 添加菜系
     *
     * @param foodType
     */
    @Override
    public void addFoodType(FoodType foodType) {
        try {
            foodTypeDao.addFoodType(foodType);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 删除菜系
     *
     * @param id
     */
    @Override
    public void deleteFoodType(int id) {
        try {
            foodTypeDao.deleteFoodType(id);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    /**
     * 更新菜品
     *
     * @param foodType
     */
    @Override
    public void updateFoodType(FoodType foodType) {
        try {
            foodTypeDao.updateFoodType(foodType);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    /**
     * 根据主键进行查询
     *
     * @param id
     */
    @Override
    public FoodType selectByPrimaryKey(int id) {
        try {
            return foodTypeDao.selectByPrimaryKey(id);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    /**
     * 获取全部菜系
     *
     * @return
     */
    @Override
    public List<FoodType> selectAll() {
        try {
            return foodTypeDao.selectAll();
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    /**
     * 通过菜系名称进行模糊查询
     *
     * @param typeName
     * @return
     */
    @Override
    public List<FoodType> selectByTypeName(String typeName) {
        try {
            return foodTypeDao.selectByTypeName(typeName);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
