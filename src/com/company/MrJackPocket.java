package com.company;

public class MrJackPocket {
    public int tour;


    public void play(){

    }
    public int Win(){ //0 si personne gagne, 1 si MrJack gagne, 2 si detectives gagne, 3 si les 2 gagne
        boolean jack = plus6Sablier();
        boolean det =  only1Suspect();
        if (jack != det){
            if (jack){
                return 1;
            } else {
                return 2;
            }
        } else if (!jack){ //si les 2 sont false
            return 0;
        } else { //si les 2 true, donc cas particulier
            return 3;
        }
    }
    public boolean plus6Sablier(){ //dit true si oui
        return false;
    }
    public boolean only1Suspect(){ //dit true si oui
        return false;
    }
    public boolean
}
