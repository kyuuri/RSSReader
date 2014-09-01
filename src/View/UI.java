package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

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
	private JTextField textField;
	private JPanel panel;
	private JPanel topPanel;
	private JButton btnRead;
	private JPanel centerPanel;
	private JTextArea information;
	
	public UI(RSSReader rssReader){
		
		this.rssReader = rssReader;
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel( new BorderLayout() );
		
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textField = new JTextField();
		
		topPanel.add(textField);
		textField.setColumns(10);
		
		btnRead = new JButton("READ");
		topPanel.add(btnRead);
		
		centerPanel = new JPanel();
		
		final JList jList = new JList( rssReader.getArrayItem() );
		JScrollPane jscroll = new JScrollPane(jList);
		
		information = new JTextArea();
		JScrollPane jdata = new JScrollPane(information);
		
		jdata.setPreferredSize( new Dimension(300,300));
		jList.addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				if(e.getValueIsAdjusting()){
					Item item = (Item)(jList.getSelectedValue());
					information.setText(item.getDesc());
					information.setLineWrap(true);
					//System.out.println(item.getDesc());
				}
				
			}
		});

		centerPanel.add(jscroll,BorderLayout.WEST);
		centerPanel.add(jdata, BorderLayout.EAST);
		
		panel.add(topPanel, BorderLayout.NORTH);
		panel.add(centerPanel, BorderLayout.CENTER);
		this.getContentPane().add(panel);

	}
	
	public void run(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		System.out.println("Runn");
	}
	
	
}
