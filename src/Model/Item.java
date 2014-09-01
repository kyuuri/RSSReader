package Model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
	
	private String title;
	private String link;
	private String description;
	
	public Item(){}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getDesc() {
		return description;
	}
	
	public void setDesc(String description) {
		this.description = description;
	}
	
	public String toString(){
		return this.getTitle();
	}
	
}
