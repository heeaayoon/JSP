package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import membership.MemberDAO;
import membership.MemberDTO;
import utils.JSFunction;

@WebFilter(filterName = "LoginFilter", urlPatterns = "/15FilterListener/LoginFilter.jsp")
public class LoginFilter implements Filter{
	String sqldriver, sqlurl, sqlid, sqlpwd;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext application = filterConfig.getServletContext();
		
		sqldriver = application.getInitParameter("MySQLDriver");
		sqlurl = application.getInitParameter("MySQLURL");
		sqlid = application.getInitParameter("MySQLId");
		sqlpwd = application.getInitParameter("MySQLPwd");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		HttpSession session = req.getSession();
		String method = req.getMethod();
		
		if(method.equals("POST")) {
			String user_id = request.getParameter("user_id");
			String user_pw = request.getParameter("user_pw");
		
			MemberDAO dao = new MemberDAO(sqldriver, sqlurl, sqlid, sqlpwd);
			MemberDTO memberDTO = dao.getMemberDTO(user_id, user_pw);
			dao.close();

			if(memberDTO.getId()!=null){ //일치하는 회원이 존재
				//세션에 로그인 정보 저장
				session.setAttribute("UserId", memberDTO.getId());
				session.setAttribute("UserName", memberDTO.getName());
				
				//다음 페이지로 이동
				String backUrl = request.getParameter("backUrl");
				if(backUrl!=null&&!backUrl.equals("")) {
					JSFunction.alertLocation(resp, "로그인 전 요청 페이지로 이동합니다.", backUrl);
					return;
				}else {
					resp.sendRedirect("../15FilterListener/LoginFilter.jsp");
				}
			}else{ //일치하는 회원이 없음
				req.setAttribute("LoginErrMsg", "로그인 오류입니다.");
				req.getRequestDispatcher("../15FilterListener/LoginFilter.jsp").forward(req, resp);
			}
		}else if(method.equals("GET")) {
			String mode = request.getParameter("mode");
			if(mode!=null && mode.equals("logout")) {
				session.invalidate();
			}
		}
		chain.doFilter(request, response);
	}
}
