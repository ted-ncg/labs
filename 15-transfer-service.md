# Transfer Service Form

**Link:** `https://github.com/ted-ncg/labs/blob/master/15-transfer-service.md`

**Goal**: Implement (test-first!) a service that will transfer money from one account to another.

## Part 1: Transfer Service

1. Create a JUnit (non-Spring) test class `AccountTransferServiceTest`

1. Write tests against a new class, `AccountTransferService`, where the transfer method signature is:

    ```java
    public void transfer(Account source,
                         Account destination,
                         int amount)    
    ```

1. Write tests (and then the code to make it pass) for the following:

   1. Transferring money from one account (the *source*) to another (the *destination*) works
   
   1. Attempting to transfer an amount <= 0, throws an `InvalidAmountException`
   
   1. Attempting to transfer more money than the *source* account has, throws an `InsufficientBalanceException`
   
   1. If either account is `null`, it should throw an `IllegalArgumentException`.

