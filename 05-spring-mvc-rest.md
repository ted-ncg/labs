# Spring REST Controller

## Your Task

1. Implement a class named `AccountController`, annotated with `@RestController`.
 
2. Implement a method `public Account accountInfo(String accountId)` that is mapped so that it will respond (be routed) to

    ```
    http://localhost:8080/api/accounts/123
    ``` 

    * Use the `@GetMapping` annotation for the method
    
    * Use the `@PathVariable` annotation for the `accountId` variable
    
    * Note: `8080` will be the default port when running the application

1. Inside the `accountInfo` method, instantiate a new `Account` object, set its ID to the incoming `accountId` from the path, set the `balance` to some amount, and then return the object.

1. Run the `CanteenApplication` from within IntelliJ IDEA

1. Make a request against the endpoint by either:
 
    * **Browser**: opening the URL `http://localhost:8080/api/accounts/123`

    * **`curl`**: use `curl` to make the request, e.g.:
      ```
      curl -v localhost:8080/api/accounts/123
      ```

1. You should see something like

    ```json
    {"id": 123}
    ```

----

## Part II

Make the `/api/accounts/{id}` endpoint return JSON that contains the ID and Balance for the account having the given ID.

1. Copy the [AccountRestTest.java](https://github.com/ted-ncg/labs/blob/master/AccountRestTest.java) file into your test/java directory and run the test.

1. Returning a "plain old" Java object (POJO, also called a JavaBean or just Bean) that has *properties* for ID and for Balance:

   * Create a *new* class, e.g., `AccountResponse`, that has two *JavaBean* properties: `Id`, and `Balance`.
     * Remember, you'll need a getter and setter, named properly, for each property
   * Change the `accountInfo()` method (in your controller class) to return the `AccountResponse` instance, instead of returning an `Account` object.
     * e.g.: `public AccountResponse accountInfo(...`

1. Make the AccountRestTest test pass.

## Documentation

* Spring `@RestController`: http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc-ann-restcontroller

  * An example of a *Controller* "endpoint":
  
  ```java
  package com.visa.ncg.canteen;
  
  import org.springframework.web.bind.annotation.*;
  
  @RestController
  public class HelloController {
  
    @GetMapping("/api/hello/{text}")
    public String sayHelloTo(@PathVariable("text") String name) {
      return "Hello " + name;
    }
  }  
  ```

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