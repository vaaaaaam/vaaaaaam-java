<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="index" scope="application" />
<t:wrapper>
		<h1>Services</h1>
		<div class="row">
			<div class="col s12">
				<div class="center-align">
					<a class="btn-floating btn-large waves-effect  indigo lighten-2" href="edit.jsp"><i class="material-icons">add</i></a>
				</div>
			</div>
		</div>
		<table>
			<thead>
				<tr>
					<th>Service name</th>
					<th>Service cost</th>
					<th>Edit</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Internet(200/100 Мбит/с)</td>
					<td>48,00 BYN</td>
					<th><a class="btn-floating btn-small waves-effect  indigo lighten-2" href="edit.jsp"><i class="material-icons">edit</i></a></th>
				</tr>
				<tr>
					<td>Internet(100/50 Мбит/с и нелимитируемый доступ Wi-Fi)</td>
					<td>50,00 BYN</td>
					<th><a class="btn-floating btn-small waves-effect  indigo lighten-2" href="edit.jsp"><i class="material-icons">edit</i></a></th>
				</tr>
				<tr>
					<td>Телефонная связь</td>
					<td>41,90 BYN</td>
					<th><a class="btn-floating btn-small waves-effect  indigo lighten-2" href="edit.jsp"><i class="material-icons">edit</i></a></th>
				</tr>
				<tr>
					<td>Умный дом</td>
					<td>46,00 BYN</td>
					<th><a class="btn-floating btn-small waves-effect  indigo lighten-2" href="edit.jsp"><i class="material-icons">edit</i></a></th>
				</tr>
				<tr>
					<td>Видеоконтроль</td>
					<td>41,50 BYN</td>
					<th><a class="btn-floating btn-small waves-effect  indigo lighten-2" href="edit.jsp"><i class="material-icons">edit</i></a></th>
				</tr>
			</tbody>
		</table>

	</div>
</div>
</t:wrapper>