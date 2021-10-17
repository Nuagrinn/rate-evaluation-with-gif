package ru.keller.spring.feign.api.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RequestParam;
import ru.keller.spring.feign.api.dto.gifyDTO.GIFYResponse;

@PropertySource("classpath:application.properties")
@FeignClient(url = "${giphy.url}", name = "GIFY-CLIENT")
public interface GIPHYClient {

    @GetMapping()
    public EntityModel<GIFYResponse> getRandomGif(
            @RequestParam String tag,
            @RequestParam String api_key
    );

}