package com.msy.model;

public class PageInfo {
 
	/**
	 * 获取当前页
	 * */
	private int currPageNO;
	
	
	/**
	 * 总的数据
	 * */
	private int totalResults;
	
	/**
	 * 总的页数
	 * */
	private int totalPages;

	public int getCurrPageNO() {
		return currPageNO;
	}

	public void setCurrPageNO(int currPageNO) {
		this.currPageNO = currPageNO;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	
	
}
