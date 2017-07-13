import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Day_Time {
	
	private String intime;
	private String outtime;
	private String time;
	private long minutes;
	private Date date1;
	private Date date2;
	
	public Day_Time(String in, String out){
//		String s[] = intime.split(":");
//		intime_hours = Integer.parseInt(s[0]);
//		intime_minutes = Integer.parseInt(s[1]);
//		String s1[] = outtime.split(":");
//		outtime_hours = Integer.parseInt(s1[0]);
//		outtime_minutes = Integer.parseInt(s1[1]);
		intime = in;
		outtime = out;
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		long difference, hours, seconds;
		
		try {
			date1 = format.parse(intime);
			date2 = format.parse(outtime);
			difference = date2.getTime() - date1.getTime(); 
	        seconds = difference / 1000;
			minutes = seconds / 60;
			hours = minutes / 60;
			time = hours % 24 + ":" + minutes % 60; 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getTotaltime(){
		return time;
	}
	
	public long getminutes(){
		return minutes;
	}
	
	public String getintime(){
		return intime;
	}
	
	public String getOuttime(){
		return outtime;
	}
	
	public String toString(){
		return "In - " + intime + "Out - " + outtime +"Total - " + time; 
	}
	
	public String toCSV(){
		return intime + "," + outtime;
	}

}
