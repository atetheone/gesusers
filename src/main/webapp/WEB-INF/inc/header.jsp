<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8">	
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    <title>${requestScope.title != null ? requestScope.title : 'Accueil'} - Gestion Utilisateurs</title>
</head>
<body>
    <div class="header-top">
        <div class="container">
            <h1 class="site-title">Gestion des Utilisateurs</h1>
        </div>
    </div>
    <nav class="main-nav">
        <div class="container">
            <ul class="nav-menu">
                <li class="nav-item ${requestScope.currentPage == 'home' ? 'active' : ''}">
                    <a href="<c:url value='/' />">Accueil</a>
                </li>
                <li class="nav-item ${requestScope.currentPage == 'list' ? 'active' : ''}">
                    <a href="<c:url value='/list' />">Utilisateurs</a>
                </li>
                <li class="nav-item ${requestScope.currentPage == 'add' ? 'active' : ''}">
                    <a href="<c:url value='/add' />">Ajouter un utilisateur</a>
                </li>
            </ul>
        </div>
    </nav>
