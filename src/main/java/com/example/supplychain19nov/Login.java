package com.example.supplychain19nov;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;

public class Login {
    DatabaseConnection dbConn = new DatabaseConnection();


    public static byte[] getSHA(String input){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(input.getBytes((StandardCharsets.UTF_8)));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // Used SHA hash and SALTED hash to encrypt the password -- Read about this(to explain in interview)
    static String getEncryptedPassword(String password){
        String encryptedPassword = "";
        BigInteger number = new BigInteger(1, getSHA(password));
        StringBuilder hexString = new StringBuilder(number.toString(16));
        encryptedPassword = hexString.toString();
        return encryptedPassword;
    }

    public boolean customerLogin(String email, String password){
        try{
            String query = String.format("SELECT * FROM customer WHERE email = '%s' and password ='%s'",email,
                    getEncryptedPassword(password));
            ResultSet rs = dbConn.getQueryTable(query);
//            if(rs == null) return false;
            if(rs != null && rs.next()) return true;
            else return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


//    public static void main(String[] args) {
//        System.out.println(getEncryptedPassword("abc123"));
//        // 6ca13d52ca70c883e0f0bb101e425a89e8624de51db2d2392593af6a84118090
//    }


}
