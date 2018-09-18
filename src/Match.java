public class Match {

    private Player player1, player2;
    private char[][] board = new char[3][3];
    private boolean ready;
    private Player currentPlayer;

    private static final char VAZIO = '.';
    private static final char PLAYER1 = 'X';
    private static final char PLAYER2 = 'O';

    public Match(Player player1) {
        this.player1 = player1;
        this.ready = false;
        this.currentPlayer = this.player1;

        // Inicia o tabuleiro

        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = VAZIO;
            }
        }
    }

    public void setPlayer1(Player p) { this.player1 = p; }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
        this.ready = true;
    }

    public boolean canDelete() { return this.player1 == null && this.player2 == null; }

    public Player getWinner() {
        if ((board[0][0] == PLAYER1 && board[0][1] == PLAYER1 && board[0][2] == PLAYER1) ||
                (board[1][0] == PLAYER1 && board[1][1] == PLAYER1 && board[1][2] == PLAYER1) ||
                (board[2][0] == PLAYER1 && board[2][1] == PLAYER1 && board[2][2] == PLAYER1) ||
                (board[0][0] == PLAYER1 && board[1][1] == PLAYER1 && board[2][2] == PLAYER1) ||
                (board[2][0] == PLAYER1 && board[1][1] == PLAYER1 && board[0][2] == PLAYER1))
            return player1;
        else if ((board[0][0] == PLAYER2 && board[0][1] == PLAYER2 && board[0][2] == PLAYER2) ||
                (board[1][0] == PLAYER2 && board[1][1] == PLAYER2 && board[1][2] == PLAYER2) ||
                (board[2][0] == PLAYER2 && board[2][1] == PLAYER2 && board[2][2] == PLAYER2) ||
                (board[0][0] == PLAYER2 && board[1][1] == PLAYER2 && board[2][2] == PLAYER2) ||
                (board[2][0] == PLAYER2 && board[1][1] == PLAYER2 && board[0][2] == PLAYER2))
            return player2;
        else
            return null;
    }

    public boolean isReady() { return this.ready; }
    public Player getPlayer1() { return this.player1; }
    public Player getPlayer2() { return this.player2; }
    public char[][] getBoard() { return this.board; }
    public Player getCurrentPlayer() { return this.currentPlayer; }

    private void changeTurn() {
        this.currentPlayer = (this.currentPlayer == this.player1 ? this.player2 : this.player1);
    }

    public int move(int linha, int coluna) {

        if ((linha < 0) || (linha > 2) || (coluna < 0) || (coluna > 2)) {
            return 0;
        }

        if (board[linha][coluna] != VAZIO) {
            return 0;
        }

        if (currentPlayer == player1)
            board[linha][coluna] = PLAYER1;
        else if (currentPlayer == player2)
            board[linha][coluna] = PLAYER2;
        else
            return 0;

        currentPlayer.updateTimestamp();
        this.changeTurn();
        return 1;
    }

    public boolean hasTimedOut() {
    return ((player1 == null && player2 == null) ||
            (player1 == null && player2.hasTimedOut()) ||
            (player1.hasTimedOut() && player2 == null) ||
            (player1.hasTimedOut() && player2.hasTimedOut()));
    }
}
