package SourceCode;
import java.util.Date;


public abstract class Employee{
	private static int lastID = 0;
	private int ID;
	private String name;
	private String email;
	private paymentMethod method;
	private double commisionRate = 0;
	private boolean unionMember = false;
	private double membershipFee = 0;
	private double grossSales = 0;
	public Employee(String name, String email, paymentMethod method, double commisionRate){
		lastID++;
		this.ID = lastID;
		this.name = name;
		this.email = email;
		this.method = method;
		this.commisionRate = commisionRate;
	}
	public Employee(String name, String email, paymentMethod method){
		this(name, email, method, 0.0);
	}
	public double getGrossSales(){
		return this.grossSales;
	}
	public void setGrossSales(double value){
		this.grossSales = (value > 0.0) ? value : 0.0;
	}
	public double getCommisionRate(){
		return this.commisionRate;
	}
	public double getMembershipFee(){
		return this.membershipFee;
	}
	public boolean getUnionMember(){
		return this.unionMember;
	}
	public void setCommisionRate(double rate){
		this.commisionRate = rate;
	}
	public void setPaymentMethod(paymentMethod method){
		this.method = method;
	}
	public void editMembershipStatus(boolean status, double fee){
		if(status==false){
			this.unionMember = status;
			this.membershipFee = 0;
		} else{
			this.unionMember = status;
			this.membershipFee = fee;
		}
	}
	public void editMembershipStatus(boolean status){
		this.editMembershipStatus(status, 0);
	}
	public void editMembershipFee(double fee){
		if(unionMember==true){
			this.membershipFee = fee;
		}
	}
	public void addSales(double saleAmount){
		if(saleAmount > 0){
			grossSales +=saleAmount;
		}
	}
	public abstract double makeSalaryPayment(Date date);
}