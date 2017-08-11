## Calling Remote *Restful* APIs

### This page is at: `https://github.com/ted-ncg/labs/blob/master/call-remote-api.md`

**Goal:** Convert the account balance from USD ($) to GBP (£)

1. Create a new class: `CurrencyService` that has a method

    ```java
    int convertToGbp(int amount)
    ```

   * This method will use the `RestTemplate` Spring class to call out to the currency converter at `trampoline.io/convert`.
     It will send three *query* parameters:
       * `from` - the source currency, e.g., `USD`
       * `to` - the converted currency, e.g., `GBP`
       * `amount` - the amount to convert
   * **Example:**
       * To convert $100 to GBP, the URL is `http://trampoline.io/convert?from=USD&to=GBP&amount=100`
       * The JSON returned from this API would look like this:
          ```json
          {
            "currency": "GBP",
            "converted": 76.83
          }
          ```
   * Create a POJO that represents the returned JSON.
   * Extract the `converted` value and return it.

   * Here is the Trivia example that we saw:
   
    ```java
    RestTemplate restTemplate = new RestTemplate();
    String numberFactsUrl = "http://numbersapi.com/{number}?json";

    Map<String, String> params = new HashMap<>();
    params.put("number", "6");
    Trivia fact = restTemplate
        .getForObject(numberFactsUrl, Trivia.class, params);
    ```

1. Inject the CurrencyService into your web `@Controller` and use this service in the `@GetMapping` for the account view page.
   Display both USD and GBP on the page, like this:
   ```
   USD Balance: $10
   GBP Balance: £8
   ```

### Proxying

You may need to add the following proxy information to your Run Configurations as Program Arguments:

    -Dhttp.proxyHost=userproxy.visa.com –Dhttp.proxyPort=80

