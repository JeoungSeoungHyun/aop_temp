package site.metacoding.reflect.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class MessageConverter{
	
	public static void resolve(Object data,HttpServletResponse resp) {
		try {
			// 버퍼 달기 전에 세팅을 해줘야한다. 컨텐트타입을		
		resp.setContentType("application/json;charset=utf-8");
		Gson gson = new Gson();
		String responseBody = gson.toJson(data);
		PrintWriter out = resp.getWriter();
		out.print(responseBody);
		} catch (IOException e) {
			writeError(resp);
		}
	}
	
	private static void writeError(HttpServletResponse resp) {
		try {
//			resp.setHeader("Content-Type", "text/html;charset=utf-8");
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("<h1>JSON 변환에 실패하였습니다.</h1>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
