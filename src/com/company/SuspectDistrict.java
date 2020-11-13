package com.company;

public class SuspectDistrict extends District{
    public int seable;


    public SuspectDistrict(String nom, int orientation, int typeDistrict) {
        super(nom, orientation, typeDistrict);
    }
    /*
    public int[] see(){

        if orientation = nord
        in[] a = new int[]
            a[1] = par le nord seeTrough
            a[2] = par le sud seeTrough
            a[3] = par le est seeTrough
            a[0] = par le ouest seeTrough


    }
    */
        
    public int seeTrough(){//dit si on peut voir au travers completement = 2, si juste le perso dedans 1, si non 0
        return 1;
    } //2 si on peut voir a travers, 1 si on peu voir le perso, 0 si on voit pas


}
