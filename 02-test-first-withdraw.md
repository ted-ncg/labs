# Test First With Pairing

#### Short Link: `http://bit.ly/ncg-lab2`

## Goal

Now that you've seen how the Deposit functionality was written in test-first style, you'll write tests and code for the  **withdraw** functionality in the `Account` class.

## Steps

Make sure you have the latest version, where we "mob programmed" the **deposit** functionality to the `Account` class.

1. Download (or `git pull`) the latest code from `https://github.com/tedyoung/austin-canteen-201804` (or `https://bitbucket.org/tedmyoung/austin-canteen-201804`)

1. Make sure all of the tests currently pass before continuing!

### Using Ping-Pong Pairing

1. Add a new test class `AccountWithdrawTest`

1. Add a test method such as
 
    ```java
    @Test
    public void withdraw3DollarsFromAccountHaving7DollarsResultsIn4DollarBalance() throws Exception
    ```

   Make sure to properly import AssertJ's `assertThat` method: `import static org.assertj.core.api.Assertions.assertThat;`

1. Make sure the test **fails** before continuing.

1. Pass the computer to your pair and they should write the code to make the test pass.

1. Write a couple of other tests

1. Continue to follow the _Red-Green-Refactor-Commit_ cycle to add functionality.
