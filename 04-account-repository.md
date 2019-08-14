# Account Repository

### This page: https://github.com/ted-ncg/labs/blob/master/04-account-repository.md

## Goal

Have a way of storing and finding accounts in memory, behind a single interface.

## Steps

Be sure to skim through all of the steps before getting started.

### A. Give Account Identity

This will make an Account an *Entity*.

1. Add a `Long id` private member variable to the `Account` class.

   * **IMPORTANT:** Make sure you name the member variable exactly this way, as a `Long` with the name `id` (all lower-case).

1. Create a getter & setter for the `id` property.

   * Use IntelliJ to generate the getter & setter using `⌘ + N` (Mac) or `Alt + Insert` (Windows)

### B. Create Repository Interface

Create a new *interface* named `AccountRepository` (using `New > Java Class` from the menu)

<img src="create-accountrepository-interface.png" width="237">

Add the following code:

```java
public interface AccountRepository {

  Account findOne(Long id);
  
  Account save(Account entity);
  
  List<Account> findAll();

}
```

### C. Create AccountRepository Implementation

1. Create a new *class* named `FakeAccountRepository` that implements the `AccountRepository` interface and start with the following code:

    ```java
    public class FakeAccountRepository implements AccountRepository {
    
      public FakeAccountRepository() {}
      
      public FakeAccountRepository(Account... accounts) {
        // You can iterate through the accounts array like this:
        for (Account account : accounts) {
          // store each account here so it can be found later
        }
        // You can also convert it directly to a List:
        // See: https://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html#asList-T...-
        List<Account> accountList = Arrays.asList(accounts);
      }
  
      public Account findOne(Long id) {
        throw new UnsupportedOperationException(); 
      }
  
      public Account save(Account entity) {
        throw new UnsupportedOperationException(); 
      }
  
      public List<Account> findAll() {
      }
  
    }
    ```

1. First, you'll focus on creating the `findAll()` functionality, writing failing tests first:

   > NOTE: Put tests for `findAll` into a new Test class named `AccountRepositoryFindTest`

   * Write a *failing* test that verifies that if there are no accounts (an empty Repository),
     you will get an *empty* `List`
   
     * Once it fails, write just enough code to make it pass
   
   * If there are accounts, return all of them in a `List`

     * Use this test to get you started:   
       ```java
       @Test
       public void findAllShouldReturn2Accounts() {
         Account account1 = new Account();
         account1.setId(1L);
         Account account2 = new Account();
         account2.setId(2L);
       
         AccountRepository repo = new FakeAccountRepository(account1, account2);
         assertThat(repo.findAll())
           .hasSize(2);
       }
       ```
     
     * Make sure the above test fails, and then write just enough code to make it pass

   * Make sure **all tests** pass before continuing.

----

1. Next, you'll create the `findOne()` functionality, writing failing tests first:

   > NOTE: Add tests for `findOne` into `AccountRepositoryFindTest`

   * **Think:** What is appropriate Java Collection class to use to store the accounts
     so that it's easy to find `Account`'s by their `id`?
     
   * What is a **failing** test that you can write for each of these scenarios:

       * If there's no `Account` with that `id`, return `null`.
       * If there exists an `Account` with the given `id`, return it

   * Write a failing test, then the minimal amount of code to make them pass.

   * Make sure all tests **pass** before continuing.

----

1. Now you can work on the `save()` functionality, which stores the given account
   in the repository so it can be found later.

   > NOTE: Put tests for `save` into a new Test class named `AccountRepositorySaveTest`

   * If the incoming account object's `id` property is ALREADY set, DON'T modify it, just store it.
     This is the same as an UPDATE.
   
      * If the `id` is set, then the `save` is an **update**,
        i.e., you should be able to find an Account by its ID, modify it, and save it **back** to the repository. 

      * This test demonstrates the idea of the **update**:
       
        ```java
        @Test
        public void saveAccountWithIdHasChangedAttributesWhenFound() throws Exception {
          AccountRepository accountRepository = new AccountRepository();
          Account account = new Account();
          account.deposit(15);
          account.setId(12L);
          accountRepository.save(account);
      
          Account found = accountRepository.findOne(12L);
          found.withdraw(7);
          accountRepository.save(found);
      
          Account account12 = accountRepository.findOne(12L);
          assertThat(account12.balance())
              .isEqualTo(8);
        }
        ```

1. Once the above works where the `account` already has an ID, 
   then you can work on an account that does **not** have an ID:

   * Rule: If the account came in with NO `id` (it is `null`), SET it with a UNIQUE one that the repository generates
   
     * **Think:** How will you guarantee uniqueness?
      
   * Return the Account object that must now have its id set (i.e., is NOT null)

   * Here's a test to ensure that each newly saved Account is given an ID that's different from other newly saved Accounts.
  
      ```java
      @Test
      public void newlySavedAccountsHaveUniqueIds() {
        AccountRepository accountRepository = new FakeAccountRepository();
        Account account1 = new Account();
        account1 = accountRepository.save(account1);
        Account account2 = new Account();
        account2 = accountRepository.save(account2);
      
        assertThat(account1.getId())
            .isNotEqualTo(account2.getId());
      }
      ```


----

### Negative Tests

* Be sure and think about other *negative* tests that you can add and write them!

----

<div style="padding-right: 8px;">
  <p style="text-align: left; font-size: 110%; font-weight: 700;">
    <img src="/stop-sign.jpg" style="float: left; vertical-align: middle; width: 80px; padding-right: 10px">
    Once you've completed the above steps,<br/>
    check in with the instructor to review your code.
  </p>
</div>

----

## Optional

1. Implement the "delete" functionality by adding the following method to the `AccountRepository` interface:

    ```java
    void delete(Account account);
    ```

1. Implement the following behavior in your `FakeAccountRepository`, remember to write a failing test **first**:

   > NOTE: Put tests for `delete` into a new Test class named `AccountRepositoryDeleteTest`
  
   * Deleting an account means that trying to find it by its `id` will return `null` -- as if the account was never saved.
    
     > NOTE: Don't modify the account object that's being deleted -- it will continue to have the id that was assigned upon `save`
    
   * If `null` reference for the `Account` is passed in, throw an `IllegalArgumentException`
    
   * If an `Account` with an `id` that's not found is passed in, throw an `IllegalArgumentException` 
  
----

<div style="padding-right: 8px;">
  <p style="text-align: left; font-size: 110%; font-weight: 700;">
    <img src="/stop-sign.jpg" style="float: left; vertical-align: middle; width: 80px; padding-right: 10px">Once you've completed the above steps,<br/>
    check in with the instructor to review your code.
  </p>
</div>

----  

## References

Article: ["Mocks Aren't Stubs"](https://www.martinfowler.com/articles/mocksArentStubs.html) by Martin Fowler

Book: [xUnit Test Patterns](https://martinfowler.com/books/meszaros.html) by Gerard Meszaros

