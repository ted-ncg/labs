## View Account

### References

* Thymeleaf 3: http://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#using-and-displaying-variables

### Goal: Create an "account view" page.

1. Create a new controller method that is mapped to `localhost:8080/account/{id}`:

  ```
      @GetMapping(...) // do the right thing here
      public String accountView(@PathVariable(...) String id,
                                Model model) {
        // lookup the account in the repository
        model.addAttribute("account", account);
        return "account-view";
      }
  ```
  
1. Create an HTML file `account-view.html` in the templates directory

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

1. "Templatize" the `<h1>Account</h1>` and the `<p>Balance</p>` so that it displays the information from the account by using the `th:text` attribute.

