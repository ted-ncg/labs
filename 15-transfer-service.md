# Transfer Service

## https://github.com/ted-ncg/labs/blob/master/15-transfer-service.md

**Goal**: Implement (test-first!) a service that will transfer money from one account to another.

### Steps

1. Create a test class AccountTransferServiceTest

1. Write tests against the AccountTransferService, where the transfer method signature is:

    ```java
    public void transfer(Account source,
                         Account destination,
                         int amount)    
    ```

1. Write tests to ensure the following:

   1. Transferring money from one account (the *source*) to another (the *destination*) works
   
   1. Attempting to transfer an amount <= 0 fails (throws an `InvalidAmountException`)
   
   1. Attempting to transfer more money than the *source* account has throws an `InsufficientFundsException`
   
   1. If either account is `null`, it should throw an `IllegalArgumentException`.
