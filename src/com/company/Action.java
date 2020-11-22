package com.company;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.xml.bind.SchemaOutputResolver;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.valueOf;

public class Action {
    //constructeur de création des jetons
    public int jeton; //le num du jeton
    public String recto;
    public String verso;

    public Action[] jetons; // les jetons de 1 à 4 avec leur faces
    public String[] actionsPossible;
    public String[] actionsPossibleRetournée;

    public Action(int jeton, String recto, String verso) {
        this.jeton = jeton;
        this.recto = recto;
        this.verso = verso;
    }

    public String getRecto() {
        return recto;
    }

    public String getVerso() {
        return verso;
    }

    public int getJeton() {
        return jeton;
    }

    public void setJeton(int jeton) {
        this.jeton = jeton;
    }

    public void setRecto(String recto) {
        this.recto = recto;
    }

    public void setVerso(String verso) {
        this.verso = verso;
    }

    public Action[] getJetons() {
        return jetons;
    }

    public void setJetons(Action[] jetons) {
        this.jetons = jetons;
    }

    public String[] getActionsPossible() {
        return actionsPossible;
    }

    public void setActionsPossible(String[] actionsPossible) {
        this.actionsPossible = actionsPossible;
    }

    public String[] getActionsPossibleRetournée() {
        return actionsPossibleRetournée;
    }

    public void setActionsPossibleRetournée(String[] actionsPossibleRetournée) {
        this.actionsPossibleRetournée = actionsPossibleRetournée;
    }

    //permet de transferé la valeur des atributs de mrjack a action
    private MrJackPocket mrJackPocket;
    public Action(MrJackPocket mrJackPocket) {
        this.mrJackPocket = mrJackPocket;
    }

    //permet de transferé la valeur des atributs de alibi a action
    private Alibi alibi;
    public Action(Alibi alibi) {
        this.alibi = alibi;
    }


    public void initialiseJetons(){
        Action jeton1 = new Action(1, "getAlibi","Holmes");
        Action jeton2 = new Action(2,"Toby","watson");
        Action jeton3 = new Action(3,"rotation","échange");
        Action jeton4 = new Action(4,"rotation","Joker");

        jetons = new Action[]{jeton1, jeton2, jeton3, jeton4};
    }


    public void échange(String coord1, String coord2){ //echange 2 district de place sans changer l'orientation
        //entre en ligne colone ex: 12 ,32
        int[] coordo1;
        coordo1 = new int[]{Integer.valueOf(coord1.substring(0, 1)), Integer.valueOf(coord1.substring(1, 2))};

        int[] coordo2;
        coordo2 = new int[]{Integer.valueOf(coord2.substring(0, 1)), Integer.valueOf(coord2.substring(1, 2))};

        int[] tempo = coordo1;

        mrJackPocket.board[coordo1[0]][coordo1[1]] = mrJackPocket.board[coordo2[0]][coordo2[1]];
        mrJackPocket.board[coordo2[0]][coordo2[1]] = mrJackPocket.board[tempo[0]][tempo[1]];
    }

    public void rotation(String coord1, char direction){
        int[] coordo1;
        coordo1 = new int[]{Integer.valueOf(coord1.substring(0, 1)), Integer.valueOf(coord1.substring(1, 2))};

        int a; //converti la direction en int, N = 0, E = 1, S = 2, Ouest = 3, c'est le bout du T dans cette direction
        switch (direction) {
            case 'N':
                a = 0;
                break;
            case 'E':
                a = 1;
                break;
            case 'S':
                a = 2;
                break;
            case 'O':
                a = 3;
                break;
            default:
                a = 4;
        }
        mrJackPocket.board[coordo1[0]][coordo1[1]].setOrientation(a);
    }

    public void alibi(){
        Alibi cartePioché = alibi.draw1Carte();
        if (mrJackPocket.currentPlayer.getName() == "Enqueteur"){
            System.out.println(cartePioché.getNom());
        } else if (mrJackPocket.currentPlayer.getName() == "Mr. Jack"){
            System.out.println(cartePioché.getNom());
            alibi.updateAlibiJack(cartePioché);
            System.out.println(alibi.nbSablierAlibi());
        }
    }

    public void joker(){
        if (mrJackPocket.currentPlayer.getName() == "Enqueteur"){
            System.out.println("Quel enqueteur veux tu déplacer de 1 espace ?");
            Scanner scanner = new Scanner(System.in);
            String déplacé = scanner.nextLine();
            if (déplacé == "Holmes" || déplacé == "holmes"){
                holmes();
            } else if (déplacé == "Watson" || déplacé == "watson"){
                watson();
            } else if (déplacé == "Toby" || déplacé == "toby"){
                toby();
            }


        } else if (mrJackPocket.currentPlayer.getName() == "Mr. Jack"){
            System.out.println("Quel enqueteur veux tu déplacer de 1 espace ? (none si aucune action)");
            Scanner scanner = new Scanner(System.in);
            String déplacé = scanner.nextLine();
            if (déplacé == "Holmes" || déplacé == "holmes"){
                holmes();
            } else if (déplacé == "Watson" || déplacé == "watson"){
                watson();
            } else if (déplacé == "Toby" || déplacé == "toby"){
                toby();
            }
        }
    }


    //déplacement a coder
    public void holmes(){
        //bouge dans le sens des aiguille d'une montre de 1 ou 2 case
        System.out.println("Veux tu etre avancé de 1 ou 2 espaces dans le sens des aiguilles d'une montre ?"); // rentrer 1 ou 2
        Scanner scanner = new Scanner(System.in);
        String deplacement = scanner.nextLine();
        int a = Integer.valueOf(deplacement);
    }

    public void watson(){
        //bouge dans le sens des aiguille d'une montre de 1 ou 2 case
        System.out.println("Veux tu etre avancé de 1 ou 2 espaces dans le sens des aiguilles d'une montre ?"); // rentrer 1 ou 2
        Scanner scanner = new Scanner(System.in);
        String deplacement = scanner.nextLine();
        int a = Integer.valueOf(deplacement);
    }

    public void toby(){
        //bouge dans le sens des aiguille d'une montre de 1 ou 2 case
        System.out.println("Veux tu etre avancé de 1 ou 2 espaces dans le sens des aiguilles d'une montre ?"); // rentrer 1 ou 2
        Scanner scanner = new Scanner(System.in);
        String deplacement = scanner.nextLine();
        int a = Integer.valueOf(deplacement);
    }

    public void initialisePossibleAction(){// change list de string des action possible aux 2 prochains tours
        Random rd = new Random();
        boolean[] faceAction= new boolean[4];
        String[] sortieEnqueteur = new String[0];
        String[] sortieJack = new String[0];
        for (int i = 0; i < faceAction.length; i++) {
            faceAction[i] = rd.nextBoolean();
            sortieEnqueteur = Arrays.copyOf(sortieEnqueteur, sortieEnqueteur.length + 1);// on crée une copie qui ecrase l'originale et qui est plus longue de 1 (append en python)
            sortieJack = Arrays.copyOf(sortieJack, sortieJack.length + 1);
            if (faceAction[i]){
                sortieEnqueteur[sortieEnqueteur.length - 1] =jetons[i].getRecto();
                sortieJack[sortieJack.length - 1] =jetons[i].getVerso();
            } else {
                sortieEnqueteur[sortieEnqueteur.length - 1] =jetons[i].getVerso();
                sortieJack[sortieJack.length - 1] =jetons[i].getRecto();
            }
        }
        actionsPossible = sortieEnqueteur;
        actionsPossibleRetournée = sortieJack;
    }

    public void printActionPossible(){
        System.out.println();
        for (int i = 0; i < actionsPossible.length-1; i++){
            System.out.print(actionsPossible[i] + "\t");
        }
        System.out.println(actionsPossible[actionsPossible.length-1]);
    }

    public void printActionPossibleRetournée(){
        System.out.println();
        for (int i = 0; i < actionsPossibleRetournée.length-1; i++){
            System.out.print(actionsPossibleRetournée[i] + "\t");
        }
        System.out.println(actionsPossibleRetournée[actionsPossibleRetournée.length-1]);
    }

    public void updateActionPossible(String actionUtilisé){
        String[] newPioche = new String[0];
        for(int i = 0; i < actionsPossible.length; i++){
            if(actionsPossible[i] != actionUtilisé){
                newPioche = Arrays.copyOf(newPioche, newPioche.length + 1);// append en python
                newPioche[newPioche.length - 1] = actionsPossible[i];
            }
        }
        setActionsPossible(newPioche);
    }

    public void updateActionPossibleRetournée(String actionUtilisé){
        String[] newPioche = new String[0];
        for(int i = 0; i < actionsPossibleRetournée.length; i++){
            if(actionsPossibleRetournée[i] != actionUtilisé){
                newPioche = Arrays.copyOf(newPioche, newPioche.length + 1);// append en python
                newPioche[newPioche.length - 1] = actionsPossibleRetournée[i];
            }
        }
        setActionsPossibleRetournée(newPioche);
    }

    public String chooseAction(){
        System.out.println();
        System.out.println("Quelle action choisi-tu ?");
        Scanner scanner = new Scanner(System.in);
        String actionChoisi = scanner.nextLine();
        return actionChoisi;
    }
}
