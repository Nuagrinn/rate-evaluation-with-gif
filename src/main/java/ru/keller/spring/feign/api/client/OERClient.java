package ru.keller.spring.feign.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.keller.spring.feign.api.dto.OpenExchangeRatesDTO.OERResponse;

@PropertySource("classpath:application.properties")
@FeignClient(url="${oer.url}", name = "OER-CLIENT")
public interface OERClient {

    @GetMapping("{date}.json")
    EntityModel<OERResponse> getCurrentRate(@PathVariable String date,
                                            @RequestParam String app_id);

    @GetMapping("{date}.json")
    EntityModel<OERResponse> getPreviousRate(@PathVariable String date,
                                             @RequestParam String app_id);


}
