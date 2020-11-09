package core;

public class Endpoint {
   /* These are definition of all the endpoints required
        for the opay integrations.*/

    //CHECKOUT ENDPOINTS
    private static String OPAY_CHECKOUT_INITIALIZE_TRANSACTION="/cashier/initialize";
    private static String OPAY_CHECKOUT_TRANSACTION_STATUS="/cashier/status";
    private static String OPAY_CHECKOUT_CLOSE_TRANSACTION="/cashier/status";

    //TRANSFER TO OPAY WALLET ENDPOINTS
    private static String OPAY_TRANSFER_TOWALLET="/transfer/toWallet";
    private static String OPAY_TRANSFER_STATUS_TOWALLET="/transfer/status/toWallet";

    //ENDPOINT FOR TRANSFER COUNTRIES THAT OPAY CURRENTLY SUPPORT
    private static String OPAY_SUPPORT_COUNTRIES="/countries";

    //ENDPOINT FOR BANKS THAT OPAY CURRENTLY SUPPORTS
    private static String OPAY_SUPPORT_BANKS="/banks";

    // ENDPOINT TO TRANSFER TO BANK
    private static String OPAY_TRANSFER_TOBANKS="/transfer/toBank";
    private static String OPAY_TRANSFER_STATUS_TOBANKS="/transfer/status/toBank";

  // ENDPOINT FOR THE BALANCES OF ALL YOUR OPAY ACCOUNTS
    private static String OPAY_BALANCE="/balance";

    // ENDPOINT TO VALIDATE OPAY USER
    private static String OPAY_VALIDATE_USER="/info/user";

    // ENDPOINT TO VALIDATE OPAY MERCHANT
    private static String OPAY_VALIDATE_MERCHANT="/info/merchant";


    //  verifies specified account number and returns allocated account name.
    private static String OPAY_VERIFY_ACCOUNT_AND_RETURN_ALLOCATEDACCOUNTNAME="/verification/accountNumber/resolve";
}