
> <img src="stop-sign.jpg" width="56" /> Once you've completed the above steps, do **not** move on until you have checked in with the instructor.

----

## Bonus

You can use the `@ModelAttribute` annotation on a "factory" method for instantiating the `CreateForm` object, like this:

  ```java
    @ModelAttribute("createForm")
    public CreateForm createForm() {
      CreateForm createForm = new CreateForm();
      createForm.setAccountName("");
      // more initialization here ...
      return createForm;
    }
  ```

You can now **remove** the initialization that you were doing in the create form `GET` method, and you'll no longer need *any* parameters passed into your `GET`-mapped method, e.g.:

  ```java
  @GetMapping("create-account")
  public String createForm()
  ...etc...
  ```

----
