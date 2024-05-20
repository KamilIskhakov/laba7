package Groupld.Server;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class PasswordEncoder {
    private PasswordEncoder() {
    }

    public static String encode(String password) {
        String hash = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD2");
            messageDigest.update(password.getBytes());
            byte[] digested = messageDigest.digest();
            hash = DatatypeConverter.printHexBinary(digested);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }
}
