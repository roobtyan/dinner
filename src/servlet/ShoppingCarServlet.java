package servlet;

import entity.DinnerTable;
import entity.FoodType;
import entity.OrderDetail;
import entity.Orders;
import entity.view.ViewOrders;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 描述：购物车servlet
 *
 * @author 言立慧
 */
@WebServlet(name = "shoppingCar", urlPatterns = "/shopping")
public class ShoppingCarServlet extends BaseServlet {
    public Object addFood(HttpServletRequest request, HttpServletResponse response) {
        //设置标志位，判断是否为存在session中的元素
        int flag = 0;
        //从前端获取加入的值
        String foodId = request.getParameter("foodId");
        String price = request.getParameter("price");
        //创建接收类
        ViewOrders viewOrders = new ViewOrders();
        //设置值
        viewOrders.setFood_id(Integer.parseInt(foodId));
        viewOrders.setPrice(Double.valueOf(price));
        viewOrders.setSmPrice(viewOrders.getSmPrice());
        viewOrders.setFoodName(foodService.findById(Integer.parseInt(foodId)).getFoodName());
        //从session中获取
        HttpSession session = request.getSession();
        List<ViewOrders> orders = (List<ViewOrders>) session.getAttribute("orders");
        if (orders != null) {
            for (ViewOrders o : orders) {
                if (o.getFood_id() == viewOrders.getFood_id()) {
                    o.setCount(o.getCount() + 1);
                    flag = 1;
                }
            }
            //如果标志位不为1（没有增加菜品count）
            if (flag != 1) {
                orders.add(viewOrders);
            }
        } else {
            orders = new ArrayList<>();
            orders.add(viewOrders);
        }
        //计算总价格
        double totalPrice = 0.0;
        for (ViewOrders o : orders) {
            totalPrice = totalPrice + o.getSmPrice();
        }
        System.out.println(orders.toString());
        //重新设置session
        session.setAttribute("totalPrice", totalPrice);
        session.setAttribute("orders", orders);
        request.setAttribute("totalPrice", totalPrice);
        //转发
        return toShoppingCar(request, response);
    }

    /**
     * 转发到餐车订餐列表
     *
     * @param request
     * @param response
     * @return
     */
    public Object toShoppingCar(HttpServletRequest request, HttpServletResponse response) {
        //查询所有菜品
        List<FoodType> foodTypes = foodTypeService.selectAll();

        //从session中读取已经保存的列表
        HttpSession session = request.getSession();
        List<ViewOrders> orders = (List<ViewOrders>) session.getAttribute("orders");
        //保存
        request.setAttribute("foodTypes", foodTypes);
        request.setAttribute("orders", orders);

        //转发
        return request.getRequestDispatcher("/app/clientCart.jsp");
    }

    /**
     * 删除购物车中的菜品
     *
     * @param request
     * @param response
     * @return
     */
    public Object deleteFood(HttpServletRequest request, HttpServletResponse response) {
        //获取删除的id
        String foodId = request.getParameter("foodId");
        //获取session
        HttpSession session = request.getSession();
        List<ViewOrders> orders = (List<ViewOrders>) session.getAttribute("orders");
        //迭代删除
        Iterator iterator = orders.iterator();
        while (iterator.hasNext()) {
            ViewOrders o = (ViewOrders) iterator.next();
            if (o.getFood_id() == Integer.parseInt(foodId)) {
                //找到需要删除的对象
                iterator.remove();
            }
        }
        //重新设置总价格
        double totalPrice = 0.0;
        for (ViewOrders o : orders) {
            totalPrice = totalPrice + o.getSmPrice();
        }
        System.out.println(orders.toString());
        //重新设置session
        session.setAttribute("totalPrice", totalPrice);
        //放入session
        session.setAttribute("orders", orders);
        request.setAttribute("totalPrice", totalPrice);
        return toShoppingCar(request, response);
    }

    /**
     * 跳转到下单页面
     *
     * @param request
     * @param response
     * @return
     */
    public Object order(HttpServletRequest request, HttpServletResponse response) {
        //从session中获取当前的所有orders
        HttpSession session = request.getSession();
        List<ViewOrders> orders = (List<ViewOrders>) session.getAttribute("orders");
        //从session中获取总价
        double total = (double) session.getAttribute("totalPrice");
        //输出测试
        System.out.println("已定的菜品信息：" + orders.toString());
        System.out.println("原来的总价格：" + total);

        /*start:重新设置数量，并计算单个菜品小计价格*/
        String[] count = request.getParameterValues("count");
        int i = 0;
        for (ViewOrders o : orders) {
            o.setCount(Integer.parseInt(count[i]));
            o.setSmPrice(o.getSmPrice());
            i++;
        }
        session.setAttribute("orders", orders);
        /*end:重新计算价格设置单位完成*/

        /*start：重新设置总价格*/
        double totalPrice = 0.0;
        for (ViewOrders o : orders) {
            totalPrice = totalPrice + o.getSmPrice();
        }
        session.setAttribute("totalPrice", totalPrice);
        System.out.println("总金额：" + totalPrice);
        /*end:重新计算完成*/

        //查找所有菜系信息
        List<FoodType> list = foodTypeService.selectAll();
        //保存
        request.setAttribute("orders", orders);
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("foodTypes", list);
        return request.getRequestDispatcher("/app/clientOrderList.jsp");
    }

    /**
     * 下单并结账（客户端）
     * @param request
     * @param response
     * @return
     * @throws ParseException
     * @throws ServletException
     * @throws IOException
     */
    public Object account(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {
        //从session中获取对象
        HttpSession session = request.getSession();
        List<ViewOrders> orders = (List<ViewOrders>) session.getAttribute("orders");
        double totalPrice = (double) session.getAttribute("totalPrice");
        DinnerTable dinnerTable = (DinnerTable) session.getAttribute("dinnerTable");

        /*start:开始写入数据库*/
        Orders o = new Orders();
        List<OrderDetail> orderDetails = new ArrayList<>();
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        String d = dt.format(calendar.getTime());

        //保存数据:order
        int id = ordersService.countOrders() + 1;
        o.setId(id);
        o.setOrder_date(dt.parse(d));
        o.setTable_id(dinnerTable.getId());
        o.setTotal_price(totalPrice);
        //保存数据：ordersList
        for (ViewOrders orders1 : orders) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setFood_count(orders1.getCount());
            orderDetail.setFood_id(orders1.getFood_id());
            orderDetail.setOrder_id(id);
            //1为未结账，0为结账
            orderDetail.setOrder_status(1);
            orderDetails.add(orderDetail);
        }
        //1为占用，0为未占用
        dinnerTable.setTable_status(1);

        //写入数据库
        ordersService.insert(o);
        for (OrderDetail od : orderDetails){
            orderDetailService.insert(od);
        }
        //更新餐桌信息
        dinnerTableService.updateDinnerTable(dinnerTable);

        /*end:数据库写入结束*/
        //跳转
        return new IndexServlet().listDinnerTable(request,response);
    }
}
