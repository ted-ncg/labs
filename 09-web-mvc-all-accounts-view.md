# All Accounts View

### This page: https://github.com/ted-ncg/labs/blob/master/09-web-mvc-all-accounts-view.md

## Goal

In this lab you'll add error handling to the previous lab to get rid of `NullPointerException`s.

You'll then provide a "home" page that displays **all** the accounts in the system.

----

### References

* Thymeleaf 3: http://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#using-and-displaying-variables

----

## A. First, Some Tests

Add this [AccountViewWebIntegrationTest](https://github.com/ted-ncg/labs/blob/master/AccountViewWebIntegrationTest.java) and run it and make sure it passes.

  * If it doesn't, ensure that you're putting an `AccountResponse` object into the model and not an `Account`.

**Don't move on until this test passes**

----

## B. Handle Not Found

Add error handling when an account isn't found.

There are a number of ways to handle problems, we're going to use the `@ResponseStatus` annotation
on a custom `Exception` to indicate that there's a problem.

### Steps

1. Let's add a test that will fail, and then we'll make it pass.
   Add this test to the `AccountViewWebIntegrationTest` class, run it and it should fail.

   ```java
   @Test
   public void getOfNonExistentAccountReturns404() throws Exception {
     mockMvc.perform(get("/account/9999"))
            .andExpect(status().isNotFound());
   }
   ```

1. Make sure the above test **fails** before writing the code to make it pass.

1. Create a new exception class like this:

   ```java
   import org.springframework.http.HttpStatus;
   import org.springframework.web.bind.annotation.ResponseStatus;
  
   @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No account with that ID was found.")
   public class NoSuchAccountHttpException extends RuntimeException {   
   }
   ```

1. Inside of your `AccountWebController`, add code to the `accountView` method
   so that if the `findOne` method from the repository returns `null`, 
   you will throw the `NoSuchAccountHttpException` that you created above.

1. Try running the `AccountViewWebIntegrationTest` now and it should **pass**.

1. Try it out from the browser: you should get a 404 page when requesting:

    ```
    http://localhost:8080/account/9999
    ```

### Exception Handling Alternatives

Other ways to handle exceptions with a specific view (HTML page) can be found here:

`https://docs.spring.io/spring/docs/5.1.8.RELEASE/spring-framework-reference/web.html#mvc-ann-exceptionhandler`

For example:

```java
@ExceptionHandler
public String handleNotFound(NoSuchAccountException ex, Model model) {
  model.addAttribute("message", "Couldn't find account.");
  return "404";
}
```

----

<div style="padding-right: 8px;">
  <p style="text-align: left; font-size: 110%; font-weight: 700;">
    <img src="/stop-sign.jpg" style="float: left; vertical-align: middle; width: 80px; padding-right: 10px">Once you've completed the above steps,<br/>
    check in with the instructor to review your code.
  </p>
</div>

----  


## C. Account Summary

View a summary of all accounts.

### Steps

1. Create an HTML "template" named `all-accounts.html` in the `src/main/resources/templates`
   directory:

    ```HTML
    <!DOCTYPE html>
    <html lang="en" xmlns:th="http://www.thymeleaf.org" >
    <head>
      <meta charset="UTF-8">
      <title>Accounts</title>
    </head>
    <body>
    <table>
      <tr>
        <th>Account Name</th>
        <th>Balance</th>
      </tr>
      <tr>
        <td>Fun Stuff</td>
        <td>99</td>
      </tr>
    </table>
    </body>
    </html>
    ```

1. Add Thymeleaf text substitution (`th:each` and `th:text`) to the `<tr>` and `<td>` tags so that it pulls the content from the list of accounts.
   You will need to do something similar to this example, replacing **account** for **product**.
   
   >EXAMPLE: if we were working with a collection of `products`:
   >
   >    ```html
   >    <tr th:each="product : ${products}">
   >      <td th:text="${product.name}">Onions</td>
   >      <td th:text="${product.price}">2.41</td>
   >    </tr>
   >    ```
   >
   > In this example, Thymeleaf iterates over the "products" collection that it got from Spring's Model, and for each element, will assign it to the `product` variable.

1. Using the same `AccountWebController`, create a `@GetMapping()` method to `/account` that 
   displays all accounts. Remember these steps:

    * Add `Model model` as a parameter to the method
    * Get the list of `Account` domain objects from the `AccountRepository`
    * Iterate through the list, converting `Account` objects to `AccountResponse` objects
    * Use `model.addAttribute()` method to add the list of `AccountResponse` objects with the name `"accounts"`
    * Return a `String` with the name of the html template you created in the first step
      * Remember, this is the *view name*, without the `.html` suffix.

1. If you access `localhost:8080/account/` you should see all of the accounts as a two-column table.

----

> <img src="stop-sign.jpg" width="56" /> Once you've completed the above steps, you're done, so let the instructor know.

----
