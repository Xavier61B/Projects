import java.awt.*;
import java.util.Random;

public class Main {
    private static int width = 100;
    private static int height = 100;
    private static int iterations = 5000;
    private Random random;
    private Starts startP;
    private Point startA;
    private static final TETile def = new TETile('.', Color.white, Color.black, "point");
    private static final TETile nothing = new TETile(' ', Color.black, Color.black, "nothing");

    private class Point {
        int x;
        int y;

        public Point(int xCoord, int yCoord) {
            x = xCoord;
            y = yCoord;
        }
    }

    private class Starts {
        Point[] value;

        public Starts() {
            value = new Point[3];
            value[0] = new Point(0, 0);
            value[1] = new Point(0, 100);
            value[2] = new Point(100, 50);
        }
    }

    public Point randCoord() {
        return new Point(random.nextInt(width), random.nextInt(height));
    }

    public TETile[][] render(TETile[][] world) {
        world[startA.x][startA.y] = def;
        int corner = random.nextInt(3);
        int newX = (startA.x + startP.value[corner].x)/2;
        int newY = (startA.y + startP.value[corner].y)/2;
        startA = new Point(newX, newY);
        return world;
    }

    public Main() {
        random = new Random();
        startP = new Starts();
        startA = randCoord();
    }

    public static void main(String args[]) {
        Main main = new Main();
        TERenderer ter = new TERenderer();
        TETile[][] world = new TETile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                world[i][j] = nothing;
            }
        }
        ter.initialize(width, height);
        ter.renderFrame(world);
        for (int q = 0; q < iterations; q++) {
            world = main.render(world);
            ter.renderFrame(world);
            StdDraw.show();
        }
    }
}
