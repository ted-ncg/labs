## Spend Some Money

### Link to the Spend Money Page
1. Add a link to `/withdraw/{id}` from the Account view page.
   The raw HTML is:
   
   ```html
   <br/>
   Want to: <a href="#">Spend Money?</a>
   ```
   You will need to add the appropriate `th` tag.

### Create the Spend Money Page

1. Add a new HTML page, named `withdraw.html` with a form that let's us spend (withdraw) money.

  ```html
  <!DOCTYPE html>
  <html lang="en" xmlns:th="http://www.thymeleaf.org" >
  <head>
    <meta charset="UTF-8">
    <title>Spend Money</title>
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
  <h2>Spend Money from <span th:text="${account.name}">AccountName</span>:</h2>
  <p>Available Balance: <span th:text="${account.balance}">100</span></p>
  <hr/>
  <h2>How much to spend?</h2>
  <form action="#" th:action="@{/withdraw}" th:object="${withdrawForm}" method="post">
    <input type="hidden" th:field="*{accountId}"/>
    <p>Amount: $<input th:field="*{amount}"/></p>
    <p><button class="btn">Spend!</button></p>
  </form>
  </body>
  </html>
  ```
1. Create a WithdrawForm "POJO" (Plain Old Java Object) that has two properties, with getters and setters:
   * `accountId` (`long`) - ID of the account to withdraw from
   * `amount` (`int`) - how much to spend

1. Create a `@GetMapping` method that will serve up the form.

   ```java
     @GetMapping("/withdraw/{id}")
     public String withdrawGet(Model model,
                               @PathVariable("id") long id) {
       // put the Account into the model
       // create a new WithdrawForm and set its accountId
       return "withdraw";
     }
   ```

1. Create a `@PostMapping` method for the `/withdraw/{id}` path that will take the amount and withdraw it from the current account and **redirect** back to the account view page using `"redirect:/account/{id}"`.

   ```java
     @PostMapping("/withdraw")
     public String withdrawPost(@ModelAttribute WithdrawForm form) {
       // get the account ID from the form
       // execute the withdraw on that account via the service
       return "redirect:/account/" + form.getAccountId();
     }
   ```
