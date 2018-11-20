import java.util.*;

public class BinarySearchLibrary {
	
	public static <T>
	    int firstIndexSlow(List<T> list, 
	    		           T target, Comparator<T> comp) {
		int index = Collections.binarySearch(list, target,comp);
		
		if (index < 0) return index;
		
		while (0 <= index && comp.compare(list.get(index),target) == 0) {
			index -= 1;
		}
		return index+1;
	}
	
	/**
	 * Uses binary search to find the index of the first object in parameter
	 * list -- the first object o such that comp.compare(o,target) == 0.
	 * 
	 * This method should not call comparator.compare() more than 1+log n times
	 * @param list is the list of objects being searched
	 * @param target is the object being searched for
	 * @param comp is how comparisons are made
	 * @return index i such that comp.compare(list.get(i),target) == 0
	 * and there is no index < i such that this is true. Return -1
	 * if there is no such object in list.
	 */
	
	public static <T>
    	int firstIndex(List<T> list, 
	               	T target, Comparator<T> comp) {
		
		
		int low = -1;//set to -1 since the interval contains 0 to 99 inclusive
					//we are not including -1 with this
		int high = list.size()-1;
		// (low,high] contains target
		// TODO: complete method
		
		if(list.size()== 0) return -1;//if we have no elements
		while(low + 1 != high)//loop invariant
		{
			T mid = list.get((high+low)/2);//get the midpoint
			
			int y = comp.compare(mid, target);//compare to the target
			
			
			//adjust our low/high based on whether mid is too high or too low
			if(y < 0)
			{
				low =  (high + low)/2 ;
			}
			else if(y >= 0)
			{
				high = (high + low)/2 ;
			}
			
			
		}
		
		if(comp.compare(list.get(high),target) == 0)//if we found target, then we return high
			return high;
		
		//otherwise, the target is not in the list
		return -1;
	}

	
	/**
	 * Uses binary search to find the index of the last object in parameter
	 * list -- the first object o such that comp.compare(o,target) == 0.
	 * 
	 * This method should not call comparator.compare() more than 1+log n times
	 * @param list is the list of objects being searched
	 * @param target is the object being searched for
	 * @param comp is how comparisons are made
	 * @return index i such that comp.compare(list.get(i),target) == 0
	 * and there is no index > i such that this is true. Return -1
	 * if there is no such object in list.
	 */
	public static <T>
	int lastIndex(List<T> list, 
               	  T target, Comparator<T> comp) {
		
		int low = 0;
		int high = list.size();
		
		if(list.size()== 0) return -1;
		
		//similar reasoning as before with firstIndex
		while(high - 1 != low)
		{
			T mid = list.get((high+low)/2);//get midpoint
			
			int y = comp.compare(mid, target);//compare mid to the target
			
			
			//adjust high and low based on mid vs target
			if(y <= 0)
			{
				low =  (high + low)/2 ;
			}
			else if(y > 0)
			{
				high = (high + low)/2 ;
			}
			
		}
		if(comp.compare(list.get(low),target) == 0) //return low once we've adjusted enough times
			return low;
		
		return -1;//return -1 if list doesn't contain the target
	}
	
}
