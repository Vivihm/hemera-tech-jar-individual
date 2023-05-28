/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hemera.tech.looca;

import com.github.britooo.looca.api.group.discos.Disco;

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

        //System.out.println(api.qtdDiscos);
        System.out.println("CPU");
        System.out.println(api.usoCpu);

        System.out.println("DISCO - porcentagem ocupada");
        System.out.println(api.usoDisco);

        System.out.println("MEMORIA - porcentagem ocupada");
        System.out.println(api.usoRam);

        System.out.println("Velocidade Dowload - mpbs");
        System.out.println(api.getVelocidadeDowload());
        
        System.out.println("Velocidade Upload - mpbs");
        System.out.println(api.getVelocidadeUpload());

    }

}
