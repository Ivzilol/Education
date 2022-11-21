package com.example.mobile.web;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import java.util.Locale;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationControllerGreenMailIT {

    @Value("${mail.host}")
    private String mailHost;
    @Value("${mail.port}")
    private Integer mailPort;
    @Value("${mail.username}")
    private String userName;
    @Value("${mail.password}")
    private String password;

    private GreenMail greenMail;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        greenMail = new GreenMail(new ServerSetup(mailPort, mailHost, "smtp"));
        greenMail.start();
        greenMail.setUser(userName, password);
    }

    @AfterEach
    void tearDown() {
        greenMail.stop();
    }

    @Test
    void testRegistration() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("email", "ivo@example.com")
                        .param("firstName", "Ivo")
                        .param("lastName", "Alichkov")
                        .param("password", "123456")
                        .param("confirmPassword", "123456")
                        .with(csrf())
                ).
                andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        MimeMessage[] receiveMessages = greenMail.getReceivedMessages();
        Assertions.assertEquals(1, receiveMessages.length);

        MimeMessage welcomeMessage = receiveMessages[0];
        Assertions.assertTrue(welcomeMessage
                .getContent()
                .toString()
                .contains("Ivo Alichkov"));
    }
}
