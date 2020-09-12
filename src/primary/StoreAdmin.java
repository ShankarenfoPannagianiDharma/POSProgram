package primary;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.Box;
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

public class StoreAdmin extends JPanel 
{
	private static final long serialVersionUID = 5567714597482987902L;
	
	//jtables
	private static JTable tbl_AdminGrocerItems;
	private static JTable tbl_AdminRetailItems;
	private static JTable tbl_AdminGrocerTypes;
	private static JTable tbl_AdminRetailTypes;
	//comboboxes
	private static JComboBox<String> cmb_DialogAdminEditGrocerSelected;
	private static JComboBox<String> cmb_DialogAdminDeleteGrocerSelected;
	private static JComboBox<String> cmb_DialogAdminEditRetailSelected;
	private static JComboBox<String> cmb_DialogAdminDeleteRetailSelected;
	private static JComboBox<String> cmb_DialogAdminEditGrocerSelectedName;
	private static JComboBox<String> cmb_DialogAdminEditRetailSelectedName;
	private static JComboBox<String> cmb_DialogAdminEditGrocerType;
	private static JComboBox<String> cmb_DialogAdminEditRetailType;
	private static JComboBox<String> cmb_DialogAdminDeleteGrocerTypeName;
	private static JComboBox<String> cmb_DialogAdminDeleteRetailTypeName;
	
	StoreAdmin()
	{
		//generate components
		this.setBackground(new Color(102, 51, 153));
		GridBagLayout gbl_pnl_Admin = new GridBagLayout();
		gbl_pnl_Admin.columnWidths = new int[]{909, 0};
		gbl_pnl_Admin.rowHeights = new int[]{427, 0};
		gbl_pnl_Admin.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnl_Admin.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		this.setLayout(gbl_pnl_Admin);
		JPanel pnl_AdminAccessed = new JPanel();
		pnl_AdminAccessed.setVisible(false);
		pnl_AdminAccessed.setBackground(new Color(102, 0, 153));
		JButton btn_AccessAdmin = new JButton("Access Admin");
		btn_AccessAdmin.setAlignmentX(Component.CENTER_ALIGNMENT);
		//Components for Admin View:
		GridBagLayout gbl_pnl_AdminAccessed = new GridBagLayout();
		gbl_pnl_AdminAccessed.columnWidths = new int[]{0, 0};
		gbl_pnl_AdminAccessed.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_pnl_AdminAccessed.columnWeights = new double[]{0.0, 0.0};
		gbl_pnl_AdminAccessed.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnl_AdminAccessed.setLayout(gbl_pnl_AdminAccessed);
		
		JLabel lbl_AdminRetailStock = new JLabel("Retail Stock");
		lbl_AdminRetailStock.setForeground(new Color(255, 255, 255));
		lbl_AdminRetailStock.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lbl_AdminRetailStock = new GridBagConstraints();
		gbc_lbl_AdminRetailStock.anchor = GridBagConstraints.WEST;
		gbc_lbl_AdminRetailStock.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_AdminRetailStock.gridx = 1;
		gbc_lbl_AdminRetailStock.gridy = 2;
		
		//table for full grocery stock
		JScrollPane scrl_AdminGrocerItems = new JScrollPane((Component) null);
		GridBagConstraints gbc_scrl_AdminGrocerItems = new GridBagConstraints();
		gbc_scrl_AdminGrocerItems.weighty = 0.5;
		gbc_scrl_AdminGrocerItems.weightx = 1.0;
		gbc_scrl_AdminGrocerItems.insets = new Insets(5, 5, 5, 5);
		gbc_scrl_AdminGrocerItems.fill = GridBagConstraints.BOTH;
		gbc_scrl_AdminGrocerItems.gridx = 0;
		gbc_scrl_AdminGrocerItems.gridy = 3;
		tbl_AdminGrocerItems = new JTable(StoreWindow.getGrocerExcelData(true));
		scrl_AdminGrocerItems.setViewportView(tbl_AdminGrocerItems);
		
		JLabel lbl_AdminGroceryStock = new JLabel("Grocery Stock");
		lbl_AdminGroceryStock.setForeground(new Color(255, 255, 255));
		lbl_AdminGroceryStock.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lbl_AdminGroceryStock = new GridBagConstraints();
		gbc_lbl_AdminGroceryStock.insets = new Insets(5, 5, 5, 5);
		gbc_lbl_AdminGroceryStock.anchor = GridBagConstraints.WEST;
		gbc_lbl_AdminGroceryStock.gridx = 0;
		gbc_lbl_AdminGroceryStock.gridy = 2;
		
		//table for full retail stock
		JScrollPane scrl_AdminRetailItems = new JScrollPane();
		GridBagConstraints gbc_scrl_AdminRetailItems = new GridBagConstraints();
		gbc_scrl_AdminRetailItems.weighty = 0.5;
		gbc_scrl_AdminRetailItems.weightx = 1.0;
		gbc_scrl_AdminRetailItems.insets = new Insets(5, 5, 5, 5);
		gbc_scrl_AdminRetailItems.fill = GridBagConstraints.BOTH;
		gbc_scrl_AdminRetailItems.gridx = 1;
		gbc_scrl_AdminRetailItems.gridy = 3;
		tbl_AdminRetailItems = new JTable(StoreWindow.getRetailExcelData(true));
		scrl_AdminRetailItems.setViewportView(tbl_AdminRetailItems);
		GridBagConstraints gbc_pnl_AdminAccessed = new GridBagConstraints();
		gbc_pnl_AdminAccessed.fill = GridBagConstraints.BOTH;
		gbc_pnl_AdminAccessed.insets = new Insets(5, 5, 0, 0);
		gbc_pnl_AdminAccessed.anchor = GridBagConstraints.NORTH;
		gbc_pnl_AdminAccessed.gridx = 0;
		gbc_pnl_AdminAccessed.gridy = 0;
		
		JLabel lbl_AdminBillLoc = new JLabel("Bill file address: "+new File(StoreWindow.billfilesloc).getAbsolutePath());
		lbl_AdminBillLoc.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lbl_AdminBillLoc = new GridBagConstraints();
		gbc_lbl_AdminBillLoc.anchor = GridBagConstraints.WEST;
		gbc_lbl_AdminBillLoc.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_AdminBillLoc.gridx = 0;
		gbc_lbl_AdminBillLoc.gridy = 0;
		GridBagConstraints gbc_btn_AccessAdmin = new GridBagConstraints();
		gbc_btn_AccessAdmin.gridx = 0;
		gbc_btn_AccessAdmin.gridy = 0;
		JButton btn_AdminOpenFolder = new JButton("Open Folder");
		GridBagConstraints gbc_btn_AdminOpenFolder = new GridBagConstraints();
		gbc_btn_AdminOpenFolder.gridwidth = 2;
		gbc_btn_AdminOpenFolder.insets = new Insets(0, 0, 5, 0);
		gbc_btn_AdminOpenFolder.gridx = 0;
		gbc_btn_AdminOpenFolder.gridy = 1;
		
		JPanel pnl_AdminEditGrocer = new JPanel();
		GridBagConstraints gbc_pnl_AdminEditGrocer = new GridBagConstraints();
		gbc_pnl_AdminEditGrocer.insets = new Insets(0, 0, 5, 5);
		gbc_pnl_AdminEditGrocer.gridx = 0;
		gbc_pnl_AdminEditGrocer.gridy = 4;
		JButton btn_AdminEditGrocer = new JButton("Edit");
		JButton btn_AdminDeleteGrocer = new JButton("Delete");
		JPanel pnl_AdminEditRetail = new JPanel();
		GridBagConstraints gbc_pnl_AdminEditRetail = new GridBagConstraints();
		gbc_pnl_AdminEditRetail.insets = new Insets(0, 0, 5, 0);
		gbc_pnl_AdminEditRetail.gridx = 1;
		gbc_pnl_AdminEditRetail.gridy = 4;
		JButton btn_AdminEditRetail = new JButton("Edit");
		JButton btn_AdminDeleteRetail = new JButton("Delete");
		
		JLabel lbl_AdminStockedCount = new JLabel("Item Type Stock");
		lbl_AdminStockedCount.setForeground(new Color(255, 255, 255));
		lbl_AdminStockedCount.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lbl_AdminStockedCount = new GridBagConstraints();
		gbc_lbl_AdminStockedCount.gridx = 0;
		gbc_lbl_AdminStockedCount.gridy = 5;
		gbc_lbl_AdminStockedCount.gridwidth = 2;
		
		//Add admin stock views
		tbl_AdminRetailTypes = new JTable(StoreWindow.getStockCountSets(true,true));
		JScrollPane scrl_AdminRetailTypes = new JScrollPane();
		GridBagConstraints gbc_scrl_AdminRetailTypes = new GridBagConstraints();
		gbc_scrl_AdminRetailTypes.weighty = 0.5;
		gbc_scrl_AdminRetailTypes.weightx = 1.0;
		gbc_scrl_AdminRetailTypes.insets = new Insets(5, 5, 5, 5);
		gbc_scrl_AdminRetailTypes.fill = GridBagConstraints.BOTH;
		gbc_scrl_AdminRetailTypes.gridx = 1;
		gbc_scrl_AdminRetailTypes.gridy = 6;
		scrl_AdminRetailTypes.setViewportView(tbl_AdminRetailTypes);
		tbl_AdminGrocerTypes = new JTable(StoreWindow.getStockCountSets(false,true));
		JScrollPane scrl_AdminGrocerTypes = new JScrollPane();
		GridBagConstraints gbc_scrl_AdminGrocerTypes = new GridBagConstraints();
		gbc_scrl_AdminGrocerTypes.weighty = 0.5;
		gbc_scrl_AdminGrocerTypes.weightx = 1.0;
		gbc_scrl_AdminGrocerTypes.insets = new Insets(5, 5, 5, 5);
		gbc_scrl_AdminGrocerTypes.fill = GridBagConstraints.BOTH;
		gbc_scrl_AdminGrocerTypes.gridx = 0;
		gbc_scrl_AdminGrocerTypes.gridy = 6;
		scrl_AdminGrocerTypes.setViewportView(tbl_AdminGrocerTypes);
		
		//Add admin control: Buttons to make new itemtypes and edit itemtypes
		JPanel pnl_AdminGrocerTypeControls = new JPanel();
		GridBagConstraints gbc_pnl_AdminGrocerTypeControls = new GridBagConstraints();
		gbc_pnl_AdminGrocerTypeControls.insets = new Insets(5, 5, 5, 5);
		gbc_pnl_AdminGrocerTypeControls.gridx = 0;
		gbc_pnl_AdminGrocerTypeControls.gridy = 7;
		JButton btn_AdminGrocerAddNewType = new JButton("Add new grocer item type");
		JButton btn_AdminGrocerEditType = new JButton("Edit type data");
		JButton btn_AdminGrocerDeleteType = new JButton("Delete item type");
		JPanel pnl_AdminRetailTypeControls = new JPanel();
		GridBagConstraints gbc_pnl_AdminRetailTypeControls = new GridBagConstraints();
		gbc_pnl_AdminRetailTypeControls.insets = new Insets(5, 5, 5, 5);
		gbc_pnl_AdminRetailTypeControls.gridx = 1;
		gbc_pnl_AdminRetailTypeControls.gridy = 7;
		JButton btn_AdminRetailAddNewType = new JButton("Add new retail item type");
		JButton btn_AdminRetailEditType = new JButton("Edit type data");
		JButton btn_AdminRetailDeleteType = new JButton("Delete item type");
		
		JButton btn_AcessAdminLogout = new JButton("Logout");
		GridBagConstraints gbc_btn_AcessAdminLogout = new GridBagConstraints();
		gbc_btn_AcessAdminLogout.insets = new Insets(5, 5, 5, 5);
		gbc_btn_AcessAdminLogout.gridx = 0;
		gbc_btn_AcessAdminLogout.gridy = 8;
		gbc_btn_AcessAdminLogout.gridwidth = 2;
		
		//components for small input dialogbox: new grocery item type
		JPanel pnl_DialogInsertNewGrocerType = new JPanel();
		JLabel lbl_DialogInsertNewGrocerTypeName = new JLabel("Item Name: ");
		JLabel lbl_DialogInsertNewGrocerTypeCost = new JLabel("Item Price: ");
		JLabel lbl_DialogInsertNewGrocerTypeCOGS = new JLabel("Item COGS: ");
		JTextField txt_DialogInsertNewGrocerTypeName = new JTextField(15);
	    JFormattedTextField txt_DialogInsertNewGrocerTypeCost = new JFormattedTextField(StoreWindow.currency);
	    txt_DialogInsertNewGrocerTypeCost.setColumns(15);
	    JFormattedTextField txt_DialogInsertNewGrocerTypeCOGS = new JFormattedTextField(StoreWindow.currency);
	    txt_DialogInsertNewGrocerTypeCOGS.setColumns(15);
		
		//components for small input dialogbox: new retail item
	    JPanel pnl_DialogInsertNewRetailType = new JPanel(); 
		JLabel lbl_DialogInsertNewRetailTypeName = new JLabel("Item Name: ");
		JTextField txt_DialogInsertNewRetailTypeName = new JTextField(15);
		pnl_DialogInsertNewRetailType.add(Box.createHorizontalStrut(15)); // a spacer
		JLabel lbl_DialogInsertNewRetailTypeCost = new JLabel("Item Price: ");	    
		JFormattedTextField txt_DialogInsertNewRetailTypeCost = new JFormattedTextField(StoreWindow.currency);
		txt_DialogInsertNewRetailTypeCost.setColumns(15);
		JLabel lbl_DialogInsertNewRetailTypeStack = new JLabel("Item Stack: ");
		JFormattedTextField txt_DialogInsertNewRetailTypeStack = new JFormattedTextField(NumberFormat.getIntegerInstance());
		txt_DialogInsertNewRetailTypeStack.setColumns(15);
		JLabel lbl_DialogInsertNewRetailTypeCOGS = new JLabel("Item COGS: ");	    
		JFormattedTextField txt_DialogInsertNewRetailTypeCOGS = new JFormattedTextField(StoreWindow.currency);
		txt_DialogInsertNewRetailTypeCOGS.setColumns(15);
		
		//Dialogbox for admin edit grocer
		JPanel pnl_DialogAdminEditGrocer = new JPanel();
		GridBagLayout gbl_pnl_DialogAdminEditGrocer = new GridBagLayout();
		gbl_pnl_DialogAdminEditGrocer.columnWidths = new int[]{0, 0, 0, 0};
		gbl_pnl_DialogAdminEditGrocer.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_pnl_DialogAdminEditGrocer.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnl_DialogAdminEditGrocer.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnl_DialogAdminEditGrocer.setLayout(gbl_pnl_DialogAdminEditGrocer);
		JLabel lbl_DialogAdminEditGrocerGuide = new JLabel("Item to Edit : ");
		GridBagConstraints gbc_lbl_DialogAdminEditGrocerGuide = new GridBagConstraints();
		gbc_lbl_DialogAdminEditGrocerGuide.insets = new Insets(5, 5, 5, 5);
		gbc_lbl_DialogAdminEditGrocerGuide.gridx = 0;
		gbc_lbl_DialogAdminEditGrocerGuide.gridy = 0;
		JLabel lbl_DialogAdminEditGrocerTo = new JLabel("To:");
		GridBagConstraints gbc_lbl_DialogAdminEditGrocerTo = new GridBagConstraints();
		gbc_lbl_DialogAdminEditGrocerTo.insets = new Insets(5, 5, 5, 0);
		gbc_lbl_DialogAdminEditGrocerTo.gridx = 0;
		gbc_lbl_DialogAdminEditGrocerTo.gridy = 1;
		gbc_lbl_DialogAdminEditGrocerTo.gridwidth = 3;
		cmb_DialogAdminEditGrocerSelected = new JComboBox<String>();
		GridBagConstraints gbc_cmb_DialogAdminEditGrocerSelected = new GridBagConstraints();
		gbc_cmb_DialogAdminEditGrocerSelected.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmb_DialogAdminEditGrocerSelected.gridwidth = 2;
		gbc_cmb_DialogAdminEditGrocerSelected.insets = new Insets(5, 5, 5, 0);
		gbc_cmb_DialogAdminEditGrocerSelected.gridx = 1;
		gbc_cmb_DialogAdminEditGrocerSelected.gridy = 0;
		cmb_DialogAdminEditGrocerSelected.setModel(new DefaultComboBoxModel<String>(StoreWindow.getItemIDs(false)));
		JLabel lbl_DialogAdminEditGrocerName = new JLabel("Type");
		GridBagConstraints gbc_lbl_DialogAdminEditGrocerName = new GridBagConstraints();
		gbc_lbl_DialogAdminEditGrocerName.insets = new Insets(5, 5, 5, 5);
		gbc_lbl_DialogAdminEditGrocerName.gridx = 0;
		gbc_lbl_DialogAdminEditGrocerName.gridy = 2;
		JLabel lbl_DialogAdminEditGrocerCost = new JLabel("Cost");
		GridBagConstraints gbc_lbl_DialogAdminEditGrocerCost = new GridBagConstraints();
		gbc_lbl_DialogAdminEditGrocerCost.insets = new Insets(5, 5, 5, 5);
		gbc_lbl_DialogAdminEditGrocerCost.gridx = 1;
		gbc_lbl_DialogAdminEditGrocerCost.gridy = 2;
		JLabel lbl_DialogAdminEditGrocerCOGS = new JLabel("COGS");
		GridBagConstraints gbc_lbl_DialogAdminEditGrocerCOGS = new GridBagConstraints();
		gbc_lbl_DialogAdminEditGrocerCOGS.insets = new Insets(5, 5, 5, 0);
		gbc_lbl_DialogAdminEditGrocerCOGS.gridx = 2;
		gbc_lbl_DialogAdminEditGrocerCOGS.gridy = 2;
		cmb_DialogAdminEditGrocerSelectedName = new JComboBox<String>();
		cmb_DialogAdminEditGrocerSelectedName.setModel(new DefaultComboBoxModel<String>(StoreWindow.getItemTypes(false)));
		GridBagConstraints gbc_cmb_DialogAdminEditGrocerSelectedName = new GridBagConstraints();
		gbc_cmb_DialogAdminEditGrocerSelectedName.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmb_DialogAdminEditGrocerSelectedName.insets = new Insets(5, 5, 0, 5);
		gbc_cmb_DialogAdminEditGrocerSelectedName.gridx = 0;
		gbc_cmb_DialogAdminEditGrocerSelectedName.gridy = 3;
		JFormattedTextField txt_DialogAdminEditGrocerSelectedCost = new JFormattedTextField(StoreWindow.currency);
		GridBagConstraints gbc_txt_DialogAdminEditGrocerSelectedCost = new GridBagConstraints();
		gbc_txt_DialogAdminEditGrocerSelectedCost.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_DialogAdminEditGrocerSelectedCost.insets = new Insets(5, 5, 0, 5);
		gbc_txt_DialogAdminEditGrocerSelectedCost.gridx = 1;
		gbc_txt_DialogAdminEditGrocerSelectedCost.gridy = 3;
		txt_DialogAdminEditGrocerSelectedCost.setColumns(15);
		txt_DialogAdminEditGrocerSelectedCost.setEditable(false);
		JFormattedTextField txt_DialogAdminEditGrocerSelectedCOGS = new JFormattedTextField(StoreWindow.currency);
		GridBagConstraints gbc_txt_DialogAdminEditGrocerSelectedCOGS = new GridBagConstraints();
		gbc_txt_DialogAdminEditGrocerSelectedCOGS.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_DialogAdminEditGrocerSelectedCOGS.insets = new Insets(5, 5, 0, 0);
		gbc_txt_DialogAdminEditGrocerSelectedCOGS.gridx = 2;
		gbc_txt_DialogAdminEditGrocerSelectedCOGS.gridy = 3;
		txt_DialogAdminEditGrocerSelectedCOGS.setColumns(15);
		txt_DialogAdminEditGrocerSelectedCOGS.setEditable(false);
		
		//Dialogbox for admin delete grocer
		JPanel pnl_DialogAdminDeleteGrocer = new JPanel();
		cmb_DialogAdminDeleteGrocerSelected = new JComboBox<String>();
		cmb_DialogAdminDeleteGrocerSelected.setModel(new DefaultComboBoxModel<String>(StoreWindow.getItemIDs(false)));
		JLabel lbl_DialogAdminDeleteGrocer = new JLabel("Item to delete: ");
		JTextField txt_DialogAdminDeleteGrocerName = new JTextField();
		txt_DialogAdminDeleteGrocerName.setColumns(15);
		txt_DialogAdminDeleteGrocerName.setEditable(false);
		
		//Dialogbox for admin edit retail
		JPanel pnl_DialogAdminEditRetail = new JPanel();
		GridBagLayout gbl_pnl_DialogAdminEditRetail = new GridBagLayout();
		gbl_pnl_DialogAdminEditRetail.columnWidths = new int[]{0, 0, 0, 0};
		gbl_pnl_DialogAdminEditRetail.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_pnl_DialogAdminEditRetail.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnl_DialogAdminEditRetail.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnl_DialogAdminEditRetail.setLayout(gbl_pnl_DialogAdminEditRetail);
		JLabel lbl_DialogAdminEditRetailGuide = new JLabel("Item to Edit : ");
		GridBagConstraints gbc_lbl_DialogAdminEditRetailGuide = new GridBagConstraints();
		gbc_lbl_DialogAdminEditRetailGuide.insets = new Insets(5, 5, 5, 5);
		gbc_lbl_DialogAdminEditRetailGuide.gridx = 0;
		gbc_lbl_DialogAdminEditRetailGuide.gridy = 0;
		JLabel lbl_DialogAdminEditRetailTo = new JLabel("To:");
		GridBagConstraints gbc_lbl_DialogAdminEditRetailTo = new GridBagConstraints();
		gbc_lbl_DialogAdminEditRetailTo.insets = new Insets(5, 5, 5, 0);
		gbc_lbl_DialogAdminEditRetailTo.gridx = 0;
		gbc_lbl_DialogAdminEditRetailTo.gridy = 1;
		gbc_lbl_DialogAdminEditRetailTo.gridwidth = 3;
		cmb_DialogAdminEditRetailSelected = new JComboBox<String>();
		GridBagConstraints gbc_cmb_DialogAdminEditRetailSelected = new GridBagConstraints();
		gbc_cmb_DialogAdminEditRetailSelected.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmb_DialogAdminEditRetailSelected.gridwidth = 2;
		gbc_cmb_DialogAdminEditRetailSelected.insets = new Insets(5, 5, 5, 0);
		gbc_cmb_DialogAdminEditRetailSelected.gridx = 1;
		gbc_cmb_DialogAdminEditRetailSelected.gridy = 0;
		cmb_DialogAdminEditRetailSelected.setModel(new DefaultComboBoxModel<String>(StoreWindow.getItemIDs(true)));
		JLabel lbl_DialogAdminEditRetailName = new JLabel("Name");
		GridBagConstraints gbc_lbl_DialogAdminEditRetailName = new GridBagConstraints();
		gbc_lbl_DialogAdminEditRetailName.insets = new Insets(5, 5, 5, 5);
		gbc_lbl_DialogAdminEditRetailName.gridx = 0;
		gbc_lbl_DialogAdminEditRetailName.gridy = 2;
		JLabel lbl_DialogAdminEditRetailCost = new JLabel("Cost");
		GridBagConstraints gbc_lbl_DialogAdminEditRetailCost = new GridBagConstraints();
		gbc_lbl_DialogAdminEditRetailCost.insets = new Insets(5, 5, 5, 5);
		gbc_lbl_DialogAdminEditRetailCost.gridx = 1;
		gbc_lbl_DialogAdminEditRetailCost.gridy = 2;
		JLabel lbl_DialogAdminEditRetailStck = new JLabel("Stack");
		GridBagConstraints gbc_lbl_DialogAdminEditRetailStck = new GridBagConstraints();
		gbc_lbl_DialogAdminEditRetailStck.insets = new Insets(5, 5, 5, 0);
		gbc_lbl_DialogAdminEditRetailStck.gridx = 2;
		gbc_lbl_DialogAdminEditRetailStck.gridy = 2;
		JLabel lbl_DialogAdminEditRetailCOGS = new JLabel("COGS");
		GridBagConstraints gbc_lbl_DialogAdminEditRetailCOGS = new GridBagConstraints();
		gbc_lbl_DialogAdminEditRetailCOGS.insets = new Insets(5, 5, 5, 0);
		gbc_lbl_DialogAdminEditRetailCOGS.gridx = 3;
		gbc_lbl_DialogAdminEditRetailCOGS.gridy = 2;
		cmb_DialogAdminEditRetailSelectedName = new JComboBox<String>();
		cmb_DialogAdminEditRetailSelectedName.setModel(new DefaultComboBoxModel<String>(StoreWindow.getItemTypes(true)));
		GridBagConstraints gbc_cmb_DialogAdminEditRetailSelectedName = new GridBagConstraints();
		gbc_cmb_DialogAdminEditRetailSelectedName.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmb_DialogAdminEditRetailSelectedName.insets = new Insets(5, 5, 0, 5);
		gbc_cmb_DialogAdminEditRetailSelectedName.gridx = 0;
		gbc_cmb_DialogAdminEditRetailSelectedName.gridy = 3;
		JFormattedTextField txt_DialogAdminEditRetailSelectedCost = new JFormattedTextField(StoreWindow.currency);
		GridBagConstraints gbc_txt_DialogAdminEditRetailSelectedCost = new GridBagConstraints();
		gbc_txt_DialogAdminEditRetailSelectedCost.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_DialogAdminEditRetailSelectedCost.insets = new Insets(5, 5, 0, 5);
		gbc_txt_DialogAdminEditRetailSelectedCost.gridx = 1;
		gbc_txt_DialogAdminEditRetailSelectedCost.gridy = 3;
		txt_DialogAdminEditRetailSelectedCost.setColumns(15);
		txt_DialogAdminEditRetailSelectedCost.setEditable(false);
		JFormattedTextField txt_DialogAdminEditRetailSelectedStck = new JFormattedTextField(NumberFormat.getIntegerInstance());
		GridBagConstraints gbc_txt_DialogAdminEditRetailSelectedStck = new GridBagConstraints();
		gbc_txt_DialogAdminEditRetailSelectedStck.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_DialogAdminEditRetailSelectedStck.insets = new Insets(5, 5, 0, 5);
		gbc_txt_DialogAdminEditRetailSelectedStck.gridx = 3;
		gbc_txt_DialogAdminEditRetailSelectedStck.gridy = 3;
		txt_DialogAdminEditRetailSelectedStck.setColumns(15);
		txt_DialogAdminEditRetailSelectedStck.setEditable(false);
		JFormattedTextField txt_DialogAdminEditRetailSelectedCOGS = new JFormattedTextField(StoreWindow.currency);
		GridBagConstraints gbc_txt_DialogAdminEditRetailSelectedCOGS = new GridBagConstraints();
		gbc_txt_DialogAdminEditRetailSelectedCOGS.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_DialogAdminEditRetailSelectedCOGS.insets = new Insets(5, 5, 0, 0);
		gbc_txt_DialogAdminEditRetailSelectedCOGS.gridx = 2;
		gbc_txt_DialogAdminEditRetailSelectedCOGS.gridy = 3;
		txt_DialogAdminEditRetailSelectedCOGS.setColumns(15);
		txt_DialogAdminEditRetailSelectedCOGS.setEditable(false);
		
		//Dialogbox for admin delete retail
		JPanel pnl_DialogAdminDeleteRetail = new JPanel();
		cmb_DialogAdminDeleteRetailSelected = new JComboBox<String>();
		cmb_DialogAdminDeleteRetailSelected.setModel(new DefaultComboBoxModel<String>(StoreWindow.getItemIDs(true)));
		JLabel lbl_DialogAdminDeleteRetail = new JLabel("Item to delete: ");
		JTextField txt_DialogAdminDeleteRetailName = new JTextField();
		txt_DialogAdminDeleteRetailName.setColumns(15);
		txt_DialogAdminDeleteRetailName.setEditable(false);
		JTextField txt_DialogAdminDeleteRetailStack = new JTextField();
		txt_DialogAdminDeleteRetailStack.setColumns(15);
		txt_DialogAdminDeleteRetailStack.setEditable(false);
		
		//Dialogbox for admin edit grocer item type
		JPanel pnl_DialogAdminEditGrocerType = new JPanel();
		GridBagLayout gbl_pnl_DialogAdminEditGrocerType = new GridBagLayout();
		gbl_pnl_DialogAdminEditGrocerType.columnWidths = new int[]{0, 0, 0, 0};
		gbl_pnl_DialogAdminEditGrocerType.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_pnl_DialogAdminEditGrocerType.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnl_DialogAdminEditGrocerType.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnl_DialogAdminEditGrocerType.setLayout(gbl_pnl_DialogAdminEditGrocerType);
		JLabel lbl_DialogAdminEditGrocerGuideType = new JLabel("Type to Edit : ");
		GridBagConstraints gbc_lbl_DialogAdminEditGrocerGuideType = new GridBagConstraints();
		gbc_lbl_DialogAdminEditGrocerGuideType.insets = new Insets(5, 5, 5, 5);
		gbc_lbl_DialogAdminEditGrocerGuideType.gridx = 0;
		gbc_lbl_DialogAdminEditGrocerGuideType.gridy = 0;
		JLabel lbl_DialogAdminEditGrocerToType = new JLabel("To:");
		GridBagConstraints gbc_lbl_DialogAdminEditGrocerToType = new GridBagConstraints();
		gbc_lbl_DialogAdminEditGrocerToType.insets = new Insets(5, 5, 5, 0);
		gbc_lbl_DialogAdminEditGrocerToType.gridx = 0;
		gbc_lbl_DialogAdminEditGrocerToType.gridy = 1;
		gbc_lbl_DialogAdminEditGrocerToType.gridwidth = 3;
		cmb_DialogAdminEditGrocerType = new JComboBox<String>();
		GridBagConstraints gbc_cmb_DialogAdminEditGrocerType = new GridBagConstraints();
		gbc_cmb_DialogAdminEditGrocerType.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmb_DialogAdminEditGrocerType.gridwidth = 2;
		gbc_cmb_DialogAdminEditGrocerType.insets = new Insets(5, 5, 5, 0);
		gbc_cmb_DialogAdminEditGrocerType.gridx = 1;
		gbc_cmb_DialogAdminEditGrocerType.gridy = 0;
		cmb_DialogAdminEditGrocerType.setModel(new DefaultComboBoxModel<String>(StoreWindow.getItemTypes(false)));
		JLabel lbl_DialogAdminEditGrocerTypeName = new JLabel("Type");
		GridBagConstraints gbc_lbl_DialogAdminEditGrocerTypeName = new GridBagConstraints();
		gbc_lbl_DialogAdminEditGrocerTypeName.insets = new Insets(5, 5, 5, 5);
		gbc_lbl_DialogAdminEditGrocerTypeName.gridx = 0;
		gbc_lbl_DialogAdminEditGrocerTypeName.gridy = 2;
		JLabel lbl_DialogAdminEditGrocerTypeCost = new JLabel("Cost");
		GridBagConstraints gbc_lbl_DialogAdminEditGrocerTypeCost = new GridBagConstraints();
		gbc_lbl_DialogAdminEditGrocerTypeCost.insets = new Insets(5, 5, 5, 5);
		gbc_lbl_DialogAdminEditGrocerTypeCost.gridx = 1;
		gbc_lbl_DialogAdminEditGrocerTypeCost.gridy = 2;
		JLabel lbl_DialogAdminEditGrocerTypeCOGS = new JLabel("COGS");
		GridBagConstraints gbc_lbl_DialogAdminEditGrocerTypeCOGS = new GridBagConstraints();
		gbc_lbl_DialogAdminEditGrocerTypeCOGS.insets = new Insets(5, 5, 5, 0);
		gbc_lbl_DialogAdminEditGrocerTypeCOGS.gridx = 2;
		gbc_lbl_DialogAdminEditGrocerTypeCOGS.gridy = 2;
		JTextField txt_DialogAdminEditGrocerSelectedTypeName = new JTextField();
		GridBagConstraints gbc_txt_DialogAdminEditGrocerSelectedTypeName = new GridBagConstraints();
		gbc_txt_DialogAdminEditGrocerSelectedTypeName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_DialogAdminEditGrocerSelectedTypeName.insets = new Insets(5, 5, 0, 5);
		gbc_txt_DialogAdminEditGrocerSelectedTypeName.gridx = 0;
		gbc_txt_DialogAdminEditGrocerSelectedTypeName.gridy = 3;
		JFormattedTextField txt_DialogAdminEditGrocerSelectedTypeCost = new JFormattedTextField(StoreWindow.currency);
		GridBagConstraints gbc_txt_DialogAdminEditGrocerSelectedTypeCost = new GridBagConstraints();
		gbc_txt_DialogAdminEditGrocerSelectedTypeCost.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_DialogAdminEditGrocerSelectedTypeCost.insets = new Insets(5, 5, 0, 5);
		gbc_txt_DialogAdminEditGrocerSelectedTypeCost.gridx = 1;
		gbc_txt_DialogAdminEditGrocerSelectedTypeCost.gridy = 3;
		txt_DialogAdminEditGrocerSelectedTypeCost.setColumns(15);
		JFormattedTextField txt_DialogAdminEditGrocerSelectedTypeCOGS = new JFormattedTextField(StoreWindow.currency);
		GridBagConstraints gbc_txt_DialogAdminEditGrocerSelectedTypeCOGS = new GridBagConstraints();
		gbc_txt_DialogAdminEditGrocerSelectedTypeCOGS.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_DialogAdminEditGrocerSelectedTypeCOGS.insets = new Insets(5, 5, 0, 0);
		gbc_txt_DialogAdminEditGrocerSelectedTypeCOGS.gridx = 2;
		gbc_txt_DialogAdminEditGrocerSelectedTypeCOGS.gridy = 3;
		txt_DialogAdminEditGrocerSelectedTypeCOGS.setColumns(15);
		
		//Dialogbox for admin edit grocer item type
		JPanel pnl_DialogAdminEditRetailType = new JPanel();
		GridBagLayout gbl_pnl_DialogAdminEditRetailType = new GridBagLayout();
		gbl_pnl_DialogAdminEditRetailType.columnWidths = new int[]{0, 0, 0, 0};
		gbl_pnl_DialogAdminEditRetailType.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_pnl_DialogAdminEditRetailType.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnl_DialogAdminEditRetailType.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnl_DialogAdminEditRetailType.setLayout(gbl_pnl_DialogAdminEditRetailType);
		JLabel lbl_DialogAdminEditRetailGuideType = new JLabel("Type to Edit : ");
		GridBagConstraints gbc_lbl_DialogAdminEditRetailGuideType = new GridBagConstraints();
		gbc_lbl_DialogAdminEditRetailGuideType.insets = new Insets(5, 5, 5, 5);
		gbc_lbl_DialogAdminEditRetailGuideType.gridx = 0;
		gbc_lbl_DialogAdminEditRetailGuideType.gridy = 0;
		JLabel lbl_DialogAdminEditRetailToType = new JLabel("To:");
		GridBagConstraints gbc_lbl_DialogAdminEditRetailToType = new GridBagConstraints();
		gbc_lbl_DialogAdminEditRetailToType.insets = new Insets(5, 5, 5, 0);
		gbc_lbl_DialogAdminEditRetailToType.gridx = 0;
		gbc_lbl_DialogAdminEditRetailToType.gridy = 1;
		gbc_lbl_DialogAdminEditRetailToType.gridwidth = 3;
		cmb_DialogAdminEditRetailType = new JComboBox<String>();
		GridBagConstraints gbc_cmb_DialogAdminEditRetailType = new GridBagConstraints();
		gbc_cmb_DialogAdminEditRetailType.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmb_DialogAdminEditRetailType.gridwidth = 2;
		gbc_cmb_DialogAdminEditRetailType.insets = new Insets(5, 5, 5, 0);
		gbc_cmb_DialogAdminEditRetailType.gridx = 1;
		gbc_cmb_DialogAdminEditRetailType.gridy = 0;
		cmb_DialogAdminEditRetailType.setModel(new DefaultComboBoxModel<String>(StoreWindow.getItemTypes(true)));
		JLabel lbl_DialogAdminEditRetailTypeName = new JLabel("Type");
		GridBagConstraints gbc_lbl_DialogAdminEditRetailTypeName = new GridBagConstraints();
		gbc_lbl_DialogAdminEditRetailTypeName.insets = new Insets(5, 5, 5, 5);
		gbc_lbl_DialogAdminEditRetailTypeName.gridx = 0;
		gbc_lbl_DialogAdminEditRetailTypeName.gridy = 2;
		JLabel lbl_DialogAdminEditRetailTypeCost = new JLabel("Cost");
		GridBagConstraints gbc_lbl_DialogAdminEditRetailTypeCost = new GridBagConstraints();
		gbc_lbl_DialogAdminEditRetailTypeCost.insets = new Insets(5, 5, 5, 5);
		gbc_lbl_DialogAdminEditRetailTypeCost.gridx = 1;
		gbc_lbl_DialogAdminEditRetailTypeCost.gridy = 2;
		JLabel lbl_DialogAdminEditRetailTypeCOGS = new JLabel("COGS");
		GridBagConstraints gbc_lbl_DialogAdminEditRetailTypeCOGS = new GridBagConstraints();
		gbc_lbl_DialogAdminEditRetailTypeCOGS.insets = new Insets(5, 5, 5, 0);
		gbc_lbl_DialogAdminEditRetailTypeCOGS.gridx = 2;
		gbc_lbl_DialogAdminEditRetailTypeCOGS.gridy = 2;
		JLabel lbl_DialogAdminEditRetailTypeStck = new JLabel("Stack");
		GridBagConstraints gbc_lbl_DialogAdminEditRetailTypeStck = new GridBagConstraints();
		gbc_lbl_DialogAdminEditRetailTypeStck.insets = new Insets(5, 5, 5, 0);
		gbc_lbl_DialogAdminEditRetailTypeStck.gridx = 3;
		gbc_lbl_DialogAdminEditRetailTypeStck.gridy = 2;
		JTextField txt_DialogAdminEditRetailSelectedTypeName = new JTextField();
		GridBagConstraints gbc_txt_DialogAdminEditRetailSelectedTypeName = new GridBagConstraints();
		gbc_txt_DialogAdminEditRetailSelectedTypeName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_DialogAdminEditRetailSelectedTypeName.insets = new Insets(5, 5, 0, 5);
		gbc_txt_DialogAdminEditRetailSelectedTypeName.gridx = 0;
		gbc_txt_DialogAdminEditRetailSelectedTypeName.gridy = 3;
		JFormattedTextField txt_DialogAdminEditRetailSelectedTypeCost = new JFormattedTextField(StoreWindow.currency);
		GridBagConstraints gbc_txt_DialogAdminEditRetailSelectedTypeCost = new GridBagConstraints();
		gbc_txt_DialogAdminEditRetailSelectedTypeCost.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_DialogAdminEditRetailSelectedTypeCost.insets = new Insets(5, 5, 0, 5);
		gbc_txt_DialogAdminEditRetailSelectedTypeCost.gridx = 1;
		gbc_txt_DialogAdminEditRetailSelectedTypeCost.gridy = 3;
		txt_DialogAdminEditRetailSelectedTypeCost.setColumns(15);
		JFormattedTextField txt_DialogAdminEditRetailSelectedTypeCOGS = new JFormattedTextField(StoreWindow.currency);
		GridBagConstraints gbc_txt_DialogAdminEditRetailSelectedTypeCOGS = new GridBagConstraints();
		gbc_txt_DialogAdminEditRetailSelectedTypeCOGS.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_DialogAdminEditRetailSelectedTypeCOGS.insets = new Insets(5, 5, 0, 0);
		gbc_txt_DialogAdminEditRetailSelectedTypeCOGS.gridx = 2;
		gbc_txt_DialogAdminEditRetailSelectedTypeCOGS.gridy = 3;
		txt_DialogAdminEditRetailSelectedTypeCOGS.setColumns(15);
		txt_DialogAdminEditRetailSelectedTypeCost.setColumns(15);
		JFormattedTextField txt_DialogAdminEditRetailSelectedTypeStck = new JFormattedTextField(StoreWindow.currency);
		GridBagConstraints gbc_txt_DialogAdminEditRetailSelectedTypeStck = new GridBagConstraints();
		gbc_txt_DialogAdminEditRetailSelectedTypeStck.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_DialogAdminEditRetailSelectedTypeStck.insets = new Insets(5, 5, 0, 0);
		gbc_txt_DialogAdminEditRetailSelectedTypeStck.gridx = 3;
		gbc_txt_DialogAdminEditRetailSelectedTypeStck.gridy = 3;
		txt_DialogAdminEditRetailSelectedTypeStck.setColumns(15);

		JPanel pnl_DialogAdminDeleteGrocerType = new JPanel();
		JLabel lbl_DialogAdminDeleteGrocerTypeName = new JLabel("Type to remove: ");
		GridBagConstraints gbc_lbl_DialogAdminDeleteGrocerTypeName = new GridBagConstraints();
		gbc_lbl_DialogAdminDeleteGrocerTypeName.insets = new Insets(5, 5, 5, 5);
		gbc_lbl_DialogAdminDeleteGrocerTypeName.gridx = 0;
		gbc_lbl_DialogAdminDeleteGrocerTypeName.gridy = 0;
		cmb_DialogAdminDeleteGrocerTypeName = new JComboBox<String>();
		cmb_DialogAdminDeleteGrocerTypeName.setModel(new DefaultComboBoxModel<String>(StoreWindow.getItemTypes(false)));
		GridBagConstraints gbc_cmb_DialogAdminDeleteGrocerTypeName = new GridBagConstraints();
		gbc_cmb_DialogAdminDeleteGrocerTypeName.insets = new Insets(5, 5, 5, 5);
		gbc_cmb_DialogAdminDeleteGrocerTypeName.gridx = 1;
		gbc_cmb_DialogAdminDeleteGrocerTypeName.gridy = 0;
		JLabel lbl_DialogAdminDeleteGrocerTypeInfo = new JLabel("");
		GridBagConstraints gbc_lbl_DialogAdminDeleteGrocerTypeInfo = new GridBagConstraints();
		gbc_lbl_DialogAdminDeleteGrocerTypeInfo.insets = new Insets(5, 5, 5, 5);
		gbc_lbl_DialogAdminDeleteGrocerTypeInfo.gridx = 0;
		gbc_lbl_DialogAdminDeleteGrocerTypeInfo.gridy = 1;
		gbc_lbl_DialogAdminDeleteGrocerTypeInfo.gridwidth = 2;

		JPanel pnl_DialogAdminDeleteRetailType = new JPanel();
		JLabel lbl_DialogAdminDeleteRetailTypeName = new JLabel("Type to remove: ");
		GridBagConstraints gbc_lbl_DialogAdminDeleteRetailTypeName = new GridBagConstraints();
		gbc_lbl_DialogAdminDeleteRetailTypeName.insets = new Insets(5, 5, 5, 5);
		gbc_lbl_DialogAdminDeleteRetailTypeName.gridx = 0;
		gbc_lbl_DialogAdminDeleteRetailTypeName.gridy = 0;
		cmb_DialogAdminDeleteRetailTypeName = new JComboBox<String>();
		cmb_DialogAdminDeleteRetailTypeName.setModel(new DefaultComboBoxModel<String>(StoreWindow.getItemTypes(true)));
		GridBagConstraints gbc_cmb_DialogAdminDeleteRetailTypeName = new GridBagConstraints();
		gbc_cmb_DialogAdminDeleteRetailTypeName.insets = new Insets(5, 5, 5, 5);
		gbc_cmb_DialogAdminDeleteRetailTypeName.gridx = 1;
		gbc_cmb_DialogAdminDeleteRetailTypeName.gridy = 0;
		JLabel lbl_DialogAdminDeleteRetailTypeInfo = new JLabel();
		GridBagConstraints gbc_lbl_DialogAdminDeleteRetailTypeInfo = new GridBagConstraints();
		gbc_lbl_DialogAdminDeleteRetailTypeInfo.insets = new Insets(5, 5, 5, 5);
		gbc_lbl_DialogAdminDeleteRetailTypeInfo.gridx = 0;
		gbc_lbl_DialogAdminDeleteRetailTypeInfo.gridy = 1;
		gbc_lbl_DialogAdminDeleteRetailTypeInfo.gridwidth =2;
		
		//add autosorters
		tbl_AdminGrocerItems.setAutoCreateRowSorter(true);
		tbl_AdminRetailItems.setAutoCreateRowSorter(true);
		//make jtables uneditable
		tbl_AdminGrocerItems.setEnabled(false);
		tbl_AdminRetailItems.setEnabled(false);
		tbl_AdminGrocerTypes.setEnabled(false);
		tbl_AdminRetailTypes.setEnabled(false);
		
		//add functions to components
		//Listener for admin panel: Password/Logout
		btn_AccessAdmin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//if not logged in, ask pass
				String response = (String) JOptionPane.showInputDialog("Enter Password:");
				//if response is correct, enable the adminpanel
				if(response != null && response.equals(StoreWindow.pswrd))
				{
					btn_AccessAdmin.setVisible(false);
					pnl_AdminAccessed.setVisible(true);
				}
				else
				{JOptionPane.showMessageDialog(StoreWindow.frame, "Wrong password.");}
			}
		});
		btn_AcessAdminLogout.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				pnl_AdminAccessed.setVisible(false);
				btn_AccessAdmin.setVisible(true);
			}
		});

		//button to access the program folders
		btn_AdminOpenFolder.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{Desktop.getDesktop().open(new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()));} 
				catch (IOException e) 
				{e.printStackTrace();}
			}
		});
		
		//Button for admin to edit grocery: Start editing
		btn_AdminEditGrocer.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//pop up an inputdialogbox
				int result = JOptionPane.showOptionDialog(null, pnl_DialogAdminEditGrocer,"Select entity to edit.", 
							 JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, StoreWindow.DialogOptions, null);
				if(result == JOptionPane.OK_OPTION)
				{
					//if confirm yes, check inputs
					String itemid = cmb_DialogAdminEditGrocerSelected.getSelectedItem().toString();
					String newname = cmb_DialogAdminEditGrocerSelectedName.getSelectedItem().toString();
					long newcost = (long) txt_DialogAdminEditGrocerSelectedCost.getValue();
					long newCOGS = (long) txt_DialogAdminEditGrocerSelectedCOGS.getValue();
					if(!newname.isEmpty())
					{
						//check data integrity: does item multiple exist?
						ArrayList<String> multipleids = StoreWindow.findItemInstances(false,newname);
						multipleids.remove(itemid);
						if(!multipleids.isEmpty())
						{
							//if yes, does entered data match?
							String[] multiples = StoreWindow.findItem(false,multipleids.get(0));
							if(!multiples[2].replaceAll(",", "").equals(newcost) && !multiples[3].replaceAll(",", "").equals(newCOGS))
							{
								//if not match, ask to overwrite with input or existing data.
								int response = JOptionPane.showOptionDialog(null, "Data input does not match existing item data. Do you want to overwrite/update existing items? Otherwise will use the existing item's data instead.", "Data mismatch", 
											   JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, StoreWindow.DialogOptions, null);
								if(response == JOptionPane.OK_OPTION)
								{
									//if yes: overwrite existing data, update the database with new data.
									StoreWindow.updateExcel(false,itemid,newname,newcost,0,newCOGS);
									//make the same changes to all other instances of [newname]
									
									for(String ids : multipleids)
									{StoreWindow.updateExcel(false,ids,newname,newcost,0,newCOGS);}
								}
								else
								{
									//if no: get data from existing item, write using that instead
									long oricost = Long.valueOf( multiples[2].replaceAll(",", "").toString() );
									long oriCOGS = Long.valueOf( multiples[3].replaceAll(",", "").toString() );
									StoreWindow.updateExcel(false,itemid,newname,oricost,0,oriCOGS);
								}
							}
							else
							{
								//if there is no multiple, just change the id's item
								StoreWindow.updateExcel(false,itemid,newname,newcost,0,newCOGS);
							}
						}
						else
						{
							//if there is no multiple, just change the id's item
							StoreWindow.updateExcel(false,itemid,newname,newcost,0,newCOGS);
						}
					}
					else
					{JOptionPane.showMessageDialog(StoreWindow.frame, "Item name is empty. Cancelling.");}
				}
				StoreWindow.refreshTables();
			}
		});
		//add combobox listener to edit grocer
		cmb_DialogAdminEditGrocerSelected.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//get item data from ID
				String[] itemdata = StoreWindow.findItem(false,cmb_DialogAdminEditGrocerSelected.getSelectedItem().toString());
				//post data to interfaces
				cmb_DialogAdminEditGrocerSelectedName.setSelectedItem(itemdata[1]);
				txt_DialogAdminEditGrocerSelectedCost.setValue(Long.valueOf(itemdata[2].replaceAll(",", "")));
				txt_DialogAdminEditGrocerSelectedCOGS.setValue(Long.valueOf(itemdata[3].replaceAll(",", "")));
			}
		});
		//combobox to change the item's data. Selecting an item will paste the textboxes with new data.
		cmb_DialogAdminEditGrocerSelectedName.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String itemname = cmb_DialogAdminEditGrocerSelectedName.getSelectedItem().toString();
				String[] itemdata = StoreWindow.getItemData(itemname);
				txt_DialogAdminEditGrocerSelectedCost.setValue(Long.valueOf(itemdata[0].replaceAll(",", "")));
				txt_DialogAdminEditGrocerSelectedCOGS.setValue(Long.valueOf(itemdata[1].replaceAll(",", "")));
			}
		});
		
		//Button for admin to edit grocery: delete a row 
		btn_AdminDeleteGrocer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				//pop up a selection dialogbox.
				int result = JOptionPane.showOptionDialog(null, pnl_DialogAdminDeleteGrocer,"Select item to delete.", 
						 JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, StoreWindow.DialogOptions, null);
				if(result == JOptionPane.OK_OPTION)
				{
					//if confirm yes, get selected id
					String todel = cmb_DialogAdminDeleteGrocerSelected.getSelectedItem().toString();
					//delete item from database
					StoreWindow.excelRemove(false, todel);
				}
				StoreWindow.refreshTables();
			}
		});
		cmb_DialogAdminDeleteGrocerSelected.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//get item data from ID
				String[] itemdata = StoreWindow.findItem(false,cmb_DialogAdminDeleteGrocerSelected.getSelectedItem().toString());
				//post data to interface
				txt_DialogAdminDeleteGrocerName.setText(itemdata[1]);
			}
		});
		
		//Button for admin to edit retail: Start editing
		btn_AdminEditRetail.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//pop up an inputdialogbox
				int result = JOptionPane.showOptionDialog(null, pnl_DialogAdminEditRetail,"Select entity to edit.", 
							 JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, StoreWindow.DialogOptions, null);
				if(result == JOptionPane.OK_OPTION)
				{
					//if confirm yes, check inputs
					String itemid = cmb_DialogAdminEditRetailSelected.getSelectedItem().toString();
					String newname = cmb_DialogAdminEditRetailSelectedName.getSelectedItem().toString();
					long newcost = (long) txt_DialogAdminEditRetailSelectedCost.getValue();
					int newstck  = Integer.parseInt(txt_DialogAdminEditRetailSelectedStck.getValue().toString());
					long newCOGS = (long) txt_DialogAdminEditRetailSelectedCOGS.getValue();
					if(!newname.isEmpty())
					{
						//check data integrity: does item multiple exist?
						ArrayList<String> multipleids = StoreWindow.findItemInstances(false,newname);
						multipleids.remove(itemid);
						if(!multipleids.isEmpty())
						{
							//if yes, does entered data match?
							String[] multiples = StoreWindow.findItem(false,multipleids.get(0));
							if(!multiples[2].replaceAll(",", "").equals(newcost) && !multiples[3].replaceAll(",", "").equals(newstck) && !multiples[4].replaceAll(",", "").equals(newCOGS))
							{
								//if not match, ask to overwrite with input or existing data.
								int response = JOptionPane.showOptionDialog(null, "Data input does not match existing item data. Do you want to overwrite/update existing items? Otherwise will use the existing item's data instead.", "Data mismatch", 
											   JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, StoreWindow.DialogOptions, null);
								if(response == JOptionPane.OK_OPTION)
								{
									//if yes: overwrite existing data, update the database with new data.
									StoreWindow.updateExcel(true,itemid,newname,newcost,newstck,newCOGS);
									//make the same changes to all other instances of [newname]
									
									for(String ids : multipleids)
									{StoreWindow.updateExcel(true,ids,newname,newcost,newstck,newCOGS);}
								}
								else
								{
									//if no: get data from existing item, write using that instead
									long oricost = Long.valueOf( multiples[2].replaceAll(",", "").toString() );
									int oriStck = Integer.valueOf( multiples[3].replaceAll(",", "").toString() );
									long oriCOGS = Long.valueOf( multiples[4].replaceAll(",", "").toString() );
									StoreWindow.updateExcel(true,itemid,newname,oricost,oriStck,oriCOGS);
								}
							}
							else
							{
								//if there is no multiple, just change the id's item
								StoreWindow.updateExcel(true,itemid,newname,newcost,newstck,newCOGS);
							}
						}
						else
						{
							//if there is no multiple, just change the id's item
							StoreWindow.updateExcel(true,itemid,newname,newcost,newstck,newCOGS);
						}
					}
					else
					{JOptionPane.showMessageDialog(StoreWindow.frame, "Item name is empty. Cancelling.");}
				}
				StoreWindow.refreshTables();
			}
		});
		//add combobox listener to edit retail
		cmb_DialogAdminEditRetailSelected.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//get item data from ID
				String[] itemdata = StoreWindow.findItem(true,cmb_DialogAdminEditRetailSelected.getSelectedItem().toString());
				//post data to interfaces
				cmb_DialogAdminEditRetailSelectedName.setSelectedItem(itemdata[1]);
				txt_DialogAdminEditRetailSelectedCost.setValue(Long.valueOf(itemdata[2].replaceAll(",", "")));
				txt_DialogAdminEditRetailSelectedStck.setValue(Integer.valueOf(itemdata[3].replaceAll(",", "")));
				txt_DialogAdminEditRetailSelectedCOGS.setValue(Long.valueOf(itemdata[4].replaceAll(",", "")));
			}
		});
		//dialog to select a different item type
		cmb_DialogAdminEditRetailSelectedName.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String itemname = cmb_DialogAdminEditRetailSelectedName.getSelectedItem().toString();
				String[] itemdata = StoreWindow.getItemData(itemname);
				txt_DialogAdminEditRetailSelectedCost.setValue(Long.valueOf(itemdata[0].replaceAll(",", "")));
				txt_DialogAdminEditRetailSelectedStck.setValue(Integer.valueOf(itemdata[1].replaceAll(",", "")));
				txt_DialogAdminEditRetailSelectedCOGS.setValue(Long.valueOf(itemdata[2].replaceAll(",", "")));
			}
		});

		//Button for admin to edit retail: delete a row 
		btn_AdminDeleteRetail.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				//pop up a selection dialogbox.
				int result = JOptionPane.showOptionDialog(null, pnl_DialogAdminDeleteRetail,"Select item to delete.", 
						 JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, StoreWindow.DialogOptions, null);
				if(result == JOptionPane.OK_OPTION)
				{
					//if confirm yes, get selected id
					String todel = cmb_DialogAdminDeleteRetailSelected.getSelectedItem().toString();
					//delete item from database
					StoreWindow.excelRemove(true, todel);
				}
				StoreWindow.refreshTables();
			}
		});
		cmb_DialogAdminDeleteRetailSelected.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//get item data from ID
				String[] itemdata = StoreWindow.findItem(true,cmb_DialogAdminDeleteRetailSelected.getSelectedItem().toString());
				//post data to interface
				txt_DialogAdminDeleteRetailName.setText(itemdata[1]);
				txt_DialogAdminDeleteRetailStack.setText(itemdata[4]);
			}
		});
		
		//button function for admin add new item type to database
		btn_AdminGrocerAddNewType.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//pop up an inputdialogbox
				int result = JOptionPane.showOptionDialog(null, pnl_DialogInsertNewGrocerType,"Enter item name, price, cost and COGS", 
							 JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, StoreWindow.DialogOptions, null);
				if(result == JOptionPane.OK_OPTION)
				{
					//get the input data
					String newname = txt_DialogInsertNewGrocerTypeName.getText();
					long newcost = (long) txt_DialogInsertNewGrocerTypeCost.getValue();
					long newCOGS = (long) txt_DialogInsertNewGrocerTypeCOGS.getValue();
					 
					String[] namesearch = StoreWindow.getItemData(newname);
					//new item is actually new/do not exist yet?
					if(namesearch == null)
					{
						//add into database
						String[] results = {newname,String.valueOf(newcost),String.valueOf(newCOGS),"1"};
						StoreWindow.addNewItemType(false,results);
					}
					else
					{JOptionPane.showMessageDialog(StoreWindow.frame, "New item "+newname+" already exists."); }
				}
				StoreWindow.refreshTables();
			}
		});
		btn_AdminRetailAddNewType.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//pop up an inputdialogbox
				int result = JOptionPane.showOptionDialog(null, pnl_DialogInsertNewRetailType,"Enter item name, price and COGS", 
							 JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, StoreWindow.DialogOptions, null);
				if(result == JOptionPane.OK_OPTION)
				{
					//get the input data
					String newname = txt_DialogInsertNewRetailTypeName.getText();
					long newcost = (long) txt_DialogInsertNewRetailTypeCost.getValue();
					long newcogs = (long) txt_DialogInsertNewRetailTypeCOGS.getValue();
					String stack = txt_DialogInsertNewRetailTypeStack.getText();

					//new item is actually new/do not exist yet?
					String[] namesearch = StoreWindow.getItemData(newname);
					if(namesearch == null)
					{
						//place input to database
						String[] results = {newname,String.valueOf(newcost),String.valueOf(newcogs),stack};
						StoreWindow.addNewItemType(true,results);
					}
					else
					{JOptionPane.showMessageDialog(StoreWindow.frame, "New item "+newname+" already exists.");}
				}
				StoreWindow.refreshTables();
			}
		});
		
		//button functions to edit grocer item types by admin
		btn_AdminGrocerEditType.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				int result = JOptionPane.showOptionDialog(null, pnl_DialogAdminEditGrocerType,"Enter item name, price and COGS", 
						 JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, StoreWindow.DialogOptions, null);
				if(result == JOptionPane.OK_OPTION)
				{
					//get the input data
					String itemtype = cmb_DialogAdminEditGrocerType.getSelectedItem().toString();
					String typename = txt_DialogAdminEditGrocerSelectedTypeName.getText();
					long typecost = ((Number)txt_DialogAdminEditGrocerSelectedTypeCost.getValue()).longValue();
					long typeCOGS = ((Number)txt_DialogAdminEditGrocerSelectedTypeCOGS.getValue()).longValue();

					//apply changes to itemtype database
					StoreWindow.itemTypeChange(false,itemtype,typename,typecost,typeCOGS,1);
				}
				StoreWindow.refreshTables();
			}
		});
		//combobox function to fill in interface data
		cmb_DialogAdminEditGrocerType.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String[] datas = StoreWindow.getItemData(cmb_DialogAdminEditGrocerType.getSelectedItem().toString());
				txt_DialogAdminEditGrocerSelectedTypeName.setText(cmb_DialogAdminEditGrocerType.getSelectedItem().toString());
				txt_DialogAdminEditGrocerSelectedTypeCost.setValue(Long.parseLong(datas[0].replaceAll(",", "")));
				txt_DialogAdminEditGrocerSelectedTypeCOGS.setValue(Long.parseLong(datas[1].replaceAll(",", "")));
			}
		});
		//button functions to edit retail item types by admin
		btn_AdminRetailEditType.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				int result = JOptionPane.showOptionDialog(null, pnl_DialogAdminEditRetailType,"Enter item name, price, COGS and stack", 
						 JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, StoreWindow.DialogOptions, null);
				if(result == JOptionPane.OK_OPTION)
				{
					//get the input data
					String itemtype = cmb_DialogAdminEditRetailType.getSelectedItem().toString();
					String typename = txt_DialogAdminEditRetailSelectedTypeName.getText();
					long typecost = ((Number)txt_DialogAdminEditRetailSelectedTypeCost.getValue()).longValue();
					long typeCOGS = ((Number)txt_DialogAdminEditRetailSelectedTypeCOGS.getValue()).longValue();
					int  typestck = Integer.parseInt(txt_DialogAdminEditRetailSelectedTypeStck.getValue().toString());
					//apply changes to itemtype database
					StoreWindow.itemTypeChange(true,itemtype,typename,typecost,typeCOGS,typestck);
				}
				StoreWindow.refreshTables();
			}
		});
		//combobox function to fill in interface data
		cmb_DialogAdminEditRetailType.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String[] datas = StoreWindow.getItemData(cmb_DialogAdminEditRetailType.getSelectedItem().toString());
				txt_DialogAdminEditRetailSelectedTypeName.setText(cmb_DialogAdminEditRetailType.getSelectedItem().toString());
				txt_DialogAdminEditRetailSelectedTypeCost.setValue(Long.parseLong(datas[0].replaceAll(",", "")));
				txt_DialogAdminEditRetailSelectedTypeCOGS.setValue(Long.parseLong(datas[1].replaceAll(",", "")));
				txt_DialogAdminEditRetailSelectedTypeStck.setValue(Integer.parseInt(datas[2].replaceAll(",", "")));
			}
		});
		
		//Button functions to delete a grocer itemtype by admin
		btn_AdminGrocerDeleteType.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				int result = JOptionPane.showOptionDialog(null, pnl_DialogAdminDeleteGrocerType,"Remove all occurences of an item type in databases.", 
						 JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, StoreWindow.DialogOptions, null);
				if(result == JOptionPane.OK_OPTION)
				{
					String todel = cmb_DialogAdminDeleteGrocerTypeName.getSelectedItem().toString(); 
					StoreWindow.deleteItemType(false,todel);
					StoreWindow.refreshTables();
				}
			}
		});
		cmb_DialogAdminDeleteGrocerTypeName.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String todel = cmb_DialogAdminDeleteGrocerTypeName.getSelectedItem().toString();
				ArrayList<String> instances = StoreWindow.findItemInstances(false,todel);
				lbl_DialogAdminDeleteGrocerTypeInfo.setText("Instances of item: "+instances.size());
			}
		});
		//Button functions to delete a retail itemtype by admin
		btn_AdminRetailDeleteType.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				int result = JOptionPane.showOptionDialog(null, pnl_DialogAdminDeleteRetailType,"Remove all occurences of an item type in databases.", 
						 JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, StoreWindow.DialogOptions, null);
				if(result == JOptionPane.OK_OPTION)
				{
					String todel = cmb_DialogAdminDeleteRetailTypeName.getSelectedItem().toString(); 
					StoreWindow.deleteItemType(true,todel);
					StoreWindow.refreshTables();
				}
			}
		});
		cmb_DialogAdminDeleteRetailTypeName.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String todel = cmb_DialogAdminDeleteRetailTypeName.getSelectedItem().toString();
				ArrayList<String> instances = StoreWindow.findItemInstances(true,todel);
				lbl_DialogAdminDeleteGrocerTypeInfo.setText("Instances of item: "+instances.size());
			}
		});		
		
		//integrate components
		this.add(btn_AccessAdmin, gbc_btn_AccessAdmin);
		this.add(pnl_AdminAccessed, gbc_pnl_AdminAccessed);
		pnl_AdminAccessed.add(lbl_AdminBillLoc, gbc_lbl_AdminBillLoc);
		pnl_AdminAccessed.add(pnl_AdminEditGrocer, gbc_pnl_AdminEditGrocer);
		pnl_AdminEditGrocer.add(btn_AdminEditGrocer);
		pnl_AdminEditGrocer.add(btn_AdminDeleteGrocer);
		pnl_AdminAccessed.add(pnl_AdminEditRetail, gbc_pnl_AdminEditRetail);
		pnl_AdminEditRetail.add(btn_AdminEditRetail);
		pnl_AdminEditRetail.add(btn_AdminDeleteRetail);
		pnl_AdminAccessed.add(btn_AdminOpenFolder, gbc_btn_AdminOpenFolder);
		pnl_AdminAccessed.add(lbl_AdminRetailStock, gbc_lbl_AdminRetailStock);
		pnl_AdminAccessed.add(scrl_AdminGrocerItems, gbc_scrl_AdminGrocerItems);
		pnl_AdminAccessed.add(lbl_AdminGroceryStock, gbc_lbl_AdminGroceryStock);
		pnl_AdminAccessed.add(scrl_AdminRetailItems, gbc_scrl_AdminRetailItems);
		pnl_AdminAccessed.add(lbl_AdminStockedCount,gbc_lbl_AdminStockedCount);
		pnl_AdminAccessed.add(scrl_AdminGrocerTypes,gbc_scrl_AdminGrocerTypes);
		pnl_AdminAccessed.add(scrl_AdminRetailTypes,gbc_scrl_AdminRetailTypes);
		pnl_AdminAccessed.add(pnl_AdminGrocerTypeControls, gbc_pnl_AdminGrocerTypeControls);
		pnl_AdminGrocerTypeControls.add(btn_AdminGrocerAddNewType);
		pnl_AdminGrocerTypeControls.add(btn_AdminGrocerEditType);
		pnl_AdminGrocerTypeControls.add(btn_AdminGrocerDeleteType);
		pnl_AdminAccessed.add(pnl_AdminRetailTypeControls, gbc_pnl_AdminRetailTypeControls);
		pnl_AdminRetailTypeControls.add(btn_AdminRetailAddNewType);
		pnl_AdminRetailTypeControls.add(btn_AdminRetailEditType);
		pnl_AdminRetailTypeControls.add(btn_AdminRetailDeleteType);
		pnl_AdminAccessed.add(btn_AcessAdminLogout, gbc_btn_AcessAdminLogout);

		pnl_DialogAdminEditGrocer.add(lbl_DialogAdminEditGrocerGuide,gbc_lbl_DialogAdminEditGrocerGuide);
		pnl_DialogAdminEditGrocer.add(lbl_DialogAdminEditGrocerTo,gbc_lbl_DialogAdminEditGrocerTo);
		pnl_DialogAdminEditGrocer.add(cmb_DialogAdminEditGrocerSelected,gbc_cmb_DialogAdminEditGrocerSelected);
		pnl_DialogAdminEditGrocer.add(lbl_DialogAdminEditGrocerName,gbc_lbl_DialogAdminEditGrocerName);
		pnl_DialogAdminEditGrocer.add(lbl_DialogAdminEditGrocerCost,gbc_lbl_DialogAdminEditGrocerCost);
		pnl_DialogAdminEditGrocer.add(lbl_DialogAdminEditGrocerCOGS,gbc_lbl_DialogAdminEditGrocerCOGS);
		pnl_DialogAdminEditGrocer.add(cmb_DialogAdminEditGrocerSelectedName,gbc_cmb_DialogAdminEditGrocerSelectedName);
		pnl_DialogAdminEditGrocer.add(txt_DialogAdminEditGrocerSelectedCost,gbc_txt_DialogAdminEditGrocerSelectedCost);
		pnl_DialogAdminEditGrocer.add(txt_DialogAdminEditGrocerSelectedCOGS,gbc_txt_DialogAdminEditGrocerSelectedCOGS);

		pnl_DialogAdminDeleteGrocer.add(lbl_DialogAdminDeleteGrocer);
		pnl_DialogAdminDeleteGrocer.add(Box.createHorizontalStrut(15)); // a spacer
		pnl_DialogAdminDeleteGrocer.add(cmb_DialogAdminDeleteGrocerSelected);
		pnl_DialogAdminDeleteGrocer.add(Box.createHorizontalStrut(15)); // a spacer
		pnl_DialogAdminDeleteGrocer.add(txt_DialogAdminDeleteGrocerName);
		
		pnl_DialogAdminEditRetail.add(lbl_DialogAdminEditRetailGuide,gbc_lbl_DialogAdminEditRetailGuide);
		pnl_DialogAdminEditRetail.add(lbl_DialogAdminEditRetailTo,gbc_lbl_DialogAdminEditRetailTo);
		pnl_DialogAdminEditRetail.add(cmb_DialogAdminEditRetailSelected,gbc_cmb_DialogAdminEditRetailSelected);
		pnl_DialogAdminEditRetail.add(lbl_DialogAdminEditRetailName,gbc_lbl_DialogAdminEditRetailName);
		pnl_DialogAdminEditRetail.add(lbl_DialogAdminEditRetailCost,gbc_lbl_DialogAdminEditRetailCost);
		pnl_DialogAdminEditRetail.add(lbl_DialogAdminEditRetailStck,gbc_lbl_DialogAdminEditRetailStck);
		pnl_DialogAdminEditRetail.add(lbl_DialogAdminEditRetailCOGS,gbc_lbl_DialogAdminEditRetailCOGS);
		pnl_DialogAdminEditRetail.add(cmb_DialogAdminEditRetailSelectedName,gbc_cmb_DialogAdminEditRetailSelectedName);
		pnl_DialogAdminEditRetail.add(txt_DialogAdminEditRetailSelectedCost,gbc_txt_DialogAdminEditRetailSelectedCost);
		pnl_DialogAdminEditRetail.add(txt_DialogAdminEditRetailSelectedStck,gbc_txt_DialogAdminEditRetailSelectedStck);
		pnl_DialogAdminEditRetail.add(txt_DialogAdminEditRetailSelectedCOGS,gbc_txt_DialogAdminEditRetailSelectedCOGS);
		
		pnl_DialogAdminDeleteRetail.add(lbl_DialogAdminDeleteRetail);
		pnl_DialogAdminDeleteRetail.add(Box.createHorizontalStrut(15)); // a spacer
		pnl_DialogAdminDeleteRetail.add(cmb_DialogAdminDeleteRetailSelected);
		pnl_DialogAdminDeleteRetail.add(Box.createHorizontalStrut(15)); // a spacer
		pnl_DialogAdminDeleteRetail.add(txt_DialogAdminDeleteRetailName);
		pnl_DialogAdminDeleteRetail.add(Box.createHorizontalStrut(15)); // a spacer
		pnl_DialogAdminDeleteRetail.add(txt_DialogAdminDeleteRetailStack);
		
		pnl_DialogAdminEditGrocerType.add(lbl_DialogAdminEditGrocerGuideType, gbc_lbl_DialogAdminEditGrocerGuideType);
		pnl_DialogAdminEditGrocerType.add(lbl_DialogAdminEditGrocerToType, gbc_lbl_DialogAdminEditGrocerToType);
		pnl_DialogAdminEditGrocerType.add(cmb_DialogAdminEditGrocerType,gbc_cmb_DialogAdminEditGrocerType);
		pnl_DialogAdminEditGrocerType.add(lbl_DialogAdminEditGrocerTypeName,gbc_lbl_DialogAdminEditGrocerTypeName);
		pnl_DialogAdminEditGrocerType.add(lbl_DialogAdminEditGrocerTypeCost,gbc_lbl_DialogAdminEditGrocerTypeCost);
		pnl_DialogAdminEditGrocerType.add(lbl_DialogAdminEditGrocerTypeCOGS,gbc_lbl_DialogAdminEditGrocerTypeCOGS);
		pnl_DialogAdminEditGrocerType.add(txt_DialogAdminEditGrocerSelectedTypeName,gbc_txt_DialogAdminEditGrocerSelectedTypeName);
		pnl_DialogAdminEditGrocerType.add(txt_DialogAdminEditGrocerSelectedTypeCost,gbc_txt_DialogAdminEditGrocerSelectedTypeCost);
		pnl_DialogAdminEditGrocerType.add(txt_DialogAdminEditGrocerSelectedTypeCOGS,gbc_txt_DialogAdminEditGrocerSelectedTypeCOGS);
		
		pnl_DialogAdminEditRetailType.add(lbl_DialogAdminEditRetailGuideType, gbc_lbl_DialogAdminEditRetailGuideType);
		pnl_DialogAdminEditRetailType.add(lbl_DialogAdminEditRetailToType, gbc_lbl_DialogAdminEditRetailToType);
		pnl_DialogAdminEditRetailType.add(cmb_DialogAdminEditRetailType,gbc_cmb_DialogAdminEditRetailType);
		pnl_DialogAdminEditRetailType.add(lbl_DialogAdminEditRetailTypeName,gbc_lbl_DialogAdminEditRetailTypeName);
		pnl_DialogAdminEditRetailType.add(lbl_DialogAdminEditRetailTypeCost,gbc_lbl_DialogAdminEditRetailTypeCost);
		pnl_DialogAdminEditRetailType.add(lbl_DialogAdminEditRetailTypeStck,gbc_lbl_DialogAdminEditRetailTypeStck);
		pnl_DialogAdminEditRetailType.add(lbl_DialogAdminEditRetailTypeCOGS,gbc_lbl_DialogAdminEditRetailTypeCOGS);
		pnl_DialogAdminEditRetailType.add(txt_DialogAdminEditRetailSelectedTypeName,gbc_txt_DialogAdminEditRetailSelectedTypeName);
		pnl_DialogAdminEditRetailType.add(txt_DialogAdminEditRetailSelectedTypeCost,gbc_txt_DialogAdminEditRetailSelectedTypeCost);
		pnl_DialogAdminEditRetailType.add(txt_DialogAdminEditRetailSelectedTypeStck,gbc_txt_DialogAdminEditRetailSelectedTypeStck);
		pnl_DialogAdminEditRetailType.add(txt_DialogAdminEditRetailSelectedTypeCOGS,gbc_txt_DialogAdminEditRetailSelectedTypeCOGS);
		
		pnl_DialogInsertNewGrocerType.add(lbl_DialogInsertNewGrocerTypeName);
		pnl_DialogInsertNewGrocerType.add(txt_DialogInsertNewGrocerTypeName);
		pnl_DialogInsertNewGrocerType.add(Box.createHorizontalStrut(15)); // a spacer
		pnl_DialogInsertNewGrocerType.add(lbl_DialogInsertNewGrocerTypeCost);	    
		pnl_DialogInsertNewGrocerType.add(txt_DialogInsertNewGrocerTypeCost);    
		pnl_DialogInsertNewGrocerType.add(lbl_DialogInsertNewGrocerTypeCOGS);
		pnl_DialogInsertNewGrocerType.add(txt_DialogInsertNewGrocerTypeCOGS);
		
		pnl_DialogInsertNewRetailType.add(lbl_DialogInsertNewRetailTypeName);
		pnl_DialogInsertNewRetailType.add(txt_DialogInsertNewRetailTypeName);
		pnl_DialogInsertNewRetailType.add(Box.createHorizontalStrut(15)); // a spacer
		pnl_DialogInsertNewRetailType.add(lbl_DialogInsertNewRetailTypeCost);	    
		pnl_DialogInsertNewRetailType.add(txt_DialogInsertNewRetailTypeCost);
		pnl_DialogInsertNewRetailType.add(lbl_DialogInsertNewRetailTypeCOGS);	    
		pnl_DialogInsertNewRetailType.add(txt_DialogInsertNewRetailTypeCOGS);
		pnl_DialogInsertNewRetailType.add(lbl_DialogInsertNewRetailTypeStack);
		pnl_DialogInsertNewRetailType.add(txt_DialogInsertNewRetailTypeStack);
		
		pnl_DialogAdminDeleteGrocerType.add(lbl_DialogAdminDeleteGrocerTypeName,gbc_lbl_DialogAdminDeleteGrocerTypeName);
		pnl_DialogAdminDeleteGrocerType.add(cmb_DialogAdminDeleteGrocerTypeName,gbc_cmb_DialogAdminDeleteGrocerTypeName);
		pnl_DialogAdminDeleteGrocerType.add(lbl_DialogAdminDeleteGrocerTypeInfo,gbc_lbl_DialogAdminDeleteGrocerTypeInfo);

		pnl_DialogAdminDeleteRetailType.add(lbl_DialogAdminDeleteRetailTypeName,gbc_lbl_DialogAdminDeleteRetailTypeName);
		pnl_DialogAdminDeleteRetailType.add(cmb_DialogAdminDeleteRetailTypeName,gbc_cmb_DialogAdminDeleteRetailTypeName);
		pnl_DialogAdminDeleteRetailType.add(lbl_DialogAdminDeleteRetailTypeInfo,gbc_lbl_DialogAdminDeleteRetailTypeInfo);
		
		//Force selection of comboboxes (activates functions embedded)
		cmb_DialogAdminEditGrocerSelected.setSelectedIndex(0);
		cmb_DialogAdminDeleteGrocerSelected.setSelectedIndex(0);
		cmb_DialogAdminEditRetailSelected.setSelectedIndex(0);
		cmb_DialogAdminDeleteRetailSelected.setSelectedIndex(0);
		cmb_DialogAdminEditGrocerSelectedName.setSelectedIndex(0);
		cmb_DialogAdminEditRetailSelectedName.setSelectedIndex(0);
		cmb_DialogAdminDeleteGrocerTypeName.setSelectedIndex(0);
		cmb_DialogAdminDeleteRetailTypeName.setSelectedIndex(0);	
	}

	//support functions
	//reset components
	public static void refreshMe() 
	{
		tbl_AdminGrocerItems.setModel(StoreWindow.getGrocerExcelData(true));
		tbl_AdminRetailItems.setModel(StoreWindow.getRetailExcelData(true));
		tbl_AdminGrocerTypes.setModel(StoreWindow.getStockCountSets(false,true));
		tbl_AdminRetailTypes.setModel(StoreWindow.getStockCountSets(true,true));
		//comboboxes
		cmb_DialogAdminEditGrocerSelected.setModel(new DefaultComboBoxModel<String>(StoreWindow.getItemIDs(false)));
		cmb_DialogAdminDeleteGrocerSelected.setModel(new DefaultComboBoxModel<String>(StoreWindow.getItemIDs(false)));
		cmb_DialogAdminEditRetailSelected.setModel(new DefaultComboBoxModel<String>(StoreWindow.getItemIDs(true)));
		cmb_DialogAdminDeleteRetailSelected.setModel(new DefaultComboBoxModel<String>(StoreWindow.getItemIDs(true)));
		cmb_DialogAdminEditGrocerSelectedName.setModel(new DefaultComboBoxModel<String>(StoreWindow.getItemTypes(false)));
		cmb_DialogAdminEditRetailSelectedName.setModel(new DefaultComboBoxModel<String>(StoreWindow.getItemTypes(true)));
		cmb_DialogAdminEditGrocerType.setModel(new DefaultComboBoxModel<String>(StoreWindow.getItemTypes(false)));
		cmb_DialogAdminEditRetailType.setModel(new DefaultComboBoxModel<String>(StoreWindow.getItemTypes(true)));
		cmb_DialogAdminDeleteGrocerTypeName.setModel(new DefaultComboBoxModel<String>(StoreWindow.getItemTypes(false)));
		cmb_DialogAdminDeleteRetailTypeName.setModel(new DefaultComboBoxModel<String>(StoreWindow.getItemTypes(true)));
		
	}
	
}
