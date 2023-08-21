/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.aluno.modelos.Elementos;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author felii
 */
public abstract class ElementoGenerico {

    protected int x;
    protected int y;
    protected int altura;
    protected int largura;
    protected int velocidade;
    protected int vida;
    protected boolean visivel;

    protected Image img;
    protected String caminhoImagem;

    public ElementoGenerico(int x, int y, String caminho, int velocidade, int vida, boolean visivel) {
        this.x = x;
        this.y = y;
        this.caminhoImagem = caminho;
        this.velocidade = velocidade;
        this.vida = vida;
        this.visivel = visivel;
        carregarImagem();
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    private void carregarImagem() {
        this.img = new ImageIcon(getClass().getResource(this.caminhoImagem)).getImage();
        this.altura = this.img.getHeight(null);
        this.largura = this.img.getWidth(null);
    }

    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }
}
