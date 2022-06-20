package site.metacoding.reflect.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import site.metacoding.reflect.domain.Member;
import site.metacoding.reflect.util.UtilsLog;
import site.metacoding.reflect.web.MemberController;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet{
	
	// 서블릿은 request마다 새로 만들어지기 때문에 이렇게 해놓는것도 계속 new하게 되는것이다.
	// 숙제 이것도 앞단에서 띄워보기
//	private MemberController controller;
	
//	public DispatcherServlet() {
//		controller = new MemberController();
//	}

	private static final long serialVersionUID = 1L;
	private static final String TAG = "DispatcherServlet : ";

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UtilsLog.getInsance().info(TAG, "doDelete()");
		UtilsLog.getInsance().info(TAG, req.getRequestURI());
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberController controller = new MemberController();
		UtilsLog.getInsance().info(TAG, "doGet()");
		
		String identifier = req.getRequestURI();
		
		// 리플레션 발동
		// 런타임시 실행되어 무슨 메서드들이 있는지 확인
		Method[] methods = controller.getClass().getDeclaredMethods();
		for(Method method : methods) {
			UtilsLog.getInsance().info(TAG, method.getName());
			
			String idf = identifier.replace("/", "");
			if(idf.equals(method.getName())) {
				UtilsLog.getInsance().info(TAG, method.getName()+"이 실행되었습니다");
				try {
					method.invoke(controller, req,resp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UtilsLog.getInsance().info(TAG, "doPost()");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UtilsLog.getInsance().info(TAG, "doPut()");
	}

}
