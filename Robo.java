class Robo {

    int codigo;
    String nome;
    int ataque;
    int defesa;
    int energia;
    int vitorias;
    int derrotas;
    int pontos;

    Robo(int codigo, String nome, int ataque, int defesa) {
        this.codigo = codigo;
        this.nome = nome;
        this.ataque = ataque;
        this.defesa = defesa;
        this.energia = 100;
        this.vitorias = 0;
        this.derrotas = 0;
        this.pontos = 0;
    }

    void receberDano(int dano) {
        energia -= dano;
        if (energia < 0) {
            energia = 0;
        }
    }

    int calcularDano(Robo adversario, int rodada) {
        int dano = ataque - adversario.defesa;
        if (dano < 5) {
            dano = 5;
        }
        if (rodada % 2 == 0) {
            dano += 5;
        }
        return dano;
    }

    void vencer() {
        vitorias++;
        pontos += 3;
    }

    void perder() {
        derrotas++;
    }

    void empatar() {
        pontos++;
    }

    void exibirDados() {
        System.out.println("Codigo: " + codigo);
        System.out.println("Nome: " + nome);
        System.out.println("Ataque: " + ataque);
        System.out.println("Defesa: " + defesa);
        System.out.println("Energia: " + energia);
        System.out.println("Vitorias: " + vitorias);
        System.out.println("Derrotas: " + derrotas);
        System.out.println("Pontos: " + pontos);
        if (energia >= 30) {
            System.out.println("Disponivel");
        } else {
            System.out.println("Em recuperacao");
        }
        System.out.println();
    }
}