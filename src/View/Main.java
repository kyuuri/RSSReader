package View;
import Controller.RSSReader;


public class Main {
	public static void main(String[] args){
		RSSReader rssR = new RSSReader();
		UI ui = new UI(rssR);
		ui.run();
	}
}
