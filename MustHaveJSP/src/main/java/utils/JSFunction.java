package utils;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.JspWriter;

public class JSFunction {
	//메세지 알림창을 띄운 후, 명시한 URL로 이동
	public static void alertLocation(String msg, String url, JspWriter out) {
		try {
			String script = "" + "<script>"+"alert('"+msg+"');"+"location.href=' " + url+"';"+"</script>";
			out.println(script);
		}catch(Exception e) {}
	}
	
	//메세지 알림창을 띄운 후, 이전 페이지로 돌아갑니다.
	public static void alertBack(String msg, JspWriter out) {
		try {
			String script = "" + "<script>"+"alert('"+msg+"');"+"history.back()"+"</script>";
			out.println(script);
		}catch(Exception e) {}
	}

	//메세지 알림창을 띄운 후, 명시한 URL로 이동
	//서블릿에서 사용할 메서드(서블릿에서 즉시 내용을 출력하려함) 
	//			-> response 내장 객체의 setContentType() 메서드로 콘텐츠타입 지정 & 
	//				PrintWriter 객체로 실제 내용(HTML 코드, JSON 데이터 등)을 텍스트 형태로 쓸 수 있게 해주기
	public static void alertLocation(HttpServletResponse resp, String msg, String url) {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String script = "" + "<script>"+"alert('"+msg+"');"+"location.href=' " + url+"';"+"</script>";
			writer.print(script);
		}catch(Exception e) {}
	}
	
	//메세지 알림창을 띄운 후, 이전 페이지로 돌아감
	public static void alertBack(HttpServletResponse resp, String msg) {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String script = "" + "<script>"+"alert('"+msg+"');"+"history.back()"+"</script>";
			writer.print(script);
		}catch(Exception e) {}
	}

}
