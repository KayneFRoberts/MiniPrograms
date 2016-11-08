/**
 * 
 */
package singlyLinkedList.Tests;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import singlyLinkedList.SinglyLinkedList;

/**
 * @author Kayne
 *
 */
public class SinglyLinkedListTests {

	private SinglyLinkedList newList;
	private String test1="Test 1";
	private String test2="Test 2";
	private String test3="Test 3";
	private String test4="Test 4";
	
	@Test
	public void setupList() {
		newList= new SinglyLinkedList();
		newList.addListItem(test1);
		newList.addListItem(test2);
		assertEquals("First item in list is "+test1+" as expected.", test1, newList.getListItem(1));
		assertEquals("Second item in list is "+test2+" as expected.", test2, newList.getListItem(2));
	}

	@Test
	public void removeFromList() {
		newList= new SinglyLinkedList();
		newList.addListItem(test1);
		newList.addListItem(test2);
		newList.addListItem(test3);
		newList.removeListItem(2);
		assertEquals("First item in list is "+test1+" as expected.", test1, newList.getListItem(1));
		assertEquals("Second item in list is "+test3+" as expected.", test3, newList.getListItem(2));
	}
	
	@Test
	public void addAndRemoveFromList() {
		newList= new SinglyLinkedList();
		newList.addListItem(test1);
		newList.addListItem(test2);
		newList.addListItem(test3);
		newList.addListItem(test4);
		newList.removeListItem(1);
		assertEquals("First item in list is "+test2+" as expected.", test2, newList.getListItem(1));
		newList.removeListItem(2);
		assertEquals("Second item in list is "+test4+" as expected.", test4, newList.getListItem(2));
	}
	
	@Test
	public void sizeOfList() {
		newList= new SinglyLinkedList();
		newList.addListItem(test1);
		newList.addListItem(test2);
		newList.addListItem(test3);
		newList.addListItem(test4);
		newList.outputList();
		assertEquals("Size of list is created as expected.", 4, newList.getSize());
		newList.removeListItem(1);
		newList.outputList();
		assertEquals("Size of list is created as expected.", 3, newList.getSize());
		newList.addListItem(test1, 1);
		assertEquals("Size of list is created as expected.", 4, newList.getSize());
		newList.outputList();
	}
}
