package com.company;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class District { //nom en foction du perso dessus
    public String nom;
    public int faceVisible; //1 face suspect 0 face vide
    public int orientation; //N = 0, E = 1, S = 2, Ouest = 3, c'est le bout du T dans cette direction
    public int typeDistrict; //0 case vide, 1 case suspect, 2 case detective
    public District[][] baseDeDonnee; //ne pas modifier !!!!!!!!!!

    public District(String nom, int orientation, int typeDistrict,int faceVisible) {
        this.nom = nom;
        this.orientation = orientation;
        this.typeDistrict = typeDistrict;
        this.faceVisible = faceVisible;
    }

    public District(){ }






    public District[][] getBaseDeDonnee() { return baseDeDonnee; }

    public void setBaseDeDonnee(District[][] baseDeDonnee) { this.baseDeDonnee = baseDeDonnee; }

    public int getFaceVisible() {
        return faceVisible;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setFaceVisible(int faceVisible) {
        this.faceVisible = faceVisible;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public int getTypeDistrict() {
        return typeDistrict;
    }

    public void setTypeDistrict(int typeDistrict) {
        this.typeDistrict = typeDistrict;
    }

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }

    public void setUp(){
        //on creer une liste random pour avoir des orientations de carte random
        Random random = new Random();
        int[] orienté = new int[9];
        for(int i = 0; i< 8; i++){
            int rand = random.nextInt(3 - 0 + 1);
            orienté[i] = rand;
        }

        District Mme = new District("Madame",orienté[0],1,1);
        District SGT = new District("SGT Goodley",orienté[1],1,1);
        District JB = new District("Jeremy Bert",orienté[2],1,1);
        District WG = new District("William Gull",orienté[3],1,1);
        District Ms = new District("Miss Stealthy",orienté[4],1,1);
        District Gs = new District("Gensmith",orienté[5],1,1);
        District Insp = new District("Insp. Lestrade",orienté[6],1,1);
        District JP = new District("John Piser",orienté[7],1,1);
        District JL = new District("Joseph Lane",orienté[8],1,1); // spécial si retournéé croix preciser dans suspect district

        //des district speciaux avec les inspecteurs dessus, regles diff
        District Holmes = new District("Holmes",1,2,1);
        District Watson = new District("Watson",1,2,1);
        District Toby = new District("Toby",1,2,1);

        //districtes juste case ou y'as rien
        District Vide = new District("Vide",1,0,1);

        District[][] bdd = new District[3][9];

        //on schuffle cette liste pour que les districtes soient tjr à des positions différentes
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
                    a = a+1; //passe au prochain detect
                    if(a == 3){
                        break;
                    }
                } else {
                    bdd[i][j] = detect[3];
                    break;
                }

            }
        }
        System.out.println(bdd[0][1]);
        setBaseDeDonnee(bdd);
    }
}
