<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="user-edit" scope="application" />
<t:wrapper>
    <c:choose>
		<c:when test="${empty dto.id}">
			<h1>Создать Пользователя</h1>
		</c:when>
		<c:otherwise>
			<h1>Изменить пользователя #${dto.id}</h1>
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/user">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="row">
				<div class="input-field col s6">
					<input type="text" name="name" value="${dto.name}"> <label for="name">Имя</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s6">
					<input type="text" name="surname" value="${dto.surname}"> <label for="surname">Фамилия</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s6">
					<input type="text" name="date" value="${dto.birthDate}"> <label for="date">Дата рождения (в формате YYYY-MM-DD)</label>
				</div>
			</div>
			<div class="input-field col s6">
				<label><input type="checkbox" name="role" ${dto.role ? 'checked' : ''} value="true" /> <span>Администратор</span>
				</label>
			</div>
		</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/user"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
</t:wrapper>