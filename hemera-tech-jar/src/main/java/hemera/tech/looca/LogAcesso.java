/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hemera.tech.looca;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 *
 * @author VivianaMarca
 */
public class LogAcesso {

    public void salvar(String email, Boolean logou) {

        LocalDateTime dataHora = LocalDateTime.now();
        String horaAtual = String.format("%d:%d:%d ",dataHora.getHour(),dataHora.getMinute(),dataHora.getSecond());
        String diaAtual = dataHora.toLocalDate().toString();

        File arquivo = new File(diaAtual);

        try {
            arquivo.createNewFile();
            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter escrever = new BufferedWriter(fw);

            if (logou) {
                escrever.write(diaAtual+" - "+ horaAtual +" - " + email +" - " +" fez login \n");
            } else {
                escrever.write(diaAtual + " - "+ horaAtual +" - "+ email +" - " +" fez uma tentativa de login\n");
            }
            escrever.close();
            fw.close();

            System.out.println("inseri");

        } catch (IOException ex) {
        }
    }

}
