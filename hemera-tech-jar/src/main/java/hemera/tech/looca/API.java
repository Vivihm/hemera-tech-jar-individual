/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hemera.tech.looca;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import com.github.britooo.looca.api.group.sistema.Sistema;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author VivianaMarca
 */
public class API {

    Looca looca = new Looca();

    Sistema sistema = looca.getSistema();
    Processador processador = looca.getProcessador();
    Memoria memoria = looca.getMemoria();

    List<Disco> discos = looca.getGrupoDeDiscos().getDiscos();
    Long tamanhioTotal = looca.getGrupoDeDiscos().getTamanhoTotal();
    // Integer qtdDiscos = looca.getGrupoDeDiscos().getQuantidadeDeDiscos();
    //SETADO EM 0 PQ TENHO APENAS UM DISCO NO NOTE
    Disco discoAtual = discos.get(0);
    //PRECISO DO VOLUME PQ É NELE QUE CONTEM O DISPONIVEL PRA CALCULO DE USO
    List<Volume> volumes = looca.getGrupoDeDiscos().getVolumes();
    Volume volume = volumes.get(0);

    Rede rede = looca.getRede();
    //PEGANDO TODA O GRUPO DE INTERFACES
    List<RedeInterface> interfacesRede = rede.getGrupoDeInterfaces().getInterfaces();

    //FILTRANDO APENAS A INTERFACE QUE TEM DOWLOAD E UPLOAD MAIOR QUE 0
    List<RedeInterface> dadosRede = interfacesRede.stream().filter(rede -> rede.getBytesEnviados() > 0 && rede.getBytesRecebidos() > 0).toList();
    //PEGANDO A UNICA INTERFACE QUE TEM DOWLOAD E UPLOAD
    RedeInterface redeAtual = dadosRede.get(0);

    //RECEBE UM VALOR E FAZ UMA CONTA DE CONVERSAO 
    //TRANFORMA O VALOR EM GIGABYTE
    private static Double byteConverter(long bytes) {
        return (double) bytes / (1024 * 1024 * 1024);
    }
    // TRANFORMA O VALOR EM MEGABYTE

    private static Double byteConverterMega(long bytes) {
        return (double) bytes / (1024 * 1024);
    }

    //Informações computador
    String sistemaOperacional = sistema.getSistemaOperacional();
    String modeloProcessador = processador.getFabricante();
    String macAddress = redeAtual.getEnderecoMac();
    String totalMemoria = String.format("%.2f", byteConverter(memoria.getTotal()));
    String totalDisco = String.format("%.2f", byteConverter(discoAtual.getTamanho()));

    //INFORMAÇÕES PARA TABELA REGISTROS

    public Double getVelocidadeDowload() {
        try {
            Long velocidadeDowloadInicioSegundo = redeAtual.getBytesRecebidos();
            TimeUnit.SECONDS.sleep(1);
            Long velocidadeDowloadFinalSegundo = redeAtual.getBytesRecebidos();
            
            Long diferencaInicioFimSegundoDowload = velocidadeDowloadInicioSegundo - velocidadeDowloadFinalSegundo;
            return byteConverterMega(diferencaInicioFimSegundoDowload);
        } catch (InterruptedException e) {
            System.out.println("Sleep deu errado");
            return 0.0; 
        }
    }
    
        public Double getVelocidadeUpload() {
        try {
            Long velocidadeUploadInicioSegundo = redeAtual.getBytesRecebidos();
            TimeUnit.SECONDS.sleep(1);
            Long velocidadeUploadFinalSegundo = redeAtual.getBytesRecebidos();
            
            Long diferencaInicioFimSegundoUpload = velocidadeUploadInicioSegundo - velocidadeUploadFinalSegundo;
            return byteConverterMega(diferencaInicioFimSegundoUpload);
        } catch (InterruptedException e) {
            System.out.println("Sleep deu errado");
            return 0.0; 
        }
    }

}
