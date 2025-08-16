package com.example.demo;

import com.example.demo.ch3.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalTime;

@SpringBootApplication
@Slf4j
public class DemoApplication {

	private URI baseUriV1 = UriComponentsBuilder.newInstance()
			.scheme("http")
			.host("localhost")
			.port(8080)
			.path("/v1/books")
			.build()
			.encode()
			.toUri();

	private URI baseUriV2 = UriComponentsBuilder.newInstance()
			.scheme("http")
			.host("localhost")
			.port(8080)
			.path("/v2/books")
			.build()
			.encode()
			.toUri();

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return (String ... args) -> {
			log.info("# 요청 시작 시간 : {}", LocalTime.now());

//			for (int i = 1; i <= 5; i++) {
//				Book book = this.getBook(i);
//				log.info("name : {}",book.getTitle());
//			}

			for (int i = 1; i <= 5; i++) {
				this.getBook2(i)
						.subscribe(
								book -> {

									log.info("name : {}", book.getTitle());
									log.info("Thread Name : {}" , Thread.currentThread().getName());
								}
						);
			}
		};
	}

	private Book getBook(long bookId) {
		RestTemplate restTemplate = new RestTemplate();

		URI getBooksUri = UriComponentsBuilder
				.fromUri(baseUriV1)
				.path("/{book-id}")
				.build()
				.expand(bookId)
				.encode()
				.toUri();

		ResponseEntity<Book> response = restTemplate.getForEntity(getBooksUri, Book.class);
		return response.getBody();
	}

	private Mono<Book> getBook2(long bookId) {
		URI getBooksUri = UriComponentsBuilder
				.fromUri(baseUriV2)
				.path("/{book-id}")
				.build()
				.expand(bookId)
				.encode()
				.toUri();

		return WebClient.create()
				.get()
				.uri(getBooksUri)
				.retrieve()
				.bodyToMono(Book.class);
	}
}
