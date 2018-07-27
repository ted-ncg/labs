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

### Steps

1. Create a new class: `CurrencyService` that has a method:

    ```java
    int convertToGbp(int amount)
    ```

1. Create a fake version of this service to return `123`. We'll write the actual implementation later.

1. Inject (autowire) the CurrencyService into your Web (Account) Controller, and use this service in the `@GetMapping` for the account view page.

   > **(??)** What will you need to do to properly autowire the service?

   * Update the HTML Account view page to display both USD and GBP on the page, like this:
     ```
     USD Balance: $10
     GBP Balance: £8
     ```

** Do not proceed further until you have the account view page displaying both USD and GBP balance **

----

1. Now implement the CurrencyService so it will call out to the remote API.

   * The `convertToGbp()` will use the `RestTemplate` Spring class and a JavaBean (described below) to call out to the currency converter at `http://jitterted-currency-conversion.herokuapp.com/convert`.
     It will send three *query* parameters:
       * `from` - the source currency, e.g., `USD`
       * `to` - the converted currency, e.g., `GBP`
       * `amount` - the amount to convert, e.g., 10
   * **Example:**
       * To convert $100 to GBP, the URL would be `http://jitterted-currency-conversion.herokuapp.com/convert?from=USD&to=GBP&amount=100`
       * The JSON returned from this API would look like this:
          ```json
          {
            "currency": "GBP",
            "converted": 71.00
          }
          ```
   * Create an object that represents the returned JSON called `ConvertedCurrency`. 
     The *properties* for your JavaBean must *match* the JSON names, i.e., it will have 2 properties for the currency and the converted result.
   
   * Take the `converted` value (which is a `double`) and return it as an `int` (you can just cast it or use the `intValue()`).

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

## Bonus

The server at http://jitterted-currency-conversion.herokuapp.com also supports converting to Bitcoin.

What would you need to do to have the account view page show the balance in Bitcoin in addition to USD and GBP?

* Think about what new method you want in your CurrencyService
