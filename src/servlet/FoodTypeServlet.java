package servlet;

import entity.FoodType;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 作者：言立慧
 * 描述：菜系管理控制器
 * 时间: 2018/4/23
 */
@WebServlet(name = "FoodTypeServlet", urlPatterns = "/foodType")
public class FoodTypeServlet extends BaseServlet {
    //uri
    private Object uri;

    //具体方法
    //菜系列表
    public Object typelist(HttpServletRequest request, HttpServletResponse response) {
        //获取所有菜系
        List<FoodType> foodTypes = foodTypeService.selectAll();
        //保存
        request.setAttribute("foodTypes", foodTypes);
        //跳转
        uri = request.getRequestDispatcher("/sys/type/foodType_list.jsp");
        return uri;
    }

    //添加菜系
    public Object addType(HttpServletRequest request, HttpServletResponse response) {

        //获取数据
        String typeName = request.getParameter("typeName");
        //封装对象
        FoodType foodType = new FoodType();
        foodType.setTypeName(typeName);
        //调用service
        foodTypeService.addFoodType(foodType);
        //重定向列表
        uri = request.getContextPath() + "/foodType?method=typelist";
        return uri;

    }


    //更新菜系视图
    public Object viewUpdate(HttpServletRequest request, HttpServletResponse response) {

        //获取数据
        String id = request.getParameter("typeId");
        //调用service
        FoodType foodType = foodTypeService.selectByPrimaryKey(Integer.parseInt(id));
        //保存
        request.setAttribute("foodType", foodType);
        //跳转到更新页面
        uri = request.getRequestDispatcher("/sys/type/foodType_update.jsp");
        return uri;
    }

    //删除菜系
    public Object delete(HttpServletRequest request, HttpServletResponse response) {

        //获取数据
        String id = request.getParameter("typeId");
        //调用service
        foodTypeService.deleteFoodType(Integer.parseInt(id));
        //回到列表页面
        uri = "/foodType?method=typelist";

        return uri;
    }

    //更新菜系
    public Object update(HttpServletRequest request, HttpServletResponse response) {

        //获取数据
        String id = request.getParameter("typeId");
        String typeName = request.getParameter("typeName");
        //封装数据
        FoodType foodType = new FoodType();
        foodType.setTypeName(typeName);
        foodType.setId(Integer.parseInt(id));
        //调用service
        foodTypeService.updateFoodType(foodType);
        //重定向到列表
        uri = "/foodType?method=typelist";
        return uri;
    }

    public Object search(HttpServletRequest request, HttpServletResponse response) {

        //获取数据
        String typeName = request.getParameter("typeName");
        System.out.println("输入的搜索值是:" + typeName);
        //调用service
        List<FoodType> foodTypes = foodTypeService.selectByTypeName(typeName);
        //保存
        request.setAttribute("foodTypes", foodTypes);
        //转发
        uri = request.getRequestDispatcher("/sys/type/foodType_list.jsp");
        return uri;

    }
}
