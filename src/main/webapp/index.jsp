<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="outline/header.jsp"  />

<h2>작업등록</h2>
<form name='frmRegist' id='frmRegist' method="post" action="<c:url value="register"  />" target="ifrmProcess" autocomplete="off">
    <dl>
        <dt>작업 구분</dt>
        <dd>
            <select name="status" id='status'>
                <option value="READY">준비중</option>
                <option value="PROGRESS">진행중</option>
                <option value="DONE">완료</option>
            </select>
        </dd>
        <dt>작업내용</dt>
        <dd>
            <input type="text" name="workNm" >
        </dd>
        <button type="submit" id="register">등록하기</button>
    </dl>
</form>

<h2>작업목록</h2>
<form id='frmDelete' name='frmDelete' method="post" action="<c:url value="/delete" />" target="ifrmProcess" autocomplete="off">
    <div id='wrap'>
	    <%-- 준비중 목록  --%>
	    <div class="works ready">
	        <h3>준비중</h3>
		    <ul>
		        <c:forEach var="kanban" items="${list}" varStatus="status">
		           <c:if test="${kanban.status == 'READY' }">
		            <li>
		                <input type="checkbox" name='id'  value='${kanban.id}' id='ready_${status.index}'>
		                <label for='ready_${status.index}'>${status.count}.${kanban.workNm}</label>
		            </li>
		            </c:if>
		        </c:forEach> 
		    </ul>
	    </div>
	    <%-- 진행중 목록  --%>
	    <div class="works progress">
	        <h3>진행중</h3>
	        <ul>
	            <c:forEach var="kanban" items="${list}" varStatus="status">
	               <c:if test="${kanban.status == 'PROGRESS' }">
	                <li>
	                    <input type="checkbox" name='id'  value='${kanban.id}' id='progress_${status.index}'>
	                    <label for='ready_${status.index}'>${status.count}.${kanban.workNm}</label>
	                </li>
	                </c:if>
	            </c:forEach> 
	        </ul>
	    </div>
	    <%-- 완료 목록 --%>
	    <div class="works done">
	        <h3>완료</h3>
	        <ul>
	            <c:forEach var="kanban" items="${list}" varStatus="status">
	               <c:if test="${kanban.status == 'DONE' }">
	                <li>
	                    <input type="checkbox" name='id'  value='${kanban.id}' id='done_${status.index}'>
	                    <label for='ready_${status.index}'>${status.count}.${kanban.workNm}</label>
	                </li>
	                </c:if>
	            </c:forEach> 
	        </ul>
	    </div>
    </div><!--// wrap  -->
    <button type="submit"  id='delete'>선택 작업 삭제하기</button>
</form>

<jsp:include page="outline/footer.jsp" />