/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package br.edu.ifsp.aluno.modelos.CutCine;

import br.edu.ifsp.aluno.modelos.Elementos.Comandante;
import br.edu.ifsp.aluno.modelos.Elementos.EstructusDadus;
import br.edu.ifsp.aluno.modelos.Elementos.Mensagem;
import br.edu.ifsp.aluno.modelos.Elementos.Nave;
import br.edu.ifsp.aluno.modelos.Elementos.SO;
import br.edu.ifsp.aluno.modelos.Elementos.Trediston;

import br.edu.ifsp.aluno.modelos.Util.TocarMusica;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

/**
 *
 * @author felii
 */
public class CutCine implements Runnable {

    private Comandante krebs;
    private Comandante olivetis;
    private Mensagem msgKrebs;
    private Mensagem msgOlivetis;
    private ArrayList<Trediston> tredistons;
    private Nave nave;
    private SO so;
    private EstructusDadus estructusDadus;
    private Mensagem plasma;
    private Mensagem nova;
    private Mensagem painel;

    private int cut;

    private boolean fimCut = false;

    public CutCine(Comandante krebs, Comandante olivetis, Mensagem msgKrebs, Mensagem msgOlivetis, ArrayList<Trediston> tredistons, Nave nave, SO so, EstructusDadus estructusDadus, Mensagem plasma, Mensagem nova, Mensagem painel) {
        this.krebs = krebs;
        this.olivetis = olivetis;
        this.msgKrebs = msgKrebs;
        this.msgOlivetis = msgOlivetis;
        this.tredistons = tredistons;
        this.nave = nave;
        this.so = so;
        this.estructusDadus = estructusDadus;
        this.plasma = plasma;
        this.nova = nova;
        this.painel = painel;
    }

    public void setCut(int cut) {
        this.cut = cut;
    }

    public boolean isFimCut() {
        return fimCut;
    }

    public void setFimCut(boolean fimCut) {
        this.fimCut = fimCut;
    }

    public void primeira() {
        while (painel.getY() > 460) {
            painel.setY(painel.getY() - 2);
            if (plasma.getY() > 648) {
                plasma.setY(plasma.getY() - 1);
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(CutCine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(CutCine.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (nave.getX() < 100) {
            nave.setX(nave.getX() + 1);
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(CutCine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        while (krebs.getY() > 421) {
            krebs.setY(krebs.getY() - 1);
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(CutCine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        msgKrebs.setVisivel(true);

        new TocarMusica("src/br/edu/ifsp/aluno/som/typing.wav");

        TimerTask dialogoDois = new TimerTask() {
            @Override
            public void run() {
                msgKrebs.setImg(new ImageIcon(getClass().getResource("/br/edu/ifsp/aluno/imagens/dialogos/Krebs2.gif")).getImage());
                new TocarMusica("src/br/edu/ifsp/aluno/som/typing.wav");
            }
        };

        TimerTask dialogoTres = new TimerTask() {
            @Override
            public void run() {
                msgKrebs.setImg(new ImageIcon(getClass().getResource("/br/edu/ifsp/aluno/imagens/dialogos/Krebs3.gif")).getImage());
                new TocarMusica("src/br/edu/ifsp/aluno/som/typing.wav");
            }
        };

        TimerTask dialogoQuatro = new TimerTask() {
            @Override
            public void run() {
                msgKrebs.setImg(new ImageIcon(getClass().getResource("/br/edu/ifsp/aluno/imagens/dialogos/Krebs4.gif")).getImage());
                new TocarMusica("src/br/edu/ifsp/aluno/som/typing.wav");
            }
        };

        TimerTask dialogoCinco = new TimerTask() {
            @Override
            public void run() {
                msgKrebs.setImg(new ImageIcon(getClass().getResource("/br/edu/ifsp/aluno/imagens/dialogos/Krebs5.gif")).getImage());
                new TocarMusica("src/br/edu/ifsp/aluno/som/typingCurto.wav");
            }
        };

        TimerTask fimDialogo = new TimerTask() {
            @Override
            public void run() {
                msgKrebs.setVisivel(false);
                while (krebs.getY() < 800) {
                    krebs.setY(krebs.getY() + 1);
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CutCine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                for (int i = 0; i < 10; i++) {
                    Trediston g = new Trediston(tredistons, 1350, 200, "/br/edu/ifsp/aluno/imagens/tredistons.gif", 0, 1, true);
                    tredistons.add(g);
                    Thread gThread = new Thread(g);
                    gThread.start();
                }
                nave.setMexer(true);
                fimCut = true;
                Thread threadSO = new Thread(so);
                threadSO.start();
//                ChecarColisoesTiroD ccTiroD = new ChecarColisoesTiroD(tredistons, nave.getTirosD());
//                Thread thccTiroD = new Thread(ccTiroD);
//                thccTiroD.start();
//                ChecarColisoesTiroE ccTiroE = new ChecarColisoesTiroE(tredistons, nave.getTirosE());
//                Thread thccTiroE = new Thread(ccTiroE);
//                thccTiroE.start();
            }
        };

        Timer t = new Timer();
        t.schedule(dialogoDois, 5500);//executa a task passada como argumento 1 ms após iniciar a cada 2 seg;
        t.schedule(dialogoTres, 11000);
        t.schedule(dialogoQuatro, 16000);
        t.schedule(dialogoCinco, 21000);
        t.schedule(fimDialogo, 26000);

    }

    public void segunda() {
        nave.setMexer(false);
        while (nave.getX() > 0) {
            nave.setX(nave.getX() - 1);
            if (nave.getY() > 101) {
                nave.setY(nave.getY() - 1);
            } else if (nave.getY() < 99) {
                nave.setY(nave.getY() + 1);
            }
            try {
                Thread.sleep(3);
            } catch (InterruptedException ex) {
                Logger.getLogger(CutCine.class.getName()).log(Level.SEVERE, null, ex);
            }
            nave.setMexer(false);
            nave.setImg(new ImageIcon(getClass().getResource("/br/edu/ifsp/aluno/imagens/nav.png")).getImage());
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CutCine.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (olivetis.getY() > 421) {
            olivetis.setY(olivetis.getY() - 1);
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(CutCine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        msgOlivetis.setVisivel(true);
        new TocarMusica("src/br/edu/ifsp/aluno/som/typing.wav");

        TimerTask dialogoDois = new TimerTask() {
            @Override
            public void run() {
                msgOlivetis.setImg(new ImageIcon(getClass().getResource("/br/edu/ifsp/aluno/imagens/dialogos/Olivetis2.gif")).getImage());
                new TocarMusica("src/br/edu/ifsp/aluno/som/typing.wav");
            }
        };

        TimerTask dialogoTres = new TimerTask() {
            @Override
            public void run() {
                msgOlivetis.setImg(new ImageIcon(getClass().getResource("/br/edu/ifsp/aluno/imagens/dialogos/Olivetis3.gif")).getImage());
                new TocarMusica("src/br/edu/ifsp/aluno/som/typing.wav");
            }
        };

        TimerTask dialogoQuatro = new TimerTask() {
            @Override
            public void run() {
                msgOlivetis.setImg(new ImageIcon(getClass().getResource("/br/edu/ifsp/aluno/imagens/dialogos/Olivetis4.gif")).getImage());
                new TocarMusica("src/br/edu/ifsp/aluno/som/typing.wav");
                while (nova.getX() < 100) {
                    nova.setX(nova.getX() + 1);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CutCine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };

        TimerTask dialogoCinco = new TimerTask() {
            @Override
            public void run() {
                msgOlivetis.setImg(new ImageIcon(getClass().getResource("/br/edu/ifsp/aluno/imagens/dialogos/Olivetis5.gif")).getImage());
                new TocarMusica("src/br/edu/ifsp/aluno/som/typing.wav");
            }
        };

        TimerTask fimDialogo = new TimerTask() {
            @Override
            public void run() {
                msgOlivetis.setVisivel(false);
                while (olivetis.getY() < 800) {
                    olivetis.setY(olivetis.getY() + 1);
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CutCine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                while (nova.getX() > -300) {
                    nova.setX(nova.getX() - 1);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CutCine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                for (int i = 0; i < 10; i++) {
                    Trediston g = new Trediston(tredistons, 1350, 200, "/br/edu/ifsp/aluno/imagens/tredistons.gif", 0, 1, true);
                    tredistons.add(g);
                    Thread gThread = new Thread(g);
                    gThread.start();
                }
                nave.setMexer(true);
                nave.setFaseDois(true);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CutCine.class.getName()).log(Level.SEVERE, null, ex);
                }

                Thread threadBoss = new Thread(estructusDadus);
                threadBoss.start();

                estructusDadus.setVisivel(true);
                nave.setSentidoDireita(true);
                nave.setLiberarPlasma(true);
                plasma.setImg(new ImageIcon(getClass().getResource("/br/edu/ifsp/aluno/imagens/plasma.png")).getImage());
                nave.setMexer(true);
//                ChecarColisoesTiroD ccTiroD = new ChecarColisoesTiroD(tredistons, nave.getTirosD());
//                Thread thccTiroD = new Thread(ccTiroD);
//                thccTiroD.start();
//                ChecarColisoesTiroE ccTiroE = new ChecarColisoesTiroE(tredistons, nave.getTirosE());
//                Thread thccTiroE = new Thread(ccTiroE);
//                thccTiroE.start();
            }
        };

        Timer t = new Timer();
        t.schedule(dialogoDois, 4700);//executa a task passada como argumento 1 ms após iniciar a cada 2 seg;
        t.schedule(dialogoTres, 9600);
        t.schedule(dialogoQuatro, 15000);
        t.schedule(dialogoCinco, 20000);
        t.schedule(fimDialogo, 25000);

    }

    @Override
    public void run() {
        switch (cut) {
            case 1:
                primeira();
                fimCut = false;
                break;
            case 2:
                segunda();
                break;
            case 3:
                break;
        }
    }

}
