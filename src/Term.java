
/*************************************************************************
 * @author Kevin Wayne
 *
 * Description: A term and its weight.
 * 
 *************************************************************************/

import java.util.Comparator;

public class Term implements Comparable<Term> {

	private final String myWord;
	private final double myWeight;

	/**
	 * The constructor for the Term class. Should set the values of word and
	 * weight to the inputs, and throw the exceptions listed below
	 * 
	 * @param word
	 *            The word this term consists of
	 * @param weight
	 *            The weight of this word in the Autocomplete algorithm
	 * @throws NullPointerException
	 *             if word is null
	 * @throws IllegalArgumentException
	 *             if weight is negative
	 */
	public Term(String word, double weight) {
		// TODO: Complete Term constructor
		
		if(word.equals(null))//.equals bc string
			throw new IllegalArgumentException("word is null" + word);
		myWord = word;
		
		
		 
		if(weight < 0)
			throw new IllegalArgumentException("negative weight "+weight);
		myWeight = weight;
		
	}
	
	/**
	 * The default sorting of Terms is lexicographical ordering.
	 */
	public int compareTo(Term that) {
		return myWord.compareTo(that.myWord);
	}

	/**
	 * Getter methods, use these in other classes which use Term
	 */
	public String getWord() {
		return myWord;
	}

	public double getWeight() {
		return myWeight;
	}

	public String toString() {
		return String.format("(%2.1f,%s)", myWeight, myWord);
	}
	
	@Override
	public boolean equals(Object o) {
		Term other = (Term) o;
		return this.compareTo(other) == 0;
	}

	/**
	 * A Comparator for comparing Terms using a set number of the letters they
	 * start with. This Comparator may be useful in writing your implementations
	 * of Autocompletors.
	 *
	 */
	public static class PrefixOrder implements Comparator<Term> {
		private final int myPrefixSize;

		public PrefixOrder(int r) {
			this.myPrefixSize = r;
		}

		/**
		 * Compares v and w lexicographically using only their first r letters.
		 * If the first r letters are the same, then v and w should be
		 * considered equal. This method should take O(r) to run, and be
		 * independent of the length of v and w's length. You can access the
		 * Strings to compare using v.word and w.word.
		 * 
		 * @param v/w
		 *            - Two Terms whose words are being compared
		 */
		public int compare(Term v, Term w) {
			// TODO: Implement compare
			
			//first, we need to figure out our size 
			//for the for loop to iterate
			
			
			//we want to set to the shorter one if the words differ
			
			int compareSize = myPrefixSize; 
			
			int vlength = v.getWord().length();
			int wlength = w.getWord().length();
			
			int min = Math.min(wlength, vlength);//we are getting the minimum out of these three
			
			compareSize = Math.min(min, compareSize);//same reasoning as before.
			
			for(int i = 0; i < compareSize; i++)
			{
				if(v.getWord().charAt(i) < w.getWord().charAt(i)) return -1;
				if(v.getWord().charAt(i) > w.getWord().charAt(i)) return 1;
			}
			
			
			//if our characters are equal, compare size of vlength and wlength
			if(vlength >= myPrefixSize && wlength >= myPrefixSize)
			{
				return 0;
			}
			
			if(vlength > wlength) return 1;
			return -1;
		}
	
	}

	/**
	 * A Comparator for comparing Terms using only their weights, in descending
	 * order. This Comparator may be useful in writing your implementations of
	 * Autocompletor
	 *
	 */
	public static class ReverseWeightOrder implements Comparator<Term> {
		public int compare(Term v, Term w) {
			// TODO: implement compare
			
			if(v.getWeight() < w.getWeight()) return 1;
			if(v.getWeight() == w.getWeight()) return 0;
			if(v.getWeight() > w.getWeight())return -1;
 			
			
			return 0;
		}
	}

	/**
	 * A Comparator for comparing Terms using only their weights, in ascending
	 * order. This Comparator may be useful in writing your implementations of
	 * Autocompletor
	 *
	 */
	public static class WeightOrder implements Comparator<Term> {
		public int compare(Term v, Term w) {
			// TODO: implement compare
			if(v.getWeight() < w.getWeight()) return -1;
			if(v.getWeight() == w.getWeight()) return 0;
			if(v.getWeight() > w.getWeight())return 1;
 			
			
			return 0;
		}
	}
}
