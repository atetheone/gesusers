package servlets;

import java.io.IOException;

import beans.Utilisateur;
import dao.UtilisateurDao;
import forms.UserForm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/update")
public class UpdateUser extends HttpServlet {
	private static final String VUE_UPDATE_USER = "/WEB-INF/modifierUtilisateur.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			int userId = Integer.parseInt(request.getParameter("id"));
			Utilisateur user = UtilisateurDao.getUserById(userId);

			if (user != null) {
				request.setAttribute("utilisateur", user);
				request.getRequestDispatcher(VUE_UPDATE_USER).forward(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Utilisateur introuvable");
			}
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID utilisateur invalide");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserForm updateForm = new UserForm(request);
        
        if (updateForm.update()) {
			HttpSession session = request.getSession();
			session.setAttribute("statusMessage", updateForm.getStatusMessage());
			session.setAttribute("status", updateForm.getStatus());

            response.sendRedirect("list");
        } else {
            request.setAttribute("erreurs", updateForm.getErreurs());
            request.setAttribute("statusMessage", updateForm.getStatusMessage());
            request.setAttribute("utilisateur", updateForm.getUtilisateur());
            getServletContext().getRequestDispatcher(VUE_UPDATE_USER).forward(request, response);
        }

	}
}
