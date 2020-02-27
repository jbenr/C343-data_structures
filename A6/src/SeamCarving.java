
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Function;

public class SeamCarving {
    private int[] pixels;
    private int type, height, width;

    int[] getPixels() {
        return pixels;
    }

    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }

    Map<Position, Pair<List<Position>, Integer>> hash = new WeakHashMap<>();

    void readImage(String filename) throws IOException {
        BufferedImage image = ImageIO.read(new File(filename));
        type = image.getType();
        height = image.getHeight();
        width = image.getWidth();
        pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);
    }

    void writeImage(String filename) throws IOException {
        BufferedImage image = new BufferedImage(width, height, type);
        image.setRGB(0, 0, width, height, pixels, 0, width);
        ImageIO.write(image, "jpg", new File(filename));
    }

    Color getColor(int h, int w) {
        int pixel = pixels[w + h * width];
        return new Color(pixel, true);
    }


    ArrayList<Position> getHVneighbors(int h, int w) {
        Position r, l, t, b;
        ArrayList<Position> ghvn = new ArrayList<Position>();
        if (w == 0) {
            l = null;
        } else {
            l = new Position(h, w-1);
            ghvn.add(l);
        }

        if (w == this.width-1) {
            r = null;
        } else {
            r = new Position(h, w+1);
            ghvn.add(r);
        }
        if (h == 0) {
            t = null;
        } else {
            t = new Position(h-1, w);
            ghvn.add(t);
        }

        if (h == this.height-1) {
            b = null;
        } else {
            b = new Position(h+1, w);
            ghvn.add(b);
        }
        return ghvn;
    }


    ArrayList<Position> getBelowNeighbors(int h, int w) {
        ArrayList<Position> r = new ArrayList<>();
        if (h != height-1 && w != width-1) {
            r.add(new Position(h+1, w+1));
        }
        if (h != height - 1 && w != 0) {
            r.add(new Position(h+1, w-1));
        }
        if (h != height-1) {
            r.add(new Position(h+1, w));
        }
        return r;
    }


    int computeEnergy(int h, int w) {

        ArrayList<Position> n = getHVneighbors(h, w);
        int r = 0;
        final Color cc = getColor(h, w);

        for (Position i : n) {
            int red = getColor(i.getFirst(), i.getSecond()).getRed();
            int green = getColor(i.getFirst(), i.getSecond()).getGreen();
            int blue = getColor(i.getFirst(), i.getSecond()).getBlue();
            int sRed = (int) Math.pow((cc.getRed() - red), 2);
            int sGreen = (int) Math.pow((cc.getGreen() - green), 2);
            int sBlue = (int) Math.pow((cc.getBlue() - blue), 2);
            int temp = sRed + sBlue + sGreen;
            r = r + temp;
        }
        return (int)r;
    }

    Pair<List<Position>, Integer> findSeam(int h, int w) {

        Pair<List<Position>, Integer> min = null;
        int best = Integer.MAX_VALUE;
        ArrayList<Position> belowNeighbors = getBelowNeighbors(h, w);
        if (belowNeighbors.isEmpty()) {
            return new Pair<List<Position>, Integer>((List<Position>) new Node<Position>(new Position(h, w), new Empty<>()), computeEnergy(h, w));
        }
        int currentEnergy = computeEnergy(h, w);
        Position currentPixel = new Position(h, w);
        Pair<List<Position>, Integer> finalSeam = null;
        if (hash.containsKey(currentPixel)) {
            return hash.get(currentPixel);
        } else {
            for (Position childPixel : belowNeighbors) {
                Pair<List<Position>, Integer> childSeam = findSeam(childPixel.getFirst(), childPixel.getSecond());
                if (childSeam.getSecond() < best) {
                    best = childSeam.getSecond();
                    min = childSeam;
                }
            }
            finalSeam = new Pair<>(new Node<>(currentPixel, min.getFirst()), currentEnergy + min.getSecond());
            hash.put(currentPixel, finalSeam);
        }
        return finalSeam;
    }

    Pair<List<Position>, Integer> bestSeam() {
        hash.clear();
        Pair<List<Position>, Integer> bs = new Pair<>(new Empty<>(), Integer.MAX_VALUE);

        for (int i = 1; i < width; i++) {
            Pair<List<Position>, Integer> temp = findSeam(0, i);
            if (temp.getSecond() < bs.getSecond()) {
                bs = temp;
            }
        }
        return bs;
    }

    void cutSeam() {
        int[] cs = new int[height * (width - 1)];
        Pair<List<Position>, Integer> best = bestSeam();
        List<Position> list = best.getFirst();

        try {
            List<Position> pl = best.getFirst();
            Position p1 = pl.getFirst();
            for (int h = 0; h < height; h++) {
                int w2 = 0;
                for (int w = 0; w < width; w++) {
                    if (pl.isEmpty()) {
                        cs[h * (width - 1) + w2] = pixels[h + w2];
                        w2 = w2+1;
                    } else if ((pl.getFirst().getFirst() == h) && (pl.getFirst().getSecond() == w)) {
                        pl = pl.getRest();
                    } else {
                        cs[h * (width - 1) + w2] = pixels[h + w2];
                        w2 = w2+1;
                    }
                    pl = pl.getRest();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pixels = cs;
        width = width - 1;
    }

}
