# Transfer Service API and Web Form

### This page: https://github.com/ted-ncg/labs/blob/master/16-transfer-money.md

## Part 1: Transfer Service

**Goal**: Implement (test-first!) a service that will transfer money from one account to another.

1. Create a JUnit (non-Spring) test class `AccountTransferServiceTest`

1. Write tests against a new class, `AccountTransferService`, where the transfer method signature is:

    ```java
    public void transfer(Account source,
                         Account destination,
                         int amount)    
    ```

1. Write tests (and then the code to make it pass) for the following:

   1. Transferring money from one account (the *source*) to another (the *destination*) works
   
   1. Attempting to transfer an amount <= 0, throws an `InvalidAmountException`
   
   1. Attempting to transfer more money than the *source* account has, throws an `InsufficientBalanceException`
   
   1. If either account is `null`, it should throw an `IllegalArgumentException`.

----

## Part 2: REST API for Transferring Money

**Goal**: Create a new API endpoint for transferring money between accounts.

1. Create a new `RestController` (e.g., `TransferMoneyApiController`) that has a method that is:
 
    * POST mapped to: `/api/transfers`
    * Takes a parameter: `TransferRequest`, which is a _JavaBean_ that has these three properties
       * `source`, the ID for the source account
       * `destination`, the ID for the destination account
       * `amount`, an `int` for the amount to transfer
    * Inside the method, use the `TransferService` that you created above (by injecting it into your controller) to transfer the money.
    * Return `200` if everything works, otherwise a `400` if any exceptions are thrown by the service.
    
2. Try it out using Postman, or from the command-line using `curl`, with the following JSON:

```json
{"source": 1, "destination": 2, "amount": 5}
```

----

## Part 3: UI For Transferring Money

Create a web form so users can transfer money between accounts.

1. Add a link to `/deposit/{id}` from the Account details **view** page (account-view.html).

   The link should point to `/deposit/3` to take you to the Deposit page for the account with an ID of 3, and point to `/deposit/5` for an account with an ID of 5.
     
   * You will make the "href" dynamic, similar to what you did in the "All Accounts Navigation" lab.

      * Remember, Thymeleaf does this through the `th:href` tag.
        Documentation for that tag can be found here: http://www.thymeleaf.org/doc/articles/standardurlsyntax.html

   * To do this, we need to create a *parameterized* link, which will look similar to the templated path we used in our `GetMapping`.
   
     As a reminder, to produce a link to a product page using its product ID, we would use the `@{}` expression, with an embedded `${}` variable expression like this:
   
       ```
       <a th:href="@{/product/{prodId}(prodId=${product.id})}>Product</a>
       ```
   
     As you saw previously, this breaks down to:
     
        1. The `${product.id}` is the dynamic ID from a `product` object
        
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

1. Create a `DepositForm` *JavaBean* that has two properties, with getters and setters:
   * `accountId` (`long`) - ID of the account to deposit into
   * `amount` (`int`) - how much to deposit

1. Create a `@GetMapping` method in the `AccountWebController` that will serve up the form:

   ```java
     @GetMapping("/deposit/{id}")
     public String depositGet(Model model,
                              @PathVariable("id") long id) {
       // 1: Find the account from the repository
       // 2: Convert to an AccountReponse
       // 3: Add the response object into the model
       // 4: Instantiate a DepositForm and set its accountId
       // 5: Add the deposit form into the model
       return "deposit";
     }
   ```

1. Create a `@PostMapping` method for the `/deposit` path that will take the amount *from* the form and deposit it to the account.
   Then use the `redirect` command to send the browser back to the account view page using `"redirect:/account/{id}"`.

   ```java
     @PostMapping("/deposit")
     public String depositPost(@ModelAttribute DepositForm form) {
       // 1: Get the account ID from the form
       // 2: Find the account in the repository
       // 3: execute the deposit on that account
       // 4: save the account back to the repository
       // 5: redirect to the account's detail view page
     }
   ```

1. You should now be able to go to the account's page, click on the "deposit money" link, enter an amount, and see that the balance for that account has been increased by that amount.
