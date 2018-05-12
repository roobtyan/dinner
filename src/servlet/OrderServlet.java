package servlet;

import entity.DinnerTable;
import entity.view.ViewOrders;
import vo.ViewOrdersVo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @author 言立慧
 * 描述：系统管理，餐厅订单控制器
 */
@WebServlet(name = "order", urlPatterns = "/order")
public class OrderServlet extends BaseServlet {
    /**
     * 描述：餐厅订单列表
     *
     * @param request
     * @param response
     * @return
     */
    public Object toOrderList(HttpServletRequest request, HttpServletResponse response) {
        //查询所有的订单详情
        List<ViewOrdersVo> ordersVoList = ordersService.selectView();
        //保存
        request.setAttribute("orderVoList", ordersVoList);
        //转发
        return request.getRequestDispatcher("/sys/orderList.jsp");
    }

    /**
     * 查询订单详细信息
     *
     * @param request
     * @param response
     * @return
     */
    public Object toOrderDetail(HttpServletRequest request, HttpServletResponse response) {
        //通过id查询订单详细信息
        String orderId = request.getParameter("orderId");
        List<ViewOrders> ordersList = orderDetailService.selectByOrderId(Integer.parseInt(orderId));
        //保存
        request.setAttribute("ordersList", ordersList);
        //转发
        return request.getRequestDispatcher("/sys/orderDetail.jsp");
    }

    public Object endAccount(HttpServletRequest request, HttpServletResponse response) {
        //取值
        String tableId = request.getParameter("tableId");
        String orderId = request.getParameter("orderId");
        //查询相关信息
        DinnerTable dinnerTable = dinnerTableService.findById(Integer.parseInt(tableId));
        //设置变化值(0为空闲，1为占用)
        dinnerTable.setTable_status(0);
        //更新
        dinnerTableService.updateDinnerTable(dinnerTable);
        ordersService.update(Integer.parseInt(orderId), 0);
        orderDetailService.update(Integer.parseInt(orderId), 0);

        //跳转到列表
        return toOrderList(request, response);
    }
}
