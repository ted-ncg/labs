## Deposit Money

### This page is at: `https://github.com/ted-ncg/labs/blob/master/08-deposit-money.md`

----

### Link to the Deposit Money Page

**Goal:** Create a link to a Deposit Money Form to deposit money into an account.


1. Add a link to `/deposit/{id}` from the Account view page.

   * With static HTML, a link looks like this:
   
     `<a href="/deposit">Deposit</a>`
    
     We want the link to point to `/deposit/3` to take us to the Deposit page for the account with an ID of 3, and point to `/deposit/5` for an account with an ID of 5.
     
   * We need to make the "href" dynamic, which Thymeleaf does through its `th:href` tag.
     Documentation for that tag can be found here: http://www.thymeleaf.org/doc/articles/standardurlsyntax.html

   * To do this, we need to create a *parameterized* link, which will look similar to the templated path we used in our `GetMapping`.
   
     For example, to produce a link to a product page using its product ID, we would use the `@{}` expression, with an embedded `${}` variable expression like this:
   
     ```
     <a th:href="@{/product/{prodId}(prodId=${product.id})}>Product</a>
     ```
   
   This looks complicated, so let's break it down:
   
      1. The `${product.id}` is the dynamic ID from a product object
      
      1. The `(prodId=${product.id})` means assign the value of the product's ID to `prodId`.
      
      1. Finally, the `/product/{prodId}` then gets the `{prodId}` replaced at runtime with the `prodId` variable.
      
      1. So, if a product object's ID is 12, the `th:href="@{/product/{prodId}(prodId=${product.id})"` would be replaced by Thymeleaf with `href="/product/12"`. 
   


### Create the Deposit Money Page

1. Add a new HTML template, named `deposit.html` with a form that let's us add money to an account.

  ```html
  <!DOCTYPE html>
  <html lang="en" xmlns:th="http://www.thymeleaf.org" >
  <head>
    <meta charset="UTF-8">
    <title>Deposit Money</title>
    <style type="text/css">
      .btn {
        border-radius: 5px;
        border-color: #1dc45a;
        color: #ffffff;
        font-size: 18px;
        background: #179c53;
        padding: 10px 20px 10px 20px;
      }
      .btn:hover {
        background: #1dc45a;
      }
    </style>
  </head>
  <body>
  <h2>Deposit Money to <span th:text="${account.id}">17</span>:</h2>
  <p>Current Balance: $<span th:text="${account.balance}">100</span></p>
  <hr/>
  <h2>How much to deposit?</h2>
  <form action="#" th:action="@{/deposit}" th:object="${depositForm}" method="post">
    <input type="hidden" th:field="*{accountId}"/>
    <p>Amount: $<input th:field="*{amount}"/></p>
    <p><button class="btn">Deposit!</button></p>
  </form>
  </body>
  </html>
  ```
1. Create a `DepositForm` *POJO* (Plain Old Java Object) that has two properties, with getters and setters:
   * `accountId` (`long`) - ID of the account to deposit to
   * `amount` (`int`) - how much to deposit

1. Create a `@GetMapping` method in your web controller that will serve up the form:

   ```java
     @GetMapping("/deposit/{id}")
     public String depositGet(Model model,
                               @PathVariable("id") long id) {
       // put the Account object for the id into the model
       // create a new DepositForm and set its accountId
       return "deposit";
     }
   ```

1. Create a `@PostMapping` method for the `/deposit` path that will take the amount *from* the form and deposit it to the account.
   Then use the `redirect` command to send the browser back to the account view page using `"redirect:/account/{id}"`.

   ```java
     @PostMapping("/deposit")
     public String depositPost(@ModelAttribute DepositForm form) {
       // get the account ID from the form
       // execute the deposit on that account
       // save the account back to the repository
       return "redirect:/account/" + form.getAccountId();
     }
   ```

1. You should now be able to go to the account's page, click on the "deposit money" link, enter an amount, and see that the balance for that account has been increased.

