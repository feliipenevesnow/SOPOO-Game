/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.aluno.modelos.Elementos;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felii
 */
public class Estrela extends ElementoGenerico implements Runnable {

    private int dx;

    public Estrela(int x, int y, String caminho, int velocidade, int vida, boolean visivel) {
        super(x, y, caminho, velocidade, vida, visivel);
        Random r = new Random();
        this.y = r.nextInt(900);
        this.x = r.nextInt(4000);
    }

    public void atualizar() {
        this.x += -1;
    }

    @Override
    public void run() {
        while (this.x > -100) {
            if (this.x < -10) {
                this.x = 1300;
            } else {
                atualizar();
            }
            try {
                Thread.sleep(15);
            } catch (InterruptedException ex) {
                Logger.getLogger(TiroDireita.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
