/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hemera.tech.looca;

/**
 *
 * @author VivianaMarca
 */
public class TESTE {
    public static void main(String[] args) {
        
            API api = new API();
            
          //LISTAR DADOS DA MÁQUINA
            System.out.format("\nSISTEMA OPERACIONAL\n%s ", api.sistemaOperacional);
            System.out.format("\nMODELO DO PROCESSADOR\n%s", api.modeloProcessador);
            System.out.format("\nTAMANHO TOTAL DA MEMÓRIA\n%s", api.totalMemoria);
            System.out.format("\nTAMANHO TOTAL DA MEMÓRIA\n%s", api.totalDisco);
            
            System.out.format("\nmacAddres\n%s\n", api.macAddress);
            System.out.println("----------------------------------------");
    }

}
