package customer.contract.textSources;

import java.util.ArrayList;
import java.util.List;

public class Coverage {
	String name;
	Double charge;
	Double benefit;
	String details;
	
	public Coverage(String name, Double charge, Double benefit, String details) {
		this.name = name;
		this.charge = charge;
		this.benefit = benefit;
		this.details = details;
	}
	
	 @Override
    public String toString() {
        return String.format("Coverage{name='%s', charge=%.0f원, benefit=%.0f원, details='%s'}",
                name, charge, benefit, details);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getCharge() {
		return charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

	public Double getBenefit() {
		return benefit;
	}

	public void setBenefit(Double benefit) {
		this.benefit = benefit;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
