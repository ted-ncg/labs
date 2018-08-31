# Create Account - Advanced Form

### This page: https://github.com/ted-ncg/labs/blob/master/13-web-mvc-create-account-form.md

----

### References

* Thymeleaf form tutorial: http://www.thymeleaf.org/doc/tutorials/3.0/thymeleafspring.html#creating-a-form

----

## Goal

Allow the user to specify an initial deposit amount, and an overdraft limit, in addition to the name. We'd also like to pre-fill the form with a default deposit amount of $10 and overdraft limit of $100.

## Steps

1. Create a new `CreateForm` JavaBean class that has properties for `accountName`, `initialDeposit`, and `overdraftLimit`

1. Update the `create-account.html` to be a Thymeleaf *template*, by replacing the `form` section as follows:

   ```html
   <form action="#" th:action="@{/create-account}" th:object="${createForm}" method="post">
     <p>Name for Account: <input type="text" th:field="*{accountName}"/></p>
     <p>Initial Deposit: $<input type="text" th:field="*{initialDeposit}"/></p>
     <p>Overdraft Limit: $<input type="text" th:field="*{overdraftLimit}"/></p>
     <p><input type="submit" value="Create Account"/> <input type="reset" value="Clear"/></p>
   </form>
   ```

1. Modify the GET method in your web controller that is mapped to `/create-account` to do the following:
   * Instantiate a new `CreateForm` instance
   * Set the values for the instance to default to 10 for the initial deposit, empty for the account name, and 1000 for the overdraft limit.
   * Put the instance into the Spring `Model`, with the correct attribute name to match what's in the form.
   
1. Modify the `POST` method mapped to `/create-account` to:
   * Take in a `CreateForm` as a parameter (replacing the `ModelAttribute` parameter)
   * Create a new `Account` instance based on the information from the `createForm` JavaBean and save it in the repository
   * Make sure to redirect the user to the account view page for the newly created account

1. Try it out...

### Questions

> How might you write a test (or modify existing tests) to test the above in an automated way?

> Should you display the overdraft limit to the user? Why or why not?

----

## Bonus #1

1. Update the account view template to use the `th:style` that will conditionally display the account balance in red if the account is overdrawn, e.g.:

   ```html
   th:style="${condition} ? 'color:black' : 'color:red'"
   ``` 

   Will apply the color style `black` if the `condition` is `true`, otherwise will apply the color style of `red`. So your display of the balance will look something like:

   ```html
   <span th:text="${account.balance}"
         th:style="${account.overdrawn} ? 'color:#FF5733' : 'color:black' ">99
   </span>
   ```

   * You'll need to add an appropriate method to the `AccountResponse` (or `AccountView` if you switched to that) object so that `isOverdrawn()` returns `true` if the balance < 0, otherwise returns `false`. 
   
     > Where should "overdrawn" be calculated?
     
     > How about if "overdrawn" means that the balance is more negative than the overdraft limit?

1. Create some sample data in the `AccountDataLoader` that creates some accounts with a negative balance.

----

## Bonus #2

You can use the `@ModelAttribute` annotation on a "factory" method for instantiating the `CreateForm` object, like this:

  ```java
    @ModelAttribute("createForm")
    public CreateForm createForm() {
      CreateForm createForm = new CreateForm();
      createForm.setAccountName("");
      // more initialization here ...
      return createForm;
    }
  ```

You can now remove the initialization that you were doing in the create form `GET` method, and you'll no longer need *any* parameters passed into your `GET`-mapped method, e.g.:

  ```java
  @GetMapping("create-account")
  public String createForm()
  ...etc...
  ```
