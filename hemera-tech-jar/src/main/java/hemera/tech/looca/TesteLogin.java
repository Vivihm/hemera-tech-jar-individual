/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hemera.tech.looca;

import com.github.britooo.looca.api.group.discos.Disco;
import java.util.List;
import java.util.Scanner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author VivianaMarca
 */
public class TesteLogin {

    public static void main(String[] args) {
        API api = new API();

        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConnection();

        Scanner leitor = new Scanner(System.in);
        System.out.println("Seja bem vindo de volta ao sistema de monitoramento da hemera Tech");
        System.out.println("Email:");
        String emailDigitado = leitor.nextLine();
        System.out.println("Senha:");
        String senhaDigitada = leitor.nextLine();
        
        //SELECT PARA AUTENTICAR LOGIN
        String selectUsuario = "select empresa.nome as nomeEmpresa,empresa.idEmpresa,funcionario.nome,funcionario.sobrenome,funcionario.email,funcionario.senha, funcao.funcao from funcionario join funcao on funcionario.idFuncionario = funcao.idFuncionario join empresa on empresa.idEmpresa = funcao.idEmpresa where funcionario.email = ? and funcionario.senha= ?";
        Usuario usuarioLogado = con.queryForObject(selectUsuario, new BeanPropertyRowMapper<>(Usuario.class), emailDigitado, senhaDigitada);

        System.out.println(usuarioLogado);

        if (usuarioLogado == null) {
            System.out.println("Usuário ou senha incorretos");
        } else {
            //ALTERAÇÃO A SER FEITA
            //puxar hostName da maquina para saber se ela já é cadastrada

            //PUXAR SE EXISTE COMPUTADORES DA EMPRESA COM TAL HOSTNAME  (ALTERAR)
            List<Empresa> computadores = con.query("select idEmpresa, processador from computador where idEmpresa = ? and processador = ?",
                             new BeanPropertyRowMapper(Empresa.class), usuarioLogado.getIdEmpresa(), api.processador.getFabricante());
           
            if(computadores.isEmpty()){
                System.out.println("vamos cadastrar esse  computador");
                // FAZER INSERT NA TABELA COMPUTADOR //PROVAVELMENTE VOU TER QUE MUDARRR
               con.update(String.format("insert into computador values (2,%d,'%s','%s','16')",usuarioLogado.getIdEmpresa(),api.sistemaOperacional,api.processador.getFabricante()));
            }else{
                System.out.println("computador já está cadastrado");
            }

            //LISTAR DADOS DA MÁQUINA
            System.out.format("\nSISTEMA OPERACIONAL\n%s ", api.sistemaOperacional);
            System.out.format("\nMODELO DO PROCESSADOR\n%s", api.processadorModelo);
            System.out.format("\nTAMANHO TOTAL DA MEMÓRIA\n%d", api.memoriaTotal);

            for (Disco disco : api.discos) {
                System.out.println("\nTAMANHO TOTAL DO DISCO\n" + disco.getTamanho());
            }
            System.out.format("\nHOSTNAME\n%s\n", api.hostName);
            System.out.println("----------------------------------------");
            //CONVERTER EM PORCENTAGEM
            System.out.println("uso" + api.looca.getProcessador().getUso());
        }
    }
}
