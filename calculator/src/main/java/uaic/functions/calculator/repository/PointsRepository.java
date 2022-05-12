package uaic.functions.calculator.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import uaic.functions.calculator.dto.PointDTO;

@Repository
public class PointsRepository implements CustomRepository {

	private List<PointDTO> functionPoints;

	@PostConstruct
	void initializeDummyPoints() {
		System.out.println("Initialize PointsRepository class...");
		functionPoints = DoubleStream.iterate(0.0, point -> point + 0.2).mapToObj(x -> {
			PointDTO point = new PointDTO(x, 2 * x * x);
			return point;
		}).limit(100).collect(Collectors.toList());

		for (PointDTO pointDTO : functionPoints) {
			System.out.println(pointDTO);
		}
		System.out.println("Done");
	}

	@Override
	public List<PointDTO> getFunctionPlotPoints() {
		return functionPoints;
	}

}
