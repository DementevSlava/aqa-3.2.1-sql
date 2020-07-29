package ru.netology.data;

import lombok.Value;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DataHelper {
    private static String url = "jdbc:mysql://192.168.99.100:3306/app";
    private static String user = "app";
    private static String password = "pass";

    private DataHelper() {

    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getInvalidLoginAuthInfo() {
        return new AuthInfo("vova", "qwerty123");
    }

    public static AuthInfo getInvalidPasswordAuthInfo() {
        return new AuthInfo("vasya", "12345");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }


    public static String getVerificationCode() {
        val authCodeSQL = "SELECT  code FROM auth_codes order by created desc limit 1;";
        try (
                val conn = DriverManager.getConnection(url, user, password);
                val countStmt = conn.createStatement();
        ) {
            try (val rs = countStmt.executeQuery(authCodeSQL)) {
                if (rs.next()) {
                    // выборка значения по индексу столбца (нумерация с 1)
                    val code = rs.getString("code");
                    return code;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void cleanDataBase() {
        val cleanCards = "DELETE FROM cards";
        val cleanAuthCodes = "DELETE FROM auth_codes";
        val cleanUser = "DELETE FROM users";
        val runner = new QueryRunner();

        try (
                val conn = DriverManager.getConnection(url, user, password)
        ) {
            runner.update(conn, cleanAuthCodes);
            runner.update(conn, cleanCards);
            runner.update(conn, cleanUser);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
