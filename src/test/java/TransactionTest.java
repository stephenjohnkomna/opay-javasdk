import core.ConnectionClient;
import core.Util;
import core.module.Cashout;
import core.module.Transaction;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.TreeMap;
import static org.junit.Assert.assertEquals;

public class TransactionTest {
    private Transaction transaction;
    private ConnectionClient connectionClient;
    private static JSONObject bankTransferStatusInput;
    private static JSONObject walletTransferUserStatusInput;
    private static JSONObject walletTransferMerchantStatusInput;

    private final String BASEURL ="http://sandbox.cashierapi.operapay.com/api/v3";
    private final String MERCHANTID ="256620112018031";
    private final String PUBLICKEY ="OPAYPUB16058777635980.9961229244591103";
    private final String PRIVATEKEY ="OPAYPRV16058777635980.3804652128291669";


    @Test
    public void Test_Transfer_To_Bank_Successful() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        TreeMap<String, Object> receiver = new TreeMap<String, Object>();
        receiver.put("bankAccountNumber","22222222222222");
        receiver.put("bankCode","058");
        receiver.put("name","test_20191123132233");

        TreeMap<String, Object> param = new TreeMap<String, Object>();
        param.put("amount","100");
        param.put("country","NG");
        param.put("currency","NGN");
        param.put("reason","transfer reason message");
        param.put("receiver",receiver);
        param.put("reference",Util.generateTransactionRefrenceNo());

        String paramString = Util.mapToJsonString(param);
        String signature = Util.calculateHMAC(paramString,PRIVATEKEY);

        connectionClient = new ConnectionClient(BASEURL, Util.getHeader(signature,MERCHANTID));
        transaction = new Transaction(connectionClient);

        JSONObject response = transaction.transferToBank(param);
        bankTransferStatusInput = (JSONObject) response.get("data");
        assertEquals("SUCCESSFUL", response.get("message"));
    }


    @Test
    public void Test_Bank_Transfer_Status_Successful() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        TreeMap<String, Object> param = new TreeMap<String, Object>();
        param.put("orderNo",bankTransferStatusInput.get("orderNo"));
        param.put("reference",bankTransferStatusInput.get("reference"));

        String paramString = Util.mapToJsonString(param);
        String signature = Util.calculateHMAC(paramString,PRIVATEKEY);

        connectionClient = new ConnectionClient(BASEURL,
                Util.getHeader(signature,MERCHANTID));
        transaction = new Transaction(connectionClient);

        JSONObject response = transaction.checkBankTransferStatus(param);
        assertEquals("SUCCESSFUL", response.get("message"));
    }



    @Test
    public void Test_Transfer_To_User_Wallet_Successful() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        TreeMap<String, Object> receiver = new TreeMap<String, Object>();
        receiver.put("name","Adny Lee");
        receiver.put("phoneNumber","+2348131393827");
        receiver.put("type","USER");


        TreeMap<String, Object> param = new TreeMap<String, Object>();
        param.put("amount","100");
        param.put("country","NG");
        param.put("currency","NGN");
        param.put("reason","transfer reason message");
        param.put("receiver",receiver);
        param.put("reference",Util.generateTransactionRefrenceNo());

        String paramString = Util.mapToJsonString(param);
        String signature = Util.calculateHMAC(paramString,PRIVATEKEY);

        connectionClient = new ConnectionClient(BASEURL, Util.getHeader(signature,MERCHANTID));
        transaction = new Transaction(connectionClient);

        JSONObject response = transaction.transferToWallet(param);
        walletTransferUserStatusInput =(JSONObject) response.get("data");
        assertEquals("SUCCESSFUL", response.get("message"));
    }

    @Test
    public void Test_Query_Transfer_To_User_Wallet_Status_Successful() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        // Sorted in Alphabetic Order
        TreeMap<String, Object> param = new TreeMap<String, Object>();
        param.put("orderNo",walletTransferUserStatusInput.get("orderNo"));
        param.put("reference",walletTransferUserStatusInput.get("reference"));

        String paramString = Util.mapToJsonString(param);
        String signature = Util.calculateHMAC(paramString,PRIVATEKEY);

        connectionClient = new ConnectionClient(BASEURL,
                Util.getHeader(signature,MERCHANTID));
        transaction = new Transaction(connectionClient);

        JSONObject response = transaction.checkWalletTransferStatus(param);
        assertEquals("SUCCESSFUL", response.get("message"));
    }




    @Test
    public void Test_Transfer_To_Merchant_Wallet_Successful() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        TreeMap<String, Object> receiver = new TreeMap<String, Object>();
        receiver.put("name","Andy Lee");
        receiver.put("type","MERCHANT");
        receiver.put("merchantId","256620111818011");
      /*  receiver.put("name","Stephen John");
        receiver.put("merchantId","256620111650021");
        receiver.put("type","MERCHANT");*/


        TreeMap<String, Object> param = new TreeMap<String, Object>();
        param.put("amount","100");
        param.put("country","NG");
        param.put("currency","NGN");
        param.put("reason","transfer reason message");
        param.put("receiver",receiver);
        param.put("reference",Util.generateTransactionRefrenceNo());

        String paramString = Util.mapToJsonString(param);
        String signature = Util.calculateHMAC(paramString,PRIVATEKEY);

        connectionClient = new ConnectionClient(BASEURL, Util.getHeader(signature,MERCHANTID));
        transaction = new Transaction(connectionClient);

        JSONObject response = transaction.transferToWallet(param);
        walletTransferMerchantStatusInput =response;
        assertEquals("SUCCESSFUL", response.get("message"));
    }

    @Test
    public void Test_Query_Transfer_To_Merchant_Wallet_Status_Successful() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        TreeMap<String, Object> param = new TreeMap<String, Object>();
        param.put("orderNo",walletTransferMerchantStatusInput.get("orderNo"));
        param.put("reference",walletTransferMerchantStatusInput.get("reference"));

        String paramString = Util.mapToJsonString(param);
        String signature = Util.calculateHMAC(paramString,PRIVATEKEY);

        connectionClient = new ConnectionClient(BASEURL,
                Util.getHeader(signature,MERCHANTID));
        transaction = new Transaction(connectionClient);

        JSONObject response = transaction.checkWalletTransferStatus(param);
        assertEquals("SUCCESSFUL", response.get("message"));
    }

    @Test
    public void Test_Get_All_Supporting_Banks() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        TreeMap<String, Object> param = new TreeMap<String, Object>();
        param.put("countryCode","NG");

        connectionClient = new ConnectionClient(BASEURL, Util.getHeader(PUBLICKEY,MERCHANTID));
        transaction = new Transaction(connectionClient);

        JSONObject response = transaction.allSupportingBanks(param);
        assertEquals("SUCCESSFUL", response.get("message"));
    }


    @Test
    public void Test_Get_All_Supporting_Countries() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        connectionClient = new ConnectionClient(BASEURL, Util.getHeader(PUBLICKEY,MERCHANTID));
        transaction = new Transaction(connectionClient);

        JSONObject response = transaction.allSupportingCountries();
        assertEquals("SUCCESSFUL", response.get("message"));
    }

/*    @After
    public void TearDown ()
    {
        connectionClient.shutDown();
    }*/
}
