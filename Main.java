import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Robo 
    buscarRobo(ArrayList<Robo> robos, int codigo) {

        Robo encontrado = null;

        for (Robo r : robos) {
            if (r.codigo == codigo) {
                encontrado = r;
            }
        }
        return encontrado;
    }

    public static void cadastrar(
            Scanner teclado,
            ArrayList<Robo> robos) {

        int codigo = 1;

        for (int i = 0; i < robos.size(); i++) {
        }

        System.out.println("Codigo: " + codigo);

        System.out.print("Nome: ");
        String nome = teclado.nextLine();

        if (nome.length() == 0) {
            System.out.println("O nome nao pode estar vazio.");
            return;
        }

        System.out.print("Ataque: ");
        int ataque = teclado.nextInt();

        if (ataque < 10 || ataque > 30) {
            System.out.println("O ataque deve estar entre 10 e 30.");
            return;
        }

        System.out.print("Defesa: ");
        int defesa = teclado.nextInt();

        if (defesa < 0 || defesa > 20) {
            System.out.println("A defesa deve estar entre 0 e 20.");
            return;
        }

        robos.add(new Robo(codigo, nome, ataque, defesa));

        System.out.println("Robo cadastrado.");
    }

    public static void listar(ArrayList<Robo> robos) {

        if (robos.size() == 0) {
            System.out.println("Nenhum robo cadastrado.");
            return;
        }

        for (Robo r : robos) {
            r.exibirDados();
        }
    }

    public static void consultar(
            Scanner teclado,
            ArrayList<Robo> robos) {

        System.out.print("Codigo: ");
        int codigo = teclado.nextInt();

        for(Robo r : robos) {
            if(r.codigo == codigo) {
                r.exibirDados();
                return;
            }
        }
            System.out.println("Robo nao encontrado.");
    }

    public static void combate(
            Scanner teclado,
            ArrayList<Robo> robos) {

        System.out.print("Codigo do primeiro robo: ");
        int codigo1 = teclado.nextInt();

        System.out.print("Codigo do segundo robo: ");
        int codigo2 = teclado.nextInt();

        if (codigo1 == codigo2) {
            System.out.println("Os robos devem ser diferentes.");
            return;
        }

        Robo robo1 = null;
        Robo robo2 = null;

        for(Robo r : robos) {
            if(r.codigo == codigo1) {
                robo1 = r;
            }
            if(r.codigo == codigo2) {
                robo2 = r;
            }
        }

        if (robo1 == null || robo2 == null){
            System.out.println("Não tem os robo.");
            return;
        }

        if (robo1.energia < 30 || robo2.energia < 30) {
            System.out.println(
                "Os dois robos precisam ter pelo menos 30 de energia."
            );
            return;
        }

        Robo primeiro;
        Robo segundo;

        if (robo1.pontos < robo2.pontos) {
            primeiro = robo1;
            segundo = robo2;

        } else if (robo2.pontos < robo1.pontos) {
            primeiro = robo2;
            segundo = robo1;

        } else if (robo1.codigo < robo2.codigo) {
            primeiro = robo1;
            segundo = robo2;

        } else {
            primeiro = robo2;
            segundo = robo1;
        }

        System.out.println();
        System.out.println(primeiro.nome + " comeca atacando.");

        for (int rodada = 1; rodada <= 5; rodada++) {

            System.out.println();
            System.out.println("Rodada " + rodada);

            int dano = primeiro.calcularDano(segundo, rodada);

            segundo.receberDano(dano);

            System.out.println(primeiro.nome + " atacou " + segundo.nome + " causando " + dano + " de dano.");
            System.out.println("Energia de " + segundo.nome + ": " + segundo.energia);

            if (segundo.energia == 0) {
                primeiro.vencer();
                segundo.perder();
                System.out.println("Vencedor: " + primeiro.nome);
                return;
            }

            dano = segundo.calcularDano(primeiro, rodada);
            primeiro.receberDano(dano);
            System.out.println(segundo.nome + " atacou " + primeiro.nome + " causando " + dano + " de dano.");

            System.out.println("Energia de " + primeiro.nome + ": " + primeiro.energia);

            if (primeiro.energia == 0) {
                segundo.vencer();
                primeiro.perder();
                System.out.println("Vencedor: " + segundo.nome);
                return;
            }
        }

        if (primeiro.energia > segundo.energia) {
            primeiro.vencer();
            segundo.perder();
            System.out.println("Vencedor: " + primeiro.nome);

        } else if (segundo.energia > primeiro.energia) {
            segundo.vencer();
            primeiro.perder();
            System.out.println("Vencedor: " + segundo.nome);

        } else {
            primeiro.empatar();
            segundo.empatar();
            System.out.println("Empate.");
        }
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArrayList<Robo> robos = new ArrayList<>();
        int opcao;

        do {
            System.out.println();
            System.out.println("===== CAMPEONATO DE ROBOLUDOS =====");
            System.out.println("1 - Cadastrar robo");
            System.out.println("2 - Consultar robo");
            System.out.println("3 - Listar robos");
            System.out.println("4 - Realizar combate");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");
            opcao = teclado.nextInt();

            switch (opcao) {
                case 1:
                    teclado.nextLine();
                    cadastrar(teclado, robos);
                    break;
                case 2:
                    consultar(teclado, robos);
                    break;
                case 3:
                    listar(robos);
                    break;
                case 4:
                    combate(teclado, robos);
                    break;
                case 0:
                    System.out.println("Sair");
                    break;
                default:
                    System.out.println("Não tem.");
            }
        } while (opcao != 0);
        teclado.close();
    }
}