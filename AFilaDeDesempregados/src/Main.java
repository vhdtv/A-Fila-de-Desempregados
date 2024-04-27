import java.util.Scanner;

class Main {

    static class Vctm {
        int id;
        Vctm prev;
        Vctm next;

        Vctm(int id) {
            this.id = id;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n, k, m;
        Vctm lista, K, M, auxK, auxM;

        while ((n = scanner.nextInt()) != 0) {
            k = scanner.nextInt();
            m = scanner.nextInt();

            lista = preencher(n);
            K = lista;
            M = lista.prev;

            while (contar(lista) > 2) {
                K = percorrer(K, k, 0);
                M = percorrer(M, m, 1);

                if (K.next == M)
                    auxK = M.next;
                else
                    auxK = K.next;

                if (M.prev == K)
                    auxM = K.prev;
                else
                    auxM = M.prev;

                if (K == M) {
                    System.out.printf("%3d,", M.id);
                    lista = remover(lista, K);
                } else {
                    System.out.printf("%3d%3d,", K.id, M.id);
                    lista = remover(lista, M);
                    lista = remover(lista, K);
                }

                K = auxK;
                M = auxM;
            }

            if (contar(lista) == 2) {
                K = percorrer(K, k, 0);
                M = percorrer(M, m, 1);

                if (K == M)
                    System.out.printf("%3d,%3d\n", K.id, K.next.id);
                else
                    System.out.printf("%3d%3d\n", K.id, K.next.id);
            } else
                System.out.printf("%3d\n", lista.id);
        }

        scanner.close();
    }

    static Vctm preencher(int tamanho) {
        Vctm inicio = null;
        Vctm noAnterior = null;

        for (int i = 1; i <= tamanho; ++i) {
            Vctm no = new Vctm(i);

            if (inicio == null)
                inicio = no;
            else {
                noAnterior.next = no;
                no.prev = noAnterior;
            }

            noAnterior = no;
        }

        inicio.prev = noAnterior;
        noAnterior.next = inicio;

        return inicio;
    }

    static Vctm remover(Vctm lista, Vctm reg) {
        Vctm noAnterior = reg.prev;
        Vctm noProximo = reg.next;

        if (reg == lista) {
            lista = lista.next;
            noAnterior.next = lista;
            lista.prev = reg.prev;
        } else {
            noAnterior.next = noProximo;
            noProximo.prev = noAnterior;
        }

        return lista;
    }

    static int contar(Vctm lista) {
        int i = 1;
        Vctm no = lista;
        while (lista != no.next) {
            no = no.next;
            i++;
        }
        return i;
    }

    static Vctm percorrer(Vctm lista, int n, int direcao) {
        Vctm no = lista;
        if (direcao == 0)
            while (--n > 0)
                no = no.next;
        else
            while (--n > 0)
                no = no.prev;

        return no;
    }
}
