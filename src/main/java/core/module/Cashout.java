package core.module;

import core.ConnectionClient;
import core.Endpoint;
import org.json.JSONObject;
import java.util.Map;
import java.util.TreeMap;


public class Cashout {
    private ConnectionClient connectionClient;


    public Cashout(ConnectionClient connectionClient)
    {
        this.connectionClient = connectionClient;
    }

    public JSONObject initializeTransaction(TreeMap<String, Object> param) {
        return this.connectionClient.makePostRequest(param,Endpoint.OPAY_CHECKOUT_INITIALIZE_TRANSACTION);
    }

    public JSONObject transactionStatus(TreeMap<String, Object> param) {
        return this.connectionClient.makePostRequest(param,Endpoint.OPAY_CHECKOUT_TRANSACTION_STATUS);
    }

    public JSONObject closeTransaction(TreeMap<String, Object> param) {
        return this.connectionClient.makePostRequest(param,Endpoint.OPAY_CHECKOUT_CLOSE_TRANSACTION);
    }
}
