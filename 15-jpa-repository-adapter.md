# Use Spring Data's JPA Repository

### This page: https://github.com/ted-ncg/labs/blob/master/15-jpa-repository-adapter.md

----

## Goal 

Create a JPA implementation of the `AccountRepository` that uses Spring Data to store Accounts in a database.

## Preparation

1. Add the following dependencies to the `pom.xml` file inside the `<dependencies>` section:

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
        </dependency>

1. From the command-line, do a `mvn compile`, like this:

   ```
   mvn compile -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true -Dmaven.wagon.http.ssl.ignore.validity.dates=true`
   ```
   
   This may take a little while as it has to download the Spring Data dependencies.

## A. Create Entity and JPA Repository Interface

1. Copy the [AccountDto.java](AccountDto.java) class into your production code directory.

1. Create a new *interface* as follows:

    ```java
    public interface AccountJpaRepository extends CrudRepository<AccountDto, Long> {
    }
    ```

## B. Implement the Repository Adapter

1. Create an *Adapter* class called `AccountJpaRepositoryAdapter` that implements the `AccountRepository` interface
   and has the `@Repository` and `@Primary` annotations:

    ```java
    @Primary
    @Repository
    public class AccountJpaRepositoryAdapter implements AccountRepository {
    }
    ```

1. In this adapter, create a constructor to accept an *injected* (auto-wired) instance of `AccountJpaRepository`

1. In this adapter, implement the three `AccountRepository` methods, 
   converting from the domain `Account` objects to `AccountDto` objects that can be stored in the database.
   
   >Note: The `AccountDto` class has two conversion methods already written: `asAccount` and `from`
   >that you can use to convert between AccountDto (DTO) objects and Account (domain) objects.
   
   For example: 

    ```java
    @Override
    public Account findOne(Long id) {
      AccountDto dto = accountJpaRepository.findById(id).get(); // extract from Optional
      return dto.asAccount(); // convert from DTO to Account (domain) object
    }    
    ```

    Now implement `findAll()` and `save()`, delegating to those methods in the `AccountJpaRepository` instance.

----

**Try out the application, everything should continue to work as before!**

----

# Extra Information

Spring Data (JPA) makes it very easy to add new query (find) methods *without writing any code*.

## Query by Name

Find by name: `List<Account> findByName(String name)`

Docs: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation

---

### Custom queries

You can also write custom queries without writing much code
 
Docs: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.custom-implementations