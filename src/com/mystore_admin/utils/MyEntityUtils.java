package com.mystore_admin.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
public class MyEntityUtils {
	private String tablename = "";
	private String[] colnames;
	private String[] colTypes;
	private int[] colSizes; // ??????§³
	private int[] colScale; // ????§³??????
	private String packagename;
	private boolean importUtil = false;
	private boolean importSql = false;
	private boolean importMath = false;
	public void tableToEntity(String tName, String packagename,
			Connection connection) {
		this.packagename = packagename;
		tablename = tName;
		Connection conn = connection;
		String strSql = "SELECT * FROM " + tablename;

		try {
			PreparedStatement pstm= conn.prepareStatement(strSql);
			pstm.executeQuery();
			ResultSetMetaData rsmd = pstm.getMetaData();
			int size = rsmd.getColumnCount(); // ???§Ø?????
			colnames = new String[size];
			colTypes = new String[size];
			colSizes = new int[size];
			colScale = new int[size];
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				rsmd.getCatalogName(i + 1);
				colnames[i] = rsmd.getColumnName(i + 1).toLowerCase();
				colTypes[i] = rsmd.getColumnTypeName(i + 1).toLowerCase();
				colScale[i] = rsmd.getScale(i + 1);
				if ("datetime".equalsIgnoreCase(colTypes[i])
						|| "date".equalsIgnoreCase(colTypes[i])) {
					importUtil = true;
				}
				if ("image".equalsIgnoreCase(colTypes[i])
						|| "text".equalsIgnoreCase(colTypes[i])
						|| "Timestamp".equalsIgnoreCase(colTypes[i])) {
					importSql = true;
				}

				if (colScale[i] > 0) {
					importMath = true;
				}
				colSizes[i] = rsmd.getPrecision(i + 1);
			}
			String content = parse(colnames, colTypes, colSizes);
			try {
				FileWriter fw = new FileWriter("src/" + packagename + "/"
						+ initcap(tablename) + ".java");
				PrintWriter pw = new PrintWriter(fw);
				pw.println(content);
				pw.flush();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	private String parse(String[] colNames, String[] colTypes, int[] colSizes) {
		StringBuffer sb = new StringBuffer();
		String pag = "";
		String arr[] = packagename.split("");
		for (int i = 0; i < arr.length; i++) {
			// System.out.print(arr[i]);
			if ("/".equals(arr[i])) {
				arr[i] = ".";
			}
			pag += arr[i];
		}

		sb.append("package  " + pag + ";\r\n");
		sb.append("\r\nimport java.io.Serializable;\r\n");
		if (importUtil) {
			sb.append("import java.util.Date;\r\n");
		}
		if (importSql) {
			sb.append("import java.sql.*;\r\n\r\n");
		}
		if (importMath) {
			sb.append("import java.math.*;\r\n\r\n");
		}

		sb.append("public class " + initcap(tablename)
				+ " implements Serializable {\r\n");
		processAllAttrs(sb);
		processAllMethod(sb);
		sb.append("}\r\n");
		System.out.println("??????!");
		// System.out.println(sb.toString());
		return sb.toString();

	}


	private void processAllMethod(StringBuffer sb) {
		for (int i = 0; i < colnames.length; i++) {
			sb.append("\tpublic void set"
					+ initcap(colnames[i])
					+ "("
					+ oracleSqlType2JavaType(colTypes[i], colScale[i],
							colSizes[i]) + " " + colnames[i] + "){\r\n");
			sb.append("\t\tthis." + colnames[i] + "=" + colnames[i] + ";\r\n");
			sb.append("\t}\r\n");

			sb.append("\tpublic "
					+ oracleSqlType2JavaType(colTypes[i], colScale[i],
							colSizes[i]) + " get" + initcap(colnames[i])
					+ "(){\r\n");
			sb.append("\t\treturn " + colnames[i] + ";\r\n");
			sb.append("\t}\r\n");
		}
	}


	private void processAllAttrs(StringBuffer sb) {
		sb.append("\tprivate static final long serialVersionUID = 1L;\r\n");
		for (int i = 0; i < colnames.length; i++) {
			sb.append("\tprivate "
					+ oracleSqlType2JavaType(colTypes[i], colScale[i],
							colSizes[i]) + " " + colnames[i] + ";\r\n");
		}
		sb.append("\r\n");
	}


	private String initcap(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}


	private String oracleSqlType2JavaType(String sqlType, int scale, int size) {
		if (sqlType.equalsIgnoreCase("int")) {
			return "Integer";
		} else if (sqlType.equalsIgnoreCase("Integer")) {
			return "Integer";
		} else if (sqlType.equalsIgnoreCase("long")) {
			return "Long";
		} else if (sqlType.equalsIgnoreCase("decimal")
				|| sqlType.equalsIgnoreCase("float precision")
				|| sqlType.equalsIgnoreCase("double")
				|| sqlType.equalsIgnoreCase("double precision")) {
			return "BigDecimal";
		} else if (sqlType.equalsIgnoreCase("number")
				|| sqlType.equalsIgnoreCase("decimal")
				|| sqlType.equalsIgnoreCase("numeric")
				|| sqlType.equalsIgnoreCase("real")) {
			return scale == 0 ? (size < 7 ? "Integer" : "Long") : "BigDecimal";
		} else if (sqlType.equalsIgnoreCase("varchar")
				|| sqlType.equalsIgnoreCase("char")
				|| sqlType.equalsIgnoreCase("nvarchar")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("datetime")
				|| sqlType.equalsIgnoreCase("date")) {
			return "Date";
		} else if (sqlType.equalsIgnoreCase("Timestamp")) {
			return "Timestamp";
		}
		return null;
	}
}