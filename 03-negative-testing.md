# More Negative Tests

### https://github.com/ted-ncg/labs/blob/master/03-negative-testing.md

## Goal

Add more "negative" tests to the Account class testing.

## Steps

1. Download (or `git pull`) the latest code from `https://github.com/ted-ncg/austin-canteen`

1. Make sure all of the tests currently pass!

1. Add new code (test first!) that ensures amounts for withdraw are positive, i.e., don't allow withdrawing negative amounts.

    * If not valid, throw an appropriate `RuntimeException`
    
    * Use AssertJ's exception assertion mechanism, e.g.:
    
    ```java
    assertThatThrownBy(() -> { account.withdraw(-1); })
            .isInstanceOf(InvalidAmountException.class);
    ```
    
    * Remember to follow the *Red-Green-Commit-Refactor-Commit* cycle

1. Do the same thing for `deposit`, ensuring that the amount being deposited is valid.

1. Now add tests and code to ensure that the Account's balance can not go negative, and throws an appropriate exception if you try to overdraw the account.
