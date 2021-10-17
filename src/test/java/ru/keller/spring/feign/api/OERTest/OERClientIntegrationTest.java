package ru.keller.spring.feign.api.OERTest;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.keller.spring.feign.api.WireMockConfig;
import ru.keller.spring.feign.api.client.OERClient;

import java.io.IOException;

@SpringBootTest
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WireMockConfig.class })
class OERClientIntegrationTest {

    @Autowired
    private WireMockServer mockOERService;

    @Autowired
    private OERClient oerClient;


    @BeforeEach
    void setUp() throws IOException {
        OERMock.setupMockOERResponse(mockOERService);
    }


    @Test
    public void isGotRateOK() {
        Assertions.assertEquals((double) oerClient.getPreviousRate("0000-00-00", "0_A")
                .getContent().getRates().getRub(), 70.9857);
    }

}