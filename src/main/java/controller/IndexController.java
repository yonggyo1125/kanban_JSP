package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import dto.KanbanDto;
import service.KanbanListService;


@WebServlet("/main")
public class IndexController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		KanbanListService service = new KanbanListService();
		List<KanbanDto> list = service.gets(req);
		
		req.setAttribute("list", list);
		
		RequestDispatcher rd = req.getRequestDispatcher("main/index.jsp");
		rd.forward(req, res);
	}	
}
