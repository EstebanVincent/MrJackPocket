package com.company;

import java.util.*;
import java.util.List;

public class MrJackPocket{

    //Attributs
    private District[][] board = new District[5][5]; //5 5 pour mettre les detectives
    private Player currentPlayer;
    private String jack;

    private ArrayList<String> innocent = new ArrayList<>();
    private ArrayList<String> suspects = new ArrayList<>();
    protected int sabliers = 0;

    //Getters and Setters


    public District[][] getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setJack(String jack) {
        this.jack = jack;
    }


    //nouvelles instances de classes
    Action action = new Action(this);
    SuspectDistrict sus = new SuspectDistrict(this); //permet de transférer la valeur des attributs de mrjackpocket a suspectDistrict
    Player player = new Player();
    Alibi alibi = new Alibi();
    District district = new District();

    //Méthodes

    //méthode principale appelée par le main
    public void play(){
        débutPartie();
        clearConsole();
        printBoard();

        for (int i = 1; i < 9; i++ ) {//i est le compteur de tour
            if (i % 2 == 1) { // tour impair, enquêteur commence
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
                String act4 = action.chooseAction(); //ajouter un verif que c possible avec do while
                joueAction(act4);
                action.updateActionPossible(act4);

                suspects.addAll(seeTotal());
                if (suspects.contains(jack)){
                    addInnocent(notSuspect());

                } else {
                    addInnocent(suspects);
                    sabliers += 1;
                }
                end(i);
                suspects.clear();

            } else { // tour pair Jack commence
                System.out.println();
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
                String act4 = action.chooseAction(); //ajouter un verif que c possible avec do while
                joueAction(act4);
                action.updateActionPossibleRetournée(act4);

                suspects.addAll(seeTotal());
                if (suspects.contains(jack)){
                    addInnocent(notSuspect());

                } else {
                    addInnocent(suspects);
                    sabliers += 1;
                }
                end(i);
                suspects.clear();
            }
        }
    }

    //lance la game behind the scene, initialise le game, les joueurs, choisi Jack
    private void débutPartie(){
        initialiseBoard();

        player.initialiseName();
        setCurrentPlayer(player.players[0]); //enqueteur commence
        alibi.initialisePiocheAlibi();//on crée la pioche

        Alibi alibiJack = alibi.choixJack(); //alibiJack est la carte alibi de Jack, et la pioche est update
        sabliers += alibiJack.getSablier();
        setJack(alibiJack.getNom()); //jack est le nom en string de son perso

        action.initialiseJetons();

        System.out.println("Mr. Jack est "+ jack);
        pause();
    }

    //comme il n'y a pas de clear, on improvise
    private static void clearConsole() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    //change le currentPlayer
    //si on passe a l'enquêteur, le précise
    //si on passe a jack, le précise et donne le nombre de sablier
    private void switchPlayer(){
        if (currentPlayer == player.players[1]){
            setCurrentPlayer(player.players[0]);
            System.out.println("\n\tAu tour de " + currentPlayer.getName());
            System.out.println("Tu as " + sabliers + " sabliers");
        } else {
            setCurrentPlayer(player.players[1]);
            System.out.println("\n\tAu tour de " + currentPlayer.getName());
        }

    }

    //initialise le plateau et toutes les variables
    private void initialiseBoard(){
        district.setUp();
        //les cases extérieurs sont vides
        for (int i = 0; i < 5; i++) {//ligne
            board[0][i] = district.getBaseDeDonnee()[2][0]; //exception
            for (int j = 0; j < 5; j++) {//colonne
                if (j == 0 || j == 4) {
                    board[i][j] = district.getBaseDeDonnee()[2][0]; //Vide
                }
                if (i == 0 || i == 4){
                    board[i][j] = district.getBaseDeDonnee()[2][0];//Vide
                }
            }
        }
        //board[0][0] = board[4][0] =board[0][4] = board[4][4] = null;
        board[1][0] = district.getBaseDeDonnee()[1][0]; //Holmes

        board[1][4] = district.getBaseDeDonnee()[1][1]; //Watson

        board[4][2] = district.getBaseDeDonnee()[1][2]; //Toby

        //on shuffle cette liste pour que les districts soient tjr à des positions différentes
        District[] perso = district.getBaseDeDonnee()[0];
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
    }

    //affiche le plateau dans la console avec le nom du district, l'orientation du bout du "T" (0 = Nord, 1 = Est, 2 = Sud, 3 = Ouest) et si le suspect est visible (true, false)
    protected void printBoard(){
        final int lineLength = 16;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(repeatChar(' ', lineLength - board[i][j].getNom().length()) + board[i][j].getNom() + " " +board[i][j].getOrientation()+ " ");
                //permet de pas décaler le tableau
                if (board[i][j].getFaceVisible()){
                    System.out.print(board[i][j].getFaceVisible() + " ");
                } else {
                    System.out.print(board[i][j].getFaceVisible());
                }
            }
            System.out.println();
        }
    }

    //entrer le char a répéter et le nombre de fois
    //retourne un string du caractère répété n fois
    private static String repeatChar(char repeatChar, int repeatTimes) {
        String result = "";
        for(int j = 0; j < repeatTimes; j++) {
            result += repeatChar;
        }
        return result;
    }

    //sépare les cas en fonction du jeton action choisi
    //appelle les méthodes de action
    //affiche une ligne de tiret pour séparer les actions jouées avant d'afficher le plateau mis à jour
    private void joueAction(String actionChoisie){
        if (Objects.equals(actionChoisie, "rotation1") || Objects.equals(actionChoisie, "rotation2")){
            action.rotation();
            separation();
            printBoard();
        } else if (Objects.equals(actionChoisie, "échange")){
            action.échange();
            separation();
            printBoard();
        } else if (Objects.equals(actionChoisie, "getAlibi")){
            action.alibi(alibi);
            separation();
        } else if (Objects.equals(actionChoisie, "holmes")){
            action.deplacementDetective(district.getBaseDeDonnee()[1][0]);
            separation();
            printBoard();
        } else if (Objects.equals(actionChoisie, "watson")){
            action.deplacementDetective(district.getBaseDeDonnee()[1][1]);
            separation();
            printBoard();
        } else if (Objects.equals(actionChoisie, "toby")){
            action.deplacementDetective(district.getBaseDeDonnee()[1][2]);
            separation();
            printBoard();
        } else if (Objects.equals(actionChoisie, "joker")){
            //ce cas est réaliser directement dans la méthode pour raison de facilité
            if (currentPlayer == player.players[1]) { //si c'est le tour de l'enquêteur
                System.out.println("Quel Detective veux-tu déplacer ?"); //répondre holmes, watson ou toby
                Scanner scannerD = new Scanner(System.in);
                String detective = scannerD.nextLine();
                if (Objects.equals(detective, "holmes")) {
                    action.deplacementDetective(district.getBaseDeDonnee()[1][0], 1);
                } else if (Objects.equals(actionChoisie, "watson")) {
                    action.deplacementDetective(district.getBaseDeDonnee()[1][1], 1);
                } else if (Objects.equals(actionChoisie, "toby")) {
                    action.deplacementDetective(district.getBaseDeDonnee()[1][2], 1);
                }
            } else { //si c'est le tour de Jack
                System.out.println("Veux tu déplacer un detective"); //répondre Y/N
                Scanner scanner = new Scanner(System.in);
                String rep = scanner.nextLine();
                if (Objects.equals(rep, "Y")) {
                    System.out.println("Quel Detective veux-tu déplacer ?"); //répondre holmes, watson ou toby
                    Scanner scannerJ = new Scanner(System.in);
                    String detective = scannerJ.nextLine();
                    if (Objects.equals(detective, "holmes")) {
                        action.deplacementDetective(district.getBaseDeDonnee()[1][0], 1);
                    } else if (Objects.equals(actionChoisie, "watson")) {
                        action.deplacementDetective(district.getBaseDeDonnee()[1][1], 1);
                    } else if (Objects.equals(actionChoisie, "toby")) {
                        action.deplacementDetective(district.getBaseDeDonnee()[1][2], 1);
                    }
                }
            }
            separation();
            printBoard();
        }
    }

    //affiche une ligne de tiret
    private static void separation(){
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------\n");
    }

    //pause le programme pour 3s
    private static void pause(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ie) { }
    }

    //trouve la position d'un district dans le plateau
    protected int[] findPosition(District district){
        int[] défaut = {0,0};
        for (int i = 0; i< board.length; i++){
            for (int j = 0; j< board.length; j++){
                if (board[i][j].getNom().equals(district.getNom())){
                    return new int[]{i,j};
                }
            }
        }
        return défaut;
    }

    //trouve les suspects visibles par les 3 enquêteurs
    private ArrayList<String> seeTotal(){
        ArrayList<String> sH = sus.see(findPosition(district.getBaseDeDonnee()[1][0]), regard(findPosition(district.getBaseDeDonnee()[1][0])));
        ArrayList<String> sW = sus.see(findPosition(district.getBaseDeDonnee()[1][1]), regard(findPosition(district.getBaseDeDonnee()[1][1])));
        ArrayList<String> sT = sus.see(findPosition(district.getBaseDeDonnee()[1][2]), regard(findPosition(district.getBaseDeDonnee()[1][2])));

        ArrayList<String> sortie = new ArrayList<>(sH); //sortie add les districts que peux voir holmes

        List<String> sWC = new ArrayList<>(sW);
        sWC.removeAll(sortie);
        sortie.addAll(sWC); //sortie add les districts que peux voir watson qui ne sont pas des doublons la liste de base

        List<String> sTC = new ArrayList<>(sT);
        sTC.removeAll(sortie);
        sortie.addAll(sTC); //sortie add les districts que peux voir toby qui ne sont pas des doublons la liste de base

        return sortie;
    }

    //défini ou regarde un enquêteur en fonction de ses coordonnées
    private int regard(int[] coord){
        int regard;
        if (coord[0] == 0) {//si il est sur ligne 0
            regard = 2;
        } else if (coord[1] == 0){//si sur colonne 0
            regard = 1;
        } else if (coord[0] == 4){//si sur dernière ligne
            regard = 0;
        } else { //si sur dernière colonne
            regard = 3;
        }
        return regard;
    }

    //ajoute la liste donnée aux innocents sans les doublons
    //affiche la liste des innocents
    //retourne les districts des innocents
    protected void addInnocent(List<String> add){
        List<String> copy = new ArrayList<>(add);
        copy.removeAll(innocent);
        innocent.addAll(copy);
        System.out.println("\nLes innocents sont :");
        for (String s : innocent) {
            System.out.print(s + "\t, ");
            //on retourne les innocents
            for (int i = 1; i < board.length; i++) {
                for (int j = 1; j < board.length; j++) {
                    if (board[i][j].getNom().equals(s)) {
                        board[i][j].setFaceVisible(false);
                    }
                }
            }
        }
        printBoard();

    }

    //retourne une liste de noms des districts non visibles par les enquêteurs
    private ArrayList<String> notSuspect(){
        ArrayList<String> notSuspect = new ArrayList<>();
        for (int i = 0; i < district.getBaseDeDonnee()[0].length;i++){
            if (!suspects.contains(district.getBaseDeDonnee()[0][i].getNom())){
                notSuspect.add(district.getBaseDeDonnee()[0][i].getNom());
            }
        }
        return notSuspect;
    }

    //return 0 si personne gagne, 1 si MrJack gagne, 2 si detectives gagne, 3 si les 2 gagne
    private int win(){
        boolean winJack = plus6Sablier();
        boolean winDetective =  only1Suspect();
        if (winJack != winDetective){
            if (winJack){
                return 1;
            } else {
                return 2;
            }
        } else if (!winJack){ //si les 2 sont false
            return 0;
        } else { //si les 2 true, donc cas particulier
            return 3;
        }
    }

    //demande le tour
    //si il y a un vainqueur, l'affiche et stop le programme
    private void end(int turn){
        int winner = win();

        if (winner == 0){
            if (turn == 8){
                clearConsole();
                System.out.println("Mr. Jack gagne");
                System.exit(0);
            }
        }

        else if (winner == 1){
            clearConsole();
            System.out.println("Mr. Jack gagne");
            System.exit(0);
        }

        else if(winner == 2){
            clearConsole();
            System.out.println("L'enquêteur gagne");
            System.exit(0);
        }

        else if (winner == 3){
            if (turn == 8){
                if (suspects.contains(jack)){
                    clearConsole();
                    System.out.println("L'enquêteur gagne");
                    System.exit(0);
                } else {
                    clearConsole();
                    System.out.println("Mr. Jack gagne");
                    System.exit(0);
                }
            } else {
                if (suspects.contains(jack)){
                    clearConsole();
                    System.out.println("L'enquêteur gagne");
                    System.exit(0);
                }
            }
        }
    }

    //return true si Jack a plus de 6 sabliers
    private boolean plus6Sablier(){
        return sabliers > 5;
    }

    //return true si l'enquêteur a plus que 1 suspect
    private boolean only1Suspect(){
        return innocent.size() == 8;
    }
}
