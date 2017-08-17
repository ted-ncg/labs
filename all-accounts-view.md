# All Accounts View

1. Create an HTML "template":

    ```
  <!DOCTYPE html>
  <html lang="en" xmlns:th="http://www.thymeleaf.org" >
  <head>
    <meta charset="UTF-8">
    <title>Accounts</title>
  </head>
  <body>
  <ul>
    <li>Account ID:<span>10</span></li>
    <li>Balance: $<span>99</span></li>
  </ul>
  </body>
  </html>
    ```
    
1. Create a WebController class annotated with `@Controller`

1. Create a `@GetMapping()` to `/account/` that displays all accounts.

    * Add a `Model model` as your parameter
    * Return a `String` with the name of the html template you created in the first step.

1. If you hit `localhost:8080/account/` you should see all of the accounts.
