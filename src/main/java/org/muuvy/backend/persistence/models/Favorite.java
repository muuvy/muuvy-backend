package org.muuvy.backend.persistence.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Favorite.findAll", query = "SELECT t FROM Favorite t")
public class Favorite {
    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Favorite () {

    }

    public Favorite(String movieId){
        this.id = movieId;
    }
}
