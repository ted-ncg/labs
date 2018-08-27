# Test First With Pairing

### This page: `http://bit.ly/ncg-lab2`

## Goal

Now that you've seen how the Deposit functionality was written in test-first style, you'll write tests and code for the  **withdraw** functionality in the `Account` class.

## Preparation

Make sure you have the latest version, where we "group programmed" the **deposit** functionality to the `Account` class.

1. Download (or `git pull`) the latest code from `https://bitbucket.org/tedmyoung/austin-201808-canteen`

1. Make sure all of the tests currently pass before continuing!

### Using Ping-Pong Pairing

1. Add a new test class `AccountWithdrawTest`

   * **Note:** Make sure to properly import AssertJ's `assertThat` method: `import static org.assertj.core.api.Assertions.assertThat;`

1. Add a test method such as
 
    ```java
    @Test
    public void withdraw3DollarsFromAccountHaving7DollarsResultsIn4DollarBalance() throws Exception
    ```
   
   * AssertJ JavaDoc can be found here: http://joel-costigliola.github.io/assertj/core/api/index.html

1. Make sure the test **fails** before continuing.

1. Pass the computer to your pair and they should write the code to make the test pass.

1. Have the person who wrote the code, write another test to further exercise the code.

1. Now have the other person write any code to make all of the tests pass.
