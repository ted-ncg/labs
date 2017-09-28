## All Accounts Navigation

## https://github.com/ted-ncg/labs/blob/master/10-web-mvc-accounts-navigation.md

### References

* Thymeleaf URL (href) Syntax: http://www.thymeleaf.org/doc/articles/standardurlsyntax.html

* Thymeleaf variable replacement: http://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#using-and-displaying-variables

### Create a Root (Home) Page

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
    <div><a href="/account">View all accounts</a></div>
    </body>
    </html>
    ```

1. Verify that going to `http://localhost:8080/` returns the above page.

### Linking to Specific Account Pages

**Goal:**
Use the `th:href` tag to create links to view a specific account page.

1. Update the all-accounts.html template to add a link (an "anchor" or "href") around the account **name** that will link to the details (account-view) page for that account.

   * With normal static HTML, a link looks like this: `<a href="/account">Bank</a>`. We want to create a link that will point to `/account/3` for an account with an ID of 3, and point to `/account/5` for an account with an ID of 5.
   * We'll need to make the "href" dynamic, which Thymeleaf does through its `th:href` tag.
     Documentation for that tag can be found here: http://www.thymeleaf.org/doc/articles/standardurlsyntax.html

1. Add a parameterized link, which will look like our templated path in our `GetMapping`.
   For example, to produce a link to a product page using its product ID, we would use the `@{}` expression, with an embedded `${}` variable expression like this:
   
   ```
   <a th:href="@{/product/{id}(id=${product.id})}">Product</a>
   ```
   
   This can look complicated, so let's look at the pieces, the `${product.id}` is the dynamic ID from the product and `(id=${...})` means assign it to `id`.
   The `/product/{id}` then gets the `{id}` replaced at runtime with the `id` variable.
   If `${product.id}` was 2, then the final output from Thymeleaf is `/product/2` and the href would look like this:
   
      `<a href="/product/2">Product</a>`
   
1. Since we want each account name cell to have both the name of the account and a link, we'll need to pull the `th:text` out of the `<td>` tag and into a `<span>`.

   The resulting HTML that we want to be generated is:

    `<td><a href="/account/3"><span>Luxuries</span></a></td>`
   
   Use the concepts you've learned to translate the above into Thymeleaf attributes.
   
1. Verify that clicking on a link from the "all accounts" page takes you to the specific page for that account.

## Bonus

1. You can use the "pre-processing" syntax to generate the href, which for this particular use case is easier:

    `th:href="@{/account/__${account.id}__}"`
    
   In case you're interested, the documentation for the pre-processing syntax is here: http://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#preprocessing