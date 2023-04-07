import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class bibliotecateste {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        int op = 0;

        do {

            System.out.println("\n╔═══════════════════════════════╗");
            System.out.println("║      Menu da Biblioteca       ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║    [1] Adicionar livro        ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║    [2] Remover livro          ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║    [3] Buscar livro           ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║    [4] Gerar relatório        ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║    [5] Sair do programa       ║");
            System.out.println("╚═══════════════════════════════╝");

            System.out.print("\nDigite sua opção: ");
            op = input.nextInt();

            if (op == 1) {
                AdicionarLivro();
            }
            else if (op == 2) {
                ExcluirLivro();
            }
            else if (op == 3) {
                BuscarLivro();
            }
            else if (op == 4) {
                GerarRelatorio();
            }
            else if (op != 5) {
                System.out.println("\n╔═══════════════════════════════════════════════╗");
                System.out.println("║        Opção inválida, tente novamente        ║");
                System.out.println("╚═══════════════════════════════════════════════╝");
            }

        } while (op != 5);



        input.close();
    }

    public static void AdicionarLivro() {
        Scanner input = new Scanner(System.in);
        int cont = 0;

        do {
            System.out.print("\nDigite o nome do livro: ");
            String nomeDoLivro = input.nextLine();

            String qtdDePaginas = "";
            while (true) {
                System.out.print("Digite o número de páginas do livro: ");
                qtdDePaginas = input.nextLine();

                if (qtdDePaginas.matches("\\d+")) {
                    break;
                } else {
                    System.out.println("\nNúmero inválido, tente novamente!\n");
                    continue;
                }
            }

            System.out.print("Digite o nome do autor: ");
            String nomeDoAutor = input.nextLine();

            System.out.print("Digite a area de interesse: ");
            String areaDeInteresse = input.nextLine();


            String salvar = nomeDoLivro + "," + qtdDePaginas + "," + nomeDoAutor + "," + areaDeInteresse;

            String filePath = "C:\\Users\\Matheus\\Documents\\biblioteca.csv";
            String dados = salvar;

            try {
                //Abrir o arquivo para salvar a string
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));

                //Escrever os dados na nova linha
                writer.write(dados);
                writer.newLine();

                //fechar arquivo
                writer.close();

                System.out.println("\nDados salvos com sucesso.");

            }
            catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║      Deseja continuar?       ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║    [Qualquer número] Sim     ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║    [2] Não                   ║");
            System.out.println("╚══════════════════════════════╝");

            System.out.print("\nDigite sua opção: ");
            cont = input.nextInt();
            input.nextLine();

        } while (cont != 2);

    }

    public static void ExcluirLivro() throws IOException {
        Scanner input = new Scanner(System.in);
        int cont = 0;

        String nomeArquivo = "C:\\Users\\Matheus\\Documents\\biblioteca.csv";

        do {

            String[] linhas = Files.readAllLines(Paths.get(nomeArquivo)).toArray(new String [0]);

            if(linhas.length >= 1) {
                System.out.println("\n╔═══════════════════════════════════════════════╗");
                System.out.println("║      Por qual dado você deseja excluir?       ║");
                System.out.println("╠═══════════════════════════════════════════════╣");
                System.out.println("║      [1] Nome Do Livro                        ║");
                System.out.println("╠═══════════════════════════════════════════════╣");
                System.out.println("║      [2] Nome Do Autor                        ║");
                System.out.println("╠═══════════════════════════════════════════════╣");
                System.out.println("║      [3] Area De Interesse                    ║");
                System.out.println("╚═══════════════════════════════════════════════╝");

                System.out.print("\nDigite sua opção: ");
                int op = input.nextInt();
                input.nextLine();

                if (op == 1) {
                    while(true) {
                        boolean nomeExiste = false;

                        System.out.print("\nDigite o nome do(s) livro(s) que deseja excluir: ");
                        String nomeLivro = input.nextLine();

                        for (int i = 0; i < linhas.length; i ++) {
                            String[] campos = linhas[i].split(",");
                            if (campos[0].equals(nomeLivro)) {
                                nomeExiste = true;
                            }
                        }

                        if (nomeExiste) {
                            BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo));

                            for (int i = 0; i < linhas.length; i ++) {
                                String[] campos = linhas[i].split(",");
                                if (!campos[0].equals(nomeLivro)) {
                                    writer.write(linhas[i]);
                                    writer.newLine();
                                }
                            }


                            writer.close();
                            break;
                        }
                        else {
                            System.out.println("\nNão existe nenhum livro com este nome, tente novamente.");
                            continue;
                        }
                    }
                }

                else if (op == 2) {
                    while(true) {
                        boolean nomeExiste = false;

                        System.out.print("\nDigite o nome do autor do(s) livro(s) que deseja excluir: ");
                        String nomeAutor = input.nextLine();

                        for (int i = 0; i < linhas.length; i ++) {
                            String[] campos = linhas[i].split(",");
                            if (campos[2].equals(nomeAutor)) {
                                nomeExiste = true;
                            }
                        }

                        if (nomeExiste) {
                            BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo));

                            for (int i = 0; i < linhas.length; i ++) {
                                String[] campos = linhas[i].split(",");
                                if (!campos[2].equals(nomeAutor)) {
                                    writer.write(linhas[i]);
                                    writer.newLine();
                                }
                            }

                            writer.close();
                            break;
                        }
                        else {
                            System.out.println("\nNão existe nenhum autor com este nome, tente novamente.");
                            continue;
                        }
                    }

                }

                else if (op == 3) {
                    while(true) {
                        boolean nomeExiste = false;

                        System.out.print("\nDigite o nome do autor do(s) livro(s) que deseja excluir: ");
                        String areaInteresse = input.nextLine();

                        for (int i = 0; i < linhas.length; i ++) {
                            String[] campos = linhas[i].split(",");
                            if (campos[3].equals(areaInteresse)) {
                                nomeExiste = true;
                            }
                        }

                        if (nomeExiste) {
                            BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo));

                            for (int i = 0; i < linhas.length; i ++) {
                                String[] campos = linhas[i].split(",");
                                if (!campos[3].equals(areaInteresse)) {
                                    writer.write(linhas[i]);
                                    writer.newLine();
                                }
                            }

                            writer.close();
                            break;
                        }
                        else {
                            System.out.println("\nNão existe nenhuma area de interesse com este nome, tente novamente.");
                            continue;
                        }
                    }

                }

                System.out.println("\n╔══════════════════════════════╗");
                System.out.println("║      Deseja continuar?       ║");
                System.out.println("╠══════════════════════════════╣");
                System.out.println("║    [Qualquer número] Sim     ║");
                System.out.println("╠══════════════════════════════╣");
                System.out.println("║    [2] Não                   ║");
                System.out.println("╚══════════════════════════════╝");

                System.out.print("\nDigite sua opção: ");
                cont = input.nextInt();

            }
            else {
                System.out.println("\nNenhum livro cadastrado.");
                break;
            }

        } while (cont != 2);
    }

    public static void BuscarLivro() throws IOException {
        Scanner input = new Scanner(System.in);
        int cont = 0;

        String nomeArquivo = "C:\\Users\\Matheus\\Documents\\biblioteca.csv";

        do {

            String[] linhas = Files.readAllLines(Paths.get(nomeArquivo)).toArray(new String [0]);

            if (linhas.length >= 1) {
                System.out.println("\n╔═══════════════════════════════════════════════╗");
                System.out.println("║      Por qual dado você deseja buscar?        ║");
                System.out.println("╠═══════════════════════════════════════════════╣");
                System.out.println("║      [1] Nome Do Livro                        ║");
                System.out.println("╠═══════════════════════════════════════════════╣");
                System.out.println("║      [2] Número De Páginas                    ║");
                System.out.println("╠═══════════════════════════════════════════════╣");
                System.out.println("║      [3] Nome Do Autor                        ║");
                System.out.println("╠═══════════════════════════════════════════════╣");
                System.out.println("║      [4] Area De Interesse                    ║");
                System.out.println("╚═══════════════════════════════════════════════╝");

                System.out.print("\nDigite sua opção: ");
                int op = input.nextInt();
                input.nextLine();

                if (op == 1) {
                    while(true) {
                        boolean nomeExiste = false;

                        System.out.print("\nDigite o nome do(s) livro(s) que deseja buscar: ");
                        String nomeLivro = input.nextLine();

                        for (int i = 0; i < linhas.length; i ++) {
                            String[] campos = linhas[i].split(",");
                            if (campos[0].equals(nomeLivro)) {
                                nomeExiste = true;
                            }
                        }

                        if (nomeExiste) {

                            System.out.println("\n╔══════════════════════════════════════════════════════════════════════════════════════════════╗");
                            System.out.println("║                     LIVROS ENCONTRADOS COM ESTES NÚMERO DE PÁGINA                            ║    ");
                            System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════════╝");

                            for (int i = 0; i < linhas.length; i ++) {
                                String[] campos = linhas[i].split(",");

                                if (campos[0].equals(nomeLivro)) {
                                    System.out.println("╔══════════════════════════════════════════════════════════════════════════════════════════════╗");
                                    System.out.println("║ 👉🏻 Nome: " + campos[0] + ", Nº págs: " + campos[1] + ", Ator: " + campos[2] + ", Area de interesse: " + campos[3]);
                                    System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════════╝");
                                }
                            }

                            break;
                        }
                        else {
                            System.out.println("\nNão existe nenhum livro com este nome, tente novamente.");
                            break;
                        }
                    }
                }
                else if (op == 2) {
                    while(true) {
                        boolean nomeExiste = false;

                        System.out.print("\nDigite o número de páginas do(s) livro(s) que deseja buscar: ");
                        String numeroPaginas = "";
                        while (true) {
                            System.out.print("Digite o número de páginas do livro: ");
                            numeroPaginas = input.nextLine();

                            if (numeroPaginas.matches("\\d+")) {
                                break;
                            } else {
                                System.out.println("\nNúmero inválido, tente novamente!\n");
                                continue;
                            }
                        }

                        for (int i = 0; i < linhas.length; i ++) {
                            String[] campos = linhas[i].split(",");
                            if (campos[1].equals(numeroPaginas)) {
                                nomeExiste = true;
                            }
                        }

                        if (nomeExiste) {

                            System.out.println("\n╔══════════════════════════════════════════════════════════════════════════════════════════════╗");
                            System.out.println("║                     LIVROS ENCONTRADOS COM ESTES NÚMERO DE PÁGINA                            ║    ");
                            System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════════╝");

                            for (int i = 0; i < linhas.length; i ++) {
                                String[] campos = linhas[i].split(",");

                                if (campos[1].equals(numeroPaginas)) {
                                    System.out.println("╔══════════════════════════════════════════════════════════════════════════════════════════════╗");
                                    System.out.println("║ 👉🏻 Nome: " + campos[0] + ", Nº págs: " + campos[1] + ", Ator: " + campos[2] + ", Area de interesse: " + campos[3]);
                                    System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════════╝");
                                }
                            }

                            break;
                        }
                        else {
                            System.out.println("\nNão existe nenhum livro com este número de páginas, tente novamente.");
                            break;
                        }
                    }
                }

                else if (op == 3) {
                    while(true) {
                        boolean nomeExiste = false;

                        System.out.print("\nDigite o nome do autor do(s) livro(s) que deseja buscar: ");
                        String nomeAutor = input.nextLine();

                        for (int i = 0; i < linhas.length; i ++) {
                            String[] campos = linhas[i].split(",");
                            if (campos[2].equals(nomeAutor)) {
                                nomeExiste = true;
                            }
                        }

                        if (nomeExiste) {

                            System.out.println("\n╔══════════════════════════════════════════════════════════════════════════════════════════════╗");
                            System.out.println("║                       LIVROS ENCONTRADOS COM ESTES NOME DE AUTOR                             ║    ");
                            System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════════╝");

                            for (int i = 0; i < linhas.length; i ++) {
                                String[] campos = linhas[i].split(",");

                                if (campos[2].equals(nomeAutor)) {
                                    System.out.println("╔══════════════════════════════════════════════════════════════════════════════════════════════╗");
                                    System.out.println("║ 👉🏻 Nome: " + campos[0] + ", Nº págs: " + campos[1] + ", Ator: " + campos[2] + ", Area de interesse: " + campos[3]);
                                    System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════════╝");
                                }
                            }

                            break;
                        }
                        else {
                            System.out.println("\nNão existe nenhum livro com este nome de autor, tente novamente.");
                            break;
                        }
                    }
                }
                else if (op == 4) {
                    while(true) {
                        boolean nomeExiste = false;

                        System.out.print("\nDigite a area de interesse do(s) livro(s) que deseja buscar: ");
                        String areaInteresse = input.nextLine();

                        for (int i = 0; i < linhas.length; i ++) {
                            String[] campos = linhas[i].split(",");
                            if (campos[3].equals(areaInteresse)) {
                                nomeExiste = true;
                            }
                        }

                        if (nomeExiste) {

                            System.out.println("\n╔══════════════════════════════════════════════════════════════════════════════════════════════╗");
                            System.out.println("║                      LIVROS ENCONTRADOS COM ESTA AREA DE INTERESSE                           ║    ");
                            System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════════╝");

                            for (int i = 0; i < linhas.length; i ++) {
                                String[] campos = linhas[i].split(",");

                                if (campos[3].equals(areaInteresse)) {
                                    System.out.println("╔══════════════════════════════════════════════════════════════════════════════════════════════╗");
                                    System.out.println("║ 👉🏻 Nome: " + campos[0] + ", Nº págs: " + campos[1] + ", Ator: " + campos[2] + ", Area de interesse: " + campos[3]);
                                    System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════════╝");
                                }
                            }

                            break;
                        }
                        else {
                            System.out.println("\nNão existe nenhum livro com esta area de interesse, tente novamente.");
                            break;
                        }
                    }
                }

                System.out.println("\n╔══════════════════════════════╗");
                System.out.println("║      Deseja continuar?       ║");
                System.out.println("╠══════════════════════════════╣");
                System.out.println("║    [Qualquer número] Sim     ║");
                System.out.println("╠══════════════════════════════╣");
                System.out.println("║    [2] Não                   ║");
                System.out.println("╚══════════════════════════════╝");

                System.out.print("\nDigite sua opção: ");
                cont = input.nextInt();

            }

            else {
                System.out.println("\nNenhum livro cadastrado.");
                break;
            }

        } while (cont !=2);
    }

    public static void GerarRelatorio() throws IOException {
        Scanner input = new Scanner(System.in);
        int cont = 0;
        int R = 0;
        int RD = 0;

        String nomeArquivoExistente = "C:\\Users\\Matheus\\Documents\\biblioteca.csv";
        String[] dados = Files.readAllLines(Paths.get(nomeArquivoExistente)).toArray(new String [0]);

        if (dados.length >= 1) {
            do {
                System.out.println("\n╔═══════════════════════════════════════════════╗");
                System.out.println("║      Você deseja gerar um relatório de :      ║");
                System.out.println("╠═══════════════════════════════════════════════╣");
                System.out.println("║      [1] Todos os livros                      ║");
                System.out.println("╠═══════════════════════════════════════════════╣");
                System.out.println("║      [2] Livros Específicos                   ║");
                System.out.println("╚═══════════════════════════════════════════════╝");

                System.out.print("\nDigite sua opção: ");
                int op = input.nextInt();
                input.nextLine();

                if (op == 1) {
                    try {
                        String nomeDoArquivoNovo = "C:\\Users\\julio\\OneDrive\\Área de Trabalho\\relatorio("+R+").csv";
                        R++;
                        FileWriter arquivo = new FileWriter(nomeDoArquivoNovo);
                        PrintWriter gravador = new PrintWriter(arquivo);

                        for (String linha : dados) {
                            gravador.println(linha);
                        }

                        gravador.close();
                        arquivo.close();

                        System.out.println("\n╔═══════════════════════════════════════════════════════════════╗");
                        System.out.println("║                  Arquivo gerado com sucesso!                  ║");
                        System.out.println("╠═══════════════════════════════════════════════════════════════╣");
                        System.out.println("║ 👉🏻 "+ nomeDoArquivoNovo +"  ║");
                        System.out.println("╚═══════════════════════════════════════════════════════════════╝");

                    } catch (IOException e) {
                        System.out.println("Erro ao gerar arquivo CSV: " + e.getMessage());
                    }
                }
                else if (op == 2) {
                    System.out.println("\n╔═══════════════════════════════════════════════╗");
                    System.out.println("║      Por qual dado você deseja gerar?         ║");
                    System.out.println("╠═══════════════════════════════════════════════╣");
                    System.out.println("║      [1] Nome Do Livro                        ║");
                    System.out.println("╠═══════════════════════════════════════════════╣");
                    System.out.println("║      [2] Número De Páginas                    ║");
                    System.out.println("╠═══════════════════════════════════════════════╣");
                    System.out.println("║      [3] Nome Do Autor                        ║");
                    System.out.println("╠═══════════════════════════════════════════════╣");
                    System.out.println("║      [4] Area De Interesse                    ║");
                    System.out.println("╚═══════════════════════════════════════════════╝");

                    System.out.print("\nDigite sua opção: ");
                    op = input.nextInt();
                    input.nextLine();

                    if (op == 1) {
                        while(true) {
                            boolean nomeExiste = false;

                            System.out.print("\nDigite o nome do(s) livro(s) que deseja buscar: ");
                            String nomeLivro = input.nextLine();

                            for (int i = 0; i < dados.length; i ++) {
                                String[] campos = dados[i].split(",");
                                if (campos[0].equals(nomeLivro)) {
                                    nomeExiste = true;
                                }
                            }

                            if (nomeExiste) {
                                String nomeDoArquivoNovo = "C:\\Users\\julio\\OneDrive\\Área de Trabalho\\relatorioDetalhado("+RD+").csv";
                                RD++;

                                try {
                                    FileWriter fw = new FileWriter(nomeDoArquivoNovo, true);
                                    BufferedWriter bw = new BufferedWriter(fw);

                                    for (int i = 0; i < dados.length; i ++) {
                                        String[] campos = dados[i].split(",");
                                        if (campos[0].equals(nomeLivro)) {
                                            bw.write(dados[i]);
                                            bw.newLine();
                                        }
                                    }

                                    bw.close();
                                    fw.close();

                                    System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
                                    System.out.println("║                      Arquivo gerado com sucesso!                       ║");
                                    System.out.println("╠════════════════════════════════════════════════════════════════════════╣");
                                    System.out.println("║ 👉🏻 "+ nomeDoArquivoNovo +"  ║");
                                    System.out.println("╚════════════════════════════════════════════════════════════════════════╝");

                                }
                                catch (Exception e) {
                                    System.out.println("Erro ao adicionar dados filtrados ao arquivo " + nomeDoArquivoNovo + ".");
                                    e.printStackTrace();
                                }

                                break;
                            }
                            else {
                                System.out.println("\nNão existe nenhum livro com este nome, tente novamente.");
                                continue;
                            }
                        }
                    }
                    else if (op == 2) {
                        while(true) {
                            boolean nomeExiste = false;

                            System.out.print("\nDigite o número de páginas do(s) livro(s) que deseja buscar: ");
                            String numeroPaginas = "";
                            while (true) {
                                System.out.print("Digite o número de páginas do livro: ");
                                numeroPaginas = input.nextLine();

                                if (numeroPaginas.matches("\\d+")) {
                                    break;
                                } else {
                                    System.out.println("\nNúmero inválido, tente novamente!\n");
                                    continue;
                                }
                            }

                            for (int i = 0; i < dados.length; i ++) {
                                String[] campos = dados[i].split(",");
                                if (campos[1].equals(numeroPaginas)) {
                                    nomeExiste = true;
                                }
                            }

                            if (nomeExiste) {

                                String nomeDoArquivoNovo = "C:\\Users\\julio\\OneDrive\\Área de Trabalho\\relatorioDetalhado("+RD+").csv";
                                RD++;

                                try {
                                    FileWriter fw = new FileWriter(nomeDoArquivoNovo, true);
                                    BufferedWriter bw = new BufferedWriter(fw);

                                    for (int i = 0; i < dados.length; i ++) {
                                        String[] campos = dados[i].split(",");
                                        if (campos[1].equals(numeroPaginas)) {
                                            bw.write(dados[i]);
                                            bw.newLine();
                                        }
                                    }

                                    bw.close();
                                    fw.close();

                                    System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
                                    System.out.println("║                      Arquivo gerado com sucesso!                       ║");
                                    System.out.println("╠════════════════════════════════════════════════════════════════════════╣");
                                    System.out.println("║ 👉🏻 "+ nomeDoArquivoNovo +"  ║");
                                    System.out.println("╚════════════════════════════════════════════════════════════════════════╝");

                                }
                                catch (Exception e) {
                                    System.out.println("Erro ao adicionar dados filtrados ao arquivo " + nomeDoArquivoNovo + ".");
                                    e.printStackTrace();
                                }

                                break;
                            }
                            else {
                                System.out.println("\nNão existe nenhum livro com este número de páginas, tente novamente.");
                                continue;
                            }
                        }
                    }

                    else if (op == 3) {
                        while(true) {
                            boolean nomeExiste = false;

                            System.out.print("\nDigite o nome do autor do(s) livro(s) que deseja buscar: ");
                            String nomeAutor = input.nextLine();

                            for (int i = 0; i < dados.length; i ++) {
                                String[] campos = dados[i].split(",");
                                if (campos[2].equals(nomeAutor)) {
                                    nomeExiste = true;
                                }
                            }

                            if (nomeExiste) {

                                String nomeDoArquivoNovo = "C:\\Users\\julio\\OneDrive\\Área de Trabalho\\relatorioDetalhado("+RD+").csv";
                                RD++;

                                try {
                                    FileWriter fw = new FileWriter(nomeDoArquivoNovo, true);
                                    BufferedWriter bw = new BufferedWriter(fw);

                                    for (int i = 0; i < dados.length; i ++) {
                                        String[] campos = dados[i].split(",");
                                        if (campos[2].equals(nomeAutor)) {
                                            bw.write(dados[i]);
                                            bw.newLine();
                                        }
                                    }

                                    bw.close();
                                    fw.close();

                                    System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
                                    System.out.println("║                      Arquivo gerado com sucesso!                       ║");
                                    System.out.println("╠════════════════════════════════════════════════════════════════════════╣");
                                    System.out.println("║ 👉🏻 "+ nomeDoArquivoNovo +"  ║");
                                    System.out.println("╚════════════════════════════════════════════════════════════════════════╝");

                                }
                                catch (Exception e) {
                                    System.out.println("Erro ao adicionar dados filtrados ao arquivo " + nomeDoArquivoNovo + ".");
                                    e.printStackTrace();
                                }

                                break;
                            }
                            else {
                                System.out.println("\nNão existe nenhum livro com este nome de autor, tente novamente.");
                                continue;
                            }
                        }
                    }
                    else if (op == 4) {
                        while(true) {
                            boolean nomeExiste = false;

                            System.out.print("\nDigite a area de interesse do(s) livro(s) que deseja buscar: ");
                            String areaInteresse = input.nextLine();

                            for (int i = 0; i < dados.length; i ++) {
                                String[] campos = dados[i].split(",");
                                if (campos[3].equals(areaInteresse)) {
                                    nomeExiste = true;
                                }
                            }

                            if (nomeExiste) {

                                String nomeDoArquivoNovo = "C:\\Users\\julio\\OneDrive\\Área de Trabalho\\relatorioDetalhado("+RD+").csv";
                                RD++;

                                try {
                                    FileWriter fw = new FileWriter(nomeDoArquivoNovo, true);
                                    BufferedWriter bw = new BufferedWriter(fw);

                                    for (int i = 0; i < dados.length; i ++) {
                                        String[] campos = dados[i].split(",");
                                        if (campos[3].equals(areaInteresse)) {
                                            bw.write(dados[i]);
                                            bw.newLine();
                                        }
                                    }

                                    bw.close();
                                    fw.close();

                                    System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
                                    System.out.println("║                      Arquivo gerado com sucesso!                       ║");
                                    System.out.println("╠════════════════════════════════════════════════════════════════════════╣");
                                    System.out.println("║ 👉🏻 "+ nomeDoArquivoNovo +"  ║");
                                    System.out.println("╚════════════════════════════════════════════════════════════════════════╝");

                                }
                                catch (Exception e) {
                                    System.out.println("Erro ao adicionar dados filtrados ao arquivo " + nomeDoArquivoNovo + ".");
                                    e.printStackTrace();
                                }

                                break;
                            }
                            else {
                                System.out.println("\nNão existe nenhum livro com esta area de interesse, tente novamente.");
                                continue;
                            }
                        }
                    }
                }

                System.out.println("\n╔══════════════════════════════╗");
                System.out.println("║      Deseja continuar?       ║");
                System.out.println("╠══════════════════════════════╣");
                System.out.println("║    [Qualquer número] Sim     ║");
                System.out.println("╠══════════════════════════════╣");
                System.out.println("║    [2] Não                   ║");
                System.out.println("╚══════════════════════════════╝");

                System.out.print("\nDigite sua opção: ");
                cont = input.nextInt();

            } while (cont != 2);
        }
        else {
            System.out.println("\nNenhum livro cadastrado.");
        }
    }
}