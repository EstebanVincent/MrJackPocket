package com.company;

public class SuspectDistrict extends District{
    public int seable;

    public SuspectDistrict(String nom, int orientation, int typeDistrict, int faceVisible) {
        super(nom, orientation, typeDistrict, faceVisible);
    }


/*

    public int[] see(District district){
        int[] a = new int[2];
        return a;

        if (district == JL){
            return {2,2,2,2};
        }

 */
/*
        if orientation = nord
        in[] a = new int[]
            a[1] = par le nord seeTrough
            a[2] = par le sud seeTrough
            a[3] = par le est seeTrough
            a[0] = par le ouest seeTrough




    }

      */
    public int seeTrough(){//2 si on peut voir a travers, 1 si on peu voir le perso, 0 si on voit pas

        return 1;
    }


}
