/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hemera.tech.looca;

import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author VivianaMarca
 */
public class GraficosTeste {

    public static void main(String[] args) {
        API apiteste = new API();
            Rede rede = apiteste.rede;
            
        List<RedeInterface> redeInter = rede.getGrupoDeInterfaces().getInterfaces();


        System.out.println(apiteste.rede.getParametros());
        System.out.println(apiteste.rede.getParametros().getNomeDeDominio());
        System.out.println(apiteste.rede.getParametros().getServidoresDns());

        System.out.println(apiteste.processador);
                System.out.println(apiteste.processador.getFrequencia());
                System.out.println("------------------------------------------------");
                System.out.println(redeInter);

    }

}
