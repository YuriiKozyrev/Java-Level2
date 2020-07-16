package Lesson_6.server;

import java.sql.*;

public class AuthService {

    private static Connection connection;
    private static Statement stmt;

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:userDB.db");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPass(String login, String pass) throws SQLException {
        String sql = String.format("SELECT nickname FROM main where " +
                "login = '%s' and password = '%s'", login, pass);
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public static void addToBlackList (String currentNick, String blockedNick) throws SQLException {
        String currentUserId = getIdByNick(currentNick);
        String blockedUserId = getIdByNick(blockedNick);
        String sql = String.format( "INSERT INTO blacklist (user_id, user_block_id)" +
                                    "VALUES ('%s', '%s')", currentUserId, blockedUserId);
        stmt.executeUpdate(sql);
    }

    public static void removeFromBlackList (String currentNick, String blockedNick) throws SQLException {
        String currentUserId = getIdByNick(currentNick);
        String blockedUserId = getIdByNick(blockedNick);
        String sql = String.format( "DELETE FROM blacklist WHERE user_id = '%s' AND  user_block_id = '%s'", currentUserId, blockedUserId);
        stmt.executeUpdate(sql);
    }

    public static boolean isNickInBlackList (String currentNick, String blockedNick) throws SQLException {
        String currentUserId = getIdByNick(currentNick);
        String blockedUserId = getIdByNick(blockedNick);
        String sql = String.format("SELECT * FROM blacklist WHERE user_id = '%s' AND user_block_id = '%s'", currentUserId, blockedUserId);
        ResultSet rs = stmt.executeQuery(sql);
        return rs.next();
    }

    public static String getIdByNick (String nick) throws SQLException {
        String sql = String.format("SELECT id FROM main where " +
                "nickname = '%s'", nick);
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
