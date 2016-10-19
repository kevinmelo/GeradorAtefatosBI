package objects;

public class ForeignKey {

	private String tableName = "";
	private String name = "";

	public ForeignKey(String foreingTable, String foreingKey) {
		this.tableName = foreingTable;
		this.name = foreingKey;
	}

	public String getTable() {
		return tableName;
	}

	public void setTable(String foreignTable) {
		this.tableName = foreignTable;
	}

	public String getName() {
		return name;
	}

	public void setName(String foreignKey) {
		this.name = foreignKey;
	}

	@Override
	public boolean equals(Object obj) {
		ForeignKey fk = (ForeignKey) obj;
		
		if (fk.getName().equals(this.name) && fk.getTable().equals(this.tableName))
			return true;

		return false;
	}
}
