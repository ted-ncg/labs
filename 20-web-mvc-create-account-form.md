## Create Account - Advanced Form

### This page is at: `https://github.com/ted-ncg/labs/blob/master/20-web-mvc-create-account-form.md`

### References

* Thymeleaf form tutorial: http://www.thymeleaf.org/doc/tutorials/3.0/thymeleafspring.html#creating-a-form

----

**Goal:** We'd like to allow the user to specify an initial deposit amount in addition to the name. Then we want to send the user to the details page for the new account. We'd also like to pre-fill the form with a default deposit amount of $10.

1. Create a `createForm` POJO class that has properties for `accountName` and `initialDeposit`

1. Update the create-account.html to be a template, by replace the `form` section as follows:

   ```html
   <form action="#" th:action="@{/create-account}" th:object="${createForm}" method="post">
     <p>Name for Account: <input type="text" th:field="*{accountName}"/></p>
     <p>Initial Deposit: $<input type="text" th:field="*{initialDeposit}"/></p>
     <p><input type="submit" value="Create Account"/> <input type="reset" value="Clear"/></p>
   </form>
   ```

1. Modify the GET method in your web controller that is mapped to `/create-account` to:
   * Instantiate a `CreateForm` POJO instance
   * Set the values for the instance to default to $10 for the initial deposit and empty for the account name.
   * Put the instance into the Spring `Model`, with the correct attribute name
   
1. Modify the `POST` method mapped to `/create-account` to:
   * Take in a `CreateForm` as a parameter
   * Create a new `Account` instance based on the information from the POJO and save it in the repository
   * Redirect the user to the account view page for the newly created account

1. Copy the [AccountWebIntegrationTest.java](https://github.com/ted-ncg/labs/blob/master/AccountWebIntegrationTest.java) into your project and run it -- it should pass.
