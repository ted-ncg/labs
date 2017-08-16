# Repository for Accounts

## https://github.com/ted-ncg/labs/blob/master/in-memory-repository.md

* Add a `Long id` to the `Account` class.

* Create a new class `AccountRepository` that implements the methods below.

  * Write the code **Test First**, writing a failing test, and then just enough code to make the test pass. 

```java
public class AccountRepository {

    public Account findOne(Long id) {
      // return null if there's no account with the id
    }

    public Account save(Account entity) {
      // if account's id is already set, DON'T replace it
      // if the account came in with NO id, replace it with one generated from AtomicLong.
      // return the Account object that must now have its id set
    }

    public List<Account> findAll() {
      // return all accounts as a List, or an empty List if there are no accounts 
    }

}
```

### Notes

* Figure out what appropriate Java Collection class to use to store the accounts so that it's easy to find `Account`'s by its `id`.

* You will want an AccountRepository constructor that can take a `List<Account>` to "pre-load" the repository with data. For example, you should be able to (in your test) create an AccountRepository like this:

  ```java
  @Test
  public void findAllShouldReturn2Accounts() {
    List<Account> accounts = new ArrayList<Account>();
   
    Account a1 = new Account();
    Account a2 = new Account();
    accounts.add(a1);
    accounts.add(a2);
  
    AccountRepository repo = new AccountRepository(accounts);
    assertThat(repo.findAll())
      .hasSize(2);
  }
  ```

* 