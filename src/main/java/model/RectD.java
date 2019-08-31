package model;

public class RectD {

    public PointD pos;
    public double height;
    public double width;

    public RectD(){
        this.pos = new PointD(0,0);
        this.height = 0;
        this.width = 0;
    }

    public RectD(PointD pos, double width, double height){
        this.pos = pos.copy();
        this.height = height;
        this.width = width;
    }

    public PointD getCenter(){
        return new PointD(pos.x+width/2, pos.y+height/2);
    }

    public void setCenter(PointD pos){
        this.pos.x = pos.x-width/2;
        this.pos.y = pos.y-height/2;
    }

    public boolean contains(PointD point){
        if( point.x < pos.x || point.x > pos.x+width ) return false;
        if( point.y < pos.y || point.y > pos.y+height ) return false;
        return true;
    }
}
