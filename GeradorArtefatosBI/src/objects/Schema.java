package objects;

import java.util.ArrayList;

public class Schema {

	private ArrayList<Table> tables = new ArrayList<>();
	private ArrayList<Table> dimensions = new ArrayList<>();
	private ArrayList<Table> cubes = new ArrayList<>();
	private ArrayList<Table> aggregations = new ArrayList<>();

	public ArrayList<Table> getTables() {
		return tables;
	}

	public void setTables(ArrayList<Table> tables) {
		this.tables = tables;
	}

	public ArrayList<Table> getDimensions() {
		return dimensions;
	}

	public void setDimensions(ArrayList<Table> dimensions) {
		this.dimensions = dimensions;
	}

	public ArrayList<Table> getCubes() {
		return cubes;
	}

	public void setCubes(ArrayList<Table> cubes) {
		this.cubes = cubes;
	}

	public ArrayList<Table> getAggregations() {
		return aggregations;
	}

	public void setAggregations(ArrayList<Table> aggregations) {
		this.aggregations = aggregations;
	}

	public void addDimension(Table dimension) {
		dimensions.add(dimension);
	}

	public void addCube(Table cube) {
		cubes.add(cube);
	}

	public void addAggregator(Table aggregator) {
		aggregations.add(aggregator);
	}

}
