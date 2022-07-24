package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.KanbanException;
import service.KanbanRegisterService;

/**
 * 작업 등록 
 * 
 * @author YONGGYO
 *
 */
public class RegisterController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = res.getWriter();
		try {
			KanbanRegisterService kanbanService = new KanbanRegisterService();
			kanbanService.regist(req); // 등록 처리 
			
			out.println("<script>parent.location.reload();</script>");
		} catch (KanbanException e) {
			e.printStackTrace();
			out.println("<script>alert('" + e.getMessage() + "');</script>");
		}
	}
	
}
