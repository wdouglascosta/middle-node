package br.uem.middlenode.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Properties {

    @Value("${instanceName}")
    private String instanceName;

    @Value("${instanceAddress}")
    private String instanceAddress;

    @Value("${server.port}")
    private String instancePort;

    @Value("${isClient}")
    private Boolean isClient;
}
