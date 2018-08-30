# POST to Create New Account

### This page: https://github.com/ted-ncg/labs/blob/master/07a-rest-request-post.md

## Goal

Create a POST API to create new `Account`s that are stored in the Account repository.
You will be sending in JSON to create an account, like this:

  ```json
  {
    "initialBalance": 100,
    "accountName": "Video Games"
  }
  ```

### References

This reference of annotations might be helpful: http://engineering.pivotal.io/post/must-know-spring-boot-annotations-controllers/

## Run Tests

Remember: Run *all* your tests to make sure everything is still working.

## Steps

### Create Request JavaBean

1. Create a **new** class, `AccountCreateRequest` that is a *JavaBean* and has the following two properties:

   1. `initialBalance` as an `int`
   1. `accountName` as a `String`

2. Write or generate *getters* and *setters* for the two properties.

### Add POST Method in the Controller

1. Add a new method in the `AccountApiController` that is a `POST` mapping:

    ```java
      @PostMapping("/api/accounts")
      public AccountResponse createAccount(@RequestBody AccountCreateRequest request) {
      }
    ```

    * Save that account in the account repository
    * Return the new account via the response object

1. In this new method, create a **new** `Account` object, initialized with an initial balance that you get from the `request` parameter.

1. Save this new `Account` instance in the `AccountRepository`

1. Take the newly saved account and convert it into an `AccountReponse` object

### Try it out

Using `curl`, or `Postman`, do a POST with the following JSON data

```json
{"initialBalance":10,"accountName":"Video Games"}
```

For Linux/Mac:

```bash
curl -v --noproxy "*" -d '{"initialBalance":10,"accountName":"Video Games"}' -H 'Content-Type: application/json' "localhost:8080/api/accounts"
```

For Windows:

```
curl -v --noproxy "*" -d "{\"initialBalance\":10,\"accountName\":\"Video Games\"}" -H "Content-Type: application/json" "localhost:8080/api/accounts"
```
