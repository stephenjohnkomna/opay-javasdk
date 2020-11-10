package core.module;

import core.ConnectionClient;
import core.Endpoint;
import org.json.JSONObject;

import java.util.HashMap;

public class Inquiry {

    private ConnectionClient connectionClient;

    public Inquiry(ConnectionClient connectionClient)
    {
        this.connectionClient = connectionClient;
    }

    public JSONObject balanceForAllAccount(HashMap<String, Object> param) {
        return this.connectionClient.makePostRequest(param, Endpoint.OPAY_INQUIRY_BALANCE_FOR_ALL_ACCOUNT);
    }

    public JSONObject allSupportingBanks(HashMap<String, Object> param) {
        return this.connectionClient.makePostRequest(param,Endpoint.OPAY_INQUIRY_SUPPORT_BANKS);
    }

    public JSONObject allSupportingCounttries(HashMap<String, Object> param) {
        return this.connectionClient.makePostRequest(param,Endpoint.OPAY_INQUIRY_SUPPORT_COUNTRIES);
    }

    public JSONObject validateMerchant(HashMap<String, Object> param) {
        return this.connectionClient.makePostRequest(param,Endpoint.OPAY_INQUIRY_VALIDATE_MERCHANT);
    }

    public JSONObject validateUser(HashMap<String, Object> param) {
        return this.connectionClient.makePostRequest(param,Endpoint.OPAY_INQUIRY_VALIDATE_USER);
    }

    public JSONObject verifyAccountAndReturnAllocatedAccountName(HashMap<String, Object> param) {
        return this.connectionClient.makePostRequest(param,Endpoint.OPAY_INQUIRY_VERIFY_ACCOUNT_AND_RETURN_ALLOCATEDACCOUNTNAME);
    }
}
