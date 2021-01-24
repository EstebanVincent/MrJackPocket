package com.company;


import java.util.*;

public class Action {

    //Attributs
    public int jeton; //le num du jeton
    public String recto;
    public String verso;

    public Action[] jetons; // les jetons de 1 à 4 avec leur faces
    public String[] actionsPossible;
    public String[] actionsPossibleRetournée;

    public final int[][] positionDetectiveList = { {0,1}, {0,2}, {0,3}, {1,4}, {2,4}, {3,4}, {4,3}, {4,2}, {4,1}, {3,0}, {2,0}, {1,0}, {0,1}, {0,2} };


    //Constructeur
    public Action(int jeton, String recto, String verso) {//création des jetons
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

    public void setJetons(Action[] jetons) {
        this.jetons = jetons;
    }

    public void setActionsPossible(String[] actionsPossible) {
        this.actionsPossible = actionsPossible;
    }

    public void setActionsPossibleRetournée(String[] actionsPossibleRetournée) { this.actionsPossibleRetournée = actionsPossibleRetournée; }

    //permet de transféré la valeur des attributs de mrjack a action
    private MrJackPocket mrJackPocket;
    public Action(MrJackPocket mrJackPocket) {
        this.mrJackPocket = mrJackPocket;
    }

    //Méthodes

    //initialise las jetons
    public void initialiseJetons(){
        Action jeton1 = new Action(1, "getAlibi","holmes");
        Action jeton2 = new Action(2,"toby","watson");
        Action jeton3 = new Action(3,"rotation1","échange");
        Action jeton4 = new Action(4,"rotation2","joker");

        setJetons(new Action[]{jeton1, jeton2, jeton3, jeton4});
    }

    //échange 2 district de place sans changer l'orientation
    public void échange(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Quelles districts veux tu échanger? (donner ses coordonnées ex: 12)");//rentrer le premier enter puis le deuxième
        String districtChoisie1 = scanner.nextLine();
        int[] coordo1;
        coordo1 = new int[]{Integer.parseInt(districtChoisie1.substring(0, 1)), Integer.parseInt(districtChoisie1.substring(1, 2))};

        String districtChoisie2 = scanner.nextLine();
        int[] coordo2;
        coordo2 = new int[]{Integer.parseInt(districtChoisie2.substring(0, 1)), Integer.parseInt(districtChoisie2.substring(1, 2))};

        District tempo = mrJackPocket.getBoard()[coordo1[0]][coordo1[1]];

        mrJackPocket.getBoard()[coordo1[0]][coordo1[1]] = mrJackPocket.getBoard()[coordo2[0]][coordo2[1]];
        mrJackPocket.getBoard()[coordo2[0]][coordo2[1]] = tempo;
    }

    //change l'orientation d'un district
    public void rotation(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quelle district veux tu tourner? (donner ses coordonnées ex: 12)");
        String districtChoisie = scanner.nextLine();
        int[] coordo1;
        coordo1 = new int[]{Integer.parseInt(districtChoisie.substring(0, 1)), Integer.parseInt(districtChoisie.substring(1, 2))};

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
        mrJackPocket.getBoard()[coordo1[0]][coordo1[1]].setOrientation(a);
    }

    //pioche 1 carte alibi
    public void alibi(Alibi alibi){
        Alibi cartePioché = alibi.draw1Carte();
        if (mrJackPocket.getCurrentPlayer().getName().equals("Enquêteur")){
            System.out.println("Tu as pioché " + cartePioché.getNom());

            ArrayList<String> pioché = new ArrayList<>();
            pioché.add(cartePioché.getNom());
            mrJackPocket.addInnocent(pioché);

        } else if (mrJackPocket.getCurrentPlayer().getName().equals("Mr. Jack")){
            System.out.println("Tu as pioché " + cartePioché.getNom());
            mrJackPocket.sabliers += cartePioché.getSablier();
            System.out.println("Tu as " + mrJackPocket.sabliers + " sablier");
            mrJackPocket.printBoard();
        }
    }

    //bouge un détective dans le sens des aiguille d'une montre de 1 ou 2 case en demandant
    public void deplacementDetective(District detective){
        int[] oldCoordDetective;
        int[] newCoordDetective = new int[2];
        oldCoordDetective = mrJackPocket.findPosition(detective);
        System.out.println("Veux-tu avancer de 1 ou 2 espaces dans le sens des aiguilles d'une montre ?"); // rentrer 1 ou 2
        Scanner scanner = new Scanner(System.in);
        String deplacement = scanner.nextLine();
        int a = Integer.parseInt(deplacement);
        for (int i = 0; i < positionDetectiveList.length; i++ ) {
            if (positionDetectiveList[i][0] == oldCoordDetective[0] && positionDetectiveList[i][1] == oldCoordDetective[1]){
                newCoordDetective = positionDetectiveList[i + a];
                break;
            }
        }
        mrJackPocket.getBoard()[oldCoordDetective[0]][oldCoordDetective[1]] = mrJackPocket.getBoard()[0][0];
        mrJackPocket.getBoard()[newCoordDetective[0]][newCoordDetective[1]] = detective;
    }

    //bouge un détective dans le sens des aiguille d'une montre de 1 ou 2 case en entrée
    public void deplacementDetective(District detective, int a){
        //bouge dans le sens des aiguille d'une montre de 1 ou 2 case
        int[] oldCoordDetective;
        int[] newCoordDetective = new int[2];
        oldCoordDetective = mrJackPocket.findPosition(detective);
        for (int i = 0; i < positionDetectiveList.length; i++ ) {
            if (positionDetectiveList[i][0] == oldCoordDetective[0] && positionDetectiveList[i][1] == oldCoordDetective[1]){
                newCoordDetective = positionDetectiveList[i + a];
                break;
            }
        }
        mrJackPocket.getBoard()[oldCoordDetective[0]][oldCoordDetective[1]] = mrJackPocket.getBoard()[0][0];
        mrJackPocket.getBoard()[newCoordDetective[0]][newCoordDetective[1]] = detective;
    }

    // initialise les action possible aux 2 prochains tours
    public void initialisePossibleAction(){
        Random rd = new Random();
        boolean[] faceAction= new boolean[4];
        String[] sortieEnqueteur = new String[0];
        String[] sortieJack = new String[0];
        for (int i = 0; i < faceAction.length; i++) {
            faceAction[i] = rd.nextBoolean();
            sortieEnqueteur = Arrays.copyOf(sortieEnqueteur, sortieEnqueteur.length + 1);// on crée une copie qui écrase l'originale et qui est plus longue de 1 (append en python)
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

    //print les actions possible aux tours impairs
    public void printActionPossible(){
        System.out.print("Actions possibles : ");
        for (String s : actionsPossible) {
            System.out.print(s + "\t");
        }
    }

    //print les actions possible aux tours pairs
    public void printActionPossibleRetournée(){
        System.out.print("Actions possibles : ");
        for (String s : actionsPossibleRetournée) {
            System.out.print(s + "\t");
        }
    }

    //enlève l'action utilisé des actions possible au tour impair
    public void updateActionPossible(String actionUtilisé){
        String[] newPioche = new String[0];
        for (String s : actionsPossible) {
            if (!(Objects.equals(s, actionUtilisé))) {
                newPioche = Arrays.copyOf(newPioche, newPioche.length + 1);// append en python
                newPioche[newPioche.length - 1] = s;
            }
        }
        setActionsPossible(newPioche);
    }

    //enlève l'action utilisé des actions possible au tour pair
    public void updateActionPossibleRetournée(String actionUtilisé){
        String[] newPioche = new String[0];
        for (String s : actionsPossibleRetournée) {
            if (!(Objects.equals(s, actionUtilisé))) {
                newPioche = Arrays.copyOf(newPioche, newPioche.length + 1);// append en python
                newPioche[newPioche.length - 1] = s;
            }
        }
        setActionsPossibleRetournée(newPioche);
    }

    //demande au joueur de rentrer son choix d'action, return l'action choisie FINI
    public String chooseAction(){
        System.out.println();
        System.out.println("Quelle action choisi-tu ?");
        Scanner scanner = new Scanner(System.in);
        String actionCh = scanner.nextLine();
        return actionCh;
    }
}
