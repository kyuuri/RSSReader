package Controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import Model.Item;
import Model.RSS;
public class RSSReader {
	
	private URL url;

	public RSSReader(){}
	
	public RSS readRSS(){
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(RSS.class);
			Unmarshaller um = context.createUnmarshaller();
			if(url == null){
				url = new URL("http://feeds.bbci.co.uk/news/rss.xml");
			}
			Object obj = um.unmarshal( url );
			RSS rss = (RSS)obj;
			
			return rss;
			
		} catch (JAXBException | MalformedURLException e) {
			e.printStackTrace();
		}
		System.out.println("null");
		return null;
	}
	
	public void setUrl(URL url) {
		this.url = url;
	}
	
	public Item[] getArrayItem(){
		List<Item> items = this.readRSS().getChannel().getItems();
		Item[] itemArray = new Item[items.size()];
		
		items.toArray(itemArray);
		return itemArray;
	}
}
