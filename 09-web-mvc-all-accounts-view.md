# All Accounts View

## https://github.com/ted-ncg/labs/blob/master/09-web-mvc-all-accounts-view.md

### References

* Thymeleaf 3: http://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#using-and-displaying-variables

## Lab Instructions

1. Create an HTML "template" (name it `all-accounts.html') in the `resources/templates` folder :

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

1. Using the same `AccountWebController`, create a `@GetMapping()` method to `/account/` that displays all accounts.

    * Add a `Model model` as your parameter to the method
    * use the `model.addAttribute()` method to add the account list to the name `"accounts"`
    * Return a `String` with the name of the html template you created in the first step -- **without** the `.html` suffix.

1. If you hit `localhost:8080/account/` you should see all of the accounts.
