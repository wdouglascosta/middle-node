package br.uem.middlenode.service;

import br.uem.middlenode.model.Signee;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.concurrent.TimeUnit;

public class RequestService implements Runnable {

    private RestTemplate restTemplate = new RestTemplate();

    public RequestService(String addr, Object ownSignee) {
        this.addr = addr;
        this.ownSignee = ownSignee;
    }

    private String addr;
    private Object ownSignee;

    @Override
    public void run() {
        System.out.println("Enviando request -> " + addr);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        restTemplate.postForObject(addr, ownSignee, String.class);
    }
}
