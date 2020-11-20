package com.company;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MrJackPocket {

    public District[][] board; //5 5 pour mettre les detectives
    public int tour;
    public int[] temps; //jeton temps double face 0 = tour et 1 = sablier

    public MrJackPocket() {

    }


    public void play(){
        initialiseBoard();
        printBoard();
    }

    public District[][] getBoard() {
        return board;
    }

    public void setBoard(District[][] board) {
        this.board = board;
    }

    public int getTour() {
        return tour;
    }

    public void setTour(int tour) {
        this.tour = tour;
    }

    public int[] getTemps() {
        return temps;
    }

    public void setTemps(int[] temps) {
        this.temps = temps;
    }




    private void initialiseBoard(){ //fini
        District[][] board = new District[5][5];
        District district = new District();
        district.setUp();

        //les cases exterieurs sont vides
        for (int i = 0; i < 5; i++) {//ligne
            board[0][i] = district.baseDeDonnee[2][0]; //exception
            for (int j = 0; j < 5; j++) {//colone
                if (j == 0 || j == 4) {
                    board[i][j] = district.baseDeDonnee[2][0]; //Vide
                }
                if (i == 0 || i == 4){
                    board[i][j] = district.baseDeDonnee[2][0];//Vide
                }
            }
        }
        //board[0][0] = board[4][0] =board[0][4] = board[4][4] = null;
        board[1][0] = district.baseDeDonnee[1][0]; //Holmes
        board[1][4] = district.baseDeDonnee[1][1]; //Watson
        board[4][2] = district.baseDeDonnee[1][2]; //Toby

        //on schuffle cette liste pour que les districtes soient tjr à des positions différentes
        District[] perso = district.baseDeDonnee[0];
        List<District> list = Arrays.asList(perso);
        Collections.shuffle(list);
        list.toArray(perso);

        int a = 0; //indice pour parcourir perso

        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                board[i][j] = perso[a];
                a = a+1; //passe au prochain perso
            }
        }

        setBoard(board); //on set la valeur de board locale à celle de la classe
    }

    private void printBoard(){// faire un truc graphique
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(board[i][j].getNom() + "\t");
            }
            System.out.println();
        }
    }

    public int win(){ //0 si personne gagne, 1 si MrJack gagne, 2 si detectives gagne, 3 si les 2 gagne
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
}
