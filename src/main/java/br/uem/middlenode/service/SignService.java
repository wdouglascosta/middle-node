package br.uem.middlenode.service;

import br.uem.middlenode.model.Message;
import br.uem.middlenode.model.Signee;
import br.uem.middlenode.model.Subscriber;
import br.uem.middlenode.repository.MainTable;
import br.uem.middlenode.utils.Properties;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

@Service
public class SignService {

    private static final Log LOG = LogFactory.getLog(SignService.class);


    @Autowired
    private Properties properties;

    public void subscrible(Signee signee) {
        if (signee.getIsclient()) {
            MainTable.getInstance().addSubscription(signee);
        } else {
            MainTable.getInstance().addRoute(signee);
        }
        propagate(signee);
        LOG.info(signee.getSubscriber().getName() + " foi inscrito com interesse em " + signee.getSubject().name());
    }

    private void propagate(Signee signee) {
        for (Subscriber subscriber : MainTable.getInstance().getNeighborhood()) {
            if (!subscriber.getName().equals(signee.getSubscriber().getName()) && !subscriber.getIsClient()) {
                Signee ownSignee = Signee
                        .builder()
                        .isclient(false)
                        .subject(signee.getSubject())
                        .subscriber(Subscriber
                                .builder()
                                .name(properties.getInstanceName())
                                .address(properties.getInstanceAddress() + ":" + properties.getInstancePort())
                                .isClient(false)
                                .build())
                        .build();
                final String addr = subscriber.getAddress() + "/subscribe";
                LOG.info("propagando inscrição para vizinho " + subscriber.getName() + " (" + addr + ")");
                System.out.println(ownSignee.toString());
                RequestService requestService = new RequestService(addr, ownSignee);
                requestService.run();
            }
        }
    }

    public void createNeighborhood() {

        Gson gson = new Gson();
        try {
            Reader reader = new FileReader("vizinhos.json");
            Subscriber[] neighborhood = gson.fromJson(
                    new FileReader("vizinhos.json"),
                    Subscriber[].class);
            MainTable.getInstance().setNeighborhood(Arrays.asList(neighborhood));
            printNighborhood();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printNighborhood() {
        System.out.println("Vizinhos de " + properties.getInstanceName());
        MainTable
                .getInstance()
                .getNeighborhood()
                .stream()
                .forEach(System.out::println);
    }

    public void postMessage(Message message) {

        for (Signee signee : MainTable.getInstance().getSubscriptions()) {
            if (message.getSubject() == signee.getSubject()) {
                LOG.info("Mensagem recebida");
                System.out.println("Assunto: " + message.getSubject());
                System.out.println("Mensagem: " + message.getMessage());
                return;
            }
        }
        for (Signee signee : MainTable.getInstance().getRouting()) {
            if (message.getSubject() == signee.getSubject()) {
                LOG.info("Reencaminhando Mensagem");
                final String addr = signee.getSubscriber().getAddress() + "/message";
                RequestService requestService = new RequestService(addr, message);
                requestService.run();
            }
        }
    }
}
