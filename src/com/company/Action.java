package com.company;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.Random;

public class Action {
    public int jeton; // les jetons de 1 à 4
    public boolean face; // boolean : recto = true et verso = false
    public Alibi alibi; // objet Alibi
    public Detectives Holmes;
    public Detectives Toby;
    public Detectives Watson; // objets Detectives pour les methodes
    public District district;

    public Action(int jeton, boolean face) {
        this.jeton = jeton;
        this.face = face;
    }

    public void initialiseJeton(){
        Action getAlibi = new Action(1,true);
        Action Holmes = new Action(1,false);
        Action Toby = new Action(2,true);
        Action Watson = new Action(2,false);
        Action Rotation1 = new Action(3,true);
        Action Exchange = new Action(3,false);
        Action Rotation2 = new Action(4, true);
        Action Joker = new Action(4,false);
    }

    public Alibi getAlibi(){
        Random random = new Random();
        return ;
    }

    public void Exchange(){
        // selected piece.board = other selected piece.board
        // selected piece.board = other selected piece.board

    }

    public void Rotation(){
        // rotate selected piece
        // select district
        district.orientation = (district.orientation + 1)%4;
    }

    public void Joker(){

    }

    public void Holmes(){
        //bouge dans le sens des aiguille d'une montre de 1 ou 2 case
    }

    public void Watson(){
        //bouge dans le sens des aiguille d'une montre de 1 ou 2 case
    }

    public void Toby(){
        //bouge dans le sens des aiguille d'une montre de 1 ou 2 case
    }
}
