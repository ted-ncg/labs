# Repository for Accounts

## https://github.com/ted-ncg/labs/blob/master/in-memory-repository.md

* Add a `Long id` to the `Account` class.

* Create a new class `AccountRepository` that implements the methods below.

  * Write the code **Test First**, writing a failing test, and then just enough code to make the test pass. 

```java
public class AccountRepository {

    public Account findOne(Long id) {
      
    }

    public Account save(Account entity) {
      // if account's id is already set, DON'T replace it
    }

    public List<Account> findAll() {
      
    }

}
```
