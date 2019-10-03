# MVC View Account

### This page: https://github.com/ted-ncg/labs/blob/master/09-web-mvc-account-view.md

----

### References

* **Spring Web Controller**: https://docs.spring.io/spring/docs/5.1.8.RELEASE/spring-framework-reference/web.html#mvc-controller

* **Thymeleaf 3**: https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#using-and-displaying-variables

* **Test Annotations**: more details about how `@SpringBootTest`, etc. work:
    * https://spring.io/guides/gs/testing-web/
    * https://spring.io/blog/2016/04/15/testing-improvements-in-spring-boot-1-4
    * https://spring.io/blog/2016/08/30/custom-test-slice-with-spring-boot-1-4
 
----

## Goal: Create an "account view" page.

In this lab you will create a web app that will show account information for a single account.

## A. Return a Static Page

First you'll return a static version of the Account View page to make sure everything is annotated and configured properly.

1. Create a new controller class for the web pages called `AccountWebController`.

   ```java
   // ?? What annotation do you need on the class here ??
   public class AccountWebController {
     // your GET mapping method goes here
   }
   ```

1. Add a method that is mapped to a `GET` to `localhost:8080/account/{id}` (**NOTE** this is a *different* URL than from the API):

    ```java
        // ?? What annotation do you need for this method ??
        public String accountView(/* ?? What annotation goes here ?? */ String accountId) {
          return "account-view";
        }
    ```

1. Create a `templates` directory underneath the `/src/main/resources` directory.

1. Create a new HTML file (with the content below) named `account-view.html` 
   and put it in the `/src/main/resources/templates` directory:

   ```HTML
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

----

#### ** Do not go further until the above works! **

----

Add this [AccountViewWebIntegrationTest](https://github.com/ted-ncg/labs/blob/master/AccountViewWebIntegrationTest.java)
and run it, it should fail.
The next steps will do the work to make it pass.

----

## B. Templatize the Page

Instead of returning a static page, you will use Spring MVC to "fill in" parts of the page
with information that comes from the account entity.

1. "Templatize" the `<h1>Account</h1>` and the `<p>Balance</p>` elements so that it displays
   the information from the account by using the `th:text` attribute.

    * For example, if you wanted to templatize the 99 for the account number and have it be replaced with the actual account ID, you'd surround the 99 with a `span` tag like this:
    
        `<h1>Account: <span th:text="${account.id}">99</span></h1>`

    * Make sure to do the same for the `Balance` 

1. In the `accountView` method, add a new parameter, `Model model`, e.g.:

   ```java
   public String accountView(@PathVariable("id") String accountId, Model model)
   ```

1. Look up the Account in the AccountRepository, convert to an accountResponse, 
   and then add it into the `Model`. This is similar to what you did in the API-based lab.
   For example:

    ```java
    // lookup the account in the repository by the accountId
    // ...
    // convert to an accountResponse object
    // ...
    // add the accountResponse to the Model, e.g.:
    model.addAttribute("account", accountResponse);
    ```

1. Run the `AccountViewWebIntegrationTest` and it should now pass.
   If it doesn't, ensure that you're putting an `AccountResponse` object into the model and not an `Account` object.

1. Restart and test out the page and see what you find by going to `localhost:8080/account/1`.

----

#### ** Do not go further until the above works! **

----

## C. Replace ID With Name

1. Update the `account-view.html` template and **replace** showing 
   the account ID with showing the account's **name** (e.g., `account.name`).

1. Restart and test out the page and see what you find by going to `localhost:8080/account/0` 
   and then `localhost:8080/account/1`.

----

> <img src="stop-sign.jpg" width="56" /> Once you've completed the above steps, you're done, so let the instructor know.

----
