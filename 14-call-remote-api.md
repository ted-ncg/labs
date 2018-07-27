## Calling Remote *Restful* APIs

### This page: https://github.com/ted-ncg/labs/blob/master/14-call-remote-api.md

## Reference Docs

* Spring RestTemplate docs: https://docs.spring.io/spring/docs/4.3.16.RELEASE/spring-framework-reference/html/remoting.html#rest-resttemplate

* Spring Boot - testing Rest Clients: https://docs.spring.io/spring-boot/docs/1.5.14.RELEASE/reference/html/boot-features-testing.html#boot-features-testing-spring-boot-applications-testing-autoconfigured-rest-client

## Preparation

From within IDEA, you'll need to set up the proxy information so you can reach out to APIs on the internet.

* Add proxy information to your Run Configurations as **VM** Options:

   * Go to the `Run` menu, select `Edit Configurations...`
   * Look for `CanteenApplication` on the left side, under the `Spring Boot` section
   * Fill in the following in the `VM Options` field:

     `-Dhttp.proxyHost=userproxy.visa.com -Dhttp.proxyPort=80`

----

## The Lab

### Goal

Convert the account balance from USD ($) to GBP (£) and display it on the account view page.

### Change the View

1. Create a new class: `CurrencyService` that has a method:

    ```java
    int convertToGbp(int amount)
    ```

1. Create a _fake_ version of this service that simply returns `123`. We'll write the actual implementation later.

1. Inject (autowire) the CurrencyService into your Web (Account) Controller.

   > **(??)** What will you need to do to properly autowire the service?
   >
   > **(??)** What annotations are necessary?

1. Update the `AccountResponse` object so it has a property for the GBP balance.

1. When transforming the `Account` to an `AccountReponse` (in `accountView()`), use the `CurrencyService` to convert the balance and store that into the `AccountResponse`.

1. Update the Account View HTML page to display both USD and GBP on the page, like this:
     ```
     USD Balance: $10
     GBP Balance: £123
     ```

> Remember, the converted GBP value is faked, so will always be 123.


**Do not proceed further until you have the account view page displaying both USD and GBP balance**

----

### Use the Real Currency Conversion Service

Now you will implement the `CurrencyService` so it will call out to the remote API.

You will write code in `convertToGbp()` to use the `RestTemplate` Spring class and a JavaBean (described below) that will call out to the currency converter at `http://jitterted-currency-conversion.herokuapp.com/convert`.

1. Create a JavaBean `ConvertedCurrency` that represents the returned JSON called `ConvertedCurrency`.
   The *properties* for your JavaBean must *match* the JSON names, i.e., it will have 2 properties for the currency and the converted result.
   
   * The JSON returned from this API looks like this:
   
     ```json
     {
       "currency": "GBP",
       "converted": 71.00
     }
     ```

1. Instantiate a `RestTemplate` that you'll use

1. Create a URI Template string for the remote API.

   * It will send three *query* parameters:
       * `from` - the source currency, e.g., `USD`
       * `to` - the converted currency, e.g., `GBP`
       * `amount` - the amount to convert, e.g., 10
   * **Example:**
       * To convert $100 to GBP, the URL would look like this: `http://jitterted-currency-conversion.herokuapp.com/convert?from=USD&to=GBP&amount=100`

   > Think about what parts of the string need to become template variables.
   > A URI Template Variable looks like `{amount}`.
   
1. Use the `RestTemplate`'s `getForObject()` method to pass in the URL, the `ConvertedCurrency.class`, and the query parameters.

   > Docs for RestTemplate are here: https://docs.spring.io/spring-framework/docs/4.3.18.RELEASE/javadoc-api/org/springframework/web/client/RestTemplate.html

1. The `getForObject()` will return an instance of the `ConvertedCurrency`, so you will take the `converted` property (which is a `double`) and return it as an `int` (you can just cast it or use the `intValue()`).

   * Here is the Trivia example that we saw:
   
    ```java
    RestTemplate restTemplate = new RestTemplate();
    String numberFactsUrl = "http://numbersapi.com/{number}?json";

    Map<String, String> params = new HashMap<>();
    params.put("number", "6");
    Trivia fact = restTemplate
        .getForObject(numberFactsUrl, Trivia.class, params);
    ```

    And here's the Trivia JavaBean that's used above: https://github.com/ted-ncg/labs/blob/master/Trivia.java

1. Now try viewing an account and see that the GBP converted value is correct.

**Do not proceed further unless this is working**

----

## Bonus #1

The server at http://jitterted-currency-conversion.herokuapp.com also supports converting to Bitcoin.

What would you need to do to have the account view page show the balance in Bitcoin in addition to USD and GBP?

* Think about what new method you want in your CurrencyService

## Bonus #2

1. Write automated tests that make sure that the CurrencyService works properly.

1. What tests would you want to change, expand, or add, to make sure the account view has the additional balances?
