package com.visa.ncg.canteen;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountViewWebIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void getWithAccountIdReturnsAccountForThatId() throws Exception {
    MvcResult mvcResult = mockMvc.perform(post("/create-account")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .content("accountName=Videogames&initialDeposit=30")
    )
        .andExpect(status().is3xxRedirection())
        .andReturn();
    mockMvc.perform(get(mvcResult.getResponse().getRedirectedUrl()))
        .andExpect(status().isOk());
  }

}