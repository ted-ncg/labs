# Use Spring Data's JPA Repository

### This page: https://github.com/ted-ncg/labs/blob/master/20-memory-repository-to-jpa.md

----

## Goal 

Replace the in-memory `AccountRepository` that we've been using with Spring's JPA repository to use a database.

## Steps

1. Update the `Account` *domain* class to be a full-fledged JPA `Entity` class as follows:
   1. Annotate the Account with `@Entity`
   1. Ensure the ID is a `Long` object (not primitive), and is a properly named property (i.e., `getId` *not* `getID` -- case matters!)
   1. Annotate the `id` property to look like:

      ```java
      @Id 
      @GeneratedValue(strategy=GenerationType.AUTO)
      private Long id;
      ```

      > *Note:* The `@Id` and `@GeneratedValue` annotations are in the `javax.persistence` package

1. Rename the existing `AccountRepository` class to `InMemAccountRepository` by using IntelliJ's "Refactor -> Rename" tool.

1. Create a new **interface** as follows:

    ```java
    public interface AccountRepository extends CrudRepository<Account, Long>
    ```

1. Clean up any code that uses `AccountRepository` so that it's calling the correct methods.

    * Check that both API and Web controllers are using `AccountRepository` for the fields, and the constructor is autowired with `AccountRepository`.
    
    * The JPA version of the `AccountRepository` uses `Iterable` for `findAll()`, so you'll need to adapt that.
    
      * If you're using Java 8 streams, you can use:

```java
Iterable<Account> iterable = accountRepository.findAll();
List<AccountResponse> responses = StreamSupport.stream(iterable.spliterator(), false)
                                               .map(AccountResponse::fromAccount)
                                               .collect(Collectors.toList());
``` 


**Try out the application, everything should continue to work as before!**

----

# Extra Information

Spring Data (JPA) makes it very easy to add new query (find) methods *without writing any code*.

## Query by Name

Find by name: `List<Account> findByName(String name)`

See docs here: https://docs.spring.io/spring-data/jpa/docs/1.11.13.RELEASE/reference/html/#repositories.query-methods.query-creation

---

### Custom queries

You can also write custom queries without writing much code, see https://docs.spring.io/spring-data/jpa/docs/1.11.13.RELEASE/reference/html/#repositories.custom-implementations