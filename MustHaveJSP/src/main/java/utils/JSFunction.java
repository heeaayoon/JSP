package utils;

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

}
