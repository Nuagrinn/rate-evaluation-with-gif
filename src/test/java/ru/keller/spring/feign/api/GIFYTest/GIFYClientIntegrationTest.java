package ru.keller.spring.feign.api.GIFYTest;


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
import ru.keller.spring.feign.api.client.GIPHYClient;

import java.io.IOException;

@SpringBootTest
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WireMockConfig.class })
public class GIFYClientIntegrationTest {

    @Autowired
    private WireMockServer mockOERService;

    @Autowired
    private GIPHYClient giphyClient;

    @BeforeEach
    void setUp() throws IOException {
        gifyMock.setupMockGIFYResponse(mockOERService);
    }

    @Test
    public void isGifyContentOK() {
        Assertions.assertEquals((String) giphyClient.getRandomGif("tag", "0_A")
                .getContent().getData().getId(), "3otPoP32sMALXPmqYM");
    }

}
