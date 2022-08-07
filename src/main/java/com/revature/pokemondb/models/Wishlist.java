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
import javax.persistence.Table;

import com.revature.pokemondb.models.dtos.PokemonDTO;

@Entity
@Table(name = "pokemon_wishlists", schema = "pokemon_db")
public class Wishlist {
    @Id
    @Column(name = "id", updatable = false, insertable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(targetEntity = PokemonDTO.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "pokemon_id", referencedColumnName = "id")
    private Pokemon pokemon;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User wisher;
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    public Wishlist() {

    }

    public Wishlist(long id, Pokemon pokemon, User wisher, Timestamp createdAt) {
        this.id = id;
        this.pokemon = pokemon;
        this.wisher = wisher;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public User getWisher() {
        return wisher;
    }

    public void setWisher(User wisher) {
        this.wisher = wisher;
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
                + ", wisher=" + wisher + "]";
    }

}
