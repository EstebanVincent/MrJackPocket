package com.company;

import java.util.Random;

public class Alibi {
    public String alibi; //j'ai changer en String c'est mieux avec des nom pour les cartes
    public int sablier; //0 à 2
    public Alibi[] piocheAlibi = new Alibi[9];

    public Alibi(String alibi, int sablier) {
        this.alibi = alibi;
        this.sablier = sablier;
    }

    private void initialiseAlibi(){
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
    }

    public Alibi choixJack(){ // qui est mrJack
        Random random = new Random();
        int rand = random.nextInt(8 - 0 + 1);
        return piocheAlibi[rand];
    }

    public void updatePiocheAlibi(){
        Alibi enleve = choixJack(); // la ca en prend un nouveau a modif en temps que valeur de classe
        Alibi[] newPioche = new Alibi[0];
        for(int i = 0; i <= 8; i++){
            if
        }
    }

}
