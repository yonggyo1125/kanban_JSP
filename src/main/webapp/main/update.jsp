<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form name='frmUpdate' id='frmUpdate' method="post" action="<c:url value="/update"  />" target="ifrmProcess" autocomplete="off">
<input type='hidden' name='id' value='${kanban.id}'>
<h3>작업 수정</h3>
 <select name="status" id='status'>
    <option value="READY" ${kanban.status == 'READY' ? " selected":""}>준비중</option>
    <option value="PROGRESS"${kanban.status == 'PROGRESS' ? " selected":""}>진행중</option>
     <option value="DONE"${kanban.status == 'DONE' ? " selected":""}>완료</option>
</select>
 <input type="text" name="workNm" placeholder='작업내용' value='${kanban.workNm}'>
 <button type="submit" id="update"><i class='xi-pen-o'></i> 수정하기</button>
</form>