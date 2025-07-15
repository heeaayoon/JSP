package edu.pnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/gugudan01")
public class Gugudan01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String snum = req.getParameter("num");
		int num = 2; //디폴트 설정
		if (snum != null)  num = Integer.parseInt(snum);
		
		res.setContentType("text/html; charset = utf-8");
		PrintWriter out = res.getWriter();
		out.println("<h2>"+num+"단입니다.</h2>");
		out.println("<ul>");
		for(int i =1;i<=9;i++) {
			out.println(String.format("<li>%d*%d=%d</li>",num, i, num*i));			
		}
		out.println("</ul>");
	}
	



}
