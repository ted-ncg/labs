## View Account

### References

* Thymeleaf 3: http://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#using-and-displaying-variables

### Goal: Create an "account view" page.

1. Create an HTML file `account-view.html`

```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" >
<head>
  <meta charset="UTF-8">
  <title>Account Information</title>
</head>
<body>
<h1>Account <span th:text="${account.name}">Name</span></h1>
<p>Balance: 100</p>
</body>
</html>
```

2. Modify your controller method to add the account into the model:

```
    @GetMapping(...)
    public String accountView(Model model) {
      ...
      model.addAttribute("account", account);
      return "account-view";
    }
```

3. Map a controller method to localhost:8080/account

4. Make the balance pull the information from the model.
