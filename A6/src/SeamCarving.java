import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Function;

public class SeamCarving {
    private int[] pixels;
    private int type, height, width;

    // Field getters

    int[] getPixels() {
        return pixels;
    }

    int getType() {
        return type;
    }

    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }

    // Read and write images

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

    // Accessing pixels and their neighbors

    Color getColor(int h, int w) {
        int pixel = pixels[w + h * width];
        return new Color(pixel, true);
    }

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
}
