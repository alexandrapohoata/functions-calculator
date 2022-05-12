package uaic.functions.calculator.repository;

import java.util.List;

import uaic.functions.calculator.dto.PointDTO;

public interface CustomRepository {
	List<PointDTO> getFunctionPlotPoints(); 
}
