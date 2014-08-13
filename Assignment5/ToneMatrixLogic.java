
public class ToneMatrixLogic {

	/**
	 * Given the contents of the tone matrix, returns a string of notes that should be played
	 * to represent that matrix.
	 * 
	 * @param toneMatrix The contents of the tone matrix.
	 * @param column The column number that is currently being played.
	 * @param samples The sound samples associated with each row.
	 * @return A sound sample corresponding to all notes currently being played.
	 */
	public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
		double[] result = new double[ToneMatrixConstants.sampleSize()];
		
		int numLights = getLightCount(toneMatrix, column);
		
		produceFinalSound(toneMatrix, column, samples, result);
		
		normalizeSound(result, numLights);

		return result;
	}

	/** 
	 * Returns the number of lights switched on in the column being played 
	 * 
	 * @param toneMatrix The contents of the tone matrix.
	 * @param column The column number that is currently being played.
	 * @return The number of lights switched on in the column currently being played.
	 */
	private static int getLightCount(boolean[][] toneMatrix, int column) {

		int numLights = 0;

		for(int row = 0 ; row < toneMatrix.length ; row++) {
			if(toneMatrix[row][column]) {
				numLights++;
			}
		}
		return numLights;
	}

	/**
	 * Applies the normalization algorithm to the final sound sample.
	 * 
	 * @param result The sound sample corresponding to all notes currently being played.
	 * @param numLights The number of lights switched on in the column currently being played.
	 */
	private static void normalizeSound(double[] result, int numLights) {
		
		if(numLights != 0) {
			for(int i = 0 ; i < result.length ; i++) {
				result[i] /= numLights;
			}
		}
	}

	/**
	 * Produces a single sound sample corresponding to all notes currently being played
	 * by adding up all the sound samples.
	 * 
	 * @param toneMatrix The contents of the tone matrix.
	 * @param column The column number that is currently being played.
	 * @param samples The sound samples associated with each row.
	 * @param result The sound sample corresponding to all notes currently being played.
	 */	
	private static void produceFinalSound(boolean[][] toneMatrix, int column, double[][] samples, double[] result) {
		
		for(int row = 0 ; row < toneMatrix.length ; row++) {
			if(toneMatrix[row][column]) {
				for(int i = 0 ; i < samples[0].length ; i++) {
					result[i] += samples[row][i];
				}
			}
		}
	}
}
