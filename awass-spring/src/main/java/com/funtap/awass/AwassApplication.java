package com.funtap.awass;



import com.funtap.awass.SpiderWeb.SpiderWeb;
import com.funtap.awass.SpiderWeb.login;
import com.funtap.awass.Start.Start;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
public class AwassApplication {

	public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
		SpringApplication.run(AwassApplication.class, args);

		System.out.println("====== AWASS =======");
		Start s = new Start();
		try {
			s.StartGame();
		} catch (Exception e) {
			e.printStackTrace();
		}

}
}

