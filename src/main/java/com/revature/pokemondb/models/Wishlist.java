package com.revature.pokemondb.models;

import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.revature.pokemondb.models.dtos.PokemonDTO;

@Entity
@Table(name = "pokemon_wishlists")
public class Wishlist {
    @Id
    @Column(name = "id", updatable = false, insertable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(targetEntity = PokemonDTO.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "pokemon_id", referencedColumnName = "id")

    private PokemonDTO pokemon;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")

    private User wisher;
    private Instant createdAt;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Wishlist> lists;

    public Wishlist() {

    }

    public Wishlist(long id, PokemonDTO pokemon, User wisher, Instant createdAt, List<Wishlist> lists) {
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

    public PokemonDTO getPokemon() {
        return pokemon;
    }

    public void setPokemon(PokemonDTO pokemon) {
        this.pokemon = pokemon;
    }

    public User getWisher() {
        return wisher;
    }

    public void setWisher(User wisher) {
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
