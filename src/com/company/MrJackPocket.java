package com.company;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class MrJackPocket {

    public District[][] board; //5 5 pour mettre les detectives
    public int tour = 1;
    public int[] temps; //jeton temps double face 0 = tour et 1 = sablier
    public Player currentPlayer;

    public MrJackPocket() {

    }



    public void play(){
        débutPartie();
        for (int i = 1; i < 9; i++ ) {//i est le compteur de tour
            if (i % 2 == 1){ // tour impair, enqueteurs commence

            } else { // tour pair Jack commence

            }
        }


        SuspectDistrict sus = new SuspectDistrict(this); //permet de transferé la valeur des atributs de mrjack a suspectDis
        Action action = new Action(this);
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

    public Player getCurrentPlayer() { return currentPlayer; }

    public void setCurrentPlayer(Player currentPlayer) { this.currentPlayer = currentPlayer; }


    //permet de transferé la valeur des atributs de player a mrjack
    private Player player;
    public MrJackPocket(Player player) {
        this.player = player;
    }

    public void débutPartie(){
        //lance la game behind the scene, initialise le plateau, les joueurs, choisi Jack
        initialiseBoard();

        player.initialiseName();
        setCurrentPlayer(player.players[1]); //enqueteur commence

        Alibi alibi = new Alibi();

        alibi.initialisePiocheAlibi();//on crée la pioche
        Alibi alibiJack = alibi.choixJack(); //alibiJack est la carte alibi de Jack, et la pioche est update
        String jack = alibiJack.getNom(); //jack est le nom en string de son perso
        alibi.initialiseAlibiJack(alibiJack); //on initialise les carte alibi en possession de Jack
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
        //on fait en sorte que les inspecteurs soient en face d'un mur
        board[1][1].setOrientation(1);
        board[1][3].setOrientation(3);
        board[3][2].setOrientation(0);

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
