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
		<h2><a href="/">Acceuil</a> <a href="/indexdeux">CLiquer ici pour Signer encore des Pétitons</a></h2>
		<h1>Vos pétitions Signées :</h1>
		<%
			List<Entity> petitions = (List<Entity>) request.getAttribute("petitionSigne");
			for (Entity petition : petitions) {
		%>
		<p>
			<strong><%= petition.getProperty("petitionSigne") %></strong> :

			<%= petition.getProperty("vote_soumis") %>
    
		</p>
		<%
			}
		%>



		
	</body>
</html>