package Model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
/**
 * Class for channel.
 * 
 * @author Sarathit Sangtaweep 5510546182
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Channel {
	
	/**attributes*/
	private String title;
	private String link;
	private String description;
	@XmlElement( name = "item" )
	private List<Item> items;
	
	/**constructor of the class*/
	public Channel(){}
	
	/**
	 * Get the channel's title.
	 * @return title of the channel.
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Get the channel's link.
	 * @return link of the channel.
	 */
	public String getLink() {
		return link;
	}
	
	/**
	 * Get the channel's description.
	 * @return description of the channel.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Get the list of items in the channel.
	 * @return list of items in the channel.
	 */
	public List<Item> getItems() {
		return items;
	}
}
