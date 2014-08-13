import acm.graphics.*;

public class SteganographyLogic {

	/**
	 * Given a GImage containing a hidden message, finds the hidden message
	 * contained within it and returns a boolean array containing that message.
	 * <p>
	 * A message has been hidden in the input image as follows.  For each pixel
	 * in the image, if that pixel has a red component that is an even number,
	 * the message value at that pixel is false.  If the red component is an odd
	 * number, the message value at that pixel is true.
	 * 
	 * @param source The image containing the hidden message.
	 * @return The hidden message, expressed as a boolean array.
	 */
	public static boolean[][] findMessage(GImage source) {

		int[][] sourceArray = source.getPixelArray();
		int numRows = sourceArray.length;
		int numColumns = sourceArray[0].length;
		boolean[][] hiddenImage = new boolean[numRows][numColumns];
		
		for(int row = 0; row < numRows ; row++) {
			for(int col = 0; col < numColumns ; col++) {
				int redPixelValue = GImage.getRed(sourceArray[row][col]);
				
				if(redPixelValue % 2 != 0) {
					hiddenImage[row][col] = true;
				} else {
					hiddenImage[row][col] = false;
				}
			}
		}
		return hiddenImage;
	}
	
	/**
	 * Hides the given message inside the specified image.
	 * <p>
	 * The image will be given to you as a GImage of some size, and the message will
	 * be specified as a boolean array of pixels, where each white pixel is denoted
	 * false and each black pixel is denoted true.
	 * <p>
	 * The message should be hidden in the image by adjusting the red channel of all
	 * the pixels in the original image.  For each pixel in the original image, you
	 * should make the red channel an even number if the message color is white at
	 * that position, and odd otherwise.
	 * <p>
	 * You can assume that the dimensions of the message and the image are the same.
	 * <p>
	 * 
	 * @param message The message to hide.
	 * @param source The source image.
	 * @return A GImage whose pixels have the message hidden within it.
	 */
	public static GImage hideMessage(boolean[][] message, GImage source) {

		int[][] sourceArray = source.getPixelArray();
		int numRows = sourceArray.length;
		int numColumns = sourceArray[0].length;
		
		for(int row = 0 ; row < numRows ; row++) {
			for(int col = 0 ; col < numColumns ; col++) {
				int red = GImage.getRed(sourceArray[row][col]);
				int green = GImage.getGreen(sourceArray[row][col]);
				int blue = GImage.getBlue(sourceArray[row][col]);

				red = getNewRedPixelValue(message, row, col, red);
				
				sourceArray[row][col] = GImage.createRGBPixel(red, green, blue);
			}
		}
		
		return new GImage(sourceArray);
	}

	/**
	 * Calculates the new value for the red pixel and returns the same.
	 * 
	 * @param message The message to hide.
	 * @param row The row in the pixel array currently being iterated through.
	 * @param col The column in the pixel array currently being iterated through.
	 * @param red The value of the red pixel
	 * @return The new/changed value of the red pixel.
	 */
	private static int getNewRedPixelValue(boolean[][] message, int row, int col, int red) {
		if(message[row][col] == true) {
			if(red % 2 == 0) {
				if(red == 0) {
					red++;
				} else {
					red--;
				}
			} 
		} else {
			if(red % 2 != 0) {
				if(red == 255) {
					red--;
				} else {
					red++;
				}
			}
		}
		return red;
	}
}
