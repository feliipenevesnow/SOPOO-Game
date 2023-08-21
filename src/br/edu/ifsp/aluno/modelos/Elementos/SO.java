/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.aluno.modelos.Elementos;

import br.edu.ifsp.aluno.modelos.CutCine.CutCine;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felii
 */
public class SO extends ElementoGenerico implements Runnable {

    public SO(int x, int y, String caminho, int velocidade, int vida, boolean visivel) {
        super(x, y, caminho, velocidade, vida, visivel);
    }

    @Override
    public void run() {
        while (this.x > 800) {
            this.x -= 1;
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(CutCine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(SO.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (this.x < 1400) {
            this.x += 1;
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(CutCine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.visivel = false;
    }
}
