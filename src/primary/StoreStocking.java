package primary;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

public class StoreStocking extends JPanel 
{	
	private static final long serialVersionUID = -1748101870413032656L;
	private static JTable tbl_StockingGrocerItems;
	private static JTable tbl_StockingRetailItems;
	private static JTable tbl_StockSetGrocer;
	private static JTable tbl_StockSetRetail;
	private static JTable tbl_DialogGrocerToRetailToMoveList;
	private static JTable tbl_DialogRetailToGrocerToMoveList;
	private static JComboBox<String> cmb_DialogGrocerToRetailSelectedID;
	private static JComboBox<String> cmb_DialogGrocerToRetailRetailName;
	private static JComboBox<String> cmb_DialogRetailToGrocerSelectedID;
	private static JComboBox<String> cmb_DialogRetailToGrocerGrocerName;
	
	StoreStocking()
	{
		//generate components
		GridBagLayout gbl_Stocking = new GridBagLayout();
		gbl_Stocking.columnWidths = new int[]{0};
		gbl_Stocking.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_Stocking.columnWeights = new double[]{0.0};
		gbl_Stocking.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		this.setLayout(gbl_Stocking);
		this.setBackground(Color.BLUE);
		JLabel lbl_StockingGroceryStock = new JLabel("Grocery Stock");
		lbl_StockingGroceryStock.setForeground(Color.WHITE);
		lbl_StockingGroceryStock.setFont(new Font("Tahoma", Font.BOLD, 17));
		GridBagConstraints gbc_lbl_StockingGroceryStock = new GridBagConstraints();
		gbc_lbl_StockingGroceryStock.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_StockingGroceryStock.anchor = GridBagConstraints.WEST;
		gbc_lbl_StockingGroceryStock.gridx = 0;
		gbc_lbl_StockingGroceryStock.gridy = 0;
		JLabel lbl_StockingRetailStock = new JLabel("Retail Stock");
		lbl_StockingRetailStock.setForeground(Color.WHITE);
		lbl_StockingRetailStock.setFont(new Font("Tahoma", Font.BOLD, 17));
		GridBagConstraints gbc_lbl_StockingRetailStock = new GridBagConstraints();
		gbc_lbl_StockingRetailStock.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_StockingRetailStock.anchor = GridBagConstraints.WEST;
		gbc_lbl_StockingRetailStock.gridx = 1;
		gbc_lbl_StockingRetailStock.gridy = 0;
		JLabel lbl_StockingStockCount = new JLabel("Stock Count");
		lbl_StockingStockCount.setForeground(Color.WHITE);
		lbl_StockingStockCount.setFont(new Font("Tahoma", Font.BOLD, 17));
		GridBagConstraints gbc_lbl_StockingStockCount = new GridBagConstraints();
		gbc_lbl_StockingStockCount.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_StockingStockCount.anchor = GridBagConstraints.WEST;
		gbc_lbl_StockingStockCount.gridx = 0;
		gbc_lbl_StockingStockCount.gridy = 3;
		gbc_lbl_StockingStockCount.gridwidth = 2;
		gbc_lbl_StockingStockCount.anchor = GridBagConstraints.NORTH;
		//add model into grocery items stocked
		tbl_StockingGrocerItems = new JTable(StoreWindow.getGrocerExcelData(false));
		tbl_StockingGrocerItems.setRowSelectionAllowed(false);
		//setup table design
		tbl_StockingGrocerItems.getColumnModel().getColumn(0).setResizable(false);
		tbl_StockingGrocerItems.getColumnModel().getColumn(0).setPreferredWidth(90);
		tbl_StockingGrocerItems.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		GridBagConstraints gbc_tbl_StockingGrocerItems = new GridBagConstraints();
		gbc_tbl_StockingGrocerItems.fill = GridBagConstraints.BOTH;
		gbc_tbl_StockingGrocerItems.insets = new Insets(5, 5, 5, 5);
		gbc_tbl_StockingGrocerItems.weightx = 1.0;
		gbc_tbl_StockingGrocerItems.weighty = 0.5;
		gbc_tbl_StockingGrocerItems.gridx = 0;
		gbc_tbl_StockingGrocerItems.gridy = 1;
		GridBagLayout gbl_tbl_GrocerItems = new GridBagLayout();
		gbl_tbl_GrocerItems.columnWidths = new int[]{289, 1, 0};
		gbl_tbl_GrocerItems.rowHeights = new int[]{1, 0};
		gbl_tbl_GrocerItems.columnWeights = new double[]{};
		gbl_tbl_GrocerItems.rowWeights = new double[]{};
		tbl_StockingGrocerItems.setLayout(gbl_tbl_GrocerItems);
		//add scrollpane to table
		JScrollPane scrl_StockingGrocerItems=new JScrollPane(tbl_StockingGrocerItems);
		//add model to retail items in stock
		tbl_StockingRetailItems = new JTable(StoreWindow.getRetailExcelData(false));
		tbl_StockingRetailItems.setRowSelectionAllowed(false);
		//setup table design
		tbl_StockingRetailItems.getColumnModel().getColumn(0).setResizable(false);
		tbl_StockingRetailItems.getColumnModel().getColumn(0).setPreferredWidth(90);
		tbl_StockingRetailItems.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		GridBagConstraints gbc_tbl_StockingRetailItems = new GridBagConstraints();
		gbc_tbl_StockingRetailItems.fill = GridBagConstraints.BOTH;
		gbc_tbl_StockingRetailItems.insets = new Insets(5, 5, 5, 5);
		gbc_tbl_StockingRetailItems.weightx = 1.0;
		gbc_tbl_StockingRetailItems.weighty = 0.5;
		gbc_tbl_StockingRetailItems.gridx = 1;
		gbc_tbl_StockingRetailItems.gridy = 1;
		GridBagLayout gbl_tbl_RetailItems = new GridBagLayout();
		gbl_tbl_RetailItems.columnWidths = new int[]{289, 1, 0};
		gbl_tbl_RetailItems.rowHeights = new int[]{1, 0};
		gbl_tbl_RetailItems.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_tbl_RetailItems.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		tbl_StockingGrocerItems.setLayout(gbl_tbl_RetailItems);
		//add scrollpane to table
		JScrollPane scrl_StockingRetailItems=new JScrollPane(tbl_StockingRetailItems);
		//buttons for stock migration options
		JButton btn_StockingMigrateToRetail = new JButton("Migrate to Retail");
		GridBagConstraints gbc_btn_StockingMigrateToRetail = new GridBagConstraints();
		gbc_btn_StockingMigrateToRetail.insets = new Insets(0, 0, 5, 5);
		gbc_btn_StockingMigrateToRetail.gridx = 0;
		gbc_btn_StockingMigrateToRetail.gridy = 2;
		JButton btn_StockingMigratetoGrocer = new JButton("Migrate to Grocer");
		GridBagConstraints gbc_btn_StockingMigratetoGrocer = new GridBagConstraints();
		gbc_btn_StockingMigratetoGrocer.insets = new Insets(0, 0, 5, 0);
		gbc_btn_StockingMigratetoGrocer.gridx = 1;
		gbc_btn_StockingMigratetoGrocer.gridy = 2;
		//add model into raw Grocer item counts
		tbl_StockSetGrocer = new JTable(StoreWindow.getStockCountSets(false,false));
		tbl_StockSetGrocer.setRowSelectionAllowed(false);
		tbl_StockSetGrocer.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		GridBagConstraints gbc_tbl_StockSetGrocer = new GridBagConstraints();
		gbc_tbl_StockSetGrocer.fill = GridBagConstraints.BOTH;
		gbc_tbl_StockSetGrocer.insets = new Insets(5, 5, 5, 0);
		gbc_tbl_StockSetGrocer.weightx = 1.0;
		gbc_tbl_StockSetGrocer.weighty = 1.0;
		gbc_tbl_StockSetGrocer.gridx = 0;
		gbc_tbl_StockSetGrocer.gridy = 4;
		GridBagLayout gbl_tbl_StockSetGrocer = new GridBagLayout();
		gbl_tbl_StockSetGrocer.columnWidths = new int[]{289, 1, 0};
		gbl_tbl_StockSetGrocer.rowHeights = new int[]{1, 0};
		gbl_tbl_StockSetGrocer.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_tbl_StockSetGrocer.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		tbl_StockSetGrocer.setLayout(gbl_tbl_StockSetGrocer);
		//add scrollpane to table
		JScrollPane scrl_StockSetGrocer=new JScrollPane(tbl_StockSetGrocer);
		scrl_StockSetGrocer.setVisible(true);
		//add model into raw retail item counts
		tbl_StockSetRetail = new JTable(StoreWindow.getStockCountSets(true,false));
		tbl_StockSetRetail.setRowSelectionAllowed(false);
		tbl_StockSetRetail.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		GridBagConstraints gbc_tbl_StockSetRetail = new GridBagConstraints();
		gbc_tbl_StockSetRetail.fill = GridBagConstraints.BOTH;
		gbc_tbl_StockSetRetail.insets = new Insets(5, 5, 5, 5);
		gbc_tbl_StockSetRetail.weightx = 1.0;
		gbc_tbl_StockSetRetail.weighty = 1.0;
		gbc_tbl_StockSetRetail.gridx = 1;
		gbc_tbl_StockSetRetail.gridy = 4;
		GridBagLayout gbl_tbl_StockSetRetail = new GridBagLayout();
		gbl_tbl_StockSetRetail.columnWidths = new int[]{289, 1, 0};
		gbl_tbl_StockSetRetail.rowHeights = new int[]{1, 0};
		gbl_tbl_StockSetRetail.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_tbl_StockSetRetail.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		tbl_StockSetRetail.setLayout(gbl_tbl_StockSetRetail);
		//add scrollpane to table
		JScrollPane scrl_StockSetRetail=new JScrollPane(tbl_StockSetRetail);
		scrl_StockSetRetail.setVisible(true);
		
		//add restock Grocer Dialog components
		JPanel pnl_DialogStockingAddGrocerItem = new JPanel();
		JLabel lbl_DialogAddGrocerItemId = new JLabel("ID : ");
		JTextField txt_DialogAddGrocerItemCode = new JTextField();
		txt_DialogAddGrocerItemCode.setColumns(10);
		JLabel lbl_DialogAddGrocerItemName = new JLabel("Name : ");
		JComboBox<String> cmb_DialogAddGrocerItemName = new JComboBox<String>();
		cmb_DialogAddGrocerItemName.setModel(new DefaultComboBoxModel<String>(StoreWindow.getItemTypes(false)));
		JLabel lbl_DialogAddGrocerItemCost = new JLabel("Cost : ");
		JFormattedTextField txt_DialogAddGrocerItemCost =  new JFormattedTextField(StoreWindow.currency);
		txt_DialogAddGrocerItemCost.setEditable(false);
		txt_DialogAddGrocerItemCost.setColumns(10);
		
		//add restock Retail Dialog components
		JPanel pnl_DialogStockingAddRetailItem = new JPanel();
		JLabel lbl_DialogAddRetailItemId = new JLabel("ID : ");
		JTextField txt_DialogAddRetailItemCode = new JTextField();
		txt_DialogAddRetailItemCode.setColumns(10);
		JLabel lbl_DialogAddRetailItemName = new JLabel("Name : ");
		JComboBox<String> cmb_DialogAddRetailItemName = new JComboBox<String>();
		cmb_DialogAddRetailItemName.setModel(new DefaultComboBoxModel<String>(StoreWindow.getItemTypes(true)));
		JLabel lbl_DialogAddRetailItemCost = new JLabel("Cost : ");
		JFormattedTextField txt_DialogAddRetailItemCost =  new JFormattedTextField(StoreWindow.currency);
		txt_DialogAddRetailItemCost.setEditable(false);
		txt_DialogAddRetailItemCost.setColumns(10);
		JLabel lbl_DialogAddRetailItemStack = new JLabel("Stack : ");
		JTextField txt_DialogAddRetailItemStack = new JTextField();
		txt_DialogAddRetailItemStack.setColumns(10);
		txt_DialogAddRetailItemStack.setEditable(false);
		
		//buttons to show add new stock to respective tables
		JButton btn_DialogGroceryStockAddItem = new JButton("Add Grocery Stock");
		GridBagConstraints gbc_btn_DialogGroceryStockAddItem = new GridBagConstraints();
		gbc_btn_DialogGroceryStockAddItem.insets = new Insets(5, 0, 10, 0);
		gbc_btn_DialogGroceryStockAddItem.fill = GridBagConstraints.VERTICAL;
		gbc_btn_DialogGroceryStockAddItem.gridx = 0;
		gbc_btn_DialogGroceryStockAddItem.gridy = 5;
		JButton btn_DialogRetailStockAddItem = new JButton("Add Retail Stock");
		GridBagConstraints gbc_btn_DialogRetailStockAddItem = new GridBagConstraints();
		gbc_btn_DialogRetailStockAddItem.insets = new Insets(5, 0, 10, 0);
		gbc_btn_DialogRetailStockAddItem.fill = GridBagConstraints.VERTICAL;
		gbc_btn_DialogRetailStockAddItem.gridx = 1;
		gbc_btn_DialogRetailStockAddItem.gridy = 5;
		
		//Dialogbox to migrate grocery to retail
  		JPanel pnl_DialogGrocerToRetail = new JPanel();
  		GridBagLayout gbl_pnl_DialogGrocerToRetail = new GridBagLayout();
  		gbl_pnl_DialogGrocerToRetail.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
  		gbl_pnl_DialogGrocerToRetail.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
  		gbl_pnl_DialogGrocerToRetail.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
  		gbl_pnl_DialogGrocerToRetail.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
  		pnl_DialogGrocerToRetail.setLayout(gbl_pnl_DialogGrocerToRetail);
  		JLabel lbl_DialogGrocerToRetailSelectedItem = new JLabel("Select item to pack : ");
  		GridBagConstraints gbc_lbl_DialogGrocerToRetailSelectedItem = new GridBagConstraints();
  		gbc_lbl_DialogGrocerToRetailSelectedItem.anchor = GridBagConstraints.WEST;
  		gbc_lbl_DialogGrocerToRetailSelectedItem.insets = new Insets(0, 0, 5, 5);
  		gbc_lbl_DialogGrocerToRetailSelectedItem.gridx = 0;
  		gbc_lbl_DialogGrocerToRetailSelectedItem.gridy = 0;
  		JLabel lbl_DialogGrocerToRetailSelectedId = new JLabel("ID");
  		GridBagConstraints gbc_lbl_DialogGrocerToRetailSelectedId = new GridBagConstraints();
  		gbc_lbl_DialogGrocerToRetailSelectedId.insets = new Insets(0, 0, 5, 5);
  		gbc_lbl_DialogGrocerToRetailSelectedId.gridx = 1;
  		gbc_lbl_DialogGrocerToRetailSelectedId.gridy = 0;
  		JLabel lbl_DialogGrocerToRetailSelectedName = new JLabel("Name");
  		GridBagConstraints gbc_lbl_DialogGrocerToRetailSelectedName = new GridBagConstraints();
  		gbc_lbl_DialogGrocerToRetailSelectedName.insets = new Insets(0, 0, 5, 5);
  		gbc_lbl_DialogGrocerToRetailSelectedName.gridx = 2;
  		gbc_lbl_DialogGrocerToRetailSelectedName.gridy = 0;
  		JLabel lbl_DialogGrocerToRetailSelectedCost = new JLabel("Cost");
  		GridBagConstraints gbc_lbl_DialogGrocerToRetailSelectedCost = new GridBagConstraints();
  		gbc_lbl_DialogGrocerToRetailSelectedCost.insets = new Insets(0, 0, 5, 5);
  		gbc_lbl_DialogGrocerToRetailSelectedCost.gridx = 3;
  		gbc_lbl_DialogGrocerToRetailSelectedCost.gridy = 0;
  		cmb_DialogGrocerToRetailSelectedID = new JComboBox<String>();
  		cmb_DialogGrocerToRetailSelectedID.setModel(new DefaultComboBoxModel<String>(StoreWindow.getExcelUniqueSet(false)));
  		cmb_DialogGrocerToRetailSelectedID.setEditable(true);
  		GridBagConstraints gbc_cmb_DialogGrocerToRetailSelectedID = new GridBagConstraints();
  		gbc_cmb_DialogGrocerToRetailSelectedID.insets = new Insets(0, 0, 5, 5);
  		gbc_cmb_DialogGrocerToRetailSelectedID.fill = GridBagConstraints.HORIZONTAL;
  		gbc_cmb_DialogGrocerToRetailSelectedID.gridx = 1;
  		gbc_cmb_DialogGrocerToRetailSelectedID.gridy = 1;
  		JTextField txt_DialogGrocerToRetailSelectedName = new JTextField();
  		txt_DialogGrocerToRetailSelectedName.setEditable(false);
  		txt_DialogGrocerToRetailSelectedName.setColumns(10);
  		GridBagConstraints gbc_txt_DialogGrocerToRetailSelectedName = new GridBagConstraints();
  		gbc_txt_DialogGrocerToRetailSelectedName.insets = new Insets(0, 0, 5, 5);
  		gbc_txt_DialogGrocerToRetailSelectedName.fill = GridBagConstraints.HORIZONTAL;
  		gbc_txt_DialogGrocerToRetailSelectedName.gridx = 2;
  		gbc_txt_DialogGrocerToRetailSelectedName.gridy = 1;
  		JFormattedTextField txt_DialogGrocerToRetailSelectedCost = new JFormattedTextField(StoreWindow.currency);
  		txt_DialogGrocerToRetailSelectedCost.setEditable(false);
  		txt_DialogGrocerToRetailSelectedCost.setColumns(10);
  		GridBagConstraints gbc_txt_DialogGrocerToRetailSelectedCost = new GridBagConstraints();
  		gbc_txt_DialogGrocerToRetailSelectedCost.insets = new Insets(0, 0, 5, 5);
  		gbc_txt_DialogGrocerToRetailSelectedCost.fill = GridBagConstraints.HORIZONTAL;
  		gbc_txt_DialogGrocerToRetailSelectedCost.gridx = 3;
  		gbc_txt_DialogGrocerToRetailSelectedCost.gridy = 1;
  		JFormattedTextField txt_DialogGrocerToRetailSelectedPrincipal = new JFormattedTextField(StoreWindow.currency);
  		txt_DialogGrocerToRetailSelectedPrincipal.setEditable(false);
  		txt_DialogGrocerToRetailSelectedPrincipal.setColumns(10);
  		GridBagConstraints gbc_txt_DialogGrocerToRetailSelectedPrincipal = new GridBagConstraints();
  		gbc_txt_DialogGrocerToRetailSelectedPrincipal.insets = new Insets(0, 0, 5, 0);
  		gbc_txt_DialogGrocerToRetailSelectedPrincipal.fill = GridBagConstraints.HORIZONTAL;
  		gbc_txt_DialogGrocerToRetailSelectedPrincipal.gridx = 4;
  		gbc_txt_DialogGrocerToRetailSelectedPrincipal.gridy = 1;
  		txt_DialogGrocerToRetailSelectedPrincipal.setVisible(false);
  		JLabel lbl_DialogGrocerToRetailItemsToMove = new JLabel("Items to move:");
  		GridBagConstraints gbc_lbl_DialogGrocerToRetailItemsToMove = new GridBagConstraints();
  		gbc_lbl_DialogGrocerToRetailItemsToMove.insets = new Insets(0, 0, 5, 5);
  		gbc_lbl_DialogGrocerToRetailItemsToMove.anchor = GridBagConstraints.WEST;
  		gbc_lbl_DialogGrocerToRetailItemsToMove.gridx = 0;
  		gbc_lbl_DialogGrocerToRetailItemsToMove.gridy = 2;
  		JTextField txt_DialogGrocerToRetailListedItems = new JTextField("0");
  		txt_DialogGrocerToRetailListedItems.setEditable(false);
  		GridBagConstraints gbc_txt_DialogGrocerToRetailListedItems = new GridBagConstraints();
  		gbc_txt_DialogGrocerToRetailListedItems.insets = new Insets(0, 0, 5, 5);
  		gbc_txt_DialogGrocerToRetailListedItems.anchor = GridBagConstraints.WEST;
  		gbc_txt_DialogGrocerToRetailListedItems.gridx = 1;
  		gbc_txt_DialogGrocerToRetailListedItems.gridy = 2;
  		JButton btn_DialogGrocerToRetailSelectedAdd = new JButton("Add");
  		GridBagConstraints gbc_btn_DialogGrocerToRetailSelectedAdd = new GridBagConstraints();
  		gbc_btn_DialogGrocerToRetailSelectedAdd.insets = new Insets(0, 0, 5, 5);
  		gbc_btn_DialogGrocerToRetailSelectedAdd.anchor = GridBagConstraints.CENTER;
  		gbc_btn_DialogGrocerToRetailSelectedAdd.gridx = 2;
  		gbc_btn_DialogGrocerToRetailSelectedAdd.gridy = 2;
  		tbl_DialogGrocerToRetailToMoveList = new JTable();
  		tbl_DialogGrocerToRetailToMoveList.setModel(new DefaultTableModel(null,StoreWindow.grocerStandardTableModel));
  		JScrollPane scrl_DialogGrocerToRetailToMoveList = new JScrollPane(tbl_DialogGrocerToRetailToMoveList);
  		scrl_DialogGrocerToRetailToMoveList.setVisible(true);
  		GridBagConstraints gbc_scrl_DialogGrocerToRetailToMoveList = new GridBagConstraints();
  		gbc_scrl_DialogGrocerToRetailToMoveList.insets = new Insets(0, 0, 5, 5);
  		gbc_scrl_DialogGrocerToRetailToMoveList.fill = GridBagConstraints.HORIZONTAL;
  		gbc_scrl_DialogGrocerToRetailToMoveList.gridx = 0;
  		gbc_scrl_DialogGrocerToRetailToMoveList.gridwidth = 5;
  		gbc_scrl_DialogGrocerToRetailToMoveList.gridy = 3;
  		JLabel lbl_DialogGrocerToRetailTo = new JLabel("To");
  		GridBagConstraints gbc_lbl_DialogGrocerToRetailTo = new GridBagConstraints();
  		gbc_lbl_DialogGrocerToRetailTo.gridwidth = 5;
  		gbc_lbl_DialogGrocerToRetailTo.insets = new Insets(0, 0, 5, 0);
  		gbc_lbl_DialogGrocerToRetailTo.gridx = 0;
  		gbc_lbl_DialogGrocerToRetailTo.gridy = 4;
  		JLabel lbl_DialogGrocerToRetailRetailId = new JLabel("ID");
  		GridBagConstraints gbc_lbl_DialogGrocerToRetailRetailId = new GridBagConstraints();
  		gbc_lbl_DialogGrocerToRetailRetailId.insets = new Insets(0, 0, 5, 5);
  		gbc_lbl_DialogGrocerToRetailRetailId.gridx = 0;
  		gbc_lbl_DialogGrocerToRetailRetailId.gridy = 5;
  		JLabel lbl_DialogGrocerToRetailRetailName = new JLabel("Name");
  		GridBagConstraints gbc_lbl_DialogGrocerToRetailRetailName = new GridBagConstraints();
  		gbc_lbl_DialogGrocerToRetailRetailName.insets = new Insets(0, 0, 5, 5);
  		gbc_lbl_DialogGrocerToRetailRetailName.gridx = 1;
  		gbc_lbl_DialogGrocerToRetailRetailName.gridy = 5;
  		JLabel lbl_DialogGrocerToRetailRetailCost = new JLabel("Cost");
  		GridBagConstraints gbc_lbl_DialogGrocerToRetailRetailCost = new GridBagConstraints();
  		gbc_lbl_DialogGrocerToRetailRetailCost.insets = new Insets(0, 0, 5, 5);
  		gbc_lbl_DialogGrocerToRetailRetailCost.gridx = 2;
  		gbc_lbl_DialogGrocerToRetailRetailCost.gridy = 5;
  		JLabel lblStack = new JLabel("Stack");
  		GridBagConstraints gbc_lblStack = new GridBagConstraints();
  		gbc_lblStack.insets = new Insets(0, 0, 5, 5);
  		gbc_lblStack.gridx = 3;
  		gbc_lblStack.gridy = 5;
  		pnl_DialogGrocerToRetail.add(lblStack, gbc_lblStack);
  		JTextField txt_DialogGrocerToRetailRetailId = new JTextField();
  		GridBagConstraints gbc_txt_DialogGrocerToRetailRetailId = new GridBagConstraints();
  		gbc_txt_DialogGrocerToRetailRetailId.insets = new Insets(0, 0, 5, 5);
  		gbc_txt_DialogGrocerToRetailRetailId.gridx = 0;
  		gbc_txt_DialogGrocerToRetailRetailId.gridy = 6;
  		txt_DialogGrocerToRetailRetailId.setColumns(10);
  		cmb_DialogGrocerToRetailRetailName = new JComboBox<String>();
  		cmb_DialogGrocerToRetailRetailName.setModel(new DefaultComboBoxModel<String>(StoreWindow.getItemTypes(true)));
  		GridBagConstraints gbc_cmb_DialogGrocerToRetailRetailName = new GridBagConstraints();
  		gbc_cmb_DialogGrocerToRetailRetailName.insets = new Insets(0, 0, 5, 5);
  		gbc_cmb_DialogGrocerToRetailRetailName.fill = GridBagConstraints.HORIZONTAL;
  		gbc_cmb_DialogGrocerToRetailRetailName.gridx = 1;
  		gbc_cmb_DialogGrocerToRetailRetailName.gridy = 6;
  		JFormattedTextField txt_DialogGrocerToRetailRetailCost = new JFormattedTextField(StoreWindow.currency);
  		txt_DialogGrocerToRetailRetailCost.setEditable(false);
  		GridBagConstraints gbc_txt_DialogGrocerToRetailRetailCost = new GridBagConstraints();
  		gbc_txt_DialogGrocerToRetailRetailCost.insets = new Insets(0, 0, 5, 5);
  		gbc_txt_DialogGrocerToRetailRetailCost.fill = GridBagConstraints.HORIZONTAL;
  		gbc_txt_DialogGrocerToRetailRetailCost.gridx = 2;
  		gbc_txt_DialogGrocerToRetailRetailCost.gridy = 6;
  		txt_DialogGrocerToRetailRetailCost.setColumns(10);
  		JFormattedTextField txt_DialogGrocerToRetailRetailStack = new JFormattedTextField(NumberFormat.getIntegerInstance());
  		txt_DialogGrocerToRetailRetailStack.setEditable(false);
  		GridBagConstraints gbc_txt_DialogGrocerToRetailRetailStack = new GridBagConstraints();
  		gbc_txt_DialogGrocerToRetailRetailStack.insets = new Insets(0, 0, 5, 5);
  		gbc_txt_DialogGrocerToRetailRetailStack.fill = GridBagConstraints.HORIZONTAL;
  		gbc_txt_DialogGrocerToRetailRetailStack.gridx = 3;
  		gbc_txt_DialogGrocerToRetailRetailStack.gridy = 6;
  		txt_DialogGrocerToRetailRetailStack.setColumns(10);
  		JFormattedTextField txt_DialogGrocerToRetailRetailPrincipal = new JFormattedTextField(StoreWindow.currency);
  		txt_DialogGrocerToRetailRetailPrincipal.setEditable(false);
  		GridBagConstraints gbc_txt_DialogGrocerToRetailRetailPrincipal = new GridBagConstraints();
  		gbc_txt_DialogGrocerToRetailRetailPrincipal.insets = new Insets(0, 0, 5, 0);
  		gbc_txt_DialogGrocerToRetailRetailPrincipal.fill = GridBagConstraints.HORIZONTAL;
  		gbc_txt_DialogGrocerToRetailRetailPrincipal.gridx = 4;
  		gbc_txt_DialogGrocerToRetailRetailPrincipal.gridy = 6;
  		txt_DialogGrocerToRetailRetailPrincipal.setColumns(10);
  		txt_DialogGrocerToRetailRetailPrincipal.setVisible(false);
  		JButton btn_DialogGrocerToRetailReset = new JButton("Reset");
  		GridBagConstraints gbc_btn_DialogGrocerToRetailReset = new GridBagConstraints();
  		gbc_btn_DialogGrocerToRetailReset.insets = new Insets(5, 5, 5, 5);
  		gbc_btn_DialogGrocerToRetailReset.anchor = GridBagConstraints.CENTER;
  		gbc_btn_DialogGrocerToRetailReset.gridwidth = 5;
  		gbc_btn_DialogGrocerToRetailReset.gridx = 0;
  		gbc_btn_DialogGrocerToRetailReset.gridy = 7;
  		
  		//Dialogbox to migrate grocery to retail
  		JPanel pnl_DialogRetailToGrocer = new JPanel();
  		GridBagLayout gbl_pnl_DialogRetailToGrocer = new GridBagLayout();
  		gbl_pnl_DialogRetailToGrocer.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
  		gbl_pnl_DialogRetailToGrocer.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
  		gbl_pnl_DialogRetailToGrocer.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
  		gbl_pnl_DialogRetailToGrocer.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
  		pnl_DialogRetailToGrocer.setLayout(gbl_pnl_DialogRetailToGrocer);
  		JLabel lbl_DialogRetailToGrocerSelectedItem = new JLabel("Select item to unpack : ");
  		GridBagConstraints gbc_lbl_DialogRetailToGrocerSelectedItem = new GridBagConstraints();
  		gbc_lbl_DialogRetailToGrocerSelectedItem.anchor = GridBagConstraints.WEST;
  		gbc_lbl_DialogRetailToGrocerSelectedItem.insets = new Insets(0, 0, 5, 5);
  		gbc_lbl_DialogRetailToGrocerSelectedItem.gridx = 0;
  		gbc_lbl_DialogRetailToGrocerSelectedItem.gridy = 0;
  		JLabel lbl_DialogRetailToGrocerSelectedId = new JLabel("ID");
  		GridBagConstraints gbc_lbl_DialogRetailToGrocerSelectedId = new GridBagConstraints();
  		gbc_lbl_DialogRetailToGrocerSelectedId.insets = new Insets(0, 0, 5, 5);
  		gbc_lbl_DialogRetailToGrocerSelectedId.gridx = 0;
  		gbc_lbl_DialogRetailToGrocerSelectedId.gridy = 1;
  		JLabel lbl_DialogRetailToGrocerSelectedName = new JLabel("Name");
  		GridBagConstraints gbc_lbl_DialogRetailToGrocerSelectedName = new GridBagConstraints();
  		gbc_lbl_DialogRetailToGrocerSelectedName.insets = new Insets(0, 0, 5, 5);
  		gbc_lbl_DialogRetailToGrocerSelectedName.gridx = 1;
  		gbc_lbl_DialogRetailToGrocerSelectedName.gridy = 1;
  		JLabel lbl_DialogRetailToGrocerSelectedCost = new JLabel("Cost");
  		GridBagConstraints gbc_lbl_DialogRetailToGrocerSelectedCost = new GridBagConstraints();
  		gbc_lbl_DialogRetailToGrocerSelectedCost.insets = new Insets(0, 0, 5, 5);
  		gbc_lbl_DialogRetailToGrocerSelectedCost.gridx = 2;
  		gbc_lbl_DialogRetailToGrocerSelectedCost.gridy = 1;
  		JLabel lbl_DialogRetailToGrocerSelectedStack = new JLabel("Stack");
  		GridBagConstraints gbc_lbl_DialogRetailToGrocerSelectedStack = new GridBagConstraints();
  		gbc_lbl_DialogRetailToGrocerSelectedStack.insets = new Insets(0, 0, 5, 5);
  		gbc_lbl_DialogRetailToGrocerSelectedStack.gridx = 3;
  		gbc_lbl_DialogRetailToGrocerSelectedStack.gridy = 1;
  		cmb_DialogRetailToGrocerSelectedID = new JComboBox<String>();
  		cmb_DialogRetailToGrocerSelectedID.setModel(new DefaultComboBoxModel<String>(StoreWindow.getExcelUniqueSet(true)));
  		cmb_DialogRetailToGrocerSelectedID.setEditable(true);
  		GridBagConstraints gbc_cmb_DialogRetailToGrocerSelectedID = new GridBagConstraints();
  		gbc_cmb_DialogRetailToGrocerSelectedID.insets = new Insets(0, 0, 5, 5);
  		gbc_cmb_DialogRetailToGrocerSelectedID.fill = GridBagConstraints.HORIZONTAL;
  		gbc_cmb_DialogRetailToGrocerSelectedID.gridx = 0;
  		gbc_cmb_DialogRetailToGrocerSelectedID.gridy = 2;
  		JTextField txt_DialogRetailToGrocerSelectedName = new JTextField();
  		txt_DialogRetailToGrocerSelectedName.setEditable(false);
  		txt_DialogRetailToGrocerSelectedName.setColumns(10);
  		GridBagConstraints gbc_txt_DialogRetailToGrocerSelectedName = new GridBagConstraints();
  		gbc_txt_DialogRetailToGrocerSelectedName.insets = new Insets(0, 0, 5, 5);
  		gbc_txt_DialogRetailToGrocerSelectedName.fill = GridBagConstraints.HORIZONTAL;
  		gbc_txt_DialogRetailToGrocerSelectedName.gridx = 1;
  		gbc_txt_DialogRetailToGrocerSelectedName.gridy = 2;
  		JFormattedTextField txt_DialogRetailToGrocerSelectedCost = new JFormattedTextField(StoreWindow.currency);
  		txt_DialogRetailToGrocerSelectedCost.setEditable(false);
  		txt_DialogRetailToGrocerSelectedCost.setColumns(10);
  		GridBagConstraints gbc_txt_DialogRetailToGrocerSelectedCost = new GridBagConstraints();
  		gbc_txt_DialogRetailToGrocerSelectedCost.insets = new Insets(0, 0, 5, 5);
  		gbc_txt_DialogRetailToGrocerSelectedCost.fill = GridBagConstraints.HORIZONTAL;
  		gbc_txt_DialogRetailToGrocerSelectedCost.gridx = 2;
  		gbc_txt_DialogRetailToGrocerSelectedCost.gridy = 2;
  		JTextField txt_DialogRetailToGrocerSelectedStack = new JTextField();
  		txt_DialogRetailToGrocerSelectedStack.setEditable(false);
  		txt_DialogRetailToGrocerSelectedStack.setColumns(10);
  		GridBagConstraints gbc_txt_DialogRetailToGrocerSelectedStack = new GridBagConstraints();
  		gbc_txt_DialogRetailToGrocerSelectedStack.insets = new Insets(0, 0, 5, 5);
  		gbc_txt_DialogRetailToGrocerSelectedStack.fill = GridBagConstraints.HORIZONTAL;
  		gbc_txt_DialogRetailToGrocerSelectedStack.gridx = 3;
  		gbc_txt_DialogRetailToGrocerSelectedStack.gridy = 2;
  		JFormattedTextField txt_DialogRetailToGrocerSelectedCOGS = new JFormattedTextField(StoreWindow.currency);
  		txt_DialogRetailToGrocerSelectedCOGS.setEditable(false);
  		txt_DialogRetailToGrocerSelectedCOGS.setColumns(10);
  		txt_DialogRetailToGrocerSelectedCOGS.setVisible(false);
  		GridBagConstraints gbc_txt_DialogRetailToGrocerSelectedCOGS = new GridBagConstraints();
  		gbc_txt_DialogRetailToGrocerSelectedCOGS.insets = new Insets(0, 0, 5, 0);
  		gbc_txt_DialogRetailToGrocerSelectedCOGS.fill = GridBagConstraints.HORIZONTAL;
  		gbc_txt_DialogRetailToGrocerSelectedCOGS.gridx = 4;
  		gbc_txt_DialogRetailToGrocerSelectedCOGS.gridy = 2;
  		JLabel lbl_DialogRetailToGrocerTo = new JLabel("To");
  		GridBagConstraints gbc_lbl_DialogRetailToGrocerTo = new GridBagConstraints();
  		gbc_lbl_DialogRetailToGrocerTo.gridwidth = 5;
  		gbc_lbl_DialogRetailToGrocerTo.insets = new Insets(0, 0, 5, 0);
  		gbc_lbl_DialogRetailToGrocerTo.gridx = 0;
  		gbc_lbl_DialogRetailToGrocerTo.gridy = 3;
  		JLabel lbl_DialogRetailToGrocerGrocerId = new JLabel("ID");
  		GridBagConstraints gbc_lbl_DialogRetailToGrocerGrocerId = new GridBagConstraints();
  		gbc_lbl_DialogRetailToGrocerGrocerId.insets = new Insets(0, 0, 5, 5);
  		gbc_lbl_DialogRetailToGrocerGrocerId.gridx = 0;
  		gbc_lbl_DialogRetailToGrocerGrocerId.gridy = 4;
  		JLabel lbl_DialogRetailToGrocerGrocerName = new JLabel("Name");
  		GridBagConstraints gbc_lbl_DialogRetailToGrocerGrocerName = new GridBagConstraints();
  		gbc_lbl_DialogRetailToGrocerGrocerName.insets = new Insets(0, 0, 5, 5);
  		gbc_lbl_DialogRetailToGrocerGrocerName.gridx = 1;
  		gbc_lbl_DialogRetailToGrocerGrocerName.gridy = 4;
  		JLabel lbl_DialogRetailToGrocerGrocerCost = new JLabel("Cost");
  		GridBagConstraints gbc_lbl_DialogRetailToGrocerGrocerCost = new GridBagConstraints();
  		gbc_lbl_DialogRetailToGrocerGrocerCost.insets = new Insets(0, 0, 5, 5);
  		gbc_lbl_DialogRetailToGrocerGrocerCost.gridx = 2;
  		gbc_lbl_DialogRetailToGrocerGrocerCost.gridy = 4;
  		JTextField txt_DialogRetailToGrocerGrocerId = new JTextField();
  		GridBagConstraints gbc_txt_DialogRetailToGrocerGrocerId = new GridBagConstraints();
  		gbc_txt_DialogRetailToGrocerGrocerId.insets = new Insets(0, 0, 5, 5);
  		gbc_txt_DialogRetailToGrocerGrocerId.gridx = 0;
  		gbc_txt_DialogRetailToGrocerGrocerId.gridy = 5;
  		txt_DialogRetailToGrocerGrocerId.setColumns(10);
  		cmb_DialogRetailToGrocerGrocerName = new JComboBox<String>();
  		cmb_DialogRetailToGrocerGrocerName.setModel(new DefaultComboBoxModel<String>(StoreWindow.getItemTypes(false)));
  		GridBagConstraints gbc_cmb_DialogRetailToGrocerGrocerName = new GridBagConstraints();
  		gbc_cmb_DialogRetailToGrocerGrocerName.insets = new Insets(0, 0, 5, 5);
  		gbc_cmb_DialogRetailToGrocerGrocerName.fill = GridBagConstraints.HORIZONTAL;
  		gbc_cmb_DialogRetailToGrocerGrocerName.gridx = 1;
  		gbc_cmb_DialogRetailToGrocerGrocerName.gridy = 5;
  		JFormattedTextField txt_DialogRetailToGrocerGrocerCost = new JFormattedTextField(StoreWindow.currency);
  		txt_DialogRetailToGrocerGrocerCost.setEditable(false);
  		GridBagConstraints gbc_txt_DialogRetailToGrocerGrocerCost = new GridBagConstraints();
  		gbc_txt_DialogRetailToGrocerGrocerCost.insets = new Insets(0, 0, 5, 5);
  		gbc_txt_DialogRetailToGrocerGrocerCost.fill = GridBagConstraints.HORIZONTAL;
  		gbc_txt_DialogRetailToGrocerGrocerCost.gridx = 2;
  		gbc_txt_DialogRetailToGrocerGrocerCost.gridy = 5;
  		txt_DialogRetailToGrocerGrocerCost.setColumns(10);
  		JFormattedTextField txt_DialogRetailToGrocerGrocerCOGS = new JFormattedTextField(StoreWindow.currency);
  		txt_DialogRetailToGrocerGrocerCOGS.setEditable(false);
  		GridBagConstraints gbc_txt_DialogRetailToGrocerGrocerCOGS = new GridBagConstraints();
  		gbc_txt_DialogRetailToGrocerGrocerCOGS.insets = new Insets(0, 0, 5, 0);
  		gbc_txt_DialogRetailToGrocerGrocerCOGS.fill = GridBagConstraints.HORIZONTAL;
  		gbc_txt_DialogRetailToGrocerGrocerCOGS.gridx = 3;
  		gbc_txt_DialogRetailToGrocerGrocerCOGS.gridy = 5;
  		txt_DialogRetailToGrocerGrocerCOGS.setColumns(10);
  		txt_DialogRetailToGrocerGrocerCOGS.setVisible(false);
  		JButton btn_DialogRetailToGrocerSelectedAdd = new JButton("Add");
  		GridBagConstraints gbc_btn_DialogRetailToGrocerSelectedAdd = new GridBagConstraints();
  		gbc_btn_DialogRetailToGrocerSelectedAdd.insets = new Insets(0, 0, 5, 5);
  		gbc_btn_DialogRetailToGrocerSelectedAdd.anchor = GridBagConstraints.CENTER;
  		gbc_btn_DialogRetailToGrocerSelectedAdd.gridx = 3;
  		gbc_btn_DialogRetailToGrocerSelectedAdd.gridy = 5;
  		JLabel lbl_DialogRetailToGrocerItemsToMove = new JLabel("Items to introduce:");
  		GridBagConstraints gbc_lbl_DialogRetailToGrocerItemsToMove = new GridBagConstraints();
  		gbc_lbl_DialogRetailToGrocerItemsToMove.insets = new Insets(0, 0, 5, 5);
  		gbc_lbl_DialogRetailToGrocerItemsToMove.anchor = GridBagConstraints.WEST;
  		gbc_lbl_DialogRetailToGrocerItemsToMove.gridx = 0;
  		gbc_lbl_DialogRetailToGrocerItemsToMove.gridy = 6;
  		JTextField txt_DialogRetailToGrocerListedItems = new JTextField("0");
  		txt_DialogRetailToGrocerListedItems.setEditable(false);
  		GridBagConstraints gbc_txt_DialogRetailToGrocerListedItems = new GridBagConstraints();
  		gbc_txt_DialogRetailToGrocerListedItems.insets = new Insets(0, 0, 5, 5);
  		gbc_txt_DialogRetailToGrocerListedItems.anchor = GridBagConstraints.WEST;
  		gbc_txt_DialogRetailToGrocerListedItems.gridx = 1;
  		gbc_txt_DialogRetailToGrocerListedItems.gridy = 6;
  		tbl_DialogRetailToGrocerToMoveList = new JTable();
  		tbl_DialogRetailToGrocerToMoveList.setModel(new DefaultTableModel(null,StoreWindow.grocerStandardTableModel));
  		JScrollPane scrl_DialogRetailToGrocerToMoveList = new JScrollPane(tbl_DialogRetailToGrocerToMoveList);
  		scrl_DialogGrocerToRetailToMoveList.setVisible(true);
  		GridBagConstraints gbc_scrl_DialogRetailToGrocerToMoveList = new GridBagConstraints();
  		gbc_scrl_DialogRetailToGrocerToMoveList.insets = new Insets(0, 0, 5, 5);
  		gbc_scrl_DialogRetailToGrocerToMoveList.fill = GridBagConstraints.HORIZONTAL;
  		gbc_scrl_DialogRetailToGrocerToMoveList.gridx = 0;
  		gbc_scrl_DialogRetailToGrocerToMoveList.gridwidth = 4;
  		gbc_scrl_DialogRetailToGrocerToMoveList.gridy = 7;
  		JButton btn_DialogRetailToGrocerReset = new JButton("Reset");
  		GridBagConstraints gbc_btn_DialogRetailToGrocerReset = new GridBagConstraints();
  		gbc_btn_DialogRetailToGrocerReset.insets = new Insets(5, 5, 5, 5);
  		gbc_btn_DialogRetailToGrocerReset.anchor = GridBagConstraints.CENTER;
  		gbc_btn_DialogRetailToGrocerReset.gridwidth = 5;
  		gbc_btn_DialogRetailToGrocerReset.gridx = 0;
  		gbc_btn_DialogRetailToGrocerReset.gridy = 8;
		
		//make jtables uneditable
		tbl_StockingGrocerItems.setEnabled(false);
		tbl_StockSetGrocer.setEnabled(false);
		tbl_StockSetRetail.setEnabled(false);
		tbl_StockingRetailItems.setEnabled(false);
		tbl_DialogGrocerToRetailToMoveList.setEnabled(false);
		tbl_DialogRetailToGrocerToMoveList.setEnabled(false);
		//add autosorters
		tbl_StockingGrocerItems.setAutoCreateRowSorter(true);
		tbl_StockSetGrocer.setAutoCreateRowSorter(true);
		tbl_StockSetRetail.setAutoCreateRowSorter(true);
		tbl_StockingRetailItems.setAutoCreateRowSorter(true);
		
		//component functions
		//Button for Adding new item to Grocer: Show dialogbox, add new item.
		btn_DialogGroceryStockAddItem.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//Show dialogbox
				int result = JOptionPane.showOptionDialog(null, pnl_DialogStockingAddGrocerItem,"Enter item data.", 
							 JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, StoreWindow.DialogOptions, null);
				if(result == JOptionPane.OK_OPTION)
				{
					if(!txt_DialogAddGrocerItemCode.getText().isEmpty())
					{
						//does ID already exist?
						if(StoreWindow.findItem(false,txt_DialogAddGrocerItemCode.getText()) == null)
						{
							//receive data 
							String code = txt_DialogAddGrocerItemCode.getText();
							String itemdata[] = StoreWindow.getItemData(cmb_DialogAddGrocerItemName.getSelectedItem().toString());
							//add data to database
							StoreWindow.addNewItem(false,code,cmb_DialogAddGrocerItemName.getSelectedItem().toString(),itemdata);
						}
						else
						{JOptionPane.showMessageDialog(StoreWindow.frame, "New grocery item already exists.");}
					}
					else
					{JOptionPane.showMessageDialog(StoreWindow.frame, "Error: Inserted ID is empty.");}
				}
				//refresh data
				txt_DialogAddGrocerItemCode.setText(null);
				cmb_DialogAddGrocerItemName.setSelectedIndex(0);
				txt_DialogAddGrocerItemCost.setValue(null);
				StoreWindow.refreshTables();
			}
		});
		//add listener for itemname combobox add new item type to Grocer: search item data+ add new datas
		cmb_DialogAddGrocerItemName.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//get the selected item
				String target = cmb_DialogAddGrocerItemName.getSelectedItem().toString();
				//get info on the item's cost
				String[] feedback = StoreWindow.getItemData(target);
				if(feedback[0] != null)
				{
					//paste info on interface
					txt_DialogAddGrocerItemCost.setValue(Long.valueOf(feedback[0].replaceAll(",", "")));
				}
				else
				{JOptionPane.showMessageDialog(StoreWindow.frame, "Item is not found in grocer database.");}
			}
		});
		
		//Button for Adding new item to Retail: Show dialogbox, add new item.
		btn_DialogRetailStockAddItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//Show dialogbox
				int result = JOptionPane.showOptionDialog(null, pnl_DialogStockingAddRetailItem,"Enter item data.", 
							 JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, StoreWindow.DialogOptions, null);
				if(result == JOptionPane.OK_OPTION)
				{
					if(!txt_DialogAddRetailItemCode.getText().isEmpty())
					{
						//does ID already exist?
						if(StoreWindow.findItem(true,txt_DialogAddRetailItemCode.getText()) == null)
						{
							//receive data
							String code = txt_DialogAddRetailItemCode.getText();
							String itemdata[] = StoreWindow.getItemData(cmb_DialogAddRetailItemName.getSelectedItem().toString());
							//add data to database
							StoreWindow.addNewItem(true,code,cmb_DialogAddRetailItemName.getSelectedItem().toString(),itemdata);
						}
						else
						{JOptionPane.showMessageDialog(StoreWindow.frame, "New retail item already exists.");}
					}
					else
					{JOptionPane.showMessageDialog(StoreWindow.frame, "Error: Inserted ID is empty.");}
				}
				//refresh data
				txt_DialogAddGrocerItemCode.setText(null);
				cmb_DialogAddGrocerItemName.setSelectedIndex(0);
				txt_DialogAddGrocerItemCost.setValue(null);
				txt_DialogAddRetailItemStack.setText(null);
				StoreWindow.refreshTables();
			}
			
		});
		//add listener for itemname combobox add new item type to Grocer: search item data+ add new datas
		cmb_DialogAddRetailItemName.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String target = cmb_DialogAddRetailItemName.getSelectedItem().toString();
				//get info on the item's cost
				String[] feedback = StoreWindow.getItemData(target);
				if(feedback[0] != null)
				{
					//paste info on interface
					txt_DialogAddRetailItemCost.setValue(Long.valueOf(feedback[0].replaceAll(",", "")));
					txt_DialogAddRetailItemStack.setText((feedback[2].replaceAll(",", "")));
				}
				else
				{JOptionPane.showMessageDialog(StoreWindow.frame, "Item is not found in grocer database.");}
			}
		});
		
		// Button functions for stock migration
		//Move items from grocery to retail button: popup selection dialog.
		btn_StockingMigrateToRetail.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//pop up input dialogbox
				int result = JOptionPane.showOptionDialog(null, pnl_DialogGrocerToRetail,"Enter migration details", 
							 JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, StoreWindow.DialogOptions, null);
				if(result == JOptionPane.OK_OPTION)
				{
					//gather data from components
					ArrayList<String> groceritems = new ArrayList<String>();
					DefaultTableModel grocertable = (DefaultTableModel) tbl_DialogGrocerToRetailToMoveList.getModel();
					for (int count = 0; count < grocertable.getRowCount(); count++)
					{
						String item = grocertable.getValueAt(count, 0).toString();
						groceritems.add(item);
					}
					String retailID = txt_DialogGrocerToRetailRetailId.getText();
					String retailName = cmb_DialogGrocerToRetailRetailName.getSelectedItem().toString();
					String retailStack= txt_DialogGrocerToRetailRetailStack.getValue().toString();
					String[] newretailitem = StoreWindow.getItemData(retailName);
					//confirm migrate items
					int stackeditems = Integer.valueOf( txt_DialogGrocerToRetailListedItems.getText() );
					if(Integer.valueOf(retailStack) == stackeditems)
					{
						//delete items from grocery
						for(String groceritem : groceritems)
						{StoreWindow.excelRemove(false,groceritem);}
						//add item to retail
						StoreWindow.addNewItem(true,retailID,retailName,newretailitem);
					}
					else
					{JOptionPane.showMessageDialog(StoreWindow.frame, "Items in list mismatch with stated stack number.");}
				}
				//once done, reset components
				btn_DialogGrocerToRetailReset.doClick();
			}
		});
		//move items from retail to grocer: open a dialogbox.
		btn_StockingMigratetoGrocer.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//pop up input dialogbox
				int result = JOptionPane.showOptionDialog(null, pnl_DialogRetailToGrocer,"Enter migration details", 
							 JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, StoreWindow.DialogOptions, null);
				if(result == JOptionPane.OK_OPTION)
				{
					//gather data from components
					String retailid = (String) cmb_DialogRetailToGrocerSelectedID.getSelectedItem();
					ArrayList<String[]> groceries = new ArrayList<String[]>();
					
					DefaultTableModel grocertable = (DefaultTableModel) tbl_DialogRetailToGrocerToMoveList.getModel();
					for (int count = 0; count < grocertable.getRowCount(); count++)
					{
						String[] item = new String[4];
						item[0] = grocertable.getValueAt(count, 0).toString();
						item[1] = grocertable.getValueAt(count, 1).toString();
						groceries.add(item);
					}
					
					//confirm migrate items
					String[] retailitem = StoreWindow.getItemData(retailid);
					if(retailitem == null)
					{
						//item count check
						if(txt_DialogRetailToGrocerListedItems.getText().equals(txt_DialogRetailToGrocerSelectedStack.getText()))
						{
							//delete item from retail
							StoreWindow.excelRemove(true, retailid);
							//add items to grocery
							for(String[] item : groceries)
							{
								String[] itemdatas = StoreWindow.getItemData(item[1]);
								StoreWindow.addNewItem(false,item[0],item[1],itemdatas);
							}
						}
						else
						{JOptionPane.showMessageDialog(StoreWindow.frame, "Items in list mismatch with stated stack number.");}
					}
					else
					{JOptionPane.showMessageDialog(StoreWindow.frame, "Error: Retail item already exists.");}
				}
				//once done, reset components
				btn_DialogRetailToGrocerReset.doClick();
			}
			
		});
	
		//button function for grocery migration dialog: add selected item to list of groceries to be migrated 
		btn_DialogGrocerToRetailSelectedAdd.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//get data from inputs
				String id = (String) cmb_DialogGrocerToRetailSelectedID.getSelectedItem();
				String name = txt_DialogGrocerToRetailSelectedName.getText();
				String price = (String) txt_DialogGrocerToRetailSelectedCost.getText();
				String COSG = (String) txt_DialogGrocerToRetailSelectedPrincipal.getText();
				String[] data = {id,name,price,COSG};
				//insert data into table if data IS entered
				if(!id.isEmpty() && !name.isEmpty() && !price.isEmpty() && !COSG.isEmpty())
				{
					DefaultTableModel tablemodel = (DefaultTableModel) tbl_DialogGrocerToRetailToMoveList.getModel();
					tablemodel.addRow(data);
					tbl_DialogGrocerToRetailToMoveList.setModel(tablemodel);
					//update stack count
					int itemsinlist = Integer.valueOf( txt_DialogGrocerToRetailListedItems.getText() ) + 1;
					txt_DialogGrocerToRetailListedItems.setText(""+itemsinlist);
					//Remove item from dropbox (prevent multiples)
					cmb_DialogGrocerToRetailSelectedID.removeItemAt(cmb_DialogGrocerToRetailSelectedID.getSelectedIndex());
				}
			}
		});
		//combobox listener grocery migration dialog: use selected item ID to fetch data
		cmb_DialogGrocerToRetailSelectedID.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//get selected
				String selectedID = (String) cmb_DialogGrocerToRetailSelectedID.getSelectedItem();
				//search grocer table for id
				String[] selected = StoreWindow.findItem(false,selectedID);
				//if item found, show data. Else, popup error
				if(selected != null)
				{
					txt_DialogGrocerToRetailSelectedName.setText(selected[1]);
					txt_DialogGrocerToRetailSelectedCost.setValue( Long.valueOf(selected[2].replaceAll(",","")) );
					txt_DialogGrocerToRetailSelectedPrincipal.setValue( Long.valueOf(selected[3].replaceAll(",","")) );
				}
				else
				{JOptionPane.showMessageDialog(StoreWindow.frame, "Item with ID"+selectedID+"not found.");}
			}
		});
		//Combobox listener grocery migration dialog: shows all available retail item sets w/data, or a new retail item
		cmb_DialogGrocerToRetailRetailName.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//if selected item is not the newstock option, find the item's datas
				String target = cmb_DialogGrocerToRetailRetailName.getSelectedItem().toString();
				String[] feedback = StoreWindow.getItemData(target);
				if(feedback != null)
				{
					//parse info
					long cost = Long.valueOf(feedback[0].replaceAll(",", ""));
					long COSG = Long.valueOf(feedback[1].replaceAll(",", ""));
					int stack = Integer.parseInt(feedback[2]);
					
					//paste info on interface
					txt_DialogGrocerToRetailRetailCost.setValue(cost);
					txt_DialogGrocerToRetailRetailStack.setValue(stack);
					txt_DialogGrocerToRetailRetailPrincipal.setValue(COSG);
				}
			}
		});
		//listener grocer migration dialog: reset components via click
		btn_DialogGrocerToRetailReset.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//reset components
				txt_DialogGrocerToRetailRetailId.setText(null);
				cmb_DialogGrocerToRetailRetailName.setSelectedIndex(0);
				txt_DialogGrocerToRetailRetailCost.setValue(null);
				txt_DialogGrocerToRetailRetailStack.setValue(null);
				txt_DialogGrocerToRetailRetailPrincipal.setValue(null);
				cmb_DialogGrocerToRetailSelectedID.setModel(new DefaultComboBoxModel<String>(StoreWindow.getExcelUniqueSet(false)));
				cmb_DialogGrocerToRetailSelectedID.setSelectedIndex(0);
				txt_DialogGrocerToRetailSelectedName.setText(null);
				txt_DialogGrocerToRetailSelectedCost.setText(null);
				txt_DialogGrocerToRetailSelectedPrincipal.setText(null);
				txt_DialogGrocerToRetailListedItems.setText("0");
				//refresh tables
				StoreWindow.refreshTables();
			}
		});
		//listener retail migration dialog: reset components via click
		btn_DialogRetailToGrocerReset.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//reset components
				txt_DialogRetailToGrocerGrocerId.setText(null);
				cmb_DialogRetailToGrocerGrocerName.setSelectedIndex(0);
				txt_DialogRetailToGrocerGrocerCost.setValue(null);
				txt_DialogRetailToGrocerGrocerCOGS.setValue(null);
				cmb_DialogRetailToGrocerSelectedID.setSelectedIndex(0);
				txt_DialogRetailToGrocerSelectedName.setText(null);
				txt_DialogRetailToGrocerSelectedCost.setText(null);
				txt_DialogRetailToGrocerSelectedStack.setText(null);
				txt_DialogRetailToGrocerSelectedCOGS.setText(null);
				txt_DialogRetailToGrocerListedItems.setText("0");
				//refresh tables
				StoreWindow.refreshTables();
			}
		});
		
		//button listener retail migration dialog: add selected grocery input to the list + increment count
		btn_DialogRetailToGrocerSelectedAdd.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//get data from inputs
				String id = (String) txt_DialogRetailToGrocerGrocerId.getText();
				String name = (String) cmb_DialogRetailToGrocerGrocerName.getSelectedItem();
				String price = (String) txt_DialogRetailToGrocerGrocerCost.getText();
				String COGS = (String) txt_DialogRetailToGrocerGrocerCOGS.getText();
				String[] data = {id,name,price,COGS};
				//insert data into table if data IS entered
				if(!id.isEmpty() && !name.isEmpty() && !price.isEmpty() && !COGS.isEmpty())
				{
					//another check: Is added ID legal? (not in table and not in grocery)
					String[] newID = StoreWindow.getItemData(id);
					boolean newid = true;
					DefaultTableModel tablemodel = (DefaultTableModel) tbl_DialogRetailToGrocerToMoveList.getModel();
					for(int i = 0; i < tablemodel.getRowCount(); i++)
					{
						if(tablemodel.getValueAt(i, 0).equals(id))
						{newid = false;}
					}
					if(newID == null && newid)
					{
						tablemodel.addRow(data);
						tbl_DialogRetailToGrocerToMoveList.setModel(tablemodel);
						//update stack count
						int itemsinlist = Integer.valueOf( txt_DialogRetailToGrocerListedItems.getText() ) + 1;
						txt_DialogRetailToGrocerListedItems.setText(""+itemsinlist);
					}
					else
					{JOptionPane.showMessageDialog(StoreWindow.frame, "New item's ID already exists. Use another ID.");}
				}
				else
				{ JOptionPane.showMessageDialog(StoreWindow.frame, "Incomplete data entered. Ensure the grocery item data is complete."); }
			}
			
		});
		
		//combobox listener for retail migration dialog: fetch data from retail and show on interface
		cmb_DialogRetailToGrocerSelectedID.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//get selected
				String selectedID = (String) cmb_DialogRetailToGrocerSelectedID.getSelectedItem();
				//search grocer table for id
				String[] selected = StoreWindow.findItem(true,selectedID);
				//if item found, show data. Else, popup error
				if(selected != null)
				{
					txt_DialogRetailToGrocerSelectedName.setText(selected[1]);
					txt_DialogRetailToGrocerSelectedCost.setValue( Long.valueOf(selected[2].replaceAll(",","")) );
					txt_DialogRetailToGrocerSelectedCOGS.setValue( Long.valueOf(selected[3].replaceAll(",","")) );
					txt_DialogRetailToGrocerSelectedStack.setText(selected[4]);
				}
				else
				{JOptionPane.showMessageDialog(StoreWindow.frame, "Item with ID "+selectedID+" not found.");}
			}
		});
		
		//combobox listener for retail migration dialog: fetch data from grocery OR make dialogbox for new item
		cmb_DialogRetailToGrocerGrocerName.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
			
				//if selected item is not the newstock option, find the item's datas
				String target = cmb_DialogRetailToGrocerGrocerName.getSelectedItem().toString();
				String[] feedback = StoreWindow.getItemData(target);
				if(feedback != null)
				{
					//parse info
					long cost = Long.valueOf(feedback[0].replaceAll(",", ""));
					long COSG = Long.valueOf(feedback[1].replaceAll(",", ""));
					
					//paste info on interface
					txt_DialogRetailToGrocerGrocerCost.setValue(cost);
					txt_DialogRetailToGrocerGrocerCOGS.setValue(COSG);
				}
			}
		});
		
		//textbox checks for grocer migration dialog: does ID exists?
		txt_DialogGrocerToRetailRetailId.addFocusListener(new FocusListener()
		{

			@Override
			public void focusGained(FocusEvent arg0) {}

			//on focus loss, make a check: does ID exists on retail?
			@Override
			public void focusLost(FocusEvent arg0) 
			{
				String ID = txt_DialogGrocerToRetailRetailId.getText();
				String[] item = StoreWindow.findItem(false,ID);
				if(item !=null)
				{JOptionPane.showMessageDialog(StoreWindow.frame, "Retail stock already has that ID: "+item[1]+" ID:"+item[0]);}
			}
			
		});
		
		//integrate component
		this.add(lbl_StockingGroceryStock, gbc_lbl_StockingGroceryStock);
		this.add(lbl_StockingRetailStock, gbc_lbl_StockingRetailStock);
		this.add(scrl_StockingGrocerItems,gbc_tbl_StockingGrocerItems);
		this.add(scrl_StockingRetailItems, gbc_tbl_StockingRetailItems);
		this.add(btn_StockingMigrateToRetail, gbc_btn_StockingMigrateToRetail);
		this.add(btn_StockingMigratetoGrocer, gbc_btn_StockingMigratetoGrocer);
		this.add(lbl_StockingStockCount, gbc_lbl_StockingStockCount);
		this.add(scrl_StockSetGrocer,gbc_tbl_StockSetGrocer);
		this.add(scrl_StockSetRetail,gbc_tbl_StockSetRetail);
		this.add(btn_DialogRetailStockAddItem, gbc_btn_DialogRetailStockAddItem);
		this.add(btn_DialogGroceryStockAddItem, gbc_btn_DialogGroceryStockAddItem);

		pnl_DialogStockingAddGrocerItem.add(lbl_DialogAddGrocerItemId);
		pnl_DialogStockingAddGrocerItem.add(txt_DialogAddGrocerItemCode);
		pnl_DialogStockingAddGrocerItem.add(lbl_DialogAddGrocerItemName);
		pnl_DialogStockingAddGrocerItem.add(cmb_DialogAddGrocerItemName);
		pnl_DialogStockingAddGrocerItem.add(lbl_DialogAddGrocerItemCost);
		pnl_DialogStockingAddGrocerItem.add(txt_DialogAddGrocerItemCost);

		pnl_DialogStockingAddRetailItem.add(lbl_DialogAddRetailItemId);
		pnl_DialogStockingAddRetailItem.add(txt_DialogAddRetailItemCode);
		pnl_DialogStockingAddRetailItem.add(lbl_DialogAddRetailItemName);
		pnl_DialogStockingAddRetailItem.add(cmb_DialogAddRetailItemName);
		pnl_DialogStockingAddRetailItem.add(lbl_DialogAddRetailItemCost);
		pnl_DialogStockingAddRetailItem.add(txt_DialogAddRetailItemCost);
		pnl_DialogStockingAddRetailItem.add(lbl_DialogAddRetailItemStack);
		pnl_DialogStockingAddRetailItem.add(txt_DialogAddRetailItemStack);
		
		pnl_DialogGrocerToRetail.add(lbl_DialogGrocerToRetailSelectedItem, gbc_lbl_DialogGrocerToRetailSelectedItem);
		pnl_DialogGrocerToRetail.add(lbl_DialogGrocerToRetailSelectedId, gbc_lbl_DialogGrocerToRetailSelectedId);
		pnl_DialogGrocerToRetail.add(lbl_DialogGrocerToRetailSelectedName, gbc_lbl_DialogGrocerToRetailSelectedName);
		pnl_DialogGrocerToRetail.add(lbl_DialogGrocerToRetailSelectedCost, gbc_lbl_DialogGrocerToRetailSelectedCost);
		pnl_DialogGrocerToRetail.add(cmb_DialogGrocerToRetailSelectedID, gbc_cmb_DialogGrocerToRetailSelectedID);
		pnl_DialogGrocerToRetail.add(txt_DialogGrocerToRetailSelectedName, gbc_txt_DialogGrocerToRetailSelectedName);
		pnl_DialogGrocerToRetail.add(txt_DialogGrocerToRetailSelectedCost, gbc_txt_DialogGrocerToRetailSelectedCost);
		pnl_DialogGrocerToRetail.add(txt_DialogGrocerToRetailSelectedPrincipal, gbc_txt_DialogGrocerToRetailSelectedPrincipal);
		pnl_DialogGrocerToRetail.add(lbl_DialogGrocerToRetailItemsToMove, gbc_lbl_DialogGrocerToRetailItemsToMove);
		pnl_DialogGrocerToRetail.add(btn_DialogGrocerToRetailSelectedAdd,gbc_btn_DialogGrocerToRetailSelectedAdd);
		pnl_DialogGrocerToRetail.add(txt_DialogGrocerToRetailListedItems,gbc_txt_DialogGrocerToRetailListedItems);
		pnl_DialogGrocerToRetail.add(scrl_DialogGrocerToRetailToMoveList, gbc_scrl_DialogGrocerToRetailToMoveList);
		pnl_DialogGrocerToRetail.add(lbl_DialogGrocerToRetailTo, gbc_lbl_DialogGrocerToRetailTo);
		pnl_DialogGrocerToRetail.add(lbl_DialogGrocerToRetailRetailId, gbc_lbl_DialogGrocerToRetailRetailId);
		pnl_DialogGrocerToRetail.add(lbl_DialogGrocerToRetailRetailName, gbc_lbl_DialogGrocerToRetailRetailName);
		pnl_DialogGrocerToRetail.add(lbl_DialogGrocerToRetailRetailCost, gbc_lbl_DialogGrocerToRetailRetailCost);
		pnl_DialogGrocerToRetail.add(txt_DialogGrocerToRetailRetailId, gbc_txt_DialogGrocerToRetailRetailId);
		pnl_DialogGrocerToRetail.add(cmb_DialogGrocerToRetailRetailName, gbc_cmb_DialogGrocerToRetailRetailName);
		pnl_DialogGrocerToRetail.add(txt_DialogGrocerToRetailRetailCost, gbc_txt_DialogGrocerToRetailRetailCost);
		pnl_DialogGrocerToRetail.add(txt_DialogGrocerToRetailRetailStack, gbc_txt_DialogGrocerToRetailRetailStack);
		pnl_DialogGrocerToRetail.add(txt_DialogGrocerToRetailRetailPrincipal, gbc_txt_DialogGrocerToRetailRetailPrincipal);
		pnl_DialogGrocerToRetail.add(btn_DialogGrocerToRetailReset,gbc_btn_DialogGrocerToRetailReset);
		
		pnl_DialogRetailToGrocer.add(lbl_DialogRetailToGrocerSelectedItem, gbc_lbl_DialogRetailToGrocerSelectedItem);
		pnl_DialogRetailToGrocer.add(lbl_DialogRetailToGrocerSelectedId, gbc_lbl_DialogRetailToGrocerSelectedId);
		pnl_DialogRetailToGrocer.add(lbl_DialogRetailToGrocerSelectedName, gbc_lbl_DialogRetailToGrocerSelectedName);
		pnl_DialogRetailToGrocer.add(lbl_DialogRetailToGrocerSelectedCost, gbc_lbl_DialogRetailToGrocerSelectedCost);
		pnl_DialogRetailToGrocer.add(lbl_DialogRetailToGrocerSelectedStack, gbc_lbl_DialogRetailToGrocerSelectedStack);
		pnl_DialogRetailToGrocer.add(cmb_DialogRetailToGrocerSelectedID, gbc_cmb_DialogRetailToGrocerSelectedID);
		pnl_DialogRetailToGrocer.add(txt_DialogRetailToGrocerSelectedName, gbc_txt_DialogRetailToGrocerSelectedName);
		pnl_DialogRetailToGrocer.add(txt_DialogRetailToGrocerSelectedCost, gbc_txt_DialogRetailToGrocerSelectedCost);
		pnl_DialogRetailToGrocer.add(txt_DialogRetailToGrocerSelectedStack, gbc_txt_DialogRetailToGrocerSelectedStack);
		pnl_DialogRetailToGrocer.add(txt_DialogRetailToGrocerSelectedCOGS, gbc_txt_DialogRetailToGrocerSelectedCOGS);
		pnl_DialogRetailToGrocer.add(lbl_DialogRetailToGrocerItemsToMove, gbc_lbl_DialogRetailToGrocerItemsToMove);
		pnl_DialogRetailToGrocer.add(btn_DialogRetailToGrocerSelectedAdd,gbc_btn_DialogRetailToGrocerSelectedAdd);
		pnl_DialogRetailToGrocer.add(txt_DialogRetailToGrocerListedItems,gbc_txt_DialogRetailToGrocerListedItems);
		pnl_DialogRetailToGrocer.add(scrl_DialogRetailToGrocerToMoveList, gbc_scrl_DialogRetailToGrocerToMoveList);
		pnl_DialogRetailToGrocer.add(lbl_DialogRetailToGrocerTo, gbc_lbl_DialogRetailToGrocerTo);
		pnl_DialogRetailToGrocer.add(lbl_DialogRetailToGrocerGrocerId, gbc_lbl_DialogRetailToGrocerGrocerId);
		pnl_DialogRetailToGrocer.add(lbl_DialogRetailToGrocerGrocerName, gbc_lbl_DialogRetailToGrocerGrocerName);
		pnl_DialogRetailToGrocer.add(lbl_DialogRetailToGrocerGrocerCost, gbc_lbl_DialogRetailToGrocerGrocerCost);
		pnl_DialogRetailToGrocer.add(txt_DialogRetailToGrocerGrocerId, gbc_txt_DialogRetailToGrocerGrocerId);
		pnl_DialogRetailToGrocer.add(cmb_DialogRetailToGrocerGrocerName, gbc_cmb_DialogRetailToGrocerGrocerName);
		pnl_DialogRetailToGrocer.add(txt_DialogRetailToGrocerGrocerCost, gbc_txt_DialogRetailToGrocerGrocerCost);
		pnl_DialogRetailToGrocer.add(txt_DialogRetailToGrocerGrocerCOGS, gbc_txt_DialogRetailToGrocerGrocerCOGS);
		pnl_DialogRetailToGrocer.add(btn_DialogRetailToGrocerReset,gbc_btn_DialogRetailToGrocerReset);
		
		cmb_DialogAddGrocerItemName.setSelectedIndex(0);
		cmb_DialogAddRetailItemName.setSelectedIndex(0);
		cmb_DialogGrocerToRetailRetailName.setSelectedIndex(0);
		cmb_DialogRetailToGrocerGrocerName.setSelectedIndex(0);
		cmb_DialogGrocerToRetailSelectedID.setSelectedIndex(0);
		cmb_DialogRetailToGrocerSelectedID.setSelectedIndex(0);
	}

	public static void refreshMe() 
	{
		tbl_StockingGrocerItems.setModel(StoreWindow.getGrocerExcelData(false));
		tbl_StockingRetailItems.setModel(StoreWindow.getRetailExcelData(false));
		tbl_StockSetGrocer.setModel(StoreWindow.getStockCountSets(false,false));
		tbl_StockSetRetail.setModel(StoreWindow.getStockCountSets(true,false));
		tbl_DialogGrocerToRetailToMoveList.setModel(new DefaultTableModel(null,StoreWindow.grocerAdminTableModel));
		tbl_DialogRetailToGrocerToMoveList.setModel(new DefaultTableModel(null,StoreWindow.retailAdminTableModel));
		cmb_DialogGrocerToRetailSelectedID.setModel(new DefaultComboBoxModel<String>(StoreWindow.getExcelUniqueSet(false)));
		cmb_DialogGrocerToRetailRetailName.setModel(new DefaultComboBoxModel<String>(StoreWindow.getExcelUniqueSet(true)));
		cmb_DialogRetailToGrocerSelectedID.setModel(new DefaultComboBoxModel<String>(StoreWindow.getExcelUniqueSet(true)));
		cmb_DialogRetailToGrocerGrocerName.setModel(new DefaultComboBoxModel<String>(StoreWindow.getExcelUniqueSet(false)));
	}
	
	//support functions
}
