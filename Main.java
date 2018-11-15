import java.util.Scanner;

public class Main {
    public static final double LEVEL_REWARDS[] = new double[]{43.2, 4.15, 1.92, 1.45, 1.48, 1.67, 1.38, 1.75, 2.85, 4.1, 4.95, 13.85, 17.25};
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        FCTMilhoes jogo = new FCTMilhoes();
        processCommands(in, jogo);
        System.out.printf("Valor acumulado: %.2f Euros. Ate a proxima.", jogo.getPrize());
    }

    private static void printBets(FCTMilhoes jogo) {
        for (int i = 1; i < 14; i++) {
            System.out.print("Nivel: " + i + " Jogadores: " + jogo.getBetsByLevel(i));
            if (jogo.getBetsByLevel(i) != 0)
                //Mudar para dividir o reward pelo numero de winners
                System.out.printf(" Valor premio: %.2f Euros", (jogo.getPrize() * (LEVEL_REWARDS[i-1] / 100))/jogo.getBetsByLevel(i));
            System.out.println();
        }
        jogo.finish();
        System.out.printf("Valor acumulado: %.2f Euros\n", Math.abs(jogo.getPrize()));
    }

    private static void processCommands(Scanner in, FCTMilhoes jogo) {
        String command = "";
        while (!command.equals("SAI")) {
            System.out.print("> ");
            command = in.next().toUpperCase();
            switch (command) {
                case "AJUDA":
                    printHelp();
                    break;
                case "NOVO":
                    if (newGame(in, jogo))
                        command = gameCommands(in, jogo);
                    break;
                case "SAI":
                    break;
                default:
                    System.out.println("Comando inexistente.");
            }
            in.nextLine();
        }
    }

    private static boolean newGame(Scanner in, FCTMilhoes jogo) {
        double prize = in.nextDouble();
        if (prize <= 0)
            System.out.println("Valor incorrecto.");
        else {
            jogo.newGame(prize);
            System.out.printf("Jogo iniciado. Valor do premio: %.2f Euros.\n", jogo.getPrize());
            in.nextLine();
            return true;
        }
        return false;
    }

    private static String gameCommands(Scanner in, FCTMilhoes jogo) {
        String command = "";
        while (!command.equals("FIM")) {
            System.out.print("FCTMILHOES> ");
            command = in.next().toUpperCase();
            switch (command) {
                case "AJUDA":
                    printGameHelp();
                    in.nextLine();
                    break;
                case "JOGA":
                    processPlay(in, jogo);
                    in.nextLine();
                    break;
                case "FIM":
                    printBets(jogo);
                    break;
                default:
                    System.out.println("Comando inexistente.");
                    in.nextLine();
            }
        }
        return command;
    }


    private static void processPlay(Scanner in, FCTMilhoes jogo) {
        int level;
        int numbers[] = new int[7];
        for (int i = 0; i < 7; i++) {
            numbers[i] = in.nextInt();
        }
        level = jogo.bet(numbers);
        if (level == -1)
            System.out.println("Chave incorrecta.");
        else {
            System.out.print("Obrigado pela aposta.");
            if (level != 0)
                System.out.println(" Premio de nivel: " + level);
            else
                System.out.println();
        }
    }

    private static void printGameHelp() {
        System.out.println("joga - Simula uma aposta, dando uma chave");
        System.out.println("fim - Termina o jogo em curso");
        System.out.println("ajuda - Mostra os comandos existentes");
    }

    private static void printHelp() {
        System.out.println("novo - Novo jogo dando um valor inicial");
        System.out.println("sai - Termina a execucao do programa");
        System.out.println("ajuda - Mostra os comandos existentes");
    }
}
