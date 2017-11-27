/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazerunner;

/**
 *
 * @author dthole
 */
public class Zelle {
 
    boolean wand_nord = true;
    boolean wand_ost = true;
    boolean wand_sued = true;
    boolean wand_west = true;
    final int zeilenposition;
    final int spaltenposition;
 
    public Zelle(int zeile, int spalte) {
        zeilenposition = zeile;
        spaltenposition = spalte;
    }
}
