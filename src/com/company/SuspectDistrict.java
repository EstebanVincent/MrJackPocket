package com.company;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.*;
import static java.lang.Math.abs;

public class SuspectDistrict extends District{
    public int seable;

    public SuspectDistrict(String nom, int orientation, int typeDistrict, int faceVisible) {
        super(nom, orientation, typeDistrict, faceVisible);
    }




    public int[] see(String coord0bservateur, int orientationRegard){
        //N = 0, E = 1, S = 2, Ouest = 3
        //en entree les coordonnee en string ex : 12, 22
        String[] sortie;
        int[] observ = {valueOf(coord0bservateur.substring(0, 1)), valueOf(coord0bservateur.substring(1, 2))};


        boolean visionBloque = false;
        int[] move;


        //on défini les movemments a faire sur les districts a partir de l'observateur
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

        do {
            District observé = board[observ[0]+move[0]][observ[1]+move[1]];
            if(observé.getOrientation() == orientationRegard){ //on regarde le mur directement (dos du T)
                visionBloque = true; //la boucle s'arete
            } else if (abs(observé.getOrientation()-orientationRegard) == 2) { //on regarde la base du T
                if(faceVisible == 1){ //si face suspect
                    sortie = Arrays.copyOf(sortie, sortie.length + 1);// on crée une copie qui ecrase l'originale et qui est plus longue de 1 (append en python)
                    sortie[sortie.length - 1] = observé.getNom(); //On rajoute le nom du personnage dans la case car il est visible
                }
                visionBloque = true; // la fin du T bloque la vision, la boucle s'arete
            }



            visionBloque = true;
        } while(!visionBloque);




        int[] a = new int[2];


        int[] visibilite;
        if (district == baseDeDonnee[0][8] && baseDeDonnee[0][8].getFaceVisible() == 0){ //si le personnage est Joseph Lane et la carte est case vide
            visibilite = new int[] {2, 2, 2, 2}; //on voit a travers quel que soit l'orientation car rue en croix
        } else {

        }
        return visibilite;



        if orientation = nord
        in[] a = new int[]
            a[1] = par le nord seeTrough
            a[2] = par le sud seeTrough
            a[3] = par le est seeTrough
            a[0] = par le ouest seeTrough




    }


    public int seeTrough(String coordDistrictObserve, String coordDbservateur){ //en entree les coordonnee en string ex : 12, 22
    //faire la requete des positions avant
    //renvoir 2 si on peut voir a travers, 1 si on peu voir le perso, 0 si on voit pas
    //on suppose que districtObservateur a la vision sur observé
        Scanner scanner = new Scanner(System.in);

        System.out.println("Quel est ta position actuelle " + currentPlayer.name + " ?" ); //le joueur entre la position actuelle ex: e1
        String oldMove = scanner.nextLine();
        System.out.println("Quel est ta nouvelle position " + currentPlayer.name + " ?"); //le joueur entre la nouvelle position ex: e2
        String newMove = scanner.nextLine();
        //le currentPlayer pose probleme, sans ca fonctionne


        Position oM = new Position(oldMove.charAt(0), valueOf(oldMove.substring(1,2)));
        Position nM = new Position(newMove.charAt(0), valueOf(newMove.substring(1,2)));

        Position[] move =  new Position[2];
        move[0] = oM;
        move[1] = nM;

        setMove(move); //tableau position 2 valeurs, ancienne position et nouvelle

        int[] observe = new int[2];
        int[] observateur = new int[2];
        valueOf(coordDistrictObserve.substring(0,1));
        valueOf(coordDistrictObserve.substring(1,2));
        return 1;
    }


}
