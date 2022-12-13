<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="invoice-edit" scope="application" />
<t:wrapper>
    <c:choose>
		<c:when test="${empty dto.id}">
			<h1>Создать счет</h1>
		</c:when>
		<c:otherwise>
			<h1>Изменить счет #${dto.id}</h1>
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/invoice">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="row">
				<div class="input-field col s6">
					<input type="text" name="invoice" value="${dto.invoice}"> <label for="invoice">Остаток на счете</label>
				</div>
			</div>
			<div class="col s6">
				<label for="userId">User ID</label> 
				<select name="userId" class="browser-default" required>
					<option value="">--select user--</option>
					<c:forEach items="${allUsers}" var="user">
						<option value="${user.id}" <c:if test="${user.id eq dto.userId}">selected="selected"</c:if>>${user.name} ${user.surname}</option>
					</c:forEach>
				</select>
			</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/invoice"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
</t:wrapper>