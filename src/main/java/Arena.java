import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private final int x_;
    private final int y_;
    private final Hero hero;

    private final List <Wall> walls;

    private final List <Coin> coins;

    private  final List <Monster> monsters;

    public Arena(int x, int y) {
        x_ = x;
        y_ = y;

        this.hero = new Hero(x_ / 2, y_ / 2);
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();


    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 15 ; i++)
            monsters.add(new Monster(random.nextInt(x_- 2) + 1, random.nextInt(y_ - 2) + 1));
        return monsters;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 12; i++)
            coins.add(new Coin(random.nextInt(x_- 2) + 1, random.nextInt(y_ - 2) + 1));
        return coins;
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < x_; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, y_ - 1));
        }
        for (int r = 1; r < y_ - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(x_ - 1, r));
        }
        return walls;
    }

    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
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

        retrieveCoins();
        verifyMonsterColisions();
        moveMonsters();
        verifyMonsterColisions();

        System.out.println(key);
    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#2E8B57"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new
                TerminalSize(x_, y_), ' ');
        hero.draw(graphics);
        for (Wall wall : walls) wall.draw(graphics);
        for (Coin coin : coins) coin.draw(graphics);
        for (Monster monster: monsters) monster.draw(graphics);

    }

    private  void verifyMonsterColisions(){
        for (Monster monster: monsters)
            if(hero.getPosition().equals(monster.getPosition())){
                System.out.println("Morreste!");
                System.exit(0);
            }

    }
    private void moveMonsters(){
        for (Monster monster: monsters){
            Position monsterPosition = monster.move();
            if(canHeroMove(monsterPosition)) monster.setPosition(monsterPosition);
        }
    }
    private void retrieveCoins(){
        if (coins.isEmpty()) {
            System.out.println("Parabéns, recolheste todas as moedas!");
            System.exit(0);
        }
        for(Coin coin : coins)
            if(hero.getPosition().equals(coin.getPosition())){
                coins.remove(coin);
                break;
            }

    }

    public void moveHero(Position position) {
        if (canHeroMove(position)) {
            hero.setPosition(position);
        }
    }

    private boolean canHeroMove(Position position) {
        if ((position.getX() > x_ - 1 && position.getX() < 0) && (position.getY() > y_ - 1 && position.getY() < 0))
            return false;

        for (Wall wall : walls) if (wall.getPosition().equals(position)) return false;

        return true;

    }

}
