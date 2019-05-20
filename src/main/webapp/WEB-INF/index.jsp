<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.datastore.*" %>
<%@ page import="java.util.List" %>


<!DOCTYPE html>

<html>
	<head>
		<title>Meilleur Site de Pétitions</title>
		<meta charset="utf-8" />
		<style type="text/css">
		body {background-color: white;} 
		h1 {background-color: #d86d86;}
		</style> 
	</head>

	<body>
		<h1>Créer votre pétition</h1>
		<form action="/post" method="post">
			<p>
				<label>Votre pétition : <input type="text" name="titre" /></label>
			</p>
			<p>
				<label>Description de la pétition :

				<textarea name="description" style="width: 200px; height: 100px;"></textarea></label>
			</p>
			<p>
				<label>Nombre de Votes Nécessaires pour la  pétition : <input type="text" name="votes_necessaire" /></label>
			</p>
			<p>
				<input type="submit" name="btn_petition" value="créer Pétition" onclick="window.location.reload(false)" />
			    <input type="submit" value="Rafraichir la page pour bien afficher les petitions créées" onclick="window.location.reload(false)" />
			</p>
		</form>
	
		<h1>Vos pétitions :</h1>
		<%
			List<Entity> petitions = (List<Entity>) request.getAttribute("petitions");
			for (Entity petition : petitions) {
		%>
		<p>
			<strong><%= petition.getProperty("titre") %></strong> :

			<%= petition.getProperty("description") %>
    
		</p>
		<%
			}
		%>
	<h1>Cliquez sur ce lien pour signer une pétition : </h1>
		
		<a href="/indexdeux">CLiquer ici pour voter pour une pétition</a>

<h1>Liste des pétitions déjà Signées :</h1>
	<a href="/indextrois">CLiquer ici pour voir la liste de vos Pétitons Déjà Signées</a>
		
	</body>
</html>