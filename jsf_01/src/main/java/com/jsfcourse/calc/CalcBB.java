package com.jsfcourse.calc;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@SessionScoped
public class CalcBB implements Serializable{
	private double amount;
	private double interestRate;
	private int period;
	private Double result;
	private Double fullResult;

	@Inject
	FacesContext ctx;
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public Double getResult() {
		return result;
	}
	
	public Double getFullResult() {
		return fullResult;
	}

	public void setFullResult(Double fullResult) {
		this.fullResult = fullResult;
	}

	public boolean calculate() {
		try {
			result =  amount * (interestRate/100)*(period/12);
			setFullResult(result + amount);
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Operacja wykonana poprawnie.", null));
			return true;
		}catch (Exception e){
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Błąd podczas wykonywania operacji.", null));
			return false;
		}
	}

	// Go to "showresult" if ok
	public String calc() {
		if (calculate()) {
			return "showresult";
		}
		return null;
	}

	public String calculateAjax() {
		if(calculate()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zysk: " + result, null));
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pełna kwota: " + fullResult, null));
			}
			return null;
	}
	public String info() {
		return "info";
	}
}
