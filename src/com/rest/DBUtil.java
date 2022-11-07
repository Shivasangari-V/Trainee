//$Id$
package com.rest;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONObject;

public class DBUtil {
	static DBConnectors obj = new DBConnectors();
	public static void insertDataIntoEntry(String table,String clientID) throws Exception {
		Statement stmnt = obj.getConnection().createStatement();
		stmnt.executeUpdate("insert into entry values('"+clientID+"','"+table+"')");
	}
	public static void insertData(String table, ArrayList<String> keys, ArrayList<?> values) throws Exception {
		StringBuilder query = null;
		Statement stmnt = obj.getConnection().createStatement();
		query = new StringBuilder();
		query.append("insert into " + table + "(");
		int len = keys.size();
		for (int i = 0; i < len; i++) {
			query.append(keys.get(i));
			if (i != len - 1)
				query.append(",");
			else
				query.append(")values(");
		}
		for (int i = 0; i < len; i++) {
			query.append("'" + values.get(i) + "'");
			if (i != len - 1)
				query.append(",");
			else
				query.append(")");
		}
		stmnt.executeUpdate(query.toString());
	}

	public static Boolean isPresentData(String table, ArrayList<String> constraint_keys, ArrayList<?> constraint_values) throws Exception {
		StringBuilder query = null;
		Statement stmnt = obj.getConnection().createStatement();
		query = new StringBuilder();
		query.append("select * from " + table + " where ");
		int len = constraint_keys.size();
		for (int i = 0; i < len; i++) {
			query.append(constraint_keys.get(i) + "=");
			query.append("'" + constraint_values.get(i) + "'");
			if (i != len - 1) {
				query.append(" and ");
			}
		} 
		ResultSet rs = stmnt.executeQuery(query.toString());
		Boolean res = false;
		while (rs.next()) {
			res = true;
			break;
		}
		return res;
	}

	public static void updateData(String table, ArrayList<String> constraint_keys, ArrayList<?> constraint_values, ArrayList<?> keys, ArrayList<?> values) throws Exception {
		StringBuilder query = null;
		Statement stmnt = obj.getConnection().createStatement();
		query = new StringBuilder();
		query.append("update " + table + " set ");
		int len = keys.size();
		for (int i = 0; i < len; i++) {
			query.append(keys.get(i) + "=" + "'" + values.get(i) + "'");
			if (i != len - 1)
				query.append(',');
		}
		query.append(" where ");
		for (int i = 0; i < len; i++) {
			query.append(constraint_keys.get(i) + "=");
			query.append("'" + constraint_values.get(i) + "'");
			if (i != len - 1) {
				query.append(" and ");
			}
		}
		stmnt.executeUpdate(query.toString());
		

	}

	public static void deleteData(String table, String id) throws Exception {
		Statement stmnt = obj.getConnection().createStatement();
		stmnt.executeUpdate("Delete from " + table + " where id='" + id + "'");
	}

	public static JSONObject getData(String table, String key, Object value, ArrayList<String> fields) throws Exception {
		Statement stmnt = obj.getConnection().createStatement();
		StringBuilder query = null;
		JSONObject response = new JSONObject();
		query = new StringBuilder();
		query.append("select * from " + table + " where " + key + "='" + value.toString() + "'");
		ResultSet rs = stmnt.executeQuery(query.toString());
		while (rs.next()) {
			for (int i = 0; i < fields.size(); i++) {
				response.put(fields.get(i), rs.getString(fields.get(i)));
			}
		}
		return response;
	}public static void tokenMapper(String token,String clientID) throws Exception{
		Statement stmnt= obj.getConnection().createStatement();
		stmnt.executeUpdate("insert into ClientTokenMapper values('"+token+"','"+clientID+"')");
	}
}
