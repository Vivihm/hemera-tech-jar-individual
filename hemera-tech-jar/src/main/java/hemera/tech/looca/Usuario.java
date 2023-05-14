/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hemera.tech.looca;

/**
 *
 * @author VivianaMarca
 */
public class Usuario {
    private String nomeEmpresa;
    private Integer idEmpresa;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String funcao;

    public Usuario(String nomeEmpresa, Integer idEmpresa, String nome, String sobrenome, String email, String senha, String funcao) {
        this.nomeEmpresa = nomeEmpresa;
        this.idEmpresa = idEmpresa;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.funcao = funcao;
    }

        public Usuario() {
       
    }
    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return  String.format("\nUsuario"
                + "\nEmpresa: %s"
                + "\nidEmpresa: %d"
                + "\nFuncionário: %s %s"
                + "\nEmail: %s"
                + "\nSenha: %s"
                + "\nFunção: %s",nomeEmpresa, idEmpresa, nome, sobrenome, email,senha,funcao);
    }

   }
