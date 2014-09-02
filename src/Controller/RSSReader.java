package Controller;

/**
 * RSSReader class, use for reading rss from url.
 * 
 * @author Sarathit Sangtaweep 5510546182
 */
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import Model.Item;
import Model.RSS;
public class RSSReader {
	
	/** attributes */
	private URL url;
	private RSS rss;

	/**constructor of the class*/
	public RSSReader(){}
	
	/**
	 * Unmarshal or read the RSS from the current url;
	 * @return RSS from the current url.
	 */
	public RSS readRSS(){
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(RSS.class);
			Unmarshaller um = context.createUnmarshaller();
			if(url == null){
				url = new URL("http://feeds.bbci.co.uk/news/rss.xml");
			}
			Object obj = um.unmarshal( url );
			rss = (RSS)obj;
			
			return rss;
			
		} catch (JAXBException | MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Set the current url.
	 * @param url to be set.
	 */
	public void setUrl(URL url) {
		this.url = url;
	}
	
	/**
	 * Convert the list of items from RSS to items array.
	 * @return array of items in the RSS.
	 */
	public Item[] getArrayItem(){
		List<Item> items = this.getRSS().getChannel().getItems();
		Item[] itemArray = new Item[items.size()];
		
		items.toArray(itemArray);
		return itemArray;
	}
	
	/**
	 * Get RSS. If rss is null, the readRSS will be called.
	 * @return current RSS.
	 */
	public RSS getRSS(){
		if(rss == null)
			this.readRSS();
		return rss;
	}
}
