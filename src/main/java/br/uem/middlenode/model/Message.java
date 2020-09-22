package br.uem.middlenode.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Message {
    private Subject subject;
    private String message;
}
