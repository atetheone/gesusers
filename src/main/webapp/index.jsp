<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="currentPage" value="home" scope="request"/>
<c:set var="title" value="Accueil" scope="request"/>
<%@include file="WEB-INF/inc/header.jsp" %>

<main class="main-content">
    <section>
        <h2>Bienvenue sur l'application de gestion des utilisateurs</h2>
        <p>Cette application vous permet de gérer les utilisateurs de votre application.</p>
    </section>
</main>
<%@include file="WEB-INF/inc/footer.jsp" %>