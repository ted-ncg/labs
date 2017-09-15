## Create Account

### This page is at: `https://github.com/ted-ncg/labs/blob/master/11-web-mvc-create-account.md`

----

### References

* Thymeleaf form tutorial: http://www.thymeleaf.org/doc/tutorials/3.0/thymeleafspring.html#creating-a-form


### Link to the Create Account Page

**Goal:** Create a link to a Create Account Form to create a new account and save it in the account repository.

1. Add a link on the index.html page (that you created in `/src/main/resources/static`) to link to `/create-account`.

1. Create an HTML template called `create-account.html` and put it in the `templates` directory.
   The body of the HTML file is as follows:
   
   ```html
    <body>
    <h1>New Account:</h1>
    <form action="/create-account" method="post">
      <p>Name: <input type="text" name="accountName"/></p>
      <p>
        <input type="submit" value="Create Account"/> 
        <input type="reset" value="Clear"/>
      </p>
    </form>
    </body>
   ```
1. Create a method (`createAccountForm()`) `@GetMapping` to `/create-account` that returns the above html as a view.

    * **Think!** Since this is returning a "view", what might you need to do to the `model`?

1. Add a createAccount() method to your web controller class that:

    * Has a `@PostMapping` annotation, mapping to `/create-account`
    * Takes a `@ModelAttribute("accountName") String name` parameter
    * Creates a new `Account` with the given `name` and does a `save` to the account repository.
    * Redirects to the "all accounts" view

1. Open your browser and go to `localhost:8080/` and see if it works!
