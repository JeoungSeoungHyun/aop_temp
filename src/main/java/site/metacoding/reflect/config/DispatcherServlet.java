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
		
		if(identifier.equals("/join")){
			
			// 인터셉터 코드
			HttpSession session = req.getSession();
			Member member = (Member)session.getAttribute("principal");
			
			if(member == null) {
				PrintWriter out = resp.getWriter();
				out.println("인증되지 않은 사용자입니다");
				out.flush();
				out.close();
			}
			
			controller.join(req,resp);
		} else if(identifier.equals("/login")){
			controller.login(req,resp);
		} else if(identifier.equals("/findById")){
			controller.findById(req,resp);
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
