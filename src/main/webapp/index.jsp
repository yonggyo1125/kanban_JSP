<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="outline/header.jsp" />

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
        <button type="submit"id="register">등록하기</button>
    </dl>
</form>
<jsp:include page="outline/footer.jsp" />