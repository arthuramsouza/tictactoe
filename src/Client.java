import java.rmi.Naming;
import java.util.Scanner;


public class Client {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java Client <servidor> <nome_jogador>");
            System.exit(1);
        }

        try {
            ITicTacToe game = (ITicTacToe) Naming.lookup("//" + args[0] + "/TicTacToe");
            Scanner stdin = new Scanner(System.in);

            // Registra jogador no servidor remoto
            int id = game.addPlayer(args[1]);

            if (id == -1) {
                System.err.println("Nome de jogador já em uso!");
                System.exit(1);
            }

            if (id == -2) {
                System.err.println("Número máximo de jogadores do servidor excedido!");
                System.exit(1);
            }

            int hasMatch = game.hasMatch(id);

            System.out.println("Procurando partida ...");

            // Verifica se há alguma partida ativa
            while (hasMatch != 1 && hasMatch != 2) {
                if (hasMatch == -2) {
                    System.err.println("Tempo de espera esgotado!");
                    System.exit(1);
                }

                if (hasMatch == -1) {
                    System.err.println("Erro no servidor!");
                    System.exit(1);
                }

                // Verificao a cada 1 segundo
                Thread.sleep(1000);
                hasMatch = game.hasMatch(id);
            }

            System.out.println("Segundo jogador " + game.getOpponent(id) + " entrou ....");

            int isMyTurn;
            String message = null;

            // Loop de jogo
            while (true) {

                // Verifica se eh a vez deste jogador
                isMyTurn = game.isMyTurn(id);

                if (isMyTurn == -2) {
                    System.err.println("Não existem dois jogadores nesta partida!");
                    game.endMatch(id);
                    System.exit(1);
                }

                if (isMyTurn == -1) {
                    System.err.println("Erro no servidor!");
                    game.endMatch(id);
                    System.exit(1);
                }

                switch (isMyTurn) {
                    case 2:
                        message = "Você venceu!";
                        break;
                    case 3:
                        message = "Você perdeu!";
                        break;
                    case 4:
                        message = "Empate!";
                        break;
                    case 5:
                        message = "Você venceu por WO!";
                        break;
                    case 6:
                        message = "Você perdeu por WO!";
                        break;
                }

                // Final de jogo, exibe resultado e encerra
                if (isMyTurn > 1 && isMyTurn < 7) {
                    System.out.println(message);

                    if (game.endMatch(id) == -1) {
                        System.err.println("Erro ao encerrar jogo!");
                        System.exit(1);
                    } else {
                        System.out.println("Partida finalizada!");
                        System.exit(0);
                    }
                }

                // Pode apenas mover as pecas no tabuleiro
                int ret_movePeca = -1;

                while ((ret_movePeca != 1) && (ret_movePeca != -3) && (isMyTurn == 1)) {

                    System.out.println(game.getBoard(id));

                    System.out.println("Informe a posição da peça a ser movida.");
                    System.out.print("Linha: ");
                    int linha = stdin.nextInt();

                    System.out.print("Coluna: ");
                    int coluna = stdin.nextInt();

                    ret_movePeca = game.move(id, linha, coluna);

                    switch (ret_movePeca) {
                        case 2:
                            System.out.println("Você perdeu por WO!");
                            game.endMatch(id);
                            System.exit(0);
                        case 1:
                            System.out.println("Jogada concluída com sucesso");
                            System.out.println(game.getBoard(id));
                            break;
                        case 0:
                            System.out.println("Posicão invalida!");
                            break;
                        case -1:
                            System.out.println("Parâmetros inválidos!");
                            break;
                        case -2:
                            System.err.println("Não existem dois jogadores nesta partida!");
                            game.endMatch(id);
                            System.exit(1);
                        case -3:
                            System.out.println("Parâmetros inválidos!");
                            break;
                        case -4:
                            System.out.println("Não é a sua vez de move!");
                            break;
                    }

                }
            }

        } catch (Exception e) {
            System.err.println("TicTacToe client failed!");
            System.err.println(e.toString());
        }
    }
}