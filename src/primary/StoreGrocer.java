package primary;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

public class StoreGrocer extends JPanel 
{
	private static final long serialVersionUID = -649888497270078466L;
	private static JTable tbl_SaleGrocerDatabase;
	private static JTable tbl_SaleGrocerPurchased;
	
	StoreGrocer()
	{
		//generate components
		this.setBackground(UIManager.getColor("ComboBox.buttonShadow"));
		GridBagLayout gbl_pnl_SaleGrocer = new GridBagLayout();
		gbl_pnl_SaleGrocer.columnWidths = new int[]{0};
		gbl_pnl_SaleGrocer.rowHeights = new int[]{0};
		gbl_pnl_SaleGrocer.columnWeights = new double[]{1.0};
		gbl_pnl_SaleGrocer.rowWeights = new double[]{0.0};
		this.setLayout(gbl_pnl_SaleGrocer);
		//grocery list panel
		JPanel pnl_SaleGrocerStockList = new JPanel();
		pnl_SaleGrocerStockList.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		GridBagConstraints gbc_pnl_SaleGrocerStockList = new GridBagConstraints();
		gbc_pnl_SaleGrocerStockList.anchor = GridBagConstraints.NORTH;
		gbc_pnl_SaleGrocerStockList.insets = new Insets(5, 5, 5, 5);
		gbc_pnl_SaleGrocerStockList.fill = GridBagConstraints.BOTH;
		gbc_pnl_SaleGrocerStockList.weightx = 0.5;
		gbc_pnl_SaleGrocerStockList.gridheight = 2;
		gbc_pnl_SaleGrocerStockList.gridx = 0;
		gbc_pnl_SaleGrocerStockList.gridy = 0;
		GridBagLayout gbl_pnl_SaleGrocerStockList = new GridBagLayout();
		gbl_pnl_SaleGrocerStockList.columnWeights = new double[]{1.0};
		pnl_SaleGrocerStockList.setLayout(gbl_pnl_SaleGrocerStockList);
		//search/filter textbox
		JTextField txt_SaleGrocerStockSearch = new JTextField();
		txt_SaleGrocerStockSearch.setColumns(25);
		GridBagConstraints gbc_txt_SaleGrocerStockSearch = new GridBagConstraints();
		gbc_txt_SaleGrocerStockSearch.anchor = GridBagConstraints.NORTH;
		gbc_txt_SaleGrocerStockSearch.insets = new Insets(0, 0, 5, 0);
		gbc_txt_SaleGrocerStockSearch.gridx = 0;
		gbc_txt_SaleGrocerStockSearch.gridy = 0;
		gbc_txt_SaleGrocerStockSearch.weighty = 0.001;
		gbc_txt_SaleGrocerStockSearch.weightx = 1;
		//add model into grocery table
		tbl_SaleGrocerDatabase = new JTable(StoreWindow.getGrocerExcelData(false));
		tbl_SaleGrocerDatabase.setRowSelectionAllowed(false);
		//setup table design
		tbl_SaleGrocerDatabase.getColumnModel().getColumn(0).setResizable(false);
		tbl_SaleGrocerDatabase.getColumnModel().getColumn(0).setPreferredWidth(90);
		tbl_SaleGrocerDatabase.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		GridBagConstraints gbc_scrl_GrocerDatabase = new GridBagConstraints();
		gbc_scrl_GrocerDatabase.fill = GridBagConstraints.BOTH;
		gbc_scrl_GrocerDatabase.gridx = 0;
		gbc_scrl_GrocerDatabase.gridy = 1;
		gbc_scrl_GrocerDatabase.weighty = 0.9;
		gbc_scrl_GrocerDatabase.weightx = 1;
		//add scrollpane to table
		JScrollPane scrl_GrocerDatabase=new JScrollPane(tbl_SaleGrocerDatabase);
		scrl_GrocerDatabase.setVisible(true);
		
		//Payment money Panel
		JPanel pnl_SaleGrocerCosts = new JPanel();
		pnl_SaleGrocerCosts.setBackground(UIManager.getColor("ComboBox.buttonShadow"));
		GridBagLayout gbl_Costs = new GridBagLayout();
		gbl_Costs.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_Costs.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		pnl_SaleGrocerCosts.setLayout(gbl_Costs);
		GridBagConstraints gbc_pnl_SaleGrocerCosts = new GridBagConstraints();
		gbc_pnl_SaleGrocerCosts.insets = new Insets(0, 0, 5, 5);
		gbc_pnl_SaleGrocerCosts.gridx = 1;
		gbc_pnl_SaleGrocerCosts.gridy = 1;
		JLabel lbl_SaleGrocerCost = new JLabel("Cost :");
		GridBagConstraints gbc_lbl_SaleGrocerCosts = new GridBagConstraints();
		gbc_lbl_SaleGrocerCosts.anchor = GridBagConstraints.EAST;
		gbc_lbl_SaleGrocerCosts.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_SaleGrocerCosts.gridx = 0;
		gbc_lbl_SaleGrocerCosts.gridy = 0;
		JFormattedTextField txt_SaleGrocerCashCost = new JFormattedTextField(StoreWindow.currency);
		txt_SaleGrocerCashCost.setValue(new Double(0));
		txt_SaleGrocerCashCost.setEditable(false);
		GridBagConstraints gbc_txt_SaleGrocerCashCost = new GridBagConstraints();
		gbc_txt_SaleGrocerCashCost.gridwidth = 4;
		gbc_txt_SaleGrocerCashCost.fill = GridBagConstraints.BOTH;
		gbc_txt_SaleGrocerCashCost.insets = new Insets(0, 0, 5, 0);
		gbc_txt_SaleGrocerCashCost.gridx = 1;
		gbc_txt_SaleGrocerCashCost.gridy = 0;
		JLabel lbl_SaleGrocerPayment = new JLabel("Payment:");
		GridBagConstraints gbc_lbl_SaleGrocerPayment = new GridBagConstraints();
		gbc_lbl_SaleGrocerPayment.anchor = GridBagConstraints.EAST;
		gbc_lbl_SaleGrocerPayment.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_SaleGrocerPayment.gridx = 0;
		gbc_lbl_SaleGrocerPayment.gridy = 1;
		JFormattedTextField txt_SaleGrocerCashPayment = new JFormattedTextField(StoreWindow.currency);
		txt_SaleGrocerCashPayment.setColumns(20);
		txt_SaleGrocerCashPayment.setValue(new Double(0));
		GridBagConstraints gbc_txt_SaleGrocerCashPayment = new GridBagConstraints();
		gbc_txt_SaleGrocerCashPayment.gridwidth = 4;
		gbc_txt_SaleGrocerCashPayment.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_SaleGrocerCashPayment.insets = new Insets(0, 0, 5, 0);
		gbc_txt_SaleGrocerCashPayment.gridx = 1;
		gbc_txt_SaleGrocerCashPayment.gridy = 1;
		JLabel lbl_SaleGrocerChange = new JLabel("Change :");
		GridBagConstraints gbc_lbl_SaleGrocerChange = new GridBagConstraints();
		gbc_lbl_SaleGrocerChange.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_SaleGrocerChange.anchor = GridBagConstraints.EAST;
		gbc_lbl_SaleGrocerChange.gridx = 0;
		gbc_lbl_SaleGrocerChange.gridy = 2;
		JFormattedTextField txt_SaleGrocerCashReturn = new JFormattedTextField(StoreWindow.currency);
		txt_SaleGrocerCashReturn.setEditable(false);
		txt_SaleGrocerCashReturn.setValue(new Double(0));
		GridBagConstraints gbc_txt_SaleGrocerCashReturn = new GridBagConstraints();
		gbc_txt_SaleGrocerCashReturn.gridwidth = 4;
		gbc_txt_SaleGrocerCashReturn.insets = new Insets(0, 0, 5, 0);
		gbc_txt_SaleGrocerCashReturn.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_SaleGrocerCashReturn.gridx = 1;
		gbc_txt_SaleGrocerCashReturn.gridy = 2;
		txt_SaleGrocerCashReturn.setColumns(10);
		JButton btn_SaleGrocerTransConfirm = new JButton("Confirm Transaction");
		GridBagConstraints gbc_btn_SaleGrocerTransConfirm = new GridBagConstraints();
		gbc_btn_SaleGrocerTransConfirm.gridwidth = 2;
		gbc_btn_SaleGrocerTransConfirm.insets = new Insets(0, 0, 0, 5);
		gbc_btn_SaleGrocerTransConfirm.fill = GridBagConstraints.BOTH;
		gbc_btn_SaleGrocerTransConfirm.gridx = 0;
		gbc_btn_SaleGrocerTransConfirm.gridy = 3;
		JButton btn_SaleGrocerTransCancel = new JButton("Cancel Transaction");
		GridBagConstraints gbc_btn_SaleGrocerTransCancel = new GridBagConstraints();
		gbc_btn_SaleGrocerTransCancel.fill = GridBagConstraints.BOTH;
		gbc_btn_SaleGrocerTransCancel.gridx = 4;
		gbc_btn_SaleGrocerTransCancel.gridy = 3;
		JButton btn_SaleGrocerClear = new JButton("Clear");
		GridBagConstraints gbc_btn_SaleGrocerClear = new GridBagConstraints();
		gbc_btn_SaleGrocerClear.insets = new Insets(5, 10, 5, 0);
		gbc_btn_SaleGrocerClear.gridx = 0;
		gbc_btn_SaleGrocerClear.gridy = 0;
		
		//purchased items panel
		GridBagConstraints layout1 = new GridBagConstraints();
		JPanel pnl_SaleGrocerPurchases = new JPanel();
		pnl_SaleGrocerPurchases.setBackground(Color.CYAN);
		layout1.fill = GridBagConstraints.BOTH;
		layout1.weightx = 0.5;
		layout1.weighty = 0.9;
		layout1.gridx = 1;
		layout1.gridy = 0;
		GridBagLayout gbl_Purchases = new GridBagLayout();
		gbl_Purchases.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_Purchases.rowHeights = new int[]{0, 22, 0};
		gbl_Purchases.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_Purchases.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		pnl_SaleGrocerPurchases.setLayout(gbl_Purchases);
		tbl_SaleGrocerPurchased = new JTable();
		tbl_SaleGrocerPurchased.setModel(new DefaultTableModel(null,StoreWindow.grocerStandardTableModel));
		GridBagConstraints gbc_tbl_SaleGrocerPurchased = new GridBagConstraints();
		gbc_tbl_SaleGrocerPurchased.weighty = 1.0;
		gbc_tbl_SaleGrocerPurchased.gridwidth = 5;
		gbc_tbl_SaleGrocerPurchased.insets = new Insets(10, 10, 10, 10);
		gbc_tbl_SaleGrocerPurchased.fill = GridBagConstraints.BOTH;
		gbc_tbl_SaleGrocerPurchased.gridx = 0;
		gbc_tbl_SaleGrocerPurchased.gridy = 1;
		//add scrollpane to table
		JScrollPane scrl_SaleGrocerPurchased=new JScrollPane(tbl_SaleGrocerPurchased);
		JTextField txt_SaleGrocerScannedCode = new JTextField();
		GridBagConstraints gbc_txt_SaleGrocerScannedCode = new GridBagConstraints();
		gbc_txt_SaleGrocerScannedCode.weightx = 0.25;
		gbc_txt_SaleGrocerScannedCode.insets = new Insets(10, 10, 5, 5);
		gbc_txt_SaleGrocerScannedCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_SaleGrocerScannedCode.gridx = 1;
		gbc_txt_SaleGrocerScannedCode.gridy = 0;
		txt_SaleGrocerScannedCode.setColumns(10);
		JTextField txt_SaleGrocerScannedName = new JTextField();
		txt_SaleGrocerScannedName.setEditable(false);
		GridBagConstraints gbc_txt_SaleGrocerScannedName = new GridBagConstraints();
		gbc_txt_SaleGrocerScannedName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_SaleGrocerScannedName.anchor = GridBagConstraints.SOUTH;
		gbc_txt_SaleGrocerScannedName.weightx = 0.25;
		gbc_txt_SaleGrocerScannedName.insets = new Insets(0, 0, 5, 5);
		gbc_txt_SaleGrocerScannedName.gridx = 2;
		gbc_txt_SaleGrocerScannedName.gridy = 0;
		txt_SaleGrocerScannedName.setColumns(10);
		JTextField txt_SaleGrocerScannedCount = new JTextField();
		txt_SaleGrocerScannedCount.setEditable(false);
		txt_SaleGrocerScannedCount.setText("");
		GridBagConstraints gbc_txt_SaleGrocerScannedCount = new GridBagConstraints();
		gbc_txt_SaleGrocerScannedCount.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_SaleGrocerScannedCount.weightx = 0.25;
		gbc_txt_SaleGrocerScannedCount.anchor = GridBagConstraints.SOUTH;
		gbc_txt_SaleGrocerScannedCount.insets = new Insets(0, 0, 5, 5);
		gbc_txt_SaleGrocerScannedCount.gridx = 3;
		gbc_txt_SaleGrocerScannedCount.gridy = 0;
		txt_SaleGrocerScannedCount.setColumns(10);
		JButton btn_SaleGrocerScannedConfirm = new JButton("Add Item");
		btn_SaleGrocerScannedConfirm.setEnabled(false);
		GridBagConstraints gbc_btn_SaleGrocerScannedConfirm = new GridBagConstraints();
		gbc_btn_SaleGrocerScannedConfirm.anchor = GridBagConstraints.SOUTH;
		gbc_btn_SaleGrocerScannedConfirm.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_SaleGrocerScannedConfirm.weightx = 0.1;
		gbc_btn_SaleGrocerScannedConfirm.insets = new Insets(0, 0, 5, 10);
		gbc_btn_SaleGrocerScannedConfirm.gridx = 4;
		gbc_btn_SaleGrocerScannedConfirm.gridy = 0;
		
		//add autosorters
		tbl_SaleGrocerDatabase.setAutoCreateRowSorter(true);
		//make jtables uneditable
		tbl_SaleGrocerDatabase.setEnabled(false);
		tbl_SaleGrocerPurchased.setEnabled(false);
		//add functions
		//grocery sell database textbox to search for entered text == database id matches
		txt_SaleGrocerStockSearch.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				DefaultTableModel OGmodel = StoreWindow.getGrocerExcelData(false);
				String text = txt_SaleGrocerStockSearch.getText();
		        if (text.length() == 0) 
		        {tbl_SaleGrocerDatabase.setModel(OGmodel);} 
		        else 
		        {
		        	//search for strings matching text*
		        	ArrayList<String[]> matches = new ArrayList<String[]>();
		        	matches = StoreWindow.collectionsearch(false, text+".*");
		        	//modify tablemodel
		        	DefaultTableModel newmodel = new DefaultTableModel(null,StoreWindow.grocerStandardTableModel);
		        	for(String[] match : matches)
		        	{newmodel.addRow(match);}
		        	//set model
		        	tbl_SaleGrocerDatabase.setModel(newmodel);
		        }
			}
		});
		
		//add scan event on the textbox.
		txt_SaleGrocerScannedCode.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				//generally readers append completed barcode with ENTER key
				//thus when enter key it detected, read the entered barcode and get item info for it
				if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				{
					String barcode = txt_SaleGrocerScannedCode.getText();
					txt_SaleGrocerScannedCode.setEditable(false);
					String[] item = StoreWindow.findItem(false,barcode);
					if(item != null)
					{
						//show the found results
						txt_SaleGrocerScannedName.setText(item[1]);
						txt_SaleGrocerScannedCount.setText(item[2]);
						//enable next step
						btn_SaleGrocerScannedConfirm.setEnabled(true);
						btn_SaleGrocerScannedConfirm.requestFocus();       
					}
					else
					{
						JOptionPane.showMessageDialog(StoreWindow.frame, "Item ID " + barcode + " not found");
						txt_SaleGrocerScannedCode.setEditable(true);
					}
				} 
				else 
				{
		       		//put into string variable
				}
			}
		});
		//Button to confirm Autoscan
		btn_SaleGrocerScannedConfirm.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				String[] inputs = new String[3];
				//get text from auto input
				inputs[0] = txt_SaleGrocerScannedCode.getText();
				inputs[1] = txt_SaleGrocerScannedName.getText();
				inputs[2] = txt_SaleGrocerScannedCount.getText();
				
				txt_SaleGrocerScannedCode.setText("");
				txt_SaleGrocerScannedName.setText("");
				txt_SaleGrocerScannedCount.setText("");
				
				//get the collection of ids already in table
				boolean duplicate = false;
				DefaultTableModel model = (DefaultTableModel) tbl_SaleGrocerPurchased.getModel();
				for (int count = 0; count < model.getRowCount(); count++)
				{
					String alreadyin = model.getValueAt(count, 0).toString().trim();
					//check if new id is a duplicate
					if( alreadyin.equals(inputs[0]) )
					{duplicate = true;}
				}
				if(duplicate)
				{
					JOptionPane.showMessageDialog(StoreWindow.frame, "Error: Inserted id is duplicate.");
					//clear selection
					txt_SaleGrocerScannedCode.setText("");
		            txt_SaleGrocerScannedName.setText("");
		            txt_SaleGrocerScannedCount.setText("");
		            btn_SaleGrocerScannedConfirm.setEnabled(false);
					txt_SaleGrocerScannedCode.setEditable(true);
				}
				else
				{
					//add into the list in purchased table
					StoreWindow.addPurchaseItem(tbl_SaleGrocerPurchased,inputs);
					
					//calculate the total costs,place costs into textfield
					txt_SaleGrocerCashCost.setValue(StoreWindow.getPurchaseCost(tbl_SaleGrocerPurchased));
					
					txt_SaleGrocerScannedCode.setEditable(true);
					btn_SaleGrocerScannedConfirm.setEnabled(false);
				}
			}
		});
		
		//script to run when the 'payment' textfield is changed.
		txt_SaleGrocerCashPayment.addPropertyChangeListener(new PropertyChangeListener() 
		{
			public void propertyChange(PropertyChangeEvent arg0) 
			{
				//AUTOMATICALLY CALCULATE CHANGE
				//get purchase total cost
				long totalcost = ((Number)txt_SaleGrocerCashCost.getValue()).longValue();
				//get the payment
				long payment = ((Number)txt_SaleGrocerCashPayment.getValue()).longValue();
				//calculate change
				long change = payment - totalcost;
				txt_SaleGrocerCashReturn.setValue(change);
			}
		});
		
		//button for transaction confirm-> Delete excel records, save bill, clear components
		btn_SaleGrocerTransConfirm.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//get items to be sold
				ArrayList<String[]> purchaseditems = new ArrayList<String[]>();
				DefaultTableModel model = (DefaultTableModel) tbl_SaleGrocerPurchased.getModel();
				for (int count = 0; count < model.getRowCount(); count++)
				{
					String[] item = new String[3];
					item[0] = model.getValueAt(count, 0).toString();
					item[1] = model.getValueAt(count, 1).toString();
					item[2] = model.getValueAt(count, 2).toString();
					purchaseditems.add(item);
				}
				//get purchase total cost
				long totalcost = ((Number)txt_SaleGrocerCashCost.getValue()).longValue();
				//get the payment
				long payment = ((Number)txt_SaleGrocerCashPayment.getValue()).longValue();
				//get the return
				long change = ((Number)txt_SaleGrocerCashReturn.getValue()).longValue();
				//get store loss from items
				long salecost = 0;
				for(int count = 0; count < model.getRowCount(); count++)
				{
					String itemcode = model.getValueAt(count, 0).toString();
					String item[] = StoreWindow.findItem(false,itemcode);
					salecost += Long.parseLong(item[3].replaceAll(",", ""));
				}
				
				//check if payment is legal: payment must be => than cost
				//confirm sale
				if(payment >= totalcost)
				{
					StoreWindow.makeBill(purchaseditems, totalcost, payment, change, salecost,false);
					//delete purchased item
					for(String[] grocerspurchased : purchaseditems)
					{StoreWindow.excelRemove(false,grocerspurchased[0]);}
					//refresh tableportal
					StoreWindow.refreshTables();
					
					//clear all to initial state (use with cancel button's function)
					btn_SaleGrocerTransCancel.doClick();
					
				}
				else //show error: payment not enough
				{JOptionPane.showMessageDialog(StoreWindow.frame, "Error: Payment not sufficient.");}
			}
		});
		btn_SaleGrocerTransCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//reset scanning
				txt_SaleGrocerScannedCode.setText("");
	            txt_SaleGrocerScannedName.setText("");
	            txt_SaleGrocerScannedCount.setText("");
	            btn_SaleGrocerScannedConfirm.setEnabled(false);
				txt_SaleGrocerScannedCode.setEditable(true);
				//reset table
				StoreWindow.refreshTables();
				//reset payments
				txt_SaleGrocerCashCost.setValue(0);
				txt_SaleGrocerCashPayment.setValue(0);
				txt_SaleGrocerCashReturn.setValue(0);
			}
		});
		btn_SaleGrocerClear.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//reset scanning
				txt_SaleGrocerScannedCode.setText("");
	            txt_SaleGrocerScannedName.setText("");
	            txt_SaleGrocerScannedCount.setText("");
	            btn_SaleGrocerScannedConfirm.setEnabled(false);
				txt_SaleGrocerScannedCode.setEditable(true);
			}
		});
		
		//integrate components
		this.add(pnl_SaleGrocerStockList,gbc_pnl_SaleGrocerStockList);
		pnl_SaleGrocerStockList.add(txt_SaleGrocerStockSearch, gbc_txt_SaleGrocerStockSearch);
		pnl_SaleGrocerStockList.add(scrl_GrocerDatabase, gbc_scrl_GrocerDatabase);
		this.add(pnl_SaleGrocerPurchases, layout1);
		pnl_SaleGrocerPurchases.add(btn_SaleGrocerClear, gbc_btn_SaleGrocerClear);
		pnl_SaleGrocerPurchases.add(txt_SaleGrocerScannedCode, gbc_txt_SaleGrocerScannedCode);
		pnl_SaleGrocerPurchases.add(txt_SaleGrocerScannedName, gbc_txt_SaleGrocerScannedName);
		pnl_SaleGrocerPurchases.add(txt_SaleGrocerScannedCount, gbc_txt_SaleGrocerScannedCount);
		pnl_SaleGrocerPurchases.add(btn_SaleGrocerScannedConfirm, gbc_btn_SaleGrocerScannedConfirm);
		pnl_SaleGrocerPurchases.add(scrl_SaleGrocerPurchased, gbc_tbl_SaleGrocerPurchased);
		this.add(pnl_SaleGrocerCosts, gbc_pnl_SaleGrocerCosts);
		pnl_SaleGrocerCosts.add(lbl_SaleGrocerCost, gbc_lbl_SaleGrocerCosts);
		pnl_SaleGrocerCosts.add(txt_SaleGrocerCashCost, gbc_txt_SaleGrocerCashCost);
		pnl_SaleGrocerCosts.add(lbl_SaleGrocerPayment, gbc_lbl_SaleGrocerPayment);
		pnl_SaleGrocerCosts.add(txt_SaleGrocerCashPayment, gbc_txt_SaleGrocerCashPayment);
		pnl_SaleGrocerCosts.add(lbl_SaleGrocerChange, gbc_lbl_SaleGrocerChange);
		pnl_SaleGrocerCosts.add(txt_SaleGrocerCashReturn, gbc_txt_SaleGrocerCashReturn);
		pnl_SaleGrocerCosts.add(btn_SaleGrocerTransConfirm, gbc_btn_SaleGrocerTransConfirm);
		pnl_SaleGrocerCosts.add(btn_SaleGrocerTransCancel, gbc_btn_SaleGrocerTransCancel);
	}//end constructor
	
	//support functions
	//refresh components
	public static void refreshMe() 
	{
		tbl_SaleGrocerDatabase.setModel(StoreWindow.getGrocerExcelData(false));
		tbl_SaleGrocerPurchased.setModel(new DefaultTableModel(null,StoreWindow.grocerStandardTableModel));
	}
}
