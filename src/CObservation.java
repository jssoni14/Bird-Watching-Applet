//CObservation

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


public class CObservation implements Serializable

{

	public static final String[] birds = {"Sparrow", "Goldfinch", "Crow", "Cardinal", "BlueJay", "Other"};
	
	private String name;
	private int birdindex;
	private int quantity;
	private String tobs;
	
		
	public CObservation(String name, int bird, int q)
	{
		this.name = name;
		this.birdindex = bird;
		this.quantity = q;	
		Calendar cal = Calendar.getInstance();
		tobs = cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
		System.out.println(tobs);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBird() {
		return birdindex;
	}

	public void setBird(int bird) {
		this.birdindex = bird;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return 
				String.format("%s, %d %s, %s", name,quantity,birds[birdindex],tobs);
	}
}
