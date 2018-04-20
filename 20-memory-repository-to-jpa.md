# Use Spring Data's JPA Repository

*This page is at:* `https://github.com/ted-ncg/labs/blob/master/20-memory-repository-to-jpa.md`

----

## Goal 

Replace the in-memory, HashMap-based `AccountRepository` that we've been using with Spring's JPA repository to use a database.

## Steps

1. Update the `Account` *domain* class to be a full-fledged entity class as follows:
   1. Annotate the Account with `@Entity`
   1. Ensure the ID is a `Long` object (not primitive), and is a properly named property (i.e., `getId` *not* `getID` -- case matters)
   1. Annotate the `id` property to look like:

      ```java
      @Id 
      @GeneratedValue(strategy=GenerationType.AUTO)
      private Long id;
      ```

      > *Note:* The `@Id` and `@GeneratedValue` annotations are in the `javax.persistence` package

1. Rename the existing `AccountRepository` class to `InMemAccountRepository` by using IntelliJ's "Rename" refactoring tool.

1. Create a new interface as follows:

    ```java
    public interface AccountRepository extends CrudRepository<Account, Long>
    ```

1. Clean up code that uses `AccountRepository` so that it's calling the correct methods.

1. Try out the application, everything should continue to work as before!

----

### Query by Name

Find by name: List<Account> findByName(String name)

* https://docs.spring.io/spring-data/jpa/docs/1.11.11.RELEASE/reference/html/#repositories.query-methods.query-creation

---

### Custom queries

* https://docs.spring.io/spring-data/jpa/docs/1.11.11.RELEASE/reference/html/#repositories.custom-implementations