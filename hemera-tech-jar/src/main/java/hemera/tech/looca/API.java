/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hemera.tech.looca;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import java.util.List;

/**
 *
 * @author VivianaMarca
 */
public class API {    
    Looca looca = new Looca();
    Sistema sistema = looca.getSistema();
    Processador processador = looca.getProcessador();
    Memoria memoria = looca.getMemoria();
    Temperatura temperatura = looca.getTemperatura();
    Rede rede = looca.getRede();
    DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
    List<Disco> discos = grupoDeDiscos.getDiscos();

    //Informações computador
    
    String sistemaOperacional = sistema.getSistemaOperacional();
    String processadorModelo = processador.getNome();
    Long memoriaTotal = memoria.getTotal();
    String hostName = rede.getParametros().getHostName();
    
     // for (Disco disco : discos) {
       //     System.out.println("\nTAMANHO TOTAL DO DISCO\n" + disco.getTamanho());
        //}
    
    //METODOS PARA OS GRÁFICOS
    Double processadorUsoEmPorncentagem = processador.getUso();
}
