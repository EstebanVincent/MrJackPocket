package com.company;
import random;
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

    private void initialiseBoard(){
        District Mme = new District("Madame",1,1);
        District SGT = new District("SGT Goodley",1,1);
        District JB = new District("Jeremy Bert",1,1);
        District WG = new District("William Gull",1,1);
        District Ms = new District("Miss Stealthy",1,1);
        District Gs = new District("Gensmith",1,1);
        District Insp = new District("Insp. Lestrade",1,1);
        District JP = new District("John Piser",1,1);
        District JL = new District("Joseph Layne",1,1);

        District Holmes = new District("Holmes",1,2);
        District Watson = new District("Watson",1,2);
        District Toby = new District("Toby",1,2);

        District Vide = new District("Vide",1,0);

        District[][] board = new District[5][5];

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
        board[0][0] = board[4][0] =board[0][4] = board[4][4] = null;
        board[1][0] = Holmes;
        board[1][4] = Watson;
        board[4][2] = Toby;

        random.nextInt(max - min + 1) + min

        setBoard(board);
    }

    private void printBoard(){// faire un truc graphique
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(board[i][j] + "\t");
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
