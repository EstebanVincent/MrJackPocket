package com.company;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Alibi {
    public String nom; //j'ai changer en String c'est mieux avec des nom pour les cartes
    public int sablier; //0 à 2

    public Alibi[] piocheAlibi = new Alibi[9];
    public Alibi[] carteJack = new Alibi[1];

    public Alibi(String alibi, int sablier) { //constructeur pour les cartes alibli
        this.nom = nom;
        this.sablier = sablier;
    }

    public Alibi() {} //constructeur vide pour le début


    Action action = new Action(this);



    public String getNom() {
        return nom;
    }

    public void setNom(String alibi) {
        this.nom = alibi;
    }

    public int getSablier() {
        return sablier;
    }

    public void setSablier(int sablier) {
        this.sablier = sablier;
    }

    public Alibi[] getPiocheAlibi() {
        return piocheAlibi;
    }

    public void setPiocheAlibi(Alibi[] piocheAlibi) {
        this.piocheAlibi = piocheAlibi;
    }

    public Alibi[] getCarteJack() {
        return carteJack;
    }

    public void setCarteJack(Alibi[] carteJack) {
        this.carteJack = carteJack;
    }

    public void initialisePiocheAlibi(){ //crée la pioche
        Alibi Mme = new Alibi("Madame",2);
        Alibi SGT = new Alibi("SGT Goodley",0);
        Alibi JB = new Alibi("Jeremy Bert",1);
        Alibi WG = new Alibi("William Gull",1);
        Alibi Ms = new Alibi("Miss Stealthy",1);
        Alibi JS = new Alibi("John Smith",1);
        Alibi Insp = new Alibi("Insp. Lestrade",0);
        Alibi JP = new Alibi("John Piser",1);
        Alibi JL = new Alibi("Joseph Lane",1);

        piocheAlibi = new Alibi[] {Mme, SGT, JB, WG, Ms, JS, Insp, JP,JL};

        //la pioche est diférente a chaque partie
        List<Alibi> list = Arrays.asList(piocheAlibi);
        Collections.shuffle(list);
        list.toArray(piocheAlibi);
    }



    public Alibi choixJack(){ // renvoi qui est mrJack et update la pioche
        Random random = new Random();
        int rand = random.nextInt(8 - 0 + 1);//int random entre 0 et 8
        Alibi tempo = piocheAlibi[rand]; //valeur tempo car sinon indexOutofBounds avec le update
        updatePiocheAlibi(piocheAlibi[rand]);//la carte pioché n'est plus dans la pioche
        return tempo;
    }

    public Alibi draw1Carte(){//return la carte pioché et update la pioche
        Random random = new Random();
        int rand = random.nextInt((piocheAlibi.length-1) - 0 + 1);//int random entre 0 et la longueur de piocheAlibi-1
        updatePiocheAlibi(piocheAlibi[rand]);//la carte pioché n'est plus dans la pioche
        return piocheAlibi[rand];

    }

    public void updatePiocheAlibi(Alibi cartePioché){ //update la pioche en enlevant la carte pioché
        Alibi[] newPioche = new Alibi[0];
        for(int i = 0; i < piocheAlibi.length; i++){
            if(piocheAlibi[i] != cartePioché){
                newPioche = Arrays.copyOf(newPioche, newPioche.length + 1);// append en python
                newPioche[newPioche.length - 1] = piocheAlibi[i];
            }
        }
        piocheAlibi = newPioche;
    }

    public void initialiseAlibiJack(Alibi mrJack){//mettre en entré le jack optenu par choixJack
        carteJack[0] = mrJack;
    }

    public void updateAlibiJack(Alibi cartePioché){ //ajoute les cartes pioché par Jack
        carteJack = Arrays.copyOf(carteJack, carteJack.length + 1);// on crée une copie qui ecrase l'originale et qui est plus longue de 1 (append en python)
        carteJack[carteJack.length - 1] = cartePioché; //On rajoute le nom du personnage dans la case car il est visible
    }

    public int nbSablierAlibi(){ //return le nb de sablier de Jack uniquement avec les cartes alibi
        int a = 0;
        for (int i = 0; i < carteJack.length; i++){
            a += carteJack[0].sablier; //add le nb de sablier de la carte
        }
        return a;
    }

}
