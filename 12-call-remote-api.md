## Calling Remote *Restful* APIs

### This page is at: `https://github.com/ted-ncg/labs/blob/master/12-call-remote-api.md`

**Goal:** Convert the account balance from USD ($) to GBP (£)

1. Create a new class: `CurrencyService` (marked with the `@Service` annotation) that has a method:

    ```java
    int convertToGbp(int amount)
    ```

   * This method will use the `RestTemplate` Spring class and a POJO (described below) to call out to the currency converter at `http://jitterted-currency-conversion.herokuapp.com/convert`.
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
            "converted": 76.83
          }
          ```
   * Create a POJO that represents the returned JSON called `ConvertedCurrency`. 
     The *properties* for your POJO must *match* the JSON names, i.e., it will have 2 properties for the currency and the converted result.
   
   * Extract the `converted` value and return it as an `int` (you can just cast it or use the `intValue()`).

   * Here is the Trivia example that we saw:
   
    ```java
    RestTemplate restTemplate = new RestTemplate();
    String numberFactsUrl = "http://numbersapi.com/{number}?json";

    Map<String, String> params = new HashMap<>();
    params.put("number", "6");
    Trivia fact = restTemplate
        .getForObject(numberFactsUrl, Trivia.class, params);
    ```

    And here's the Trivia POJO that's used above: https://github.com/ted-ncg/labs/blob/master/Trivia.java
    
1. Inject the CurrencyService into your web (Account) Controller and use this service in the `@GetMapping` for the account view page.

   * Update the HTML view page to display both USD and GBP on the page, like this:
     ```
     USD Balance: $10
     GBP Balance: £8
     ```

### Proxying

You may need to add the following proxy information to your Run Configurations as **VM** Options:

    `-Dhttp.proxyHost=userproxy.visa.com -Dhttp.proxyPort=80`

