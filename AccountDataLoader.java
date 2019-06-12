package com.visa.ncg.canteen;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountDataLoader implements ApplicationRunner {
  private AccountRepository accountRepository;

  public AccountDataLoader(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    // create a couple of accounts with initial balance and save them to the repository
    Account account1 = new Account();
    account1.deposit(10);
    accountRepository.save(account1);
    Account account2 = new Account();
    account2.deposit(20);
    accountRepository.save(account2);
  }
}
