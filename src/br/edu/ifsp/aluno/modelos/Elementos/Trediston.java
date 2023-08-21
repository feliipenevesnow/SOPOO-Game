/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.aluno.modelos.Elementos;

import br.edu.ifsp.aluno.modelos.Util.TocarMusica;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Aluno
 */
public class Trediston extends ElementoGenerico implements Runnable {

    private boolean trocarImage = false;

    private int velocidade = 10;

    private int acrescimoX = 1;
    private int acrescimoY = 1;
    private int acrescimoDist = 1;

    private int distancia = 40;

    private boolean vivo = true;

    private boolean indoDireita = true;

    private ArrayList<Trediston> tredistons;

    public Trediston(ArrayList<Trediston> tredistons, int x, int y, String caminho, int velocidade, int vida, boolean visivel) {
        super(x, y, caminho, velocidade, vida, visivel);
        this.tredistons = tredistons;
    }

    public int getAcrescimoX() {
        return acrescimoX;
    }

    public void setAcrescimoX(int acrescimoX) {
        this.acrescimoX = acrescimoX;
    }

    public int getAcrescimoY() {
        return acrescimoY;
    }

    public void setAcrescimoY(int acrescimoY) {
        this.acrescimoY = acrescimoY;
    }

    public int getAcrescimoDist() {
        return acrescimoDist;
    }

    public void setAcrescimoDist(int acrescimoDist) {
        this.acrescimoDist = acrescimoDist;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getAltura() {
        return altura;
    }

    @Override
    public void setAltura(int altura) {
        this.altura = altura;
    }

    @Override
    public int getLargura() {
        return largura;
    }

    @Override
    public void setLargura(int largura) {
        this.largura = largura;
    }

    @Override
    public Image getImg() {
        return img;
    }

    @Override
    public void setImg(Image img) {
        this.img = img;
    }

    public void die() {
        this.vida = 0;
        new TocarMusica("src/br/edu/ifsp/aluno/som/morteTrediston.wav");
    }

    public void removerDaList() {
        this.tredistons.remove(this);
    }

    @Override
    public void run() {
        while (this.vida != 0) {
            int soma = 0;
            while (soma < this.distancia && this.vida != 0) {
                soma += this.acrescimoDist;
                andar();

//                if(this.x == 0)
//                    this.vivo = false;
            }
            sortearTudo();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Trediston.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        ImageIcon img = new ImageIcon(getClass().getResource("/br/edu/ifsp/aluno/imagens/trediston_morte_esquerda.gif"));
        this.img = img.getImage();
        this.x = this.x - 50;
        this.y = this.y - 50;
        TimerTask morto = new TimerTask() {
            @Override
            public void run() {
                visivel = false;
                removerDaList();
            }
        };
        Timer t = new Timer();
        t.schedule(morto, 600);
    }

    public void sortearTudo() {
        this.distancia = (int) (Math.random() * 300) + 500;

        do {
            this.acrescimoX = (int) (Math.random() * 5) - 2;
            this.acrescimoY = (int) (Math.random() * 5) - 2;
        } while (this.acrescimoX == 0 && this.acrescimoY == 0);

        this.velocidade = (int) (Math.random() * 30) + 50;
        this.acrescimoDist = (int) Math.sqrt(Math.pow(this.acrescimoX, 2)
                + Math.pow(this.acrescimoY, 2));

    }

    private void andar() {
        try {
            Thread.sleep(1000 / this.velocidade);

            if (this.acrescimoX > 0 && this.vida != 0) {
                ImageIcon img = new ImageIcon(getClass().getResource("/br/edu/ifsp/aluno/imagens/tredistons_direita.gif"));
                this.img = img.getImage();
            } else if (this.acrescimoX <= 0 && this.vida != 0) {
                ImageIcon img = new ImageIcon(getClass().getResource("/br/edu/ifsp/aluno/imagens/tredistons.gif"));
                this.img = img.getImage();
            }

            this.x += this.acrescimoX;

            if (this.x <= 0) {
                this.acrescimoX *= (-1);
            }

            if (this.x >= 1400) {
                this.acrescimoX *= (-1);
            }

            this.y += this.acrescimoY;

            if (this.y <= -100) {
                this.acrescimoY *= (-1);
            }

            if (this.y >= 600) {
                this.acrescimoY *= (-1);
            }

        } catch (InterruptedException ex) {
            System.out.println("zica");
        }

    }
}
