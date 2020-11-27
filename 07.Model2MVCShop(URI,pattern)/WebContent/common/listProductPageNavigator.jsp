<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<c:if test="${ resultPage.currentPage <= resultPage.pageUnit }">
			◀ 이전
	</c:if>
	<c:if test="${ resultPage.currentPage > resultPage.pageUnit }">
			<a href="/product/listProduct?page=${resultPage.currentPage - 1}&menu=${menu=='manage' ? 'manage' : 'search' }&searchCondition=${search.searchCondition}&searchKeyword=${search.searchKeyword}">◀ 이전</a>
	</c:if>
	
	<c:forEach var="i"  begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}" step="1">
		<a href="/product/listProduct?page=${i}&menu=${menu=='manage' ? 'manage' : 'search' }&searchCondition=${search.searchCondition}&searchKeyword=${search.searchKeyword}">${ i }</a>
	</c:forEach>
	
	<c:if test="${ resultPage.endUnitPage >= resultPage.maxPage }">
			이후 ▶
	</c:if>
	<c:if test="${ resultPage.endUnitPage < resultPage.maxPage }">
			<a href="/product/listProduct?page=${resultPage.endUnitPage + 1}&menu=${menu=='manage' ? 'manage' : 'search' }&searchCondition=${search.searchCondition}&searchKeyword=${search.searchKeyword}">이후 ▶</a>
	</c:if>
	

	
	