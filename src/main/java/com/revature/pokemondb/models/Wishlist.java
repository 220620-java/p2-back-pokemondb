package com.revature.pokemondb.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.revature.pokemondb.models.dtos.PokemonDTO;

@Entity
@Table(name = "pokemon_wishlists", schema = "pokemon_db")
public class Wishlist {
    @Id
    @Column(name = "id", updatable = false, insertable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "pokemon_id")
    private PokemonDTO pokemon;

    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    public Wishlist() {
    }

    public Wishlist(long id, User user, PokemonDTO pokemon, Timestamp createdAt) {
        this.id = id;
        this.pokemon = pokemon;
        this.user = user;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PokemonDTO getPokemon() {
        return pokemon;
    }

    public void setPokemon(PokemonDTO pokemon) {
        this.pokemon = pokemon;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }


    @Override
    public String toString() {
        return "Wishlist [createdAt=" + createdAt + ", id=" + id + ", pokemon=" + pokemon
                + ", User=" + user + "]";
    }

}
