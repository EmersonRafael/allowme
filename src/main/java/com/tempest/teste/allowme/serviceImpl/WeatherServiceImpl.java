package com.tempest.teste.allowme.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.tempest.teste.allowme.entity.Services;
import com.tempest.teste.allowme.service.ServiceRequestsService;
import com.tempest.teste.allowme.service.ServicesService;
import com.tempest.teste.allowme.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService{
	
	RestTemplate restTemplate;
	ServicesService servicesService;
	ServiceRequestsService serviceRequestsService;
	Configuration conf;
	
	@Autowired
	public WeatherServiceImpl(RestTemplate restTemplate, ServicesService servicesService, ServiceRequestsService serviceRequestsService) {
		this.restTemplate = restTemplate;
		this.servicesService = servicesService;
		this.serviceRequestsService = serviceRequestsService;
		this.conf = Configuration.builder().options(Option.SUPPRESS_EXCEPTIONS).build();
	}
	
	public String getToWeather(String city){
	
		Services service = servicesService.getService("Weather - API");
		String weatherLink = service.getEndPoint().concat(service.getPath()).concat("?city=").concat(city);
		String retorno = restTemplate.getForObject(weatherLink.concat(city), String.class);
		
		if(retorno != null) {
			serviceRequestsService.persistRequest(service, JsonPath.using(conf).parse(retorno).read("$.cod"));
		}
		
		return retorno;
	}
	
	
	
}
