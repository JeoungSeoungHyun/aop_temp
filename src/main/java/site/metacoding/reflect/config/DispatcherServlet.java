package site.metacoding.reflect.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import site.metacoding.reflect.config.web.RequestMaping;
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
			Annotation annotation = method.getDeclaredAnnotation(RequestMaping.class);
//			System.out.println(annotation.toString());
			RequestMaping requestMapping = (RequestMaping)annotation;
//			System.out.println("밸류 : " +requestMapping.value().toString());
//			System.out.println("idf : " + identifier);
			if(requestMapping.value().equals(identifier)) {				
				try {
					Parameter[] params = method.getParameters();
					// invoke에서 parameter를 Object또는 Object[]로 받기 때문에 arrayList로는 불가능하다.
					// 이 부분부터 확인을 해봤어야 하는데 생각없이 arrayList로 해버려서 한참 헤맸네..
//					List<Object> paramList = new ArrayList<>(); 
					Object[] paramList= new Object[params.length];
					for(int i=0;i<params.length;i++) {
						Class<?> cls;
						cls = params[i].getType();
//						String[] typeParse;
						// 정규표현식에서 .은 그냥 인식이 안된다.
//						typeParse = params[i].getType().getTypeName().split("\\.");
//						String type = typeParse[typeParse.length-1];
						// 1. HttpServletRequest를 찾으면 req를 넣어준다.
						// 바로 비교가 안되는데 이유가 뭘까???
						if(cls == HttpServletRequest.class) {
//							System.out.println("HttpServletRequest입니다.");
							paramList[i] = req;
						// 2. HttpServletResponse를 찾으면 resp를 넣어준다.
						} else if(cls  == HttpServletResponse.class) {
//							System.out.println("HttpServletResponse입니다.");
							paramList[i] = resp;
						// 3. Member를 찾으면 없기 때문에 new해서 넣어준다.
						} else {
//							System.out.println(cls + "입니다.");
							paramList[i] =  cls.getConstructor().newInstance();
							Method[] ms = paramList[i].getClass().getDeclaredMethods();
							for(Method m : ms) {
								if(m.getName().contains("set")) {
									String field = req.getParameter(m.getName().replace("set", "").toLowerCase());
									if(field != null) {
//										System.out.println(field);
										m.invoke(paramList[i], field);
									}
								}								
							}
						}
					}
					// invoke에는 파라미터 부분에 List가 아닌 배열을 넣어주면 순서대로 바인딩이 된다.
//					method.invoke(controller, req,resp);
					method.invoke(controller, paramList);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break; // 찾은경우 for 문 순회하지 않도록
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
