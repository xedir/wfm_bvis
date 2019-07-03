package com.bvis.spring.api;

public class ContractResponse {

	private int contractId;
	private boolean accepted;
	
	
	public int getContractId() {
		return contractId;
	}
	public void setContractId(int contractId) {
		this.contractId = contractId;
	}
	public boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	
	
	
}
