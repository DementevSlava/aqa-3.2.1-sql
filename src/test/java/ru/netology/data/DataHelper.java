package ru.netology.data;

import lombok.*;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DataHelper {

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

    public static String getVerificationCode() throws SQLException {
        val authCodeSQL = "SELECT  code FROM auth_codes order by created desc limit 1;";
        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://192.168.99.100:3306/app", "app", "pass"
                );
                val countStmt = conn.createStatement();
        ) {
            try (val rs = countStmt.executeQuery(authCodeSQL)) {
                if (rs.next()) {
                    // выборка значения по индексу столбца (нумерация с 1)
                    val code = rs.getString("code");
                    return code;
                }
            }
        }
        return null;
    }

}
