package com.sinnet.weixin.msg.pojo;


public class PrescriptionForm {
	private String diagnosis;
	private String takemedicine;
	private String price;
	private String patientname;
	
	private String[] prescriptiontypes;
	private String[] medicines;
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getTakemedicine() {
		return takemedicine;
	}
	public void setTakemedicine(String takemedicine) {
		this.takemedicine = takemedicine;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPatientname() {
		return patientname;
	}
	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}
	public String[] getPrescriptiontypes() {
		return prescriptiontypes;
	}
	public void setPrescriptiontypes(String[] prescriptiontypes) {
		this.prescriptiontypes = prescriptiontypes;
	}
	public String[] getMedicines() {
		return medicines;
	}
	public void setMedicines(String[] medicines) {
		this.medicines = medicines;
	}
	
	
}
