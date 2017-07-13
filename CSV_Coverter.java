import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@SuppressWarnings("unused")
public class CSV_Coverter {

	private static List<Employee_Time> Employees = new ArrayList<Employee_Time>();
	private static Employee_Time employee;
	private static Map<Integer,Day_Time> Timing;
	private static Day_Time time;
	private static String header;
	private static int No_of_Days;
	
	public static void main(String[] args) {
		
//		time = new Day_Time("", "18:47");
//		System.out.println(time.getTotaltime());
//		System.out.println(time.getminutes());
		// TODO Auto-generated method stub
		Employees.clear();
		double no_Working_Days;
		try{
			BufferedReader filein = new BufferedReader(new FileReader("C:/Users/Healers at home/Desktop/Book1.csv"));
			String sCurrentLine;
			int Lineno = 0;
			header = filein.readLine();       // Read Header
			while ((sCurrentLine = filein.readLine()) != null) 
			{	
				String s[]= sCurrentLine.split(",");
				if(Lineno % 4 == 0)
				{
					String s1[] = s[0].split("  ");
					String s2[] = s1[1].split(":");
					String s3[] = s1[0].split(":");
					employee = new Employee_Time(s3[1],s2[1]);
				}
				if(Lineno == 1){
					List<String> stringList = new ArrayList<String>(Arrays.asList(s));
					No_of_Days = Integer.parseInt(stringList.get(stringList.size() - 1));
				}
				if((Lineno-2) %4 ==0){
					no_Working_Days = 0;
					List<String> stringList = new ArrayList<String>(Arrays.asList(s));
					int i;
					Timing = new HashMap<Integer,Day_Time>();
					for(i = 0; i < stringList.size(); i++){
						String line = stringList.get(i);
						String s1[] = line.split(" ");
						List<String> timingstringlist = new ArrayList<String>(Arrays.asList(s1));
						if(timingstringlist.size() == 1 && (!timingstringlist.get(0).equals(""))){
							time = new Day_Time("10:00","14:45");
							Timing.put(i+1,time);
							no_Working_Days += 0.5;
//							System.out.print(time);
						}
						else if(timingstringlist.size() >= 2){
							time = new Day_Time(timingstringlist.get(0),timingstringlist.get(timingstringlist.size() - 1));
							Timing.put(i+1,time);
							no_Working_Days++;
//							System.out.print(time);
						}
					}
					employee.setTime(Timing);
					employee.setDays(no_Working_Days);
					Employees.add(employee);
//					System.out.println("");
				}
				Lineno++;
			}
			filein.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		PrintoutputCSV();
	}
	
	public static void PrintoutputCSV(){
		try{
			FileWriter fw = new FileWriter("C:/Users/Healers at home/Desktop/Book2.csv");
			int i = 0;
			for(i = 0; i < Employees.size(); i++){
				fw.append(Employees.get(i).getCSV());
				fw.append("\n");
			}
			fw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		try{
			FileWriter fw = new FileWriter("C:/Users/Healers at home/Desktop/Book3.csv");
			int i = 0;
			for(i = 0; i < Employees.size(); i++){
				fw.append(Employees.get(i).getDetailsCSV());
				fw.append("\n");
			}
			fw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
