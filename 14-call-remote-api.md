# Calling Remote *Restful* APIs

### This page: https://github.com/ted-ncg/labs/blob/master/14-call-remote-api.md

### Reference Docs

* Spring RestTemplate docs:
  * https://docs.spring.io/spring/docs/5.1.8.RELEASE/spring-framework-reference/integration.html#rest-resttemplate

* Spring Boot - testing Rest Clients: 
  * https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html#boot-features-testing-spring-boot-applications-testing-autoconfigured-rest-client

## Preparation

From within IDEA, you'll need to set up the proxy information so you can reach out to APIs on the internet.

* Add proxy information to your Run Configurations as **VM** Options:

   * Go to the `Run` menu, select `Edit Configurations...`
   * Look for `CanteenApplication` on the left side, under the `Spring Boot` section
   * Fill in the following in the `VM Options` field:

     `-Dhttp.proxyHost=userproxy.visa.com -Dhttp.proxyPort=80`

* You will **also** need to add this proxy information to your JUnit test's run configuration

  * Look for the test in the list of configurations under the **JUnit** section

----

## The Lab: Part I

### Goal

Convert the account balance from USD ($) to GBP (£) and display it on the account view page.

### Change the View

1. Create a new class: `CurrencyService` that has a method:

    ```java
    int convertToGbp(int amount)
    ```

1. Create a _stub_ version of this service that simply returns `123`. We'll write the actual implementation later.

1. Inject (autowire) the CurrencyService into your Web (Account) Controller.

   > **(??)** What will you need to do to properly autowire the service?
   >
   > **(??)** What annotations are necessary?

1. Update the `AccountResponse` object so it has a property for the GBP balance. (Don't forget the getter and setter.)

1. In the `accountView()` method, after transforming the `Account` to an `AccountReponse`, use the `CurrencyService` to convert the balance and store that into the `AccountResponse` as well.

1. Update the Account View HTML page to display both USD and GBP on the page, like this:
     ```
     USD Balance: $10
     GBP Balance: £123
     ```

> Remember, the converted GBP value is hard-coded, so will always be 123.


**Do not proceed further until you have the account view page displaying both USD and GBP balance**

----

> <img src="stop-sign.jpg" width="56" /> Once you've completed the above steps, do **not** move on until you have checked in with the instructor.

----

## Part II

### Use the Real Currency Conversion Service

Now you will implement the `CurrencyService` so it will call out to the remote API.


   > **Example**
   >
   > _Learning from examples is great, but don't copy-n-paste..._
   >
   > Here is the Weather example:
   >
   > ```java
   > // instantiate a RestTemplate instance
   > RestTemplate restTemplate = new RestTemplate();
   > // create a Template URL for the API
   > String weatherUrl = "https://basic-weather.herokuapp.com/api/weather/{zip}";
   >
   > // put the variables in a Map
   > Map<String, String> uriVariables = new HashMap<>();
   > uriVariables.put("zip", "94404");
   >
   > // call out to the remote API and get back a JavaBean
   > WeatherResponse response =
   >     restTemplate.getForObject(weatherUrl, 
   >                               WeatherResponse.class,
   >                               uriVariables);
   > ```
   >
   >
   > And here's the JavaBean (DTO) that's used above:
   > 
   > ```java
   > public class WeatherResponse {
   >   private String location;
   >   private String updated;
   >   private Float temp;
   >   private String condition;
   >   // getters and setters would go here
   > }
   > ```


1. Create a JavaBean (DTO) `ConvertedCurrency` that represents the returned JSON called `ConvertedCurrency`.
   The *properties* for your DTO must *match* the JSON names, i.e., it will have 2 properties for the currency and the converted result.
   
   * The JSON returned from this API looks like this:
   
     ```json
     {
       "currency": "GBP",
       "converted": 71.00
     }
     ```

1. Create a URI Template string for the remote API:

   * It will send three *query* parameters:
       * `from` - the source currency, e.g., `USD`
       * `to` - the converted currency, e.g., `GBP`
       * `amount` - the amount to convert, e.g., 10

   * **Example:**
       * To convert $100 to GBP, the URL would look like this:
         ```
         http://jitterted-currency-conversion.herokuapp.com/convert?from=USD&to=GBP&amount=100
         ```

   > Think about what parts of the string need to become template variables.
   > A URI Template Variable looks like `{amount}`.
   
1. Instantiate a `RestTemplate` class and use the `getForObject()` method to pass in: the URL, the `ConvertedCurrency.class`, and the query parameters.

1. The `getForObject()` will return an instance of the `ConvertedCurrency`, so you will take the `converted` property (which is a `double`) and return it as an `int` (you can just cast it or use the `intValue()`).

1. Now try viewing an account and see that the GBP converted value is correct.

----

> <img src="stop-sign.jpg" width="56" /> Once you've completed the above steps, do **not** move on until you have checked in with the instructor.

----

## Bonus #1

The server at http://jitterted-currency-conversion.herokuapp.com also supports converting to Japanese Yen.

The `to` currency for the Yen is `JPY` (instead of `GBP`).

What would you need to do to have the account view page show the balance in Yen in addition to USD and GBP?

* Think about what new method you want in your `CurrencyService` interface and then implement it in the stub and the real service.

----

> <img src="stop-sign.jpg" width="56" /> Once you've completed the above steps, do **not** move on until you have checked in with the instructor.

----

## Bonus #2

1. Write automated tests that make sure that the CurrencyService works properly.

1. What tests would you want to change, expand, or add, to make sure the account view has the additional balances?
