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

public class StoreRetail extends JPanel 
{
	private static final long serialVersionUID = 4950868374567687037L;
	//jtables
	private static JTable tbl_SaleRetailDatabase;
	private static JTable tbl_SaleRetailPurchased;
	
	StoreRetail()
	{
		//create components
		this.setBackground(UIManager.getColor("ComboBox.buttonShadow"));
		GridBagLayout gbl_Retail = new GridBagLayout();
		gbl_Retail.columnWidths = new int[]{0};
		gbl_Retail.rowHeights = new int[]{0};
		gbl_Retail.columnWeights = new double[]{1.0};
		gbl_Retail.rowWeights = new double[]{0.0};
		this.setLayout(gbl_Retail);
		//add model into retail table
		tbl_SaleRetailDatabase = new JTable(StoreWindow.getRetailExcelData(true));
		tbl_SaleRetailDatabase.setRowSelectionAllowed(false);
		//setup table design
		tbl_SaleRetailDatabase.getColumnModel().getColumn(0).setResizable(false);
		tbl_SaleRetailDatabase.getColumnModel().getColumn(0).setPreferredWidth(90);
		tbl_SaleRetailDatabase.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		GridBagConstraints gbc_scrl_RetailDatabase = new GridBagConstraints();
		gbc_scrl_RetailDatabase.insets = new Insets(5, 5, 5, 5);
		gbc_scrl_RetailDatabase.fill = GridBagConstraints.BOTH;
		gbc_scrl_RetailDatabase.weightx = 0.5;
		gbc_scrl_RetailDatabase.weighty = 1;
		gbc_scrl_RetailDatabase.gridheight = 2;
		gbc_scrl_RetailDatabase.gridx = 0;
		gbc_scrl_RetailDatabase.gridy = 0;
		GridBagLayout gbl_tbl_Database = new GridBagLayout();
		gbl_tbl_Database.columnWidths = new int[]{289, 1, 0};
		gbl_tbl_Database.rowHeights = new int[]{1, 0};
		gbl_tbl_Database.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_tbl_Database.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		tbl_SaleRetailDatabase.setLayout(gbl_tbl_Database);
		//add scrollpane to table
		JScrollPane scrl_RetailDatabase=new JScrollPane(tbl_SaleRetailDatabase);
		scrl_RetailDatabase.setVisible(true);
		tbl_SaleRetailPurchased = new JTable();
		tbl_SaleRetailPurchased.setModel(new DefaultTableModel(null,StoreWindow.grocerStandardTableModel));
		GridBagConstraints gbc_tbl_SaleRetailPurchased = new GridBagConstraints();
		gbc_tbl_SaleRetailPurchased.weighty = 1.0;
		gbc_tbl_SaleRetailPurchased.gridwidth = 5;
		gbc_tbl_SaleRetailPurchased.insets = new Insets(10, 10, 10, 10);
		gbc_tbl_SaleRetailPurchased.fill = GridBagConstraints.BOTH;
		gbc_tbl_SaleRetailPurchased.gridx = 0;
		gbc_tbl_SaleRetailPurchased.gridy = 1;
		//add scrollpane to table
		JScrollPane scrl_SaleRetailPurchased=new JScrollPane(tbl_SaleRetailPurchased);
		//Payment money Panel
		GridBagConstraints layout2 = new GridBagConstraints();
		layout2.fill = GridBagConstraints.BOTH;
		layout2.insets = new Insets(10, 10, 10, 10);
		layout2.weighty = 0.1;
		layout2.ipady = 20;
		JPanel pnl_SaleRetailCosts = new JPanel();
		pnl_SaleRetailCosts.setBackground(UIManager.getColor("ComboBox.buttonShadow"));
		layout2.gridx = 1;
		layout2.gridy = 1;
		GridBagLayout gbl_SaleRetailCosts = new GridBagLayout();
		gbl_SaleRetailCosts.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_SaleRetailCosts.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		pnl_SaleRetailCosts.setLayout(gbl_SaleRetailCosts);
		JLabel lbl_SaleRetailCost = new JLabel("Cost :");
		GridBagConstraints gbc_lbl_SaleRetailCost = new GridBagConstraints();
		gbc_lbl_SaleRetailCost.anchor = GridBagConstraints.EAST;
		gbc_lbl_SaleRetailCost.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_SaleRetailCost.gridx = 0;
		gbc_lbl_SaleRetailCost.gridy = 0;
		JFormattedTextField txt_SaleRetailCashCost = new JFormattedTextField(StoreWindow.currency);
		txt_SaleRetailCashCost.setValue(new Double(0));
		txt_SaleRetailCashCost.setEditable(false);
		GridBagConstraints gbc_txt_SaleRetailCashCost = new GridBagConstraints();
		gbc_txt_SaleRetailCashCost.gridwidth = 4;
		gbc_txt_SaleRetailCashCost.fill = GridBagConstraints.BOTH;
		gbc_txt_SaleRetailCashCost.insets = new Insets(0, 0, 5, 0);
		gbc_txt_SaleRetailCashCost.gridx = 1;
		gbc_txt_SaleRetailCashCost.gridy = 0;
		JLabel lbl_SaleRetailPayment = new JLabel("Payment:");
		GridBagConstraints gbc_lbl_SaleRetailPayment = new GridBagConstraints();
		gbc_lbl_SaleRetailPayment.anchor = GridBagConstraints.EAST;
		gbc_lbl_SaleRetailPayment.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_SaleRetailPayment.gridx = 0;
		gbc_lbl_SaleRetailPayment.gridy = 1;
		JFormattedTextField txt_SaleRetailCashPayment = new JFormattedTextField(StoreWindow.currency);
		txt_SaleRetailCashPayment.setColumns(20);
		txt_SaleRetailCashPayment.setValue(new Double(0));
		GridBagConstraints gbc_txt_SaleRetailCashPayment = new GridBagConstraints();
		gbc_txt_SaleRetailCashPayment.gridwidth = 4;
		gbc_txt_SaleRetailCashPayment.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_SaleRetailCashPayment.insets = new Insets(0, 0, 5, 0);
		gbc_txt_SaleRetailCashPayment.gridx = 1;
		gbc_txt_SaleRetailCashPayment.gridy = 1;
		JLabel lbl_SaleRetailChange = new JLabel("Change :");
		GridBagConstraints gbc_lbl_SaleRetailChange = new GridBagConstraints();
		gbc_lbl_SaleRetailChange.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_SaleRetailChange.anchor = GridBagConstraints.EAST;
		gbc_lbl_SaleRetailChange.gridx = 0;
		gbc_lbl_SaleRetailChange.gridy = 2;
		JFormattedTextField txt_SaleRetailCashReturn = new JFormattedTextField(StoreWindow.currency);
		txt_SaleRetailCashReturn.setEditable(false);
		txt_SaleRetailCashReturn.setValue(new Double(0));
		GridBagConstraints gbc_txt_SaleRetailCashReturn = new GridBagConstraints();
		gbc_txt_SaleRetailCashReturn.gridwidth = 4;
		gbc_txt_SaleRetailCashReturn.insets = new Insets(0, 0, 5, 0);
		gbc_txt_SaleRetailCashReturn.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_SaleRetailCashReturn.gridx = 1;
		gbc_txt_SaleRetailCashReturn.gridy = 2;
		txt_SaleRetailCashReturn.setColumns(10);
		JButton btn_SaleRetailTransConfirm = new JButton("Confirm Transaction");
		GridBagConstraints gbc_btn_SaleRetailTransConfirm = new GridBagConstraints();
		gbc_btn_SaleRetailTransConfirm.gridwidth = 2;
		gbc_btn_SaleRetailTransConfirm.insets = new Insets(0, 0, 0, 5);
		gbc_btn_SaleRetailTransConfirm.fill = GridBagConstraints.BOTH;
		gbc_btn_SaleRetailTransConfirm.gridx = 0;
		gbc_btn_SaleRetailTransConfirm.gridy = 3;
		JButton btn_SaleRetailTransCancel = new JButton("Cancel Transaction");
		GridBagConstraints gbc_btn_SaleRetailTransCancel = new GridBagConstraints();
		gbc_btn_SaleRetailTransCancel.fill = GridBagConstraints.BOTH;
		gbc_btn_SaleRetailTransCancel.gridx = 4;
		gbc_btn_SaleRetailTransCancel.gridy = 3;
		JButton btn_SaleRetailClear = new JButton("Clear");
		GridBagConstraints gbc_btn_SaleRetailClear = new GridBagConstraints();
		gbc_btn_SaleRetailClear.insets = new Insets(5, 10, 5, 5);
		gbc_btn_SaleRetailClear.gridx = 0;
		gbc_btn_SaleRetailClear.gridy = 0;
		//purchased items panel
		GridBagConstraints layout3 = new GridBagConstraints();
		JPanel pnl_SaleRetailPurchases = new JPanel();
		pnl_SaleRetailPurchases.setBackground(Color.ORANGE);
		layout3.fill = GridBagConstraints.BOTH;
		layout3.weightx = 0.5;
		layout3.weighty = 0.9;
		layout3.gridx = 1;
		layout3.gridy = 0;
		GridBagLayout gbl_SaleRetailPurchases = new GridBagLayout();
		gbl_SaleRetailPurchases.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_SaleRetailPurchases.rowHeights = new int[]{0, 22, 0};
		gbl_SaleRetailPurchases.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_SaleRetailPurchases.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		pnl_SaleRetailPurchases.setLayout(gbl_SaleRetailPurchases);
		JTextField txt_SaleRetailScannedCode = new JTextField();
		GridBagConstraints gbc_txt_SaleRetailScannedCode = new GridBagConstraints();
		gbc_txt_SaleRetailScannedCode.weightx = 0.25;
		gbc_txt_SaleRetailScannedCode.insets = new Insets(10, 10, 5, 5);
		gbc_txt_SaleRetailScannedCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_SaleRetailScannedCode.gridx = 1;
		gbc_txt_SaleRetailScannedCode.gridy = 0;
		txt_SaleRetailScannedCode.setColumns(10);
		JTextField txt_SaleRetailScannedName = new JTextField();
		txt_SaleRetailScannedName.setEditable(false);
		GridBagConstraints gbc_txt_SaleRetailScannedName = new GridBagConstraints();
		gbc_txt_SaleRetailScannedName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_SaleRetailScannedName.anchor = GridBagConstraints.SOUTH;
		gbc_txt_SaleRetailScannedName.weightx = 0.25;
		gbc_txt_SaleRetailScannedName.insets = new Insets(0, 0, 5, 5);
		gbc_txt_SaleRetailScannedName.gridx = 2;
		gbc_txt_SaleRetailScannedName.gridy = 0;
		txt_SaleRetailScannedName.setColumns(10);
		JTextField txt_SaleRetailScannedCount = new JTextField();
		txt_SaleRetailScannedCount.setEditable(false);
		txt_SaleRetailScannedCount.setText("");
		GridBagConstraints gbc_txt_SaleRetailScannedCount = new GridBagConstraints();
		gbc_txt_SaleRetailScannedCount.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_SaleRetailScannedCount.weightx = 0.25;
		gbc_txt_SaleRetailScannedCount.anchor = GridBagConstraints.SOUTH;
		gbc_txt_SaleRetailScannedCount.insets = new Insets(0, 0, 5, 5);
		gbc_txt_SaleRetailScannedCount.gridx = 3;
		gbc_txt_SaleRetailScannedCount.gridy = 0;
		txt_SaleRetailScannedCount.setColumns(10);
		JButton btn_SaleRetailScannedConfirm = new JButton("Add Item");
		btn_SaleRetailScannedConfirm.setEnabled(false);
		GridBagConstraints gbc_btn_SaleRetailScannedConfirm = new GridBagConstraints();
		gbc_btn_SaleRetailScannedConfirm.anchor = GridBagConstraints.SOUTH;
		gbc_btn_SaleRetailScannedConfirm.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_SaleRetailScannedConfirm.weightx = 0.1;
		gbc_btn_SaleRetailScannedConfirm.insets = new Insets(0, 0, 5, 10);
		gbc_btn_SaleRetailScannedConfirm.gridx = 4;
		gbc_btn_SaleRetailScannedConfirm.gridy = 0;
		scrl_RetailDatabase.setVisible(true);
		//add autosorters
		tbl_SaleRetailDatabase.setAutoCreateRowSorter(true);
		//make jtables uneditable
		tbl_SaleRetailDatabase.setEnabled(false);
		tbl_SaleRetailPurchased.setEnabled(false);
		
		//add functions to components
		//add scan event on the textbox.
		txt_SaleRetailScannedCode.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				//generally readers append completed barcode with ENTER key
				//thus when enter key it detected, read the entered barcode and get item info for it
				if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				{
					String barcode = txt_SaleRetailScannedCode.getText();
					txt_SaleRetailScannedCode.setEditable(false);
					String[] item = StoreWindow.findItem(true,barcode);
					if(item != null)
					{
						//show the found results
						txt_SaleRetailScannedName.setText(item[1]);
						txt_SaleRetailScannedCount.setText(item[2]);
						//enable next step
						btn_SaleRetailScannedConfirm.setEnabled(true);
						btn_SaleRetailScannedConfirm.requestFocus();       
					}
					else
					{
						JOptionPane.showMessageDialog(StoreWindow.frame, "Item ID " + barcode + " not found");
						txt_SaleRetailScannedCode.setEditable(true);
					}
				} 
				else 
				{
		       		//put into string variable
				}
			}
		});
		//Button to confirm Autoscan
		btn_SaleRetailScannedConfirm.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				String[] inputs = new String[3];
				//get text from auto input
				inputs[0] = txt_SaleRetailScannedCode.getText();
				inputs[1] = txt_SaleRetailScannedName.getText();
				inputs[2] = txt_SaleRetailScannedCount.getText();
				
				txt_SaleRetailScannedCode.setText("");
				txt_SaleRetailScannedName.setText("");
				txt_SaleRetailScannedCount.setText("");
				
				//get the collection of ids already in table
				boolean duplicate = false;
				DefaultTableModel model = (DefaultTableModel) tbl_SaleRetailPurchased.getModel();
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
					txt_SaleRetailScannedCode.setText("");
		            txt_SaleRetailScannedName.setText("");
		            txt_SaleRetailScannedCount.setText("");
		            btn_SaleRetailScannedConfirm.setEnabled(false);
					txt_SaleRetailScannedCode.setEditable(true);
				}
				else
				{
				
					//add into the list in purchased table
					StoreWindow.addPurchaseItem(tbl_SaleRetailPurchased,inputs);
					
					//calculate the total costs,place costs into textfield
					txt_SaleRetailCashCost.setValue(StoreWindow.getPurchaseCost(tbl_SaleRetailPurchased));
	
					txt_SaleRetailScannedCode.setEditable(true);
				}
			}
		});
		
		//script to run when the 'payment' textfield is changed.
		txt_SaleRetailCashPayment.addPropertyChangeListener(new PropertyChangeListener() 
		{
			public void propertyChange(PropertyChangeEvent arg0) 
			{
				//AUTOMATICALLY CALCULATE CHANGE
				//get purchase total cost
				long totalcost = ((Number)txt_SaleRetailCashCost.getValue()).longValue();
				//get the payment
				long payment = ((Number)txt_SaleRetailCashPayment.getValue()).longValue();
				//calculate change
				long change = payment - totalcost;
				txt_SaleRetailCashReturn.setValue(change);
			}
		});
		
		//button for transaction confirm-> Delete excel records, save bill, clear components
		btn_SaleRetailTransConfirm.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//get items to be sold
				ArrayList<String[]> purchaseditems = new ArrayList<String[]>();
				DefaultTableModel model = (DefaultTableModel) tbl_SaleRetailPurchased.getModel();
				for (int count = 0; count < model.getRowCount(); count++)
				{
					String[] item = new String[3];
					item[0] = model.getValueAt(count, 0).toString();
					item[1] = model.getValueAt(count, 1).toString();
					item[2] = model.getValueAt(count, 2).toString();
					purchaseditems.add(item);
				}
				//get purchase total cost
				long totalcost = ((Number)txt_SaleRetailCashCost.getValue()).longValue();
				//get the payment
				long payment = ((Number)txt_SaleRetailCashPayment.getValue()).longValue();
				//get the return
				long change = ((Number)txt_SaleRetailCashReturn.getValue()).longValue();
				//get store loss from items
				long salecost = 0;
				for(int count = 0; count < model.getRowCount(); count++)
				{
					String itemcode = model.getValueAt(count, 0).toString();
					String item[] = StoreWindow.findItem(true,itemcode);
					salecost += Long.parseLong(item[3].replaceAll(",", ""));
				}
				
				//check if payment is legal: payment must be => than cost
				//confirm sale
				if(payment >= totalcost)
				{
					StoreWindow.makeBill(purchaseditems, totalcost, payment, change, salecost,true);
					
					//delete purchased item
					for(String[] retailspurchased : purchaseditems)
					{StoreWindow.excelRemove(true,retailspurchased[0]);}
					
					//refresh tableportal
					StoreWindow.refreshTables();
					
					//clear all to initial state (use with cancel button's function)
					btn_SaleRetailTransCancel.doClick();
					
				}
				else //show error: payment not enough
				{JOptionPane.showMessageDialog(StoreWindow.frame, "Error: Payment not sufficient.");}
			}
		});
		btn_SaleRetailTransCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//reset scanning
				txt_SaleRetailScannedCode.setText("");
	            txt_SaleRetailScannedName.setText("");
	            txt_SaleRetailScannedCount.setText("");
	            btn_SaleRetailScannedConfirm.setEnabled(false);
				txt_SaleRetailScannedCode.setEditable(true);
				//reset table
				StoreWindow.refreshTables();
				//reset payments
				txt_SaleRetailCashCost.setValue(0);
				txt_SaleRetailCashPayment.setValue(0);
				txt_SaleRetailCashReturn.setValue(0);
			}
		});

		btn_SaleRetailClear.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				txt_SaleRetailScannedCode.setText("");
	            txt_SaleRetailScannedName.setText("");
	            txt_SaleRetailScannedCount.setText("");
	            btn_SaleRetailScannedConfirm.setEnabled(false);
				txt_SaleRetailScannedCode.setEditable(true);
			}
		});
		
		//integrate components
		this.add(scrl_RetailDatabase, gbc_scrl_RetailDatabase);
		this.add(pnl_SaleRetailPurchases, layout3);
		pnl_SaleRetailPurchases.add(btn_SaleRetailClear, gbc_btn_SaleRetailClear);
		pnl_SaleRetailPurchases.add(txt_SaleRetailScannedCode, gbc_txt_SaleRetailScannedCode);
		pnl_SaleRetailPurchases.add(txt_SaleRetailScannedName, gbc_txt_SaleRetailScannedName);
		pnl_SaleRetailPurchases.add(txt_SaleRetailScannedCount, gbc_txt_SaleRetailScannedCount);
		pnl_SaleRetailPurchases.add(btn_SaleRetailScannedConfirm, gbc_btn_SaleRetailScannedConfirm);
		pnl_SaleRetailPurchases.add(scrl_SaleRetailPurchased, gbc_tbl_SaleRetailPurchased);
		this.add(pnl_SaleRetailCosts, layout2);
		pnl_SaleRetailCosts.add(lbl_SaleRetailCost, gbc_lbl_SaleRetailCost);
		pnl_SaleRetailCosts.add(txt_SaleRetailCashCost, gbc_txt_SaleRetailCashCost);
		pnl_SaleRetailCosts.add(lbl_SaleRetailPayment, gbc_lbl_SaleRetailPayment);
		pnl_SaleRetailCosts.add(txt_SaleRetailCashPayment, gbc_txt_SaleRetailCashPayment);
		pnl_SaleRetailCosts.add(lbl_SaleRetailChange, gbc_lbl_SaleRetailChange);
		pnl_SaleRetailCosts.add(txt_SaleRetailCashReturn, gbc_txt_SaleRetailCashReturn);
		pnl_SaleRetailCosts.add(btn_SaleRetailTransConfirm, gbc_btn_SaleRetailTransConfirm);
		pnl_SaleRetailCosts.add(btn_SaleRetailTransCancel, gbc_btn_SaleRetailTransCancel);
		
	}//end constructor

	//support functions
	public static void refreshMe() 
	{
		tbl_SaleRetailDatabase.setModel(StoreWindow.getGrocerExcelData(true));
		tbl_SaleRetailPurchased.setModel(new DefaultTableModel(null,StoreWindow.retailStandardTableModel));
	}
	
}
