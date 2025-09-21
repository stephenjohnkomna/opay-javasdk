package core.module;

import core.ConnectionClient;
import core.Endpoint;
import org.json.JSONObject;
import java.util.TreeMap;

/**
 * Inquiry Class
 */
public class Inquiry {

    /**
     * Inquiry Class
     */
    private ConnectionClient connectionClient;

    /**
     * Inquiry Constructor
     */
    public Inquiry(ConnectionClient connectionClient)
    {
        this.connectionClient = connectionClient;
    }

    /**
     * balanceForAllAccount
     */
    public JSONObject balanceForAllAccount() {
        return this.connectionClient.makePostRequest(Endpoint.OPAY_INQUIRY_BALANCE_FOR_ALL_ACCOUNT);
    }

    /**
     * validateMerchant
     */
    public JSONObject validateMerchant(TreeMap<String, Object> param) {
        return this.connectionClient.makePostRequest(param,Endpoint.OPAY_INQUIRY_VALIDATE_MERCHANT);
    }

    /**
     * validateUser
     */
    public JSONObject validateUser(TreeMap<String, Object> param) {
        return this.connectionClient.makePostRequest(param,Endpoint.OPAY_INQUIRY_VALIDATE_USER);
    }

    /**
     * verifyAccountAndReturnAllocatedAccountName
     */
    public JSONObject verifyAccountAndReturnAllocatedAccountName(TreeMap<String, Object> param) {
        return this.connectionClient.makePostRequest(param,Endpoint.OPAY_INQUIRY_VERIFY_ACCOUNT_AND_RETURN_ALLOCATEDACCOUNTNAME);
    }
}
