package ru.keller.spring.feign.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.keller.spring.feign.api.client.GIPHYClient;
import ru.keller.spring.feign.api.client.OERClient;
import ru.keller.spring.feign.api.dto.OpenExchangeRatesDTO.OERResponse;
import ru.keller.spring.feign.api.dto.gifyDTO.GIFYResponse;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@RestController
@PropertySource("classpath:application.properties")
public class MainController {

    @Autowired
    private OERClient oerClient;

    @Autowired
    private GIPHYClient giphyClient;

    @Value("${oer.api_id}")
    private String oerApi;

    @Value("${giphy.api.key}")
    private String giphyApi;

    @Value("${giphy.tag.rich}")
    private String giphyTagRich;

    @Value("${giphy.tag.broke}")
    private String giphyTagBroke;

    @GetMapping("/getRubRates")
    public String getRubRates() {

        String curDate = LocalDate.now().toString();
        EntityModel<OERResponse> entityModelCurr = oerClient.getCurrentRate(curDate, oerApi);
        double currRubRate = entityModelCurr.getContent().getRates().getRub();

        String prevDate = LocalDate.now().minusDays(1).toString();
        EntityModel<OERResponse> entityModelPrev = oerClient.getPreviousRate(prevDate, oerApi);
        double prevRubRate = entityModelPrev.getContent().getRates().getRub();

        String report = "Previous RUB rate = " + prevRubRate + "\n Current RUB rate = " + currRubRate +
                "\n Today date: " + curDate;

        return report;
    }

    @GetMapping("/getRandomGIF")
    public void getRandomGIF(HttpServletResponse httpServletResponse) {

        String curDate = LocalDate.now().toString();
        EntityModel<OERResponse> entityModelCurr = oerClient.getCurrentRate(curDate, oerApi);
        double currRubRate = entityModelCurr.getContent().getRates().getRub();

        String prevDate = LocalDate.now().minusDays(1).toString();
        EntityModel<OERResponse> entityModelPrev = oerClient.getPreviousRate(prevDate, oerApi);
        double prevRubRate = entityModelPrev.getContent().getRates().getRub();


        EntityModel<GIFYResponse> model;
        if (currRubRate > prevRubRate) {
            model = giphyClient.getRandomGif(giphyTagRich, giphyApi);
            GIFYResponse resp = model.getContent();
            String gifUrl = resp.getData().getEmbedUrl();

            httpServletResponse.setHeader("Location", gifUrl);
        } else {
            model = giphyClient.getRandomGif(giphyTagBroke, giphyApi);
            GIFYResponse resp = model.getContent();
            String gifUrl = resp.getData().getEmbedUrl();

            httpServletResponse.setHeader("Location", gifUrl);
        }
        httpServletResponse.setStatus(302);


    }
}
