package br.uem.middlenode.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class Signee {
    @JsonProperty(value = "client")
    private Boolean isclient;
    @JsonProperty(value = "subject")
    private Subject subject;
    @JsonProperty(value = "subscriber")
    private Subscriber subscriber;
}
