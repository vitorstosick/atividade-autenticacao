package AutenticacaoBasica;

public class Usuario {

    private String nomeUsuario;
    private String hashSenha;
    private String salt;

    public Usuario(String nomeUsuario, String hashSenha, String salt) {
        this.nomeUsuario = nomeUsuario;
        this.hashSenha = hashSenha;
        this.salt = salt;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getHashSenha() {
        return hashSenha;
    }

    public String getSalt() {
        return salt;
    }
}
