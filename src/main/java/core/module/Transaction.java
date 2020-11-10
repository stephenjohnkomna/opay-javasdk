package core.module;

import core.ConnectionClient;
import core.Endpoint;
import org.json.JSONObject;

import java.util.HashMap;

public class Transaction {
    private ConnectionClient connectionClient;

    public Transaction(ConnectionClient connectionClient)
    {
        this.connectionClient = connectionClient;
    }

    public JSONObject transferToBank(HashMap<String, Object> param) {
        return this.connectionClient.makePostRequest(param,Endpoint.OPAY_TRANSFER_TOBANKS);
    }

    public JSONObject transferToWallet(HashMap<String, Object> param) {
        return this.connectionClient.makePostRequest(param,Endpoint.OPAY_TRANSFER_TOWALLET);
    }

    public JSONObject transferStatusToBank(HashMap<String, Object> param) {
        return this.connectionClient.makePostRequest(param,Endpoint.OPAY_TRANSFER_STATUS_TOBANKS);
    }

    public JSONObject transferStatusToWallet(HashMap<String, Object> param) {
        return this.connectionClient.makePostRequest(param,Endpoint.OPAY_TRANSFER_STATUS_TOWALLET);
    }

}
