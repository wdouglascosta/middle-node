package br.uem.middlenode.repository;


import br.uem.middlenode.model.Signee;
import br.uem.middlenode.model.Subscriber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainTable implements Serializable {
    private static MainTable instance = new MainTable();

    public static MainTable getInstance(){
        return instance;
    }

    private Set<Signee> subscriptions;
    private Set<Signee> routing;
    private List<Subscriber> neighborhood;

    public MainTable() {
        this.subscriptions = new HashSet<>();
        this.routing = new HashSet<>();
        this.neighborhood = new ArrayList<>();
    }

    public void addSubscription(Signee signee){
        this.subscriptions.add(signee);
    }

    public void addRoute(Signee signee){
        this.routing.add(signee);
    }

    public void addNeighbor(Subscriber subscriber){
        this.neighborhood.add(subscriber);
    }

    public void setNeighborhood(List<Subscriber> neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Set<Signee> getSubscriptions() {
        return subscriptions;
    }

    public Set<Signee> getRouting() {
        return routing;
    }

    public List<Subscriber> getNeighborhood() {
        return neighborhood;
    }
}
