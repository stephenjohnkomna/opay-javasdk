
import com.google.gson.JsonObject;
import core.ConnectionClient;
import core.Util;
import core.module.Cashout;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.TreeMap;

public class CashoutTest {
    private Cashout cashout;
    private ConnectionClient connectionClient;
    private static JSONObject transactionCheckStatusInput;
    private final String BASEURL ="http://sandbox.cashierapi.operapay.com/api/v3";

    private final String MERCHANTID ="256620112018031";
    private final String PUBLICKEY ="OPAYPUB16058777635980.9961229244591103";
    private final String PRIVATEKEY ="OPAYPRV16058777635980.3804652128291669";


    @Test
    public void Test_Initialize_Transaction_Successful_Message()
    {

        connectionClient = new ConnectionClient(BASEURL,
                Util.getHeader(PUBLICKEY,MERCHANTID));
        cashout = new Cashout(connectionClient);

        TreeMap<String, Object> param = new TreeMap<String, Object>();
        param.put("reference",Util.generateTransactionRefrenceNo());
        param.put("mchShortName","Jerry's shop");
        param.put("productName","Apple AirPods Pro");
        param.put("productDesc","The best wireless earphone in history");
        param.put("userPhone","+2349876543210");
        param.put("userRequestIp","123.123.123.123");
        param.put("amount","100");
        param.put("currency","NGN");
        param.put("payMethods", new String[]{"account", "qrcode","bankCard","bankAccount"});
        param.put("payTypes",new String [] {"BalancePayment", "BonusPayment","OWealth"});
        param.put("callbackUrl","https://you.domain.com/callbackUrl");
        param.put("returnUrl","https://you.domain.com/returnUrl");
        param.put("expireAt","10");

       JSONObject response = cashout.initializeTransaction(param);
       transactionCheckStatusInput = (JSONObject) response.get("data");
       assertEquals("SUCCESSFUL", response.get("message"));
    }



    @Test
    public void Test_Transaction_Check_Status_Successful() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {

        // Sorted in Alphabetic Order
        TreeMap<String, Object> param = new TreeMap<String, Object>();
        param.put("orderNo",transactionCheckStatusInput.get("orderNo"));
        param.put("reference",transactionCheckStatusInput.get("reference"));

        String paramString = Util.mapToJsonString(param);
        String signature = Util.calculateHMAC(paramString,PRIVATEKEY);

        connectionClient = new ConnectionClient(BASEURL,
                Util.getHeader(signature,MERCHANTID));
        cashout = new Cashout(connectionClient);

        JSONObject response = cashout.transactionStatus(param);
        assertEquals("SUCCESSFUL", response.get("message"));
    }


    @Test
    public void Test_Transaction_Close_Successful() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {

        // Sorted in Alphabetic Order
        TreeMap<String, Object> param = new TreeMap<String, Object>();
        param.put("orderNo",transactionCheckStatusInput.get("orderNo"));
        param.put("reference",transactionCheckStatusInput.get("reference"));

        String paramString = Util.mapToJsonString(param);
        String signature = Util.calculateHMAC(paramString,PRIVATEKEY);

        connectionClient = new ConnectionClient(BASEURL,
                Util.getHeader(signature,MERCHANTID));
        cashout = new Cashout(connectionClient);

        JSONObject response = cashout.closeTransaction(param);
        assertEquals("SUCCESSFUL", response.get("message"));
    }

/*    @After
    public void TearDown ()
    {
        connectionClient.shutDown();
    }*/

}
