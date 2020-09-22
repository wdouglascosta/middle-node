package br.uem.middlenode;

import br.uem.middlenode.service.SignService;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.commons.logging.Log;

@SpringBootApplication
public class MiddleNodeApplication implements CommandLineRunner {

	private static final Log LOG = LogFactory.getLog(MiddleNodeApplication.class);

	@Autowired
	private SignService signService;

	public static void main(String[] args) {
		SpringApplication.run(MiddleNodeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("Execução iniciada, criando conexões vizinhas");
		signService.createNeighborhood();
	}
}
