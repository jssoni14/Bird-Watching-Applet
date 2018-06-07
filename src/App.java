import java.util.Vector;

public class App{
	
	private Vector <CObservation> v = new Vector <CObservation>();
	private String [] Names = new String[10];
	private int ptr;
	
	public App(){}
	
	public void AddObservation(String names, int BirdIndex, int quantity){
		CObservation newObs = new CObservation(names, BirdIndex, quantity);
		v.add(newObs);
	}
	public boolean addName(String name){
		for(int count=0; count<10; count++)
			if(Names[count].equals(name))
				return false;
		Names[ptr]=name;
		ptr++;
		return true;
	}
	public Vector <CObservation> getObservation(){
		return v;
	}
	public String [] getNames(){
		return Names;
	}
	public Vector <CObservation> getbyNames(String name){
		Vector <CObservation> d = new Vector <CObservation>();
		for(CObservation o:v)
			if(o.getName().equals(name))
				d.add(o);
		return d;
	}
	public void readData(){
		v=CData.readfromFileObservations();
		Names=CData.readNames();
		for(int count=0; count<10;count++)
			if(Names[count]!=null)
				ptr++;
		}
	public void writeData(){
		CData.writeToFileObservations(v);
		CData.writeNames(Names);
	}
}