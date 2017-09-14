## View Account

### This page: https://github.com/ted-ncg/labs/blob/master/09-web-mvc-account-view.md

### References

* Thymeleaf 3: http://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#using-and-displaying-variables

### Goal: Create an "account view" page.

1. Create a new controller class for the web pages called `AccountWebController`.

1. Add a GET method that is mapped to `localhost:8080/account/{id}`:

    ```java
        @GetMapping(...) // do the right thing here
        public String accountView(@PathVariable(...) String id,
                                  Model model) {
          // lookup the account in the repository
          return "account-view";
        }
    ```

1. Create a `templates` directory underneath the `resources` directory. Then create an HTML file called `account-view.html` in the templates directory:

   ```
    <!DOCTYPE html>
    <html lang="en" xmlns:th="http://www.thymeleaf.org" >
    <head>
      <meta charset="UTF-8">
      <title>Account Information</title>
    </head>
    <body>
    <h1>Account 99</h1>
    <p>Balance: $100</p>
    </body>
    </html>
   ```

1. Test out the static page above by going to `localhost:8080/account/0` to make sure you see the page.

### Templatize the Page

1. "Templatize" the `<h1>Account</h1>` and the `<p>Balance</p>` so that it displays the information from the account by using the `th:text` attribute.

    * For example, if you wanted to templatize the 99 for the account number and have it be replaced with the actual account ID, you'd do:
    
        `<h1>Account: <span th:text="${account.id}">99</span></h1>`

1. Test out the page and see what you find by going to `localhost:8080/account/0`.

1. Add the account response object into the `Model` and then restart and try out the application.

    * That is, add the AccountResponse object to the model via:
    
      `model.addAttribute("account", accountResponse);`
