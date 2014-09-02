package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.RSSReader;
import Model.Item;

/**
 * Class for the user interface. User can input url and enter or choose
 * 
 * @author Sarathit Sangtaweep 5510546182
 */
public class UI extends JFrame {

	/** attributes */

	/** rss reader */
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

	private JLabel titleChannelStr;
	private JLabel urlStr;
	private JLabel titleItemStr;

	private JTextArea information;
	private JList itemList;
	private JScrollPane itemListPane;
	private JScrollPane informationPane;

	/**
	 * Constructor of the class
	 * 
	 * @param rss
	 *            RSSReader for reading rss.
	 */
	public UI(RSSReader rss) {
		this.rssReader = rss;
		initComponent();
	}

	/**
	 * Initialize the components of the user interface.
	 */
	private void initComponent() {
		panel = new JPanel(new BorderLayout());

		innerTopPanelUp = new JPanel();
		innerTopPanelUp.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		strInput = new JLabel("URL : ");
		strInput.setFont(new Font("Arial", Font.BOLD, 16));
		innerTopPanelUp.add(strInput);

		// default url
		// urlStr = new JLabel("<html>Current URL : None" +
		// colorText("http://feeds.bbci.co.uk/news/rss.xml", "red") +
		// "</html>");
		urlStr = new JLabel("Current URL : None");
		urlStr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		urlStr.setHorizontalAlignment(JLabel.CENTER);

		titleItemStr = new JLabel("Title : None");
		titleItemStr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		titleItemStr.setHorizontalAlignment(JLabel.CENTER);

		// titleChannelStr = new JLabel("  " +
		// rssReader.getRSS().getChannel().getTitle());
		titleChannelStr = new JLabel("  Channel's Name");
		titleChannelStr.setFont(new Font(null, Font.BOLD, 30));

		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout(0, 0));

		innerTopPanelDown = new JPanel();
		innerTopPanelDown.setLayout(new GridLayout(2, 2));
		innerTopPanelDown.add(titleChannelStr);
		innerTopPanelDown.add(new JLabel());
		innerTopPanelDown.add(urlStr);
		innerTopPanelDown.add(titleItemStr);

		inputField = new JTextField();

		innerTopPanelUp.add(inputField);

		inputField.setColumns(30);
		inputField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		inputField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					readURL();
				}
			}
		});

		btnRead = new JButton("READ");
		btnRead.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				readURL();
			}
		});
		innerTopPanelUp.add(btnRead);

		centerPanel = new JPanel();

		// itemList = new JList( rssReader.getArrayItem() );
		itemList = new JList();
		itemList.setFont(new Font(null, Font.BOLD, 14));
		itemListPane = new JScrollPane(itemList);

		information = new JTextArea();
		information.setEditable(false);
		information.setLineWrap(true);
		information.setWrapStyleWord(true);
		information.setFont(new Font("Tahoma", Font.PLAIN, 16));

		informationPane = new JScrollPane(information);

		itemListPane.setPreferredSize(new Dimension(500, 400));
		informationPane.setPreferredSize(new Dimension(500, 400));

		itemList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {

				Item item = (Item) (itemList.getSelectedValue());
				if(item != null){
					information.setText(item.getDesc());
					titleItemStr.setText("<html>Title : " + colorText(item.getTitle(), "blue") + "</html>");
					link = item.getLink();
				}
			}
		});

		linkText = new JLabel("More detail...");
		linkText.setFont(new Font(null, Font.BOLD, 16));
		linkText.setHorizontalAlignment(JLabel.CENTER);
		linkText.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
				if(link.equals("")){
					JOptionPane.showMessageDialog(null,"Currently no more detail","Error",JOptionPane.ERROR_MESSAGE);
				}
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
			public void mouseExited(MouseEvent e) {
				linkText.setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				linkText.setCursor(new Cursor(Cursor.HAND_CURSOR));
				linkText.setForeground(Color.BLUE);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		bottomPanel = new JPanel(new GridLayout(2, 2));
		bottomPanel.add(new JLabel());
		bottomPanel.add(linkText);
		bottomPanel.add(new JLabel());
		bottomPanel.add(new JLabel());

		topPanel.add(innerTopPanelUp, BorderLayout.NORTH);
		topPanel.add(innerTopPanelDown, BorderLayout.SOUTH);

		centerPanel.add(itemListPane, BorderLayout.WEST);
		centerPanel.add(informationPane, BorderLayout.EAST);

		panel.add(topPanel, BorderLayout.NORTH);
		panel.add(centerPanel, BorderLayout.CENTER);
		panel.add(bottomPanel, BorderLayout.SOUTH);

		this.getContentPane().add(panel);

	}

	/**
	 * Run the user interface.
	 */
	public void run() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
	}

	/**
	 * Paint the text with the input color.
	 * 
	 * @param text
	 *            text to be colored.
	 * @param color
	 *            color of the text.
	 * @return the string which is in html form for colored text.
	 */
	public String colorText(String text, String color) {
		return "<font color=\"" + color + "\">" + text + "</font>";
	}

	/**
	 * Use RSSReader to read the URL and update the user interface.
	 */
	public void readURL() {
		URL url = null;
		try {
			url = new URL(inputField.getText());
			rssReader.setUrl(url);
			rssReader.readRSS();
			itemList.setListData(rssReader.getArrayItem());
			information.setText("");
			titleItemStr.setText("Title : None");
			titleChannelStr.setText("  " + rssReader.getRSS().getChannel().getTitle());
			urlStr.setText("<html>Current URL : "+ colorText(url.toString(), "red") + "</html>");
			link = "";
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(null,"Invalid URL! Please try again", "Error",JOptionPane.ERROR_MESSAGE);
		}
	}

}
