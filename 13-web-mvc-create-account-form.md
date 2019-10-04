# Create Account - Advanced Form

### This page: https://github.com/ted-ncg/labs/blob/master/13-web-mvc-create-account-form.md

In this lab we'll use a JavaBean object (DTO) to represent the form instead of a single attribute.

----

### References

**Thymeleaf form tutorial**: http://www.thymeleaf.org/doc/tutorials/3.0/thymeleafspring.html#creating-a-form

----

## Goal

Allow the user to specify an initial deposit amount, in addition to the name, when creating an account.
We'd also like to pre-fill the form with a default deposit amount of $100.

----

## Update Form HTML to JavaBean (DTO)

1. Create a new `CreateForm` DTO class that has properties for `accountName` and `initialDeposit`.

    > **Watch out for correct spelling of Properties!**

1. Update the `create-account.html` to be a Thymeleaf *template*, by replacing the `form` section as follows:

   ```html
   <form action="#" th:action="@{/create-account}" th:object="${createForm}" method="post">
     <p>Name for Account: <input type="text" th:field="*{accountName}"/></p>
     <p>Initial Deposit: $<input type="text" th:field="*{initialDeposit}"/></p>
     <p><input type="submit" value="Create Account"/> <input type="reset" value="Clear"/></p>
   </form>
   ```

   Make sure the HTML file has this declaration at the top:
   
   ```html
   <html lang="en" xmlns:th="http://www.thymeleaf.org">
   ```

## Modify Web Controller GET

Open the web controller class and modify the method that is "GET" mapped to `/create-account` to do the following:

1. Instantiate a new `CreateForm` instance

1. Set the initial deposit property to 100

1. Set the account name property to an empty string

1. Put the instance into the Spring `Model`, with the correct attribute name to match what's in the form: `"createForm"`.

## Modify Web Controller POST

Modify the `POST` method mapped to `/create-account` to:

1. Take in a `CreateForm` instance as a parameter (replace the `ModelAttribute` parameter)

1. Create a new `Account` instance based on the information from the `createForm` JavaBean and save it in the repository

1. Make sure to continue to **redirect** the user to the account details view page for the newly created account.

## Try it out...

Go to the home page or directly to `localhost:8080/create-account` and see if the form works properly.

----

## Questions

> How might you write a test (or modify existing tests) to test the above in an automated way?

----

> <img src="stop-sign.jpg" width="56" /> Once you've completed the above steps, you're done, so let the instructor know.

----
