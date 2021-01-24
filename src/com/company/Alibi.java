package com.company;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Alibi {

    //Attributs
    private String nom;
    private int sablier; //0 à 2

    private Alibi[] piocheAlibi = new Alibi[9];

    //Constructeurs
    public Alibi(String nom, int sablier) { //constructeur pour les cartes alibi
        this.nom = nom;
        this.sablier = sablier;
    }

    public Alibi() {} //constructeur vide pour le début

    //Getters and Setters

    public String getNom() {
        return nom;
    }


    public int getSablier() {
        return sablier;
    }


    public void setPiocheAlibi(Alibi[] piocheAlibi) {
        this.piocheAlibi = piocheAlibi;
    }


    //Méthodes

    //créer la pioche aléatoire
    public void initialisePiocheAlibi(){
        Alibi Mme = new Alibi("Madame",2);
        Alibi SGT = new Alibi("SGT Goodley",0);
        Alibi JB = new Alibi("Jeremy Bert",1);
        Alibi WG = new Alibi("William Gull",1);
        Alibi Ms = new Alibi("Miss Stealthy",1);
        Alibi JS = new Alibi("John Smith",1);
        Alibi Insp = new Alibi("Insp. Lestrade",0);
        Alibi JP = new Alibi("John Piser",1);
        Alibi JL = new Alibi("Joseph Lane",1);

        setPiocheAlibi(new Alibi[] {Mme, SGT, JB, WG, Ms, JS, Insp, JP, JL});

        //la pioche est différente a chaque partie
        List<Alibi> list = Arrays.asList(piocheAlibi);
        Collections.shuffle(list);
        list.toArray(piocheAlibi);
    }

    // renvoi qui est mrJack et update la pioche
    public Alibi choixJack(){
        Random random = new Random();
        int rand = random.nextInt(8 + 1);//int random entre 0 et 8
        Alibi tempo = piocheAlibi[rand]; //valeur tempo car sinon indexOutOfBounds avec update
        updatePiocheAlibi(piocheAlibi[rand]);//la carte pioché n'est plus dans la pioche
        return tempo;
    }

    //return la carte pioché et update la pioche
    public Alibi draw1Carte(){
        Random random = new Random();
        int rand = random.nextInt((piocheAlibi.length-1) + 1);//int random entre 0 et la longueur de piocheAlibi-1
        updatePiocheAlibi(piocheAlibi[rand]);//la carte pioché n'est plus dans la pioche
        return piocheAlibi[rand];
    }

    //update la pioche en enlevant la carte indiquée
    public void updatePiocheAlibi(Alibi cartePioché){
        Alibi[] newPioche = new Alibi[0];
        for (Alibi alibi : piocheAlibi) {
            if (alibi != cartePioché) {
                newPioche = Arrays.copyOf(newPioche, newPioche.length + 1);// append en python
                newPioche[newPioche.length - 1] = alibi;
            }
        }
        piocheAlibi = newPioche;
    }
}
