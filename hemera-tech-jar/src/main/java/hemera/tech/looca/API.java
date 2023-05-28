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
import com.github.britooo.looca.api.group.rede.RedeInterface;
import com.github.britooo.looca.api.group.sistema.Sistema;
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
    
    DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
    List<Disco> discos = grupoDeDiscos.getDiscos();
    Disco discoAtual = discos.get(0);
    
    Rede rede = looca.getRede();
    List<RedeInterface> interfacesRede = rede.getGrupoDeInterfaces().getInterfaces();
    List<RedeInterface> dadosRede = interfacesRede.stream().filter(rede -> rede.getBytesEnviados() > 0 && rede.getBytesRecebidos() > 0).toList();
    RedeInterface redeAtual = dadosRede.get(0);
  
    //COPIEI NAO ENTENDI
    private static Double byteConverter(long bytes){
        return (double) bytes /(1024 * 1024 * 1024);
    }
    
    //Informações computador
     String sistemaOperacional = sistema.getSistemaOperacional();
     String modeloProcessador = processador.getFabricante();
     String macAddress = redeAtual.getEnderecoMac();
     String totalMemoria = String.format("%.2f", byteConverter(memoria.getTotal()));
     String totalDisco = String.format("%.2f", byteConverter(discoAtual.getTamanho()));

}