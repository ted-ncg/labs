## View Account

### This page: https://github.com/ted-ncg/labs/blob/master/09-web-mvc-account-view.md

### References

* Thymeleaf 3: http://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#using-and-displaying-variables

## Goal: Create an "account view" page.

### Return a Static Page

1. Create a new controller class for the web pages called `AccountWebController`.

1. Add a method that is mapped to a `GET` to `localhost:8080/account/{id}`:

    ```java
        @GetMapping(...)
        public String accountView(@PathVariable(...) String id,
                                  Model model) {
          return "account-view";
        }
    ```

1. Create a `templates` directory underneath the `/src/main/resources` directory. Then create an HTML file called `account-view.html` in the `templates` directory:

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

1. "Templatize" the `<h1>Account</h1>` and the `<p>Balance</p>` elements so that it displays the information from the account by using the `th:text` attribute.

    * For example, if you wanted to templatize the 99 for the account number and have it be replaced with the actual account ID, you'd surround the 99 with a `span` tag like this:
    
        `<h1>Account: <span th:text="${account.id}">99</span></h1>`

1. Look up the Account in the repo, convert to an accountResponse, and then add it into the `Model`.

    ```java
    // lookup the account in the repository by its ID
    ...
    // convert to an accountResponse object
    ...
    // add the accountResponse to the Model
    model.addAttribute("account", accountResponse);
    ```    
1. Restart and test out the page and see what you find by going to `localhost:8080/account/0`.
   **Note:** You may want to add some more sample data to your `AccountDataLoader` class.
                                    
