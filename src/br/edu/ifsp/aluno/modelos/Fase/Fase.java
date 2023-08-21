/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.aluno.modelos.Fase;

import br.edu.ifsp.aluno.modelos.CutCine.CutCine;
import br.edu.ifsp.aluno.modelos.Elementos.Comandante;
import br.edu.ifsp.aluno.modelos.Elementos.Estrela;
import br.edu.ifsp.aluno.modelos.Elementos.EstructusDadus;
import br.edu.ifsp.aluno.modelos.Elementos.Mensagem;
import br.edu.ifsp.aluno.modelos.Elementos.Nave;
import br.edu.ifsp.aluno.modelos.Elementos.Plasma;
import br.edu.ifsp.aluno.modelos.Elementos.SO;
import br.edu.ifsp.aluno.modelos.Elementos.TiroDireita;
import br.edu.ifsp.aluno.modelos.Elementos.TiroEsquerda;
import br.edu.ifsp.aluno.modelos.Elementos.Trediston;
import br.edu.ifsp.aluno.modelos.Util.TocarMusica;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author felii
 */
public class Fase extends JPanel implements ActionListener {

    //Cenário
    private Image cenario;

    //Elementos
    private Nave nave;

    private Comandante krebs;
    private Comandante olivetis;

    private Mensagem msgKrebs;
    private Mensagem msgOlivetis;
    private Mensagem estructus;
    private Mensagem painel;
    private Mensagem plasma;
    private Mensagem jogar;
    private Mensagem gameOver;
    private Mensagem win;
    private Mensagem nova;

    private ArrayList<Trediston> tredistons = new ArrayList<>();
    private ArrayList<Estrela> estrelas = new ArrayList<>();

    private SO sistemaNave;

    private EstructusDadus estructusDadus;

    //Atualizador
    private final Timer timer;
    private Timer musicaFundo;

    //Objeto CutCine
    private CutCine cut;

    //CutCines
    private boolean segunda = false;

    //fim
    private boolean fimJogo = false;

    public Fase() {
        this.setFocusable(true);
        this.setDoubleBuffered(true);

        this.cenario = new ImageIcon(getClass().getResource("/br/edu/ifsp/aluno/imagens/cenario.png")).getImage();

        this.krebs = new Comandante(-50, 800, "/br/edu/ifsp/aluno/imagens/comandanteKrebs.png", 0, 1, true);
        this.olivetis = new Comandante(1000, 800, "/br/edu/ifsp/aluno/imagens/olivetis.png", 0, 1, true);

        this.msgKrebs = new Mensagem(200, 200, "/br/edu/ifsp/aluno/imagens/dialogos/Krebs1.gif", 0, 1, false);
        this.msgOlivetis = new Mensagem(550, 200, "/br/edu/ifsp/aluno/imagens/dialogos/Olivetis1.gif", 0, 1, false);
        this.estructus = new Mensagem(20, 0, "/br/edu/ifsp/aluno/imagens/EstructusDadus.png", 0, 1, false);
        this.painel = new Mensagem(380, 800, "/br/edu/ifsp/aluno/imagens/vidas/3.png", 0, 1, true);
        this.plasma = new Mensagem(770, 800, "/br/edu/ifsp/aluno/imagens/pretobranco.png", 0, 1, true);
        this.jogar = new Mensagem(420, 200, "/br/edu/ifsp/aluno/imagens/jogar1.png", 0, 1, true);
        this.gameOver = new Mensagem(425, 50, "/br/edu/ifsp/aluno/imagens/GO.png", 0, 1, false);
        this.win = new Mensagem(250, 50, "/br/edu/ifsp/aluno/imagens/vitoria.png", 0, 1, true);
        this.nova = new Mensagem(-300, 350, "/br/edu/ifsp/aluno/imagens/nova_1.png", 0, 1, true);

        this.nave = new Nave(-200, 100, "/br/edu/ifsp/aluno/imagens/nav.png", 0, 3, true, plasma);

        this.sistemaNave = new SO(1400, 300, "/br/edu/ifsp/aluno/imagens/SO1.png", 0, 1, true);

        this.estructusDadus = new EstructusDadus(1400, 200, "/br/edu/ifsp/aluno/imagens/boss.gif", 0, 850, false);

        for (int i = 0; i < 100; i++) {
            Estrela e = new Estrela(1400, 0, "/br/edu/ifsp/aluno/imagens/star.png", 0, 1, true);
            estrelas.add(e);
            Thread threadEstrela = new Thread(e);
            threadEstrela.start();
        }

        cut = new CutCine(krebs, olivetis, msgKrebs, msgOlivetis, tredistons, nave, sistemaNave, estructusDadus, plasma, nova, painel);

        this.addKeyListener(new Movimento());

        this.timer = new Timer(5, this);
        this.timer.start();

        new TocarMusica("src/br/edu/ifsp/aluno/som/themeboss.wav");

        this.musicaFundo = new Timer(128000, new TocarMusicaFundo());
        this.musicaFundo.start();

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;

        graficos.drawImage(cenario, 0, 0, null);

        if (!estrelas.isEmpty()) {
            for (Estrela e : estrelas) {
                if (e.isVisivel()) {
                    graficos.drawImage(e.getImg(), e.getX(), e.getY(), this);
                }
            }
        }

        if (this.jogar.isVisivel()) {
            graficos.drawImage(jogar.getImg(), jogar.getX(), jogar.getY(), this);
        }

        if (nova.isVisivel()) {
            graficos.drawImage(nova.getImg(), nova.getX(), nova.getY(), this);
        }

        if (!this.jogar.isVisivel()) {

            if (nave.getVida() > 0) {

                if (sistemaNave.isVisivel()) {
                    graficos.drawImage(sistemaNave.getImg(), sistemaNave.getX(), sistemaNave.getY(), this);
                }

                if (krebs.isVisivel()) {
                    graficos.drawImage(krebs.getImg(), krebs.getX(), krebs.getY(), this);
                }

                if (olivetis.isVisivel()) {
                    graficos.drawImage(olivetis.getImg(), olivetis.getX(), olivetis.getY(), this);
                }

                if (msgKrebs.isVisivel()) {
                    graficos.drawImage(msgKrebs.getImg(), msgKrebs.getX(), msgKrebs.getY(), this);
                }

                if (msgOlivetis.isVisivel()) {
                    graficos.drawImage(msgOlivetis.getImg(), msgOlivetis.getX(), msgOlivetis.getY(), this);
                }

                if (nave.isVisivel()) {
                    graficos.drawImage(nave.getImg(), nave.getX(), nave.getY(), this);
                }

                if (estructusDadus.isVisivel()) {
                    graficos.drawImage(estructusDadus.getImg(), estructusDadus.getX(), estructusDadus.getY(), this);
                    graficos.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    graficos.setColor(Color.RED);
                    graficos.fillRect(20, 30, 850, 20);

                    graficos.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    graficos.setColor(Color.GREEN);
                    graficos.fillRect(20, 30, estructusDadus.getVida(), 20);
                    if (estructusDadus.getVida() > 0) {
                        graficos.drawImage(estructus.getImg(), estructus.getX(), estructus.getY(), this);
                    }
                }

                if (!estructusDadus.getTiros().isEmpty()) {
                    for (int i = 0; i < estructusDadus.getTiros().size(); i++) {
                        TiroEsquerda t = estructusDadus.getTiros().get(i);
                        if (t.isVisivel()) {
                            if (estructusDadus.getVida() > 0) {
                                graficos.drawImage(t.getImg(), t.getX(), t.getY(), this);
                            } else {
                                estructusDadus.getTiros().remove(t);
                            }
                        }
                    }
                }

                if (!tredistons.isEmpty()) {
                    for (int i = 0; i < tredistons.size(); i++) {
                        Trediston t = tredistons.get(i);
                        if (t.isVisivel()) {
                            graficos.drawImage(t.getImg(), t.getX(), t.getY(), this);
                        }
                    }
                }

                if (!nave.getTirosD().isEmpty()) {
                    for (int i = 0; i < nave.getTirosD().size(); i++) {
                        TiroDireita t = nave.getTirosD().get(i);
                        if (t.isVisivel()) {
                            graficos.drawImage(t.getImg(), t.getX(), t.getY(), this);
                        }
                    }
                }

                if (nave.getPlasma() != null) {
                    Plasma p = nave.getPlasma();
                    if (p.isVisivel()) {
                        graficos.drawImage(p.getImg(), p.getX(), p.getY(), this);
                    }
                }

                if (!nave.getTirosE().isEmpty()) {
                    for (int i = 0; i < nave.getTirosE().size(); i++) {
                        TiroEsquerda t = nave.getTirosE().get(i);
                        if (t.isVisivel()) {
                            graficos.drawImage(t.getImg(), t.getX(), t.getY(), this);
                        }
                    }
                }

                if (this.painel.isVisivel()) {
                    graficos.drawImage(painel.getImg(), painel.getX(), painel.getY(), this);
                }

                if (this.plasma.isVisivel()) {
                    graficos.drawImage(plasma.getImg(), plasma.getX(), plasma.getY(), this);
                }

                if (fimJogo) {
                    graficos.drawImage(win.getImg(), win.getX(), win.getY(), this);
                }

            } else {
                graficos.drawImage(gameOver.getImg(), gameOver.getX(), gameOver.getY(), this);
                if (this.painel.isVisivel()) {
                    graficos.drawImage(painel.getImg(), painel.getX(), painel.getY(), this);
                }
            }
        }

        graficos.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        nave.atualizar();
        verificarColisoes();
        if (segunda == false && cut.isFimCut()) {
            if (tredistons.isEmpty()) {
                segundaCut();
            }
        }
        if (fimJogo) {
            for (int x = 0; x < tredistons.size(); x++) {
                Trediston trediston = tredistons.get(x);
                trediston.die();
            }
        }
        repaint();
    }

    public void segundaCut() {
        cut.setCut(2);
        Thread threadCut = new Thread(cut);
        threadCut.start();
        segunda = true;
    }

    public void verificarColisoes() {
        if (nave.getPlasma() != null && nave.getPlasma().isVisivel()) {
            Plasma p = nave.getPlasma();
            if (!tredistons.isEmpty()) {
                for (int x = 0; x < tredistons.size(); x++) {
                    Trediston trediston = tredistons.get(x);
                    if (trediston.getVida() > 0) {
                        if (p.getBounds().intersects(trediston.getBounds())) {
                            trediston.die();
                        }
                    }
                }
            }

            if (estructusDadus.isVisivel()) {
                if (p.getBounds().intersects(estructusDadus.getBounds())) {
                    estructusDadus.sofrerDano(1);
                    if (estructusDadus.getVida() <= 0) {
                        fimJogo = true;
                    }
                }
            }

        }

        if (!tredistons.isEmpty()) {
            for (int x = 0; x < tredistons.size(); x++) {
                Trediston trediston = tredistons.get(x);
                if (trediston.getVida() > 0) {
                    if (trediston.getBounds().intersects(nave.getBounds())) {
                        nave.sofrerDano(1);
                        trediston.die();
                        nave.mudarPainel(painel);
                    }
                }

            }
        }

        if (!estructusDadus.getTiros().isEmpty()) {
            for (int i = 0; i < estructusDadus.getTiros().size(); i++) {
                TiroEsquerda tiro = estructusDadus.getTiros().get(i);
                if (tiro.isVisivel()) {
                    if (tiro.getBounds().intersects(nave.getBounds())) {
                        nave.sofrerDano(1);
                        tiro.die();
                        estructusDadus.getTiros().remove(tiro);
                        nave.mudarPainel(painel);
                        if (nave.getVida() == 0) {
                            plasma.setVisivel(false);
                        }
                    }
                    if (nave.getPlasma() != null && nave.getPlasma().isVisivel()) {
                        if (tiro.getBounds().intersects(nave.getPlasma().getBounds())) {
                            tiro.die();
                            estructusDadus.getTiros().remove(tiro);
                        }
                    }
                }
            }

        }

        if (!nave.getTirosD().isEmpty()) {
            for (int i = 0; i < nave.getTirosD().size(); i++) {
                TiroDireita tiro = nave.getTirosD().get(i);
                if (!tredistons.isEmpty()) {
                    for (int x = 0; x < tredistons.size(); x++) {
                        Trediston trediston = tredistons.get(x);
                        if (trediston.getVida() > 0) {
                            if (tiro.getBounds().intersects(trediston.getBounds())) {
                                tiro.die();
                                trediston.die();
                                nave.getTirosD().remove(tiro);
                            }
                        }
                    }
                }
                if (tiro.getBounds().intersects(estructusDadus.getBounds())) {
                    tiro.die();
                    estructusDadus.sofrerDano(1);
                    nave.getTirosD().remove(tiro);
                    if (estructusDadus.getVida() <= 0) {
                        fimJogo = true;
                    }
                }
            }
        }
        if (!nave.getTirosE().isEmpty()) {
            for (int i = 0; i < nave.getTirosE().size(); i++) {
                TiroEsquerda tiro = nave.getTirosE().get(i);
                if (!tredistons.isEmpty()) {
                    for (int x = 0; x < tredistons.size(); x++) {
                        Trediston trediston = tredistons.get(x);
                        if (trediston.getVida() > 0) {
                            if (tiro.getBounds().intersects(trediston.getBounds())) {
                                tiro.die();
                                trediston.die();
                                nave.getTirosE().remove(tiro);

                            }
                        }
                    }
                }
            }
        }
    }

    public class Movimento extends KeyAdapter implements ActionListener {

        private Timer timer;

        public Movimento() {
            timer = new Timer(250, this);
            timer.start();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            nave.KeyPressed(e);
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                jogar.setVisivel(false);
                cut.setCut(1);
                Thread threadCut = new Thread(cut);
                threadCut.start();
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            nave.KeyReleased(e);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            nave.setAtirar(true);
        }
    }

    public class TocarMusicaFundo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new TocarMusica("src/br/edu/ifsp/aluno/som/themeboss.wav");
        }

    }
}
