# Account Repository

### This is at: https://github.com/ted-ncg/labs/blob/master/04-account-repository.md

## Goal

Have a way of storing and finding accounts in a central location.

## Steps

### Give Account an Identity

This will make an Account be an *Entity*.

* Add a `Long id` member variable to the `Account` class.

### Create a Repository

* Create a new class `AccountRepository` that implements the methods below.

  * Write the code **Test First** to implement each piece of functionality (for **each** method): writing a failing test, and then just enough code to make the test pass. 

  ```java
  public class AccountRepository {
  
      public Account findOne(Long id) {
        // Returns the Account that is identified by the given id.
        // If there's no Account with that id, return null.
      }
  
      public Account save(Account entity) {
        // Stores the given account in the repository so it can be found later.      
        // If the incoming account object's id is already set, DON'T modify it
        // If the account came in with NO id, replace it with one generated from AtomicLong.
        // return the Account object that must now have its id set
      }
  
      public List<Account> findAll() {
        // Return all accounts as a List
        // If there are no accounts, return empty List 
      }
  
  }
  ```

### Notes and Questions

* What is appropriate Java Collection class to use to store the accounts so that it's easy to find `Account`'s by their `id`.

* You will want an `AccountRepository` constructor that can take a `List<Account>` to "pre-load" the repository with data. For example, you should be able to (in your test) create an AccountRepository like this:

  ```java
  @Test
  public void findAllShouldReturn2Accounts() {
    List<Account> accounts = new ArrayList<>();
   
    Account a1 = new Account();
    a1.setId(1L);
    Account a2 = new Account();
    a2.setId(2L);
    accounts.add(a1);
    accounts.add(a2);
  
    AccountRepository repo = new AccountRepository(accounts);
    assertThat(repo.findAll())
      .hasSize(2);
  }
  ```

* You'll want to also check that each newly saved Account is given an ID that's different from other newly saved Accounts.

  ```java
  @Test
  public void newlySavedAccountsHaveUniqueIds() {
    AccountRepository accountRepository = new AccountRepository();
    Account account1 = new Account();
    accountRepository.save(account1);
    Account account2 = new Account();
    accountRepository.save(account2);
  
    assertThat(account1.getId())
        .isNotEqualTo(account2.getId());
  }
  ```

* Be sure and think about other *negative* tests that you can add and write them!
