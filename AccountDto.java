package com.visa.ncg.canteen;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AccountDto {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;
  private String name;
  private int balance;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getBalance() {
    return balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }

  public static AccountDto from(Account account) {
    AccountDto dto = new AccountDto();
    dto.setId(account.getId());
    dto.setBalance(account.balance());
    dto.setName(account.name());
    return dto;
  }

  Account asAccount() {
    Account account = new Account();
    account.setId(id);
    account.deposit(balance);
    account.changeNameTo(name);
    return account;
  }

}
