package servlet;

import entity.DinnerTable;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "dinnerTableServlet", urlPatterns = "/dinnerTable")
public class DinnerTableServlet extends BaseServlet {
    private Object uri;

    //查询所有的dinnerTables
    public Object findAll(HttpServletRequest request, HttpServletResponse response) {
        //调用service
        List<DinnerTable> dinnerTables = dinnerTableService.findAll();
        //System.out.println(dinnerTables.toString());
        //保存
        request.setAttribute("dinnerTables",dinnerTables);
        //转发
        uri = request.getRequestDispatcher("/sys/boardList.jsp");
        return uri;
    }

    //删除餐桌
    public Object delete(HttpServletRequest request,HttpServletResponse response){
        //查找数据
        String id = request.getParameter("id");
        //调用service
        dinnerTableService.deleteDinnerTable(Integer.parseInt(id));
        //转发
        return findAll(request,response);
    }

    //添加餐桌
    public Object add(HttpServletRequest request,HttpServletResponse response){
        //查找数据
        String tableName = request.getParameter("tableName");
        //调用service
        dinnerTableService.addDinnerTable(tableName);
        //转发
        return findAll(request,response);
    }

    //搜索餐桌
    public Object search(HttpServletRequest request,HttpServletResponse response){
        //查找数据
        String tableName = request.getParameter("tableName");
        //调用service
        List<DinnerTable> dinnerTables = dinnerTableService.findByTableName(tableName);
        //保存
        request.setAttribute("dinnerTables",dinnerTables);
        //转发
        uri = request.getRequestDispatcher("/sys/boardList.jsp");
        return uri;
    }

    /**
     * 后台更新餐桌状态：变为未预订
     */
    public Object update(HttpServletRequest request,HttpServletResponse response){
        //查询数据
        String id = request.getParameter("id");
        //时间格式化
        //Date orderDate = new Date();
        DinnerTable dinnerTable = new DinnerTable();
        //将餐桌设置为未预订
        dinnerTable.setTable_status(0);
        dinnerTable.setId(Integer.parseInt(id));
        //调用service
        dinnerTableService.updateDinnerTable(dinnerTable);
        //转发
        return findAll(request,response);
    }

}
