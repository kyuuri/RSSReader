package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.RSSReader;
import Model.Item;

public class UI extends JFrame{
	
	private RSSReader rssReader;
	private JTextField inputField;
	private JPanel panel;
	private JPanel topPanel;
	private JPanel innerTopPanelUp;
	private JPanel innerTopPanelDown;
	private JButton btnRead;
	private JPanel centerPanel;
	
	private JLabel urlStr;
	private JLabel titleStr;
	
	private JTextArea information;
	final private JList itemList;
	private JScrollPane itemListPane;
	private JScrollPane informationPane;
	
	public UI(RSSReader rss){
		
		this.rssReader = rss;
		
		panel = new JPanel( new BorderLayout() );
		
		innerTopPanelUp = new JPanel();
		innerTopPanelUp.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//default url
		urlStr = new JLabel("<html>Current URL : " + colorText("http://feeds.bbci.co.uk/news/rss.xml") + "</html>");
		urlStr.setHorizontalAlignment(JLabel.CENTER);
		
		titleStr = new JLabel("Title : None");
		titleStr.setHorizontalAlignment(JLabel.CENTER);
		
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout(0, 0));
		
		innerTopPanelDown = new JPanel();
		innerTopPanelDown.setLayout(new GridLayout(1,2));
		innerTopPanelDown.add(urlStr);
		innerTopPanelDown.add(titleStr);
		
		inputField = new JTextField();
		
		innerTopPanelUp.add(inputField);
		
		inputField.setColumns(30);
		inputField.setFont( new Font("Tahoma", Font.PLAIN, 18));
		
		btnRead = new JButton("READ");
		btnRead.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				URL url = null;
				try {
					url = new URL(inputField.getText());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				System.out.println(url);
				rssReader.setUrl(url);
				rssReader.readRSS();
				
				itemList.setListData( rssReader.getArrayItem() );
				information.setText("");
				titleStr.setText("Title : None");
				urlStr.setText("<html>Current URL : " + colorText(url.toString()) + "</html>");
			}
		});
		innerTopPanelUp.add(btnRead);
		
		centerPanel = new JPanel();
		
		itemList = new JList( rssReader.getArrayItem() );
		itemListPane = new JScrollPane(itemList);
		
		information = new JTextArea();
		setInformationArea();
		
		informationPane = new JScrollPane(information);
		
		itemListPane.setPreferredSize( new Dimension(400,300) );
		informationPane.setPreferredSize( new Dimension(400,300) );
		
		itemList.addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				if(e.getValueIsAdjusting()){
					Item item = (Item)(itemList.getSelectedValue());
					information.setText(item.getDesc());
					information.setLineWrap(true);
					
					titleStr.setText("<html>Title : " + colorText(item.getTitle()) + "</html>");
				}
				
			}
		});
		
		topPanel.add(innerTopPanelUp,BorderLayout.NORTH);
		topPanel.add(innerTopPanelDown,BorderLayout.SOUTH);

		centerPanel.add(itemListPane,BorderLayout.WEST);
		centerPanel.add(informationPane, BorderLayout.EAST);
		
		panel.add(topPanel, BorderLayout.NORTH);
		//panel.add()
		panel.add(centerPanel, BorderLayout.CENTER);
		this.getContentPane().add(panel);

	}
	
	public void run(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		System.out.println("Runn");
	}
	
	public void setInformationArea(){
		information.setEditable(false);
		information.setWrapStyleWord(true);
		information.setFont( new Font("Tahoma",Font.PLAIN,16));
	}
	
	public String colorText(String text){
		return "<font color=\"blue\">" + text + "</font>";
	}
	
	
}
