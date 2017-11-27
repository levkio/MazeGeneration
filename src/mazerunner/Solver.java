/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazerunner;

import java.io.Console;
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
        
        for (int i = 0; i < Mazerunner.ANZAHL_ZEILEN; i++) {
            for (int j = 0; j < Mazerunner.ANZAHL_SPALTEN; j++) {
                alleWege.push(laby[i][j]);
            }
        }
        Zelle startZelle;
        Zelle tmpZelle;
        
        while(!alleWege.empty()){
            tmpZelle = alleWege.pop();
            if(tmpZelle.zeilenposition == 0 && tmpZelle.wand_nord == false)
            {
                startZelle= tmpZelle;
                System.out.println("Startzelle gefunden:"+tmpZelle.spaltenposition);
                break;
            }
            
        }
    }
}
