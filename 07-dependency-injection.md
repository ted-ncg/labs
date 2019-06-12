# Automatic Dependency Injection

### This page: https://github.com/ted-ncg/labs/blob/master/07-dependency-injection.md

## Goal

Modify the `AccountApiController` so that you can return an `Account` from the `AccountRepository`.

----

### References

This reference of annotations might be helpful: http://engineering.pivotal.io/post/must-know-spring-boot-annotations-controllers/

----

### Latest Code

If you want to download the latest code that the instructor did in class, it can be found at:

```
https://bitbucket.org/tedmyoung/austin-201906-canteen
```

----

> <img src="stop-sign.jpg" width="56" /> Run *all* your tests to make sure everything is still working before continuing.

----

## Add Repository Dependency

1. Open `AccountApiController` and create a constructor that takes a *dependency* on `AccountRepository` as a parameter.

   * Make sure to save the incoming reference to a member (instance) variable

1. Create a new test class named `AccountApiControllerTest` and copy the contents below

   ```java
   package com.visa.ncg.canteen;
   
   import org.junit.Test;
   
   import static org.assertj.core.api.Assertions.assertThat;
   
   public class AccountApiControllerTest {
     @Test
     public void testGetMapping() throws Exception {
       AccountRepository accountRepository = new FakeAccountRepository();
       Account account = new Account();
       account.deposit(73);
       account.setId(123L);
       accountRepository.save(account);

       AccountApiController controller = new AccountApiController(accountRepository);
    
       AccountResponse accountResponse = controller.accountInfo("123");

       assertThat(accountResponse.getBalance())
           .isEqualTo(73);
     }
   }
   ```

1. Run this test, which should **fail**. The next section will make it pass.

## Lookup Account ID in Repository

1. Open `AccountApiController`

1. In the `accountInfo` method, use the `accountId` that's passed in to:

    * Find the `Account` from the `AccountRepository` by the *id* using `findOne()`
    
    * Convert it to an `AccountResponse` and return it

1. The `AccountApiControllerTest` should now pass.

## Autowire Repository

1. Add the `@Repository` annotation to `FakeAccountRepository` so that Spring can find and create it during the automatic dependency injection ("autowire") process.

## Sample Data in Repository

To "pre-load" some account data into the repository, copy the entire file
[AccountDataLoader.java](https://github.com/ted-ncg/labs/blob/master/AccountDataLoader.java)
into the production code directory with the other domain classes.

## Updating REST Test

1. To ensure that dependencies are wired by Spring when running tests,
   **you must** change the annotations in the `AccountRestTest` 
   class by **replacing** the `@WebMvcTest` with these two annotations:

      ```java
      @SpringBootTest
      @AutoConfigureMockMvc
      ```

1. To make the tests pass, change the first test in `AccountRestTest` 
   to reflect the account data loaded in the data loader.

1. Make sure all other tests pass, too.

## Try it Out

1. See if you get the data by hitting this URL endpoint:

   ```
   http://localhost:8080/api/accounts/1
   ```
   
   You should see the JSON representation of the Account with ID of `1` that has a balance of `20`.

   > Note: the IDs here will depend on how your `FakeAccountRepository` generates IDs in the
     `save()` method when the `AccountDataLoader.run()` is called.

1. Try adding more "sample" data to the `AccountDataLoader`'s `run()` method and the use your browser, or *curl* to hit the endpoint with different IDs.

----

> <img src="stop-sign.jpg" width="56" /> Once you've completed the above steps, you're done, so let the instructor know.
