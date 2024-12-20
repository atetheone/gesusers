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


@WebServlet("/add")
public class AddUser extends HttpServlet
{
	private static final String VUE_ADD_USER = "/WEB-INF/ajouterUtilisateur.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		getServletContext().getRequestDispatcher(VUE_ADD_USER).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		UserForm form = new UserForm(request);


		if (form.add()) {
			HttpSession session = request.getSession();
			session.setAttribute("statusMessage", form.getStatusMessage());
			session.setAttribute("status", form.getStatus());
			response.sendRedirect("list");
		} else {

			request.setAttribute("erreurs", form.getErreurs());
			request.setAttribute("statusMessage", form.getStatusMessage());
			request.setAttribute("status", form.getStatus());
			request.setAttribute("utilisateur", form.getUtilisateur());
			getServletContext().getRequestDispatcher(VUE_ADD_USER).forward(request, response);
		}
	}
}
