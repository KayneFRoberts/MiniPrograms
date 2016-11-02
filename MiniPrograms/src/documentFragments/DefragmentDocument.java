/**
 * 
 */
package documentFragments;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @author Kayne
 *
 */
public class DefragmentDocument implements Comparator<String>{
	
	/** Implement Comparator compare method to sort list by size of String in descending order 
	 * 
	 * Parameters: String, String to compare against
	 * Returns: int different between input Strings
	 * */
	public int compare(String compare1, String compare2){
		return(compare2.length()-compare1.length());
	}
	
	/** 
	 * Reassembles String of fragmented text where each fragment is separated by a ";" into
	 * one String via merging overlapping fragments
	 * 
	 * Parameters: fragmented String
	 * Returns: de-fragmented String
	 * */	
	static String reassemble(String fragmentProblem) {
        String input =fragmentProblem;//Input String from main
        List<String> fragments= new ArrayList<String>();//List of fragments in text
        DefragmentDocument sorted= new DefragmentDocument();//Create new instance of class for comparator
        int searchStart=0; //Start point for finding instances of ";"
        int searchNext=input.indexOf(";");//Position of first ";"
        String fragment=null;//Holds current fragment
        String maxFragment=null;//Stores list of concatenated fragments
        String nextFragment=null;//Holds next fragment being compared with
        Iterator itr;//To iterate over fragments list
        int rightOverlap=0;//Number of letters overlapping on right of maximum fragment
        String rightString=null;//Overlapping String on right of maximum fragment
        String leftString=null;//Overlapping String on left of maximum fragment
        int leftOverlap=0;//Number of letters overlapping on left of maximum fragment
        
        //Only one ";" found and it is at the end of the String
        if(searchNext==input.length()){
        	input=input.replace(";","");//Remove lone ";" instance and return String
        	return input;
        }
        else{
        	while(searchNext>=0 && searchNext<=input.length()){
        		fragment=input.substring(searchStart, searchNext);
        		fragments.add(fragment);
                searchStart=searchNext+1; //Start point for finding instances of ";"
                searchNext=input.indexOf(";",searchStart);
        	}
        	//No more ";" could be found so ensure we are getting the last section of String
        	if(searchNext<0){
        		fragment=input.substring(searchStart, input.length());
        		fragments.add(fragment);
        	}
        }
        Collections.sort(fragments, sorted);//sort list by size of Strings desc 
        itr=fragments.iterator();
        maxFragment=(String)itr.next();//Retrieve largest fragment to start
        fragments.remove(maxFragment);//Remove from list
        itr=fragments.iterator();
        
        //Loop through list of fragments to merge overlapping segments together
        while(itr.hasNext()){
        	nextFragment=(String)itr.next();
        	
        	if(maxFragment.contains(nextFragment)){
        		//Largest fragment contains this one so just remove from list
        		fragments.remove(nextFragment);
        		itr=fragments.iterator();//reinitialise iterator
        	}
        	else
        	{
        		//Check for overlap on the right side of maximum fragment
        		searchStart=nextFragment.length(); 
        		for(int i=searchStart-1; i>0; i--){
        			searchNext=maxFragment.length();
        			if(maxFragment.substring(searchNext-i, searchNext)
        					.equals(nextFragment.substring(0,i))){
        				rightOverlap=i;
        				rightString=nextFragment.substring(0,i);
        			}
        		}
        		//Check for overlap on the right side of maximum fragment
        		searchStart=nextFragment.length();     		
        		for(int i=1; i<nextFragment.length(); i++){
        			searchNext=searchStart-i;
        			if(maxFragment.substring(0,searchNext).equals(nextFragment.substring(i,searchStart))){
        				leftOverlap=searchStart-i;
        				leftString=nextFragment.substring(i,searchStart);
        			}
        		}
        		//Either right or left with more than one letter overlapped found, otherwise keep fragment in list
        		if(rightOverlap>1 || leftOverlap>1){
        			//Overlap on right hand side of maximum fragment
        			if(rightOverlap>leftOverlap){
        				maxFragment=maxFragment+nextFragment.substring(rightOverlap);
        			}
        			else
        			{//Overlap on left hand side of maximum fragment
        				maxFragment=nextFragment.substring(0,nextFragment.length()-leftOverlap)+maxFragment;
        			}
        			fragments.remove(nextFragment);//Remove fragment from list as merged
                	itr=fragments.iterator();//reinitialise iterator
        		}	
        	}
        	//Clear down between iterations
        	leftOverlap=0;
        	rightOverlap=0;
        	rightString=null;
        	leftString=null;
        }
        return maxFragment;
    }
	
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader(args[0]))) {
            String fragmentProblem;
            while ((fragmentProblem = in.readLine()) != null) {
                System.out.println(reassemble(fragmentProblem));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
