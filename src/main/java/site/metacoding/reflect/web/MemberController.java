package site.metacoding.reflect.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import site.metacoding.reflect.config.MessageConverter;
import site.metacoding.reflect.config.ViewReslover;
import site.metacoding.reflect.config.web.RequestMapping;
import site.metacoding.reflect.domain.Member;
import site.metacoding.reflect.test.MemberTest;
import site.metacoding.reflect.util.UtilsLog;

// API
public class MemberController {

	private static final String TAG = "MemberController : ";

	// 컴파일 시 오류 checked exception 런타임 시 오류 unchecked exception

	/* GET/join 요청시 */
	@RequestMapping("/join")
	public void join(MemberTest member, HttpServletRequest req, HttpServletResponse resp) {
		System.out.println(req.getQueryString());
		UtilsLog.getInsance().info(TAG, "join()");
		UtilsLog.getInsance().info(TAG, "Serivce가 호출되어 회원가입이 완료되었습니다.");
		req.setAttribute("username", "ssar");
//		resp.sendRedirect("main.jsp");
		ViewReslover.reslove("main.jsp", req, resp);

	}

	/* GET/login 요청시 */
	@RequestMapping("/login")
	public void login(HttpServletRequest req, HttpServletResponse resp) {
		UtilsLog.getInsance().info(TAG, "login()");
		UtilsLog.getInsance().info(TAG, "Serivce가 호출되어 로그인이 완료되었습니다.");
		HttpSession session = req.getSession();
		session.setAttribute("principal", new Member(1, "ssar", "1234"));
		ViewReslover.reslove("main.jsp", req, resp);
	}

	/* GET/findById 요청시 */
	@RequestMapping("/findById")
	public void findById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UtilsLog.getInsance().info(TAG, "findById()");
		UtilsLog.getInsance().info(TAG, "Serivce가 호출되어 Member를 찾았습니다.");
		Member memberEntity = new Member(1, "ssar", "1234");
		MessageConverter mc = new MessageConverter();
		mc.resolve(memberEntity, resp);
//		MessageConverter.resolve(memberEntity, resp);
	}

}
