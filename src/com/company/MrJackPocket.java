package com.company;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MrJackPocket{

    public District[][] board = new District[5][5]; //5 5 pour mettre les detectives
    public int tour = 1;
    public int[] temps; //jeton temps double face 0 = tour et 1 = sablier
    public Player currentPlayer;
    public JLabel[][] boardGraph = new JLabel[5][5];

    public String jack;

    public ArrayList<String> innocent = new ArrayList<>();
    public ArrayList<String> suspects = new ArrayList<>();

    public MrJackPocket() {

    }

    Action action = new Action(this);
    SuspectDistrict sus = new SuspectDistrict(this); //permet de transferé la valeur des atributs de mrjack a suspectDis
    Player player = new Player();
    Alibi alibi = new Alibi();

    public void play(){//les actions possible ne d'updatesPas probleme d'opp
        débutPartie();
        printBoard();

        //printBoardGraph();
        for (int i = 1; i < 9; i++ ) {//i est le compteur de tour
            if (i % 2 == 1) { // tour impair, enqueteurs commence
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
                //String act4 = action.actionsPossible[0]; //ajouter un verif que c possible avec do while
                joueAction(act4);
                action.updateActionPossible(act4);

                suspects.addAll(seeTotal());
                if (suspects.contains(jack)){
                    addInocent(notSuspect());

                } else {
                    addInocent(suspects);
                    //on lui ajoute un sablier
                }
                suspects.clear();

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
                String act4 = action.chooseAction(); //ajouter un verif que c possible avec do while
                joueAction(act4);
                action.updateActionPossibleRetournée(act4);

                addInocent(seeTotal());
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

    JFrame frame = new JFrame("Mr Jack Pocket");
    public void printBoardGraph() {

        //on initialise la JFrame
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //game.setSize(816,816);


        //On initialise le plateau
        JPanel plateau = new JPanel();
        plateau.setLayout(new GridLayout(5,5));
        plateau.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        for (int i = 0; i < boardGraph.length; i++){
            for (int j = 0; j < boardGraph[0].length; j++){
                plateau.add(boardGraph[i][j]);
                plateau.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
            }
        }

        //On initialise la toolBar
        JToolBar toolBar = new JToolBar();

        JLabel oneUse = new JLabel("Set up : ");
        JButton whoIsWho = new JButton("Choix des roles random");
        JButton whoIsJack = new JButton("Qui est Mr. Jack ?"); //Ce bouton initialise qui est Mr. Jack et le dis au joueur, et place la pile de alibi a droite dans cartes

        JLabel eachRound = new JLabel("En fin de tour : ");
        JButton temoin = new JButton("Appel a témoin"); //Ce bouton demande au joueur si Mr. Jack est visible

        JLabel fin = new JLabel("En fin de partie : ");
        JButton win = new JButton("Win ?"); //appuyer si tu pense avoir gagner, l'ordi vérifie

        toolBar.add(oneUse);
        toolBar.add(whoIsWho);
        toolBar.add(whoIsJack);
        toolBar.addSeparator(new Dimension(40,0));

        toolBar.add(eachRound);
        toolBar.add(temoin);
        toolBar.addSeparator(new Dimension(40,0));

        toolBar.add(fin);
        toolBar.add(win);

        //on initialise les bails a droite du plateau, cad pioche alibi et actions possible
        JPanel cartes = new JPanel();
        cartes.setLayout(new GridLayout(2,1));
        JButton piocheAlibi = new JButton(new ImageIcon("image/piocheAlibi.png")); //appuyer sur ce bouton pioche une carte alibi
        cartes.add(piocheAlibi);

        //On initialise le panel avec 4 bouton
        JPanel action = new JPanel();
        action.setLayout(new GridLayout(4,1));
        JButton action1 = new JButton("action1");
        JButton action2 = new JButton("action2");
        JButton action3 = new JButton("action3");
        JButton action4 = new JButton("action4");
        action.add(action1);    action.add(action2);    action.add(action3);    action.add(action4);

        cartes.add(action);


        //on initialise les bails a gauche du plateau
        JPanel tourDeJeu = new JPanel();
        tourDeJeu.setLayout(new GridLayout(9,1));
        tourDeJeu.setPreferredSize(new Dimension(120,0));
        JLabel carteJack = new JLabel(new ImageIcon("image/carteJack.png"));
        JLabel tour1 = new JLabel("tour1");
        JLabel tour2 = new JLabel("tour2");
        JLabel tour3 = new JLabel("tour3");
        JLabel tour4 = new JLabel("tour4");
        JLabel tour5 = new JLabel("tour5");
        JLabel tour6 = new JLabel("tour6");
        JLabel tour7 = new JLabel("tour7");
        JLabel tour8 = new JLabel("tour8");

        tourDeJeu.add(carteJack);
        tourDeJeu.add(tour8);   tourDeJeu.add(tour7);   tourDeJeu.add(tour6);   tourDeJeu.add(tour5);
        tourDeJeu.add(tour4);   tourDeJeu.add(tour3);   tourDeJeu.add(tour2);   tourDeJeu.add(tour1);
        //rajouter les jetons avec les tour dessus a l'aide d'une methode

        JLabel imgGarde = new JLabel(district.changeSize("image/garde_4x.png",600,600)); //changer image de fond elle pue la merde niv résolution
        imgGarde.setLayout( new GridBagLayout() );

        JButton start = new JButton("Start Game");
        start.setPreferredSize(new Dimension(550, 100));
        start.setFont(start.getFont().deriveFont(100f));
        start.setContentAreaFilled(false);//maybe ajouter une fleche qui s'ffiche qd tu le selectione
        imgGarde.add(start, new GridBagConstraints());

        JButton credit = new JButton("crédits");
        //imgGarde.add(credit);


        frame.add(imgGarde);
        frame.pack();
        frame.setLocationRelativeTo(null); //centre la fenetre, tjr le metre en dernier sinon marche ap

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(imgGarde);
                frame.add(toolBar, BorderLayout.NORTH);
                frame.add(plateau, BorderLayout.CENTER);
                frame.add(cartes, BorderLayout.EAST);
                frame.add(tourDeJeu,BorderLayout.WEST);
                updateFrame();
            }
        });
        whoIsJack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Alibi name = alibi.choixJack();
                System.out.println("fuck u");
                //toolBar.remove(oneUse);
                toolBar.remove(whoIsJack);
                updateFrame();
            }
        });
        whoIsWho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rd = new Random();
                boolean choix = rd.nextBoolean();
                //faire un popup et demander au joueur de choisir 1 ou 2
                if (choix){
                    System.out.println("Player1 is Mr. Jack");
                } else {
                    System.out.println("Player2 is Mr. Jack");
                }
                toolBar.remove(whoIsWho);
                updateFrame();
            }
        });
    }
    public void transparent(JButton button){ //Rend le bouton transparent, on voit juste le text et la bordure
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK,4));
    }

    public void updateFrame(){
        //les 3 autres lignes pour updates la frame
        frame.pack();
        frame.setLocationRelativeTo(null);
        SwingUtilities.updateComponentTreeUI(frame);
    }

    public void débutPartie(){
        //lance la game behind the scene, initialise le game, les joueurs, choisi Jack
        initialiseBoard();

        player.initialiseName();
        setCurrentPlayer(player.players[0]); //enqueteur commence
        alibi.initialisePiocheAlibi();//on crée la pioche

        Alibi alibiJack = alibi.choixJack(); //alibiJack est la carte alibi de Jack, et la pioche est update
        jack = alibiJack.getNom(); //jack est le nom en string de son perso
        alibi.initialiseAlibiJack(alibiJack); //on initialise les carte alibi en possession de Jack

        action.initialiseJetons();


    }

    public void switchPlayer(){
        if (currentPlayer == player.players[1]){
            setCurrentPlayer(player.players[0]);
        } else {
            setCurrentPlayer(player.players[1]);
        }
        System.out.println("\n\tAu tour de " + currentPlayer.getName());
    }

    private void initialiseBoard(){ //fini
        district.setUp();


        //les cases exterieurs sont vides
        for (int i = 0; i < 5; i++) {//ligne
            board[0][i] = district.baseDeDonnee[2][0]; //exception
            boardGraph[0][i] = new JLabel(board[0][i].getFaceSus());
            boardGraph[0][i].setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
            for (int j = 0; j < 5; j++) {//colone
                if (j == 0 || j == 4) {
                    board[i][j] = district.baseDeDonnee[2][0]; //Vide
                    boardGraph[i][j] = new JLabel(board[i][j].getFaceSus());
                    boardGraph[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                }
                if (i == 0 || i == 4){
                    board[i][j] = district.baseDeDonnee[2][0];//Vide
                    boardGraph[i][j] = new JLabel(board[i][j].getFaceSus());
                    boardGraph[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                }
            }
        }
        //board[0][0] = board[4][0] =board[0][4] = board[4][4] = null;
        board[1][0] = district.baseDeDonnee[1][0]; //Holmes
        boardGraph[1][0] = new JLabel(board[1][0].getFaceSus());
        boardGraph[1][0].setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

        board[1][4] = district.baseDeDonnee[1][1]; //Watson
        boardGraph[1][4] = new JLabel(board[1][4].getFaceSus());
        boardGraph[1][4].setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

        board[4][2] = district.baseDeDonnee[1][2]; //Toby
        boardGraph[4][2] = new JLabel(board[4][2].getFaceSus());
        boardGraph[4][2].setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

        //on schuffle cette liste pour que les districtes soient tjr à des positions différentes
        District[] perso = district.baseDeDonnee[0];
        List<District> list = Arrays.asList(perso);
        Collections.shuffle(list);
        list.toArray(perso);

        int a = 0; //indice pour parcourir perso

        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                board[i][j] = perso[a];
                boardGraph[i][j] = new JLabel(board[i][j].getFaceSus());
                boardGraph[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                a = a+1; //passe au prochain perso
            }
        }
        //on fait en sorte que les inspecteurs soient en face d'un mur
        board[1][1].setOrientation(1);
        board[1][3].setOrientation(3);
        board[3][2].setOrientation(0);
    }

    private void printBoard(){// faire un truc graphique
        final int lineLength = 16;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(repeatChar(' ', lineLength - board[i][j].getNom().length()) + board[i][j].getNom() + " " +board[i][j].getOrientation()+ " ");
                if (board[i][j].getFaceVisible()){
                    System.out.print(board[i][j].getFaceVisible() + " ");
                } else {
                    System.out.print(board[i][j].getFaceVisible());
                }
                //System.out.print(board[i][j].getNom() + " " +board[i][j].getOrientation() + "\t");
            }
            System.out.println();
        }
    }

    public static String repeatChar(char repeatChar, int repeatTimes) {
        String result = "";
        for(int j = 0; j < repeatTimes; j++) {
            result += repeatChar;
        }
        return result;
    }

    public void joueAction(String actionChoisie){
        if (Objects.equals(actionChoisie, new String("rotation1")) || Objects.equals(actionChoisie, new String("rotation2"))){
            action.rotation();
            pause();
            printBoard();
        } else if (Objects.equals(actionChoisie, new String("échange"))){
            action.échange();
            pause();
            printBoard();
        } else if (Objects.equals(actionChoisie, new String("getAlibi"))){
            action.alibi(alibi);
            pause();
            printBoard();
        } else if (Objects.equals(actionChoisie, new String("holmes"))){
            action.deplacementDetective(district.baseDeDonnee[1][0]);
            pause();
            printBoard();
        } else if (Objects.equals(actionChoisie, new String("watson"))){
            action.deplacementDetective(district.baseDeDonnee[1][1]);
            pause();
            printBoard();
        } else if (Objects.equals(actionChoisie, new String("toby"))){
            action.deplacementDetective(district.baseDeDonnee[1][2]);
            pause();
            printBoard();
        } else if (Objects.equals(actionChoisie, new String("joker"))){
            if (currentPlayer == player.players[1]) { //si c'est l'enqueteur
                System.out.println("Quel Detective veux-tu déplacer ?");
                Scanner scannerD = new Scanner(System.in);
                String detective = scannerD.nextLine();
                if (Objects.equals(detective, new String("holmes"))) {
                    action.deplacementDetective(district.baseDeDonnee[1][0], 1);
                } else if (Objects.equals(actionChoisie, new String("watson"))) {
                    action.deplacementDetective(district.baseDeDonnee[1][1], 1);
                } else if (Objects.equals(actionChoisie, new String("toby"))) {
                    action.deplacementDetective(district.baseDeDonnee[1][2], 1);
                }
            } else {
                System.out.println("Veux tu déplacer un detective"); //repondre Y/N
                Scanner scanner = new Scanner(System.in);
                String rep = scanner.nextLine();
                if (Objects.equals(rep, new String("Y"))) {
                    System.out.println("Quel Detective veux-tu déplacer ?");
                    Scanner scannerJ = new Scanner(System.in);
                    String detective = scannerJ.nextLine();
                    if (Objects.equals(detective, new String("holmes"))) {
                        action.deplacementDetective(district.baseDeDonnee[1][0], 1);
                    } else if (Objects.equals(actionChoisie, new String("watson"))) {
                        action.deplacementDetective(district.baseDeDonnee[1][1], 1);
                    } else if (Objects.equals(actionChoisie, new String("toby"))) {
                        action.deplacementDetective(district.baseDeDonnee[1][2], 1);
                    }
                } else {
                    //on fait rien
                }
            }
            pause();
            printBoard();
        }
    }
    public void pause(){
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------\n");
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

    public ArrayList<String> seeTotal(){
        ArrayList<String> sortie = new ArrayList<>();
        ArrayList<String> sH = sus.see(findPosition(district.baseDeDonnee[1][0]), regard(findPosition(district.baseDeDonnee[1][0])));
        ArrayList<String> sW = sus.see(findPosition(district.baseDeDonnee[1][1]), regard(findPosition(district.baseDeDonnee[1][1])));
        ArrayList<String> sT = sus.see(findPosition(district.baseDeDonnee[1][2]), regard(findPosition(district.baseDeDonnee[1][2])));

        sortie.addAll(sH); //sortie add les dictricts que peux voir holmes

        List<String> sWC = new ArrayList<>(sW);
        sWC.removeAll(sortie);
        sortie.addAll(sWC); //sortie add les dictricts que peux voir watson qui ne sont pas des doublons la liste de base

        List<String> sTC = new ArrayList<>(sT);
        sTC.removeAll(sortie);
        sortie.addAll(sTC); //sortie add les dictricts que peux voir toby qui ne sont pas des doublons la liste de base

        return sortie;
    }

    public int regard(int[] coord){
        int regard;
        if (coord[0] == 0) {//si il est sur ligne 0
            regard = 2;
        } else if (coord[1] == 0){//si sur colone 0
            regard = 1;
        } else if (coord[0] == 4){//si sur derniere ligne
            regard = 0;
        } else { //si sur derniere colone
            regard = 3;
        }
        return regard;
    }

    public void addInocent(List<String> add){
        List<String> copy = new ArrayList<>(add);
        copy.removeAll(innocent);
        innocent.addAll(copy);
        System.out.println("Les innocents sont :");
        for (int k = 0; k < innocent.size(); k++) {
            System.out.print(innocent.get(k)+"\t, ");
            //on retourne les innocents
            for (int i = 1; i < board.length; i++){
                for (int j = 1; j < board.length; j++){
                    if (board[i][j].getNom().equals(innocent.get(k))){
                        board[i][j].setFaceVisible(false);
                    }
                }
            }
        }

    }

    public ArrayList<String> notSuspect(){
        ArrayList<String> notSuspect = new ArrayList<>();
        for (int i = 0; i < district.baseDeDonnee[0].length;i++){
            if (!suspects.contains(district.baseDeDonnee[0][i].getNom())){
                notSuspect.add(district.baseDeDonnee[0][i].getNom());
            }
        }
        return notSuspect;
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
