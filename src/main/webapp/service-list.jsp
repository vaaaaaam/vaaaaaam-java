<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="service-list" scope="application" />
<t:wrapper>
		<h1>Service</h1>
		<div class="row">
			<div class="col s12">
				<div class="center-align">
					<a class="btn-floating btn-large waves-effect  indigo lighten-2" href="/service?view=edit"><i class="material-icons">add</i></a>
				</div>
			</div>
		</div>
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Имя</th>
					<th>Стоимость</th>
					<th>Изменить</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="entity" items="${list}" varStatus="loopCounter">
				<tr>
					<td><c:out value="${entity.id}" /></td>
					<td><c:out value="${entity.name}" /></td>
					<td><c:out value="${entity.cost}" /></td>
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="редактировать" href="/service?view=edit&id=${entity.id}"><i
							class="material-icons">edit</i></a><a class="btn-small btn-floating waves-effect waves-light red" title="удалить"
						onclick="sendHTTPDelete('/service?id=${entity.id}')"><i class="material-icons">delete</i></a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>

	</div>
</div>
</t:wrapper>