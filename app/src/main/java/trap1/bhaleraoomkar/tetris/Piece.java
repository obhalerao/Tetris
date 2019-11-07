package trap1.bhaleraoomkar.tetris;

public class Piece {

    public int color, type;

    public Tile[] tiles;

    public Piece(int type){
        this.color = MainActivity.colors.get(type);


    }

}
