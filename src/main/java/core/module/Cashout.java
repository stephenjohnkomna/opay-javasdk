package core.module;

import core.ConnectionClient;
import core.Endpoint;
import core.Util;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Cashout {
    private ConnectionClient connectionClient;


    public Cashout(ConnectionClient connectionClient)
    {
        this.connectionClient = connectionClient;
    }

    public JSONObject initializeTransaction(HashMap<String, Object> param) {
        return this.connectionClient.makePostRequest(param,Endpoint.OPAY_CHECKOUT_INITIALIZE_TRANSACTION);
    }

    public JSONObject transactionStatus(HashMap<String, Object> param) {
        return this.connectionClient.makePostRequest(param,Endpoint.OPAY_CHECKOUT_TRANSACTION_STATUS);
    }

    public JSONObject closeTransaction(HashMap<String, Object> param) {
        return this.connectionClient.makePostRequest(param,Endpoint.OPAY_CHECKOUT_CLOSE_TRANSACTION);
    }

    public static void main(String args [])
    {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + "OPAYPUB15953464969740.9412274406196679");
        headers.put("MerchantID","256620072116000");

  /*      ConnectionClient connectionClient = new ConnectionClient("http://sandbox.cashierapi.operapay.com/api/v3",headers);

        Cashout cashout = new Cashout(connectionClient);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("reference", Util.generateTransactionRefrenceNo());
        param.put("mchShortName","Jerry's shop");
        param.put("productName","Apple AirPods Pro");
        param.put("productDesc","The best wireless earphone in history");
        param.put("userPhone","+2349876543210");
        param.put("userRequestIp","123.123.123.123");
        param.put("amount","100");
        param.put("currency","NGN");
        param.put("payMethods", new String[]{"account", "qrcode"});
        param.put("payTypes",new String [] {"BalancePayment", "BonusPayment"});
        param.put("callbackUrl","https://you.domain.com/callbackUrl");
        param.put("returnUrl","https://you.domain.com/returnUrl");
        param.put("expireAt","10");

        JSONObject json = cashout.initializeTransaction(param);
        JSONObject data = (JSONObject) json.get("data");
        System.out.println("JSON:>> "+data.get("orderNo"));//.get("Status"));*/
    }
}
