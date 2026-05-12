# RisingTideDigitalInc


API created Custmer Profile w/ savings 

method: POST

endpoint 

http://localhost:8085/v1/api/customer


sample payload (w/out savings) :

```json
{
  "customerName": "Test One",
  "customerMobile": "09171252528",
  "customerEmail": "test@emailtest.com",
  "address1": "UNIT 12 ",
  "address2": ""
}

```


sample payload (w/ savings) :
```json
{
  "customerName": "Test Two",
  "customerMobile": "09171252528",
  "customerEmail": "test2@emailtest.com",
  "address1": "UNIT 12 ",
  "address2": "",
    "savings": [
    {
      "accountNumber": "SA-001010",
      "balance": 99000000.00,
      "accountType": "SAVINGS"
    }
  ]
}

```

sample payload w/ savings and Checking 
```json
{
    "customerName": "Test Three",
    "customerMobile": "09171252528",
    "customerEmail": "test3@emailtest.com",
    "address1": "UNIT 12 ",
    "address2": "",
    "savings": [
        {
            "accountNumber": "SA-00134",
            "balance": 99000000.00,
            "accountType": "SAVINGS"
        },
        {
            "accountNumber": "CH-0014440",
            "balance": 99000000.00,
            "accountType": "CHECKING"
        }
    ]
}

```


get Customer Profile

method: GET

endpoint 

http://localhost:8085/v1/api/account/{customerNumber}

success response:
```json

{
    "customerNumber": 6,
    "customerName": "Test One",
    "customerMobile": "09171252528",
    "customerEmail": "test@emailtest.com",
    "address1": "UNIT 12 ",
    "address2": "",
    "savings": [],
    "transactionStatusCode": 302,
    "transactionStatusDescription": "Customer account found "
}

```

// savings
```json
{
    "customerNumber": 7,
    "customerName": "Test Two",
    "customerMobile": "09171252528",
    "customerEmail": "test2@emailtest.com",
    "address1": "UNIT 12 ",
    "address2": "",
    "savings": [
        {
            "accountNumber": "SA-001010",
            "balance": 9.9E7,
            "accountType": "SAVINGS"
        }
    ],
    "transactionStatusCode": 302,
    "transactionStatusDescription": "Customer account found "
}

```

// w/ savings and Checking account 
```json
{
    "customerNumber": 8,
    "customerName": "Test Three",
    "customerMobile": "09171252528",
    "customerEmail": "test3@emailtest.com",
    "address1": "UNIT 12 ",
    "address2": "",
    "savings": [
        {
            "accountNumber": "SA-00134",
            "balance": 9.9E7,
            "accountType": "SAVINGS"
        },
        {
            "accountNumber": "CH-0014440",
            "balance": 9.9E7,
            "accountType": "CHECKING"
        }
    ],
    "transactionStatusCode": 302,
    "transactionStatusDescription": "Customer account found "
}



```


