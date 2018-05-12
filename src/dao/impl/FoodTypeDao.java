package dao.impl;

import dao.IFoodTypeDao;
import entity.FoodType;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public class FoodTypeDao implements IFoodTypeDao{
    /**
     * 添加菜系
     *
     * @param foodType
     */
    @Override
    public void addFoodType(FoodType foodType) {
        String sql = "insert into food_type(typeName) values(?);";
        try {
            JdbcUtils.getQueryRunner().update(sql,foodType.getTypeName());
        }catch (Exception e){
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
        String sql = "delete from food_type where id = ?";
        try {
            JdbcUtils.getQueryRunner().update(sql,id);
        } catch (SQLException e) {
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
        String sql = "update food_type set typeName = ? where id = ?";
        try {
            JdbcUtils.getQueryRunner().update(sql,foodType.getTypeName(),foodType.getId());
        } catch (SQLException e) {
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
        String sql = "select * from food_type where id = ?";
        try {
            return JdbcUtils.getQueryRunner().query(sql,new BeanHandler<FoodType>(FoodType.class),id);
        } catch (SQLException e) {
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
        String sql = "select * from food_type";
        try {
            return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<>(FoodType.class));
        } catch (SQLException e) {
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
        String sql = "select * from food_type where typeName like ?";
        try {
            return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<FoodType>(FoodType.class),"%" + typeName + "%");
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
