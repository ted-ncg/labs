# Spring REST API Response Object

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

* Spring `@RestController`: http://docs.spring.io/spring/docs/4.3.16.RELEASE/spring-framework-reference/htmlsingle/#mvc-ann-restcontroller

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

* Returning status codes via `ResponseEntity` (JavaDoc): https://docs.spring.io/spring/docs/4.3.16.RELEASE/javadoc-api/org/springframework/http/ResponseEntity.html

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

* Extracting information from the URI: http://docs.spring.io/spring/docs/4.3.16.RELEASE/spring-framework-reference/htmlsingle/#mvc-ann-requestmapping-uri-templates

* Handling exceptions using @ExceptionHandler: https://docs.spring.io/spring/docs/4.3.16.RELEASE/spring-framework-reference/html/mvc.html#mvc-ann-exceptionhandler
