# Spring REST API Response Object

### This page: https://github.com/ted-ncg/labs/blob/master/06-spring-rest-response.md

## Goal

Make the `/api/accounts/{id}` endpoint return JSON that contains both the **ID** and the **Balance**
for an account having the given ID.

## A. Failing Integration Test

1. Copy the [AccountRestTest.java](https://github.com/ted-ncg/labs/blob/master/AccountRestTest.java)
   file into your test/java directory and run the tests. *(Make sure that it fails!)*  

## B. Return JavaBean (DTO)

Instead of returning the "domain" `Account` instance, you will return a *JavaBean* object
(also known as just a *DTO*) that has *properties* for both ID and Balance:

1. Create a *new* class named `AccountResponse`, that has two *JavaBean* properties:
    * `id`, of type `long`
    * `balance` of type `int`

1. Create both getters **and** setters, named properly, for each property by using
   `Command + N` (Mac) or `Alt + Insert` (Windows) to *generate* the Getter and Setters.

1. Change the `accountInfo()` method (in the `AccountApiController` class) to return an `AccountResponse` instance, instead of returning an `Account` object.
    * e.g.: `public AccountResponse accountInfo(...`

1. Instantiate an `AccountResponse` object and **copy** the ID and Balance from the `Account`
   object to the response by using the setters.

1. Try out the new code from the browser via `http://localhost:8080/api/accounts/456`

## C. Make the Test Pass

Run the `AccountRestTest` test, see why it fails, and change **only the code** to make the test pass.

----

> <img src="stop-sign.jpg" width="56" /> Once you've completed the above steps, you are done, so let the instructor know.

----

## Documentation/Reference

Some useful documentation for learning more:

* Annotations reference: http://engineering.pivotal.io/post/must-know-spring-boot-annotations-controllers/

* Spring Docs on `@RestController`: http://docs.spring.io/spring/docs/4.3.19.RELEASE/spring-framework-reference/htmlsingle/#mvc-ann-restcontroller

### Returning Custom Status Codes

* Returning status codes via `ResponseEntity` (JavaDoc): https://docs.spring.io/spring/docs/4.3.19.RELEASE/javadoc-api/org/springframework/http/ResponseEntity.html

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
