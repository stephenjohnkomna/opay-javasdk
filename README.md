[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-17-blue)]()
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)]()
[![Contributions](https://img.shields.io/badge/contributions-welcome-orange)]()

# Opay Java SDK

**OpayJava-SDK** eliminates the complexity of integrating with **OPay’s** payment infrastructure.  
It reduces development time by up to **70%**, enabling teams to prototype, test, and deploy secure payment flows quickly.  
Built-in **security, idempotency, retries, and typed errors** lower barriers for fintech adoption—especially across emerging markets.

> Repository: https://github.com/stephenjohnkomna/opay-javasdk

---

## Table of Contents
- [Features](#features)
- [Installation](#installation)
  - [Maven](#maven)
  - [Gradle](#gradle)
  - [Manual (JAR)](#manual-jar)
- [Quick Start](#quick-start)
- [Modules & Endpoints](#modules--endpoints)
- [Security Best Practices](#security-best-practices)
- [Examples](#examples)
- [Utilities at a Glance](#utilities-at-a-glance)
- [Contributing](#contributing)
- [License](#license)

---

## Features
- ✅ **Secure auth** (HMAC signing, timestamp/nonce headers)
- ✅ **Idempotency + retry** helpers to avoid duplicate charges
- ✅ **Consistent API** across modules (Cashout, Inquiry, Transfer)
- ✅ **Typed errors & structured responses**
- ✅ **Ready-to-run examples & unit tests**

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
