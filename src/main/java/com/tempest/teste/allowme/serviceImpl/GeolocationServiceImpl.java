package com.tempest.teste.allowme.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.tempest.teste.allowme.entity.Services;
import com.tempest.teste.allowme.service.GeolocationService;
import com.tempest.teste.allowme.service.ServiceRequestsService;
import com.tempest.teste.allowme.service.ServicesService;

@Service
public class GeolocationServiceImpl implements GeolocationService{

	RestTemplate restTemplate;
	ServicesService servicesService;
	ServiceRequestsService serviceRequestsService;
	Configuration conf;
	
	@Autowired
	public GeolocationServiceImpl(RestTemplate restTemplate, ServicesService servicesService,ServiceRequestsService serviceRequestsService) {
		this.restTemplate = restTemplate;
		this.servicesService = servicesService;
		this.serviceRequestsService = serviceRequestsService;
		this.conf = Configuration.builder().options(Option.SUPPRESS_EXCEPTIONS).build();
	}
	
	public String getToGeolocation(String lat, String lon) {
		
		Services service = servicesService.getService("Geolocalização - API");
		String geolocationLink = service.getEndPoint().concat(service.getPath()).concat("?lat="+lat).concat("&lon="+lon);
		String retorno = restTemplate.getForObject(geolocationLink, String.class);
		
		if(retorno != null && JsonPath.using(conf).parse(retorno).read("$.address.state") != null) {
			serviceRequestsService.persistRequest(service, 200);
		}
		
		return retorno;
	}

}
