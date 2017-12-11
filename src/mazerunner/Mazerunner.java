package mazerunner;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;
import javax.swing.JPanel;

public class Mazerunner extends JPanel {
 
    final static int ANZAHL_ZEILEN = 5;
    final static int ANZAHL_SPALTEN = 5;
    Zelle[][] labyrinthfeld = new Zelle[ANZAHL_ZEILEN][ANZAHL_SPALTEN];
    Stack<Zelle> arbeitspfad = new Stack<Zelle>();
    Stack<Zelle> restlicheZellen = new Stack<Zelle>();
    
    public Mazerunner()
    {
        zellenVorbereiten();
        baueGaenge();
        baueAusgaenge();
        
    }
    
    private void zellenVorbereiten()
    {
        for (int i = 0; i < ANZAHL_ZEILEN; i++) {
            for (int j = 0; j < ANZAHL_SPALTEN; j++) {
                labyrinthfeld[i][j] = new Zelle(i, j);
                restlicheZellen.push(labyrinthfeld[i][j]);
            }
        }
    }
    private void baueGaenge() 
    {
        int zeile = zufall(ANZAHL_ZEILEN);
        int spalte = zufall(ANZAHL_SPALTEN);
        Zelle aktuelleZelle = labyrinthfeld[zeile][spalte];
        arbeitspfad.push(aktuelleZelle);
        restlicheZellen.remove(aktuelleZelle);

        while (restlicheZellen.size() > 0) {
            aktuelleZelle = arbeitspfad.peek();
            Vector nachbarn = findeUnverarbeiteteNachbarn(aktuelleZelle);
            if (nachbarn.size() > 0) {
                Zelle zufaelligerNachbar = (Zelle) nachbarn.get(zufall(nachbarn.size()));
                entferneZwischenwand(aktuelleZelle, zufaelligerNachbar);
                aktuelleZelle = zufaelligerNachbar;
                arbeitspfad.push(aktuelleZelle);
                restlicheZellen.remove(aktuelleZelle);
            } else {
                arbeitspfad.pop();
            }
        }
    }

    private Vector findeUnverarbeiteteNachbarn(Zelle zelle) {
        int zeile = zelle.zeilenposition;
        int spalte = zelle.spaltenposition;
        Vector<Zelle> nachbarn = new Vector<Zelle>();
        if (zeile > 0) {
            Zelle noerdlicherNachbar = labyrinthfeld[zeile - 1][spalte];
            if (restlicheZellen.contains(noerdlicherNachbar)) {
                nachbarn.add(noerdlicherNachbar);
            }
        }
        if (zeile < ANZAHL_ZEILEN - 1) {
            Zelle suedlicherNachbar = labyrinthfeld[zeile + 1][spalte];
            if (restlicheZellen.contains(suedlicherNachbar)) {
                nachbarn.add(suedlicherNachbar);
            }
        }
        if (spalte > 0) {
            Zelle westlicherNachbar = labyrinthfeld[zeile][spalte - 1];
            if (restlicheZellen.contains(westlicherNachbar)) {
                nachbarn.add(westlicherNachbar);
            }
        }
        if (spalte < ANZAHL_SPALTEN - 1) {
            Zelle oestlicherNachbar = labyrinthfeld[zeile][spalte + 1];
            if (restlicheZellen.contains(oestlicherNachbar)) {
                nachbarn.add(oestlicherNachbar);
            }
        }
        return nachbarn;
    }
    
    private void baueAusgaenge() {
        Zelle eingang = labyrinthfeld[0][zufall(ANZAHL_SPALTEN)];
        eingang.wand_nord = false;
        Zelle ausgang = labyrinthfeld[ANZAHL_ZEILEN - 1][zufall(ANZAHL_SPALTEN)];
        ausgang.wand_sued = false;
    }
    
    public int zufall(int max)
    {
        return (int) Math.floor(Math.random() * max);
    }
    
    private void entferneZwischenwand(Zelle zelle, Zelle nachbar) {
        if (zelle.zeilenposition > nachbar.zeilenposition) {
            //Nachbar liegt nördlich
            zelle.wand_nord = false;
            nachbar.wand_sued = false;
        }
        if (zelle.zeilenposition < nachbar.zeilenposition) {
            //Nachbar liegt südlich
            zelle.wand_sued = false;
            nachbar.wand_nord = false;
        }
        if (zelle.spaltenposition > nachbar.spaltenposition) {
            //Nachbar liegt westlich
            zelle.wand_west = false;
            nachbar.wand_ost = false;
        }
        if (zelle.spaltenposition < nachbar.spaltenposition) {
            //Nachbar liegt östlich
            zelle.wand_ost = false;
            nachbar.wand_west = false;
        }
    }
    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        final int zellgroesse = 30;
        int ursprungX = 30;
        int ursprungY = 30;

        for (int i = 0; i < ANZAHL_ZEILEN; i++) {
            for (int j = 0; j < ANZAHL_SPALTEN; j++) {
                int y = ursprungY + i * zellgroesse;
                int x = ursprungX + j * zellgroesse;
                g.setColor(Color.BLACK);
                Zelle zelle = labyrinthfeld[i][j];
                Line2D lin;
                if (zelle.wand_nord) {
                    lin = new Line2D.Float(x, y, x + zellgroesse, y);
                    g2.draw(lin);
                }
                if (zelle.wand_west) {
                    lin = new Line2D.Float(x, y, x, y + zellgroesse);
                    g2.draw(lin);
                }
                if (zelle.wand_sued) {
                    lin = new Line2D.Float(x, y + zellgroesse, x + zellgroesse, y + zellgroesse);
                    g2.draw(lin);
                }
                if (zelle.wand_ost) {
                    lin = new Line2D.Float(x + zellgroesse, y, x + zellgroesse, y + zellgroesse);
                    g2.draw(lin);
                }
                g2.drawString(zelle.toString(), x + (zellgroesse/2), y + (zellgroesse/2));

            }

        }

    }
    @Override
    public String toString(){
        String result="";
        for (int i = 0; i < ANZAHL_ZEILEN; i++)
        {
            for (int j = 0; j < ANZAHL_SPALTEN; j++) 
            {
               
            }
        }
        return result;
    }
            
}
