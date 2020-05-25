<%@ page import="pojo.Order" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
		 pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../../include/admin/adminHeader.jsp"%>
<%@include file="../../include/admin/adminNavigator.jsp"%>

<script>
	$(function() {
		$("#addForm").submit(function() {
			if (checkEmpty("name", "订单名称"))
				return true;
			return false;
		});
		$("button.orderPageCheckOrderItems").click(function() {
			var oid = $(this).attr("oid");
			$("tr.orderPageOrderItemTR[oid=" + oid + "]").toggle();
		});
	});
</script>
<title>订单管理</title>
<br>
<br>
<br>
<br>
<div class="workingArea">
	<h1 class="label label-info">订单管理</h1>
	<br> <br>
	<div class="listDataTableDiv">
		<table
			class="table table-striped table-bordered table-hover1  table-condensed">
			<thead>
				<tr class="success">
					<th>ID</th>
					<th>状态</th>
					<th>金额</th>
					<th width="100px">商品数量</th>
					<th width="100px">买家名称</th>
					<th>创建时间</th>
					<th>支付时间</th>
					<th>发货时间</th>
					<th>确认收货时间</th>
					<th width="120px">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orders}" var="o">
					<tr>
						<td>${o.id}</td>
						<td id="${o.id}">${o.statusDesc}</td>
						<td>￥<fmt:formatNumber type="number" value="${o.total}"
								minFractionDigits="2" /></td>
						<td align="center">${o.totalNumber}</td>
						<td align="center">${o.user.name}</td>

						<td><fmt:formatDate value="${o.createDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><fmt:formatDate value="${o.payDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><fmt:formatDate value="${o.deliveryDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><fmt:formatDate value="${o.confirmDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>

						<td>
							<button oid=${o.id
								}
								class="orderPageCheckOrderItems btn btn-primary btn-xs">查看详情</button>

							<c:if test="${o.status=='waitDelivery'}">
							<a href="order/deliveryOrder.do?id=${o.id}">
								<button oid=${o.id } class="btn btn-primary btn-xs delivery">发货</button></a>
							</c:if>
						</td>
					</tr>
					<tr class="orderPageOrderItemTR" oid=${o.id}>
						<td colspan="10" align="center">
							<div class="orderPageOrderItem">
								<table width="800px" align="center"
									class="orderPageOrderItemTable">
									<c:forEach items="${o.orderItems}" var="oi">
										<tr>
											<td><a href="foreproduct?product.id=${oi.product.id}">
													<span>${oi.product.name}</span>
											</a></td>
											<td align="right"><span class="text-muted">${oi.number}个</span>
											</td>
											<td align="right"><span class="text-muted">单价：￥${oi.product.promotePrice}</span>
											</td>

										</tr>
									</c:forEach>
								</table>
							</div>

						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<%
		String reqUri = "order/listOrder.do";
	%>
	<div class="pageDiv">
		<%@include file="../../include/admin/adminPage.jsp"%>
	</div>
</div>
<%@include file="../../include/admin/adminFooter.jsp"%>