package com.sistema.easyservice.dto;

import java.util.ArrayList;
import java.util.List;

public class GraficoBarrasDTO {
	
	List<String> labels = new ArrayList<>();

	List<DataSets> datasets = new ArrayList<>();
	
	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public List<DataSets> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<DataSets> datasets) {
		this.datasets = datasets;
	}
		

}
