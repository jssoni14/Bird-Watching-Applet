//Practice final

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class PFinalMain extends JFrame implements ActionListener, ListSelectionListener

{
	private static final int WIDTH = 760;
	private static final int HEIGHT = 380;
	
	private JLabel lblObservers;
	private JLabel lblObservations, lblQuantity;
	
	private JList<String> lstObservers;
	private JList<CObservation> lstObservations;
	
	private JScrollPane paneIn;
	private JScrollPane paneOut;
	
		private ButtonGroup grp;
	private JRadioButton[] rad = new JRadioButton[6];
	private ImageIcon[] ii = new ImageIcon[6];
	private String[] names = new String[10];
	private App AppMgr = new App();
	private JTextField tfQuantity;
	private JButton btnBird, btnAddObserver, btnAddObservation, btnExit, btnDisplay, btnDisplayAll;
	
	
	public PFinalMain() {} //constructor
		
	private void do_gui()
	{
		this.setTitle("Bird Watching");
		this.setSize(WIDTH, HEIGHT);
		
		Container c = this.getContentPane();
		c.setLayout(null);
		
		lblObservers = new JLabel("Observers");
		lblObservers.setBounds(20,10,150,30);
		c.add(lblObservers);
		
		lstObservers = new JList<String>();
		paneIn = new JScrollPane(lstObservers,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		paneIn.setBounds(20,40,100,150);
		lstObservers.addListSelectionListener(this);
		c.add(paneIn);
		
		lblObservations = new JLabel("Observations");
		lblObservations.setBounds(140,10,150,30);
		c.add(lblObservations);
		
		lstObservations = new JList<CObservation>();
		paneOut = new JScrollPane(lstObservations,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		paneOut.setBounds(140,40,250,150);
		c.add(paneOut);
		
		grp = new ButtonGroup();
		
		for (int j = 0; j < 6; j++)
		{
			rad[j] = new JRadioButton(CObservation.birds[j]);
			rad[j].setBounds(430, 20 + j*40, 100,30);
			grp.add(rad[j]);
			rad[j].addActionListener(this);
			c.add(rad[j]);
		}
		rad[0].setSelected(true);
		
		btnBird = new JButton();
		btnBird.setBounds(530,40,180,180);
		c.add(btnBird);
				
		btnAddObserver = new JButton("Add observer");
		btnAddObserver.setBounds(20, 210, 130,30);
		c.add(btnAddObserver);
		btnAddObserver.addActionListener(this);
		
		btnAddObservation = new JButton("Add observation");
		btnAddObservation.setBounds(170, 210, 140,30);
		c.add(btnAddObservation);
		btnAddObservation.addActionListener(this);
		
		lblQuantity = new JLabel("Quantity", SwingConstants.RIGHT);
		lblQuantity.setBounds(190,250,60,30);
		c.add(lblQuantity);
		
		tfQuantity = new JTextField();
		tfQuantity.setBounds(260,250,50,30);
		c.add(tfQuantity);
		
		btnDisplay = new JButton("Display by name");
		btnDisplay.setBounds(20, 250, 130,30);
		c.add(btnDisplay);
		btnDisplay.addActionListener(this);
		
		btnDisplayAll = new JButton("Display All");
		btnDisplayAll.setBounds(20, 290, 130,30);
		c.add(btnDisplayAll);
		btnDisplayAll.addActionListener(this);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(590, 250, 60,30);
		c.add(btnExit);
		btnExit.addActionListener(this);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) 
	{
	PFinalMain gm = new PFinalMain();
	gm.do_gui();
	gm.do_initialize();
	
	}//end main

	private void do_initialize()
	{
		BufferedImage i = null;
		String s = null;
		for (int j=0; j<6;j++)
		{
			s = "images/pic" + j + ".jpg";
			try{
			i = ImageIO.read(new File(s));
			ii[j] = new ImageIcon(i);
			}
			catch (Exception e){
				System.out.println(e);
			}
		}
		AppMgr.readData();
		display();
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		if (e.getSource()==btnAddObserver) do_addObserver();
		else if (e.getSource()==btnAddObservation) do_addObservation();
		else if (e.getSource()==btnExit) do_Exit();
		else if (e.getSource()==btnDisplay) do_displaybyName();
		else if (e.getSource()==btnDisplayAll) displayAll();
		else{
			for(int count=0; count<6; count++)
				if(rad[count].isSelected())
					do_Birds(count);
		}
			
	}
	
	@Override
	public void valueChanged(ListSelectionEvent arg0) {}
	
	private void do_Birds(int whichBird){
		btnBird.setIcon(ii[whichBird]);
			
	}
	
	private void do_addObserver(){
		String name;
		name=JOptionPane.showInputDialog("Please enter name");
		if(name==null || name.isEmpty())
			JOptionPane.showMessageDialog(null, "Please enter valid name");
		if(AppMgr.addName(name)==false)
			JOptionPane.showMessageDialog(null, "Duplicate Name");
		display();
		
	}
	
	private void do_addObservation(){
		int q;
		if(lstObservers.getSelectedIndex()<0)
			JOptionPane.showMessageDialog(null, "Please select name");
		else{
			String name = lstObservers.getSelectedValue();
			q=Integer.parseInt(tfQuantity.getText());
			if(q<0)
				JOptionPane.showMessageDialog(null, "Please enter valid quantity");
			AppMgr.AddObservation(name, getBirdIndex(), q);
			display();
		}
		
	}
	
	private void do_displaybyName(){
		if(lstObservers.getSelectedIndex()<0)
			JOptionPane.showMessageDialog(null, "Please pick observer");
		else{
			String name = lstObservers.getSelectedValue();
			lstObservations.setListData(AppMgr.getbyNames(name));
		}
		
		}
	
	private void displayAll(){
		display();
		
	} 
	
	private void do_Exit()
	{
		AppMgr.writeData();
		System.exit(0);
	}
	private void display(){
		lstObservers.setListData(AppMgr.getNames());
		lstObservations.setListData(AppMgr.getObservation());
		
	}
	private int getBirdIndex(){
		for(int count=0; count<6; count++)
			if(rad[count].isSelected())
				return count;
			
		return 99;
		
	}
}//end class

