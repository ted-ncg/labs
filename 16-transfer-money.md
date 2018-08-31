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

1. Write tests first (and then the code to make it pass) for the following:

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
    
2. Try it out using Postman (to the URL `localhost:8080/api/transfers`) with the following JSON:

    ```json
    {"source": 1, "destination": 2, "amount": 5}
    ```
    
   You can also use curl:
   
   **curl for Linux/Mac:**
    
    ```bash
    curl -v --noproxy "*" -d '{"source": 1, "destination": 2, "amount": 5}' -H 'Content-Type: application/json' "localhost:8080/api/transfers"
    ```
    
   **curl for Windows:**
    
    ```
    curl -v --noproxy "*" -d "{\"source\": 1, \"destination\": 2, \"amount\": 5}" -H "Content-Type: application/json" "localhost:8080/api/transfers"
    ```
----

## Part 3: UI For Transferring Money

**Goal**: Create a web form so users can transfer money between accounts.

### Link to the Transfer Page

1. On the Account Details **view** page (account-view.html), add a link to `/transfer-from/{id}`, where the `id` will be the id of the account you're viewing.

   > e.g.: The link should point to `/transfer-from/3` to take you to the Transfer page for the account with an ID of 3.
   > The end result `href` might look like: `<a href="/transfer-from/3">Transfer Money from this account</a>`
     
   * You will make the "href" dynamic, similar to what you did in the "All Accounts Navigation" lab.

     As a reminder, to produce a link to a product page using its product ID, we would use the `@{}` expression, with an embedded `${}` variable expression like this:
   
       ```
       <a th:href="@{/product/{prodId}(prodId=${product.id})}>Product</a>
       ```
   
     As you saw previously, this breaks down to:
     
        1. The `${product.id}` is the dynamic ID from a `product` object
        
        1. The `(prodId=${product.id})` means assign the value of the product's ID to `prodId`.
        
        1. Finally, the `/product/{prodId}` then gets the `{prodId}` replaced at runtime with the `prodId` variable.
        
        1. So, if a product object's ID is 12, the `th:href="@{/product/{prodId}(prodId=${product.id})"` would be replaced by Thymeleaf with `href="/product/12"`. 

----   

### Create the Transfer Money Page

1. Add a new HTML template, named `transfer.html` with a form that let's us transfer money to an account from another account.

  ```html
  <!DOCTYPE html>
  <html lang="en" xmlns:th="http://www.thymeleaf.org" >
  <head>
    <meta charset="UTF-8">
    <title>Transfer Money</title>
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
  <h2>Transfer Money from <span th:text="${account.name}">Allowance</span>:</h2>
  <p>Available Balance: $<span th:text="${account.balance}">50</span></p>
  <hr/>
  <form action="#" th:action="@{/transfer}" th:object="${transferForm}" method="post">
    <input type="hidden" th:field="*{sourceAccountId}"/>
    <p>To Account ID: <input type="number" th:field="*{targetAccountId}"/></p>
    <p>Amount: $<input th:field="*{amount}"/></p>
    <p><button class="btn">Transfer Money</button></p>
  </form>
  </body>
  </html>
  ```

### Create GetMapping for the Transfer Money Page

1. Create a `TransferForm` *JavaBean* that has three properties, with getters and setters:
   * `sourceAccountId` (`long`) - ID of the account to transfer money from
   * `targetAccountId` (`long`) - ID of the account to deposit into
   * `amount` (`int`) - how much to transfer

1. Create a `@GetMapping` method in the `AccountWebController` that will serve up the form:

   ```java
     @GetMapping("/transfer-from/{id}")
     public String transferFrom(Model model,
                                @PathVariable("id") long id) {
       // 1: Find the account from the repository using the id
       // 2: Convert to an AccountReponse
       // 3: Add the response object into the model with the name "account"
       // 4: Instantiate a TransferForm object
       // 5: Set the `sourceAccountId` to the "from" account ID
       // 6: Add the transferForm into the model with the name "transferForm"
       return "transfer";
     }
   ```

### Execute the Transfer Money Form Submission

1. Create a `@PostMapping` method for the `/transfer` path that will take the amount *from* the form and transfer it between the two accounts.

   Then use the `redirect` command to send the browser back to the account view page using `"redirect:/account/{id}"`.
   
   **NOTE:** You will be using the `TransferService` to do the transfer, which means you will need to *inject* the TransferService into the AccountWebController.

   ```java
     @PostMapping("/transfer")
     public String transfer(@ModelAttribute TransferForm form) {
       // 1: Get both the sourceAccountId and targetAccountId from the form
       // 2: Using the TransferService, transfer the amount from the form
       //    between the two accounts
       // 3: redirect to the target account's detail view page
     }
   ```

1. You should now be able to go to the account's page, click on the "transfer money" link, enter an amount, and see money transferred to the target account.
