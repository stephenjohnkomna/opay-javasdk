

# OpayJava-Sdk

A Java API SDK that facilitates quick and easy development and integration of Java-based applications with the Opay Payment API.
OpayJava-SDK facilitates quick consummation of Opay Payment API and implements diverse Streams of helper methods to enable quick prototyping and testing. 

## Links
- Project: https://StephenJohn@bitbucket.org/StephenJohn/opay-javasdk


## Getting started
### Dependencies:
- 

### OpayJava-Sdk installation:
- Download OpayJava-Sdk
- Add jar file as a Module to your Java project:
- On Intellij IDEA: File -> Project Structure -> Modules -> Dependencies Tab -> Add -> JARs or Directories -> Attach jar

### Overview
The Connection to Opay server has been abstracted, all that is required is to specify some variables when creating the connection object, then you supply the instance
of the Connection to the Constructor of the Module when instantiating them.
Please, refer to the examples for clarity.
You can also refer to the Unit Test in the code base for an elaborate implementation of all the endpoints on each modules.

Please consult the OPAY PAYMEMT API Documentation for details on setting up an account, so as to get the required keys for this integration.

### Endpoint Modules
- Cashout : This Modules consist of endpoint to do the following;
  1.Initialize a Transaction 
  2.Check a Transaction Status 
  3.Close a Transaction

- Inquiry : This Modules consist of endpoint to do the Following;
  1.Validate Opay Merchant 
  2.Validate Opay User 
  3.Validate Bank Account Number
  4.Query Balance (requests for the balances of all your OPay accounts)

- Transfer: This Modules consist of endpoint to do the Following;
  1.Transfer to Bank
  2.Transfer to Wallet
  3.Check the Status of Transfer to Bank
  4.Check the Status of Transfer to Wallet
  5.Get All Supporting Banks (fetches a list of Banks that OPay currently supports for interbank transfers)
  6.Get All Supporting Countries (fetches a list of transfer countries that OPay currently supports for bank transfers)


## Examples

### To Initialize an OPAY Transaction (Cashout Module)
```java
     // Setup the Connection Object and the Module instance
     ConnectionClient connectionClient = new ConnectionClient(BASEURL,Util.getHeader(PUBLICKEY,MERCHANTID));
     Cashout cashout = new Cashout(connectionClient);

     // Construct the Request Payload
        TreeMap<String, Object> param = new TreeMap<String, Object>();
        param.put("reference",Util.generateTransactionRefrenceNo());
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

     // Make the Call and get a response
       JSONObject response = cashout.initializeTransaction(param);
```

### To Check a OPAY Transaction Status (Cashout Module)
```java
 // Setup the Connection Object and the Module instance
     ConnectionClient connectionClient = new ConnectionClient(BASEURL,Util.getHeader(PUBLICKEY,MERCHANTID));
     Cashout cashout = new Cashout(connectionClient);
	 
// Sorted in Alphabetic Order
        TreeMap<String, Object> param = new TreeMap<String, Object>();
        param.put("orderNo",transactionCheckStatusInput.get("orderNo"));
        param.put("reference",transactionCheckStatusInput.get("reference"));

        String paramString = Util.mapToJsonString(param);
        String signature = Util.calculateHMAC(paramString,PRIVATEKEY);

        connectionClient = new ConnectionClient(BASEURL,
                Util.getHeader(signature,MERCHANTID));
        cashout = new Cashout(connectionClient);

        JSONObject response = cashout.transactionStatus(param);
```

### Signature Creation
```java
// Remember that, the Parameters keys should be arranged in Alphabetic order,  signed with the Secret Key(PRIVATEKEY) and then hash in HMAC 512
        TreeMap<String, Object> param = new TreeMap<String, Object>();
        param.put("orderNo",transactionCheckStatusInput.get("orderNo"));
        param.put("reference",transactionCheckStatusInput.get("reference"));

        String paramString = Util.mapToJsonString(param);
        String signature = Util.calculateHMAC(paramString,PRIVATEKEY);
```



####  OpayJava-Sdk utilizes a background event loop and your Java application won't be able to exit until you manually shutdown all the threads by invoking:
**Remember to always shut down the API connection once you are done making requests**
```java
connectionClient.shutDown();
```

### Unit Test
```java
 You can the Unit test for sample implementation of all the endpoints.
```

## NOTE
```
  Ensure to keep your Secret key Safe. Please do not commit your secret with your code base.
```

## Utilities at a glance
### ConnectionClient [Class]:
#### methods:
- makePostRequest returns [JSONObject]
- shutDown [void]

### Cashout [Class]:
#### methods:
- initializeTransaction returns [JSONObject]
- transactionStatus returns [JSONObject]
- closeTransaction returns [JSONObject]

### Transaction [Class]:
#### methods:
- transferToBank returns [JSONObject]
- transferToWallet returns [JSONObject]
- checkBankTransferStatus returns [JSONObject]
- checkWalletTransferStatus returns [JSONObject]
- allSupportingBanks returns [JSONObject]
- allSupportingCountries returns [JSONObject]

### Inquiry [Class]:
#### methods:
- balanceForAllAccount returns [JSONObject]
- validateMerchant returns [JSONObject]
- verifyAccountAndReturnAllocatedAccountName returns [JSONObject]
- validateUser returns [JSONObject]

### Util [Class]:
#### methods:
- mapToJsonString returns [String]
- generateTransactionRefrenceNo returns [String]
- calculateHMAC returns [String]
- getHeader returns [Map]


### Endpoint {Class}:
#### methods:
- A list of all the endpoints on the Opay Payment API


License
-------

The MIT License (MIT)

Copyright (c) 2020 John Stephen Komna

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
