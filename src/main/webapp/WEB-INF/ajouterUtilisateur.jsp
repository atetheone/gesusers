<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="currentPage" value="add" scope="request"/>
<c:set var="title" value="Ajouter un utilisateur" scope="request"/>
<%@include file="inc/header.jsp" %>

<main class="container">
    <h2 class="text-center">${title}</h2>
    
    <c:if test="${!empty requestScope.statusMessage}">
        <div class="${requestScope.status ? 'success' : 'error'}">${requestScope.statusMessage}</div>
    </c:if>

    <div class="form-container">
        <form method="post">
            <div class="form-group">
                <label for="nom">Nom</label>
                <input type="text" name="nom" id="nom" value="${ utilisateur.nom }">
                <c:if test="${ !empty erreurs.nom }">
                    <span class="error">${ erreurs.nom }</span>
                </c:if>
            </div>
            
            <div class="form-group">
                <label for="prenom">Prénom</label>
                <input type="text" name="prenom" id="prenom" value="${ utilisateur.prenom }">
                <c:if test="${ !empty erreurs.prenom }">
                    <span class="error">${ erreurs.prenom }</span>
                </c:if>
            </div>
            
            <div class="form-group">
                <label for="login">Login</label>
                <input type="text" name="login" id="login" value="${ utilisateur.login }">
                <c:if test="${ !empty erreurs.login }">
                    <span class="error">${ erreurs.login }</span>
                </c:if>
            </div>
            
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" name="password" id="password" value="${ utilisateur.password}" class="form-control">
                <c:if test="${ !empty erreurs.password }">
                    <span class="error">${ erreurs.password }</span>
                </c:if>
            </div>
            
            <div class="form-group">
                <label for="confirmation">Confirmation</label>
                <input type="password" name="confirmation" id="confirmation" class="form-control">
                <c:if test="${ !empty erreurs.confirmation }">
                    <span class="error">${ erreurs.confirmation }</span>
                </c:if>
            </div>
            <button type="submit" class="button">Ajouter</button>
        </form>
    </div>

</main>
<%@include file="inc/footer.jsp" %>