<html>
	<body>
		<h1>Einloggen</h1>
		
		<div>
			<form method="post">
				<div style="float:left;">
					<label for="username">E-Mail-Adresse</label>
					<br>
					<label for="password">Passwort</label>
					<br>
					<input type="submit" value="Einloggen">
				</div>
				<div style="float:left;">
					<input type="text" name="username">
					<br>
					<input type="password" name="password">
				</div>
			</form>
		</div>
		<p>
		<br>
		<br>
		<br>
		<h1>Anmelden</h1>
		
		<div>
			<form method="post" action="registrieren?url=${url}">
				<div style="float:left">
					<label for="username">E-Mail-Adresse</label>
					<br>
					<label for="password1">Passwort</label>
					<br>
					<label for="password2">Passwort wiederholen</label>
					<br>
					<input type="submit" value="Registrieren">
				</div>
				<div style="float:left;">		
					<input type="text" name="username">
					<br>
					<input type="password" name="password1">
					<br>
					<input type="password" name="password2">
				</div>
			</form>
		</div>
	</body>
</html>