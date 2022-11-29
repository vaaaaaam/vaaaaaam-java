<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Feedbacks" scope="application" />
<t:wrapper>
		<h1>Edit item</h1>


		<div class="row">
			<form class="col s12">
				<div class="row">
					<div class="input-field col s6">
						<input placeholder="Name" id="Service_name" type="text" class="validate"> <label for="Service_name">Service name</label>
					</div>
				</div>
				<div class="row">
					<div class="input-field col s12">
						<input placeholder="Price" id="Set_price" type="text" class="validate"><label for="Price">Set price</label>
					</div>
				</div>
				<div class="row">
					<div class="input-field col s12">
						<input id="password" type="password" class="validate"> <label for="password">Password</label>
					</div>
				</div>
				<div class="row">
					<div class="input-field col s12">
						<input id="email" type="email" class="validate"> <label for="email">Email</label>
					</div>
				</div>
				<div class="row">
					<div class="col s12">
						This is an inline input field:
						<div class="input-field inline">
							<input id="email_inline" type="email" class="validate"> <label for="email_inline">Email</label> <span class="helper-text" data-error="wrong"
								data-success="right">Helper text</span>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect red lighten-3" href="list.html"><i class="material-icons left">list</i>К списку</a>
				<a class="btn waves-effect teal lighten-3"	href="#"><i class="material-icons left">save</i>Сохранить</a>
			</div>
		</div>
	</div>
</div>
</t:wrapper>