## Create Account

### This page is at: `https://github.com/ted-ncg/labs/blob/master/12-web-mvc-create-account.md`

### Link to the Create Account Page

**Goal:** Create a link to a Create Account Form to create a new account and save it in the account repository.

1. Add the [CreateAccountWebIntegrationTest](https://github.com/ted-ncg/labs/blob/master/CreateAccountWebIntegrationTest.java) to your project and run the test. **It should fail.**

1. Add a link to the index.html page (that you created in `/src/main/resources/static`, from the previous lab) to link to `/create-account`.

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

1. Create a method (`createAccountForm()`) that maps to `/create-account` and returns the above html as a view.

1. Re-run the `CreateWebApplicationIntegrationTest`, it should now **pass**.
   If it doesn't don't move on until it's fixed!

1. Add the following test case to the `CreateWebApplicationIntegrationTest` class:

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

1. Run this new test, and it should **fail**. 

1. Add a `createAccount()` method to your web controller class that:

    * Has a `@PostMapping` annotation, mapping to `/create-account`
    * Takes a parameter `@ModelAttribute("accountName") String name` that will be the account name.
    * Creates a new `Account` with the given `name` and does a `save` to the account repository.
    * Redirects to the "all accounts" view

1. Run all the tests in `CreateWebApplicationIntegrationTest`, and they should all **pass**.

1. Open your browser and go to `localhost:8080/` and see if it works from the browser.

