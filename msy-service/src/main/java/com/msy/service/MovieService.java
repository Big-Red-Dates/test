package com.msy.service;

import java.util.List;

import com.msy.model.Movie;
import com.msy.model.Movies;
import com.msy.model.Order;
import com.msy.model.Result;
import com.msy.modol.copy.MovieCopy;
import com.msy.utils.Pager;


/**
 * 电影服务
 * @author add by jung.chen
 *
 */
public interface MovieService {
	
	/**
	 * 添加影片接口
	 * @param movie
	 */
	void add(Movie movie) throws Exception ;
	
	/**
	 * 获取影片信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Movie getMovieById(String id) throws Exception ;
	
	/**
	 * 获取影片信息列表
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<Movie> getMovieList() throws Exception ;
	
	/**
	 * 根据id删除影片
	 * @param id
	 * @throws Exception
	 */
	void delMovieById(String id)throws Exception;
	
	/**
	 * 根据id修改影片
	 * @param id
	 * @throws Exception
	 */
	void updateMovieById(Movie movie)throws Exception;
	
	/**
	 * 根据当前页查询影片
	 * @param id
	 * @throws Exception
	 */
	Movies getallMovie(String currentpage)  throws Exception;
	
	/**
	 * 根据当前页查询影片
	 * @param id
	 * @throws Exception
	 */
	Movies getMovieByCateid(String currentpage,String categoryid)  throws Exception;
	
	/**
	 * 根据电影Id查询影片详细
	 * @param id
	 * @throws Exception
	 */
	Movie getMovieDetail(String id)  throws Exception;
	
	/**
	 * 根据分类Id获取相关影片
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<MovieCopy> getRelatedMovie(MovieCopy moviecopy) throws Exception ;
	
}
