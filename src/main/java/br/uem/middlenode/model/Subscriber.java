package br.uem.middlenode.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Subscriber implements Serializable {
    @JsonProperty(value = "isClient")
    private Boolean isClient;
    private String name;
    private String address;
}
