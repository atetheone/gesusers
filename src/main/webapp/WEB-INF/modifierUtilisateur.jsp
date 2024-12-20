<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="currentPage" value="update" scope="request"/>
<c:set var="title" value="Modifier un utilisateur" scope="request"/>
<%@include file="inc/header.jsp" %>

<main class="container">
    <h2 class="text-center">${title}</h2>

    <c:if test="${!empty requestScope.statusMessage}">
        <div class="${requestScope.status ? 'success' : 'error'}">${requestScope.statusMessage}</div>
    </c:if>

    <div class="form-container">
        <form method="post">
            <div class="form-group">
                <label for="nom">Nom :</label>
                <input type="text" id="nom" name="nom" value="<c:out value='${utilisateur.nom}'/>">
                <c:if test="${!empty erreurs.nom}">
                    <span class="error">${erreurs.nom}</span>
                </c:if>
            </div>
            <div class="form-group">
                <label for="prenom">Prénom :</label>
                <input type="text" id="prenom" name="prenom" value="<c:out value='${utilisateur.prenom}'/>">
                <c:if test="${!empty erreurs.prenom}">
                    <span class="error">${erreurs.prenom}</span>
                </c:if>
            </div>
            <div class="form-group">
                <label for="login">Login :</label>
                <input type="text" id="login" name="login" value="<c:out value='${utilisateur.login}'/>">
                <c:if test="${!empty erreurs.login}">
                    <span class="error">${erreurs.login}</span>
                </c:if>
            </div>
            <div class="form-group">
                <label for="password">Mot de passe :</label>
                <input type="password" id="password" name="password" value="<c:out value='${utilisateur.password}'/>">
                <c:if test="${!empty erreurs.password}">
                    <span class="error">${erreurs.password}</span>
                </c:if>
            </div>
            <div class="form-group">
                <label for="password">Confirmer votre mot de passe :</label>
                <input type="password" id="confirmation" name="confirmation" value="<c:out value='${utilisateur.password}'/>">
                <c:if test="${!empty erreurs.confirmation}">
                    <span class="error">${erreurs.confirmation}</span>
                </c:if>
            </div>
            <button type="submit" class="button">Mettre à  jour</button>
        </form>
    </div>
</main>

<%@include file="inc/footer.jsp" %>
