/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.aluno.modelos.Elementos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felii
 */
public class TiroDireita extends ElementoGenerico implements Runnable {

    public TiroDireita(int x, int y, String caminho, int velocidade, int vida, boolean visivel) {
        super(x, y, caminho, velocidade, vida, visivel);
    }

    public void die() {
        this.vida = 0;
    }

    public void atualizar() {
        this.x += velocidade;
    }

    @Override
    public void run() {
        while (this.x < 1400 && this.vida != 0) {

            atualizar();
            try {
                Thread.sleep(2);
            } catch (InterruptedException ex) {
                Logger.getLogger(TiroDireita.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.visivel = false;
    }
}
