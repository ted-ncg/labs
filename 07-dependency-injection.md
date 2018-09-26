# Automatic Dependency Injection

### This page: https://github.com/ted-ncg/labs/blob/master/07-dependency-injection.md

## Goal

Modify the `AccountApiController` so that you can return an `Account` from the `AccountRepository`.

----

### References

This reference of annotations might be helpful: http://engineering.pivotal.io/post/must-know-spring-boot-annotations-controllers/

### Latest Code

If you want to download the latest code that the instructor did in class, it can be found at:

```
https://bitbucket.org/tedmyoung/foster-city-201809-canteen
```

----

## Steps

1. Run *all* your tests to make sure everything is still working

1. Open `AccountApiController` and create a constructor that takes a *dependency* on `AccountRepository`

   * Make sure to save the incoming reference to a member (instance) variable

1. Add the `@Repository` annotation to `FakeAccountRepository` so that Spring can find and create it during the automatic dependency injection ("autowire") process.

1. To ensure that your code works properly via the tests, **you must** change the annotations on the `AccountRestTest` by **replacing** the `@WebMvcTest` with these two annotations:

      ```java
      @SpringBootTest
      @AutoConfigureMockMvc
      ```
    
   Then run the test and see if it passes.

## Sample Data in Repository

To "pre-load" some account data into the repository, copy the entire file [AccountDataLoader.java](https://github.com/ted-ncg/labs/blob/master/AccountDataLoader.java) into the `src` production code directory.

## Lookup Account ID in Repository

1. Open `AccountApiController`

1. In the `accountInfo` method, use the `accountId` that's passed in to:

    * Find the `Account` from the `AccountRepository` by the *id* using `findOne()`
    
    * Convert it to an `AccountResponse` and return it

## Try it Out

1. See if you get the data by hitting this URL endpoint:

   ```
   http://localhost:8080/api/accounts/1
   ```
   
   You should see the JSON representation of the Account with ID of `1` that has a balance of `10`.

1. Try adding more "sample" data to the `AccountDataLoader`'s `run()` method and the use your browser, or *curl* to hit the endpoint with different IDs.

----

> <img src="stop-sign.jpg" width="56" /> Once you've completed the above steps, you're done, so let the instructor know.
