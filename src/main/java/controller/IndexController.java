package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import dto.KanbanDto;
import service.KanbanListService;


@WebServlet("/")
public class IndexController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
		rd.forward(req, res);
		
		KanbanListService service = new KanbanListService();
		List<KanbanDto> list = service.gets(req);
		
		req.setAttribute("list", list);
		
	}	
}
