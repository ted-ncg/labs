# Test First With Pairing

### https://github.com/ted-ncg/labs/blob/master/02-test-first-withdraw.md

## Goal

Writing tests *first*, add **withdraw** functionality to the `Account` class.

## Steps

1. Download (or `git pull`) the latest code from `https://github.com/ted-ncg/austin-canteen`

1. Make sure all of the tests currently pass!

### Using ping-pong pairing

1. Add a new test class `AccountWithdrawTest`

1. Add a test method such as
 
    ```java
    @Test
    public void withdraw3DollarsFromAccountHaving7DollarsResultsIn4DollarBalance() throws Exception
    ```

   Make sure to properly import AssertJ's `assertThat` method: `import static org.assertj.core.api.Assertions.assertThat;`

1. Follow the Red-Green-Refactor-Commit cycle to add functionality