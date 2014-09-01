import Controller.RSSReader;
import View.UI;


public class Main {
	public static void main(String[] args){
		RSSReader rssR = new RSSReader();
		UI ui = new UI(rssR);
		ui.run();
	}
}
