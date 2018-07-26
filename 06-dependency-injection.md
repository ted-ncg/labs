# Automatic Dependency Injection

## https://github.com/ted-ncg/labs/blob/master/06-dependency-injection

## Goal

Modify the `AccountApiController` so that you can return an `Account` from the `AccountRepository`.

## Steps

1. Modify the `AccountApiController` constructor to take in a *dependency* on `AccountRepository`

1. Ensure that `AccountRepository` is annotated properly so that Spring can find and create it during the automatic dependency injection ("autowiring") process.

1. In your Account info method, use the `accountId` that's passed in to:

    * Look up the `Account` from the `AccountRepository` by its *id*
    
    * Convert it to an `AccountResponse` and return it

    * For example, hitting this URL
    
      `http://localhost:8080/api/accounts/1`
    
      would return the Account with ID of 1, formatted as JSON.

1. To "pre-load" data into the `AccountRepository`, you can use the code in the [AccountDataLoader.java](https://github.com/ted-ncg/labs/blob/master/AccountDataLoader.java) file (put it with your other production code).

1. To ensure that your code works properly via the tests, change the annotations on the `AccountRestTest` by replacing the `@WebMvcTest` with these two annotations:

  ```java
  @SpringBootTest
  @AutoConfigureMockMvc
  ```
  
  Then you can run the test.

1. Try adding more "sample" data to the `AccountDataLoader` and using your browser, or *curl* to hit the endpoint.
