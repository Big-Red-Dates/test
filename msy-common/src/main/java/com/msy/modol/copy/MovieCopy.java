package com.msy.modol.copy;

import com.msy.model.Category;

public class MovieCopy {
	/**
     *影片ID
     */
    private String movieId;
    
    /**
     *读取ID
     */
    private int readId;

    /**
     *电影内容类型
     */
    private String categoryId;
    
    /**
     *片名
     */
    private String name;
      
	/**
     *上映时间
     */
    private String releaseDate;

    /**
     *豆瓣评分
     */
    private Float doubanRate;
    
    
    /**
     *电影简介
     */
    private String movieIntrodu;
    
    /**
     *关联分类
     */
    private Category category;
    
    public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public int getReadId() {
		return readId;
	}

	public void setReadId(int readId) {
		this.readId = readId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Float getDoubanRate() {
		return doubanRate;
	}

	public void setDoubanRate(Float doubanRate) {
		this.doubanRate = doubanRate;
	}

	public String getMovieIntrodu() {
		return movieIntrodu;
	}

	public void setMovieIntrodu(String movieIntrodu) {
		this.movieIntrodu = movieIntrodu;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
    
}
