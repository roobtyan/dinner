package servlet;

import entity.DinnerTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "indexServlet", urlPatterns = "/index")
public class IndexServlet extends BaseServlet {
    private Object uri;
    //展示主界面
    public Object listDinnerTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service处理
        List<DinnerTable> dinnerTables = dinnerTableService.findNoUse();
        //保存
        request.setAttribute("dinnerTables", dinnerTables);
        //转发
        uri = request.getRequestDispatcher("/app/index.jsp");
        //跳转
        return uri;
    }
}
