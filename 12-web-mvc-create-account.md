# Create Account

### This page: https://github.com/ted-ncg/labs/blob/master/12-web-mvc-create-account.md

## Goal
Create a link to a Create Account Form to create a new account and save it in the account repository.

## Link to the Create Account Page

1. Modify the index.html page (that you created in `/src/main/resources/static`, from the previous lab) to provide a link to a new endpoint: `/create-account`, e.g. add this to the `index.html` page;

    ```HTML
    <a href="/create-account">Create New Account</a>
    ```

## Add Create Account Page

1. Add the [CreateAccountWebIntegrationTest](https://github.com/ted-ncg/labs/blob/master/CreateAccountWebIntegrationTest.java) to your project and run the test. 

   * **It should fail.**

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

1. Create a method, `createAccountForm()`, that maps `GET` requests to `/create-account` and returns the above html as a view.

1. Re-run the `CreateAccountWebIntegrationTest`, it should now **pass**.
   If it doesn't don't move on until it's fixed!

1. Try it from the browser by going to `localhost:8080/create-account` and you should see the form.

## Process Create Account POST Request

1. Add the following test case to the `CreateAccountWebIntegrationTest` class:

    ```java
    @Test
    public void postToCreateAccountCreatesAccountAndRedirects() throws Exception {
      MvcResult mvcResult = mockMvc.perform(
          post("/create-account")
              .contentType(MediaType.APPLICATION_FORM_URLENCODED)
              .param("accountName", "Video Games")
          )
          .andExpect(status().is3xxRedirection())
          .andReturn();
      mockMvc.perform(get(mvcResult.getResponse().getRedirectedUrl()))
          .andExpect(status().isOk())
          .andExpect(view().name("all-accounts"));
    }
    ```

   Make sure to add the following imports for the above test code:
   
    ```java
    import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
    import org.springframework.test.web.servlet.MvcResult;
    import org.springframework.http.MediaType;
    ```

1. Run this new test, and it should **fail**. 

1. Add a `createAccount()` method to your web controller class that will:

    * Has a `@PostMapping` annotation, mapping to `/create-account`
    * Takes a parameter `@ModelAttribute("accountName") String name` that will be the account name.
    * Create a new `Account` instance with the incoming `name` 
    * Save that account to the account repository.
    * Redirect back to the "all accounts" view
        * **NOTE:** Instead of return the name of a _view_, you're returning a **URL**, e.g.:
        
          ```java
          return "redirect:/account";
          ```

        * For documentation on redirect, see https://docs.spring.io/spring/docs/4.3.18.RELEASE/spring-framework-reference/htmlsingle/#mvc-redirecting-redirect-prefix

1. Run all the tests in `CreateAccountWebIntegrationTest`, and they should all **pass**.

1. Open your browser and go to `localhost:8080/create-account` and see if it works from the browser.


----

> <img src="stop-sign.jpg" width="56" /> Once you've completed the above steps, do **not** move on until you have checked in with the instructor.

----

## Redirect to Account Details Page

1. Update the 2nd part of the `CreateAccountWebIntegrationTest` to be:

   ```java
    mockMvc.perform(get(mvcResult.getResponse().getRedirectedUrl()))
        .andExpect(status().isOk())
        .andExpect(view().name("account-view"))
        .andExpect(model().attributeExists("account"))
        .andExpect(model().attribute("account", instanceOf(AccountResponse.class)))
        .andExpect(model().attribute("account", hasProperty("name", is("Video Games"))));
   ```

   **Note:** You'll need to import the following matchers for the new methods above:
   
      ```java
      import static org.hamcrest.Matchers.hasProperty;
      import static org.hamcrest.Matchers.instanceOf;
      import static org.hamcrest.Matchers.is;
      import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
      ```

1. Run the test, it should **fail**.

1. Have the form submission (your `@PostMapping` method) use the "redirect:" String to redirect
   to the account view page for the new account.
   You'll need to use the newly created account's ID, 
   along with string concatenation to create a new redirect URL.

1. And it should now **pass**.

1. Try it from the browser by going to `localhost:8080/create-account` and you should see the form.
