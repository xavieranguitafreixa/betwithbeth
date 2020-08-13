package com.factorialsigma.betwithbeth.wanabet;

import com.factorialsigma.betwithbeth.wanabet.model.BetofferResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author
 * @version 1.0
 */
@FeignClient(url = "https://api.kambi.com/offering/api/v2/rfes/betoffer/main/group/1000095049.json?id=1000095050&id=1000095001&id=1000094985&id=1000094994&id=1000094991&id=1000093652&id=2000096421&id=1000093656&id=1000093657&type=2&lang=ES_es",
        name = "WanabetClient")
public interface WanabetFeignClient {

    @RequestMapping(value = "/",
            method = RequestMethod.GET,
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            })
    BetofferResponse getOffers();
}
