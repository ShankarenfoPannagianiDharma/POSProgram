package primary;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.awt.Font;
import java.awt.Component;
import java.awt.Desktop;

//TODO:Delete selection from table: tbl_SaleGrocerPurchased, tbl_SaleRetailPurchased, tbl_DialogGrocerToRetailToMoveList, tbl_DialogRetailToGrocerToMoveList, tbl_AdminRetailTypes, tbl_AdminGrocerTypes
//TODO:Bill maths
//TODO:Bill Print
//TODO:Monthly/Daily report rewrite fix
//TODO:Bills view (Pass protected)
//TODO:Concurrent work
//TODO:Online capability

public class StoreWindow 
{
	static final Object[] DialogOptions = {"OK","Cancel"};							//component for dialogbox options
	static DataFormatter formis = new DataFormatter();								//database parser/translator
	static Format currency = NumberFormat.getNumberInstance();		 				//CASH FORMAT
	
	static  JFrame frame;											//motherpane
	static final String grocerdatabasefilename = "grocer.xlsx";		//DATABASE NAME
	static final String retaildatabasefilename = "retail.xlsx";		//DATABASE NAME
	static final String itemtpdatabasefilename = "itemtypes.xlsx";	//DATABASE NAME
	static final String billfilesloc = "Bills";						//root folder for bill collection
	static final String schedulefilesloc = "StockSchedules";		//root folder for stocking schedules
	//static, no changes aside basecode
	static final String pswrd = "pass";//Admin Password

	//table headers templates
	static String[] grocerStandardTableModel = {"ID","Item Name","Price"};
	static String[] retailAdminTableModel = {"ID","Item Name","Price","COGS","Stack"};
	static String[] grocerAdminTableModel = {"ID","Item Name","Price","COGS"};
	static String[] retailStandardTableModel = {"ID","Item Name","Price","Stack"};
	static String[] countTableModel = {"Item","Cost","Count"};
	static String[] countAdminTableModel = {"Item","Cost","COGS","Count"};
	static String[] scheduleTableModel = {"Item","Count"};
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			@SuppressWarnings("static-access")
			public void run() 
			{
				try 
				{
					StoreWindow window = new StoreWindow();
					window.frame.setVisible(true);
				} 
				catch (Exception e) 
				{e.printStackTrace();}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StoreWindow() 
	{initialize();}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		//***********GENERATE INTERFACE COMPONENTS***********\\
		//mainframe
		frame = new JFrame();
		frame.setBounds(100, 100, 568, 436);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		//mother panel
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		//grocer panel
		JPanel pnl_SaleGrocer = new StoreGrocer();
		
		//retail panel
		JPanel pnl_SaleRetail = new StoreRetail();

		//restock panel
		JPanel pnl_Stocking = new StoreStocking();
		
		//admin view panel
		JPanel pnl_Admin = new StoreAdmin();
		
		//stocking schedule panel
		JPanel pnl_StockSched = new StoreStockingSchedule();
		
		//bill collection panel
		JPanel pnl_BillColl = new StoreBillColl();
		
		
		tabbedPane.addTab("Grocer", null, pnl_SaleGrocer, "Store buy/sell");
		tabbedPane.addTab("Retail",null,pnl_SaleRetail,"Retail buy/sell");
		tabbedPane.addTab("Stocking", null, pnl_Stocking, "Restocking store items");
		tabbedPane.addTab("Admin", null, pnl_Admin, "Admin panel");	
		tabbedPane.addTab("Stock Schedule",null,pnl_StockSched,"Schedule order");
		tabbedPane.addTab("Bill Record",null,pnl_BillColl,"Collected Bills");
	}//END INIT
	
	//method to gather all ID from either grocer or retail
	public static String[] getItemIDs(boolean retail) 
	{
		ArrayList<String> IDCollections = new ArrayList<String>();
		ArrayList<String[]> rawdata = getExcelData(retail);
		//for each item, get the ID to collection
		for (String[] row : rawdata)
		{IDCollections.add(row[0]);}
		String[] IDs = IDCollections.toArray(new String[0]);
		return IDs;
	}

	//method to make the sales bill
	public static void makeBill(ArrayList<String[]> purchaseditems, long totalcost, long payment, long change, long salecost, boolean isRetail) 
	{
		//separator constant
		String ContentSeparator = "---------------------------------------------";
		String ContentSeparator2 = "*********************************************";
		String isretail;
		if(isRetail)
		{isretail = "Retail";}
		else
		{isretail = "Grocer";}
		//build the bill txt file
		String newline = System.getProperty("line.separator");
		//TODO: GET THE BILL FORMAT, REFINE THE BILlteXT GEN
		String billtext = 	ContentSeparator2+newline+ 
							"Purchases:"+newline;
		for(String[] item: purchaseditems)
		{billtext = billtext + "\t"+item[1] + ":\t\tRp "+currency.format(Long.parseLong(item[2].replaceAll(",", "")))+newline;}
		billtext = billtext + ContentSeparator+newline;
		billtext = billtext + "Total cost \t: Rp " + currency.format(totalcost) +newline;
		billtext = billtext + "Payment \t: Rp " + currency.format(payment) +newline;
		billtext = billtext + "Change \t\t: Rp " + currency.format(change) +newline;
		billtext = billtext + ContentSeparator2;
		//*** save bill file
		DateFormat salesyear = new SimpleDateFormat("yyyy");			//DATE FORMAT: YEAR OF SALE
		DateFormat salesmonth = new SimpleDateFormat("MM");				//DATE FORMAT: MONTH OF SALE
		DateFormat salesday = new SimpleDateFormat("dd");				//DATE FORMAT: DAY OF SALE (FOLDERS)
		DateFormat salestime = new SimpleDateFormat("HH_mm_ss");		//DATE FORMAT: TIME OF SALE (INDIVIDUAL BILLS)
		Date dateobj = new Date();
		String saleday = (salesday.format(dateobj));
		String saletime = (salestime.format(dateobj));
		String salemonth = (salesmonth.format(dateobj));
		String saleyear = (salesyear.format(dateobj));
		//make the .txt file in the correct folder
		try
		{
			BufferedWriter writer = null;
			File targetfile = new File("Bills/"+isretail+"/"+saleyear+"/"+salemonth+"/"+saleday+"/"+saletime+".txt");
			if(!targetfile.exists())
			{
				targetfile.getParentFile().mkdirs();
				targetfile.createNewFile();
			}
			writer = new BufferedWriter(new FileWriter(targetfile));
			writer.write(billtext);
			writer.close();
		}
		catch(Exception d)
		{d.printStackTrace();}
		
		//TODO: Print bill function
		
		//compile data into daily and monthly report
		try
		{
			//make daily report
			BufferedWriter writer = null;
			File targetfile = new File("Bills/"+isretail+"/"+saleyear+"/"+salemonth+"/"+saleday+"/DailyReport.txt");
			//if report does not exist, make a new report
			ArrayList<Long> daymoney = new ArrayList<Long>();
			if(!targetfile.exists())
			{
				//create the file
				targetfile.getParentFile().mkdirs();
				targetfile.createNewFile();
				
				//report shows the revenue, cost and profit of each sales made in the day
				String headerdailyreport 	= 	"Sales Made at "+saleday+" of "+salemonth+":"+newline+
												ContentSeparator2+newline+
												"Revenue\tCOGS\tProfit"+newline+ContentSeparator+newline;
				String contentdailyreport 	= 	currency.format(totalcost)+"\t"+currency.format(salecost)+"\t"+currency.format(totalcost-salecost)+newline;
				String enddailyreport 		= 	ContentSeparator2+newline+
												"Totals:"+newline+
												currency.format(totalcost)+"\t"+currency.format(salecost)+"\t"+currency.format(totalcost-salecost);
				//write report from the ground up
				writer = new BufferedWriter(new FileWriter(targetfile));
				writer.write(headerdailyreport+contentdailyreport+enddailyreport);
				
				//record day's totals
				daymoney.add(totalcost);
				daymoney.add(salecost);
				daymoney.add(totalcost-salecost);
				
			}
			else
			{
				//read existing report
				BufferedReader reader = new BufferedReader(new FileReader(targetfile));
				//parse data from report
				StringBuilder newdailyreport = new StringBuilder();
				String currentline;
				//read heading portion
				currentline = reader.readLine();
				{newdailyreport.append(currentline+newline);}
				//consume divider and content heading
				currentline = reader.readLine();
				newdailyreport.append(currentline+newline);
				currentline = reader.readLine();
				newdailyreport.append(currentline+newline);
				currentline = reader.readLine();
				newdailyreport.append(currentline+newline);
				//read content- get datas
				long compiledtotal = totalcost;
				long compiledcost = salecost;
				while ( !(currentline = reader.readLine()).equals(ContentSeparator2))
				{
					//read the individual text
					newdailyreport.append(currentline+newline);
					//parse the data of text
					String[] data = currentline.split("\\s+");
					compiledtotal += Long.parseLong((data[0]).replaceAll("[^\\d.]", ""));
					compiledcost += Long.parseLong((data[1]).replaceAll("[^\\d.]", ""));
				}
				reader.close();
				//insert new data to pile
				newdailyreport.append(currency.format(totalcost)+"\t"+currency.format(salecost)+"\t"+currency.format(totalcost-salecost)+newline);
				//build end summation part
				newdailyreport.append(	ContentSeparator2+newline+
										"Totals:"+newline+
										currency.format(compiledtotal)+"\t"+currency.format(compiledcost)+"\t"+currency.format(compiledtotal-compiledcost)+newline );
				//write new daily report
				writer = new BufferedWriter(new FileWriter(targetfile));
				writer.write(newdailyreport.toString());
				
				//record day's totals
				daymoney.add(compiledtotal);
				daymoney.add(compiledcost);
				daymoney.add(compiledtotal-compiledcost);
			}
			writer.close();
			
			//write monthly report
			targetfile = new File("Bills/"+isretail+"/"+saleyear+"/"+salemonth+"/"+"MonthlyReport.txt");
			//check existence of monthly report file
			if(!targetfile.exists())
			{
				//create the file
				targetfile.getParentFile().mkdirs();
				targetfile.createNewFile();
				
				//build headers
				String content = 	"Sales made at "+salemonth+" of "+saleyear+":"+newline+
									ContentSeparator2+newline+
									"Date\tSales\tCOGS\tProfit"+newline+ContentSeparator+newline;
				//build content
				content = content +saleday+"\t\t"+daymoney.get(0)+"\t"+daymoney.get(1)+"\t"+daymoney.get(2)+newline;
				//build summation
				content = content + ContentSeparator2+newline+
									"Total sales this month:"+newline+
									"1\t"+currency.format(daymoney.get(0))+"\t"+currency.format(daymoney.get(1))+"\t"+currency.format(daymoney.get(2))+newline;
				
				//write the monthly report
				writer = new BufferedWriter(new FileWriter(targetfile));
				writer.write(content);
			}
			else
			{
				BufferedReader reader = new BufferedReader(new FileReader(targetfile));
				//parse data from report
				StringBuilder newmonthlyreport = new StringBuilder();
				String currentline;
				//read heading portion
				currentline = reader.readLine();
				newmonthlyreport.append(currentline+newline);
				//consume divider and content heading
				currentline = reader.readLine();
				newmonthlyreport.append(currentline+newline);
				currentline = reader.readLine();
				newmonthlyreport.append(currentline+newline);
				currentline = reader.readLine();
				newmonthlyreport.append(currentline+newline);
				currentline = reader.readLine();
				//read content- get datas
				int salesnum = 0;
				long compiledtotal = daymoney.get(0);
				long compiledcost = daymoney.get(1);
				while ( !(currentline = reader.readLine()).equals(ContentSeparator))
				{
					salesnum++;
					newmonthlyreport.append(currentline+newline);
					//parse the data of text [Date of Sale | Sales | Principal | Profits]
					String[] data = currentline.split("\\s+");
					compiledtotal += Long.parseLong((data[1]).replaceAll("[^\\d.]", ""));
					compiledcost += Long.parseLong((data[2]).replaceAll("[^\\d.]", ""));
				}
				reader.close();
				//insert new data to pile
				newmonthlyreport.append(saleday+"\t"+currency.format(daymoney.get(0))+"\t"+currency.format(daymoney.get(1))+"\t"+currency.format(daymoney.get(2))+newline);
				//build end summation part
				newmonthlyreport.append(	ContentSeparator2+newline+
											"Total sales this month:"+newline+
											salesnum+"\t"+currency.format(compiledtotal)+"\t"+currency.format(compiledcost)+"\t"+currency.format(compiledtotal-compiledcost));
				//write the monthly report
				writer = new BufferedWriter(new FileWriter(targetfile));
				writer.write(newmonthlyreport.toString());
			}
			
			writer.close();
		}
		catch(Exception exc)
		{exc.printStackTrace();}
		//popup confirmation + bill
		JOptionPane.showMessageDialog(frame, "Transaction complete:\n"+billtext);
	}

	//method to create tablemoddel for itemcounts
	public static TableModel getStockCountSets(boolean retail,boolean admin) 
	{
		//find the destination
		String targetfile = itemtpdatabasefilename;
		
		//setup the table for database
		DefaultTableModel tableModel;
		if(admin)
		{tableModel = new DefaultTableModel(null, countAdminTableModel);}
		else
		{tableModel = new DefaultTableModel(null, countTableModel);}
		//find out the itemtypes that code needs to find data on
	    ArrayList<String[]> exceldata = new ArrayList<String[]>();
	    ArrayList<String> itemtypes = new ArrayList<String>();
		try
		{
			FileInputStream excelFile = new FileInputStream(new File(targetfile));
		    Workbook workbook = new XSSFWorkbook(excelFile);
		    Sheet datatypeSheet = workbook.getSheetAt(0);
		    //explore the rows
		    Iterator<Row> iterator = datatypeSheet.iterator();
		    //skip excel's header manually
		    iterator.next();
		    while (iterator.hasNext()) 
		    {
	    		String[] itemarray = new String[3];
				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
	            Cell currentCell = cellIterator.next();
	            //check if item type is retail/grocer type
	            int itemtype = (int) currentCell.getNumericCellValue();
	            //if it is a match, add data into array.
	            if(retail && itemtype == 1 || !retail && itemtype == 0)
		        {
		            currentCell = cellIterator.next();		//name
		            itemarray[0] = formis.formatCellValue(currentCell);
		            itemtypes.add(formis.formatCellValue(currentCell));
		            currentCell = cellIterator.next();		//price
		            itemarray[1] = (currency.format(currentCell.getNumericCellValue()));
		            //addd COGS data if admin
		            if(admin)
			        {
			            currentCell = cellIterator.next();		//cogs
			            itemarray[2] = (currency.format(currentCell.getNumericCellValue())); 
		            }
		            exceldata.add(itemarray);
	            }
		    }
		    workbook.close();
		    
		    //using the data in exceldata, find the number of occurences of each itemtype
		    String secondtargetfile;
		    if(retail)
		    {secondtargetfile = retaildatabasefilename;}
		    else
		    {secondtargetfile = grocerdatabasefilename;}
		    excelFile = new FileInputStream(new File(secondtargetfile));
		    workbook = new XSSFWorkbook(excelFile);
		    datatypeSheet = workbook.getSheetAt(0);
		    //for each itemtype
		    for(String[] itemtype : exceldata)
		    {
		    	int count = 0;
			    iterator = datatypeSheet.iterator();
			    iterator.next();
			    while (iterator.hasNext()) 
			    {
			    	//get the itemname
					Row currentRow = iterator.next();
					Iterator<Cell> cellIterator = currentRow.iterator();
			    	Cell currentCell = cellIterator.next();
			    	currentCell = cellIterator.next();
			    	if(itemtype[0].equals(formis.formatCellValue(currentCell)))
			    	{count++;}
			    }
			    //add gathered data into model
			    ArrayList<String> compiledData = new ArrayList<String>();
			    compiledData.add(itemtype[0]);	//name
			    compiledData.add(itemtype[1]);	//cost
			    if(admin)
			    {compiledData.add(itemtype[2]);}//COGS
			    compiledData.add(count+"");		//count
				tableModel.addRow(compiledData.toArray(new String[0]));
		    }
		    workbook.close();
		}
		catch(Exception e)
		{e.printStackTrace();}
		
		//build model
		return tableModel;
	}

	//method to delete a row from excel file, based on ID
	public static void excelRemove(boolean retail,String ItemID) 
	{
		try
		{
			String target;
			if(retail)
				{target = retaildatabasefilename;}
			else
				{target = grocerdatabasefilename;}
			FileInputStream excelFile = new FileInputStream(new File(target));
		    Workbook workbook = WorkbookFactory.create(excelFile);
		    Sheet datatypeSheet = workbook.getSheetAt(0);
		            
		    //explore the rows
		    Row targetrow = null;
            int lastindex = datatypeSheet.getLastRowNum();
		    Iterator<Row> iterator = datatypeSheet.iterator();
		    //skip excel's header manually
		    iterator.next();
		    while(iterator.hasNext())
		    {
		    	Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                //get data from row: Hard coded in excel file.
                Cell currentCell = cellIterator.next();
                String cellID 	= formis.formatCellValue(currentCell);
                
                //Does given data match with all cells in a record?
                if( (cellID.equals(ItemID)))
                {
                	//find out which row this is and break loop
                    targetrow = currentRow;
                    break;
                }
		    }
	    
           	//shift excel record up- replace the item sold
            //if last row, delete. Otherwise, shift
            if(targetrow.getRowNum() == lastindex)
            {datatypeSheet.removeRow(targetrow);}
            else
            {datatypeSheet.shiftRows(targetrow.getRowNum()+1, lastindex, -1);}
            
            //all inputs acquired, write into excel now
            FileOutputStream ExcelWriter = new FileOutputStream(target);
            workbook.write(ExcelWriter);
            ExcelWriter.close();
		    workbook.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error Excel record deletion: Item "+ItemID+" match not found.");   
		}
	}

	//method to get the total cost of items listed in purchases
	public static long getPurchaseCost(JTable target) 
	{
		DefaultTableModel model = (DefaultTableModel) target.getModel();
		long cost = 0;
		for (int count = 0; count < model.getRowCount(); count++)
		{cost += Long.parseLong((model.getValueAt(count, 2).toString().replaceAll(",", "")));}
		return cost;
	}

	//method to add item to purchaseitem
	public static void addPurchaseItem(JTable target, String[] inputs) 
	{
		DefaultTableModel model = (DefaultTableModel) target.getModel();
		model.addRow(inputs);
		target.setModel(model);
	}

	//method to return an array of unique data in a specified column in either tables
	public static String[] getExcelUniqueSet(boolean retail) 
	{
		HashSet<String> allnames = new HashSet<String>();
		try
		{
			FileInputStream excelFile;
			if (retail)
			{excelFile = new FileInputStream(new File(retaildatabasefilename));}
			else
			{excelFile = new FileInputStream(new File(grocerdatabasefilename));}
			Workbook workbook = new XSSFWorkbook(excelFile);
		    Sheet datatypeSheet = workbook.getSheetAt(0);
		            
		    //explore the rows
		    Iterator<Row> iterator = datatypeSheet.iterator();
		    //skip excel's header manually
		    iterator.next();
		    while (iterator.hasNext()) 
	        {
		    	Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                //skip to the requested column
                Cell currentCell = cellIterator.next();
                //add into the complete list
		    	allnames.add(formis.formatCellValue(currentCell));
	        }
		    workbook.close();
		}
		catch(Exception e)
		{e.printStackTrace();}
		
		//convert and return hashset to array
		return allnames.toArray(new String[0]);
	}

	//method to build tablemodel for full excel portal for grocery data
	//parameter admin if the data to be fetched include confidential data
	public static DefaultTableModel getGrocerExcelData(boolean admin)
	{
		//setup the table for database
		ArrayList<String> headers = new ArrayList<String>();
		headers.add("ID");
		headers.add("Item");
		headers.add("Cost");
		if(admin)
		{headers.add("COGS");}
		DefaultTableModel tableModel = new DefaultTableModel(null, headers.toArray(new String[0]));
		//open excel file
		try
		{
			FileInputStream excelFile = new FileInputStream(new File(grocerdatabasefilename));
		    Workbook workbook = new XSSFWorkbook(excelFile);
		    Sheet datatypeSheet = workbook.getSheetAt(0);
		    
		    //explore the rows
		    Iterator<Row> iterator = datatypeSheet.iterator();
		    //skip excel's header manually
		    iterator.next();
		    while (iterator.hasNext()) 
	        {
		    	String Itemname = null, ItemID = null, Itemcost = null, Itemprincipal = null;
		            	
		        Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                //get data from row: Hardcoded in excel file.
                Cell currentCell = cellIterator.next();
                ItemID = formis.formatCellValue(currentCell);
                
                currentCell = cellIterator.next();
                Itemname = formis.formatCellValue(currentCell);
		                
                currentCell = cellIterator.next();
                Itemcost = "Rp "+currency.format(currentCell.getNumericCellValue());
		               
                if(admin)
                {
                	currentCell = cellIterator.next();
                	Itemprincipal = "Rp "+(currency.format(currentCell.getNumericCellValue()));
                }
                
                //add collected data into row
                ArrayList<String> objs = new ArrayList<String>();
                objs.add(ItemID);
                objs.add(Itemname);
                objs.add(Itemcost);
        		if(admin)
        		{objs.add(Itemprincipal);}
                tableModel.addRow(objs.toArray(new String[0]));
            }
            workbook.close();
		}
		catch(Exception e)
		{e.printStackTrace();}
		return tableModel;
	}
	
	//Method build tablemodel for retail data
	public static DefaultTableModel getRetailExcelData(boolean admin)
	{
		//setup the table for database
		ArrayList<String> headers = new ArrayList<String>();
		headers.add("ID");
		headers.add("Item");
		headers.add("Cost");
		if(admin)
		{headers.add("COGS");}
		headers.add("Stack");
		DefaultTableModel tableModel = new DefaultTableModel(null, headers.toArray(new String[0]));
		//open excel file
		try
		{
			FileInputStream excelFile = new FileInputStream(new File(retaildatabasefilename));
		    Workbook workbook = new XSSFWorkbook(excelFile);
		    Sheet datatypeSheet = workbook.getSheetAt(0);
				    
		    //explore the rows
		    Iterator<Row> iterator = datatypeSheet.iterator();
		    //skip excel's header manually
		    iterator.next();
		    while (iterator.hasNext()) 
	        {
		    	String Itemname = null, ItemID = null, Itemcost = null, Itemstack = null, Itemprincipal = null;
		    	
		    	Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                //get data from row: Hardcoded in excel file.
                Cell currentCell = cellIterator.next();
                ItemID = formis.formatCellValue(currentCell);
                currentCell = cellIterator.next();
                Itemname = formis.formatCellValue(currentCell);
                currentCell = cellIterator.next();
                Itemcost = "Rp "+currency.format(currentCell.getNumericCellValue());
                if(admin)
                {
                	currentCell = cellIterator.next();
                	Itemprincipal = "Rp "+(currency.format(currentCell.getNumericCellValue()));
                }
                currentCell = cellIterator.next();
                Itemstack = currency.format(currentCell.getNumericCellValue());
                
                //add collected data into row
                ArrayList<String> objs = new ArrayList<String>();
                objs.add(ItemID);
                objs.add(Itemname);
                objs.add(Itemcost);
        		if(admin)
        		{objs.add(Itemprincipal);}
                objs.add(Itemstack);
                tableModel.addRow(objs.toArray(new String[0]));
            }
            workbook.close();
		}
		catch(Exception e)
		{e.printStackTrace();}
		return tableModel;
	}
	
	//method to get data from excel file
	//returns an arraylist of raw data from excel file
	protected static ArrayList<String[]> getExcelData(boolean retail)
	{
		ArrayList<String[]> data = new ArrayList<String[]>();
		String target;
		if(retail)
		{target = retaildatabasefilename;}
		else
		{target = grocerdatabasefilename;}
		try
		{
			FileInputStream excelFile = new FileInputStream(new File(target));
		    Workbook workbook = new XSSFWorkbook(excelFile);
		    Sheet datatypeSheet = workbook.getSheetAt(0);
		            
		    //explore the rows
		    Iterator<Row> iterator = datatypeSheet.iterator();
		    //skip excel's header manually
		    iterator.next();
		    while (iterator.hasNext()) 
	        {
		    	String Itemname = null, ItemID = null, Itemcost = null, ItemPrincipal = null, ItemStack = null;
		            	
		        Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                //get data from row: Hardcoded in excel file.
                Cell currentCell = cellIterator.next();
                ItemID = formis.formatCellValue(currentCell);
                
                currentCell = cellIterator.next();
                Itemname = formis.formatCellValue(currentCell);
		                
                currentCell = cellIterator.next();
                Itemcost = (currency.format(currentCell.getNumericCellValue()));
                
                currentCell = cellIterator.next();
                ItemPrincipal = (currency.format(currentCell.getNumericCellValue()));

                //if retail, have another data: stack
                if(retail)
                {
                	currentCell = cellIterator.next();
                	ItemStack = formis.formatCellValue(currentCell);
                }
                
                //add collected data into row
                if(retail)
                {
                	String[] objs = {ItemID,Itemname,Itemcost,ItemPrincipal,ItemStack};
                    data.add(objs);
                }
                else
                {
                	String[] objs = {ItemID,Itemname,Itemcost,ItemPrincipal};
                    data.add(objs);
                }
            }
            workbook.close();
		}
		catch(Exception e)
		{e.printStackTrace();}
		
		return data;
	}
	
	//method to insert a row into an excel file
	protected static void addNewItem(boolean retail, String code, String name, String[] itemdatas) 
	{
		String target;
		if(retail)
		{target = retaildatabasefilename;}
		else
		{target = grocerdatabasefilename;}
		
		//open excel
		try
		{
			FileInputStream excelFile = new FileInputStream(new File(target));
		    Workbook workbook = new XSSFWorkbook(excelFile);
		    Sheet datatypeSheet = workbook.getSheetAt(0);
		    
		    //navigate to the last row of excel sheet
		    int lastrow = datatypeSheet.getLastRowNum()+1;
		    //add row to sheet
		    Row newrow = datatypeSheet.createRow(lastrow);
		    //insert data to cells
		    DataFormat format = workbook.createDataFormat();
		    CellStyle moneystyle = workbook.createCellStyle();
		    moneystyle.setDataFormat(format.getFormat("#,##0"));
		    CellStyle stackstyle = workbook.createCellStyle();
		    stackstyle.setDataFormat(format.getFormat("0"));
		    Cell cell = newrow.createCell(0);		//item ID
		    cell.setCellValue(code);
		    cell = newrow.createCell(1);			//name
		    cell.setCellValue(name);
		    cell = newrow.createCell(2);			//cost
		    cell.setCellValue((Long.valueOf( itemdatas[0].replaceAll(",", "") )));
		    cell.setCellStyle(moneystyle);    
	    	cell = newrow.createCell(3);		//COSG
		    cell.setCellValue((Long.valueOf(itemdatas[1].replaceAll(",", ""))));
	    	cell.setCellStyle(moneystyle);
		    if(retail)
			{	
		    	cell = newrow.createCell(4);		//stack
			    cell.setCellValue(Integer.valueOf( itemdatas[2] ));
			    cell.setCellStyle(stackstyle);
		    }
		    excelFile.close();
		    
		    // Write the output to a file
		    FileOutputStream excelNew = new FileOutputStream(target);
		    workbook.write(excelNew);
		    excelNew.close();
		    workbook.close();
		}
		catch(Exception e)
		{e.printStackTrace();}
	}
	
	//method to find item from table based on code
	protected static String[] findItem(boolean retail, String filter) 
	{
		//grab data from excel table
		ArrayList<String[]> rawdata = getExcelData(retail);
		//explore the list until a match is found
		for (String[] row: rawdata)
		{
			//if a matching identifier is found, return the row data.
			if(row[0].equals(filter))
			{return row;}
		}
		
		//return NO-MATCH
		return null;
	}
	
	//method to update or change itemid item's data w/ complete knowledge
	public static void updateExcel(boolean retaildatabase, String itemid, String newname, long newcost, int stack, long newCOGS) 
	{
		String target;
		if(retaildatabase)
		{target = retaildatabasefilename;}
		else
		{target = grocerdatabasefilename;}
		
		//open excel
		try
		{
			FileInputStream excelFile = new FileInputStream(new File(target));
		    Workbook workbook = new XSSFWorkbook(excelFile);
		    Sheet datatypeSheet = workbook.getSheetAt(0);
		    
		    //find the target row
		    int targetrowcount = 0;
		    Iterator<Row> iterator = datatypeSheet.iterator();
		    //skip excel's header manually
		    iterator.next();
		    while (iterator.hasNext()) 
	        {
		        Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                //get data from row: Hardcoded in excel file.
                Cell idcell = cellIterator.next();
                String ItemID = formis.formatCellValue(idcell);
                //if this is the target row, break; the currentrow is known.
                if(ItemID.equals(itemid))
                {
                	targetrowcount = currentRow.getRowNum();
                	break;
                }
	        }
		    if(targetrowcount != 0)
			{
		    	DataFormat format = workbook.createDataFormat();
			    CellStyle moneystyle = workbook.createCellStyle();
			    moneystyle.setDataFormat(format.getFormat("#,##0"));
			    CellStyle stackstyle = workbook.createCellStyle();
			    stackstyle.setDataFormat(format.getFormat("0"));
			    //make changes to row
	            Cell targetcell = datatypeSheet.getRow(targetrowcount).getCell(1);  
	            targetcell.setCellValue(newname);										//name
	            targetcell = datatypeSheet.getRow(targetrowcount).getCell(2);  
	            targetcell.setCellValue(newcost);										//cost
	            targetcell.setCellStyle(moneystyle);
	            if(retaildatabase)
			    {
	            	targetcell = datatypeSheet.getRow(targetrowcount).getCell(3);		//COSG
	            	targetcell.setCellValue(newCOGS);
		            targetcell.setCellStyle(moneystyle);
	            	targetcell = datatypeSheet.getRow(targetrowcount).getCell(4);		//stack
	            	targetcell.setCellValue(stack);
		            targetcell.setCellStyle(stackstyle);
			    }
			    else
			    {
			    	targetcell = datatypeSheet.getRow(targetrowcount).getCell(3);		//COSG
			    	targetcell.setCellValue(newCOGS);
		            targetcell.setCellStyle(moneystyle);
			    }
		    }
		    else
		    {JOptionPane.showMessageDialog(frame,"Error in excel record update: ID not found.");}
		    excelFile.close();
		   
		    //write the changes
		    FileOutputStream excelNew = new FileOutputStream(target);
		    workbook.write(excelNew);
		    excelNew.close();
		    workbook.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		    JOptionPane.showMessageDialog(frame, "Error in excel record update: System error.");   
		}
	}
	
	//method to return all ID instances of [itemname], excluding self.
	public static ArrayList<String> findItemInstances(boolean retail, String itemname)
	{
		ArrayList<String> instanceIDs = new ArrayList<String>();
		ArrayList<String[]> rawdata = getExcelData(retail);
		
		for(String[] row:rawdata)
		{
			//if a match is found, take note.
			if(row[1].equals(itemname))
			{instanceIDs.add(row[0]);}
		}
		//return the results
		return instanceIDs;
	}
	
	//Method to collect multiple items matching input text
	public static ArrayList<String[]> collectionsearch(boolean retail, String input)
	{
		ArrayList<String[]> results = new ArrayList<String[]>();
		//determine which database to read
		DefaultTableModel database;
		if(retail)
		{database = getRetailExcelData(true);}
		else
		{database = getGrocerExcelData(true);}
		//for each item in database
	    for(int i = 0; i < database.getRowCount();i++)
	    {
			//compare columnfocus and text- match?
			//if yes, add
	    	if(database.getValueAt(i, 0).toString().matches(input))
	    	{results.add(findItem(retail, database.getValueAt(i, 0).toString()));}
			//continue
	    }
		
		//return collection
		return results;
	}
	
	//method to return a string[] of item types in itemtypes database 
	public static String[] getItemTypes(boolean retail)
	{
		ArrayList<String> nameids = new ArrayList<String>();
		//connect to database
		try
		{
			FileInputStream excelFile = new FileInputStream(new File(itemtpdatabasefilename));
		    Workbook workbook = new XSSFWorkbook(excelFile);
		    Sheet datatypeSheet = workbook.getSheetAt(0);
		    Iterator<Row> iterator = datatypeSheet.iterator();
		    //skip first row
		    iterator.next();
		    while (iterator.hasNext()) 
	        {
		        Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                Cell cell = cellIterator.next();
                int itemtype = (int) (cell.getNumericCellValue());
    			//depending on boolean, get the list with first column 0 = not retail item || 1 = retail item
                if(retail && itemtype == 1 || !retail && itemtype == 0)
                {
                	cell = cellIterator.next();
                	nameids.add(formis.formatCellValue(cell));
                }                
	        }
	    workbook.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error in excel fetch itemtypes.");   
		}
		return nameids.toArray(new String[0]);
	}
	
	//method to return specific item data (price, cogs, stack) from itemtypes database
	public static String[] getItemData(String itemtype)
	{
		ArrayList<String> itemdata = new ArrayList<String>();

		try
		{
			//explore the list until a match is found
			FileInputStream excelFile = new FileInputStream(new File(itemtpdatabasefilename));
		    Workbook workbook = new XSSFWorkbook(excelFile);
		    Sheet datatypeSheet = workbook.getSheetAt(0);
		    Iterator<Row> iterator = datatypeSheet.iterator();
		    //skip first row
		    iterator.next();
		    while (iterator.hasNext()) 
	        {
		        Row currentRow = iterator.next();
		        Iterator<Cell> cellIterator = currentRow.iterator();
		        //skip boolean column
		        Cell cell = cellIterator.next();
		        cell = cellIterator.next();
				//if a matching identifier is found, return the row data.
				if(formis.formatCellValue(cell).equals(itemtype))
				{				
			        cell = cellIterator.next();		//cost
					itemdata.add(formis.formatCellValue(cell));
			        cell = cellIterator.next();		//cogs
					itemdata.add(formis.formatCellValue(cell));
			        cell = cellIterator.next();		//stack
					itemdata.add(formis.formatCellValue(cell));
				    workbook.close();
					return itemdata.toArray(new String[0]);
				}
			}
		    workbook.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error in excel fetch item type data.");   
		}
		return null;
	}

	//method to add new row to itemtype database
	public static void addNewItemType(boolean retail, String[] typedata)
	{
		//open excel
		try
		{
			FileInputStream excelFile = new FileInputStream(new File(itemtpdatabasefilename));
		    Workbook workbook = new XSSFWorkbook(excelFile);
		    Sheet datatypeSheet = workbook.getSheetAt(0);
		    
		    //navigate to the last row of excel sheet
		    int lastrow = datatypeSheet.getLastRowNum()+1;
		    //add row to sheet
		    Row newrow = datatypeSheet.createRow(lastrow);
		    //insert data to cells
		    DataFormat format = workbook.createDataFormat();
		    CellStyle moneystyle = workbook.createCellStyle();
		    moneystyle.setDataFormat(format.getFormat("#,##0"));
		    CellStyle stackstyle = workbook.createCellStyle();
		    stackstyle.setDataFormat(format.getFormat("0"));
		    Cell cell = newrow.createCell(0);		//item type
		    if(retail)
		    {cell.setCellValue(1);}
		    else
		    {cell.setCellValue(0);}
		    cell.setCellStyle(stackstyle);
		    cell = newrow.createCell(1);			//name
		    cell.setCellValue(typedata[0]);
		    cell = newrow.createCell(2);			//cost
		    cell.setCellValue((Long.valueOf( typedata[1].replaceAll(",", "") )));
		    cell.setCellStyle(moneystyle);
	    	cell = newrow.createCell(3);			//COSG
		    cell.setCellValue((Long.valueOf(typedata[2].replaceAll(",", ""))));
	    	cell.setCellStyle(moneystyle);
	    	cell = newrow.createCell(4);			//stack
		    cell.setCellValue(Integer.valueOf( typedata[3] ));
		    cell.setCellStyle(stackstyle);
		    
		    excelFile.close();
		    
		    // Write the output to a file
		    FileOutputStream excelNew = new FileOutputStream(itemtpdatabasefilename);
		    workbook.write(excelNew);
		    excelNew.close();
		    workbook.close();
		}
		catch(Exception e)
		{e.printStackTrace();}
	}
	
	//method to change the data of itemtype- leads to changing all occurences of itemtype as well.
	static void itemTypeChange(boolean retail,String itemtype,String typename,long typecost,long typeCOGS, int typestck)
	{
		//access itemtype database
		try
		{
			FileInputStream excelFile = new FileInputStream(new File(itemtpdatabasefilename));
		    Workbook workbook = new XSSFWorkbook(excelFile);
		    Sheet datatypeSheet = workbook.getSheetAt(0);
		    DataFormat format = workbook.createDataFormat();
		    CellStyle moneystyle = workbook.createCellStyle();
		    moneystyle.setDataFormat(format.getFormat("#,##0"));
		    CellStyle stackstyle = workbook.createCellStyle();
		    stackstyle.setDataFormat(format.getFormat("0"));
		    Iterator<Row> iterator = datatypeSheet.iterator();
		    //skip excel's header manually
		    iterator.next();
		    while (iterator.hasNext()) 
	        {
		    	Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                //get data from row: Hardcoded in excel file.
                Cell cell = cellIterator.next();
                cell = cellIterator.next();	//itemtype cell
                String ItemName = formis.formatCellValue(cell);
            	//if match item name, change data.
                if(ItemName.equals(itemtype))
                {
                	//make changes to row
                	cell.setCellValue(typename);		//name
                	cell = cellIterator.next();
                	cell.setCellValue(typecost);		//cost
                	cell.setCellStyle(moneystyle);
                	cell = cellIterator.next();
                	cell.setCellValue(typeCOGS);		//cogs
                	cell.setCellStyle(moneystyle);
                	cell = cellIterator.next();
                	cell.setCellValue(typestck);		//stack
                	cell.setCellStyle(stackstyle);
                }
                
	        }
		    //apply changes to itemtype dbase
		    FileOutputStream excelNew = new FileOutputStream(itemtpdatabasefilename);
		    workbook.write(excelNew);
		    excelNew.close();
		    workbook.close();
		}
		catch(Exception e)
		{e.printStackTrace();}
		
		//find all occurences of itemtype
		ArrayList<String> instances = findItemInstances(retail, itemtype);
		//for each instance of old itemtype, change data.
		for(String instance : instances)
		{
			//change items data
			updateExcel(retail, instance, typename, typecost, typestck, typeCOGS);
		}
	}
	
	//method to delete itemtype and all items having itemtype from database
	static void deleteItemType(boolean retail, String itemtype)
	{
		//remove from itemtype database
		try
		{
			FileInputStream excelFile = new FileInputStream(new File(itemtpdatabasefilename));
		    Workbook workbook = new XSSFWorkbook(excelFile);
		    Sheet datatypeSheet = workbook.getSheetAt(0);
		  //explore the rows
		    Row targetrow = null;
            int lastindex = datatypeSheet.getLastRowNum();
		    Iterator<Row> iterator = datatypeSheet.iterator();
		    //skip excel's header manually
		    iterator.next();
		    while(iterator.hasNext())
		    {
		    	Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                //get data from row: Hard coded in excel file.
                Cell currentCell = cellIterator.next();
                currentCell = cellIterator.next();
                String cellname 	= formis.formatCellValue(currentCell);
                
                //Does given data match with all cells in a record?
                if( (cellname.equals(itemtype)))
                {
                	//find out which row this is and break loop
                    targetrow = currentRow;
                    break;
                }
		    }
	    
           	//shift excel record up- replace the item sold
            //if last row, delete. Otherwise, shift
            if(targetrow.getRowNum() == lastindex)
            {datatypeSheet.removeRow(targetrow);}
            else
            {datatypeSheet.shiftRows(targetrow.getRowNum()+1, lastindex, -1);}
            
            //all inputs acquired, write into excel now
            FileOutputStream ExcelWriter = new FileOutputStream(itemtpdatabasefilename);
            workbook.write(ExcelWriter);
            ExcelWriter.close();
		    workbook.close();
		}
		catch(Exception e)
		{e.printStackTrace();}
		//remove all instances of itemtype in stock
		ArrayList<String> instances = findItemInstances(retail,itemtype);
		for(String instance : instances)
		{excelRemove(retail,instance);}
	}
	
	//Reset all components
	public static void refreshTables()
	{
		
		StoreGrocer.refreshMe();
		StoreRetail.refreshMe();
		StoreStocking.refreshMe();
		StoreAdmin.refreshMe();
		StoreBillColl.refreshMe();
		StoreStockingSchedule.refreshMe();
	}
}
