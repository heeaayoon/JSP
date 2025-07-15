package edu.pnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/musthave")
public class Musthave extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tbl = req.getParameter("table"); //파라미터 받아오기(?table= 이부분)
		
		if(tbl.equals("member")) {
			req.getRequestDispatcher("/member.jsp").forward(req, resp);
		}else if(tbl.equals("member")) {
			req.getRequestDispatcher("/board.jsp").forward(req, resp);
		}else {
			//tbl==null일 때 수정
			
		}
	
	}
}
