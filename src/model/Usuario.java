package model;

public class Usuario {
    
    private int id;
    private String nome;
    private String cpf;
    private String senha;
    private String telefone;
    private String cargo;
    private String sexo;

    public Usuario(int id, String nome, String cpf, String senha, String telefone, String cargo, String sexo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.telefone = telefone;
        this.cargo = cargo;
        this.sexo = sexo;
    }

    public Usuario(String nome, String cpf, String senha, String telefone, String cargo, String sexo) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.telefone = telefone;
        this.cargo = cargo;
        this.sexo = sexo;
    }

    public Usuario(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String cargo) {
        this.sexo = sexo;
    }
    
    @Override
    public String toString(){
        return "Dados usuário : "+this.id+" --"+this.cargo+" --"+this.cpf+" --"+this.senha;
    }
}
