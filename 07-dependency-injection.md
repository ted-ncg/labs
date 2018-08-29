# Automatic Dependency Injection

### This page: https://github.com/ted-ncg/labs/blob/master/07-dependency-injection.md

## Goal

Modify the `AccountApiController` so that you can return an `Account` from the `AccountRepository`.

### References

This reference of annotations might be helpful: http://engineering.pivotal.io/post/must-know-spring-boot-annotations-controllers/

## Steps

1. Run *all* your tests to make sure everything is still working

1. Modify the `AccountApiController` constructor to take in a *dependency* on `AccountRepository`

1. Ensure that `AccountRepository` is annotated properly so that Spring can find and create it during the automatic dependency injection ("autowiring") process.

1. In your Account info method, use the `accountId` that's passed in to:

    * Look up the `Account` from the `AccountRepository` by its *id*
    
    * Convert it to an `AccountResponse` and return it

    * For example, hitting this URL
    
      `http://localhost:8080/api/accounts/0`
    
      would return the Account with ID of 0, formatted as JSON.

1. To "pre-load" data into the `AccountRepository`, you can use the code in the [AccountDataLoader.java](https://github.com/ted-ncg/labs/blob/master/AccountDataLoader.java) file (put it with your other production code).

1. To ensure that your code works properly via the tests, change the annotations on the `AccountRestTest` by replacing the `@WebMvcTest` with these two annotations:

  ```java
  @SpringBootTest
  @AutoConfigureMockMvc
  ```
  
  Then you can run the test.

1. Try adding more "sample" data to the `AccountDataLoader` and using your browser, or *curl* to hit the endpoint.
