/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.aluno.modelos.Util;

import br.edu.ifsp.aluno.modelos.Fase.Fase;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author felii
 */
public class TocarMusica {

    public TocarMusica(String soundFile) {

        File f = new File(soundFile);

        AudioInputStream audioIn = null;

        try {
            audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
        } catch (MalformedURLException ex) {
            Logger.getLogger(Fase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(Fase.class.getName()).log(Level.SEVERE, null, ex);
        }

        Clip clip = null;

        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Fase.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            clip.open(audioIn);
        } catch (LineUnavailableException | IOException ex) {
            Logger.getLogger(Fase.class.getName()).log(Level.SEVERE, null, ex);
        }

        clip.start();
    }
}
