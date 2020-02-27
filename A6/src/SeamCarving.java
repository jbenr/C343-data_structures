
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
        ArrayList<Position> lst = new ArrayList<Position>();
        if(h < height-1) { lst.add(new Position(h+1, w)); }
        if(h != 0) { lst.add(new Position(h-1, w)); }
        if(w < width-1) { lst.add(new Position(h, w+1)); }
        if(w != 0) { lst.add(new Position(h, w-1)); }
        return lst;
    }


    ArrayList<Position> getBelowNeighbors(int h, int w) {
        ArrayList<Position> lst = new ArrayList<Position>();
        if(h < height-1) {
            lst.add(new Position(h+1, w));
            if(w < width-1) { lst.add(new Position(h+1, w+1)); }
            if(w != 0) { lst.add(new Position(h+1, w-1)); }
        }
        return lst;
    }


    int computeEnergy(int h, int w) {

        int energy = 0;
        ArrayList<Position> lst = getHVneighbors(h, w);
        ArrayList<Color> colors = new ArrayList<Color>();
        for(int i = 0; i < lst.size(); i++) {
            colors.add(getColor(lst.get(i).getFirst(), lst.get(i).getSecond()));
        }
        Color color = getColor(h, w);
        for(int i = 0; i < colors.size(); i++) {
            energy = energy + ( (int) Math.pow((color.getRed() - colors.get(i).getRed()), 2) +
                    (int) Math.pow((color.getGreen() - colors.get(i).getGreen()), 2) +
                    (int) Math.pow((color.getBlue() - colors.get(i).getBlue()), 2) );
        }
        return energy;
    }

    Pair<List<Position>, Integer> findSeam(int h, int w) {

        Pair<List<Position>, Integer> min = null;
        int best = Integer.MAX_VALUE;
        ArrayList<Position> belowNeighbors = getBelowNeighbors(h, w);
        if (belowNeighbors.isEmpty()) {
            return new Pair<List<Position>, Integer>((List<Position>) new Node<Position>(new Position(h, w), new Empty<>()), computeEnergy(h, w));
        }
        int currentEnergy = computeEnergy(h, w);
        Position currentPix = new Position(h, w);
        Pair<List<Position>, Integer> result = null;
        if (hash.containsKey(currentPix)) {
            return hash.get(currentPix);
        } else {
            for (Position childPixel : belowNeighbors) {
                Pair<List<Position>, Integer> childSeam = findSeam(childPixel.getFirst(), childPixel.getSecond());
                if (childSeam.getSecond() < best) {
                    best = childSeam.getSecond();
                    min = childSeam;
                }
            }
            result = new Pair<>(new Node<>(currentPix, min.getFirst()), currentEnergy + min.getSecond());
            hash.put(currentPix, result);
        }
        return result;
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
