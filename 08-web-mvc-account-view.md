## View Account

### This page: https://github.com/ted-ncg/labs/blob/master/08-web-mvc-account-view.md

### References

* Spring Controllers: https://docs.spring.io/spring/docs/4.3.18.RELEASE/spring-framework-reference/htmlsingle/#mvc-controller

* Thymeleaf 3: https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#using-and-displaying-variables

## Goal: Create an "account view" page.

In this lab you will create a web app that will show account information for a single account.

## Return a Static Version of the Account View Page

First you'll 

1. Modify your `AccountRepository` so that the ID generator (the `AtomicLong`) has an initial value of 1 (instead of the default of 0), e.g.:

    `private final AtomicLong idGenerator = new AtomicLong(1L);`

   * **Question**: why would we want to do this?

1. Create a new controller class for the web pages called `AccountWebController`.

1. Add a method that is mapped to a `GET` to `localhost:8080/account/{id}`:

    ```java
        // ?? What annotation goes here ??
        public String accountView(/* ?? What annotation goes here ?? */ String accountId) {
          return "account-view";
        }
    ```

1. Create a `templates` directory underneath the `/src/main/resources` directory.

1. Create a new HTML file (with the content below) named `account-view.html` and put it in the `/src/main/resources/templates` directory:

   ```
    <!DOCTYPE html>
    <html lang="en" xmlns:th="http://www.thymeleaf.org" >
    <head>
      <meta charset="UTF-8">
      <title>Account Information</title>
    </head>
    <body>
    <h1>Account 99</h1>
    <p>Balance: $100</p>
    </body>
    </html>
   ```

1. Test out the static page above by going to `localhost:8080/account/1` to make sure you see the page.

** Do not go further until the above works! **

### Templatize the Page

Instead of returning a static page, you will use Spring MVC to "fill in" parts of the page with information that comes from the account entity.

1. "Templatize" the `<h1>Account</h1>` and the `<p>Balance</p>` elements so that it displays the information from the account by using the `th:text` attribute.

    * For example, if you wanted to templatize the 99 for the account number and have it be replaced with the actual account ID, you'd surround the 99 with a `span` tag like this:
    
        `<h1>Account: <span th:text="${account.id}">99</span></h1>`

1. In the `accountView` method, add a new parameter, `Model model`, e.g.:

   ```java
   public String accountView(@PathVariable("id") String accountId, Model model)
   ```

1. Look up the Account in the AccountRepository, convert to an accountResponse, and then add it into the `Model`. This is similar to what you did in the API-based lab. For example:

    ```java
    // lookup the account in the repository by its ID
    // ...
    // convert to an accountResponse object
    // ...
    // add the accountResponse to the Model
    model.addAttribute("account", accountResponse);
    ```    

1. Restart and test out the page and see what you find by going to `localhost:8080/account/1`.

** Do not go further until the above works! **

### Accounts Have Names

1. Add a new member variable to the `Account` called `name` which is a `String`

   * Instead of adding a setter/getter, add methods:
   
     * `name()` that returns its name
     * `changeNameTo(String newName)` that update the name to the new value

1. Modify the `AccountDataLoader` class so that each account has a name, e.g.:

    ```java
      public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.deposit(12);
        account.changeNameTo("Luxuries");
        accountRepository.save(account);
        account = new Account();
        account.deposit(25);
        account.changeNameTo("Necessities");
        accountRepository.save(account);
        ...
      }
    ```

1. Modify the `AccountResponse` object to have a property (the variable and its getter & setter) for `name`.

1. Update the `account-view.html` template and **replace** showing the account's ID with showing the account's **name** (e.g., `account.name`).

1. Restart and test out the page and see what you find by going to `localhost:8080/account/1` and then `localhost:8080/account/2`.
