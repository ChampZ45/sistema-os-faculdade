package com.sistema.easyservice.dto;

import java.util.ArrayList;
import java.util.List;

import com.sistema.easyservice.dto.DataSetsPie;

public class GraficoPieDTO {

	List<String> labels = new ArrayList<>();

	List<DataSetsPie> datasets = new ArrayList<>();

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public List<DataSetsPie> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<DataSetsPie> datasets) {
		this.datasets = datasets;
	}
	
}
