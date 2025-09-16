[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-17-blue)]()
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)]()
[![Contributions](https://img.shields.io/badge/contributions-welcome-orange)]()

# Opay Java SDK

**OpayJava-SDK** eliminates the complexity of integrating with **OPay’s** payment infrastructure.
It reduces development time by up to **70%**, enabling teams to prototype, test, and deploy secure payment flows quickly.
Built-in **security, idempotency, retries, and typed errors** lower barriers for fintech adoption—especially across emerging markets.

> Repository: [https://github.com/stephenjohnkomna/opay-javasdk](https://github.com/stephenjohnkomna/opay-javasdk)

---

## Table of Contents

* [Features](#features)
* [Installation](#installation)

  * [Maven](#maven)
  * [Gradle](#gradle)
  * [Manual (JAR)](#manual-jar)
* [Quick Start](#quick-start)
* [Modules & Endpoints](#modules--endpoints)
* [Security Best Practices](#security-best-practices)
* [Examples](#examples)
* [Utilities at a Glance](#utilities-at-a-glance)
* [Contributing](#contributing)
* [License](#license)

---

## Features

* ✅ **Secure auth** (HMAC signing, timestamp/nonce headers)
* ✅ **Idempotency + retry** helpers to avoid duplicate charges
* ✅ **Consistent API** across modules (Cashout, Inquiry, Transfer)
* ✅ **Typed errors & structured responses**
* ✅ **Ready-to-run examples & unit tests**

---

## Installation

> **Note:** Use **one** of the options below. If Maven Central isn’t live yet, use **Manual (JAR)** for now.

### Maven

```xml
<!-- Replace with your published coordinates once live on Central -->
<dependency>
  <groupId>io.github.stephenjohnkomna</groupId>
  <artifactId>opay-javasdk</artifactId>
  <version>0.1.0</version>
</dependency>
```

### Gradle

```gradle
dependencies {
  implementation "io.github.stephenjohnkomna:opay-javasdk:0.1.0"
}
```

### Manual (JAR)

1. Download the latest release from **Releases**.
2. IntelliJ IDEA → **File → Project Structure → Modules → Dependencies → + → JARs or Directories** → select the JAR.

---

## Quick Start

```java
// 1) Configure the connection
ConnectionClient connection = new ConnectionClient(
    System.getenv("OPAY_BASEURL"),
    Util.getHeader(System.getenv("OPAY_PUBLIC_KEY"), System.getenv("OPAY_MERCHANT_ID"))
);

// 2) Use a module (Cashout shown)
Cashout cashout = new Cashout(connection);

// 3) Build request (keys sorted alphabetically for signature correctness)
TreeMap<String, Object> params = new TreeMap<>();
params.put("reference", Util.generateTransactionRefrenceNo());
params.put("mchShortName", "Jerry's shop");
params.put("productName", "Apple AirPods Pro");
params.put("productDesc", "The best wireless earphone in history");
params.put("userPhone", "+2349876543210");
params.put("userRequestIp", "123.123.123.123");
params.put("amount", "100");
params.put("currency", "NGN");
params.put("payMethods", new String[]{"account", "qrcode"});
params.put("payTypes", new String[]{"BalancePayment", "BonusPayment"});
params.put("callbackUrl", "https://you.domain.com/callbackUrl");
params.put("returnUrl", "https://you.domain.com/returnUrl");
params.put("expireAt", "10");

// 4) Call the API
JSONObject response = cashout.initializeTransaction(params);

// 5) Always shutdown when done
connection.shutDown();
```

---

## Modules & Endpoints

### Cashout

* Initialize Transaction
* Check Transaction Status
* Close Transaction

### Inquiry

* Validate Merchant
* Validate User
* Validate Bank Account Number
* Query All Account Balances

### Transfer

* Transfer to Bank
* Transfer to Wallet
* Check Bank Transfer Status
* Check Wallet Transfer Status
* List Supported Banks
* List Supported Countries

---

## Security Best Practices

* **Never commit secrets** (PUBLIC/PRIVATE keys) to source control.
* Use environment variables or a secrets manager.
* **Sort request parameter keys** alphabetically before signing.
* **Shut down** the client after use to close background threads:

```java
connection.shutDown();
```

---

## Examples

### Check Transaction Status (Cashout)

```java
TreeMap<String, Object> params = new TreeMap<>();
params.put("orderNo", transactionCheckStatusInput.get("orderNo"));
params.put("reference", transactionCheckStatusInput.get("reference"));

String payload = Util.mapToJsonString(params);
String signature = Util.calculateHMAC(payload, System.getenv("OPAY_PRIVATE_KEY"));

ConnectionClient connection = new ConnectionClient(
    System.getenv("OPAY_BASEURL"),
    Util.getHeader(signature, System.getenv("OPAY_MERCHANT_ID"))
);
Cashout cashout = new Cashout(connection);

JSONObject response = cashout.transactionStatus(params);
```

### Signature Creation

```java
// Parameters must be alphabetically sorted before HMAC signing
TreeMap<String, Object> params = new TreeMap<>();
params.put("orderNo", transactionCheckStatusInput.get("orderNo"));
params.put("reference", transactionCheckStatusInput.get("reference"));

String payload = Util.mapToJsonString(params);
String signature = Util.calculateHMAC(payload, System.getenv("OPAY_PRIVATE_KEY"));
```

---

## Utilities at a Glance

### ConnectionClient

* `makePostRequest(...) : JSONObject`
* `shutDown() : void`

### Cashout

* `initializeTransaction(...) : JSONObject`
* `transactionStatus(...) : JSONObject`
* `closeTransaction(...) : JSONObject`

### Transaction

* `transferToBank(...) : JSONObject`
* `transferToWallet(...) : JSONObject`
* `checkBankTransferStatus(...) : JSONObject`
* `checkWalletTransferStatus(...) : JSONObject`
* `allSupportingBanks() : JSONObject`
* `allSupportingCountries() : JSONObject`

### Inquiry

* `balanceForAllAccount() : JSONObject`
* `validateMerchant(...) : JSONObject`
* `verifyAccountAndReturnAllocatedAccountName(...) : JSONObject`
* `validateUser(...) : JSONObject`

### Util

* `mapToJsonString(...) : String`
* `generateTransactionRefrenceNo() : String`
* `calculateHMAC(...) : String`
* `getHeader(...) : Map`

---

## Contributing

We welcome PRs and issues 🙌
See **CONTRIBUTING.md** for guidelines and open a ticket in **Issues** for bugs/feature requests.

---

## License

This project is licensed under the **MIT License** — see [LICENSE](LICENSE).
