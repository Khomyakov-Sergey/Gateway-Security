package ru.clevertec.simpleapplication.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static java.nio.charset.StandardCharsets.UTF_8;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WireMockTest(httpPort = 9877)
public class RegistrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }


    @Test
    void wiremock_rule_test() throws Exception {
        configureStubs();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/converter/send")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(getJson("request/successUserRegistration.json")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Welcome!"))
                .andDo(MockMvcResultHandlers.print());

    }


    private void configureStubs() {
        WireMock.stubFor(
                WireMock.post(urlMatching("/registration"))
                        .willReturn(WireMock.aResponse()
                                .withStatus(200)
                                .withBody("Welcome!")));


    }

    private static String getJson(String pathname) throws IOException {
        File file = new ClassPathResource(pathname).getFile();
        return FileUtils.readFileToString(file, UTF_8);
    }


}
