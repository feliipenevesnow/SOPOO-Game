/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.aluno.modelos.Elementos;

import br.edu.ifsp.aluno.modelos.Util.TocarMusica;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;

/**
 *
 * @author felii
 */
public class Plasma extends ElementoGenerico implements Runnable {

    private Nave nave;
    private boolean ativo = false;
    private Mensagem plasma;

    public Plasma(Nave nave, int x, int y, String caminho, int velocidade, int vida, boolean visivel, Mensagem plasma) {
        super(x, y, caminho, velocidade, vida, visivel);
        this.nave = nave;
        this.plasma = plasma;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public void run() {
        TimerTask fim = new TimerTask() {
            @Override
            public void run() {
                visivel = false;
                plasma.setImg(new ImageIcon(getClass().getResource("/br/edu/ifsp/aluno/imagens/pretobranco.png")).getImage());
            }
        };
        Timer t = new Timer();
        t.schedule(fim, 5000);
        new TocarMusica("src/br/edu/ifsp/aluno/som/plasma.wav");
        while (visivel) {
            this.x = nave.getX() + nave.getLargura();
            this.y = nave.getY() - nave.getAltura() / 2;
        }
    }

}
