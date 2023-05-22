package se.iths.weatherwebservice.data;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import se.iths.weatherwebservice.business.MyWeather;
import se.iths.weatherwebservice.data.smhi.Parameter;
import se.iths.weatherwebservice.data.smhi.SmhiWeather;

import java.util.List;

@Repository
public class SmhiClientRepository {

WebClient client=WebClient.create("http://localhost:8080");

public MyWeather getSmhiWeather(){
    Mono<SmhiWeather> m=client
            .get()
            .uri("https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/18.0300/lat/59.3110/data.json")
            .retrieve()
            .bodyToMono(SmhiWeather.class);

    SmhiWeather smhiWeather = m.block();

    List<Parameter> weatherParams =smhiWeather.getTimeSeries().get(26).getParameters();
    double temp = 0;
    double humid=0;
    double windspeed=0;

    for (Parameter p:weatherParams) {
        if("t".equals(p.getName())){
            temp=p.getValues().get(0);
        }
        if("r".equals(p.getName())){
            humid=p.getValues().get(0);
        }
        if("ws".equals(p.getName())){
            windspeed=p.getValues().get(0);
        }
    }
    String timestamp = smhiWeather.getTimeSeries().get(26).getValidTime();
    return new MyWeather(temp, humid,windspeed, "SMHI", timestamp);





}
}
