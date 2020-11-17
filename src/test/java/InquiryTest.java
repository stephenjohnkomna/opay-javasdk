import core.ConnectionClient;
import core.Util;
import core.module.Cashout;
import core.module.Inquiry;
import core.module.Transaction;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class InquiryTest {
    private Inquiry inquiry;
    private ConnectionClient connectionClient;
    private final String BASEURL ="http://sandbox.cashierapi.operapay.com/api/v3";
    private final String MERCHANTID ="256620072116000";
    private final String PUBLICKEY ="OPAYPUB15953464969740.9412274406196679";


    @Test
    public void Test_Query_Balance_All_Account_Success() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        connectionClient = new ConnectionClient(BASEURL, Util.getHeader(PUBLICKEY,MERCHANTID));
        inquiry = new Inquiry(connectionClient);

        JSONObject response = inquiry.balanceForAllAccount();
        assertEquals("SUCCESSFUL", response.get("message"));
    }


    @Test
    public void Test_Validate_Merchant_Successful() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        TreeMap<String, Object> param = new TreeMap<String, Object>();
        param.put("email","aagboola@opay-inc.com");

        connectionClient = new ConnectionClient(BASEURL, Util.getHeader(PUBLICKEY,MERCHANTID));
        inquiry = new Inquiry(connectionClient);

        JSONObject response = inquiry.validateMerchant(param);
        assertEquals("SUCCESSFUL", response.get("message"));
    }

    @Test
    public void Test_Validate_User_Successful() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        TreeMap<String, Object> param = new TreeMap<String, Object>();
        param.put("phoneNumber","+2349876543210");

        connectionClient = new ConnectionClient(BASEURL, Util.getHeader(PUBLICKEY,MERCHANTID));
        inquiry = new Inquiry(connectionClient);

        JSONObject response = inquiry.validateUser(param);
        assertEquals("SUCCESSFUL", response.get("message"));
    }


    @Test
    public void Test_Verify_AccountAndReturnAllocatedAccountName_Successful() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        TreeMap<String, Object> param = new TreeMap<String, Object>();
        param.put("bankCode","058");
        param.put("bankAccountNo","45345343434");
        param.put("countryCode","NG");

        connectionClient = new ConnectionClient(BASEURL, Util.getHeader(PUBLICKEY,MERCHANTID));
        inquiry = new Inquiry(connectionClient);

        JSONObject response = inquiry.verifyAccountAndReturnAllocatedAccountName(param);
        assertEquals("SUCCESSFUL", response.get("message"));
    }

  /*  @After
    public void TearDown ()
    {
        connectionClient.shutDown();
    }
*/
}
