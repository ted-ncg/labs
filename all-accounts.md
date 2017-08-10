## All Accounts Navigation

### Parameterize Account View Page

1. Update the Account View method to take the account ID on the path. For example

   ```
   http://localhost:8080/account/2
   ```

   Would display the details for the account with ID of 2.

### Create New Root Page

1. Create an HTML page named `index.html` and place it in the `/src/main/resources/static` directory:
    ```html
    <!DOCTYPE html>
    <html lang="en">
    <head>
      <meta charset="UTF-8">
      <title>Canteen</title>
    </head>
    <body>
    <h1>Welcome to the Canteen.</h1>
    <br/>
    <div><a href="/accounts">View all accounts</a></div>
    </body>
    </html>
    ```
   ### Note
   * If you have an `index.html` file in `resources/templates`, delete it.
   * If you have a `@GetMapping` method for `"/"` in a controller, remove that method. 

1. Verify that going to `http://localhost:8080/` returns the above page.

### Create List All Accounts Page

1. Create an HTML page named `accounts.html`.
   This page will show all accounts in the `AccountRepository` formatted in an HTML table. For example, this is a page that displays products:
   ```
   <!DOCTYPE html>
   <html lang="en" xmlns:th="http://www.thymeleaf.org" >
   <head>
     <meta charset="UTF-8">
     <title>Product List</title>
   </head>
   <body>
   <h2>Products:</h2>
   <table>
     <tr>
       <th>Name</th>
       <th>Price</th>
     </tr>
     <tr>
       <td>Onions</td>
       <td>2.41</td>
     </tr>
   </table>
   </body>
   </html>
   ```

1. Create a `GET` method (e.g., `viewAllAccounts(Model model)`), mapped to `/accounts`, that displays the accounts.html template.

1. Verify that going to `http://localhost:8080/` and clicking on the **View all accounts** link takes you to the accounts page.

### Add Dynamic Data to the Template

1. Back in your `WebController`, use `findAll()` from `AccountRepository` to add the list of accounts to the model with the name `"accounts"`.

1. Update the accounts.html file and add Thymeleaf text substitution (`th:each` and `th:text`) to the `<tr>` and `<td>` tags so that it pulls the content from the list of accounts.
   For example, this is how to do it with the products page mentioned above:
   ```
   <tr>
     <th>Name</th>
     <th>Price</th>
   </tr>
   <tr th:each="product : ${products}">
     <td th:text="${product.name}">Onions</td>
     <td th:text="${product.price}">2.41</td>
   </tr>
   ```

1. Verify that going to `http://localhost:8080/` and clicking on the **View all accounts** takes you to the page and shows the actual accounts in your AccountRepository.
