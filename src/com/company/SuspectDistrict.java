package com.company;

import java.util.ArrayList;
import static java.lang.Math.abs;

public class SuspectDistrict extends District{

    private SuspectDistrict(String nom, int orientation,  boolean faceVisible) {
        super(nom, orientation, faceVisible);
    }

    //permet de transférer la valeur des attributs de mrjackpocket à suspectDistrict
    private MrJackPocket mrJackPocket;
    public SuspectDistrict(MrJackPocket mrJackPocket) {
        this.mrJackPocket = mrJackPocket;
    }


    //Méthodes

    //demande les coordonnées du detective, l'orientation de son regard
    //renvoi une liste de qui le detective vois
    protected ArrayList<String> see(int[] observ, int orientationRegard){
        //N = 0, E = 1, S = 2, Ouest = 3
        //en entree les coordonne en string ex : 12, 22, et dir du regard en int
        ArrayList<String> sortie = new ArrayList<>();

        boolean visionBloque = false;
        int[] move = new int[2];

        //on défini les mouvements a faire sur les districts a partir de l'observateur
        if(orientationRegard == 0){
            move[0] = -1;
            move[1] = 0;
        } else if (orientationRegard == 1){
            move[0] = 0;
            move[1] = 1;
        } else if (orientationRegard == 2){
            move[0] = 1;
            move[1] = 0;
        } else {
            move[0] = 0;
            move[1] = -1;
        }

        //partie principale de la méthode
        int i = 1;
        do {
            District observé = mrJackPocket.getBoard()[observ[0]+move[0]*i][observ[1]+move[1]*i];//la case observé, les *i sont pour passer à la case suivante si on vois au travers
            if(observé.getOrientation() == orientationRegard){ //on regarde le mur directement (dos du T)
                visionBloque = true; //la boucle s'arrête
            } else if (abs(observé.getOrientation()-orientationRegard) == 2) { //on regarde la base du T
                if(observé.getFaceVisible()){ //si face suspect
                    sortie.add(observé.getNom());
                }
                visionBloque = true; // la fin du T bloque la vision, la boucle s'arrête
            } else if (observé.getNom() == "Joseph Lane" && observé.getFaceVisible() == false){//exception si case coté vide car pas en forme de T mais X
                i += 1;
            }  else { //on voit a travers le T
                if(observé.getFaceVisible()){//si face suspect
                    sortie.add(observé.getNom());
                }
                i += 1;
            }
        } while(!visionBloque && i != 4); //le i est la si on peut voir a travers le plateau complet afin que la boucle s'arrête
        return sortie;
    }
}
