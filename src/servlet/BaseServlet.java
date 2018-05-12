package servlet;

import factory.BeanFactory;
import service.*;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 基础servlet，所有的Servlet都通过这个servlet执行
 * 项目中通用的Servlet，希望所有的servlet都继承此类
 */
public class BaseServlet extends HttpServlet {
    //创建service
    protected IDinnerTableService dinnerTableService = BeanFactory.getInstance("dinnerTableService",IDinnerTableService.class);
    protected IFoodTypeService foodTypeService = BeanFactory.getInstance("foodTypeService",IFoodTypeService.class);
    protected IFoodService foodService = BeanFactory.getInstance("foodService",IFoodService.class);
    protected IOrderDetailService orderDetailService = BeanFactory.getInstance("orderDetailService",IOrderDetailService.class);
    protected IOrdersService ordersService = BeanFactory.getInstance("orderService",IOrdersService.class);
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //用于接收uri对象
        Object returnValue;
        //处理编码格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("html/text;charset=UTF-8");

        //获取方法名称
        //约定：前端传来的值必须和方法名称一致
        String methodName = req.getParameter("method");
        if (methodName == null){
            //如果获取到的方法名称是空，则默认为起始页面
            methodName = "listDinnerTable";
        }
        System.out.println("调用的方法是" + methodName);
        try {
            //获取当前类的字节吗对象
            Class clazz = this.getClass();
            //获取当前请求的方法
            Method method = clazz.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            //运行方法，返回uri
            returnValue = method.invoke(this,req,resp);
        }catch (Exception e){
            e.printStackTrace();
            returnValue = "/error/error.jsp";
        }

        //跳转
        WebUtils.goTo(req,resp,returnValue);
    }
}
