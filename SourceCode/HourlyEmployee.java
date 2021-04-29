package SourceCode;
import java.util.Date;
import java.util.Calendar;
import SourceCode.Employee;


public class HourlyEmployee extends Employee{
	private double hourlyRate;
	private double[] weeklyHours;
	private double arrears = 0;
	public HourlyEmployee(String name, String email, paymentMethod paymentMethod, double commisionRate, double hourlyRate){
		super(name, email, paymentMethod, commisionRate);
		this.hourlyRate = hourlyRate;
		this.weeklyHours = new double[5];
	}
	public HourlyEmployee(String name, String email, paymentMethod paymentMethod, double hourlyRate){
		super(name, email, paymentMethod);
		this.hourlyRate = hourlyRate;
		this.weeklyHours = new double[5];
	}
	public void setHourlyRate(double rate){
		this.hourlyRate = (rate > 0.0) ? rate : 0.0;
	}
	public void setHoursOfDay(double hours, int dayOfWeek){
		if(dayOfWeek>=0 && dayOfWeek<5){
			weeklyHours[dayOfWeek] = hours;
		}
	}
	@Override
	public double makeSalaryPayment(Date date){
		boolean isFriday = (date.getDay() == 5);
		double payroll = 0;
		if(isFriday){
			for(double hours: this.weeklyHours){
				if(hours > 8.0){
					payroll += (8.0*hourlyRate + (hours - 8.0)*(1.5 * hourlyRate));
				}else{
					payroll += hours*hourlyRate;
				}
			}
			payroll -= this.arrears;
			this.arrears = 0;
			if(this.getUnionMember()){
				payroll -= this.getMembershipFee();
			}
			if(payroll < 0.0){
				this.arrears = -1.0*payroll;
				payroll = 0.0;
			}
			this.weeklyHours = new double[5];
		}
		return payroll;
	}
}