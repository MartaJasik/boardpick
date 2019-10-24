package pl.coderslab.boardpick.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {

    @Id
    private long id;
    private String cover;
    private String name;
    private String published;
    private String addedToDb;
    private int minPlayers;
    private int maxPlayers;
    private int minPlaytime;
    private int maxPlaytime;
    private double rating;
    private int ranking;
    private double weight;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "game_user", joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    public Game() {
    }



    public void setAddedToDb(String addedToDb) {
        this.addedToDb = addedToDb;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getMinPlaytime() {
        return minPlaytime;
    }

    public void setMinPlaytime(int minPlaytime) {
        this.minPlaytime = minPlaytime;
    }

    public int getMaxPlaytime() {
        return maxPlaytime;
    }

    public void setMaxPlaytime(int maxPlaytime) {
        this.maxPlaytime = maxPlaytime;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", cover='" + cover + '\'' +
                ", name='" + name + '\'' +
                ", published='" + published + '\'' +
                ", addedToDb='" + addedToDb + '\'' +
                ", minPlayers=" + minPlayers +
                ", maxPlayers=" + maxPlayers +
                ", minPlaytime=" + minPlaytime +
                ", maxPlaytime=" + maxPlaytime +
                ", rating=" + rating +
                ", ranking=" + ranking +
                ", weight=" + weight +
                ", users=" + users +
                '}';
    }
}
