package AutenticacaoAvancada;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AutenticacaoAvancada {
    private static Map<String, Usuario> usuarios = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Registrar");
            System.out.println("2. Login");
            System.out.println("3. Sair");
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (escolha) {
                case 1:
                    try {
                        registrar(scanner);
                    } catch (NoSuchAlgorithmException e) {
                        System.out.println("Erro ao registrar: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        login(scanner);
                    } catch (NoSuchAlgorithmException e) {
                        System.out.println("Erro ao fazer login: " + e.getMessage());
                    }
                    break;
                case 3:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }

    private static void registrar(Scanner scanner) throws NoSuchAlgorithmException {
        System.out.print("Nome de usuário: ");
        String usuario = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        if (usuarios.containsKey(usuario)) {
            System.out.println("Usuário já existe!");
            return;
        }

        String salt = SenhaUtilAvancada.gerarSalt();
        String hash = SenhaUtilAvancada.gerarHash(senha, salt);
        usuarios.put(usuario, new Usuario(usuario, hash, salt));
        System.out.println("Usuário registrado com sucesso!");
    }

    private static void login(Scanner scanner) throws NoSuchAlgorithmException {
        System.out.print("Nome de usuário: ");
        String usuario = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Usuario usuarioEncontrado = usuarios.get(usuario);
        if (usuarioEncontrado != null) {
            String hashArmazenado = usuarioEncontrado.getHashSenha();
            String salt = usuarioEncontrado.getSalt();
            String hash = SenhaUtilAvancada.gerarHash(senha, salt);
            System.out.println(hash);


            if (hash.equals(hashArmazenado)) {
                System.out.println("Login bem-sucedido!");
            } else {
                System.out.println("Senha incorreta!");
            }
        } else {
            System.out.println("Usuário não encontrado!");
        }
    }
}