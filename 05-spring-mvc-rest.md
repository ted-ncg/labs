# Spring REST Controller

## Your Task

1. Implement a class named `AccountController`, annotated with `@RestController`.
 
2. Implement a method `public Account accountInfo(String accountId)` that is mapped so that it will respond (be routed) to

    ```
    http://localhost:8080/api/accounts/123
    ``` 

    * Use the `@GetMapping` annotation for the method
    
    * Use the `@PathVariable` annotation for the `accountId` variable

1. Inside the `accountInfo` method, instantiate a new `Account` object, set its balance, and then return the object.

1. Run the `CanteenApplication` from within IntelliJ IDEA

1. Make a request against the endpoint by either:
 
    * **Browser**: opening the URL `http://localhost:8080/api/accounts/123`

    * **`curl`**: use `curl` to make the request, e.g.:
      ```
      curl -v localhost:8080/api/accounts/123
      ```

1. You should see something like

    ```json
    {"balance": 22}
    ```

1. Copy the AccountRestTest.java file into your test/java directory and run the test.

1. Commit your code.

## Documentation

* Spring `@RestController`: http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc-ann-restcontroller

* Returning status codes via `ResponseEntity` (JavaDoc): https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html

  **Example:**

  ```java
    @GetMapping("/{name}")
    public HttpEntity<Account> findAccount(@PathVariable("name") String accountName) {
      if (accountRepository.exists(accountName) == false) {
        return ResponseEntity.badRequest().build();
      }
      // ... rest of code
      return ResponseEntity.ok(account);
    }

  ```

* Extracting information from the URI: http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc-ann-requestmapping-uri-templates

* Handling exceptions using @ExceptionHandler: https://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html#mvc-ann-exceptionhandler