package com.company;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class Action {
    public String jeton1;
    public String jeton2;
    public String jeton3;
    public String jeton4;
    public int face; // recto et verso en binaire 0 et 1
    public Alibi alibi; // objet Alibi
    public Detectives Holmes;
    public Detectives Toby;
    public Detectives Watson; // objets Detectives pour les methodes

    public Action(String jeton1, String jeton2, String jeton3, String jeton4, int face, Alibi alibi, Detectives holmes, Detectives toby, Detectives watson) {
        this.jeton1 = jeton1;
        this.jeton2 = jeton2;
        this.jeton3 = jeton3;
        this.jeton4 = jeton4;
        this.face = face; // tous les constructeur pour l'instant j'ai tout mis
        this.alibi = alibi;
        Holmes = holmes;
        Toby = toby;
        Watson = watson;
    }

    public Alibi getAlibi(){
        return alibi; //un get pour la carte ?
    }

    public void Exchange(){
        // selected piece.board = other selected piece.board
        // selected piece.board = other selected piece.board

    }

    public void Rotation(){
        // rotate selected piece
    }

    public void Joker(){

    }

    public void Holmes(){

    }

    public void Watson(){

    }

    public void Dogs(){

    }
}
