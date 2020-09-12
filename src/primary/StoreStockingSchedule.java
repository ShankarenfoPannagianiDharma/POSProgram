package primary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

public class StoreStockingSchedule extends JPanel 
{
	private static final long serialVersionUID = -7675813462872144156L;
	private static JPanel pnl_StockSchedContents;
	private static JTable tbl_DialogScheduleAddItemList;
	
	StoreStockingSchedule()
	{
		this.setBackground(new Color(123, 104, 238));
		GridBagLayout gbl_pnl_StockSched = new GridBagLayout();
		gbl_pnl_StockSched.columnWidths = new int[]{0, 0};
		gbl_pnl_StockSched.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_pnl_StockSched.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnl_StockSched.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		this.setLayout(gbl_pnl_StockSched);
		JPanel pnl_StockSchedControl = new JPanel();
		pnl_StockSchedControl.setBackground(SystemColor.textHighlight);
		GridBagConstraints gbc_pnl_StockSchedControl = new GridBagConstraints();
		gbc_pnl_StockSchedControl.fill = GridBagConstraints.BOTH;
		gbc_pnl_StockSchedControl.insets = new Insets(5, 5, 5, 5);
		gbc_pnl_StockSchedControl.gridx = 0;
		gbc_pnl_StockSchedControl.gridy = 0;
		String[] monthrange = {"01","02","03","04","05","06","07","08","09","10","11","12"};
		JButton btn_StockSchedControlAdd = new JButton("Add new");
		JButton btn_StockSchedControlDel = new JButton("Delete");
		JScrollPane scrl_StockSched = new JScrollPane();
		pnl_StockSchedContents = new JPanel();
		pnl_StockSchedContents.setBackground(new Color(0, 51, 102));
		GridBagLayout gbl_pnl_StockSchedContents = new GridBagLayout();
		gbl_pnl_StockSchedContents.columnWidths = new int[]{0, 0};
		gbl_pnl_StockSchedContents.rowHeights = new int[]{0};
		gbl_pnl_StockSchedContents.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnl_StockSchedContents.rowWeights = new double[]{Double.MIN_VALUE};
		pnl_StockSchedContents.setLayout(gbl_pnl_StockSchedContents);
		scrl_StockSched.setViewportView(pnl_StockSchedContents);
		GridBagConstraints gbc_scrl_StockSched = new GridBagConstraints();
		gbc_scrl_StockSched.fill = GridBagConstraints.BOTH;
		gbc_scrl_StockSched.insets = new Insets(5, 5, 5, 5);
		gbc_scrl_StockSched.gridx = 0;
		gbc_scrl_StockSched.gridy = 1;
		buildSchdlScrl();
		
		//dialogbox to add new schedule
		JPanel pnl_DialogScheduleAdd = new JPanel();
		GridBagLayout gbl_pnl_DialogScheduleAdd = new GridBagLayout();
		gbl_pnl_DialogScheduleAdd.columnWidths = new int[] {0, 0, 0, 0};
		gbl_pnl_DialogScheduleAdd.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_pnl_DialogScheduleAdd.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gbl_pnl_DialogScheduleAdd.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		pnl_DialogScheduleAdd.setLayout(gbl_pnl_DialogScheduleAdd);
		JLabel lbl_DialogScheduleAddDate = new JLabel("Schedule Date: ");
		GridBagConstraints gbc_lbl_DialogScheduleAddDate = new GridBagConstraints();
		gbc_lbl_DialogScheduleAddDate.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_DialogScheduleAddDate.gridx = 0;
		gbc_lbl_DialogScheduleAddDate.gridy = 0;
		JFormattedTextField txt_DialogScheduleAddYear = new JFormattedTextField();
		txt_DialogScheduleAddYear.setColumns(6);
		GridBagConstraints gbc_txt_DialogScheduleAddYear = new GridBagConstraints();
		gbc_txt_DialogScheduleAddYear.anchor = GridBagConstraints.EAST;
		gbc_txt_DialogScheduleAddYear.insets = new Insets(0, 0, 5, 5);
		gbc_txt_DialogScheduleAddYear.gridx = 1;
		gbc_txt_DialogScheduleAddYear.gridy = 0;
		JComboBox<String> cmb_DialogScheduleAddMonth = new JComboBox<String>();
		cmb_DialogScheduleAddMonth.setModel(new DefaultComboBoxModel<String>(monthrange));
		GridBagConstraints gbc_cmb_DialogScheduleAddMonth = new GridBagConstraints();
		gbc_cmb_DialogScheduleAddMonth.insets = new Insets(0, 0, 5, 5);
		gbc_cmb_DialogScheduleAddMonth.gridx = 2;
		gbc_cmb_DialogScheduleAddMonth.gridy = 0;
		JComboBox<String> cmb_DialogScheduleAddDay = new JComboBox<String>();
		GridBagConstraints gbc_cmb_DialogScheduleAddDay = new GridBagConstraints();
		gbc_cmb_DialogScheduleAddDay.anchor = GridBagConstraints.WEST;
		gbc_cmb_DialogScheduleAddDay.insets = new Insets(0, 0, 5, 0);
		gbc_cmb_DialogScheduleAddDay.gridx = 3;
		gbc_cmb_DialogScheduleAddDay.gridy = 0;
		JLabel lbl_txtDialogScheduleAddItemData = new JLabel("Item: ");
		GridBagConstraints gbc_lbl_txtDialogScheduleAddItemData = new GridBagConstraints();
		gbc_lbl_txtDialogScheduleAddItemData.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_txtDialogScheduleAddItemData.gridx = 0;
		gbc_lbl_txtDialogScheduleAddItemData.gridy = 1;
		JTextField txt_DialogScheduleAddItemName = new JTextField();
		txt_DialogScheduleAddItemName.setColumns(20);
		GridBagConstraints gbc_txt_DialogScheduleAddItemName = new GridBagConstraints();
		gbc_txt_DialogScheduleAddItemName.insets = new Insets(0, 0, 5, 5);
		gbc_txt_DialogScheduleAddItemName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_DialogScheduleAddItemName.gridx = 1;
		gbc_txt_DialogScheduleAddItemName.gridy = 1;
		JFormattedTextField txt_DialogScheduleAddItemNumber = new JFormattedTextField(NumberFormat.getIntegerInstance());
		GridBagConstraints gbc_txt_DialogScheduleAddItemNumber = new GridBagConstraints();
		gbc_txt_DialogScheduleAddItemNumber.insets = new Insets(0, 0, 5, 5);
		gbc_txt_DialogScheduleAddItemNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_DialogScheduleAddItemNumber.gridx = 2;
		gbc_txt_DialogScheduleAddItemNumber.gridy = 1;
		JButton btn_DialogScheduleAddItemList = new JButton("Add item");
		GridBagConstraints gbc_btn_DialogScheduleAddItemList = new GridBagConstraints();
		gbc_btn_DialogScheduleAddItemList.insets = new Insets(0, 0, 5, 0);
		gbc_btn_DialogScheduleAddItemList.gridx = 3;
		gbc_btn_DialogScheduleAddItemList.gridy = 1;
		tbl_DialogScheduleAddItemList = new JTable(new DefaultTableModel(null,StoreWindow.scheduleTableModel));
		JScrollPane scrl_DialogScheduleAddItemList = new JScrollPane();
		scrl_DialogScheduleAddItemList.setViewportView(tbl_DialogScheduleAddItemList);
		GridBagConstraints gbc_scrl_DialogScheduleAddItemList = new GridBagConstraints();
		gbc_scrl_DialogScheduleAddItemList.fill = GridBagConstraints.BOTH;
		gbc_scrl_DialogScheduleAddItemList.gridx = 0;
		gbc_scrl_DialogScheduleAddItemList.gridy = 2;
		gbc_scrl_DialogScheduleAddItemList.gridwidth = 4;
		gbc_scrl_DialogScheduleAddItemList.gridheight = 6;
		
		//dialogbox to delete old schedule
		JPanel pnl_DialogScheduleDel = new JPanel();
		JLabel lbl_DialogScheduleDel = new JLabel("Schedule date to delete: ");
		JComboBox<String> cmb_DialogScheduleDel = new JComboBox<String>();
		cmb_DialogScheduleDel.setModel(new DefaultComboBoxModel<String>(getAllSchedules()));
		
		//add functions to components
		//button to add a schedule
		btn_StockSchedControlAdd.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//pop a dialogbox, setting the date of the schedule and textboxes for contents
				int result = JOptionPane.showOptionDialog(null, pnl_DialogScheduleAdd,"Add a schedule.", 
						 JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, StoreWindow.DialogOptions, null);
				if(result == JOptionPane.OK_OPTION)
				{
					//gather date from dialog
					String selectedyear = txt_DialogScheduleAddYear.getText().toString();
					String selectedmonth = cmb_DialogScheduleAddMonth.getSelectedItem().toString();
					String selectedday = cmb_DialogScheduleAddDay.getSelectedItem().toString();
					//gather data from table
					StringBuilder scheduletext = new StringBuilder();
					DefaultTableModel tabledata = (DefaultTableModel) tbl_DialogScheduleAddItemList.getModel();
					scheduletext.append(tabledata.getValueAt(0, 0).toString()+": "+tabledata.getValueAt(0, 1).toString());
					for (int count = 1; count < tabledata.getRowCount(); count++)
					{
						String[] item = new String[2];
						item[0] = tabledata.getValueAt(count, 0).toString();
						item[1] = tabledata.getValueAt(count, 1).toString();
						scheduletext.append("\n"+item[0]+": "+item[1]);
					}
					//create text file
					try
					{
						File targetfile = new File(StoreWindow.schedulefilesloc+"/"+selectedyear+"_"+selectedmonth+"_"+selectedday+".txt");
						BufferedWriter writer = null;
						if(!targetfile.exists())
						{
							targetfile.getParentFile().mkdirs();
							targetfile.createNewFile();
						}
						writer = new BufferedWriter(new FileWriter(targetfile));
						writer.write(scheduletext.toString());
						writer.close();
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(StoreWindow.frame, "Error in making schedule file.");
						e.printStackTrace();
					}
					//fill text in file
				}
				refreshMe();
				buildSchdlScrl();
			}
		});
		//Dialog textbox to select a year
		txt_DialogScheduleAddYear.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//if not empty, calculate the day in the month of the year
				if(!txt_DialogScheduleAddYear.getText().isEmpty())
				{
					//get values
					int selectedyears = Integer.parseInt(txt_DialogScheduleAddYear.getText().toString());
					int selectedmonth = Integer.parseInt(cmb_DialogScheduleAddMonth.getSelectedItem().toString());
					//get day value of month in year
					YearMonth yearMonthObject = YearMonth.of(selectedyears, selectedmonth);
					int daysInMonth = yearMonthObject.lengthOfMonth(); 
					ArrayList<String> days = new ArrayList<String>();
					for(int i =1;i<daysInMonth;i++)
					{days.add( i < 10 ? "0"+i : i+"");}
					//set the model for day combobox
					cmb_DialogScheduleAddDay.setModel(new DefaultComboBoxModel<String>(days.toArray(new String[0])));
					//enable day combobox
					cmb_DialogScheduleAddDay.setEnabled(true);
				}
			}
		});
		//Dialog combobox for dialogschedule to select a month
		cmb_DialogScheduleAddMonth.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//if not empty, calculate the day in the month of the year
				if(!txt_DialogScheduleAddYear.getText().isEmpty())
				{
					//get values
					int selectedyears = Integer.parseInt(txt_DialogScheduleAddYear.getText().toString());
					int selectedmonth = Integer.parseInt(cmb_DialogScheduleAddMonth.getSelectedItem().toString()) - 1;	//integer months start from 0 [January]
					//get day value of month in year
					YearMonth yearMonthObject = YearMonth.of(selectedyears, selectedmonth);
					int daysInMonth = yearMonthObject.lengthOfMonth(); 
					ArrayList<String> days = new ArrayList<String>();
					for(int i =1;i<daysInMonth;i++)
					{days.add( i < 10 ? "0"+i : i+"");}
					//set the model for day combobox
					cmb_DialogScheduleAddDay.setModel(new DefaultComboBoxModel<String>(days.toArray(new String[0])));
					//enable day combobox
					cmb_DialogScheduleAddDay.setEnabled(true);
				}
			}
		});
		//Dialog button to add an item to list
		btn_DialogScheduleAddItemList.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//get data
				String addeditem 	 = txt_DialogScheduleAddItemName.getText().toString();
				String numberadded   = txt_DialogScheduleAddItemNumber.getText().toString();
				//make sure data is not empty
				if(!addeditem.isEmpty() && !numberadded.isEmpty())
				{
					//add list into panel
					DefaultTableModel tabledata = (DefaultTableModel) tbl_DialogScheduleAddItemList.getModel();
					String[] rowdata = {addeditem,numberadded};
					tabledata.addRow(rowdata);
				}
				//reset components
				txt_DialogScheduleAddItemName.setText("");
				txt_DialogScheduleAddItemNumber.setText("");
			}
		});
		
		//button to delete a schedule
		btn_StockSchedControlDel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//pop dialogbox with a selection combobox to delete
				int result = JOptionPane.showOptionDialog(null, pnl_DialogScheduleDel,"Delete a schedule file.", 
						 JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, StoreWindow.DialogOptions, null);
				if(result == JOptionPane.OK_OPTION)
				{
					//get item from combobox
					String filetodel = cmb_DialogScheduleDel.getSelectedItem().toString();
					//delete file
					String trashplace = "Trash/Schedules/";
					File original = new File(StoreWindow.schedulefilesloc+"/"+filetodel+".txt");
					File replacem = new File(trashplace);
					replacem.mkdirs();
					original.renameTo(new File(replacem+"/"+filetodel+".txt"));	
				}
				
				//reset items
				cmb_DialogScheduleDel.setModel(new DefaultComboBoxModel<String>(getAllSchedules()));
				buildSchdlScrl();
			}
		});
		
		//ADD Components to panel
		this.add(pnl_StockSchedControl,gbc_pnl_StockSchedControl);
		pnl_StockSchedControl.add(btn_StockSchedControlAdd);
		pnl_StockSchedControl.add(btn_StockSchedControlDel);
		this.add(scrl_StockSched,gbc_scrl_StockSched);
		
		pnl_DialogScheduleAdd.add(lbl_DialogScheduleAddDate,gbc_lbl_DialogScheduleAddDate);
		pnl_DialogScheduleAdd.add(txt_DialogScheduleAddYear,gbc_txt_DialogScheduleAddYear);
		pnl_DialogScheduleAdd.add(cmb_DialogScheduleAddMonth,gbc_cmb_DialogScheduleAddMonth);
		pnl_DialogScheduleAdd.add(cmb_DialogScheduleAddDay,gbc_cmb_DialogScheduleAddDay);
		pnl_DialogScheduleAdd.add(lbl_txtDialogScheduleAddItemData,gbc_lbl_txtDialogScheduleAddItemData);
		pnl_DialogScheduleAdd.add(txt_DialogScheduleAddItemName,gbc_txt_DialogScheduleAddItemName);
		pnl_DialogScheduleAdd.add(txt_DialogScheduleAddItemNumber,gbc_txt_DialogScheduleAddItemNumber);
		pnl_DialogScheduleAdd.add(btn_DialogScheduleAddItemList,gbc_btn_DialogScheduleAddItemList);
		pnl_DialogScheduleAdd.add(scrl_DialogScheduleAddItemList,gbc_scrl_DialogScheduleAddItemList);
		
		pnl_DialogScheduleDel.add(lbl_DialogScheduleDel);
		pnl_DialogScheduleDel.add(cmb_DialogScheduleDel);
		
	}//end constructor
	
	//method to build a list of schedules
	private static void buildSchdlScrl()
	{
		//reset panel first
		pnl_StockSchedContents.removeAll();
		
		ArrayList<JPanel> bigpanels 		= new ArrayList<JPanel>();
		ArrayList<JPanel> panels 			= new ArrayList<JPanel>();
		ArrayList<String> dates 			= new ArrayList<String>();
		ArrayList<String> texts				= new ArrayList<String>();
		ArrayList<GridBagConstraints> gbcs 	= new ArrayList<GridBagConstraints>();
		//go to the folder of schedules
		File schedulefolder = new File(StoreWindow.schedulefilesloc);
		if(!schedulefolder.exists())
		{schedulefolder.mkdirs();}
		File[] schedules = schedulefolder.listFiles();
		if(schedules.length != 0)
		{
			//Sort the files by the numbers
			customSort(schedules);
			
			//for each piece of schedule, create a panel of schedule data
			for(File schedule:schedules)
			{
				gbcs.add(new GridBagConstraints());
				//header/schedule date
				dates.add(schedule.getName().toString().replaceAll("_", " ").replaceAll(".txt", ""));
				//each line of data makes a new line of info
				try
				{
					StringBuilder datatext = new StringBuilder();
					FileReader fileReader = new FileReader(schedule);
		            BufferedReader bufferedReader = new BufferedReader(fileReader);
		            String line;
		            while((line = bufferedReader.readLine()) != null) 
		            {datatext.append(line+"\n");}   

		            // Always close files.
		            bufferedReader.close(); 
		            texts.add(datatext.toString());
		            
				}
				catch(Exception e)
				{
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Problem with building stocking schedules.");
				}
				bigpanels.add(new JPanel());
	            panels.add(new JPanel());
			}
			
			for(int index = 0; index < panels.size(); index++)
			{
				//add elements into panel
				SimpleDateFormat df = new SimpleDateFormat("yyyy MM dd"); 
				Date relevantdate = null;
				try 
				{
					relevantdate = df.parse(dates.get(index));
					Date currentdate = new Date();
					Color bgcolor;
					if( df.format(currentdate).equals(df.format(relevantdate)))
					{bgcolor = new Color(153,0,0);}
					else if( currentdate.before(relevantdate))
					{bgcolor = new Color(51,204,255);}
					else
					{bgcolor = new Color(0,153,0);}
					bigpanels.get(index).setBackground(bgcolor);
				} 
				catch (ParseException e) 
				{
					JOptionPane.showMessageDialog(null, "Problem with dating of schedules.");
					e.printStackTrace();
				}
				panels.get(index).setLayout(new BorderLayout(8,0));
				panels.get(index).add( new JTextField(dates.get(index)) ,BorderLayout.NORTH);
				panels.get(index).add(new JTextArea(texts.get(index)),BorderLayout.CENTER);
				panels.get(index).setPreferredSize(new Dimension(360,120));
				for(Component component : panels.get(index).getComponents())
				{
					if(component instanceof JTextComponent)
					{((JTextComponent) component).setEditable(false);}
				}
				panels.get(index).revalidate();
				gbcs.get(index).anchor = GridBagConstraints.NORTH;
				gbcs.get(index).insets = new Insets(10, 10, 10, 10);
				gbcs.get(index).fill = GridBagConstraints.NONE;
				gbcs.get(index).gridy = index;
				gbcs.get(index).weighty = 1.0;
				//add panel into interface
				bigpanels.get(index).add(panels.get(index));
				pnl_StockSchedContents.add(bigpanels.get(index),gbcs.get(index));
			}
			
			//refresh interface
			//this.repaint();
			//this.revalidate();
		}
	}
	
	//supersort custom: sort schedule files [y_m_d.txt]
	public static void customSort(File[] schedulefiles)
	{
		//sort days first
		Arrays.sort(schedulefiles,new Comparator<File>()
		{
			@Override
            public int compare(File o1, File o2) 
			{
                int n1 = extractDay(o1.getName());
                int n2 = extractDay(o2.getName());
                return n1 - n2;
            }
			
			private int extractDay(String name) 
			{
				int i = 0;
                try 
                {
                	String[] nameparts = name.split("_");
                    String number = nameparts[2].replaceAll(".txt", "");
                    i = Integer.parseInt(number);
                } 
                catch(Exception e) 
                {i = 0;}
                return i;
			}
		});
		
		//sort months
		Arrays.sort(schedulefiles,new Comparator<File>()
		{
			@Override
            public int compare(File o1, File o2) 
			{
                int n1 = extractMonth(o1.getName());
                int n2 = extractMonth(o2.getName());
                return n1 - n2;
            }
			
			private int extractMonth(String name) 
			{
				int i = 0;
	            try 
	            {
	            	String[] nameparts = name.split("_");
	                String number = nameparts[1];
	                i = Integer.parseInt(number);
	            } 
	            catch(Exception e) 
	            {i = 0;}
	            return i;
			}
		});
		
		//sort years
		Arrays.sort(schedulefiles,new Comparator<File>()
		{
			@Override
            public int compare(File o1, File o2) 
			{
                int n1 = extractYear(o1.getName());
                int n2 = extractYear(o2.getName());
                return n1 - n2;
            }
			
			private int extractYear(String name) 
			{
				int i = 0;
	            try 
	            {
	            	String[] nameparts = name.split("_");
	                String number = nameparts[0];
	                i = Integer.parseInt(number);
	            } 
	            catch(Exception e) 
	            {i = 0;}
	            return i;
			}
		});
	}
	
	//method to get all schedule names in folder.
	private String[] getAllSchedules()
	{
		ArrayList<String> scheduledates = new ArrayList<String>();
		File schedulefolder = new File(StoreWindow.schedulefilesloc);
		File[] schedules = schedulefolder.listFiles();
		if(schedules.length != 0)
		{
			for(File schedule : schedules)
			{scheduledates.add(schedule.getName().replace(".txt", ""));}
		}
		else
		{scheduledates.add("No Schedules as yet.");}
		return scheduledates.toArray(new String[0]);
	}
	
	public static void refreshMe()
	{
		buildSchdlScrl();
		tbl_DialogScheduleAddItemList.setModel(new DefaultTableModel(null,StoreWindow.scheduleTableModel));}
}
