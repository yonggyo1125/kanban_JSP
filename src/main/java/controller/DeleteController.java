package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.KanbanDeleteService;
import service.KanbanException;

@WebServlet("/delete")
public class DeleteController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=utf-8");
		KanbanDeleteService service = new KanbanDeleteService();
		PrintWriter out = res.getWriter();
		try {
			service.delete(req);
			out.println("<script>parent.location.reload();</script>");
		} catch (KanbanException e) {
			out.println("<script>alert('" + e.getMessage() + "');</script>");
		}
	}
	
}
