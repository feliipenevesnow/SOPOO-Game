/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.aluno.modelos.Elementos;

import br.edu.ifsp.aluno.modelos.CutCine.CutCine;
import br.edu.ifsp.aluno.modelos.Util.TocarMusica;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author felii
 */
public class EstructusDadus extends ElementoGenerico implements Runnable {

    private boolean subir = true;
    private List<TiroEsquerda> tiros = new ArrayList<>();
    private boolean atirar = true;

    public EstructusDadus(int x, int y, String caminho, int velocidade, int vida, boolean visivel) {
        super(x, y, caminho, velocidade, vida, visivel);
    }

    public List<TiroEsquerda> getTiros() {
        return tiros;
    }


    public void subir() {
        this.y -= 1;
        if (this.y < 0) {
            subir = false;
        }
        try {
            Thread.sleep(15);
        } catch (InterruptedException ex) {
            Logger.getLogger(EstructusDadus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void descer() {
        this.y += 1;
        if (this.y > 300) {
            subir = true;
        }
        try {
            Thread.sleep(15);
        } catch (InterruptedException ex) {
            Logger.getLogger(EstructusDadus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sofrerDano(int dano) {
        this.vida = this.vida - dano;
    }

    public void atirar() {
        new TocarMusica("src/br/edu/ifsp/aluno/som/tiroBoss.wav");
        TiroEsquerda t = new TiroEsquerda(x, y + (int) altura / 2, "/br/edu/ifsp/aluno/imagens/tiroBoss.gif", -1, 1, true);
        tiros.add(t);
        Thread threadTiro = new Thread(t);
        threadTiro.start();
        atirar = true;
    }

    @Override
    public void run() {
        while (this.x > 1000 && this.vida != 0) {
            this.x -= 1;
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(CutCine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        while (vida != 0) {
            if (subir) {
                subir();
            } else {
                descer();
            }
            if (atirar) {
                TimerTask atirar = new TimerTask() {
                    @Override
                    public void run() {
                        atirar();
                    }
                };

                Timer t = new Timer();
                t.schedule(atirar, 2000);
                this.atirar = false;
            }
        }
        this.visivel = false;
    }

}
