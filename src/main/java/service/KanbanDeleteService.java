package service;

import javax.servlet.http.HttpServletRequest;

import dao.KanbanDao;

public class KanbanDeleteService {
	/**
	 * 삭제 처리 
	 * 
	 * @param request
	 */
	public void delete(HttpServletRequest request) {
		String[] ids = request.getParameterValues("id");
		if (ids == null || ids.length  == 0) {
			throw new KanbanException("삭제할 작업을 선택하세요.");
		}
		
		KanbanDao dao = KanbanDao.getInstance();
		for (String id : ids) {
			dao.delete(Integer.valueOf(id));
		}
	}
}
