package br.uem.middlenode.controller;

import br.uem.middlenode.model.Message;
import br.uem.middlenode.model.Signee;
import br.uem.middlenode.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignController {

    @Autowired
    private SignService signService;

    @GetMapping("/teste")
    public String teste() {
        return "teste";
    }

    @PostMapping("/subscrible")
    public String subscrible(@RequestBody Signee signee) {
        signService.subscrible(signee);
        return signee.toString();
    }

    @PostMapping("/message")
    public String message(@RequestBody Message message) {
        signService.postMessage(message);
        return "Mensagem postada: " + message.toString();
    }
}
