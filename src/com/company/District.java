package com.company;

public class District { //nom en foction du perso dessus
    public String nom;
    public int faceVisible; //1 face suspect 0 face vide
    public int orientation; //N E S Ouest
    public int typeDistrict; //0 case vide, 1 case suspect, 2 case detective

    public District(String nom, int orientation, int typeDistrict) {
        this.nom = nom;
        this.orientation = orientation;
        this.typeDistrict = typeDistrict;
    }

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
}
