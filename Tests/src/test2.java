import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


class test2 {
    public static void main(String args[])
            throws SQLException, ClassNotFoundException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        int attemptedCorrect = 1, attemptedWrong = 0;

        int correctOptions = 2;
        int wrongOptions = 1;

        double correctOptionWeightage = 1.0/ correctOptions;
        double wrongOptionWeightage = 1.0 / wrongOptions;

        System.out.println(correctOptionWeightage);
        System.out.println(wrongOptionWeightage);
        System.out.println( attemptedCorrect* correctOptionWeightage - attemptedWrong * wrongOptionWeightage);

//        SimpleDateFormat dfoutput = new SimpleDateFormat("dd/MM/yyyy");

//        System.out.println(new DateTime("2022-09-16T23:03:30.631Z"));



//        String base64Cipher = "Mu9GpZM6J2jnuTX2vt0gzA==";
//        byte [] cipherBytes = Base64.decodeBase64(base64Cipher);
//        byte [] iv = "B3M0B1D1t0GMZon3".getBytes();
//        byte [] keyBytes = "D1ToDpZcGAMEZONE".getBytes();
//
//        SecretKey aesKey = new SecretKeySpec(keyBytes, "AES");
//
//        cipher = Cipher.getInstance("AES/CBC/NOPADDING");
//        cipher.init(Cipher.DECRYPT_MODE, aesKey, new IvParameterSpec(iv));
//
//        byte[] result = cipher.doFinal(cipherBytes);
//        System.out.println(Hex.encodeHexString(result).trim());

//        System.out.println(getDecryptedMsisdn("DZpBTWDCGddrfcK/E/LjjA=="));
        String msisdn = "454351651515616";
        if(!msisdn.startsWith("63"))
        {
            msisdn="63"+msisdn;
        }

        System.out.println(msisdn);
    }

    public static String getDecryptedMsisdn(String encryptedMsisdn) {
        byte[] IV = "B3M0B1D1t0GMZon3".getBytes();
        byte[] message = Base64.decodeBase64(encryptedMsisdn);

        String msisdn = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            final SecretKeySpec keySpec = new SecretKeySpec("D1ToDpZcGAMEZONE".getBytes(StandardCharsets.UTF_8), "AES");
            final IvParameterSpec ivSpec = new IvParameterSpec(IV);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            msisdn = new String(cipher.doFinal(message)).trim();
        } catch (Exception e) {
//            log.info("Error Decrypting msisdn");
        }

        return msisdn;
    }
}
