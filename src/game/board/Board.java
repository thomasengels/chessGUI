package game.board;

/**
 * Created by Glenn on 1-10-2015.
 */
public class Board {
    private Tile[][] tiles;
    private String[] columns;

    public Board() {
        tiles = new Tile[8][8];
        columns = new String[] {"A", "B", "C", "D", "E", "F", "G", "H"};
        for(int i = 0; i < 8 ; i++) {
            for(int j=0; j < 8; j++) {
                tiles[7-i][j] = new Tile(columns[j]+  (i + 1));
            }
        }
    }

    public Tile getTile(String position) {
        Tile result = null;
        for(Tile[] items : tiles) {
            for(Tile tile : items) {
                if(tile.toString().trim().equals(position)) {
                    result = tile;
                }
            }
        }
        return result;
    }

    public Tile getTile(int x, int y) {
        return tiles[y][x];
    }



    public Tile[] getTiles(String[] positions) {
        Tile[] result = new Tile[positions.length];
        int counter = 0;
        for(String string : positions) {
            for(int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (tiles[i][j].toString().equals(string)) {
                        result[counter] = tiles[i][j];
                        counter++;
                    }
                }
            }
        }
        return result;
    }

    public String[] getColumns() {
        return columns;
    }

    @Override
    public String toString() {
        String result = "";
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                result += tiles[i][j];
            }
            result += "\n";
        }
        return result;
    }
}
