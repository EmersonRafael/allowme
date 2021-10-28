package com.tempest.teste.allowme.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tempest.teste.allowme.service.AllowMeService;

@RestController
@RequestMapping("allowMe")
public class AllowMeController {

	AllowMeService allowMeService;
	
	@Autowired
	public AllowMeController(AllowMeService allowMeService) {
		this.allowMeService = allowMeService;
	}
	
	@PostMapping("/gerarBilling")
	public ResponseEntity<Object> gerarBilling(@RequestBody Map<String, Object> request){
		
		try {
			allowMeService.gerarBilling(request);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao tentar gerar o billing!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Billing gerado com sucesso!");
	}
	
	
}
