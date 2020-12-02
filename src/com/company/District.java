package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class District { //nom en foction du perso dessus
    public String nom;
    public int faceVisible; //1 face suspect 0 face vide
    public int orientation; //N = 0, E = 1, S = 2, Ouest = 3, c'est le bout du T dans cette direction
    public int typeDistrict; //0 case vide, 1 case suspect, 2 case detective
    public ImageIcon faceSus;
    public ImageIcon faceVide;

    //creer un dico avec en clee face visible et obj picture? ou juste on rajoute au constructeur les 2 icons

    public District[][] baseDeDonnee; //ne pas modifier !!!!!!!!!!

    public District(String nom, int orientation, int typeDistrict,int faceVisible, ImageIcon faceSus) {
        this.nom = nom;
        this.orientation = orientation;
        this.typeDistrict = typeDistrict;
        this.faceVisible = faceVisible;
        this.faceSus = faceSus;
    }

    public ImageIcon getFaceSus() {
        return faceSus;
    }

    public void setFaceSus(ImageIcon faceSus) {
        this.faceSus = faceSus;
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

    public ImageIcon changeSize(String picture, int width, int height){
        ImageIcon imageIcon = new ImageIcon(picture); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back
        return imageIcon;
    }

    public void setUp(){
        //on creer une liste random pour avoir des orientations de carte random
        Random random = new Random();
        int[] orienté = new int[9];
        for(int i = 0; i< 8; i++){
            int rand = random.nextInt(3 - 0 + 1);
            orienté[i] = rand;
        }

        District Mme = new District("Madame",orienté[0],1,1,changeSize("image/faceSus/Madame.png",160,160));
        District SGT = new District("SGT Goodley",orienté[1],1,1, changeSize("image/faceSus/Sgt._Goodley.png",160,160));
        District JB = new District("Jeremy Bert",orienté[2],1,1, changeSize("image/faceSus/Jeremy_Bert.png",160,160));
        District WG = new District("William Gull",orienté[3],1,1, changeSize("image/faceSus/William_Gull.png",160,160));
        District Ms = new District("Miss Stealthy",orienté[4],1,1, changeSize("image/faceSus/Miss_Stealthy.png",160,160));
        District Gs = new District("John Smith",orienté[5],1,1, changeSize("image/faceSus/John_Smith.png",160,160));
        District Insp = new District("Insp. Lestrade",orienté[6],1,1, changeSize("image/faceSus/Insp._Lestrade.png",160,160));
        District JP = new District("John Pizer",orienté[7],1,1, changeSize("image/faceSus/John_Pizer.png",160,160));
        District JL = new District("Joseph Lane",orienté[8],1,1, changeSize("image/faceSus/Joseph_Lane.png",160,160)); // spécial si retournéé croix preciser dans suspect district

        //des district speciaux avec les inspecteurs dessus, regles diff
        District Holmes = new District("Holmes",1,2,1,changeSize("image/detective/holmes.png",160,160));
        District Watson = new District("Watson",1,2,1,changeSize("image/detective/watson.png",160,160));
        District Toby = new District("Toby",1,2,1,changeSize("image/detective/toby.png",160,160));

        //districtes juste case ou y'as rien
        District Vide = new District("Vide",1,0,1,changeSize("image/void.png",160,160));

        District[][] bdd = new District[3][9];//on cree tablo de 3 lignes 9 colones

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
