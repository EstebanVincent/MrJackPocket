package com.company;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.xml.bind.SchemaOutputResolver;
import java.util.Arrays;
import java.util.Objects;
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


    public void initialiseJetons(){ //FINI
        Action jeton1 = new Action(1, "getAlibi","holmes");
        Action jeton2 = new Action(2,"toby","watson");
        Action jeton3 = new Action(3,"rotation1","échange");
        Action jeton4 = new Action(4,"rotation2","joker");

        jetons = new Action[]{jeton1, jeton2, jeton3, jeton4};
    }


    public void échange(){ //echange 2 district de place sans changer l'orientation FINI

        Scanner scanner = new Scanner(System.in);
        System.out.println("Quelles districts veux tu échanger? (donner ses coordonnées ex: 12)");//rentrer le premier enter puis le deuxieme
        String districtChoisie1 = scanner.nextLine();
        int[] coordo1;
        coordo1 = new int[]{Integer.valueOf(districtChoisie1.substring(0, 1)), Integer.valueOf(districtChoisie1.substring(1, 2))};

        String districtChoisie2 = scanner.nextLine();
        int[] coordo2;
        coordo2 = new int[]{Integer.valueOf(districtChoisie2.substring(0, 1)), Integer.valueOf(districtChoisie2.substring(1, 2))};


        District tempo = mrJackPocket.board[coordo1[0]][coordo1[1]];

        mrJackPocket.board[coordo1[0]][coordo1[1]] = mrJackPocket.board[coordo2[0]][coordo2[1]];
        mrJackPocket.board[coordo2[0]][coordo2[1]] = tempo;
    }

    public void rotation(){//rotate un district FINI
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quelle district veux tu tourner? (donner ses coordonnées ex: 12)");
        String districtChoisie = scanner.nextLine();
        int[] coordo1;
        coordo1 = new int[]{Integer.valueOf(districtChoisie.substring(0, 1)), Integer.valueOf(districtChoisie.substring(1, 2))};

        System.out.println("Dans quelle direction veux tu que la pointe du T soit? (donner un char de type N E S O)");
        String direction = scanner.nextLine();
        char dir = direction.charAt(0);

        int a; //converti la direction en int, N = 0, E = 1, S = 2, Ouest = 3, c'est le bout du T dans cette direction
        switch (dir) {
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

    public void alibi(){ //pioche 1 carte alibi et fait l'action necessaire
        Alibi cartePioché = alibi.draw1Carte();
        if (mrJackPocket.currentPlayer.getName() == "Enqueteur"){
            System.out.println(cartePioché.getNom());
        } else if (mrJackPocket.currentPlayer.getName() == "Mr. Jack"){
            System.out.println(cartePioché.getNom());
            alibi.updateAlibiJack(cartePioché);
            System.out.println(alibi.nbSablierAlibi());
        }
    }

    public void joker(){ //choisi qui déplacé divergence si enqueteur ou jack
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
    public void holmes(){ //Deplace de 1 ou 2 sens aiguilles montre holmes
        //bouge dans le sens des aiguille d'une montre de 1 ou 2 case
        System.out.println("Veux tu etre avancé de 1 ou 2 espaces dans le sens des aiguilles d'une montre ?"); // rentrer 1 ou 2
        Scanner scanner = new Scanner(System.in);
        String deplacement = scanner.nextLine();
        int a = Integer.valueOf(deplacement);
    }

    public void watson(){//Deplace de 1 ou 2 sens aiguilles montre watson
        //bouge dans le sens des aiguille d'une montre de 1 ou 2 case
        System.out.println("Veux tu etre avancé de 1 ou 2 espaces dans le sens des aiguilles d'une montre ?"); // rentrer 1 ou 2
        Scanner scanner = new Scanner(System.in);
        String deplacement = scanner.nextLine();
        int a = Integer.valueOf(deplacement);
    }

    public void toby(){//Deplace de 1 ou 2 sens aiguilles montre toby
        //bouge dans le sens des aiguille d'une montre de 1 ou 2 case
        System.out.println("Veux tu etre avancé de 1 ou 2 espaces dans le sens des aiguilles d'une montre ?"); // rentrer 1 ou 2
        Scanner scanner = new Scanner(System.in);
        String deplacement = scanner.nextLine();
        int a = Integer.valueOf(deplacement);
    }

    public void initialisePossibleAction(){// initialise les action possible aux 2 prochains tours FINI
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

    public void printActionPossible(){ //print les actions possible au tour impair FINI
        System.out.println();
        for (int i = 0; i < actionsPossible.length-1; i++){
            System.out.print(actionsPossible[i] + "\t");
        }
        System.out.println(actionsPossible[actionsPossible.length-1]);
    }

    public void printActionPossibleRetournée(){ //print les actions possible au tour pair FINI
        System.out.println();
        for (int i = 0; i < actionsPossibleRetournée.length-1; i++){
            System.out.print(actionsPossibleRetournée[i] + "\t");
        }
        System.out.println(actionsPossibleRetournée[actionsPossibleRetournée.length-1]);
    }

    public void updateActionPossible(String actionUtilisé){ //enleve l'action utilisé des actions possible au tour impair FINI
        String[] newPioche = new String[0];
        for(int i = 0; i < actionsPossible.length; i++){
            if(!(Objects.equals(actionsPossible[i], new String(actionUtilisé)))){
                newPioche = Arrays.copyOf(newPioche, newPioche.length + 1);// append en python
                newPioche[newPioche.length - 1] = actionsPossible[i];
            }
        }
        setActionsPossible(newPioche);
    }

    public void updateActionPossibleRetournée(String actionUtilisé){  //enleve l'action utilisé des actions possible au tour pair FINI
        String[] newPioche = new String[0];
        for(int i = 0; i < actionsPossibleRetournée.length; i++){
            if(!(Objects.equals(actionsPossibleRetournée[i], new String(actionUtilisé)))){
                newPioche = Arrays.copyOf(newPioche, newPioche.length + 1);// append en python
                newPioche[newPioche.length - 1] = actionsPossibleRetournée[i];
            }
        }
        setActionsPossibleRetournée(newPioche);
    }

    public String chooseAction(){ //demande au joueur de rentrer son choix d'action, return l'action choisie FINI
        System.out.println();
        System.out.println("Quelle action choisi-tu ?");
        Scanner scanner = new Scanner(System.in);
        String actionChoisi = scanner.nextLine();
        return actionChoisi;
    }
}
