package servlet;

import entity.DinnerTable;
import entity.Food;
import entity.FoodType;
import entity.view.ViewFood;
import utils.Condition;
import utils.FileUploadUtils;
import utils.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

/**
 * @author 言立慧
 */
@WebServlet(name = "FoodServlet", urlPatterns = "/food")
public class FoodServlet extends BaseServlet {

    /**
     * 详细列表展示：前台
     */
    public Object foodDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //1.1 获取餐桌ID,根据ID查询，再把查询到的结果保存到session （生成订单用）
        // 只需要执行一次即可: 先从session获取看有没有餐桌对象； 如果没有，就执行根据主键查询；
        // 如果sesison中已经有餐桌对象，就不执行主键查询
        Object object = session.getAttribute("dinnerTable");
        //判断
        if (object == null) {
            //如果为空，则为第一次访问
            String id = request.getParameter("tableId");
            DinnerTable dinnerTable = dinnerTableService.findById(Integer.parseInt(id));
            //将餐桌信息保存到session对象中
            session.setAttribute("dinnerTable", dinnerTable);
        }
        //1.2查询所有菜品信息
        PageBean<Food> pageBean = new PageBean<>();
        //分页参数，查询当前页数
        String currentPage = request.getParameter("currentPage");
        //判断
        if (currentPage == null || "".equals(currentPage)) {
            //设置当前分页为1（初次访问）
            pageBean.setCurrentPage(1);
        } else {
            pageBean.setCurrentPage(Integer.parseInt(currentPage));
        }
        //条件对象
        Condition condition = new Condition();
        //设置分页参数:菜系id
        String id = request.getParameter("foodTypeId");
        String foodTypeId = null;
        if (id != null) {
            session.setAttribute("foodTypeId", id);
            condition.setFoodTypeId(Integer.parseInt(id));
        } else {
            if (session.getAttribute("foodTypeId") != null) {
                foodTypeId = (String) session.getAttribute("foodTypeId");
                condition.setFoodTypeId(Integer.parseInt(foodTypeId));
            } else {
                foodTypeId = "1";
                condition.setFoodTypeId(Integer.parseInt(foodTypeId));
            }
        }
        //System.out.println("菜系ｉｄ是：" + foodTypeId);

        //设置分页参数:菜品名称
        String foodName = request.getParameter("foodName");
        condition.setFoodName(foodName);
        //将condition对象设置到分页对象中
        pageBean.setCondition(condition);

        //1.3通过分页对象查询
        foodService.findAll(pageBean);
        //保存分页对象
        request.setAttribute("pageBean", pageBean);
        //查询所有的菜系信息并保存
        List<FoodType> foodTypes = foodTypeService.selectAll();
        request.setAttribute("foodTypes", foodTypes);

        //1.4转发
        return request.getRequestDispatcher("/app/caidan.jsp");
    }

    /**
     * 详细列表展示：后台
     */
    public Object listFood(HttpServletRequest request, HttpServletResponse response) {
        //查询所有的数据
        List<ViewFood> foods = foodService.findByNoOpt();
        System.out.println(foods.toString());
        //保存到request
        request.setAttribute("foods", foods);
        //转发
        return request.getRequestDispatcher("/sys/foodList.jsp");
    }

    /**
     * 跳转到添加菜品菜单
     */
    public Object toAddPage(HttpServletRequest request, HttpServletResponse response) {
        //查询菜品类型
        List<FoodType> foodTypeList = foodTypeService.selectAll();
        //保存
        request.setAttribute("foodTypeList", foodTypeList);
        //转发
        return request.getRequestDispatcher("/sys/saveFood.jsp");
    }

    /**
     * 添加菜品
     */
    public Object addFood(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //创建Food对象
        Food food = new Food();
        //获取上传路径

        //上传文件更改编码
        String img = FileUploadUtils.upLoad(request, response);
        String foodTypeId = (String) request.getAttribute("foodTypeId");
        String foodName = (String) request.getAttribute("foodName");
        String remark = (String) request.getAttribute("remark");
        String mprice = (String) request.getAttribute("mprice");
        String price = (String) request.getAttribute("price");

        //设置属性
        food.setImg(img);
        food.setFood_type_id(Integer.parseInt(foodTypeId));
        food.setFoodName(foodName);
        food.setRemark(remark);
        food.setMprice(Double.valueOf(mprice));
        food.setPrice(Double.valueOf(price));
        System.out.println("food的属性是：" + food.toString());
        //添加操作
        foodService.add(food);

        //跳转到菜品列表页面
        return listFood(request, response);
    }

    /**
     * 删除菜品
     *
     * @param request
     * @param response
     * @return
     */
    public Object deleteFood(HttpServletRequest request, HttpServletResponse response) {
        //获取id
        String id = request.getParameter("foodId");
        //调用service
        foodService.delete(Integer.parseInt(id));
        //返回列表
        return listFood(request, response);
    }

    /**
     * 去往菜品更新菜单
     *
     * @param request
     * @param response
     * @return
     */
    public Object toUpdatedPage(HttpServletRequest request, HttpServletResponse response) {
        //去往更新页面
        String id = request.getParameter("foodId");
        //通过service查询Food
        ViewFood food = foodService.findByView(Integer.parseInt(id));
        //查询菜系列表
        List<FoodType> foodTypes = foodTypeService.selectAll();
        //保存
        request.setAttribute("food", food);
        request.setAttribute("foodTypes", foodTypes);
        //转发
        return request.getRequestDispatcher("/sys/updateFood.jsp");
    }

    /**
     * 更新菜品信息
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public Object updateFood(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //新建food类
        Food food = new Food();
        //通过文件上传工具类设置文件和字段
        String img = FileUploadUtils.upLoad(request, response);
        //获取相关值
        String id = (String) request.getAttribute("foodId");
        String foodTypeId = (String) request.getAttribute("foodTypeId");
        String foodName = (String) request.getAttribute("foodName");
        String remark = (String) request.getAttribute("remark");
        String mprice = (String) request.getAttribute("mprice");
        String price = (String) request.getAttribute("price");

        //设置属性
        food.setId(Integer.parseInt(id));
        food.setImg(img);
        food.setFood_type_id(Integer.parseInt(foodTypeId));
        food.setFoodName(foodName);
        food.setRemark(remark);
        food.setMprice(Double.valueOf(mprice));
        food.setPrice(Double.valueOf(price));
        System.out.println("food的属性是：" + food.toString());

        //更新
        foodService.update(food);

        //跳转回列表页面
        return listFood(request, response);
    }

    /**
     * 通过视图查询菜品信息
     *
     * @param request
     * @param response
     * @return
     */
    public Object findFood(HttpServletRequest request, HttpServletResponse response) {
        //获取输入的值
        String foodName = request.getParameter("foodName");
        //查询
        List<ViewFood> foods = foodService.findByName(foodName);
        //保存
        request.setAttribute("foods", foods);
        //转发
        return request.getRequestDispatcher("/sys/foodList.jsp");
    }

    /**
     * 跳转到菜品详细信息
     *
     * @param request
     * @param response
     * @return
     */
    public Object toFoodInfo(HttpServletRequest request, HttpServletResponse response) {
        //获取返回的id
        String id = request.getParameter("foodId");
        //调用service查询菜品
        ViewFood food = foodService.findByView(Integer.parseInt(id));
        List<FoodType> foodTypes = foodTypeService.selectAll();
        //保存
        request.setAttribute("food",food);
        request.setAttribute("foodTypes",foodTypes);
        //转发
        return request.getRequestDispatcher("/app/caixiangxi.jsp");
    }
}
