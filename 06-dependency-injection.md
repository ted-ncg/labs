# Automatic Dependency Injection

## https://github.com/ted-ncg/labs/blob/master/06-dependency-injection.md

**Goal**: Modify your `AccountsController` so that you can return an `Account` from the `AccountRepository`.

1. Modify the `AccountsController` constructor to take in a *dependency* on `AccountRepository`

1. Ensure that `AccountRepository` is annotated properly so that Spring can create it during the automatic dependency injection process.

1. In your Account info method, use the `accountId` that's passed in to return the account for that ID.

    * For example, hitting this URL
    
      `http://localhost:8080/api/accounts/1`
    
      would return the `Account` with ID of 1.

1. To "pre-load" data into the `AccountRepository`, you can use the code in the [AccountDataLoader.java](https://github.com/ted-ncg/labs/blob/master/AccountDataLoader.java) file.

1. To ensure that your code works properly, add this test to the `AccountRestTest` class:

    ```java
    // use this import method:   
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
 
      // add this test to your test class
      @Test
      public void getWithAccountIdReturnsAccountForThatId() throws Exception {
        mockMvc.perform(get("/api/accounts/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.balance").value("0"));
      }
    ```