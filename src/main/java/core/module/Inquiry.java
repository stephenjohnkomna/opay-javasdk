package core.module;

import core.ConnectionClient;
import core.Endpoint;
import org.json.JSONObject;
import java.util.TreeMap;

public class Inquiry {

    private ConnectionClient connectionClient;

    public Inquiry(ConnectionClient connectionClient)
    {
        this.connectionClient = connectionClient;
    }

    public JSONObject balanceForAllAccount() {
        return this.connectionClient.makePostRequest(Endpoint.OPAY_INQUIRY_BALANCE_FOR_ALL_ACCOUNT);
    }

    public JSONObject validateMerchant(TreeMap<String, Object> param) {
        return this.connectionClient.makePostRequest(param,Endpoint.OPAY_INQUIRY_VALIDATE_MERCHANT);
    }

    public JSONObject validateUser(TreeMap<String, Object> param) {
        return this.connectionClient.makePostRequest(param,Endpoint.OPAY_INQUIRY_VALIDATE_USER);
    }

    public JSONObject verifyAccountAndReturnAllocatedAccountName(TreeMap<String, Object> param) {
        return this.connectionClient.makePostRequest(param,Endpoint.OPAY_INQUIRY_VERIFY_ACCOUNT_AND_RETURN_ALLOCATEDACCOUNTNAME);
    }
}
