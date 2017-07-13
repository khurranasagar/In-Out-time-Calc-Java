import java.util.Map;
import java.util.Map.Entry;

public class Employee_Time {
	
	private String ID;
	private String Name;
	private Map<Integer,Day_Time> Timings;
	private long Totalminutes;
	private double Days_Working;
	
	public Employee_Time(String id, String name){
		ID = id;
		Name = name;
	}
	
	public void setTime(Map<Integer,Day_Time> tim){
		Timings = tim;
		this.getTotalminutes();
	}
	
	public String getname(){
		return Name;
	}
	
	public String getid(){
		return ID;
	}

	public Map<Integer,Day_Time> gettimings(){
		return Timings;
	}
	
	public long getTotalminutes(){
		Totalminutes = 0;
		for(Entry<Integer,Day_Time> entry : Timings.entrySet()){
			Totalminutes += entry.getValue().getminutes();
		}
		return Totalminutes;
	}
	
	public String getCSV(){
		String obj = "ID," + ID + ",Name," + Name + "\n";
		obj += "DAY,";
		for(Entry<Integer,Day_Time> entry : Timings.entrySet()){
			obj += entry.getKey() + ",,";
		}
		obj += "\n,";
		for(@SuppressWarnings("unused") Entry<Integer,Day_Time> entry : Timings.entrySet()){
			obj += "IN,OUT,";
		}
		obj += "\n,";
		for(Entry<Integer,Day_Time> entry : Timings.entrySet()){
			obj +=  entry.getValue().toCSV() + ",";
		}
		obj += "\nTotal,";
		for(Entry<Integer,Day_Time> entry : Timings.entrySet()){
			obj += entry.getValue().getTotaltime() + ",,";
		}
		obj += "\n";
		return obj;
	}
	
	public void setDays(double days){
		Days_Working = days;
	}
	
	public String getDetailsCSV(){
		double min = Totalminutes/Days_Working;
		int hours = (int)(min/60);
		int mins = (int)(min % 60);
		return "Employee ID," + ID + "\nName," + Name + "\nAverage Hours," + hours +":" + mins + "\nWorking Days," + Days_Working + "\n";
	}
	
	public double getDays(){
		return Days_Working;
	}
	
}
