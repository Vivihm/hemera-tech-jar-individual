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

    public void  salvar() {
        Usuario usuario = new Usuario();

        LocalDateTime dataHora = LocalDateTime.now();
        String diaAtual = String.format("%d-%d-%d", dataHora.getDayOfMonth(), dataHora.getMonthValue(), dataHora.getYear());
        File arquivo = new File(diaAtual);

        try {
            arquivo.createNewFile();
            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter escrever = new BufferedWriter(fw);

            //COLOCAR VALIDAÇÕES TAMBEM CASO A PESSOA ERROU A SENHA
            escrever.write(dataHora + " Usuario: " + usuario.getEmail()+ " logou\n");
            escrever.close();
            fw.close();

            System.out.println(dataHora);

        } catch (IOException ex) {
        }
    }

}
