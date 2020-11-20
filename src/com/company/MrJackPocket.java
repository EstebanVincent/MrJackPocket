package com.company;
import java.util.Random;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MrJackPocket {
    public District[][] board; //5 5 pour mettre les detectives
    public int tour;
    public int[] temps; //jeton temps double face 0 = tour et 1 = sablier


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

        //on creer une liste random pour avoir des orientations de carte random
        Random random = new Random();
        int[] orienté = new int[9];
        for(int i = 0; i< 8; i++){
            int rand = random.nextInt(3 - 0 + 1);
            orienté[i] = rand;
        }

        //on créer les district
        District Mme = new District("Madame",orienté[0],1,1);
        District SGT = new District("SGT Goodley",orienté[1],1,1);
        District JB = new District("Jeremy Bert",orienté[2],1,1);
        District WG = new District("William Gull",orienté[3],1,1);
        District Ms = new District("Miss Stealthy",orienté[4],1,1);
        District Gs = new District("Gensmith",orienté[5],1,1);
        District Insp = new District("Insp. Lestrade",orienté[6],1,1);
        District JP = new District("John Piser",orienté[7],1,1);
        District JL = new District("Joseph Lane",orienté[8],1,1); // spécial si retournéé croix preciser dans suspect district

        //des district speciaux avec les inspecteurs dessus, regles diff
        District Holmes = new District("Holmes",1,2,1);
        District Watson = new District("Watson",1,2,1);
        District Toby = new District("Toby",1,2,1);

        //districtes juste case ou y'as rien
        District Vide = new District("Vide",1,0,1);

        //création du board en lui meme
        District[][] board = new District[5][5];

        //les cases exterieurs sont vides
        for (int i = 0; i < 5; i++) {//ligne
            board[0][i] = Vide; //exception
            for (int j = 0; j < 5; j++) {//colone
                if (j == 0 || j == 4) {
                    board[i][j] = Vide;
                }
                if (i == 0 || i == 4){
                    board[i][j] = Vide;
                }
            }
        }
        //board[0][0] = board[4][0] =board[0][4] = board[4][4] = null;
        board[1][0] = Holmes;
        board[1][4] = Watson;
        board[4][2] = Toby;

        //on schuffle cette liste pour que les districtes soient tjr à des positions différentes
        District[] perso = {Mme, SGT, JB, WG, Ms, Gs, Insp, JP, JL};
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
