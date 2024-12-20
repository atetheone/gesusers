package servlets;

import dao.UtilisateurDao;
import beans.Utilisateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/list")
public class ListUsers extends HttpServlet {
	private static final String VUE_LIST_USERS = "/WEB-INF/listerUtilisateur.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer la liste des utilisateurs via UtilisateurDao
        ArrayList<Utilisateur> utilisateurs;
		try {
			utilisateurs = UtilisateurDao.getUsers();
	        // Ajouter les utilisateurs en tant qu'attribut pour la vue
	        request.setAttribute("utilisateurs", utilisateurs);

	        // Rediriger vers la vue (users.jsp)
	        getServletContext().getRequestDispatcher(VUE_LIST_USERS).forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }
}
