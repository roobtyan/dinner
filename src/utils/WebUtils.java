package utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtils {

    public static void goTo(HttpServletRequest request, HttpServletResponse response, Object uri) throws ServletException, IOException {
        //判断是否属于跳转
        if (uri instanceof RequestDispatcher){
            ((RequestDispatcher) uri).forward(request,response);
        }else if (uri instanceof String){
            response.sendRedirect(request.getContextPath() + uri);
        }
    }
}
