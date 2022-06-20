package site.metacoding.reflect.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// View를 찾아주고 , request를 유지한다.
public class ViewReslover {
	
	public static void reslove(String path, HttpServletRequest req, HttpServletResponse resp) {
		try {
			RequestDispatcher dis = req.getRequestDispatcher("main.jsp");
			dis.forward(req, resp);
		} catch (Exception e) {
			writeError(resp);
		}
	}
	
	private static void writeError(HttpServletResponse resp) {
		try {	
//			resp.setHeader("Content-Type", "text/html;charset=utf-8");
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("<h1>파일을 찾을 수 없습니다</h1>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
