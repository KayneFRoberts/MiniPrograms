/**
 * 
 */
package singlyLinkedList;

/**
 * @author Kayne
 *
 */
public class SinglyLinkedList {
	private Node headNode;
	private Node currentNode;
	private Node newNode;
	private Node secondNewNode;
	private int listSize = 0;

	/**
	 * Constructor to create null head of list
	 **/
	public SinglyLinkedList() {
		headNode = new Node(null);
	}

	/**
	 * @param Object
	 *            data - add new object to end of list
	 **/
	public void addListItem(Object data) {
		newNode = new Node(data);// Temporary data node
		currentNode = headNode;// Set temporarily to start of list

		if (headNode.getData() == null) {
			// Head node not populated yet, add to head
			headNode = newNode;
		} else {// Search through list to get to end so we can point to new node
			while (currentNode.getNext() != null) {
				currentNode = currentNode.getNext();
			}
		}
		currentNode.setNext(newNode);// Set pointer for node before the one at
										// end of list just added
		listSize++;
	}

	/**
	 * @param Object
	 *            data - add new object to end of list
	 * @param int
	 *            index - index this should be added to
	 **/
	public void addListItem(Object data, int index) {
		newNode = new Node(data);// Temporary data node
		currentNode = headNode;// Set temporarily to start of list

		if (index == 1) {// Replace head
			secondNewNode = new Node(headNode.getData());
			headNode.setData(newNode.getData());
			newNode = secondNewNode;
		}
		// Search through list to index point
		for (int i = 1; i < index && currentNode.getNext() != null; i++) {
			currentNode = currentNode.getNext();
		}
		newNode.setNext(currentNode.getNext());// Set new Node's pointer to next
												// node
		currentNode.setNext(newNode);// Set current Node's reference to new node
										// setup
		listSize++;
	}

	/**
	 * @param int
	 *            index - Index of item to retrieve
	 * @return Object - object at index in list
	 **/
	public Object getListItem(int index) {
		if (index <= 0) {
			// index is invalid, return null
			return null;
		}
		currentNode = headNode;// Set temporarily to start of list
		// Loop through list until hit index
		for (int i = 1; i < index; i++) {
			if (currentNode.getNext() == null) {
				// Object at index is null
				return null;
			}
			currentNode = currentNode.getNext();// Set current node to next
												// pointer
		}
		return currentNode.getData();// Now at correct index retrieve data
	}

	/**
	 * @param int
	 *            index - index to remove from list
	 * @return boolean - returns true or false as to whether removed
	 **/
	public boolean removeListItem(int index) {
		if (index <= 0) {
			// index is invalid, return false
			return false;
		}
		currentNode = headNode;// Set temporarily to start of list

		if (index == 1) {// Remove head node
			headNode = currentNode.getNext();
			headNode.setNext(currentNode.getNext().getNext());
			listSize--;
			return true;
		}

		// Loop through list till at index
		for (int i = 1; i < index - 1; i++) {
			if (currentNode.getNext() == null) {
				return false;// No next pointer, return null
			}
			currentNode = currentNode.getNext();// Point to next node
		}
		currentNode.setNext(currentNode.getNext().getNext());// Set next node
																// pointer to
																// skip one
		listSize--;
		return true;// Removed, return true
	}

	/**
	 * @return int - returns dynamically updated size of list
	 **/
	public int getSize() {
		return listSize;
	}

	/**
	 * Print list to console
	 **/
	public void outputList() {
		currentNode = headNode;// Reset current node to head of list

		// Loop through list until current node is null
		while (true) {
			if (currentNode == null) {
				break;
			}
			System.out.println(currentNode.getData());
			currentNode = currentNode.getNext();
		}
	}
}
