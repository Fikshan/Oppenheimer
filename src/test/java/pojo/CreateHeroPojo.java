package pojo;

public class CreateHeroPojo {

	private String natid;
	private String name;
	private String gender;
	private String birthDate;
	private String deathDate = null;
	private float salary;
	private float taxPaid;
	private float browniePoints;

	// Getter Methods
	public String getNatid() {
		return natid;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public String getDeathDate() {
		return deathDate;
	}

	public float getSalary() {
		return salary;
	}

	public float getTaxPaid() {
		return taxPaid;
	}

	public float getBrowniePoints() {
		return browniePoints;
	}

	// Setter Methods
	public void setNatid(String natid) {
		this.natid = natid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public void setTaxPaid(float taxPaid) {
		this.taxPaid = taxPaid;
	}

	public void setBrowniePoints(float browniePoints) {
		this.browniePoints = browniePoints;
	}

}
