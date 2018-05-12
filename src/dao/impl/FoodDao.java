package dao.impl;

import dao.IFoodDao;
import entity.Food;
import entity.view.ViewFood;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.Condition;
import utils.JdbcUtils;
import utils.PageBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author roobtyan
 */
public class FoodDao implements IFoodDao {
    /**
     * 分页且按照条件查询菜品
     *
     * @param pageBean
     */
    @Override
    public void getAll(PageBean<Food> pageBean) {
        //获取条件对象
        Condition condition = pageBean.getCondition();
        //获取查询id
        int id = condition.getFoodTypeId();
        //获取名称
        String foodName = condition.getFoodName();

        //拼装sql语句
        StringBuffer buffer = new StringBuffer();
        buffer.append("select ");
        buffer.append(" f.id, ");
        buffer.append(" f.foodName, ");
        buffer.append(" f.food_type_id, ");
        buffer.append(" f.price, ");
        buffer.append(" f.mprice, ");
        buffer.append(" f.remark, ");
        buffer.append(" f.img, ");
        buffer.append(" t.typeName ");
        buffer.append(" from ");
        buffer.append(" food f, ");
        buffer.append(" food_type t ");
        buffer.append(" where ");
        buffer.append(" f.food_type_id = t.id ");

        //存储查询条件所对应的值
        List<Object> list = new ArrayList<>();
        //拼接条件
        if (id > 0) {
            //如果id大于0,则传入了id
            buffer.append(" and f.food_type_id = ? ");
            list.add(id);
        }
        if (foodName != null && !"".equals(foodName.trim())) {
            //如果foodName不为空
            buffer.append(" and f.foodName like ? ");
            list.add(foodName);
        }
        //分页条件
        buffer.append(" limit ?,?");    //开始的行数，总行数

        //判断分页
        //先查询总记录数
        int totalCount = getTotalCount(pageBean);
        //设置总记录数
        pageBean.setTotalCount(totalCount);
        //判断
        if (pageBean.getCurrentPage() < 1) {
            //如果传入的当前页小于1,则设为第一页
            pageBean.setCurrentPage(1);
        } else if (pageBean.getCurrentPage() > pageBean.getTotalPage()) {
            //如果传入的当前页大于总页数，则设置为最大页数
            pageBean.setCurrentPage(pageBean.getTotalPage());
        }

        //起始行
        int index = (pageBean.getCurrentPage() - 1) * pageBean.getPageCount();
        //返回每页的总行数
        int pageCount = pageBean.getPageCount();

        //加入list
        list.add(index);
        list.add(pageCount);

        try {
            List<Food> foods = JdbcUtils.getQueryRunner().query(buffer.toString(), new BeanListHandler<Food>(Food.class), list.toArray());
            //设置到分页对象之中
            pageBean.setPageData(foods);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 按照条件统计总记录数
     *
     * @param pageBean
     */
    @Override
    public int getTotalCount(PageBean<Food> pageBean) {
        //获取条件对象
        Condition condition = pageBean.getCondition();
        //获取查询id
        int id = condition.getFoodTypeId();
        //获取名称
        String foodName = condition.getFoodName();

        //拼装sql语句
        StringBuffer buffer = new StringBuffer();
        buffer.append("select ");
        buffer.append(" count(*) ");
        buffer.append(" from ");
        buffer.append(" food f, ");
        buffer.append(" food_type t ");
        buffer.append(" where ");
        buffer.append(" f.food_type_id = t.id ");

        //存储查询条件所对应的值
        List<Object> list = new ArrayList<>();
        //拼接条件
        if (id > 0) {
            //如果id大于0,则传入了id
            buffer.append(" and f.food_type_id = ? ");
            list.add(id);
        }
        if (foodName != null && !"".equals(foodName.trim())) {
            //如果foodName不为空
            buffer.append(" and f.foodName like ? ");
            list.add(foodName);
        }
        System.out.println("字符数组的元素是：" + list.toString());
        //查询
        try {
            Long num = JdbcUtils.getQueryRunner().query(buffer.toString(), new ScalarHandler<Long>(), list.toArray());
            return num.intValue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 按照id查询菜品
     *
     * @param id
     */
    @Override
    public Food findById(int id) {
        //拼装sql语句
        StringBuffer buffer = new StringBuffer();
        buffer.append("select ");
        buffer.append(" f.foodName, ");
        buffer.append(" f.food_type_id, ");
        buffer.append(" f.price, ");
        buffer.append(" f.mprice, ");
        buffer.append(" f.remark, ");
        buffer.append(" f.img, ");
        buffer.append(" t.typeName ");
        buffer.append(" from ");
        buffer.append(" food f, ");
        buffer.append(" food_type t ");
        buffer.append(" where ");
        buffer.append(" f.food_type_id = t.id ");
        buffer.append(" and f.id = ?");

        try {
            return JdbcUtils.getQueryRunner().query(buffer.toString(), new BeanHandler<>(Food.class), id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 增加菜品
     *
     * @param food
     */
    @Override
    public void add(Food food) {
        //sql语句
        String sql = "insert into food(foodName,food_type_id,price,mprice,remark,img) values(?,?,?,?,?,?)";
        //开始新增
        try {
            JdbcUtils.getQueryRunner().update(sql, food.getFoodName(), food.getFood_type_id(), food.getPrice(),
                    food.getMprice(), food.getRemark(), food.getImg());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除菜品
     *
     * @param id
     */
    @Override
    public void deleteById(int id) {
        String sql = "delete from food where id = ?";
        try {
            JdbcUtils.getQueryRunner().update(sql, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 更新菜品信息
     *
     * @param food
     */
    @Override
    public void update(Food food) {
        //创建集合保存更新值
        List<Object> list = new ArrayList<>();
        String sql = "update food set foodName=?,food_type_id=?,price=?," +
                "mprice=?,remark=?,img=? where id=?";
        try {
            JdbcUtils.getQueryRunner().update(sql, food.getFoodName(), food.getFood_type_id(),
                    food.getPrice(), food.getMprice(), food.getRemark(), food.getImg(), food.getId());
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
    public List<ViewFood> find(String foodName) {
        String sql = "select * from view_food where foodName like ?";
        try {
            return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<>(ViewFood.class), "%" + foodName + "%");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询所有菜品：无条件
     */
    @Override
    public List<ViewFood> findAllWithoutOpt() {
        String sql = "select * from view_food";
        try {
            return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<>(ViewFood.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过视图查询数据
     *
     * @param id
     * @return
     */
    @Override
    public ViewFood findByView(int id) {
        String sql = "select * from view_food where id = ?";
        try {
            return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<>(ViewFood.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
