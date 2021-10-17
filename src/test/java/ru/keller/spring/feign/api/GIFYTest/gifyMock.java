package ru.keller.spring.feign.api.GIFYTest;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.springframework.util.StreamUtils.copyToString;

public class gifyMock {
    public static void setupMockGIFYResponse(WireMockServer mockService) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/?tag=tag&api_key=0_A"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        gifyMock.class.getClassLoader().getResourceAsStream("test_files/giphy_response.json"),
                                        Charset.defaultCharset()))));
    }
}