import java.awt.*;
import java.util.Random;

public class Main {
    private static int width = 600;
    private static int height = 300;
    private static int iterations = 10000;
    private static int iterprend = 1000;
    private static int maxiter = 1000;
    private Random random;
    private Starts startP;
    private Point startA;
    private static final TETile def = new TETile('█', Color.white, Color.black, "point");
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
            value[1] = new Point(width, 0);
            value[2] = new Point(width / 2, height);
        }
    }

    public Point randCoord() {
        return new Point(random.nextInt(width), random.nextInt(height));
    }

    public TETile[][] render(TETile[][] world) {
        world[startA.x][startA.y] = def;
        int count = 0;
        while (world[startA.x][startA.y] == def) {
            int corner = random.nextInt(3);
            startA.x = (startA.x + startP.value[corner].x) / 2;
            startA.y = (startA.y + startP.value[corner].y) / 2;
            if (count == maxiter) {
                System.exit(0);
            }
            count += 1;
        }
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
        int i = (int) Math.floor(java.lang.Math.exp(7));
        System.out.println(i);
        double j = 0;
        for (int q = 0; q < iterations; q++) {
            world = main.render(world);
            if (i == 0) {
                ter.renderFrame(world);
                StdDraw.show();
                j += 1.0;
                i = (int) Math.floor(java.lang.Math.exp(7.0-(1.0/9.0)*j));
                System.out.println(i);
            }
            i -= 1;
        }
    }
}
