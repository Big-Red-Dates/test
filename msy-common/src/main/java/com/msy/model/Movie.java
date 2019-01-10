package com.msy.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author jung.chen add by 2018.03.20
 *
 */
public class Movie implements Serializable{
    /**
	 * 影片实体类
	 */
	private static final long serialVersionUID = -3697063159583602684L;

	/**
     *影片ID
     */
    private String movieId;
    
    /**
     *读取ID
     */
    private int readId;

    /**
     *片名
     */
    private String name;

    /**
     *导演
     */
    private String director;

    /**
     *演员表
     */
    private String starring;

    /**
     *电影内容类型
     */
    private String categoryId;
    
    /**
     *国家
     */
    private String country;

    /**
     *语言
     */
    private String language;

    /**
     *上映时间
     */
    private String releaseDate;

    /**
     *片长
     */
    private int filmLength;

    /**
     *豆瓣评分
     */
    private Float doubanRate;
    
    /**
     *播放文件名
     */
    private String file;

    /**
     *主图名称
     */
    private String img;
    
    /**
     *电影简介
     */
    private String movieIntrodu;
    
    /**
     *关联分类
     */
    private Category category;
    
    public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

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

    
    public String getDirector() {
        return director;
    }

   
    public void setDirector(String director) {
        this.director = director;
    }

  
    public String getStarring() {
        return starring;
    }

  
    public void setStarring(String starring) {
        this.starring = starring;
    }
  
    public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCountry() {
        return country;
    }

   
    public void setCountry(String country) {
        this.country = country;
    }

  
    public String getLanguage() {
        return language;
    }

    
    public void setLanguage(String language) {
        this.language = language;
    }

  
    public String getReleaseDate() {
        return releaseDate;
    }

   
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getFilmLength() {
        return filmLength;
    }


    public void setFilmLength(int filmLength) {
        this.filmLength = filmLength;
    }

   
    public Float getDoubanRate() {
        return doubanRate;
    }

   
    public void setDoubanRate(Float doubanRate) {
        this.doubanRate = doubanRate;
    }

    
    public String getFile() {
        return file;
    }

   
    public void setFile(String file) {
        this.file = file;
    }

 
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

	public String getMovieIntrodu() {
		return movieIntrodu;
	}

	public void setMovieIntrodu(String movieIntrodu) {
		this.movieIntrodu = movieIntrodu;
	} 
    
    
}