package com.company;


import java.util.*;

public class District {

    //Attributs
    private String nom;
    private boolean faceVisible; //1 face suspect 0 face vide
    private int orientation; //N = 0, E = 1, S = 2, Ouest = 3, c'est le bout du T dans cette direction


    private District[][] baseDeDonnee;

    //Constructeurs
    public District(){}

    public District(String nom, int orientation, boolean faceVisible) {
        this.nom = nom;
        this.orientation = orientation;
        this.faceVisible = faceVisible;
    }

    public District(String nom) {
        this.nom = nom;
    }

    //Getters and Setters

    public District[][] getBaseDeDonnee() { return baseDeDonnee; }

    public void setBaseDeDonnee(District[][] baseDeDonnee) { this.baseDeDonnee = baseDeDonnee; }

    public boolean getFaceVisible() {
        return faceVisible;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setFaceVisible(boolean faceVisible) {
        this.faceVisible = faceVisible;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public String getNom() { return nom; }


    //Méthode

    //créer une base de données qui contient tout les éléments du plateau
    public void setUp(){
        //on créer une liste random pour avoir des orientations de carte random
        Random random = new Random();
        int[] orienté = new int[9];
        for(int i = 0; i< 8; i++){
            int rand = random.nextInt(3 + 1);
            orienté[i] = rand;
        }

        District Mme = new District("Madame",orienté[0],true);
        District SGT = new District("SGT Goodley",orienté[1],true);
        District JB = new District("Jeremy Bert",orienté[2],true);
        District WG = new District("William Gull",orienté[3],true);
        District Ms = new District("Miss Stealthy",orienté[4],true);
        District Gs = new District("John Smith",orienté[5],true);
        District Insp = new District("Insp. Lestrade",orienté[6],true);
        District JP = new District("John Pizer",orienté[7],true);
        District JL = new District("Joseph Lane",orienté[8],true);

        //des district spéciaux avec les inspecteurs dessus
        District Holmes = new District("Holmes");
        District Watson = new District("Watson");
        District Toby = new District("Toby");

        //districts vide
        District Vide = new District("Vide");

        District[][] bdd = new District[3][9];//on cree tableau de 3 lignes 9 colones

        District[] perso = {Mme, SGT, JB, WG, Ms, Gs, Insp, JP, JL};
        District[] detect = {Holmes, Watson, Toby, Vide};

        //int a = 0; //indice pour parcourir perso
        for(int i = 0; i < bdd.length; i++){
            int a = 0; //indice pour parcourir perso
            for (int j = 0; j < bdd[i].length; j++){
                if(i == 0){
                    bdd[i][j] = perso[j];
                    a = a+1; //passe au prochain perso
                } else if(i == 1) {
                    bdd[i][j] = detect[j];
                    a = a+1; //passe au prochain detective
                    if(a == 3){ //on a assigné les détectives, le reste reste null
                        break;
                    }
                } else {
                    bdd[i][j] = detect[3];
                    break; //on a  assigné vide, le reste reste null
                }

            }
        }
        setBaseDeDonnee(bdd);
    }
}
