package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
/**
 * Class for item.
 * 
 * @author Sarathit Sangtaweep 5510546182
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
	
	/**attributes*/
	private String title;
	private String link;
	private String description;
	
	/**constructor of the class*/
	public Item(){}
	
	/**
	 * Get the item's title.
	 * @return item's title.
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Get the item's link.
	 * @return item's link.
	 */
	public String getLink() {
		return link;
	}
	
	/**
	 * Get the item's description.
	 * @return item's description.
	 */
	public String getDesc() {
		return description;
	}
	
	@Override
	public String toString(){
		return this.getTitle();
	}
	
}
