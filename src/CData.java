//CData
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class CData
{
	private static File fn = new File("data.txt");
	private static File fnNames = new File("names.txt");
	
	public static void writeToFileObservations(Vector<CObservation> v) 
	{
	    try
	    {
	    FileOutputStream fos = new FileOutputStream(fn);
	    DataOutputStream outStream = new DataOutputStream(fos);
	    ObjectOutputStream ooStream = new ObjectOutputStream(outStream);
	    for (CObservation x: v )
	    {
	        ooStream.writeObject(x);	        
	    }//end for	   
	    ooStream.close();
	 }//end try
	    catch (FileNotFoundException e){}	 
	 catch (Exception e)
	 {
	     JOptionPane.showMessageDialog(null,e.toString());
   }
}//end writeToFile
	
	public static Vector<CObservation> readfromFileObservations()
	   {
	       Vector<CObservation> v = new Vector<CObservation>();
	       try
	       {
	       FileInputStream fis = new FileInputStream(fn);
	       ObjectInputStream oiStream = new ObjectInputStream(fis);
	       while (true)
	       {
	           CObservation cc = (CObservation)oiStream.readObject();	          
	           v.add(cc);
	        }//end while    
	    }//end try
	   
	    catch (EOFException e){}
	    catch (FileNotFoundException e){}
	    catch (Exception e)
	    {  JOptionPane.showMessageDialog(null,e.toString()); } 
	    return v;      
	    }//end readFromFile 
	
	
	public static String[] readNames() 
	{
		String[] names = new String[10];
		int ptr = 0;
	    try
	    {
	    Scanner sc = new Scanner(new FileReader(fnNames));
	    while (sc.hasNext())
	    {
	        names[ptr] = sc.nextLine();
	        ptr++;
	    }//end for	  
	    sc.close();
	 }//end try
	catch (FileNotFoundException e){}
	 catch (Exception e)
	 {
	     JOptionPane.showMessageDialog(null,e.toString());
   }
	    return names;
}//end writeToFile
	
	public static void writeNames(String[] names)
	   {
	       
	       try
	       {
	       PrintWriter pw = new PrintWriter(fnNames);
	       for (int j=0; j<10;j++)
	       {
	    	   if (names[j]!=null)
	    	  		   pw.println(names[j]);
	        }//end while
	       pw.close();
	    }//end try
	   
	     catch (Exception e)
	    {  JOptionPane.showMessageDialog(null,e.toString()); } 
	   	    }//end readFromFile  
	
}//end of class    
