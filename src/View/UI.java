package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.RSSReader;
import Model.Item;

public class UI extends JFrame{
	
	private RSSReader rssReader;
	private JPanel panel;
	private JPanel topPanel;
	private JPanel innerTopPanelUp;
	private JPanel innerTopPanelDown;
	
	private JLabel strInput;
	private JTextField inputField;
	private JButton btnRead;
	private JPanel centerPanel;
	
	private JPanel bottomPanel;
	private JLabel linkText;
	private String link = "";
	
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
		
		strInput = new JLabel("URL : ");
		strInput.setFont( new Font("Arial", Font.BOLD, 16));
		innerTopPanelUp.add(strInput);
		
		//default url
		urlStr = new JLabel("<html>Current URL : " + colorText("http://feeds.bbci.co.uk/news/rss.xml", "red") + "</html>");
		urlStr.setFont( new Font("Tahoma", Font.PLAIN, 16));
		urlStr.setHorizontalAlignment(JLabel.CENTER);
		
		titleStr = new JLabel("Title : None");
		titleStr.setFont( new Font("Tahoma", Font.PLAIN, 16) );
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
				urlStr.setText("<html>Current URL : " + colorText(url.toString(), "red") + "</html>");
			}
		});
		innerTopPanelUp.add(btnRead);
		
		centerPanel = new JPanel();
		
		itemList = new JList( rssReader.getArrayItem() );
		itemList.setFont( new Font(null, Font.BOLD, 14) );
		itemListPane = new JScrollPane(itemList);
		
		information = new JTextArea();
		setInformationArea();
		
		informationPane = new JScrollPane(information);
		
		itemListPane.setPreferredSize( new Dimension(500,400) );
		informationPane.setPreferredSize( new Dimension(500,400) );
		
		itemList.addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				if(e.getValueIsAdjusting()){
					Item item = (Item)(itemList.getSelectedValue());
					information.setText(item.getDesc());
					
					titleStr.setText("<html>Title : " + colorText(item.getTitle(), "blue") + "</html>");
					link = item.getLink();
				}
				
			}
		});
		
		linkText = new JLabel("More detail...");
		linkText.setFont( new Font(null, Font.BOLD, 16));
		linkText.setHorizontalAlignment(JLabel.CENTER);
		linkText.addMouseListener( new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (Desktop.isDesktopSupported() && !link.equals("")) {
					Desktop desktop = Desktop.getDesktop();
					try {
						desktop.browse(new URI(link));
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				linkText.setCursor( new Cursor(Cursor.HAND_CURSOR) );
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		
		bottomPanel = new JPanel( new GridLayout(2,2));
		bottomPanel.add( new JLabel() );
		bottomPanel.add(linkText);
		bottomPanel.add( new JLabel() );
		bottomPanel.add( new JLabel() );
		
		topPanel.add(innerTopPanelUp,BorderLayout.NORTH);
		topPanel.add(innerTopPanelDown,BorderLayout.SOUTH);

		centerPanel.add(itemListPane,BorderLayout.WEST);
		centerPanel.add(informationPane, BorderLayout.EAST);
		
		panel.add(topPanel, BorderLayout.NORTH);
		panel.add(centerPanel, BorderLayout.CENTER);
		panel.add(bottomPanel, BorderLayout.SOUTH);

		this.getContentPane().add(panel);

	}
	
	public void run(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
		System.out.println("Runn");
	}
	
	public void setInformationArea(){
		information.setEditable(false);
		information.setLineWrap(true);
		information.setWrapStyleWord(true);
		information.setFont( new Font("Tahoma",Font.PLAIN,16));
	}
	
	public String colorText(String text , String color){
		return "<font color=\"" + color + "\">" + text + "</font>";
	}
	
	
}
