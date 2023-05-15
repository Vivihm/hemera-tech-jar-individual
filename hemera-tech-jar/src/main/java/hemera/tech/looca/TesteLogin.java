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

        Conexao conexaoAzure = new Conexao();
        JdbcTemplate conAzure = conexaoAzure.getConnection();

        ConexaoMySql conexaoMySql = new ConexaoMySql();
        JdbcTemplate conMySql = conexaoMySql.getConnection();

        Scanner leitor = new Scanner(System.in);
        System.out.println("Seja bem vindo de volta ao sistema de monitoramento da hemera Tech");
        System.out.println("Email:");
        String emailDigitado = leitor.nextLine();
        System.out.println("Senha:");
        String senhaDigitada = leitor.nextLine();

        //SELECT PARA AUTENTICAR LOGIN
        String selectUsuario = "select Empresa.nome as nomeEmpresa,Empresa.idEmpresa,Funcionario.nome,Funcionario.sobrenome,Funcionario.email,Funcionario.senha, Funcao.funcao from Funcionario join Funcao on Funcionario.idFuncionario = Funcao.idFuncionario join Empresa on Empresa.idEmpresa = Funcao.idEmpresa where Funcionario.email = ? and Funcionario.senha= ?";
        Usuario usuarioLogadoAzure = conAzure.queryForObject(selectUsuario, new BeanPropertyRowMapper<>(Usuario.class), emailDigitado, senhaDigitada);
        Usuario usuarioLogadoMySql = conMySql.queryForObject(selectUsuario, new BeanPropertyRowMapper<>(Usuario.class), emailDigitado, senhaDigitada);

        //System.out.println(usuarioLogado);
        if (usuarioLogadoAzure == null || usuarioLogadoMySql == null) {
            System.out.println("Usuário ou senha incorretos");
        } else {
            //ALTERAÇÃO A SER FEITA
            //puxar hostName da maquina para saber se ela já é cadastrada

            //PUXAR SE EXISTE COMPUTADORES DA EMPRESA COM TAL HOSTNAME  (ALTERAR)
            List<Empresa> computadoresAzure = conAzure.query("select idEmpresa, MacAddress from Computador where idEmpresa = ? and MacAddress = ?",
                    new BeanPropertyRowMapper(Empresa.class), usuarioLogadoAzure.getIdEmpresa(), api.hostName);

            List<Empresa> computadoresMySql = conMySql.query("select idEmpresa, MacAddress from Computador where idEmpresa = ? and MacAddress = ?",
                    new BeanPropertyRowMapper(Empresa.class), usuarioLogadoMySql.getIdEmpresa(), api.hostName);

            if (computadoresAzure.isEmpty() && computadoresMySql.isEmpty()) {
                System.out.println("vamos cadastrar esse  computador");
                // FAZER INSERT NA TABELA COMPUTADOR //PROVAVELMENTE VOU TER QUE MUDARRR
                conAzure.update(String.format("insert into Computador (MacAddress,idEmpresa,modelo,processador,memoria_ram) values ('%s',%d,'%s','%s','16')", api.hostName, usuarioLogadoAzure.getIdEmpresa(), api.sistemaOperacional, api.processador.getFabricante()));
                conMySql.update(String.format("insert into Computador (MacAddress,idEmpresa,modelo,processador,memoria_ram) values ('%s',%d,'%s','%s','16')", api.hostName, usuarioLogadoMySql.getIdEmpresa(), api.sistemaOperacional, api.processador.getFabricante()));

            } else {
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
