## Calling Remote *Restful* APIs

**This page is at:** `https://github.com/ted-ncg/labs/blob/master/14-call-remote-api.md`

## Reference Docs

* Spring RestTemplate docs: https://docs.spring.io/spring/docs/4.3.16.RELEASE/spring-framework-reference/html/remoting.html#rest-resttemplate

## Preparation

From within IDEA, you'll need to set up the proxy information so you can reach out to APIs on the internet.

* Add proxy information to your Run Configurations as **VM** Options:

   * Go to the `Run` menu, select `Edit Configurations...`
   * Look for `CanteenApplication` on the left side, under the `Spring Boot` section
   * Fill in the following in the `VM Options` field:

     `-Dhttp.proxyHost=userproxy.visa.com -Dhttp.proxyPort=80`


## The Lab

**Goal:** Convert the account balance from USD ($) to GBP (£)

1. Create a new class: `CurrencyService` that has a method:

    ```java
    int convertToGbp(int amount)
    ```

   * This method will use the `RestTemplate` Spring class and a JavaBean (described below) to call out to the currency converter at `http://jitterted-currency-conversion.herokuapp.com/convert`.
     It will send three *query* parameters:
       * `from` - the source currency, e.g., `USD`
       * `to` - the converted currency, e.g., `GBP`
       * `amount` - the amount to convert, e.g., 10
   * **Example:**
       * To convert $100 to GBP, the URL is `http://jitterted-currency-conversion.herokuapp.com/convert?from=USD&to=GBP&amount=100`
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
    
1. Inject (autowire) the CurrencyService into your Web (Account) Controller, and use this service in the `@GetMapping` for the account view page.

   > **(??)** What will you need to do to properly autowire the service?

   * Update the HTML Account view page to display both USD and GBP on the page, like this:
     ```
     USD Balance: $10
     GBP Balance: £8
     ```
