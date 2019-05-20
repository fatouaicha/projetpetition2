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
		<h2><a href="/">Acceuil</a></h2>
		<h1>Signer une pétition</h1>

	
		<h2>Dérouler la liste déroulante pour choisir une pétition :</h2>
		<form action="/post" method="post">
	<select name="petitionSigne">
		<%
			List<Entity> petitions = (List<Entity>) request.getAttribute("petitions");
			for (Entity petition : petitions) {
		%>
		<option >
		<%= petition.getProperty("titre") %>
		<%
			}
		%>
	</option >
	</select>
	
	<select name="vote_soumis">
		
		<option >Signer</option > <br/>
		<option >Pas Signer</option >
	</select>
	<p>
		<input type="submit" value="Voter" />
	</p>
		</form>
	<h1>Liste des pétitions déjà Signées :</h1>
	<a href="/indextrois">CLiquer ici pour voir la liste de vos Pétitons Déjà Signées</a>
		
	</body>
