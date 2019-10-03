# All Accounts Navigation

### This page: https://github.com/ted-ncg/labs/blob/master/11-web-mvc-accounts-navigation.md

## Goal

Now that you've displayed all the accounts, let's add links so you can view 
the details of the account.

----

### References

* Thymeleaf URL (href) Syntax: http://www.thymeleaf.org/doc/articles/standardurlsyntax.html

* Thymeleaf variable replacement: http://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#using-and-displaying-variables

----

## Create a Root (Home) Page

1. Copy the [WebIntegrationTest](WebIntegrationTest.java) class to your test path and run it.

   * **It should fail.**

1. Create an HTML page named `index.html` and place it in the `/src/main/resources/static` 
   directory:

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

1. Run the test from above and it should **pass**.

1. Also verify it by going to `http://localhost:8080/`.

## Linking to Specific Account Pages

### Goal

Use the `th:href` tag to create links to view a specific account page.

### Steps

1. Update the `all-accounts.html` template to add a link (an "anchor" or "href") around the account **name** that will link to the details (account-view) page for that account.

   * With normal static HTML, a link looks like this: `<a href="/account">Bank</a>`. We want to create a link that will point to `/account/3` for an account with an ID of 3, and point to `/account/5` for an account with an ID of 5.
   * We'll need to make the "href" dynamic, which Thymeleaf does through its `th:href` tag.
     Documentation for that tag can be found here: http://www.thymeleaf.org/doc/articles/standardurlsyntax.html

1. Add a parameterized link, using the Thymeleaf `th:href` syntax (details [here](http://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#preprocessing)):

    ```html
    <a th:href="@{/product/__${product.id}__}">Product</a>`
    ```

   At runtime, Thymeleaf will replace `__${product.id}__` with the ID of the *product* taken from the model.
   For example, if `${product.id}` was 2, then the final output from Thymeleaf is:
   
   ```html
   <a href="/product/2">Product</a>`
   ```
   
1. Since we want each account name table cell to have both the name of the account and a link, 
   we'll need to pull the `th:text` out of the `<td>` tag and into a `<span>`.

   The resulting HTML that we want to be generated is:

      `<td><a href="/account/3"><span>Luxuries</span></a></td>`
   
   Use the concepts you've learned to translate the above into Thymeleaf attributes.
   
1. Verify that clicking on a link from the "all accounts" page takes you to the specific page for that account.


----

> <img src="stop-sign.jpg" width="56" /> Once you've completed the above steps, you're done, so let the instructor know.

----
