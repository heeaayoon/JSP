package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;
//로그인 여부를 확인해주는 필터 클래스
// /09PagingBoard/DeleteProcess.jsp, Edit.jsp, Write.jsp 에서 전부 로그인이 필요했음 -> 해당요청명과 애너테이션으로 매핑하기

@WebFilter(urlPatterns = {"/09PagingBoard/DeleteProcess.jsp", "/09PagingBoard/Edit.jsp","/09PagingBoard/Write.jsp"})
public class IsSessionFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		HttpSession session = req.getSession();
		if(session.getAttribute("UserId")==null) { //로그인 상태를 확인하기
			//로그아웃 상태
			String backUrl = req.getRequestURI(); //현재 요청된 URL을 읽어오기
			JSFunction.alertLocation(resp, "[Filter] 로그인 후 이용해주세요", "../15FilterListener/LoginFilter.jsp?backUrl="+backUrl);
			return;
		}else {
			//로그인 상태
			chain.doFilter(request, response);
		}
	}

}
