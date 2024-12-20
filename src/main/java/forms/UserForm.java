package forms;

import beans.Utilisateur;
import dao.UtilisateurDao;
import jakarta.servlet.http.HttpServletRequest;

public class UserForm extends GenericForm {
    protected static final String CHAMP_ID = "id";
    protected static final String CHAMP_NOM = "nom";
    protected static final String CHAMP_PRENOM = "prenom";
    protected static final String CHAMP_LOGIN = "login";
    protected static final String CHAMP_PASSWORD = "password";
    protected static final String CHAMP_CONFIRMATION = "confirmation";
    
    protected Utilisateur utilisateur;

    public UserForm(HttpServletRequest request) {
        super(request);
    }

    public boolean add() {
        initUtilisateur();
        validateUserFields();

        if (!hasErrors() && UtilisateurDao.addUser(utilisateur)) {
            setStatus(true, "Utilisateur ajouté avec succès.", "Échec de l'ajout de l'utilisateur.");
        } else {
            setStatus(false, null, "Échec de l'ajout de l'utilisateur.");
        }

        return getStatus();
    }

    public boolean update() {
        int id = Integer.parseInt(getParameter(CHAMP_ID));
        initUtilisateur();
        utilisateur.setId(id);
        validateUserFields();

        if (!hasErrors() && UtilisateurDao.updateUser(id, utilisateur)) {
            setStatus(true, "Utilisateur mis à jour avec succès.", "Échec de la mise à jour de l'utilisateur.");
        } else {
            setStatus(false, null, "Échec de la mise à jour de l'utilisateur.");
        }

        return getStatus();
    }

    protected void validateUserFields() {
        validateChamps(CHAMP_NOM, CHAMP_PRENOM, CHAMP_LOGIN, CHAMP_PASSWORD, CHAMP_CONFIRMATION);
        validatePassword();
    }

    protected void validatePassword() {
        hasSameContent(CHAMP_PASSWORD, CHAMP_CONFIRMATION, CHAMP_PASSWORD, PASSWORD_ERROR_MESSAGE);
    }

    protected void initUtilisateur() {
        String nom = getParameter(CHAMP_NOM);
        String prenom = getParameter(CHAMP_PRENOM);
        String login = getParameter(CHAMP_LOGIN);
        String password = getParameter(CHAMP_PASSWORD);
        
        utilisateur = new Utilisateur(nom, prenom, login, password);
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
}