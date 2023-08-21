
package br.edu.ifsp.aluno.jogo;

import br.edu.ifsp.aluno.modelos.Fase.Fase;
import javax.swing.JFrame;


public class Container extends JFrame {

    public Container() {
        this.add(new Fase());
        this.setTitle("Jogo");
        this.setSize(1350, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Container();
    }

}
