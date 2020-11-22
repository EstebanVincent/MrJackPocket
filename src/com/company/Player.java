package com.company;

public class Player {
    public String name;
    public Player[] players;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPlayers(Player[] players) { this.players = players; }

    public Player(String name) {
        this.name = name;
    }
    public Player() { }

    MrJackPocket mrJackPocket = new MrJackPocket(this);

    public void initialiseName(){
        Player jack = new Player("Mr. Jack");
        Player enqueteur = new Player("Enqueteur");
        setPlayers(new Player[]{jack, enqueteur});
    }


}
