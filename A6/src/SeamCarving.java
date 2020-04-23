
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

<<<<<<< HEAD
=======
    int[] getPixels() {
        return pixels;
    }

    int getType() {
        return type;
    }

    int getHeight() {
        return height;
    }

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
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

<<<<<<< HEAD
=======
    // Accessing pixels and their neighbors

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    Color getColor(int h, int w) {
        int pixel = pixels[w + h * width];
        return new Color(pixel, true);
    }

<<<<<<< HEAD

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
        int curEnergy = computeEnergy(h, w);
        Position curPix = new Position(h, w);
        Pair<List<Position>, Integer> result = null;
        if (hash.containsKey(curPix)) {
            return hash.get(curPix);
        } else {
            for (Position childPixel : belowNeighbors) {
                Pair<List<Position>, Integer> childSeam = findSeam(childPixel.getFirst(), childPixel.getSecond());
                if (childSeam.getSecond() < best) {
                    best = childSeam.getSecond();
                    min = childSeam;
                }
            }
            result = new Pair<>(new Node<>(curPix, min.getFirst()), min.getSecond()+curEnergy);
            hash.put(curPix, result);
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
        width = width-1;
    }

=======
    ArrayList<Position> getHVneighbors(int h, int w) {
        ArrayList<Position> neighbors = new ArrayList<>();

        if (w == 0) neighbors.add(new Position(h, w + 1));
        else if (w + 1 == width) neighbors.add(new Position(h, w - 1));
        else {
            neighbors.add(new Position(h, w - 1));
            neighbors.add(new Position(h, w + 1));
        }

        if (h == 0) neighbors.add(new Position(h + 1, w));
        else if (h + 1 == height) neighbors.add(new Position(h - 1, w));
        else {
            neighbors.add(new Position(h + 1, w));
            neighbors.add(new Position(h - 1, w));
        }
        return neighbors;
    }

    ArrayList<Position> getBelowNeighbors(int h, int w) {
        ArrayList<Position> neighbors = new ArrayList<>();
        if (h + 1 == height) return neighbors;
        neighbors.add(new Position(h + 1, w));
        if (w == 0) {
            neighbors.add(new Position(h + 1, w + 1));
            return neighbors;
        } else if (w + 1 == width) {
            neighbors.add(new Position(h + 1, w - 1));
            return neighbors;
        } else {
            neighbors.add(new Position(h + 1, w + 1));
            neighbors.add(new Position(h + 1, w - 1));
            return neighbors;
        }
    }

    // Computing energy at given pixel

    int computeEnergy(int h, int w) {
        Color c = getColor(h, w);
        Function<Integer, Integer> sq = n -> n * n;
        int energy = 0;
        for (Position p : getHVneighbors(h, w)) {
            Color nc = getColor(p.getFirst(), p.getSecond());
            energy += sq.apply(nc.getRed() - c.getRed());
            energy += sq.apply(nc.getGreen() - c.getGreen());
            energy += sq.apply(nc.getBlue() - c.getBlue());
        }
        return energy;
    }

    // Find seam starting from (h,w) going down and return list of positions and cost
    // and then pick best seam starting from some position on the first row

    Map<Position, Pair<List<Position>, Integer>> hash = new WeakHashMap<>();

    Pair<List<Position>, Integer> findSeam(int h, int w) {
        return hash.computeIfAbsent(new Position(h, w), p -> {

                    int energy = computeEnergy(h, w);
                    ArrayList<Position> below = getBelowNeighbors(h, w);
                    if (below.isEmpty()) {
                        return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), new Empty<>()),
                                energy);
                    } else {
                        if (below.size() == 1) {
                            Pair<List<Position>, Integer> s = findSeam(below.get(0).getFirst(), below.get(0).getFirst());
                            return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s.getFirst()),
                                    energy + s.getSecond());
                        } else if (below.size() == 2) {
                            Pair<List<Position>, Integer> s1 = findSeam(below.get(0).getFirst(), below.get(0).getSecond());
                            Pair<List<Position>, Integer> s2 = findSeam(below.get(1).getFirst(), below.get(1).getSecond());
                            if (s1.getSecond() <= s2.getSecond()) {
                                return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s1.getFirst()),
                                        energy + s1.getSecond());
                            } else {
                                return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s2.getFirst()),
                                        energy + s2.getSecond());
                            }
                        } else if (below.size() == 3) {
                            Pair<List<Position>, Integer> s1 = findSeam(below.get(0).getFirst(), below.get(0).getSecond());
                            Pair<List<Position>, Integer> s2 = findSeam(below.get(1).getFirst(), below.get(1).getSecond());
                            Pair<List<Position>, Integer> s3 = findSeam(below.get(2).getFirst(), below.get(2).getSecond());

                            if (s1.getSecond() <= s2.getSecond()) {
                                if (s1.getSecond() <= s3.getSecond()) {
                                    return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s1.getFirst()),
                                            energy + s1.getSecond());
                                } else {
                                    return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s3.getFirst()),
                                            energy + s3.getSecond());
                                }
                            } else {
                                if (s2.getSecond() <= s3.getSecond()) {
                                    return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s2.getFirst()),
                                            energy + s2.getSecond());
                                } else {
                                    return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s3.getFirst()),
                                            energy + s3.getSecond());
                                }
                            }
                        }
                    }
                    return null;
                });
    }



    Pair<List<Position>, Integer> bestSeam() {
        hash.clear();
        int cost = Integer.MAX_VALUE;
        List<Position> seam = new Empty<>();
        for (int w = 0; w < width; w++) {
            Pair<List<Position>, Integer> r = findSeam(0, w);
            if (r.getSecond() < cost) {
                seam = r.getFirst();
                cost = r.getSecond();
            }
        }
        return new Pair<>(seam, cost);
    }

    // Putting it all together; find best seam and copy pixels without that seam

    void cutSeam() {
        try {
            List<Position> seam = bestSeam().getFirst();
            int[] newPixels = new int[height * (width - 1)];
            for (int h = 0; h < height; h++) {
                int nw = 0;
                for (int w = 0; w < width; w++) {
                    if (seam.isEmpty()) {
                        newPixels[nw + h * (width - 1)] = pixels[w + h * width];
                    }
                    else {
                        Position p = seam.getFirst();
                        if (p.getFirst() == h && p.getSecond() == w) {
                            seam = seam.getRest();
                            nw--;
                        } else {
                            newPixels[nw + h * (width - 1)] = pixels[w + h * width];
                        }
                    }
                    nw++;
                }
            }
            width = width - 1;
            pixels = newPixels;
        } catch (EmptyListE e) {
            throw new Error("Bug");
        }
    }
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
}
