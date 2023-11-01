public class Position {

    private int x_;
    private int y_;

    public Position(int x,int y){
        x_=x;
        y_=y;
    }

    public int getX(){
        return this.x_;
    }
    public void setX(int x){
        x_=x;
    }
    public void setY(int y){
        y_=y;
    }
    public int getY(){
        return this.y_;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Position p = (Position) o;
        return x_ == p.getX() && y_ == p.getY();
    }

}
