
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
		
		if(word.equals(null))//check for an exception 
			throw new IllegalArgumentException("word is null" + word);
		myWord = word;
		
		
		 
		if(weight < 0)//check for another exception
			throw new IllegalArgumentException("negative weight "+weight);
		myWeight = weight;
		
	}
	
	/**
	 * The default sorting of Terms is lexicographical ordering.
	 */
	public int compareTo(Term that) {
		return myWord.compareTo(that.myWord);//simple compareTo 
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

	public String toString() {//if we want to print our word
		return String.format("(%2.1f,%s)", myWeight, myWord);
	}
	
	@Override
	public boolean equals(Object o) {
		Term other = (Term) o;//need to convert cast object into a term object then we can use compareTo
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
		 *            
		 * @return an int signaling the order of the these terms
		 */
		public int compare(Term v, Term w) {
			// TODO: Implement compare
			
			//first, we need to figure out our size 
			//for the for loop to iterate
			
			
			//we want to set to the shorter one if the words differ
			
			int compareSize = myPrefixSize; 
			
			//store our length of the terms for this method
			int vlength = v.getWord().length();
			int wlength = w.getWord().length();
			
			/*
			 * Essentially, these min statements will find out the least amount of times
			 * we have to loop through to find the terms and check which one is greater
			 */
			
			
			//these min will eventually get you the smallest times you have to loop through terms
			int min = Math.min(wlength, vlength);//we are getting the minimum out of these three
			
			int loop = Math.min(min, compareSize);//same reasoning as before.
			
			for(int i = 0; i < loop; i++)//cycle through each character and compare the characters of each term
			{
				if(v.getWord().charAt(i) < w.getWord().charAt(i)) return -1;
				if(v.getWord().charAt(i) > w.getWord().charAt(i)) return 1;
			}
			
			
			//return 0 they are the same terms
			if(vlength >= myPrefixSize && wlength >= myPrefixSize)
			{
				return 0;
			}
			if(vlength == wlength) return 0;
			
			if(vlength > wlength) return 1;//if all the characters up to a certain point are equal, compare lengths of the terms
			//don't need to check other case
			return -1;
		}
	
	}

	/**
	 * A Comparator for comparing Terms using only their weights, in descending
	 * order. This Comparator may be useful in writing your implementations of
	 * Autocompletor
	 *
	 *@param v, w are the term parameters
	 *
	 *@return an int signaling less than, equal to, or greater than
	 */
	public static class ReverseWeightOrder implements Comparator<Term> {
		//this compare method compares the terms only using their weight
		//returning 0, -1, or 1 as you would normally with a compare function
		//descending order
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
	 *@param v, w are the term parameters
	 *
	 *@return an int signaling less than, equal to, or greater than
	 */
	public static class WeightOrder implements Comparator<Term> {
		public int compare(Term v, Term w) {
			// TODO: implement compare
			
			//this compare method compares the terms only using their weight
			//returning 0, -1, or 1 as you would normally with a compare function
			//ascending order
			if(v.getWeight() < w.getWeight()) return -1;
			if(v.getWeight() == w.getWeight()) return 0;
			if(v.getWeight() > w.getWeight())return 1;
 			
			
			return 0;
		}
	}
}
