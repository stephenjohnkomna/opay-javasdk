package core;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


/**
 * ConnectionClient Class.
 */
public class ConnectionClient {
    /**
     * baseUrl
     */
    private String baseUrl;


    /**
     * headers
     */
    private Map<String,String> headers= new HashMap<String,String>();

    /**
     * Constructor for Client Connection
     */
    public ConnectionClient(String baseUrl,HashMap<String,String> headers) {
        this.baseUrl = baseUrl;
        this.headers = headers;
    }


    /**
     * Post Request
     */
    public JSONObject makePostRequest(TreeMap<String, Object> parameters, String endpoint) {
        try {
            HttpResponse<JsonNode> result =
                    Unirest.post(this.baseUrl+endpoint)
                            .headers(headers)
                            .body(Util.mapToJsonString(parameters))
                            .asJson();
            return result.getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Post Request
     */
    public JSONObject makePostRequest(String endpoint) {
        try {
            HttpResponse<JsonNode> result =
                    Unirest.post(this.baseUrl+endpoint)
                            .headers(headers)
                            .asJson();
            return result.getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Called to shut down the background event loop
     */
    public void shutDown() {
        try {
            Unirest.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
