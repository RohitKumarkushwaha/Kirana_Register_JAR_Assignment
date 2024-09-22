# Kirana Register API

## Overview

The Kirana Register API is a Spring Boot application designed to manage and process financial transactions. It supports recording transactions in both INR and USD, with automatic currency conversion for USD. The API provides endpoints for retrieving transactions, generating daily reports, and managing records.

## Features

- **Record Transactions:** Save transactions in INR or USD, with currency conversion for USD.
- **Retrieve Transactions:** Fetch all transactions or filter by date.
- **Generate Daily Reports:** Group transactions by date to generate daily reports.
- **Update Transactions:** Update existing transaction records.
- **Delete Transactions:** Remove transactions by ID.

## Technology Stack

- **Backend:** Java, Spring Boot
- **Currency Conversion API:** [FX Rates API](https://api.fxratesapi.com/latest)
- **Database:** (Specify SQL or NoSQL database used)

## API Endpoints

### 1. Record Transaction

- **Endpoint:** `POST /KiranaRegisterAPI/save`
- **Description:** Records a new transaction.
- **Query Parameters:**
  - `currency` (required): Currency type, e.g., "USD" or "INR".
- **Request Body:**
  ```json
  {
    "name": "Alice Smith",
    "description": "Electronics purchase",
    "credit_amount": "1000",
    "debit_amount": "0"
  }
