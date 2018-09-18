import java.rmi.Naming;
import java.rmi.RemoteException;

public class Server {

    public static void main(String[] args) {

        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            System.out.println("RMI Registry ready!");
        }
        catch(RemoteException e) {
            System.err.println("RMI registry already running!");
        }

        try {
            TicTacToe game = new TicTacToe();
            Naming.rebind("TicTacToe", game);
            System.out.println("TicTacToe server ready!");

            game.garbageCollector();
        }
        catch(Exception e) {
            System.err.println("TicTacToe server failed!");
        }
    }
}
