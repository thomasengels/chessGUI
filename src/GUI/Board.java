package GUI;

/**
 * Created by Thomas on 2/10/2015.
 */
public class Board {
    private Tile[][] boardlayout = new Tile[8][8];

    public void addTile(Tile tile){
        boardlayout[tile.getLocation()[0]][tile.getLocation()[1]] = tile;
    }

    public Tile getTileFromLocation(int x, int y){
        return boardlayout[x][y];
    }

    public void replacePieceFromAtoB(int[] oldlocation, int[] newlocation){
        boardlayout[newlocation[0]][newlocation[1]].setChesspiece(boardlayout[oldlocation[0]][oldlocation[1]].getChesspiece());
        boardlayout[newlocation[0]][newlocation[1]].setChesspieceImage(boardlayout[oldlocation[0]][oldlocation[1]].getChesspieceImage());
        boardlayout[oldlocation[0]][oldlocation[1]].removeChessPiece();
    }

    public void replaceTile(Tile oldTile, Tile newTile){
        for(int i = 0; i < boardlayout.length; i++){
            for(int j = 0; j < boardlayout[i].length; j++){
                if(boardlayout[i][j].getChesspieceImage() == oldTile.getChesspieceImage()&&
                        boardlayout[newTile.getLocation()[0]][newTile.getLocation()[1]].getChesspiece().contains("chessboard")){
                    boardlayout[newTile.getLocation()[0]][newTile.getLocation()[1]] = newTile;
                    if((i + 1)%2 != 0){
                        if((j + 1)%2 != 0){
                            //checkTile = new Image(getClass().getResourceAsStream("chessboardWhite.jpg"));
                            boardlayout[i][j].setChessboardTileImage("chessboardWhite");
                        }
                        else{
                            //checkTile = new Image(getClass().getResourceAsStream("chessboardGrey.jpg"));
                            boardlayout[i][j].setChessboardTileImage("chessboardGrey");
                        }
                    }
                    else{
                        if((j + 1)%2 != 0){

                            //checkTile = new Image(getClass().getResourceAsStream("chessboardGrey.jpg"));
                            boardlayout[i][j].setChessboardTileImage("chessboardGrey");
                        }
                        else{
                            //checkTile = new Image(getClass().getResourceAsStream("chessboardWhite.jpg"));
                            boardlayout[i][j].setChessboardTileImage("chessboardWhite");
                        }
                    }
                }
            }
        }
    }
}
