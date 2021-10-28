package com.tempest.teste.allowme.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Option;
import com.tempest.teste.allowme.entity.ServiceRequests;
import com.tempest.teste.allowme.entity.Services;
import com.tempest.teste.allowme.repository.ServiceRequestsRepository;
import com.tempest.teste.allowme.service.ServiceRequestsService;

@Service
public class ServiceRequestsServiceImpl implements ServiceRequestsService{

	ServiceRequestsRepository serviceRequestsRepository;
	Configuration conf;
	
	@Autowired
	public ServiceRequestsServiceImpl(ServiceRequestsRepository serviceRequestsRepository) {
		this.serviceRequestsRepository = serviceRequestsRepository;
		this.conf = Configuration.builder().options(Option.SUPPRESS_EXCEPTIONS).build();
	}
	
	public void persistRequest(Services service, int statusCode) {
		ServiceRequests serviceRequests = new ServiceRequests();
		serviceRequests.setCreated(LocalDateTime.now());
		serviceRequests.setStatusCode(statusCode);
		serviceRequests.setServico(service);
		serviceRequestsRepository.save(serviceRequests);
	}

	public List<ServiceRequests> getForDate(LocalDateTime start, LocalDateTime end) {
		return serviceRequestsRepository.getForDate(start, end);
	}
	
}
