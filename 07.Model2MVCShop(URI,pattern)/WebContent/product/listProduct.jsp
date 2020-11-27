<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%--
<%
	List<ProductVO> list= (List<ProductVO>)request.getAttribute("list");
	Page resultPage=(Page)request.getAttribute("page");
	
	Search searchVO = (Search)request.getAttribute("search");
	//==> null �� ""(nullString)���� ����
	String searchCondition = CommonUtil.null2str(searchVO.getSearchCondition());
	String searchKeyword = CommonUtil.null2str(searchVO.getSearchKeyword());
%>
--%>
<!DOCTYPE html>
<html>
<head>
<title>��ǰ ����</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">

	function fncGetProductList() {
		document.detailForm.submit();
	}
	
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

	<div style="width: 98%; margin-left: 10px;">

		<form name="detailForm"
			action="/product/listProduct?menu=${menu=='manage' ? 'manage' : 'search' }"
			method="post">

			<table width="100%" height="37" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"
						width="15" height="37" /></td>
					<td background="/images/ct_ttl_img02.gif" width="100%"
						style="padding-left: 10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="93%" class="ct_ttl01">${menu=='manage' ? '��ǰ����' : '��ǰ�˻�' }</td>
							</tr>
						</table>
					</td>
					<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"
						width="12" height="37" /></td>
				</tr>
			</table>


			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>


					<td align="right"><select name="searchCondition"
						class="ct_input_g" style="width: 80px">

							<option value="0"
								${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>��ǰ��ȣ</option>
							<option value="1"
								${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>��ǰ�̸�</option>
							<option value="2"
								${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>����</option>



					</select> <input type="text" name="searchKeyword"
						value="${! empty search.searchKeyword ? search.searchKeyword : ""}"
						class="ct_input_g" style="width: 200px; height: 19px"></td>



					<td align="right" width="70">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="17" height="23"><img
									src="/images/ct_btnbg01.gif" width="17" height="23"></td>
								<td background="/images/ct_btnbg02.gif" class="ct_btn01"
									style="padding-top: 3px;"><a
									href="javascript:fncGetProductList();">�˻�</a></td>
								<td width="14" height="23"><img
									src="/images/ct_btnbg03.gif" width="14" height="23"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>


			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td colspan="11">��ü ${resultPage.totalCount } �Ǽ�, ����
						${resultPage.currentPage } ������</td>
				</tr>
				<tr>
					<td colspan="11" align="right"><a
						href="/listProduct.do?menu=${menu=='manage' ? 'manage' : 'search' }&searchCondition=3"
						align="right">�������ݼ�</a></td>
				</tr>
				<tr>
					<td colspan="11" align="right"><a
						href="/listProduct.do?menu=${menu=='manage' ? 'manage' : 'search' }&searchCondition=4"
						align="right">�������ݼ�</a></td>
				</tr>
				<tr>
					<td class="ct_list_b" width="100">No</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">��ǰ��</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">����</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">�����</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">�������</td>
				</tr>
				<tr>
					<td colspan="11" bgcolor="808285" height="1"></td>
				</tr>
				<%--
					int no = list.size();
				for (int i = 0; i < list.size(); i++) {
					ProductVO vo = (ProductVO) list.get(i);
				--%>

				<c:set var="i" value="0" />
				<c:forEach var="product" items="${list}">
					<c:set var="i" value="${i+1}" />
					<tr class="ct_list_pop">
						<td align="center">${i}</td>
						<td></td>
						<c:choose>
							<c:when test="${menu=='manage'}">
								<td align="left"><a
									href="/product/updateProduct?prodNo=${product.purchaseProd.prodNo}&menu=${menu=='manage' ? 'manage' : 'search' }">${product.purchaseProd.prodName}</a>
								</td>
								<td></td>
								<td align="left">${product.purchaseProd.price}</td>
								<td></td>
								<td align="left">${product.purchaseProd.regDate}</td>
								<td></td>
								<c:if test="${fn:trim(product.tranCode)==''}">
									<td align="left">�Ǹ���</td>
								</c:if>
								<c:if test="${fn:trim(product.tranCode)=='1' }">
									<td align="left">���ſϷ� <a
										href="/purchase/updateTranCodeByProd?prodNo=${product.purchaseProd.prodNo }&tranCode=2">����ϱ�</a></td>
								</c:if>
								<c:if
									test="${fn:trim(product.tranCode)=='2' || fn:trim(product.tranCode)=='3' }">
									<td align="left">������</td>
								</c:if>
							</c:when>
							<c:when test="${menu=='search'}">
								<td align="left"><a
									href="/product/getProduct?prodNo=${product.prodNo}">${product.prodName}</a>
								</td>
								<td></td>
								<td align="left">${product.price}</td>
								<td></td>
								<td align="left">${product.regDate}</td>
								<td></td>
								<c:if test="${fn:trim(product.proTranCode)==''}">
									<td align="left">�Ǹ���</td>
								</c:if>
								<c:if test="${fn:trim(product.proTranCode)=='1' }">
									<td align="left">������</td>
								</c:if>
								<c:if
									test="${fn:trim(product.proTranCode)=='2' || fn:trim(product.proTranCode)=='3' }">
									<td align="left">������</td>
								</c:if>
									</c:when>
						</c:choose>
						

					</tr>
					<tr>
						<td colspan="11" bgcolor="D6D7D6" height="1"></td>
					</tr>
				</c:forEach>

			</table>

			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td align="center"> <jsp:include
							page="../common/listProductPageNavigator.jsp" /></td>
				</tr>
			</table>
			<!--  ������ Navigator �� -->

		</form>

	</div>
</body>
</html>