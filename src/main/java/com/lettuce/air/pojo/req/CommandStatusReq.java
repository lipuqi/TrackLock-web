package com.lettuce.air.pojo.req;

import com.alibaba.fastjson.JSONObject;

public class CommandStatusReq {

	/**
	 * 命令状态
	 */
	private String resultCode;
	
	/**
	 * 命令结果详细信息
	 */
	private JSONObject resultDetail;
	
	@Override
	public String toString() {
		return "CommandStatusReq [resultCode=" + resultCode + "]";
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * @return the resultDetail
	 */
	public JSONObject getResultDetail() {
		return resultDetail;
	}

	/**
	 * @param resultDetail the resultDetail to set
	 */
	public void setResultDetail(JSONObject resultDetail) {
		this.resultDetail = resultDetail;
	}

}
