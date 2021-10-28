package com.tempest.teste.allowme.service;

import java.util.Map;

public interface AllowMeService {
	void gerarBilling(Map<String, Object> request) throws Exception;
	void iniciaEnriquecimento();
}
