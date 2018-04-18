# Spring REST Controller

### Link: `https://github.com/ted-ncg/labs/blob/master/05-spring-mvc-rest.md`

The goal of this lab is for you to implement a "RESTful" API for retrieving information about a specific account, using Spring's MVC framework.

## Your Task

**Note:** Keep this reference handy: http://engineering.pivotal.io/post/must-know-spring-boot-annotations-controllers/

1. Implement a class named `AccountApiController`, annotated with `@RestController`.
 
2. Implement a method `public Account accountInfo(String accountId)` that is mapped so that it will respond (be "routed" or "mapped") to

    ```
    http://localhost:8080/api/accounts/123
    ``` 

    * Use the `@GetMapping` annotation for the method
    
    * Use the `@PathVariable` annotation for the `accountId` variable
    
    * Note: `8080` is the default port when running the application on your machine

1. Inside the `accountInfo` method:
 
   * Instantiate a new `Account` object
   
   * Set its ID to the incoming `accountId` from the path
   
   * Set the `balance` to some amount
   
   * Return the account object

1. Run the `CanteenApplication` from within IntelliJ IDEA
   * Right click on the `CanteenApplication` file and then select "Run..."
 
   * Once you see something like the following, you can go to the next step:

   ```
   2017-08-27 09:37:21.351  INFO 11472 --- [  Main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
   2017-08-27 09:37:21.355  INFO 11472 --- [  Main] com.visa.ncg.canteen.CanteenApplication  : Started CanteenApplication in 3.433 seconds (JVM running for 4.064)
   ```

1. Make a request against the endpoint by either:
 
    * **Browser**: opening the URL `http://localhost:8080/api/accounts/123`

    * **`curl`**: use `curl` to make the request, e.g.:
      ```
      curl --noproxy localhost -v localhost:8080/api/accounts/123
      ```

      * **Note:** the `--noproxy localhost` tells `curl` to bypass the proxy for `localhost`

1. You should see something like

    ```json
    {"id": 123}
    ```
