package objects;

public class Column {

	private String name = "";
	private String type = "";
	private boolean isMeasure = false;
	private boolean isPrimaryKey = false;
	private boolean isForeignKey = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isMeasure() {
		return isMeasure;
	}

	public void setMeasure(boolean isMeasure) {
		this.isMeasure = isMeasure;
	}

	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}

	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	public boolean isForeignKey() {
		return isForeignKey;
	}

	public void setForeignKey(boolean isForeignKey) {
		this.isForeignKey = isForeignKey;
	}

	@Override
	public String toString() {
		return name;
	}

}
