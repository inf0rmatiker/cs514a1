public class Main {

    public static void main(String[] args) {

        try {
            ChessBoard board = new ChessBoard();
            board.initialize();
            System.out.println(board);
            board.move("c2", "c4");
            System.out.println(board);
        } catch (IllegalMoveException e) {
            System.out.println("Caught error!");
        }

    }
}
