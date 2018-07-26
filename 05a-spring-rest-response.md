# Spring REST API Response Object

### This page: https://github.com/ted-ncg/labs/blob/master/05a-spring-rest-response.md

## Goal

Make the `/api/accounts/{id}` endpoint return JSON that contains both the ID and the Balance for an account having the given ID.

## Steps

1. Copy the [AccountRestTest.java](https://github.com/ted-ncg/labs/blob/master/AccountRestTest.java) file into your test/java directory and run the tests. *(Make sure that it fails!)*  

1. Instead of returning the "domain" `Account` instance, you will return a *JavaBean* object (also known as just a *Bean*) that has *properties* for both ID and for Balance:

   * Create a *new* class, e.g., `AccountResponse`, that has two *JavaBean* properties: `Id`, and `Balance`.
     * Remember, you'll need a getter and setter, named properly, for each property
   * Change the `accountInfo()` method (in your controller class) to return the `AccountResponse` instance, instead of returning an `Account` object.
     * e.g.: `public AccountResponse accountInfo(...`

1. Try out the new code from the browser via `http://localhost:8080/api/accounts/456`

1. Now make the AccountRestTest tests pass.

## Documentation/Reference

* Annotations reference: http://engineering.pivotal.io/post/must-know-spring-boot-annotations-controllers/

* Spring Docs on `@RestController`: http://docs.spring.io/spring/docs/4.3.16.RELEASE/spring-framework-reference/htmlsingle/#mvc-ann-restcontroller

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

* Extracting path and query info from the URI: https://docs.spring.io/spring/docs/4.3.16.RELEASE/spring-framework-reference/htmlsingle/#mvc-ann-requestmapping-uri-templates

* Handling exceptions using @ExceptionHandler: https://docs.spring.io/spring/docs/4.3.16.RELEASE/spring-framework-reference/html/mvc.html#mvc-ann-exceptionhandler
