package core;

import com.google.gson.Gson;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.UUID;
import java.util.Map;

public class Util {
    private static final String HMAC_SHA512 = "HmacSHA512";

    public static String mapToJsonString(Map<String, Object> parameters)
    {
        Gson gson = new Gson();
        String json = gson.toJson(parameters);
        return json;
    }

    public static String generateTransactionRefrenceNo()
    {
        String value ="test_"+ UUID.randomUUID().toString().replace("-", "");
        return value;
    }


    private static String toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

    public static String calculateHMAC(String data, String key)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException
    {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), HMAC_SHA512);
        Mac mac = Mac.getInstance(HMAC_SHA512);
        mac.init(secretKeySpec);
        return toHexString(mac.doFinal(data.getBytes()));
    }

    public static  HashMap<String, String>  getHeader(String authorization,String merchantId)
    {
        HashMap<String, String> hearders = new HashMap<String, String> ();
        hearders.put("Accept","Application/json");
        hearders.put("Content-Type","Application/json");
        hearders.put("Authorization","Bearer "+authorization);
        hearders.put("MerchantId",merchantId);
        return hearders;
    }

}
