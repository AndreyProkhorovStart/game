import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Locale;

public class GenerationHMAC {
    private final String HMAC_ALGO = "HmacSHA3-256";
    private final int COUNT_BYTES = 32;
    private byte[] bytesHMAC;
    private byte[] bytesKey;

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length);
        for (byte b : bytes) {
            sb.append(String.format("%x", b));
        }
        return sb.toString();
    }

    public String generationKey() {
        String key = "";
        try {
            SecureRandom randomSecureRandom = SecureRandom.getInstance("SHA1PRNG");
            bytesKey = new byte[COUNT_BYTES];
            randomSecureRandom.nextBytes(bytesKey);
            key = bytesToHex(bytesKey).toUpperCase(Locale.ROOT);
        }
        catch (NoSuchAlgorithmException | IllegalStateException ex){
            System.out.println(ex);
        }
        return key;
    }

    public String generationHMAC(String computerMove)  {
        String hmac = "";
        try {
            Mac signer = Mac.getInstance(HMAC_ALGO);
            SecretKeySpec keySpec = new SecretKeySpec(bytesKey, HMAC_ALGO);
            signer.init(keySpec);
            bytesHMAC = signer.doFinal(computerMove.getBytes("utf-8"));
            hmac = bytesToHex(bytesHMAC).toUpperCase(Locale.ROOT);

        }
        catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException ex){
            System.out.println(ex);
        }
        return hmac;
    }
}
