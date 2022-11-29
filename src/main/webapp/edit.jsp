<html>

<head>
<title>Edit item</title>

<!-- Compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>


<nav class="deep-purple lighten-3" role="navigation">
	<div class="nav-wrapper container">
		<a id="logo-container" href="#" class="brand-logo">Vaaam</a>
		<ul class="right hide-on-med-and-down">
			<li><a href="mainPage.html">Пользователи</a></li>
			<li><a href="index.html">Услуги</a></li>
			<li><a href="edit.html">Счета</a></li>
			<li><a href="feedbacks.html">Абоненты и услуги</a></li>
		</ul>
	</div>
</nav>


<div class="section no-pad-bot" id="index-banner">
	<div class="container">
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

<!-- Compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>