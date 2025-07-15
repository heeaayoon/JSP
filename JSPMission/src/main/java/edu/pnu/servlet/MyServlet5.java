package edu.pnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/myservlet05")
public class MyServlet5 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset = utf-8");
		PrintWriter out = res.getWriter();
		out.println("<table border='1'>");
		out.println("<tr>");
		out.println("<th>번호</th>");
		out.println("<th>나라</th>");
		out.println("<th>수도</th>");
		out.println("<th>인구</th>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>1</td>");
		out.println("<td>대한민국</td>");
		out.println("<td>서울</td>");
		out.println("<td>1000만</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>2</td>");
		out.println("<td>미국</td>");
		out.println("<td>워싱턴</td>");
		out.println("<td>70만</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>3</td>");
		out.println("<td>일본</td>");
		out.println("<td>도쿄</td>");
		out.println("<td>1400만</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>4</td>");
		out.println("<td>중국</td>");
		out.println("<td>베이징</td>");
		out.println("<td>2100만</td>");
		out.println("</tr>");
		out.println("</table>");
	}
}
