import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITicTacToe extends Remote {
    int addPlayer(String name) throws RemoteException;
    int endMatch (int id) throws RemoteException;
    int hasMatch (int id) throws RemoteException;
    String getOpponent(int id) throws RemoteException;
    int isMyTurn(int id) throws RemoteException;
    String getBoard(int id) throws RemoteException;
    int move (int id, int linha, int coluna) throws RemoteException;
}
