/**
 * 
 */
package singlyLinkedList;

/**
 * @author Kayne
 *
 */
public class Node {

	private Node nextNode;
	private Object nodeData;
	
	/**
	 * @param Object nodeData - Data for node 
	 * Constructor where next node pointer not known
	 **/
	public Node (Object nodeData){
		this.nodeData=nodeData;
		this.nextNode=null;
	}
	
	/**
	 * @param Object nodeData - Data for node 
	 * @param Node nextNode - Next node to point to in list
	 * Constructor where data and next node pointer known
	 **/
	public Node (Object nodeData, Node nextNode){
		this.nodeData=nodeData;
		this.nextNode=nextNode;
	}
	
	/**
	 * @return Object nodeData - Returns data for current node
	 **/
	public Object getData()
	{
		return nodeData;
	}
	
	/**
	 * @param Object nodeData - Sets data for current node 
	 **/
	public void setData(Object nodeData)
	{
		this.nodeData = nodeData;
	}
	
	/**
	 * @return Node nextNode - returns next node
	 **/
	public Node getNext()
	{
		return nextNode;
	}
	
	/**
	 * @param Node nextNode - sets next node to point to
	 **/
	public void setNext(Node nextNode)
	{
		this.nextNode=nextNode;
	}
}
