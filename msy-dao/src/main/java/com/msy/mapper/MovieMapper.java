package com.msy.mapper;




import java.util.List;

import com.msy.model.Movie;

import com.msy.model.Order;
import com.msy.modol.copy.MovieCopy;
import com.msy.utils.ComForPage;
import com.msy.utils.PagerEntity;


public interface MovieMapper {
 
	/**
     *删除影片
     *@param movieId
	 *@return
     */
	int deleteByPrimaryKey(String movieId);

    /**
   	 * 
     * 新增影片
     * @param movie
     * @return
     */
    int insert(Movie movie);
    
    /** 
     * 查询影片
     * @param movieId
     * @return
     */
    Movie selectByPrimaryKey(String movieId);
    
    /** 
     * 根据电影id查询影片详细
     * @param movieId
     * @return
     */
    Movie selectBymovieId(String movieId);
    
    /** 
     * 根据分类id查询相关影片
     * @param movieId
     * @return
     */
    List<MovieCopy> selectListBymovieId(MovieCopy movieCopy);
    
    /** 
     * 查询影片列表
     * @param movieId
     * @return
     */
    List<Movie> selectList();
    
    /**
     * 查询总理记录数
     * @param 
     * @return
     */
	Integer countall();
	
	/**
     * 根据分类id查询总理记录数
     * @param categoryid
     * @return
     */
	Integer countBycateid(String categoryid);
	
	/**
	 * 查询电影数量
	 * @param pagerEntity
	 * @return
	 */
	List<Movie> searchallforpage(PagerEntity pagerEntity);
	
	/**
	 * 根据分类id查询电影数量
	 * @param pagerEntity
	 * @return
	 */
	List<Movie> searchBycateid(PagerEntity pagerEntity);
    
	/**
     * 修改影片
     * @param movie
     * @return
     */
    int updateByPrimaryKey(Movie movie);
}