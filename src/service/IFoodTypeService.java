package service;

import entity.FoodType;

import java.util.List;

public interface IFoodTypeService {
    /**
     * 添加菜系
     * @param foodType
     */
    void addFoodType(FoodType foodType);

    /**
     * 删除菜系
     * @param id
     */
    void deleteFoodType(int id);

    /**
     * 更新菜品
     * @param foodType
     */
    void updateFoodType(FoodType foodType);

    /**
     * 根据主键进行查询
     * @param id
     */
    FoodType selectByPrimaryKey(int id);

    /**
     * 获取全部菜系
     * @return
     */
    List<FoodType> selectAll();

    /**
     * 通过菜系名称进行模糊查询
     * @param typeName
     * @return
     */
    List<FoodType> selectByTypeName(String typeName);
}
