package com.tempest.teste.allowme.serviceImpl;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.tempest.teste.allowme.entity.BillingSummary;
import com.tempest.teste.allowme.entity.Billings;
import com.tempest.teste.allowme.entity.ServiceRequests;
import com.tempest.teste.allowme.entity.Services;
import com.tempest.teste.allowme.service.AllowMeService;
import com.tempest.teste.allowme.service.BillingSummaryService;
import com.tempest.teste.allowme.service.BillingsService;
import com.tempest.teste.allowme.service.GeolocationService;
import com.tempest.teste.allowme.service.ServiceRequestsService;
import com.tempest.teste.allowme.service.ServicesService;
import com.tempest.teste.allowme.service.WeatherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
@EnableScheduling
public class AllowMeServiceImpl implements AllowMeService{
	
	WeatherService weatherService;
	GeolocationService geolocationService;
	ServiceRequestsService serviceRequestsService;
	BillingsService billingsService;
	BillingSummaryService billingSummaryService;
	ServicesService servicesService;
	Configuration conf;
	private Logger logger;
	
	@Autowired
	public AllowMeServiceImpl(WeatherService weatherService, GeolocationService geolocationService,
			ServiceRequestsService serviceRequestsService,BillingSummaryService billingSummaryService,
			ServicesService servicesService, BillingsService billingsService) {
		this.weatherService = weatherService;
		this.geolocationService = geolocationService;
		this.serviceRequestsService = serviceRequestsService;
		this.servicesService = servicesService;
		this.billingsService = billingsService;
		this.billingSummaryService = billingSummaryService;
		this.conf = Configuration.builder().options(Option.SUPPRESS_EXCEPTIONS).build();
		this.logger =  LogManager.getLogger(AllowMeServiceImpl.class);
	}
	
	@Scheduled(cron = "${schedule.jobs.cronExp}")
	public void iniciaEnriquecimento() {
		logger.info("Iniciar Scheduled " + LocalDateTime.now());

		try {

			String weatherRecife = weatherService.getToWeather("Recife,pernambuco");

			if (weatherRecife != null)
				geolocationService.getToGeolocation(
						JsonPath.using(conf).parse(weatherRecife).read("$.coord.lat").toString(),
						JsonPath.using(conf).parse(weatherRecife).read("$.coord.lon").toString());

			String weatherSaoPaulo = weatherService.getToWeather("São Paulo,São Paulo");

			if (weatherSaoPaulo != null)
				geolocationService.getToGeolocation(
						JsonPath.using(conf).parse(weatherSaoPaulo).read("$.coord.lat").toString(),
						JsonPath.using(conf).parse(weatherSaoPaulo).read("$.coord.lon").toString());

		} catch (Exception e) {
			logger.info("Final Scheduled " + e.getMessage());
		}

		logger.info("Final Scheduled " + LocalDateTime.now());
	}

	public void gerarBilling(Map<String, Object> request) throws Exception{
		
		try {
			
			logger.info("Iniciar Geração Billing "+ LocalDateTime.now());
			LocalDateTime start = LocalDateTime.parse(request.get("start").toString()+"T00:00:00");
			LocalDateTime end = LocalDateTime.parse(request.get("end").toString()+"T23:59:59");
			
			Services servico1 = servicesService.getService("Weather - API");
			Services servico2 = servicesService.getService("Geolocalização - API");
			List<ServiceRequests> ListservRequest = serviceRequestsService.getForDate(start, end);
			Double totalPrice = 0D;
			Long totalRequstApi1 = 0L, totalRequstApi2 = 0L;
			
			if(ListservRequest.isEmpty()) {
				logger.info("Não existem dados para o período "+request.get("start")+" á "+request.get("end"));
				throw new Exception("Não existem dados para o período");
			}
					
			for (ServiceRequests servReq : ListservRequest) {
				totalPrice += servReq.getServico().getPricePerRequest();
				
				if(servReq.getServico().getName().equals("Weather - API")) {
					totalRequstApi1++;
				}else if(servReq.getServico().getName().equals("Geolocalização - API")) {
					totalRequstApi2++;
				}
			}
			
			Billings billing = new Billings(null, LocalDateTime.now(), Date.from(start.atZone(ZoneId.systemDefault()).toInstant()), 
					Date.from(end.atZone(ZoneId.systemDefault()).toInstant()), totalPrice);
					
			billingsService.persitBilling(billing);
			
			BillingSummary bilSumAPI1 = new BillingSummary(null, LocalDateTime.now(), totalRequstApi1, servico1,
					billing, (servico1.getPricePerRequest() * totalRequstApi1));
			
			billingSummaryService.persitBillingSummary(bilSumAPI1);
			
			BillingSummary bilSumAPI2 = new BillingSummary(null, LocalDateTime.now(), totalRequstApi2, servico2, 
					billing, (servico2.getPricePerRequest() * totalRequstApi2));
			
			billingSummaryService.persitBillingSummary(bilSumAPI2);
			
			logger.info("Finaliza Geração Billing "+ LocalDateTime.now());
			
		} catch (Exception e) {
			logger.error("Error gerarBilling "+e.getMessage());
			throw new Exception(e.getMessage());
		}
		
	}
	
	
}
