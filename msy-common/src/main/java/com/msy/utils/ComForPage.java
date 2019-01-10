package com.msy.utils;

import java.util.List;


public class ComForPage<T>{
	private int showpage;
	private int totaldata;
	private int currpage;
	
	private int totalpage;
	private int startdata;
	
	private List<T> alllist;
	public ComForPage(int showpage,int totaldata,int currpage){
		this.showpage=showpage;
		this.totaldata=totaldata;
		this.currpage=currpage;
		if(this.totaldata % this.showpage==0)
		{
			this.totalpage=this.totaldata/this.showpage;
		}else{
			this.totalpage=this.totaldata/this.showpage+1;
		}
		if(this.currpage>this.totalpage)
		{
			this.currpage=this.totalpage;
		}
		if(totaldata==0){
			this.currpage=1;
		}if(currpage==0){
			this.currpage=1;
		}
			this.startdata=(this.currpage-1)*this.showpage;
	}

	
	public List<T> getAlllist() {
		return alllist;
	}


	public void setAlllist(List<T> alllist) {
		this.alllist = alllist;
	}


	public int getShowPage() {
		return showpage;
	}

	public int getTotaldata() {
		return totaldata;
	}

	public int getCurrpage() {
		return currpage;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public int getStartdata() {
		return startdata;
	}
	

}
