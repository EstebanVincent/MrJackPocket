package com.company;

public class Player {
    //Attributs
    public String name;
    public Player[] players;

    //Constructeurs
    public Player(String name) {
        this.name = name;
    }
    public Player() { }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setPlayers(Player[] players) { this.players = players; }

    //Méthode

    //initialise les players
    public void initialiseName(){
        Player jack = new Player("Mr. Jack");
        Player enqueteur = new Player("Enquêteur");
        setPlayers(new Player[]{jack, enqueteur});
    }


}
