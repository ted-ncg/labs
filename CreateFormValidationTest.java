package com.visa.ncg.canteen;

import org.hibernate.validator.HibernateValidator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateFormValidationTest {

  private LocalValidatorFactoryBean localValidatorFactory;

  @Before
  public void setup() {
    localValidatorFactory = new LocalValidatorFactoryBean();
    localValidatorFactory.setProviderClass(HibernateValidator.class);
    localValidatorFactory.afterPropertiesSet();
  }

  @Test
  public void invalidCreateFormShouldReport3Errors() {
    CreateForm createForm = new CreateForm();
    createForm.setAccountName("A"); // too short
    createForm.setInitialDeposit(0); // must be >= 1
    createForm.setOverdraftLimit(11); // 10 is limit

    Set<ConstraintViolation<CreateForm>> constraintViolations = localValidatorFactory.validate(createForm);

    assertThat(constraintViolations)
        .hasSize(3);
  }

  @Test
  public void validFormShouldResultInNoErrors() throws Exception {
    CreateForm createForm = new CreateForm();
    createForm.setAccountName("Fun Stuff");
    createForm.setInitialDeposit(10);
    createForm.setOverdraftLimit(5);

    Set<ConstraintViolation<CreateForm>> constraintViolations = localValidatorFactory.validate(createForm);

    assertThat(constraintViolations)
        .isEmpty();
  }
}
