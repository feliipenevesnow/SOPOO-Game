/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.aluno.modelos.Elementos;

/**
 *
 * @author felii
 */
public class Mensagem extends ElementoGenerico implements Runnable {

    public Mensagem(int x, int y, String caminho, int velocidade, int vida, boolean visivel) {
        super(x, y, caminho, velocidade, vida, visivel);
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
