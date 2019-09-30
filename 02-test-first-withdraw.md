# Test First With Pairing

### This page: `http://bit.ly/ncg-lab2`

## Goal

Now that you've seen how the Deposit functionality was written in test-driven style,
you'll write tests and code for the  **withdraw** functionality in the `Account` class.

## Preparation

Make sure you have the latest version, where we "group coded" the **deposit** functionality in the `Account` class.

1. Download or `git pull` the latest code from

   ```
   https://bitbucket.org/tedmyoung/fostercity-201909-canteen
   ```

1. Make sure all of the tests **pass** before continuing!

----

## Using Ping-Pong Pairing

1. Add a new test class `AccountWithdrawTest`

   * **Note:** Make sure to properly import AssertJ's `assertThat` method:
   
     ```
     import static org.assertj.core.api.Assertions.assertThat;
     ```

1. Add a test method such as
 
    ```java
    @Test
    public void withdraw3DollarsFromAccountHaving7DollarsResultsIn4DollarBalance() throws Exception
    ```
   
   * Write a test that matches the intent of the method name.
   
     > AssertJ JavaDoc can be found here: http://joel-costigliola.github.io/assertj/core/api/index.html

1. Make sure the test **fails** before continuing.

1. Pass the computer to your pair, and they should write the code to make the test pass.

1. Have the person who just wrote the code, write another test to further exercise the code.

1. Now have the other person write any code to make all of the tests pass.

<br/>

----

## Test To Write

You should write at least 3 tests. Think about:

* Withdrawing zero -- this is OK!
* Withdrawing a single amount
* Withdrawing twice
* Withdrawing more than available balance -- this is allowed, do *not* throw an Exception, let the balance go *negative*

----

<img src="stop-sign.jpg" width="56"/> Once you've completed the above steps, let the instructor know.

----

## Account Class and Test

Here's the test we wrote together (the `Account` is below):

```java
package com.visa.ncg.canteen;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DepositTest {

  @Test
  public void newAccountBalanceIsZero() throws Exception {
    Account account = new Account();

    assertThat(account.balance())
        .isZero();
  }

  @Test
  public void deposit10ResultsInBalanceOf10() throws Exception {
    // Given a new account
    Account account = new Account();

    // when I deposit 10
    account.deposit(10);

    // Then I expect balance to be 10
    assertThat(account.balance())
        .isEqualTo(10);
  }

  @Test
  public void deposit7ToAccountWith12ResultsInBalanceOf19() throws Exception {
    // Given
    Account account = new Account();
    account.deposit(12);

    // When
    account.deposit(7);

    // Then
    assertThat(account.balance())
        .isEqualTo(19);
  }

  @Test
  public void depositZeroHasNoAffectOnBalance() throws Exception {
    Account account = new Account();
    account.deposit(3);

    account.deposit(0);

    assertThat(account.balance())
        .isEqualTo(3);
  }

}
```

and the Account

```java
package com.visa.ncg.canteen;

public class Account {
  private int balance;

  public int balance() {
    return balance;
  }

  public void deposit(int amount) {
    balance = balance + amount;
  }
}
```

