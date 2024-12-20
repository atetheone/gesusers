package forms;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;

public abstract class GenericForm {
    protected static final String EMPTY_ERROR_MESSAGE = "Veuillez remplir ce champ.";
    protected static final String PASSWORD_ERROR_MESSAGE = "Les mots de passe ne correspondent pas.";
    
    protected HashMap<String, String> erreurs;
    protected String statusMessage;
    protected boolean status;
    protected HttpServletRequest request;

    public GenericForm(HttpServletRequest request) {
        this.request = request;
        this.erreurs = new HashMap<>();
    }

    protected String getParameter(String parameter) {
        String value = this.request.getParameter(parameter);
        return (value == null || value.trim().isEmpty()) ? null : value.trim();
    }

    protected void validateChamps(String... champs) {
        for (String champ : champs) {
            String value = getParameter(champ);
            if (value == null) {
                erreurs.put(champ, EMPTY_ERROR_MESSAGE);
            }
        }
    }

    protected boolean hasSameContent(String field1, String field2, String errorField, String errorMessage) {
        String value1 = getParameter(field1);
        String value2 = getParameter(field2);

        if (value1 != null && !value1.equals(value2)) {
            erreurs.put(errorField, errorMessage);
            return false;
        }
        return true;
    }

    public HashMap<String, String> getErreurs() {
        return erreurs;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public boolean getStatus() {
        return status;
    }

    protected void setStatus(boolean status, String successMessage, String errorMessage) {
        this.status = status;
        this.statusMessage = status ? successMessage : errorMessage;
    }

    protected boolean hasErrors() {
        return !erreurs.isEmpty();
    }
}