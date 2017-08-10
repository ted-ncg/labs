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

   * In the above example, Thymeleaf will iterate over the "products" collection that it got from Spring's Model, and for each element, will assign it to the `product` variable.
   * You will need to do something similar, replacing *account* for *product*.

1. Verify that going to `http://localhost:8080/` and clicking on the **View all accounts** takes you to the page and shows the actual accounts in your AccountRepository.

### Linking to Specific Account Pages

**Goal:**
Use the `th:href` tag to create links to view a specific account page.

1. Update the account-view.html template to add an "href" link for the account name that will link to the details page for that account.

   * With HTML, a link looks like this: `<a href="/account">Bank</a>`. We want it to point to `/account/3` for the account with an ID of 3, but point to `/account/5` for an account with an ID of 5.
   * We'll need to make the "href" dynamic, which Thymeleaf does through its `th:href` tag.
     Documentation for that tag can be found here: http://www.thymeleaf.org/doc/articles/standardurlsyntax.html

2. Add a parameterized link, which will look like our templated path in our GetMapping.
   For example, to produce a link to a product page using its product ID, we would use the `@{}` expression, with an embedded `${}` variable expression like this:
   
   ```
   <a th:href="@{/product/{id}(id=${product.id})}>Product</a>
   ```
   
   This can look complicated, so let's look at the pieces, the `${product.id}` is the dynamic ID from the product and `(id=${...})` means assign it to `id`.
   The `/product/{id}` then gets the `{id}` replaced at runtime with the `id` variable.   
   
3. Verify that clicking on a link from the "all accounts" page takes you to the specific page for that account.
