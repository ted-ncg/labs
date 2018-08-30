# All Accounts View

### This page: https://github.com/ted-ncg/labs/blob/master/09-web-mvc-all-accounts-view.md

## Goal

In this lab you'll add some error handling to the previous lab so no more `NullPointerException`s.
You'll then provide a "home" page for accounts that displays all the accounts in the system.

### References

* Thymeleaf 3: http://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#using-and-displaying-variables

## First, Some Tests

Add this [AccountViewWebIntegrationTest](https://github.com/ted-ncg/labs/blob/master/AccountViewWebIntegrationTest.java) and run it and make sure it passes.

  * If it doesn't, ensure that you're putting an `AccountResponse` object into the model and not an `Account`.

**Don't move on until this test passes**

----

## Handle Not Found

Add error handling when an account isn't found.

There are a number of ways to handle problems, we're going to use the ResponseStatus annotation on a custom Exception to indicate that there's a problem.

### Steps

  * Let's add a test that will fail, and then we'll make it pass.
    Add this test to the `AccountViewWebIntegrationTest` class, run it and it should fail.
  
    ```java
      @Test
      public void getOfNonExistentAccountReturns404() throws Exception {
        mockMvc.perform(get("/account/9999"))
               .andExpect(status().isNotFound());
      }
    ```

  * Inside of your `AccountWebController`, add code to the `accountView` method so that if the `findOne` method from the repository returns `null`, you will throw a special exception class that you'll create.
    Create an exception like this:
  
    ```java
    import org.springframework.http.HttpStatus;
    import org.springframework.web.bind.annotation.ResponseStatus;
    
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No account with that ID was found.")
    public class NoSuchAccountException extends RuntimeException {
      
    }
    ```

  * When you get null from the repository, throw an instance of the above exception.
  
  * Try running the `AccountViewWebIntegrationTest` tests now.
  
  * Try it out from the browser.

----

## Account Summary

View a summary of all accounts.

### Steps

1. Create an HTML "template" (name it `all-accounts.html') in the `resources/templates` folder:

    ```
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
          <td>Luxuries</td>
          <td>99</td>
        </tr>
      </table>
      </body>
      </html>
    ```

1. Add Thymeleaf text substitution (`th:each` and `th:text`) to the `<tr>` and `<td>` tags so that it pulls the content from the list of accounts.
   For example, if we were working with a collection of `products`:

       <tr th:each="product : ${products}">
         <td th:text="${product.name}">Onions</td>
         <td th:text="${product.price}">2.41</td>
       </tr>

   * In the above example, Thymeleaf will iterate over the "products" collection that it got from Spring's Model, and for each element, will assign it to the `product` variable.

   * You will need to do something similar, replacing *account* for *product*.

1. Using the same `AccountWebController`, create a `@GetMapping()` method to `/account/` that displays all accounts. Remember these steps:

    * Add a `Model model` as your parameter to the method
    * use the `model.addAttribute()` method to add the account list to the name `"accounts"`
    * Return a `String` with the name of the html template you created in the first step -- **without** the `.html` suffix.

1. If you access `localhost:8080/account/` you should see all of the accounts as a two-column table.
