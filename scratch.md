  ```java
    @TestConfiguration
    static class Config {
      @Bean
      public AccountRepository createTestAccountRepository() {
        AccountRepository accountRepository = new AccountRepository();
        Account account = new Account();
        account.setId(1L);
        account.deposit(10);
        accountRepository.save(account);
        return accountRepository;
      }
    }
  ```

----

(Using the @SpringBootTest...)

----
        .andExpect(model().attributeExists("account"))
        .andExpect(model().attribute("account", instanceOf(AccountResponse.class)))
        .andExpect(model().attribute("account", hasProperty("name", is("Video Games"))));

---
