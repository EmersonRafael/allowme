package com.tempest.teste.allowme.service;

import java.time.LocalDateTime;
import java.util.List;
import com.tempest.teste.allowme.entity.ServiceRequests;
import com.tempest.teste.allowme.entity.Services;

public interface ServiceRequestsService {
	void persistRequest(Services service, int statusCode);
	List<ServiceRequests> getForDate(LocalDateTime start, LocalDateTime end);
}
