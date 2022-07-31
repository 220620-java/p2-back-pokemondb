package com.revature.pokemondb.models;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pokemon_wishlists")
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "id")
    @Column(name = "pokemon_id")
    private Pokemon pokemon;
    @ManyToOne
    @JoinColumn(name = "id")
    @Column(name = "user_id")
    private int wisher;
    private Instant createdAt;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Wishlist> lists;

    public Wishlist() {
        this.id = 0;
        //this.pokemon = pokemon();
        this.wisher = 0;
        this.createdAt = Instant.now();
        this.lists = new ArrayList<Wishlist>();
    }

    public Wishlist(long id, Pokemon pokemon, int wisher, Instant createdAt, List<Wishlist> lists) {
        this.id = id;
        this.pokemon = pokemon;
        this.wisher = wisher;
        this.createdAt = createdAt;
        this.lists = lists;
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

    public int getWisher() {
        return wisher;
    }

    public void setWisher(int wisher) {
        this.wisher = wisher;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public List<Wishlist> getLists() {
        return lists;
    }

    public void setLists(List<Wishlist> lists) {
        this.lists = lists;
    }

    @Override
    public String toString() {
        return "Wishlist [createdAt=" + createdAt + ", id=" + id + ", lists=" + lists + ", pokemon=" + pokemon
                + ", wisher=" + wisher + "]";
    }

    
}
