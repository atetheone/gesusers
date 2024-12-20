package dao;

import beans.Utilisateur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class UtilisateurDao {
    private static int lastId = 1; 
    private static ArrayList<Utilisateur> utilisateurs;

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOM = "nom";
    private static final String COLUMN_PRENOM = "prenom";
    private static final String COLUMN_LOGIN = "login";
    private static final String COLUMN_PASSWORD = "password";

    private static final String SELECT_ALL_USERS_QUERY = "SELECT * FROM utilisateurs";
    private static final String ADD_USER_QUERY = "INSERT INTO utilisateurs (" 
        + COLUMN_NOM + ", " 
        + COLUMN_PRENOM 
        + ", " + COLUMN_LOGIN + ", " 
        + COLUMN_PASSWORD 
        + ") VALUES (?, ?, ?, ?)";
    private static final String UPDATE_USER_QUERY = "UPDATE utilisateurs SET " 
        + COLUMN_NOM + " = ?, " 
        + COLUMN_PRENOM + " = ?, " 
        + COLUMN_LOGIN + " = ?, " 
        + COLUMN_PASSWORD + " = ? WHERE id = ?";
    private static final String DELETE_USER_QUERY = "DELETE FROM utilisateurs WHERE id = ?";
    private static final String SELECT_USER_BY_ID_QUERY = "SELECT * FROM utilisateurs WHERE id = ?";




    // Ajouter un utilisateur
    public static boolean addUser(Utilisateur utilisateur) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            connection = ConnectionManager.getConnection();
            pstmt = connection.prepareStatement(ADD_USER_QUERY);

            pstmt.setString(1, utilisateur.getNom());
            pstmt.setString(2, utilisateur.getPrenom());
            pstmt.setString(3, utilisateur.getLogin());
            pstmt.setString(4, utilisateur.getPassword());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // Récupérer tous les utilisateurs
    public static ArrayList<Utilisateur> getUsers() throws SQLException{
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        ArrayList<Utilisateur> getUtilisateurs = new ArrayList<>();
        
        try {
        	connection = ConnectionManager.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(SELECT_ALL_USERS_QUERY);

            int id;
            String nom, prenom, login, password;

            while (rs.next()) {
                id = rs.getInt(COLUMN_ID);
                nom = rs.getString(COLUMN_NOM);
                prenom = rs.getString(COLUMN_PRENOM);
                login = rs.getString(COLUMN_LOGIN);
                password = rs.getString(COLUMN_PASSWORD);

                getUtilisateurs.add(new Utilisateur(id, nom, prenom, login, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
            if (rs != null) rs.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
            } catch (SQLException e) {
            e.printStackTrace();
            }
        }
		return getUtilisateurs;
	}

    // Supprimer un utilisateur par ID
    public static boolean deleteUser(int id) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            connection = ConnectionManager.getConnection();
            pstmt = connection.prepareStatement(DELETE_USER_QUERY);
            pstmt.setInt(1, id);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
            
    // Récupérer un utilisateur par ID
    public static Utilisateur getUserById(int id) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            connection = ConnectionManager.getConnection();
            pstmt = connection.prepareStatement(SELECT_USER_BY_ID_QUERY);
            pstmt.setInt(1, id);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Utilisateur(
                    rs.getInt(COLUMN_ID),
                    rs.getString(COLUMN_NOM),
                    rs.getString(COLUMN_PRENOM),
                    rs.getString(COLUMN_LOGIN),
                    rs.getString(COLUMN_PASSWORD)
                );
            }
            return null;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    // Mettre à jour un utilisateur par ID
    public static boolean updateUser(int id, Utilisateur user) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            connection = ConnectionManager.getConnection();
            pstmt = connection.prepareStatement(UPDATE_USER_QUERY);
            
            pstmt.setString(1, user.getNom());
            pstmt.setString(2, user.getPrenom());
            pstmt.setString(3, user.getLogin());
            pstmt.setString(4, user.getPassword());
            pstmt.setInt(5, id);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
