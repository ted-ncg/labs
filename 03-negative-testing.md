# More Negative Tests

### https://github.com/ted-ncg/labs/blob/master/03-negative-testing.md

## Goal

Add more "negative" tests to the Account class testing.

## Steps

1. Download (or `git pull`) the latest code from `https://github.com/tedyoung/austin-canteen-201804` or `https://bitbucket.org/tedmyoung/austin-canteen-201804`

1. Make sure all of the tests currently pass!

### Invalid Amount

1. Write a test and then make it pass for each scenario: 

    * Withdrawing a negative or zero amount must `throw` an `InvalidAmountException` (which you need to create as a *subclass* of `RuntimeException`)
   
    * Depositing a negative or zero amount must also `throw` the `InvalidAmountException`

    * Use AssertJ's exception assertion mechanism, e.g.:
    
    ```java
    assertThatThrownBy(() -> { account.withdraw(-1); })
            .isInstanceOf(InvalidAmountException.class);
    ```

    * Remember to follow the *Red-Green-Commit-Refactor-Commit* cycle

### Can't Overdraw Account

1. Add tests and code to ensure that the Account's balance can not go negative when you withdraw, and if so, throws an appropriate exception (`InsufficientBalanceException`) if you try to overdraw the account.
