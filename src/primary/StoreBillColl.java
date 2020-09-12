package primary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class StoreBillColl extends JPanel 
{
	//flags to identify which billtypes (retail/grocer) + (individual/daily/monthly) collection to show
	static boolean BillCollSelectionRetail = false;	//false = grocer | true = retail
	static int BillCollSelectionReport = 0;			//0 = indiv | 1 = daily | 2 = monthly
	
	//contents of bill collections jpanel
	private static JPanel pnl_BillCollContents;
	private static final long serialVersionUID = 777077199766318551L;
	StoreBillColl()
	{
		//**generate and configure interfaces
		JButton btn_BillCollAccess = new JButton("Access");
		this.setBackground(new Color(46, 139, 87));
		GridBagLayout gbl_pnl_BillColl = new GridBagLayout();
		gbl_pnl_BillColl.columnWidths = new int[]{0, 0};
		gbl_pnl_BillColl.rowHeights = new int[]{0, 0, 0};
		gbl_pnl_BillColl.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnl_BillColl.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		this.setLayout(gbl_pnl_BillColl);
		GridBagConstraints gbc_scrl_BillColl = new GridBagConstraints();
		gbc_scrl_BillColl.fill = GridBagConstraints.BOTH;
		gbc_scrl_BillColl.insets = new Insets(5, 5, 0, 0);
		gbc_scrl_BillColl.gridx = 0;
		gbc_scrl_BillColl.gridy = 1;
		JPanel pnl_BillCollControl = new JPanel();
		pnl_BillCollControl.setBackground(new Color(72, 209, 204));
		pnl_BillCollControl.setVisible(false);
		GridBagConstraints gbc_pnl_BillCollControl = new GridBagConstraints();
		gbc_pnl_BillCollControl.fill = GridBagConstraints.BOTH;
		gbc_pnl_BillCollControl.insets = new Insets(5, 5, 5, 0);
		gbc_pnl_BillCollControl.gridx = 0;
		gbc_pnl_BillCollControl.gridy = 0;
		JScrollPane scrl_BillColl = new JScrollPane();
		scrl_BillColl.setVisible(false);
		pnl_BillCollContents = new JPanel();
		pnl_BillCollContents.setBackground(new Color(47, 79, 79));
		GridBagLayout gbl_pnl_BillCollContents = new GridBagLayout();
		gbl_pnl_BillCollContents.columnWidths = new int[]{0, 0};
		gbl_pnl_BillCollContents.rowHeights = new int[]{0, 0};
		gbl_pnl_BillCollContents.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnl_BillCollContents.rowWeights = new double[]{};
		pnl_BillCollContents.setLayout(gbl_pnl_BillCollContents);
		scrl_BillColl.setViewportView(pnl_BillCollContents);
		JButton btn_BillCollControlSwitchType = new JButton("Showing Grocer Bills");
		JButton btn_BillCollControlSwitchCategory = new JButton("Individual Bills");
		JPanel pnl_BillCollControlSearch = new JPanel();
		JLabel lbl_BillCollControlSearch = new JLabel("Search by Date");
		JComboBox<String> cmb_BillCollControlSearchYear = new JComboBox<String>();
		cmb_BillCollControlSearchYear.setEditable(true);
		cmb_BillCollControlSearchYear.setModel(new DefaultComboBoxModel<String>(getBillYearRange()));
		JComboBox<String> cmb_BillCollControlSearchMonth = new JComboBox<String>();
		cmb_BillCollControlSearchMonth.setEditable(true);
		cmb_BillCollControlSearchMonth.setEnabled(false);
		JComboBox<String> cmb_BillCollControlSearchDay = new JComboBox<String>();
		cmb_BillCollControlSearchDay.setEditable(true);
		cmb_BillCollControlSearchDay.setEnabled(false);
		JButton btn_BillCollControlDoSearch = new JButton("Search");
		JButton btn_BillCollControlReset = new JButton("Reset");
		btn_BillCollControlDoSearch.setEnabled(false);
		JButton btn_BillCollControlDel = new JButton("Delete");
		JButton btn_BillCollUnaccess = new JButton("Close tab");
		
		//Dialogpanel for selecting which bill to delete
		JPanel pnl_DialogBillCollDelete = new JPanel();
		JComboBox<String> cmb_DialogBillCollDelete = new JComboBox<String>();
		cmb_DialogBillCollDelete.setModel(new DefaultComboBoxModel<String>(getAllBills()));
		
		//** Add funtions to components
		//combobox selected trigger- year selected, find available month numbers and activate next combobox
		cmb_BillCollControlSearchYear.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//get the legal months in year folder
				String year = cmb_BillCollControlSearchYear.getSelectedItem().toString();
				ArrayList<String> legalmonths = new ArrayList<String>();
				File folder = BillCollSelectionRetail ? new File(StoreWindow.billfilesloc+"/Retail"+"/"+year) : new File(StoreWindow.billfilesloc+"/Grocer"+"/"+year);
				//scan every available year
				File[] yearsavail = folder.listFiles();
				//read the folder names as String
				if(folder.exists())
				{
					for(int i = 0; i < yearsavail.length; i++)
					{
						//place into arraylist
						if(yearsavail[i].isDirectory())
						{legalmonths.add((yearsavail[i].getName()));}
					}
				}
				//set those as month selection
				cmb_BillCollControlSearchMonth.setModel(new DefaultComboBoxModel<String>(legalmonths.toArray(new String[0])));
				//enable month combobox
				cmb_BillCollControlSearchMonth.setEnabled(true);
			}
		});
		//combobox selected trigger- month selected, find days available and activate next combobox
		cmb_BillCollControlSearchMonth.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//get the legal days in month folder
				String year = cmb_BillCollControlSearchYear.getSelectedItem().toString();
				String month = cmb_BillCollControlSearchMonth.getSelectedItem().toString();
				ArrayList<String> legaldays = new ArrayList<String>();
				File folder = BillCollSelectionRetail ? new File(StoreWindow.billfilesloc+"/Retail"+"/"+year+"/"+month) : new File(StoreWindow.billfilesloc+"/Grocer"+"/"+year+"/"+month);
				//scan every available year
				File[] dayssavail = folder.listFiles();
				//read the folder names as integer
				if(folder.exists())
				{
					for(int i = 0; i < dayssavail.length; i++)
					{
						//place into arraylist
						if(dayssavail[i].isDirectory())
						{legaldays.add((dayssavail[i].getName()));}
					}
				}
				//set those as day selection
				cmb_BillCollControlSearchDay.setModel(new DefaultComboBoxModel<String>(legaldays.toArray(new String[0])));
				//enable days combobox
				cmb_BillCollControlSearchDay.setEnabled(true);
			}
		});
		//combobox selected trigger- day selected, enable button
		cmb_BillCollControlSearchDay.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//enable search button
				btn_BillCollControlDoSearch.setEnabled(true);
			}
		});
		//button trigger- search bills that are relevant to combobox data
		btn_BillCollControlDoSearch.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//do search
				String year = cmb_BillCollControlSearchYear.getSelectedItem().toString();
				String month = cmb_BillCollControlSearchMonth.getSelectedItem().toString();
				String day = cmb_BillCollControlSearchDay.getSelectedItem().toString();

				//reset jpanel
				pnl_BillCollContents.removeAll();
				
				//lists
				ArrayList<JPanel> requiredPanels = new ArrayList<JPanel>();
				ArrayList<GridBagConstraints> requiredgbc = new ArrayList<GridBagConstraints>();
				ArrayList<String> requiredDates = new ArrayList<String>();
				ArrayList<String> requiredContent = new ArrayList<String>();
				
				File folder = BillCollSelectionRetail ? new File(StoreWindow.billfilesloc+"/Retail"+"/"+year+"/"+month+"/"+day) : new File(StoreWindow.billfilesloc+"/Grocer"+"/"+year+"/"+month+"/"+day);
				for(File bill : folder.listFiles())
				{
					//make sure not the daily report file
					if(!bill.getName().equals("DailyReport.txt") && !bill.getName().equals("DailyReport"))
					{
						String billtime = bill.getName().replace(".txt", "");
						StringBuilder datatext = new StringBuilder();
						//read file, add data into scrollpanel
						try
						{
							FileReader fileReader = new FileReader(bill);
				            BufferedReader bufferedReader = new BufferedReader(fileReader);
				            String line;
				            while((line = bufferedReader.readLine()) != null) 
				            {datatext.append(' ').append(line+"\n");}   
	
				            // Always close files.
				            bufferedReader.close();  
						}
						catch(Exception e)
						{e.printStackTrace();}
						//collect data
						requiredPanels.add(new JPanel());
						requiredgbc.add(new GridBagConstraints());
						requiredDates.add("Transaction made at: "+year+","+month+" "+day+" "+billtime.replaceAll("_", ":"));
						requiredContent.add(datatext.toString());
					}
				}
				
				//write data into a panel
				for(int index = 0; index < requiredPanels.size(); index++)
				{
					requiredPanels.get(index).setLayout(new BorderLayout(8,0));
					requiredPanels.get(index).add(new JTextField(requiredDates.get(index)),BorderLayout.NORTH);
					requiredPanels.get(index).add(new JTextArea(requiredContent.get(index)),BorderLayout.CENTER);
					requiredPanels.get(index).revalidate();
					requiredgbc.get(index).anchor = GridBagConstraints.NORTH;
					requiredgbc.get(index).insets = new Insets(10, 10, 10, 10);
					requiredgbc.get(index).fill = GridBagConstraints.NONE;
					requiredgbc.get(index).gridy = index;
					pnl_BillCollContents.add(requiredPanels.get(index),requiredgbc.get(index));
				}
				pnl_BillCollContents.revalidate();
				pnl_BillCollContents.repaint();
				
				//reset search components
				cmb_BillCollControlSearchMonth.setEnabled(false);
				cmb_BillCollControlSearchDay.setEnabled(false);
				btn_BillCollControlDoSearch.setEnabled(false);
			}
		});
		btn_BillCollControlReset.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				buildBillScrl();
				//reset search components
				cmb_BillCollControlSearchMonth.setEnabled(false);
				cmb_BillCollControlSearchDay.setEnabled(false);
				btn_BillCollControlDoSearch.setEnabled(false);
			}
		});
		
		//button to switch from grocer bills to retail bills
		btn_BillCollControlSwitchType.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//detect current state
				if(!BillCollSelectionRetail)
				{
					//change booleans
					BillCollSelectionRetail = true;
					//switch interface
					btn_BillCollControlSwitchType.setText("Showing Retail Bills");
				}
				else
				{
					//change booleans
					BillCollSelectionRetail = false;
					//switch interface
					btn_BillCollControlSwitchType.setText("Showing Grocer Bills");
				}
				cmb_BillCollControlSearchMonth.setEnabled(false);
				cmb_BillCollControlSearchDay.setEnabled(false);
				btn_BillCollControlDoSearch.setEnabled(false);
				buildBillScrl();
				cmb_DialogBillCollDelete.setModel(new DefaultComboBoxModel<String>(getAllBills()));
			}
		});
		//button to switch bill categories (individual,daily,monthly)
		btn_BillCollControlSwitchCategory.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(BillCollSelectionReport == 0)
				{
					pnl_BillCollControlSearch.setVisible(false);
					BillCollSelectionReport = 1;
					btn_BillCollControlSwitchCategory.setText("Daily Reports");
				}
				else if(BillCollSelectionReport == 1)
				{
					BillCollSelectionReport = 2;
					btn_BillCollControlSwitchCategory.setText("Monthly Reports");
				}
				else
				{
					BillCollSelectionReport = 0;
					btn_BillCollControlSwitchCategory.setText("Individual Bills");
					pnl_BillCollControlSearch.setVisible(true);
				}
				cmb_DialogBillCollDelete.setModel(new DefaultComboBoxModel<String>(getAllBills()));
				buildBillScrl();
			}
		});
		
		//button to delete a bill
		btn_BillCollControlDel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String category = (BillCollSelectionRetail ? "retail" : "grocer");
				//show a popup, which to delete?
				int result = JOptionPane.showOptionDialog(null, pnl_DialogBillCollDelete,"Select "+category+" bill to delete", 
							 JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, StoreWindow.DialogOptions, null);
				if(result == JOptionPane.OK_OPTION)
				{
					//get and delete target file
					String target = cmb_DialogBillCollDelete.getSelectedItem().toString();
					deleteBill(target);
					//refresh components
					buildBillScrl();
					cmb_DialogBillCollDelete.setModel(new DefaultComboBoxModel<String>(getAllBills()));
				}
			}
		});
		
		//button to activate bill coll tab
		btn_BillCollAccess.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String response = (String) JOptionPane.showInputDialog("Enter Password:");
				//if response is correct, enable the adminpanel
				if(response != null && response.equals(StoreWindow.pswrd))
				{
					btn_BillCollAccess.setVisible(false);
					scrl_BillColl.setVisible(true);
					pnl_BillCollControl.setVisible(true);
				}
				else
				{JOptionPane.showMessageDialog(StoreWindow.frame, "Wrong password.");}
			}
		});
		//button to close bill coll tab
		btn_BillCollUnaccess.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				btn_BillCollAccess.setVisible(true);
				scrl_BillColl.setVisible(false);
				pnl_BillCollControl.setVisible(false);
			}
		});
		
		//** Add components into the interface
		this.add(btn_BillCollAccess);
		this.add(pnl_BillCollControl,gbc_pnl_BillCollControl);
		pnl_BillCollControl.add(btn_BillCollControlSwitchType);
		pnl_BillCollControl.add(btn_BillCollControlSwitchCategory);
		pnl_BillCollControl.add(pnl_BillCollControlSearch);
		pnl_BillCollControlSearch.add(lbl_BillCollControlSearch);
		pnl_BillCollControlSearch.add(cmb_BillCollControlSearchYear);
		pnl_BillCollControlSearch.add(cmb_BillCollControlSearchMonth);
		pnl_BillCollControlSearch.add(cmb_BillCollControlSearchDay);
		pnl_BillCollControlSearch.add(btn_BillCollControlDoSearch);
		pnl_BillCollControlSearch.add(btn_BillCollControlReset);
		pnl_BillCollControl.add(btn_BillCollControlDel);
		pnl_BillCollControl.add(btn_BillCollUnaccess);
		this.add(scrl_BillColl,gbc_scrl_BillColl);

		pnl_DialogBillCollDelete.add(cmb_DialogBillCollDelete);
		
		buildBillScrl();
	}//end constructor
	
	//method to find the relevant number of years of earliest and oldest bills
	private String[] getBillYearRange()
	{
		ArrayList<String> relevant = new ArrayList<String>();
		//go into bills folder
		File folder = BillCollSelectionRetail ? new File(StoreWindow.billfilesloc+"/Retail") : new File(StoreWindow.billfilesloc+"/Grocer");
		//scan every available year
		File[] yearsavail = folder.listFiles();
		//read the folder names as integer
		if(folder.exists())
		{
			for(int i = 0; i < yearsavail.length; i++)
			{
				//place into arraylist
				if(yearsavail[i].isDirectory())
				{relevant.add((yearsavail[i].getName()));}
			}
		}
		//return data
		if(relevant.isEmpty())
		{relevant.add("0");}
		return relevant.toArray(new String[0]);
	}
	
	//methods to build the bill collection scrollpanel
	private static void buildBillScrl()
	{
		if(BillCollSelectionReport == 0)
		{buildIndividualBills(BillCollSelectionRetail);}
		else if(BillCollSelectionReport == 1)
		{buildDailyReports(BillCollSelectionRetail);}
		else
		{buildMonthlyReports(BillCollSelectionRetail);}
	}
	//populate scroll panel with individual bills
	private static void buildIndividualBills(boolean retail)
	{	
		//reset jpanel
		pnl_BillCollContents.removeAll();
		
		//lists
		ArrayList<JPanel> requiredPanels = new ArrayList<JPanel>();
		ArrayList<GridBagConstraints> requiredgbc = new ArrayList<GridBagConstraints>();
		ArrayList<String> requiredDates = new ArrayList<String>();
		ArrayList<String> requiredContent = new ArrayList<String>();
		
		//go into bills folder
		File folder = (retail ?  new File(StoreWindow.billfilesloc+"/Retail") :  new File(StoreWindow.billfilesloc+"/Grocer"));
		//scan every available year
		File[] years = folder.listFiles();
		String billyear,billmonth,billday,billtime;
		try
		{
			//for each year folder, there exists month folders
			for(File year : years)
			{
				//for each year in years collection
				billyear = year.getName();
				File[] months = year.listFiles();
				for(File month : months)
				{
					//for every month in months in a year
					billmonth = month.getName();
					File days[] = month.listFiles();
					//for every day in a month of a year
					for (File day : days)
					{
						//if not the monthly report
						billday = day.getName();
						if(day.isDirectory())
						{
							File[] bills = day.listFiles();
							//for each bill file
							for(File bill : bills)
							{
								//make sure not the daily report file
								if(!bill.getName().equals("DailyReport.txt") && !bill.getName().equals("DailyReport"))
								{
									billtime = bill.getName().replace(".txt", "");
									StringBuilder datatext = new StringBuilder();
									//read file, add data into scrollpanel
									try
									{
										FileReader fileReader = new FileReader(bill);
							            BufferedReader bufferedReader = new BufferedReader(fileReader);
							            String line;
							            while((line = bufferedReader.readLine()) != null) 
							            {datatext.append(' ').append(line+"\n");}   
	
							            // Always close files.
							            bufferedReader.close();  
									}
									catch(Exception e)
									{e.printStackTrace();}
									//collect data
									requiredPanels.add(new JPanel());
									requiredgbc.add(new GridBagConstraints());
									requiredDates.add("Transaction made at: "+billyear+","+billmonth+" "+billday+" "+billtime.replaceAll("_", ":"));
									requiredContent.add(datatext.toString());
								}
							}
						}
					}
				}
			}
		}
		catch(Exception e)
		{System.out.println("Safety Net: There isn't any bills yet");e.printStackTrace();System.out.println("Safety Net: There isn't any bills yet");}
		//write data into a panel
		for(int index = 0; index < requiredPanels.size(); index++)
		{
			requiredPanels.get(index).setLayout(new BorderLayout(8,0));
			requiredPanels.get(index).add(new JTextField(requiredDates.get(index)),BorderLayout.NORTH);
			requiredPanels.get(index).add(new JTextArea(requiredContent.get(index)),BorderLayout.CENTER);
			for(Component component : requiredPanels.get(index).getComponents())
			{
				if(component instanceof JTextComponent)
				{((JTextComponent) component).setEditable(false);}
			}
			requiredPanels.get(index).revalidate();
			requiredPanels.get(index).setPreferredSize(new Dimension(360,120));
			requiredgbc.get(index).anchor = GridBagConstraints.NORTH;
			requiredgbc.get(index).insets = new Insets(10, 10, 10, 10);
			requiredgbc.get(index).fill = GridBagConstraints.NONE;
			requiredgbc.get(index).gridy = index;
			pnl_BillCollContents.add(requiredPanels.get(index),requiredgbc.get(index));
		}
		pnl_BillCollContents.revalidate();
		pnl_BillCollContents.repaint();
	}
	//method to build the daily report scrollpanel
	private static void buildDailyReports(boolean retail)
	{
		//reset jpanel
		pnl_BillCollContents.removeAll();
		
		//lists
		ArrayList<JPanel> requiredPanels = new ArrayList<JPanel>();
		ArrayList<GridBagConstraints> requiredgbc = new ArrayList<GridBagConstraints>();
		ArrayList<String> requiredDates = new ArrayList<String>();
		ArrayList<String> requiredContent = new ArrayList<String>();
		
		//go into bills folder
		File folder = (retail ?  new File(StoreWindow.billfilesloc+"/Retail") :  new File(StoreWindow.billfilesloc+"/Grocer"));
		//scan every available year
		File[] years = folder.listFiles();
		String billyear,billmonth,billday;
		try
		{
			//for each year folder, there exists month folders
			for(File year : years)
			{
				//for each year in years collection
				billyear = year.getName();
				File[] months = year.listFiles();
				for(File month : months)
				{
					//for every month in months in a year
					billmonth = month.getName();
					File days[] = month.listFiles();
					//for every day in a month of a year
					for (File day : days)
					{
						//if not the monthly report
						billday = day.getName();
						if(day.isDirectory())
						{
							File[] bills = day.listFiles();
							//for each bill file
							for(File bill : bills)
							{
								//make sure is the daily report file
								if(bill.getName().equals("DailyReport.txt") || bill.getName().equals("DailyReport"))
								{
									StringBuilder datatext = new StringBuilder();
									//read file, add data into scrollpanel
									try
									{
										FileReader fileReader = new FileReader(bill);
							            BufferedReader bufferedReader = new BufferedReader(fileReader);
							            String line;
							            //skip redundant headers
							            bufferedReader.readLine();
							            bufferedReader.readLine();
							            while((line = bufferedReader.readLine()) != null) 
							            {datatext.append(' ').append(line+"\n");}   
	
							            // Always close files.
							            bufferedReader.close();  
									}
									catch(Exception e)
									{e.printStackTrace();}
									//collect data
									requiredPanels.add(new JPanel());
									requiredgbc.add(new GridBagConstraints());
									requiredDates.add("Report for: "+billday+" of "+billmonth+", "+billyear);
									requiredContent.add(datatext.toString());
								}
							}
						}
					}
				}
			}
		}
		catch(Exception e)
		{System.out.println("Safety Net: There isn't any bills yet");e.printStackTrace();System.out.println("Safety Net: There isn't any bills yet");}
		//write data into a panel
		for(int index = 0; index < requiredPanels.size(); index++)
		{
			requiredPanels.get(index).setLayout(new BorderLayout(8,0));
			requiredPanels.get(index).add(new JTextField(requiredDates.get(index)),BorderLayout.NORTH);
			requiredPanels.get(index).add(new JTextArea(requiredContent.get(index)),BorderLayout.CENTER);
			requiredPanels.get(index).revalidate();
			requiredgbc.get(index).anchor = GridBagConstraints.NORTH;
			requiredgbc.get(index).insets = new Insets(10, 10, 10, 10);
			requiredgbc.get(index).fill = GridBagConstraints.NONE;
			requiredgbc.get(index).gridy = index;
			pnl_BillCollContents.add(requiredPanels.get(index),requiredgbc.get(index));
		}
		pnl_BillCollContents.revalidate();
		pnl_BillCollContents.repaint();
	}
	//method to build the monthly report scrollpanel
	private static void buildMonthlyReports(boolean retail)
	{
		//reset jpanel
		pnl_BillCollContents.removeAll();
		
		//lists
		ArrayList<JPanel> requiredPanels = new ArrayList<JPanel>();
		ArrayList<GridBagConstraints> requiredgbc = new ArrayList<GridBagConstraints>();
		ArrayList<String> requiredDates = new ArrayList<String>();
		ArrayList<String> requiredContent = new ArrayList<String>();
		
		//go into bills folder
		File folder = (retail ?  new File(StoreWindow.billfilesloc+"/Retail") :  new File(StoreWindow.billfilesloc+"/Grocer"));
		//scan every available year
		File[] years = folder.listFiles();
		String billyear,billmonth;
		try
		{
			//for each year folder, there exists month folders
			for(File year : years)
			{
				//for each year in years collection
				billyear = year.getName();
				File[] months = year.listFiles();
				for(File month : months)
				{
					//for every month in months in a year
					billmonth = month.getName();
					File reports[] = month.listFiles();
					//for each month file
					for(File report : reports)
					{
						//make sure is the monthly report file
						if(report.getName().equals("MonthlyReport.txt") || report.getName().equals("MonthlyReport"))
						{
							StringBuilder datatext = new StringBuilder();
							//read file, add data into scrollpanel
							try
							{
								FileReader fileReader = new FileReader(report);
					            BufferedReader bufferedReader = new BufferedReader(fileReader);
					            String line;
					            //skip redundant headers
					            bufferedReader.readLine();
					            bufferedReader.readLine();
					            while((line = bufferedReader.readLine()) != null) 
					            {datatext.append(' ').append(line+"\n");}   

					            // Always close files.
					            bufferedReader.close();  
							}
							catch(Exception e)
							{e.printStackTrace();}
							//collect data
							requiredPanels.add(new JPanel());
							requiredgbc.add(new GridBagConstraints());
							requiredDates.add("Report for: "+billmonth+" of "+billyear);
							requiredContent.add(datatext.toString());
						}
					}
				}
			}
		}
		catch(Exception e)
		{System.out.println("Safety Net: There isn't any bills yet");e.printStackTrace();System.out.println("Safety Net: There isn't any bills yet");}
		//write data into a panel
		for(int index = 0; index < requiredPanels.size(); index++)
		{
			requiredPanels.get(index).setLayout(new BorderLayout(8,0));
			requiredPanels.get(index).add(new JTextField(requiredDates.get(index)),BorderLayout.NORTH);
			requiredPanels.get(index).add(new JTextArea(requiredContent.get(index)),BorderLayout.CENTER);
			requiredPanels.get(index).revalidate();
			requiredgbc.get(index).anchor = GridBagConstraints.NORTH;
			requiredgbc.get(index).insets = new Insets(10, 10, 10, 10);
			requiredgbc.get(index).fill = GridBagConstraints.NONE;
			requiredgbc.get(index).gridy = index;
			pnl_BillCollContents.add(requiredPanels.get(index),requiredgbc.get(index));
		}
		pnl_BillCollContents.revalidate();
		pnl_BillCollContents.repaint();
	}
	
	//method to get all relevant bill names
	private String[] getAllBills()
	{
		ArrayList<String> collection = new ArrayList<String>();
		File folder = (BillCollSelectionRetail ?  new File(StoreWindow.billfilesloc+"/Retail") :  new File(StoreWindow.billfilesloc+"/Grocer"));
		//scan every available year
		File[] years = folder.listFiles();
		String billyear,billmonth,billday,billtime;
		try
		{
			//for each year folder, there exists month folders
			for(File year : years)
			{
				//for each year in years collection
				billyear = year.getName();
				File[] months = year.listFiles();
				for(File month : months)
				{
					//for every month in months in a year
					billmonth = month.getName();
					File days[] = month.listFiles();
					if (BillCollSelectionReport == 2)
					{collection.add(billyear+"-"+billmonth);}
					else
					{
						//for every day in a month of a year
						for (File day : days)
						{
							//if not the monthly report
							billday = day.getName();
							if(day.isDirectory())
							{
								File[] bills = day.listFiles();
								//do we want reports?
								if (BillCollSelectionReport == 1)
								{collection.add(billyear+"-"+billmonth+"-"+billday);}
								else
								{
									//for each bill file
									for(File bill : bills)
									{
										//make sure not the daily report file
										if(!bill.getName().equals("DailyReport.txt") && !bill.getName().equals("DailyReport"))
										{
											billtime = bill.getName().replace(".txt", "").replaceAll("_", ":");
											//collect data
											collection.add(billyear+"-"+billmonth+"-"+billday+" "+billtime);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		catch(Exception e)
		{System.out.println("Safety Net: There isn't any bills yet");e.printStackTrace();System.out.println("Safety Net: There isn't any bills yet");}
		return collection.toArray(new String[0]);
	}
	
	//method to delete a specific target bill- actually move to trash folder
	private void deleteBill(String target)
	{
		String root = (BillCollSelectionRetail ?  (StoreWindow.billfilesloc+"/Retail") :  (StoreWindow.billfilesloc+"/Grocer"));
		//disseminate string target to get file details
		if(BillCollSelectionReport == 0)
		{
			//for individual bills
			//yyyy-mm-dd hh:mm:ss
			String[] filename = target.split(" ");
			String filetime = filename[1].replaceAll(":", "_");
			String[] filedate = filename[0].split("-");
			String fileloc = root+"/"+filedate[0]+"/"+filedate[1]+"/"+filedate[2]+"/"+filetime;
			//delete the file
			String trashplace = "Trash/"+root+"/"+filedate[0]+"/"+filedate[1]+"/"+filedate[2];
			File original = new File(fileloc+".txt");
			File replacem = new File(trashplace);
			replacem.mkdirs();
			original.renameTo(new File(replacem+"/"+filetime+".txt"));			
		}
		else if(BillCollSelectionReport == 1)
		{
			//daily reports
			//yyyy-mm-dd
			String[] filedate = target.split("-");
			String fileloc = root+"/"+filedate[0]+"/"+filedate[1]+"/"+filedate[2];
			//delete the file
			String trashplace = "Trash/"+root+"/"+filedate[0]+"/"+filedate[1]+"/"+filedate[2];
			File original = new File(fileloc+"/DailyReport.txt");
			File replacem = new File(trashplace);
			replacem.mkdirs();
			original.renameTo(new File(replacem+"/DailyReport.txt"));
		}
		else
		{
			//monthly reports
			//yyyy-mm
			String[] filedate = target.split("-");
			String fileloc = root+"/"+filedate[0]+"/"+filedate[1];
			//delete the file
			String trashplace = "Trash/"+root+"/"+filedate[0]+"/"+filedate[1];
			File original = new File(fileloc+"/MonthlyReport.txt");
			File replacem = new File(trashplace);
			replacem.mkdirs();
			original.renameTo(new File(replacem+"/MonthlyReport.txt"));
		}
		
		//cleanup folders- delete empty folders
		if(!folderCleanup(new File(root)))
		{JOptionPane.showMessageDialog(StoreWindow.frame, "Directory Deletion has problems. Please check bill directory for empty folders and delete them.");}
		
		buildBillScrl();
	}

	//method to remove empty folders
	private boolean folderCleanup(File fileLocation) 
	{
		File[] allContents = (fileLocation).listFiles();
	    if (allContents != null) 
	    {
	        for (File file : allContents) 
	        {
	        	if(file.isDirectory())
	        	folderCleanup(file);
	        	else
	        	{return false;}
	        }
	    }
        else
        {fileLocation.delete();}
	    
	    return true;
	}

	public static void refreshMe() 
	{buildBillScrl();}
	
}
