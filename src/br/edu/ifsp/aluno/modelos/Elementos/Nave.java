/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.aluno.modelos.Elementos;

import br.edu.ifsp.aluno.modelos.Fase.Fase;
import br.edu.ifsp.aluno.modelos.Util.TocarMusica;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

/**
 *
 * @author felii
 */
public class Nave extends ElementoGenerico {

    private List<TiroDireita> tirosD;
    private List<TiroEsquerda> tirosE;
    private int dx;
    private int dy;
    private boolean mexer = false;
    private boolean sentidoDireita = true;
    private Plasma plasma;
    private Mensagem imgPlasma;
    private boolean faseDois = false;
    private boolean atirar = true;

    private int qtdPlasma = 1;
    private boolean liberarPlasma = false;

    public Nave(int x, int y, String caminho, int velocidade, int vida, boolean visivel, Mensagem imgPlasma) {
        super(x, y, caminho, velocidade, vida, visivel);
        tirosD = new ArrayList<>();
        tirosE = new ArrayList<>();
        this.imgPlasma = imgPlasma;
    }

    public void setAtirar(boolean atirar) {
        this.atirar = atirar;
    }

    public void setLiberarPlasma(boolean liberarPlasma) {
        this.liberarPlasma = liberarPlasma;
    }

    public void setFaseDois(boolean faseDois) {
        this.faseDois = faseDois;
    }

    public Plasma getPlasma() {
        return plasma;
    }

    public boolean isMexer() {
        return mexer;
    }

    public void setSentidoDireita(boolean sentidoDireita) {
        this.sentidoDireita = sentidoDireita;
    }

    public void setMexer(boolean mexer) {
        this.mexer = mexer;
    }

    public List<TiroDireita> getTirosD() {
        return tirosD;
    }

    public List<TiroEsquerda> getTirosE() {
        return tirosE;
    }

    public void sofrerDano(int dano) {
        vida -= dano;
    }

    public void mudarPainel(Mensagem painel) {
        switch (vida) {
            case 2:
                painel.setImg(new ImageIcon(getClass().getResource("/br/edu/ifsp/aluno/imagens/vidas/2.png")).getImage());
                break;
            case 1:
                painel.setImg(new ImageIcon(getClass().getResource("/br/edu/ifsp/aluno/imagens/vidas/1.png")).getImage());
                break;
            case 0:
                painel.setImg(new ImageIcon(getClass().getResource("/br/edu/ifsp/aluno/imagens/vidas/0.png")).getImage());
                break;
        }
    }

    

    public void atualizar() {
        this.x += this.dx;
        this.y += this.dy;
    }

    public void KeyPressed(KeyEvent e) {
        if (mexer) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                    if (!faseDois) {
                        sentidoDireita = true;
                        this.img = new ImageIcon(getClass().getResource("/br/edu/ifsp/aluno/imagens/nav.png")).getImage();
                        this.dx = 2;
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (!faseDois) {
                        sentidoDireita = false;
                        this.img = new ImageIcon(getClass().getResource("/br/edu/ifsp/aluno/imagens/nav_esquerda.png")).getImage();
                        this.dx = -2;
                    }
                    break;
                case KeyEvent.VK_UP:
                    this.dy = -2;
                    break;
                case KeyEvent.VK_DOWN:
                    this.dy = 2;
                    break;
                case KeyEvent.VK_E:
                    if (atirar) {
                        new TocarMusica("src/br/edu/ifsp/aluno/som/tiro.wav");
                        if (sentidoDireita) {
                            TiroDireita t = new TiroDireita(x + largura, y + (int) altura / 2, "/br/edu/ifsp/aluno/imagens/tiro.png", 2, 1, true);
                            tirosD.add(t);
                            Thread threadTiro = new Thread(t);
                            threadTiro.start();
                        } else {
                            TiroEsquerda t = new TiroEsquerda(x, y + (int) altura / 2, "/br/edu/ifsp/aluno/imagens/tiro.png", -2, 1, true);
                            tirosE.add(t);
                            Thread threadTiro = new Thread(t);
                            threadTiro.start();
                        }
                        atirar = false;
                    }
                    break;
                case KeyEvent.VK_CONTROL:
                    if (liberarPlasma) {
                        if (qtdPlasma == 1) {
                            plasma = new Plasma(this, x + largura, y - altura / 2, "/br/edu/ifsp/aluno/imagens/plasma.gif", 0, 1, true, imgPlasma);
                            plasma.setAtivo(true);
                            Thread threadPlasma = new Thread(plasma);
                            threadPlasma.start();
                            qtdPlasma--;
                        }
                    }
                    break;
            }
        }
    }

    public void KeyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                this.dx = 0;
                break;
            case KeyEvent.VK_LEFT:
                this.dx = 0;
                break;
            case KeyEvent.VK_UP:
                this.dy = 0;
                break;
            case KeyEvent.VK_DOWN:
                this.dy = 0;
                break;
        }
    }

}
