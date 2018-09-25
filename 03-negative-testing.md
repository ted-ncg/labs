# Negative (Exception) Tests

### This page: https://github.com/ted-ncg/labs/blob/master/03-negative-testing.md

## Goal

Add "negative" tests to the Account class testing with exceptions.

## Prepare

1. Make sure **all** of the tests currently pass!

----

**Remember:** Follow the *Red-Green-Commit-Refactor-Commit* cycle

**Example:**

Start with the test: make the test fail, fix the exception being thrown in code, then test should pass.
Don't forget to run all tests

----

### Invalid Deposit Amount

Write a test and then make it pass for **each** scenario (**REMEMBER**: ping-pong pair!): 

  1. Depositing a negative or zero amount must `throw` the `InvalidAmountException` -- what might you need to refactor to make this change?

     **NOTE:** Use AssertJ's exception assertion mechanism, e.g.:
  
     ```java
     assertThatThrownBy(() -> { account.withdraw(-1); })
             .isInstanceOf(InvalidAmountException.class);
     ```

    **NOTE:** Create the Exception class by subclassing `RuntimeException`

### Invalid Withdrawal Amount

  1. Withdrawing a negative or zero amount must `throw` an `InvalidAmountException` (which you need to create as a *subclass* of `RuntimeException`)
   
### Can't Overdraw Account

1. Add tests and code to ensure that the Account's balance can not go negative when you **withdraw**. 
   If you try to overdraw the account, throw an exception: (`InsufficientBalanceException`).
