# POST to Create New Account

### This page: https://github.com/ted-ncg/labs/blob/master/08-rest-request-post.md

## Requirements

This lab will require you to do an HTTP `POST`, which you can't do in a browser,
so we need to use a tool like **curl** or **Postman**.

If you don't have these installed, make sure you install it before starting this lab.

## Goal

A POST API that will create new `Account` instances and are stored in the Account Repository.
You will be sending in JSON to create an account, like this:

  ```json
  {
    "initialBalance": 100,
    "accountName": "Video Games"
  }
  ```

----

### References

This reference of annotations might be helpful: http://engineering.pivotal.io/post/must-know-spring-boot-annotations-controllers/

----

### Latest Code

If you want to download the latest code that the instructor did in class, it can be found at:

```
https://bitbucket.org/tedmyoung/fostercity-201908-canteen
```

----

> <img src="stop-sign.jpg" width="56" /> Run *all* your tests to make sure everything is still working before continuing.

----

## Steps

### Create Request JavaBean

1. Create a **new** class, `AccountCreateRequest` that is a *JavaBean* and has the following two properties:

   * `initialBalance` as an `int`
   * `accountName` as a `String`

1. Generate *getters* and *setters* for these two properties.

### Accounts Have Names

1. Add a new member variable to the `Account` class called `name` which is a `String`

   * **NOTE:** Instead of adding a setter/getter, add query/command methods:
   
     * `name()` that returns its name
     * `changeNameTo(String newName)` that updates the name to the new value

1. Modify the `AccountDataLoader` class so that each account has a name, e.g.:

    ```java
      public void run(ApplicationArguments args) throws Exception {
        // create a couple of accounts with initial balance and names,
        // save each to the repository
        Account account1 = new Account();
        account1.deposit(10);
        account1.changeNameTo("Fun stuff");
        accountRepository.save(account1);
     
        Account account2 = new Account();
        account2.deposit(20);
        account2.changeNameTo("Necessities");
        accountRepository.save(account2);
      }
    ```

1. Modify the `AccountResponse` object to have a property 
   (a `String` variable and its getter & setter) for `name`.

1. Update `AccountApiControllerTest` to use names by updating the test to:

   ```java
   public void accountInfoReturnsAccountResponseWithSpecifiedId() throws Exception {
     AccountRepository accountRepository = new FakeAccountRepository();
     Account account = new Account();
     account.deposit(73);
     account.setId(123L);
     account.changeNameTo("Test");
     accountRepository.save(account);

     AccountApiController controller = new AccountApiController(accountRepository); 

     AccountResponse accountResponse = controller.accountInfo("123"); 

     assertThat(accountResponse.getBalance())
         .isEqualTo(73);
     assertThat(accountResponse.getName())
         .isEqualTo("Test");
   }   
   ```

1. This test should *fail*.

1. Open the `AccountApiController` class and in the `accountInfo` method,
   as part of transforming the domain object to the DTO, 
   copy the new `name` property from the `Account` to the `AccountResponse` 
   instance that is returned.

1. The above test should now *pass*.

### Add POST Method in the Controller

In this section, you'll create a *new* Account via an incoming POST request.

1. Add the following test method to the `AccountRestTest` class:

      ```java
      @Test
      public void postWithBalanceAndNameCreatesNewAccountInRepository() throws Exception {
        mockMvc.perform(post("/api/accounts")
                          .contentType(MediaType.APPLICATION_JSON_UTF8)
                          .content("{\"initialBalance\":32,\"accountName\":\"Video Games\"}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Video Games"))
               .andExpect(jsonPath("$.balance").value("32"));
      }
      ```

   Use this import for the `post()` method:
   
   ```java
   import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
   ``` 

1. Add a new method in the `AccountApiController` that is a `POST` mapping:

    ```java
      @PostMapping("/api/accounts")
      public AccountResponse createAccount(@RequestBody AccountCreateRequest request) {
      }
    ```

1. In this method, create a **new** `Account` object, initialized with the "initial balance" and "name" that you get from the incoming `request` parameter.

1. Save this new `Account` instance in the `AccountRepository`

1. Take the newly saved account and convert it into an `AccountReponse` object and return it.

### Try it out

* Run the `AccountRestTest`, which should pass.

* Using **Postman**, use the following JSON

    ```json
    {"initialBalance":13,"accountName":"Video Games"}
    ```

* Using **curl**, you can copy-and-paste the following terminal commands:

    **For Linux/Mac:**
    
    ```bash
    curl -v --noproxy "*" -d '{"initialBalance":13,"accountName":"Video Games"}' -H 'Content-Type: application/json' "localhost:8080/api/accounts"
    ```

    **For Windows:**
    
    ```
    curl -v --noproxy "*" -d "{\"initialBalance\":13,\"accountName\":\"Video Games\"}" -H "Content-Type: application/json" "localhost:8080/api/accounts"
    ```
