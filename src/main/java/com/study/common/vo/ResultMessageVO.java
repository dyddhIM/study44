package com.study.common.vo;

public class ResultMessageVO {
	private boolean result;
	private String title;
	private String message;
	private String url;
	private String urlTitle;
	
	public boolean isResult() {
		return result;
	}
	public ResultMessageVO setResult(boolean result) {
		this.result = result;
		return this;
	}
	public String getTitle() {
		return title;
	}
	public ResultMessageVO setTitle(String title) {
		this.title = title;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public ResultMessageVO setMessage(String message) {
		this.message = message;
		return this;
	}
	public String getUrl() {
		return url;
	}
	public ResultMessageVO setUrl(String url) {
		this.url = url;
		return this;
	}
	public String getUrlTitle() {
		return urlTitle;
	}
	public ResultMessageVO setUrlTitle(String urlTitle) {
		this.urlTitle = urlTitle;
		return this;
	}
}
