## Create Account - Validation

### This page is at: `https://github.com/ted-ncg/labs/blob/master/26-web-mvc-validation.md`

### References

* Summary of validation annotations: https://javaee.github.io/tutorial/bean-validation002.html

* Hibernate Validator reference: https://docs.jboss.org/hibernate/validator/5.3/reference/en-US/html/index.html

* Spring validation reference: https://docs.spring.io/spring/docs/4.3.11.RELEASE/spring-framework-reference/htmlsingle/#validation-beanvalidation

* Thymeleaf form tutorial: http://www.thymeleaf.org/doc/tutorials/3.0/thymeleafspring.html#creating-a-form

* Bean Validation specifications: http://beanvalidation.org/specification/

## Goal

Validate the incoming form information and tell the user if there are errors.

## Steps

1. Add the [CreateFormValidationTest](https://github.com/ted-ncg/labs/blob/master/CreateFormValidationTest.java) to your project. When you run it, it should **fail**.

   **Note:** You may need to change the test code to adjust for `int` vs. `BigDecimal`.

1. Annotate the fields (member variables) of the `CreateForm` POJO so that:

   * The account name has 2 or more characters, but no more than 63.
   
   * The initial deposit must be `>= 1` (0 is not OK, but 1 is fine), and no more than 100 (100 is ok, 101 is not)
   
   * The overdraft limit must be `>= 0` (0 is OK), but no more than 10

1. Re-run the CreateFormValidationTest, which should now **pass**.

1. Turn on validation for the form submission:
 
   * Annotate the `CreateForm` parameter with `@Valid` in the `POST`-mapped method for `/create-account`.
   
   * Add the `BindingResult` parameter to the method (no annotations needed for this)
   
   * Return the original view if there are errors in from the `BindingResult` (i.e., `result.hasErrors()` is true)
    
   * An example from Spring's PetClinic:

      ```java
          @PostMapping("/owners/new")
          public String processCreationForm(@Valid Owner owner, BindingResult result) {
              if (result.hasErrors()) {
                  return "createOwnerForm";
              }
              owners.save(owner);
              return "redirect:/owners/" + owner.getId();
          }
      ```

1. Update the `create-form.html` template to add the display of error messages next to each input field. For example, to display any errors for the input name field, which originally looks like this:

  ```html
  <p>Name for Account: <input type="text" th:field="*{accountName}"/></p>
  ```
  
  You will add a `<span>` tag with the `th:if` and `th:errors` attributes, like this:
  
  ```html
  <p>Name for Account: <input type="text" th:field="*{accountName}"/>
    <span th:if="${#fields.hasErrors('accountName')}" th:errors="*{accountName}">Name Error</span>
  </p>
  ```

1. Try it out!
