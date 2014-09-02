package Model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * Class for RSS.
 * 
 * @author Sarathit Sangtaweep 5510546182
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RSS {
	
	/**attribute*/
	private Channel channel;

	/**construcot of the class*/
	public RSS(){}
	
	/**
	 * Get the rss' channel.
	 * @return rss' channel.
	 */
	public Channel getChannel() {
		return channel;
	}
}
