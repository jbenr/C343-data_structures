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

    int[] getPixels () { return pixels; }
    int getHeight () { return height; }
    int getWidth () { return width; }

    // Read and write images

    void readImage (String filename) throws IOException {
        BufferedImage image = ImageIO.read(new File(filename));
        type = image.getType();
        height = image.getHeight();
        width = image.getWidth();
        pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);
    }

    void writeImage (String filename) throws IOException {
        BufferedImage image = new BufferedImage(width,height,type);
        image.setRGB(0,0,width,height,pixels,0,width);
        ImageIO.write(image, "jpg", new File(filename));
    }

    // Accessing pixels and their neighbors

    /***
     * By convention, h is the vertical index and w and the horizontal index.
     * The array of pixels is stored as follows:
     * [(0,0), (0,1), (0,2), ... (0,width-1), (1,0), (1,1), (1,2), ... (1,width-1), ...]
     */
    Color getColor(int h, int w) {
        int pixel = pixels[w + h * width];
        return new Color(pixel, true);
    }

    /**
     * This method takes the position of a pixel (h,w) and returns a list of its
     * neighbors' positions in the horizontal and vertical directions.
     * In the general case, these would be at  positions:
     * (h+1,w), (h-1,w), (h,w+1), (h,w-1).
     * Of course, care must be taken when dealing with pixels along the boundaries.
     */

    ArrayList<Position> getHVneighbors(int h, int w) {
        return null; // to write
    }

    /**
     * This method takes the position of a pixel (h,w) and returns a list of its
     * neighbors' positions that are below and touching it.
     * In the general case, these would be at  positions:
     * (h+1,w-1), (h+1,w), (h+1,w+1)
     * Of course, care must be taken when dealing with pixels along the boundaries.
     */

    ArrayList<Position> getBelowNeighbors(int h, int w) {
        return null; // to write
    }

    /**
     * This method takes the position of a pixel (h,w) and computes its 'energy'
     * which is an estimate of how it differs from its neighbors. The computation
     * is as follows. First, using the method getColor, get the colors of the pixel
     * and all its neighbors in the horizontal and vertical dimensions. The energy
     * is the sum of the squares of the differences along each of the RGB components
     * of the color. For example, given two colors c1 and c2 (for the current pixel
     * and one of its neighbors), we would compute this component of the energy as:
     *   square (c1.getRed() - c2.getRed()) +
     *   square (c1.getGreen() - c2.getGreen()) +
     *   square (c1.getBlue() - c2.getBlue())
     * The total energy is this quantity summed over all the neighbors in the
     * horizontal and vertical dimensions.
     */

    int computeEnergy(int h, int w) {
        return 0; // to write
    }

    /**
     * This next method is the core of our dynamic programming algorithm. We will
     * use the top-down approach with the given hash table (which you should initialize).
     * The key to the hash table is a pixel position. The value stored at each key
     * is the "seam" that starts with this pixel all the way to the bottom
     * of the image and its cost.
     *
     * The method takes the position of a pixel and returns the seam from this pixel
     * and its cost using the following steps:
     *   - compute the energy of the given pixel
     *   - get the list of neighbors below the current pixel
     *   - Base case: if the list of neighbors is empty, return the following pair:
     *       < [<h,w>], energy >
     *     the first component of the pair is a list containing just one position
     *     (the current one); the second component of the pair is the current energy.
     *   - Recursive case: we will consider each of the neighbors below the current
     *     pixel and choose the one with the cheapest seam.
     *
     */
    Pair<List<Position>, Integer> findSeam(int h, int w) {
        return null; // to write
    }

    /**
     * This next method is relatively short. It performs the following actions:
     *   - clears the hash table
     *   - iterate over the first row of the image, computing the seam
     *     from its position and returning the best one.
     */

    Pair<List<Position>,Integer> bestSeam () {
        return null; // to write
    }

    /**
     * The last method puts its all together:
     *   - it finds the best seam
     *   - then it creates a new array of pixels representing an image of dimensions
     *     (height,width-1)
     *   - it then copies the old array pixels to the new arrays skipping the pixels
     *     in the seam
     *   - the method does not return anything: instead it updates the width and
     *     pixels instance variables to the new values.
     */
    void cutSeam () {
        // to write
    }
}


