package ap.mini_project.server.controller.game;

import ap.mini_project.shared.model.Board;
import ap.mini_project.shared.model.Ship;
import ap.mini_project.shared.model.ShipType;

import java.util.Random;

public class BoardBuilder {
    private static Board board = null;

    public static Board Board_1() {
        board = new Board(10,10);
//BattleShips
//        LinkedList<Cell> cells1 = new LinkedList<>();
//        for (int i = 2; i < ShipType.BATTLESHIP.getLength() + 2; i++)
//            cells1.add(board.getCells()[9][i]);
        board.addShip(new Ship(true, ShipType.BATTLESHIP, 9,2));
//CRUISER
//        LinkedList<Cell> cells2 = new LinkedList<>();
//        for (int i = 2; i < ShipType.CRUISER.getLength() + 2; i++)
//            cells2.add(board.getCells()[i][9]);
        board.addShip(new Ship(false, ShipType.CRUISER,2,9 ));

//        LinkedList<Cell> cells3 = new LinkedList<>();
//        for (int i = 6; i < ShipType.CRUISER.getLength() + 6; i++)
//            cells3.add(board.getCells()[i][0]);
        board.addShip(new Ship(false, ShipType.CRUISER, 6,0));
//Destroyer
//        LinkedList<Cell> cells4 = new LinkedList<>();
//        for (int i = 0; i < ShipType.DESTROYER.getLength(); i++)
//            cells4.add(board.getCells()[4][i]);
        board.addShip(new Ship(true, ShipType.DESTROYER, 4,0));

//        LinkedList<Cell> cells5 = new LinkedList<>();
//        for (int i = 2; i < ShipType.DESTROYER.getLength() + 2; i++)
//            cells5.add(board.getCells()[i][5]);
        board.addShip(new Ship(false, ShipType.DESTROYER, 2,5));

//        LinkedList<Cell> cells6 = new LinkedList<>();
//        for (int i = 5; i < ShipType.DESTROYER.getLength() + 5; i++)
//            cells6.add(board.getCells()[i][6]);
        board.addShip(new Ship(false, ShipType.DESTROYER,5,6));
//Frigate

//        LinkedList<Cell> cells7 = new LinkedList<>();
//        cells7.add(board.getCells()[1][7]);
        board.addShip(new Ship(false, ShipType.FRIGATE, 1,7));

//        LinkedList<Cell> cells8 = new LinkedList<>();
//        cells8.add(board.getCells()[6][2]);
        board.addShip(new Ship(false, ShipType.FRIGATE, 6,2));

//        LinkedList<Cell> cells9 = new LinkedList<>();
//        cells9.add(board.getCells()[9][8]);
        board.addShip(new Ship(false, ShipType.FRIGATE,9,8));

//        LinkedList<Cell> cells10 = new LinkedList<>();
//        cells10.add(board.getCells()[4][3]);
        board.addShip(new Ship(false, ShipType.FRIGATE, 4,3));

        return board;
    }

    public static Board Board_2() {
        board = new Board(10,10);

//BattleShips
//        LinkedList<Cell> cells1 = new LinkedList<>();
//        for (int i = 5; i < ShipType.BATTLESHIP.getLength() + 5; i++)
//            cells1.add(board.getCells()[3][i]);
        board.addShip(new Ship(true, ShipType.BATTLESHIP, 3,5));
//CRUISER
//        LinkedList<Cell> cells2 = new LinkedList<>();
//        for (int i = 0; i < ShipType.CRUISER.getLength(); i++)
//            cells2.add(board.getCells()[0][i]);
        board.addShip(new Ship(true, ShipType.CRUISER, 0,0));

//        LinkedList<Cell> cells3 = new LinkedList<>();
//        for (int i = 2; i < ShipType.CRUISER.getLength() + 2; i++)
//            cells3.add(board.getCells()[7][i]);
        board.addShip(new Ship(true, ShipType.CRUISER,7,2));
//Destroyer
//        LinkedList<Cell> cells4 = new LinkedList<>();
//        for (int i = 3; i < ShipType.DESTROYER.getLength() + 3; i++)
//            cells4.add(board.getCells()[i][1]);
        board.addShip(new Ship(false, ShipType.DESTROYER, 3,1));

//        LinkedList<Cell> cells5 = new LinkedList<>();
//        for (int i = 8; i < ShipType.DESTROYER.getLength() + 8; i++)
//            cells5.add(board.getCells()[6][i]);
        board.addShip(new Ship(true, ShipType.DESTROYER,6,8));

//        LinkedList<Cell> cells6 = new LinkedList<>();
//        for (int i = 8; i < ShipType.DESTROYER.getLength() + 8; i++)
//            cells6.add(board.getCells()[i][8]);
        board.addShip(new Ship(false, ShipType.DESTROYER, 8,8));
//Frigate

//        LinkedList<Cell> cells7 = new LinkedList<>();
//        cells7.add(board.getCells()[1][5]);
        board.addShip(new Ship(false, ShipType.FRIGATE, 1,5));

//        LinkedList<Cell> cells8 = new LinkedList<>();
//        cells8.add(board.getCells()[0][9]);
        board.addShip(new Ship(false, ShipType.FRIGATE, 0,9));

//        LinkedList<Cell> cells9 = new LinkedList<>();
//        cells9.add(board.getCells()[5][3]);
        board.addShip(new Ship(false, ShipType.FRIGATE, 5,3));

//        LinkedList<Cell> cells10 = new LinkedList<>();
//        cells10.add(board.getCells()[7][6]);
        board.addShip(new Ship(false, ShipType.FRIGATE, 7,6));

        return board;
    }

    public static Board Board_3() {
        board = new Board(10,10);

//BattleShips
//        LinkedList<Cell> cells1 = new LinkedList<>();
//        for (int i = 0; i < ShipType.BATTLESHIP.getLength(); i++)
//            cells1.add(board.getCells()[i][3]);
        board.addShip(new Ship(false, ShipType.BATTLESHIP, 0,3));
//CRUISER
//        LinkedList<Cell> cells2 = new LinkedList<>();
//        for (int i = 0; i < ShipType.CRUISER.getLength(); i++)
//            cells2.add(board.getCells()[i][0]);
        board.addShip(new Ship(false, ShipType.CRUISER, 0,0));

//        LinkedList<Cell> cells3 = new LinkedList<>();
//        for (int i = 5; i < ShipType.CRUISER.getLength() + 5; i++)
//            cells3.add(board.getCells()[i][9]);
        board.addShip(new Ship(false, ShipType.CRUISER,5,9));
//Destroyer
//        LinkedList<Cell> cells4 = new LinkedList<>();
//        for (int i = 6; i < ShipType.DESTROYER.getLength() + 6; i++)
//            cells4.add(board.getCells()[i][4]);
        board.addShip(new Ship(false, ShipType.DESTROYER, 6,4));

//        LinkedList<Cell> cells5 = new LinkedList<>();
//        for (int i = 6; i < ShipType.DESTROYER.getLength() + 6; i++)
//            cells5.add(board.getCells()[5][i]);
        board.addShip(new Ship(true, ShipType.DESTROYER, 5,6));

//        LinkedList<Cell> cells6 = new LinkedList<>();
//        for (int i = 2; i < ShipType.DESTROYER.getLength() + 2; i++)
//            cells6.add(board.getCells()[i][8]);
        board.addShip(new Ship(false, ShipType.DESTROYER, 2,8));
//Frigate

//        LinkedList<Cell> cells7 = new LinkedList<>();
//        cells7.add(board.getCells()[4][0]);
        board.addShip(new Ship(false, ShipType.FRIGATE, 4,0));

//        LinkedList<Cell> cells8 = new LinkedList<>();
//        cells8.add(board.getCells()[8][0]);
        board.addShip(new Ship(false, ShipType.FRIGATE,8,0));

//        LinkedList<Cell> cells9 = new LinkedList<>();
//        cells9.add(board.getCells()[1][6]);
        board.addShip(new Ship(false, ShipType.FRIGATE, 1,6));

//        LinkedList<Cell> cells10 = new LinkedList<>();
//        cells10.add(board.getCells()[7][6]);
        board.addShip(new Ship(false, ShipType.FRIGATE, 7,6));

        return board;
    }

    public static Board Board_4() {
        board = new Board(10,10);

//BattleShips
//        LinkedList<Cell> cells1 = new LinkedList<>();
//        for (int i = 0; i < ShipType.BATTLESHIP.getLength(); i++)
//            cells1.add(board.getCells()[i][7]);
        board.addShip(new Ship(false, ShipType.BATTLESHIP,0,7));
//CRUISER
//        LinkedList<Cell> cells2 = new LinkedList<>();
//        for (int i = 3; i < ShipType.CRUISER.getLength() + 3; i++)
//            cells2.add(board.getCells()[i][5]);
        board.addShip(new Ship(false, ShipType.CRUISER,3,5));

//        LinkedList<Cell> cells3 = new LinkedList<>();
//        for (int i = 7; i < ShipType.CRUISER.getLength() + 7; i++)
//            cells3.add(board.getCells()[6][i]);
        board.addShip(new Ship(true, ShipType.CRUISER, 6,7));
//Destroyer
//        LinkedList<Cell> cells4 = new LinkedList<>();
//        for (int i = 4; i < ShipType.DESTROYER.getLength() + 4; i++)
//            cells4.add(board.getCells()[9][i]);
        board.addShip(new Ship(true, ShipType.DESTROYER,1,2));

//        LinkedList<Cell> cells5 = new LinkedList<>();
//        for (int i = 6; i < ShipType.DESTROYER.getLength() + 6; i++)
//            cells5.add(board.getCells()[8][i]);
        board.addShip(new Ship(true, ShipType.DESTROYER,8,6));

//        LinkedList<Cell> cells6 = new LinkedList<>();
//        for (int i = 2; i < ShipType.DESTROYER.getLength() + 2; i++)
//            cells6.add(board.getCells()[9][i]);
        board.addShip(new Ship(true, ShipType.DESTROYER,9,2));
//Frigate

//        LinkedList<Cell> cells7 = new LinkedList<>();
//        cells7.add(board.getCells()[6][1]);
        board.addShip(new Ship(false, ShipType.FRIGATE,6,1));

//        LinkedList<Cell> cells8 = new LinkedList<>();
//        cells8.add(board.getCells()[9][4]);
        board.addShip(new Ship(false, ShipType.FRIGATE, 9,0));

//        LinkedList<Cell> cells9 = new LinkedList<>();
//        cells9.add(board.getCells()[9][9]);
        board.addShip(new Ship(false, ShipType.FRIGATE,9,9));

//        LinkedList<Cell> cells10 = new LinkedList<>();
//        cells10.add(board.getCells()[3][2]);
        board.addShip(new Ship(false, ShipType.FRIGATE,3,2));

        return board;
    }

    public static Board Board_5() {
        board = new Board(10,10);

//BattleShips
//        LinkedList<Cell> cells1 = new LinkedList<>();
//        for (int i = 1; i < ShipType.BATTLESHIP.getLength() + 1; i++)
//            cells1.add(board.getCells()[i][6]);
        board.addShip(new Ship(false, ShipType.BATTLESHIP,1,6 ));
//CRUISER
//        LinkedList<Cell> cells2 = new LinkedList<>();
//        for (int i = 6; i < ShipType.CRUISER.getLength() + 6; i++)
//            cells2.add(board.getCells()[9][i]);
        board.addShip(new Ship(true, ShipType.CRUISER,9,6));

//        LinkedList<Cell> cells3 = new LinkedList<>();
//        for (int i = 5; i < ShipType.CRUISER.getLength() + 5; i++)
//            cells3.add(board.getCells()[i][9]);
        board.addShip(new Ship(false, ShipType.CRUISER,5,9));
//Destroyer
//        LinkedList<Cell> cells4 = new LinkedList<>();
//        for (int i = 0; i < ShipType.DESTROYER.getLength(); i++)
//            cells4.add(board.getCells()[3][i]);
        board.addShip(new Ship(false, ShipType.DESTROYER,3,0));

//        LinkedList<Cell> cells5 = new LinkedList<>();
//        for (int i = 2; i < ShipType.DESTROYER.getLength() + 2; i++)
//            cells5.add(board.getCells()[i][9]);
        board.addShip(new Ship(false, ShipType.DESTROYER,2,9));

//        LinkedList<Cell> cells6 = new LinkedList<>();
//        for (int i = 5; i < ShipType.DESTROYER.getLength() + 5; i++)
//            cells6.add(board.getCells()[7][i]);
        board.addShip(new Ship(true, ShipType.DESTROYER, 7,5));
//Frigate

//        LinkedList<Cell> cells7 = new LinkedList<>();
//        cells7.add(board.getCells()[7][1]);
        board.addShip(new Ship(false, ShipType.FRIGATE,7,1));

//        LinkedList<Cell> cells8 = new LinkedList<>();
//        cells8.add(board.getCells()[0][5]);
        board.addShip(new Ship(false, ShipType.FRIGATE, 0,4));

//        LinkedList<Cell> cells9 = new LinkedList<>();
//        cells9.add(board.getCells()[5][3]);
        board.addShip(new Ship(false, ShipType.FRIGATE, 5,3));

//        LinkedList<Cell> cells10 = new LinkedList<>();
//        cells10.add(board.getCells()[0][9]);
        board.addShip(new Ship(false, ShipType.FRIGATE,0,9));

        return board;
    }

    public synchronized static Board getRandomBoard() {
        Random random = new Random();
        int temp = random.nextInt(5);
        System.out.println("temp = "+temp);
        return switch (temp) {
            case 0 -> Board_1();
            case 1 -> Board_2();
            case 2 -> Board_3();
            case 3 -> Board_4();
            case 4 -> Board_5();
            default -> null;
        };

    }
}

