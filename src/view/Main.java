package view;
import controller.RSSReader;

/**
 * Main class for running and creating GUI and RSSReader.
 * 
 * @author Sarathit Sangtaweep 5510546182
 */
public class Main {
	
	/**
	 * Main method for starting the program.
	 * 
	 * @param args command line argument.
	 */
	public static void main(String[] args){
		RSSReader rssR = new RSSReader();
		RSSReaderView ui = new RSSReaderView(rssR);
		ui.run();
	}
}
