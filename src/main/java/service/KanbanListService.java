package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.KanbanDto;
import constants.Status;
import dao.KanbanDao;

/**
 * 작업목록 조회
 * 
 * @author YONGGYO
 *
 */
public class KanbanListService {
	
	public List<KanbanDto> gets(HttpServletRequest request) {
		String _status = request.getParameter("status");
		Status status = null;
		if (_status != null) {
			status = Enum.valueOf(Status.class, _status);
		}
		
		KanbanDao dao = KanbanDao.getInstance();
		
		List<KanbanDto> list = dao.gets(status);
		
		return list;
	}
}
