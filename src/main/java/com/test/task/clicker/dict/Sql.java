package com.test.task.clicker.dict;

/**
 * @author aleshin.dmitrii
 * @since 27.12.2020
 */
public enum Sql {
	GET_CLICK_NUMBER("SELECT CLICK_NUMBER FROM CLICKS;"),
	GET_COUNT_CLICK_NUMBER("SELECT COUNT(*) FROM CLICKS;"),
	UPDATE_CLICK_NUMBER("UPDATE CLICKS SET CLICK_NUMBER = $;"),
	INIT_CLICK_NUMBER("INSERT INTO CLICKS VALUES(0);"),
	CREATE_TABLE("CREATE TABLE IF NOT EXISTS CLICKS (\n" +
	             "CLICK_NUMBER INT\n" +
	             ");"),
	;

	private final String sqlQuery;

	Sql(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	public String sqlQuery() {
		return sqlQuery;
	}

	public String updateSqlQuery(int clickNumber) {
		return sqlQuery.replace("$", String.valueOf(clickNumber));
	}
}
