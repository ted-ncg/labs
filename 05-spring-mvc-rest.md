# Spring REST Controller

## Your Task

1. Implement a class named `AccountApiController`, annotated with `@RestController`.
 
2. Implement a method `public Account accountInfo(String accountId)` that is mapped so that it will respond (be "routed" or "mapped") to

    ```
    http://localhost:8080/api/accounts/123
    ``` 

    * Use the `@GetMapping` annotation for the method
    
    * Use the `@PathVariable` annotation for the `accountId` variable
    
    * Note: `8080` is the default port when running the application on your machine

1. Inside the `accountInfo` method, instantiate a new `Account` object, set its ID to the incoming `accountId` from the path, set the `balance` to some amount, and then return the object.

1. Run the `CanteenApplication` from within IntelliJ IDEA
   * Right click on the `CanteenApplication` file and then select "Run..."
 
   * Once you see something like the following, you can go to the next step:

   ```
   2017-08-27 09:37:21.351  INFO 11472 --- [  Main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
   2017-08-27 09:37:21.355  INFO 11472 --- [  Main] com.visa.ncg.canteen.CanteenApplication  : Started CanteenApplication in 3.433 seconds (JVM running for 4.064)
   ```

1. Make a request against the endpoint by either:
 
    * **Browser**: opening the URL `http://localhost:8080/api/accounts/123`

    * **`curl`**: use `curl` to make the request, e.g.:
      ```
      curl --noproxy localhost -v localhost:8080/api/accounts/123
      ```

      * **Note:** the `--noproxy localhost` tells `curl` to bypass the proxy for `localhost`

1. You should see something like

    ```json
    {"id": 123}
    ```

----

## Part II

### Goal

Make the `/api/accounts/{id}` endpoint return JSON that contains both the ID and the Balance for an account having the given ID.

### Steps

1. Copy the [AccountRestTest.java](https://github.com/ted-ncg/labs/blob/master/AccountRestTest.java) file into your test/java directory and run the tests. *(It will fail!)*  

1. Instead of returning the "domain" `Account` instance, you will return a "plain old" Java object (POJO, also called a JavaBean or just Bean) that has *properties* for ID and for Balance:

   * Create a *new* class, e.g., `AccountResponse`, that has two *JavaBean* properties: `Id`, and `Balance`.
     * Remember, you'll need a getter and setter, named properly, for each property
   * Change the `accountInfo()` method (in your controller class) to return the `AccountResponse` instance, instead of returning an `Account` object.
     * e.g.: `public AccountResponse accountInfo(...`

1. Try out the new code from the browser via `http://localhost:8080/api/accounts/456`

1. Now make the AccountRestTest tests pass.

## Documentation/Reference

* Spring `@RestController`: http://docs.spring.io/spring/docs/4.3.11.RELEASE/spring-framework-reference/htmlsingle/#mvc-ann-restcontroller

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

* Returning status codes via `ResponseEntity` (JavaDoc): https://docs.spring.io/spring/docs/4.3.11.RELEASE/javadoc-api/org/springframework/http/ResponseEntity.html

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

* Extracting information from the URI: http://docs.spring.io/spring/docs/4.3.11.RELEASE/spring-framework-reference/htmlsingle/#mvc-ann-requestmapping-uri-templates

* Handling exceptions using @ExceptionHandler: https://docs.spring.io/spring/docs/4.3.11.RELEASE/spring-framework-reference/html/mvc.html#mvc-ann-exceptionhandler