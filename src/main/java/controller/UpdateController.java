package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.KanbanDto;
import dao.KanbanDao;
import service.KanbanUpdateService;
import service.KanbanException;

/**
 * 작업 수정 
 * 
 * @author YONGGYO
 *
 */
@WebServlet("/update")
public class UpdateController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		KanbanDto kanban = KanbanDao.getInstance().get(req.getParameter("id"));
		req.setAttribute("kanban", kanban);
		RequestDispatcher rd = req.getRequestDispatcher("main/update.jsp");
		rd.forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		KanbanUpdateService service = new KanbanUpdateService();
		try {
			service.update(req);
			out.println("<script>parent.location.reload();</script>");
		} catch (KanbanException e) {
			e.printStackTrace();
			out.println("<script>alert('" + e.getMessage() + "');</script>");
		}
	}
	
}
