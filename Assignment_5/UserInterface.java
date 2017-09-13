import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*************************************************************
* Dalton Nofs                                                *
* Login ID: nofs5491                                         *
* CS-102, Summer 2017                                        *
* Programming Assignment 5                                   *
* UserInterface class:  user interface for program	         *
**************************************************************/
public class UserInterface extends JFrame implements EventHandler
{
	static JFrame mainWindow;
	static JFrame searchWindow;
	
	/*************************************************************
	* Method: UserInterface()	                      	         *
	* Purpose: default constructor for ui class			         *
	*          							                         *
	* Parameters:                N/A                             *
	* Returns: void:             N/A						     *
	**************************************************************/
	public UserInterface()
	{
		mainWindow = new JFrame();
	}
	
	/*************************************************************
	* Method: UserInterface()	                      	         *
	* Purpose: overriding constructor for ui class		         *
	*          							                         *
	* Parameters: String:		The title of the window          *
	* Returns: void:             N/A						     *
	**************************************************************/
	public UserInterface(String windowTitle)
	{
		mainWindow = new JFrame(windowTitle);
	}
	
	/*************************************************************
	* Method: getMainWindow()	                      	         *
	* Purpose: returns the mainWindow JFrame()			         *
	*          							                         *
	* Parameters: Void:			 N/A							 *
	* Returns: JFrame:           The mainWindow				     *
	**************************************************************/
	public static JFrame getMainWindow()
	{
		return mainWindow;
	}
	
	/*************************************************************
	* Method: getSearchWindow()	                      	         *
	* Purpose: returns the mainWindow JFrame()			         *
	*          							                         *
	* Parameters: Void:			 N/A							 *
	* Returns: JFrame:           The searchWindow			     *
	**************************************************************/
	public static JFrame getSearchWindow()
	{
		return searchWindow;
	}
	
	/*************************************************************
	* Method: sendMessage()		                      	         *
	* Purpose: for sending messages to user				         *
	*          							                         *
	* Parameters: String[2]:	the body of the message, window  *
	* 							  title					         *
	* Returns: void:             N/A						     *
	**************************************************************/
	public static void sendMessage(String messageBody, String messageTitle)
    {
        JOptionPane.showMessageDialog(null, messageBody, messageTitle, JOptionPane.INFORMATION_MESSAGE);
    }
	
	/*************************************************************
	* Method: sendWarning()		                      	         *
	* Purpose: for sending messages to user				         *
	*          							                         *
	* Parameters: String[2]:	the body of the message, window  *
	* 							  title					         *
	* Returns: void:             N/A						     *
	**************************************************************/
	public static void sendWarning(String messageBody, String messageTitle)
    {
        JOptionPane.showMessageDialog(null, messageBody, messageTitle, JOptionPane.ERROR_MESSAGE);
    }
	
	/*************************************************************
	* Method: addMainWindow()	                      	         *
	* Purpose: makes mainWindow as below standards		         *
	*          							                         *
	* Parameters:                N/A                             *
	* Returns: void:             N/A						     *
	**************************************************************/
	public void addMainWindow()
	{
		mainWindow = new JFrame("Main Window"); // create a new frame
		mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE); // close pgrm when closed
		mainWindow.setSize(300,600); // set the size
		GridLayout aGrid = new GridLayout(0,1,0,5); // config the layout
		mainWindow.setLayout(aGrid); // set window layout
		
		// create buttons
		JButton crsSearch 	= new JButton("Search for course number");
		JButton titleSearch = new JButton("Search course titles");
		JButton prntRecords = new JButton("Print all records");
		JButton cpuGPA 		= new JButton("Compute GPA");
		JButton addCrs		= new JButton("Add Course");
		JButton rmCrs 		= new JButton("Remove Course");
		JButton editCrs		= new JButton("Edit Course");
		JButton strData 	= new JButton("Store Database");
		JButton ldData 		= new JButton("Reload Database");
		JButton exit 		= new JButton("Exit");
		
		// Add buttons to frame
		mainWindow.add(crsSearch);
		mainWindow.add(titleSearch);
		mainWindow.add(prntRecords);
		mainWindow.add(cpuGPA);
		mainWindow.add(addCrs);
		mainWindow.add(rmCrs);
		mainWindow.add(editCrs);
		mainWindow.add(strData);
		mainWindow.add(ldData);
		mainWindow.add(exit);
		
		// config handler
		MainWindowHandler mainWindowHandler = new MainWindowHandler();
		
		// add listeners
		crsSearch.addActionListener(mainWindowHandler);
		titleSearch.addActionListener(mainWindowHandler);
		prntRecords.addActionListener(mainWindowHandler);
		cpuGPA.addActionListener(mainWindowHandler);
		addCrs.addActionListener(mainWindowHandler);
		rmCrs.addActionListener(mainWindowHandler);
		editCrs.addActionListener(mainWindowHandler);
		strData.addActionListener(mainWindowHandler);
		ldData.addActionListener(mainWindowHandler);
		exit.addActionListener(mainWindowHandler);
		
		// make the window visable
		mainWindow.setVisible(true);
	}
	
	/*************************************************************
	* Method: addSearchWindow()	                      	         *
	* Purpose: makes searchWindow as below standards	         *
	*          							                         *
	* Parameters: Boolean:       Text Enable for [num,title,term]*
	* 								true for enabled             *
	* Returns: void:             N/A						     *
	**************************************************************/
	public static void addSearchWindow(boolean num, boolean title, boolean term)
	{
		searchWindow = new JFrame("Search WIndow"); // create a new frame
		// destroy the Jframe on closing
		searchWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		searchWindow.setSize(350,200); // set the size
		GridLayout aGrid = new GridLayout(4,2); // config the layout
		searchWindow.setLayout(aGrid); // set window layout
		
		// create buttons
		JButton search 	= new JButton("Search");
		JButton close = new JButton("Close");

		// create text boxes
		JTextField courseNum = new JTextField();
		JTextField courseTitle = new JTextField();
		JTextField courseTerm = new JTextField();
		
		// create lables
		JLabel courseNumLbl = new JLabel("Enter the Course Number:");
		JLabel courseTitleLbl = new JLabel("Enter the Course Title:");
		JLabel courseTermLbl = new JLabel("Course Term (yyyytt format):");
		
		// disable the text boxes based upon the spawn
		if(!num)
			courseNum.setEnabled(false);
		if(!title)
			courseTitle.setEnabled(false);
		if(!term)
			courseTerm.setEnabled(false);
		
		// Add buttons to frame
		searchWindow.add(courseNumLbl);
		searchWindow.add(courseNum);
		searchWindow.add(courseTitleLbl);
		searchWindow.add(courseTitle);
		searchWindow.add(courseTermLbl);
		searchWindow.add(courseTerm);
		searchWindow.add(search);
		searchWindow.add(close);
		
		// config handler
		SearchWindowHandler searchWindowHandler = new SearchWindowHandler();
		
		// add listeners to buttons
		search.addActionListener(searchWindowHandler);
		close.addActionListener(searchWindowHandler);
	}
		
}
