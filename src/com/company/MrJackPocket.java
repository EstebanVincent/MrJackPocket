package com.company;
import java.util.*;


public class MrJackPocket {

    public District[][] board; //5 5 pour mettre les detectives
    public int tour = 1;
    public int[] temps; //jeton temps double face 0 = tour et 1 = sablier
    public Player currentPlayer;

    public MrJackPocket() {

    }

    Action action = new Action(this);
    SuspectDistrict sus = new SuspectDistrict(this); //permet de transferé la valeur des atributs de mrjack a suspectDis
    Player player = new Player();
    Alibi alibi = new Alibi();


    public void play(){//les actions possible ne d'updatesPas probleme d'opp
        débutPartie();
        printBoard();

        for (int i = 1; i < 9; i++ ) {//i est le compteur de tour

            if (i % 2 == 1){ // tour impair, enqueteurs commence
                switchPlayer();

                action.initialisePossibleAction();
                action.printActionPossible();
                String act1 = action.chooseAction(); //ajouter un verif que c possible avec do while
                joueAction(act1);
                action.updateActionPossible(act1);


                switchPlayer();
                action.printActionPossible();
                String act2 = action.chooseAction(); //ajouter un verif que c possible avec do while
                joueAction(act2);
                action.updateActionPossible(act2);

                action.printActionPossible();
                String act3 = action.chooseAction(); //ajouter un verif que c possible avec do while
                joueAction(act3);
                action.updateActionPossible(act3);


                switchPlayer();
                action.printActionPossible();
                String act4 = action.actionsPossible[0]; //ajouter un verif que c possible avec do while
                //joueAction(act4);

                String[] retour = sus.see(district.baseDeDonnee[1][0]);

                //nefonctionnePas
                for (int k = 0; k < retour.length ; k++){
                    System.out.println(retour[k]);
                }

            } else { // tour pair Jack commence

                switchPlayer();
                action.printActionPossibleRetournée();
                String act1 = action.chooseAction(); //ajouter un verif que c possible avec do while
                joueAction(act1);
                action.updateActionPossibleRetournée(act1);


                switchPlayer();
                action.printActionPossibleRetournée();
                String act2 = action.chooseAction(); //ajouter un verif que c possible avec do while
                joueAction(act2);
                action.updateActionPossibleRetournée(act2);

                action.printActionPossibleRetournée();
                String act3 = action.chooseAction(); //ajouter un verif que c possible avec do while
                joueAction(act3);
                action.updateActionPossibleRetournée(act3);


                switchPlayer();
                action.printActionPossibleRetournée();
                String act4 = action.actionsPossibleRetournée[0]; //ajouter un verif que c possible avec do while
                //joueAction(act4);
            }




        }




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

    District district = new District();

    /*
    //permet de transferé la valeur des atributs de player a mrjack
    private Player player;
    public MrJackPocket(Player player) {
        this.player = player;
    }

     */

    public void débutPartie(){
        //lance la game behind the scene, initialise le plateau, les joueurs, choisi Jack
        initialiseBoard();

        player.initialiseName();
        setCurrentPlayer(player.players[0]); //enqueteur commence
        alibi.initialisePiocheAlibi();//on crée la pioche

        Alibi alibiJack = alibi.choixJack(); //alibiJack est la carte alibi de Jack, et la pioche est update
        String jack = alibiJack.getNom(); //jack est le nom en string de son perso
        alibi.initialiseAlibiJack(alibiJack); //on initialise les carte alibi en possession de Jack

        action.initialiseJetons();


    }

    public void switchPlayer(){
        if (currentPlayer == player.players[1]){
            setCurrentPlayer(player.players[0]);
        } else {
            setCurrentPlayer(player.players[1]);
        }
        System.out.println("Au tour de " + currentPlayer.getName());
    }




    private void initialiseBoard(){ //fini
        District[][] board = new District[5][5];
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
                System.out.print(board[i][j].getNom() + " " +board[i][j].getOrientation() + "\t");
            }
            System.out.println();
        }
    }

    public void joueAction(String actionChoisie){
        if (Objects.equals(actionChoisie, new String("rotation1")) || Objects.equals(actionChoisie, new String("rotation2"))){
            action.rotation();
            printBoard();
        } else if (Objects.equals(actionChoisie, new String("échange"))){
            action.échange();
            printBoard();
        } else if (Objects.equals(actionChoisie, new String("getAlibi"))){
            action.alibi(); //alibi est null??
        } else if (Objects.equals(actionChoisie, new String("holmes"))){
            action.deplacementDetective(district.baseDeDonnee[1][0]);
            printBoard();
        } else if (Objects.equals(actionChoisie, new String("watson"))){
            action.deplacementDetective(district.baseDeDonnee[1][1]);
            printBoard();
        } else if (Objects.equals(actionChoisie, new String("toby"))){
            action.deplacementDetective(district.baseDeDonnee[1][2]);
            printBoard();
        } else if (Objects.equals(actionChoisie, new String("joker"))){
            System.out.println("Quel Detective veux-tu déplacer ?");
            Scanner scanner = new Scanner(System.in);
            String detective = scanner.nextLine();
            if (Objects.equals(detective, new String("holmes"))){
                action.deplacementDetective(district.baseDeDonnee[1][0],1);
            } else if (Objects.equals(actionChoisie, new String("watson"))){
                action.deplacementDetective(district.baseDeDonnee[1][1],1);
            } else if (Objects.equals(actionChoisie, new String("toby"))){
                action.deplacementDetective(district.baseDeDonnee[1][2],1);
            }
            printBoard();
        }
    }

    public int[] findPosition(District district){
        int[] defaut = {0,0};
        for (int i = 0; i< board.length; i++){
            for (int j = 0; j< board.length; j++){
                if (board[i][j].getNom() == district.getNom()){
                    int[] a = {i,j};
                    return a;
                }
            }
        }
        return defaut;
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

    public boolean appelATemoin(){ //true si jack visible, false sinon
        return false;
    }

    public boolean plus6Sablier(){ //dit true si oui
        return false;
    }
    public boolean only1Suspect(){ //dit true si oui
        return false;
    }
}
