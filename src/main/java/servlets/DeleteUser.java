package servlets;

import dao.UtilisateurDao;
import beans.Utilisateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/delete")
public class DeleteUser extends HttpServlet {
	private static final String VUE_LIST_USERS = "/WEB-INF/listerUtilisateur.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
            int userId = Integer.parseInt(request.getParameter("id"));

            boolean success = UtilisateurDao.deleteUser(userId);

            if (success) {
                // Rediriger vers la liste des utilisateurs après suppression
				HttpSession session = request.getSession();
				session.setAttribute("statusMessage", "Utilisateur supprimé avec succès !");
				session.setAttribute("status", true);
                response.sendRedirect("list");
            } else {
                // Erreur si l'utilisateur n'existe pas
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Utilisateur avec l'ID " + userId + " introuvable");
            }
        } catch (NumberFormatException e) {
            // Gérer les erreurs d'ID invalide
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID utilisateur invalide");
        }
    }
}
