/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazerunner;

import java.io.Console;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author dthole
 */
public class Solver {
    Stack<Zelle> abgelaufenerWeg = new Stack<Zelle>();
    Stack<Zelle> alleWege = new Stack<Zelle>();
    

    public  Solver(){

    }
    
    public void randomSolver(Zelle[][] laby){
        alleWege.clear();
        for (int i = 0; i < Mazerunner.ANZAHL_SPALTEN; i++) {
            for (int j = 0; j < Mazerunner.ANZAHL_ZEILEN; j++) {
                alleWege.push(laby[i][j]);
            }
        }
        Zelle startZelle= null;
        Zelle tmpZelle;
        
        while(!alleWege.empty()){
            tmpZelle = alleWege.pop();
            if(tmpZelle.zeilenposition == 0 && tmpZelle.wand_nord == false)
            {
                startZelle = tmpZelle;
                System.out.println("zeilenposition gefunden:"+startZelle.zeilenposition);
                System.out.println("spaltenposition gefunden:"+startZelle.spaltenposition);
                alleWege.clear();
                break;
            }
            
        }
        tiefensuche(startZelle,laby);
    }
    public void tiefensuche(Zelle startPunkt, Zelle[][] laby){
        Stack<Zelle> nochNichtBeuscht = new Stack<>();
        Zelle naechster = nbKnoten(startPunkt, laby);
        if(naechster != null)
            nochNichtBeuscht.add(naechster);
        
    }
    
    private Zelle nbKnoten(Zelle knoten, Zelle[][] laby){
        knoten.visited=true;
        Zelle knotenNachbar = null;
        if(knoten.wand_sued == false)
        {
            knotenNachbar=laby[knoten.zeilenposition+1][knoten.spaltenposition];
            if(!knotenNachbar.visited)
                return nbKnoten(knotenNachbar,laby);
        }
        return knotenNachbar;
    }

    
    
 
}
