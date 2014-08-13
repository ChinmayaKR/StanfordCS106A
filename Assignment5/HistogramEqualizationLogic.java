
public class HistogramEqualizationLogic {
	private static final int MAX_LUMINANCE = 255;
	
	/**
	 * Given the luminances of the pixels in an image, returns a histogram of the frequencies of
	 * those luminances.
	 * <p>
	 * You can assume that pixel luminances range from 0 to MAX_LUMINANCE, inclusive.
	 * 
	 * @param luminances The luminances in the picture.
	 * @return A histogram of those luminances.
	 */
	public static int[] histogramFor(int[][] luminances) {
	
		int[] histogram = new int[MAX_LUMINANCE + 1];

		initArray(histogram);
		
		computeHistogram(luminances, histogram);
		
		return histogram;
	}

	/**
	 * Sets all values in the array passed to zero.
	 * 
	 * @param histogram The array to be initialized.
	 */
	private static void initArray(int[] histogram) {
		
		for(int i = 0 ; i < histogram.length ; i++) {
			histogram[i] = 0;
		}
	}

	/**
	 * Calculates the histogram for the given image using the matrix
	 * of luminances.
	 * 
	 * @param luminances The luminances of the pixels of the input image.
	 * @param histogram The histogram of the luminances.
	 */
	private static void computeHistogram(int[][] luminances, int[] histogram) {
		for(int row = 0 ; row < luminances.length ; row++) {
			for(int col = 0 ; col < luminances[row].length ; col++) {

				for(int luminance = 0 ; luminance <= MAX_LUMINANCE ; luminance++) {
					if(luminances[row][col] == luminance) {
						histogram[luminance]++;
					}
				}
			}
		}
	}
	    
	/**
	 * Given a histogram of the luminances in an image, returns an array of the cumulative
	 * frequencies of that image.  Each entry of this array should be equal to the sum of all
	 * the array entries up to and including its index in the input histogram array.
	 * <p>
	 * For example, given the array [1, 2, 3, 4, 5], the result should be [1, 3, 6, 10, 15].
	 * 
	 * @param histogram The input histogram.
	 * @return The cumulative frequency array.
	 */
	public static int[] cumulativeSumFor(int[] histogram) {

		int[] cumulativeHistogram = new int[MAX_LUMINANCE + 1];
		
		initArray(cumulativeHistogram);
		
		computeCumulativeHistogram(histogram, cumulativeHistogram);
		
		return cumulativeHistogram;
	}

	/**
	 * Calculates the cumulative frequency array using the values from the input 
	 * histogram array.
	 * 
	 * @param histogram The input histogram
	 * @param cumulativeHistogram The cumulative histogram of the given image.
	 */
	private static void computeCumulativeHistogram(int[] histogram, int[] cumulativeHistogram) {
		for(int row = 0 ; row < cumulativeHistogram.length ; row++) {
			int count = row;
			
			while(count >= 0) {
				cumulativeHistogram[row] += histogram[count];
				count--;
			}
		}
	}
	
	/**
	 * Returns the total number of pixels in the given image.
	 * 
	 * @param luminances A matrix of the luminances within an image.
	 * @return The total number of pixels in that image.
	 */
	public static int totalPixelsIn(int[][] luminances) {
		
		return getPixelCount(luminances);
	}

	/**
	 * Counts and returns the total number of pixels in the given image.
	 * 
	 * @param luminances A matrix of the luminances in the given image.
	 * @return pixelCount The total number of pixels in the given image.
	 */
	private static int getPixelCount(int[][] luminances) {
		int pixelCount = 0;
		
		for(int row = 0 ; row < luminances.length ; row++) {
			for(int col = 0 ; col < luminances[row].length ; col++) {
				pixelCount++;
			}
		}
		return pixelCount;
	}
	
	/**
	 * Applies the histogram equalization algorithm to the given image, represented by a matrix
	 * of its luminances.
	 * <p>
	 * You are strongly encouraged to use the three methods you have implemented above in order to
	 * implement this method.
	 * 
	 * @param luminances The luminances of the input image.
	 * @return The luminances of the image formed by applying histogram equalization.
	 */
	public static int[][] equalize(int[][] luminances) {

		int totalPixelCount = totalPixelsIn(luminances);
		int[] cumulativeHistogram = cumulativeSumFor(histogramFor(luminances));
		
		computeNewLuminance(luminances, totalPixelCount, cumulativeHistogram);

		return luminances;
	}

	/**
	 * Calculates the new luminances for each pixel, and substitutes the old luminance
	 * value of the pixel with the newer one.
	 * 
	 * @param luminances The luminances of the input image.
	 * @param totalPixelCount the total number of pixels in the given image.
	 * @param cumulativeHistogram The cumulative histogram of the given image.
	 */
	private static void computeNewLuminance(int[][] luminances, int totalPixelCount, int[] cumulativeHistogram) {
		for(int row = 0 ; row < luminances.length ; row++) {
			for(int col = 0 ; col < luminances[row].length ; col++) {
				
				luminances[row][col] = (MAX_LUMINANCE * cumulativeHistogram[luminances[row][col]])/totalPixelCount;
			}
		}
	}
}
