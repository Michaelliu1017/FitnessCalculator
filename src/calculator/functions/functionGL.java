package calculator.functions;

/* * * * * * * * * * * * * * * * * 
*       OOP Final Project        *
*   GUOLIANG LIU     04/Dec/24   *
*                                *
*   Version 3        10/Dec/24   *
*   Function 1:   BMR            *
*   Function 2:   BMI            *
* * * * * * * * * * * * * * * * */
public class functionGL {
	
	private double weight;
	private double height;
	private int age;
	//Female=0 Male=1
	private int sex;
	//Daily Basal Metabolic Rate 
	private double BMR;
	// Ideal weight
	private double BMI;
	private int habit;
	
	public void set_habit(int a) {
		if (a>=0 && a<=3) {
			habit=a;
		}else {
			System.out.println("ERROR[1]: Illigal Format");
		}
	}
	
	public int get_habit() {
		return habit;
	}
	public String BMI_Calculator(double height) {
		//18.5 – 24.9  
		double BMI_lb=18.5;
	    double BMI_ub=24.9;
		double result=0;
		double height_cup=height;
		String str="";
		
		if(format_checker(1, height_cup, 1,1)) {
			
			this.weight=weight;
			this.height=height;
			this.age=age;
			this.sex=sex;
			result=height*height/10000*BMI_lb;
			String  str1 = String.format("%.2f",result);
			result=height*height/10000*BMI_ub;
			String  str2 = String.format("%.2f",result);
			str=str1+" ~ "+str2+" kg";
		}else {
			
			System.out.println("ERROR[2]: Illigal Format");
		}
	
		return str;
	}
	
	public double BMR_Calculator(double weight, double height, int age, int sex) {
		// According to  Mifflin-St Jeo's formula
		// BMR of Male = 10 * weight + 6.25 * height - 2 * age + 5
		// BMR of Female=10 * weight + 6.25 * height - 2 * age - 161

		double result=0;
		double weight_cup=weight;
		double height_cup=height;
		int age_cup=age;
		int sex_cup=sex;
		//
		if(format_checker(weight_cup,height_cup,age_cup,sex_cup)) {
			this.weight=weight;
			this.height=this.height;
			this.age=this.age;
			this.sex=this.sex;
			
			if(sex_cup==1) {
				result=10 * weight + 6.25 * height - 5 * age + 5;
				
			}else {	
				result=10 * weight + 6.25 * height - 2 * age - 161;
			}
		}else {
			result=-1;
		}
		
		return result;
	}
	
	private Boolean format_checker(double weight, double height, int age, int sex) {
		Boolean result=true;
		if(weight<=0) {
			result=false;
		}else if(height<=0) {
			result=false;
		}else if(age<=0) {
			result=false;
		}else if(sex!=0&&sex!=1) {
			result=false;
		}
		return result;
	}
}
