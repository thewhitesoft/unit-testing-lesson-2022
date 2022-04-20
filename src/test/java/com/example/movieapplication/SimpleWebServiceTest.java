package com.example.movieapplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SimpleWebServiceTest {

	@Test
	void send() {
		// Arrange
		WebClient webClient = mock(WebClient.class);
		SimpleWebService webService = new SimpleWebService(webClient);
		// Act
		webService.send();
		// Assert
		ArgumentCaptor<Request> captor = ArgumentCaptor.forClass(Request.class);
		verify(webClient).send(captor.capture());
		assertThat(captor.getValue().getUrl()).isEqualTo("localhost:1234/test");
		assertThat(captor.getValue().getMethod()).isEqualTo("POST");
	}
}

@RequiredArgsConstructor
class SimpleWebService {
	private final WebClient client;
	public void send() {
		client.send(new Request("POST", "localhost:1234/test"));
	}
}

interface WebClient {
	void send(Request request);
}

@Data
@AllArgsConstructor
class Request {
	private String method;
	private String url;
}