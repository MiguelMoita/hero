import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {
    private int x_;
    private int y_;
    private Hero hero = new Hero(10, 10);
    public Arena(int x, int y){
        x_=x;
        y_=y;


    }

    public void processKey(KeyStroke key) {
        switch (key.getKeyType()){
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case ArrowDown:
                moveHero(hero.moveDown());
                break;
            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                break;
        }
        System.out.println(key);
    }

    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#D18706"));
                graphics.fillRectangle(new TerminalPosition(0, 0), new
                        TerminalSize(x_,y_), ' ');
        hero.draw(graphics);
    }

    public void moveHero(Position position) {
        if (canHeroMove(position)){
            hero.setPosition(position);
        }
    }

    private boolean canHeroMove(Position position){
        if( (position.getX() < x_ && position.getX()>0) && (position.getY() < y_ && position.getY()>0) ){
            return true;
        }
        return false;
    }

}
