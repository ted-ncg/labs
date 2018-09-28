# Spend Some Money

### This lab: https://github.com/ted-ncg/labs/blob/master/19-spend-money-mvc-form.md

----

### Link to the Spend Money Page
1. Add a link to `/withdraw/{id}` from the Account view page.
   The raw HTML would be:
   
   ```html
   <br/>
   Want to: <a href="#">Spend Money?</a>
   ```
   You will need to add the appropriate `th` tag using what you've learned previously about `href`s.

### Create the Spend Money Page

1. Add a new HTML page, named `withdraw.html` with a form that let's us spend (withdraw) money from an account.

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

1. Create a `WithdrawForm` *POJO* (Plain Old Java Object) that has two properties, with getters and setters:
   * `accountId` (`long`) - ID of the account to withdraw from
   * `amount` (`int`) - how much to spend

1. Create a `@GetMapping` method in your web controller that will serve up the form:

   ```java
     @GetMapping("/withdraw/{id}")
     public String withdrawGet(Model model,
                               @PathVariable("id") long id) {
       // put the Account into the model
       // create a new WithdrawForm and set its accountId
       return "withdraw";
     }
   ```

1. Create a `@PostMapping` method for the `/withdraw` path that will take the amount *from* the form and withdraw it from the account using the `AccountService` and use the `redirect` to take the user back to the account view page using `"redirect:/account/{id}"`.

   ```java
     @PostMapping("/withdraw")
     public String withdrawPost(@ModelAttribute WithdrawForm form) {
       // get the account ID from the form
       // execute the withdraw on that account via the service
       return "redirect:/account/" + form.getAccountId();
     }
   ```

1. You should now be able to go to the home page, select an account, click on the "spend money" link, enter an amount, and see that the balance for that account has been reduced.

----

> <img src="stop-sign.jpg" width="56" /> Once you've completed the above steps, you're done, so let the instructor know.

----
