package com.lettuce.air.pojo.req;

public class CommandStatusReq {

	/**
	 * 命令状态
	 */
	private String resultCode;
	
	/**
	 * 命令结果详细信息
	 */
	private String resultDetail;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultDetail() {
		return resultDetail;
	}

	public void setResultDetail(String resultDetail) {
		this.resultDetail = resultDetail;
	}
	
}
