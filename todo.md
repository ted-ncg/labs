# To Do's

Add this to pom.xml to be compatible with Java 9/10

    <dependency>
      <groupId>org.glassfish.jaxb</groupId>
      <artifactId>jaxb-runtime</artifactId>
      <version>2.3.0</version>
      <scope>runtime</scope>
    </dependency>


## Slides

* JavaBean (differentiate from POJO?)

* Dependency injection

* HTTP Sequence diagrams

* Update examples for Thymeleaf

* What makes a POJO: Implementing equals, hashCode, and toString()

esp. for failing tests like:

```
java.lang.AssertionError: 
Expecting:
 <[]>
to contain:
 <[com.visa.ncg.canteen.Account@215be6bb, com.visa.ncg.canteen.Account@4439f31e]>
but could not find:
 <[com.visa.ncg.canteen.Account@215be6bb, com.visa.ncg.canteen.Account@4439f31e]>
```

## Labs

* Optionals for Lab 3 for negative cases

* Add assertThatThrownBy into lab as a reference page

* Check that DELETE is in the AccountRepository

  * Provide test for DELETE

### HTTP API

* POST validation

* Query (find) account by its name

* Edit name via API

* Exception handling in API

### Thymeleaf

* Add conditional (if, etc.) e.g.: http://www.baeldung.com/thymeleaf-in-spring-mvc

* Tests for Thymeleaf (esp. the URL interpolation)?

* Remote API: use https://jsonplaceholder.typicode.com/ as another example

* Currency Service: Add bonus to extract class to an interface and use Spring's config annotations to select implementation: either the real one that goes over the internet, or a fake one that provides a fake value

  * This will need Spring configuration information to select the correct one


## Posters

* Resize (larger) the TDD cycle poster


## Autobuild while running

Application “hot restart” with Spring Boot devtools
Spring Boot devtools is configured by JHipster, and will “hot restart” your application when classes from your project are compiled. This is a must-have feature, as it makes your application updated on the fly.

By default IntelliJ IDEA does not automatically compile files when the application is running. To enable the “Compile on save” feature:

Go to File -> Settings -> Build, Execution, Deployment -> Compiler and enable “Make project automatically”
Open the Action window :
Linux : CTRL+SHIFT+A
Mac OSX : SHIFT+COMMAND+A
Windows : CTRL+ALT+SHIFT+/
Enter Registry... and enable compiler.automake.allow.when.app.running


