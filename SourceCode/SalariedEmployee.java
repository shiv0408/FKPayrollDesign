package SourceCode;
import java.util.Date;
import java.time.YearMonth;
import SourceCode.Employee;


class SalariedEmployee extends Employee{
	private double monthlySalary;
	private double arrears=0;
	public SalariedEmployee(String name, String email, paymentMethod paymentMethod, double commisionRate, double salary){
		super(name, email, paymentMethod, commisionRate);
		this.monthlySalary = salary;
	}
	public SalariedEmployee(String name, String email, paymentMethod paymentMethod, double salary){
		super(name, email, paymentMethod);
		this.monthlySalary = salary;
	}
	public void setMonthlySalary(double salary){
		this.monthlySalary = salary;
	}
	@Override
	public double makeSalaryPayment(Date date){
		boolean isFriday, isLastDay;
		isFriday = date.getDay()==5;
		isLastDay = date.getDate()==YearMonth.of(date.getYear(), date.getMonth()+1).lengthOfMonth();
		if(isFriday && isLastDay){
			double grossSalary = this.monthlySalary + this.getGrossSales()*this.getCommisionRate();
			grossSalary -= this.arrears;
			this.setGrossSales(0.0);
			this.arrears = 0.0;
			return grossSalary;
		}else if(isFriday){
			double weeklyPayment = this.getGrossSales()*this.getCommisionRate() - this.arrears;
			this.arrears = 0;
			if(this.getUnionMember()){
				weeklyPayment -= this.getMembershipFee();
			}
			if(weeklyPayment<0){
				arrears = -weeklyPayment;
				weeklyPayment = 0;
			}
			this.setGrossSales(0.0);
			return weeklyPayment;
		}else if(isLastDay){
			double deductedSalary = this.monthlySalary - this.arrears;
			this.arrears = 0;
			return deductedSalary;
		}else{
			return 0.0;
		}
	}
}