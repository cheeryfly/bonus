package com.bonus.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class ActionUtil {
	public static String getResponse(String errorCode, String errorMsg) {
		String json = "{" + "\"returnFlag\":\"" + errorCode + "\","
				+ "\"returnMsg\":\"" + errorMsg + "\"";
		json += "}";
		return json;
	}

	public static void sendJSONToClient(String data, HttpServletResponse response) {
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {

			if (data.indexOf("'") != -1) {
				data = data.replaceAll("'", "\\'");
			}
			if (data.indexOf("\"") != -1) {
				data = data.replaceAll("\"", "\\\"");
			}

			if (data.indexOf("\r\n") != -1) {
				data = data.replaceAll("\r\n", "");
			}
			if (data.indexOf("\r") != -1) {
				data = data.replaceAll("\r", "");
			}
			if (data.indexOf("\n") != -1) {
				data = data.replaceAll("\n", "");
			}

			response.getWriter().write(data);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getResponse(String errorCode, String errorMsg, String data) {
		String json = "{" + "\"returnFlag\":\"" + errorCode + "\","
				+ "\"returnMsg\":\"" + errorMsg + "\"";
		if (data != null) {
			json += ",";
			json += data;
		}

		json += "}";
		return json;
	}
}
