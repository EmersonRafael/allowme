package com.tempest.teste.allowme;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.tempest.teste.allowme.controller.AllowMeController;
import com.tempest.teste.allowme.service.AllowMeService;

@RunWith(SpringRunner.class)
@SpringBootTest
class AllowmeApplicationTests {

	@Autowired
	AllowMeService allowMeService;
	
	@Test
	public void iniciaEnriquecimento() {
		 Assertions.assertDoesNotThrow(() -> {
			 allowMeService.iniciaEnriquecimento();
		  });
	}
	
	@Test
	public void gerarBilling() throws Exception {
		
		String start = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String end = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		MockMvcBuilders
        .standaloneSetup(new AllowMeController(allowMeService))
        .build().perform(MockMvcRequestBuilders.post("/allowMe/gerarBilling")
			      .content("{\n"
	                		+ "    \"start\":\""+start+"\",\n"
	                		+ "    \"end\": \""+end+"\"\n"
	                		+ "}")
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
		
	}
	
	@Test
	public void billingNaoEncontrado() throws Exception {
		
		MockMvcBuilders
        .standaloneSetup(new AllowMeController(allowMeService))
        .build().perform(MockMvcRequestBuilders.post("/allowMe/gerarBilling")
			      .content("{\n"
	                		+ "    \"start\":\"2020-10-28\",\n"
	                		+ "    \"end\": \"2020-10-28\"\n"
	                		+ "}")
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isNotFound());
		
	}
	
	@Test
	public void billingError() throws Exception {
		
		MockMvcBuilders
        .standaloneSetup(new AllowMeController(allowMeService))
        .build().perform(MockMvcRequestBuilders.post("/allowMe/gerarBilling")
			      .content("{\n"
	                		+ "    \"start1\":\"2020-10-28\",\n"
	                		+ "    \"end2\": \"2020-10-28\"\n"
	                		+ "}")
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isBadRequest());
		
	}
	


}
