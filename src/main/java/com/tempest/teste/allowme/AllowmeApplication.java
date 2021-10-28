package com.tempest.teste.allowme;


import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.time.Duration;

@SpringBootApplication
@EnableScheduling
public class AllowmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllowmeApplication.class, args);
	}
	
	private RestTemplateBuilder restTemplateBuilder(ObjectMapper objectMapper, RestTemplateBuilder restBdr)
			throws Exception {
		TrustManager[] trustManager = new TrustManager[] {

				new X509TrustManager() {

					@Override
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return new X509Certificate[0];
					}

					@Override
					public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
					}

					@Override
					public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
					}
				} };

		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(null, trustManager, new SecureRandom());

		HttpClient httpClient = HttpClients.custom().setSSLContext(sslContext)
				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();

		HttpComponentsClientHttpRequestFactory customRequestFactory = new HttpComponentsClientHttpRequestFactory();
		customRequestFactory.setHttpClient(httpClient);

		return restBdr.requestFactory(() -> customRequestFactory);
	}

	@Bean(name = "longRest")
	@Primary
	public RestTemplate restTemplateLongTimeout(ObjectMapper objectMapper, RestTemplateBuilder builder)
			throws Exception {

		return restTemplateBuilder(objectMapper, builder).setConnectTimeout(Duration.ofMillis(300))
				.setReadTimeout(Duration.ofMillis(15000)).build();
	}

	@Bean(name = "shortRest")
	public RestTemplate restTemplateShortTimeout(ObjectMapper objectMapper, RestTemplateBuilder builder)
			throws Exception {

		return restTemplateBuilder(objectMapper, builder).setConnectTimeout(Duration.ofMillis(300))
				.setReadTimeout(Duration.ofMillis(750)).build();
	}
}
