package com.msy.controller.api.out;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msy.constant.Constants;
import com.msy.model.Category;
import com.msy.model.Datas;
import com.msy.model.DatasForMoviedetail;
import com.msy.model.Movie;
import com.msy.model.Movies;
import com.msy.model.ResultForMovie;
import com.msy.modol.copy.MovieCopy;
import com.msy.service.CateGoryService;
import com.msy.service.MovieService;



@RestController
@RequestMapping(value = "/api/out" )
public class MovieApiOutController {
	
	private static final Logger logger = Logger.getLogger(MovieApiOutController.class);
	
	@Autowired
	private MovieService movieservice;
	
	@Autowired
	private CateGoryService cateGoryService;
	
	@RequestMapping(value = "/movie/getAllMovie")
    public ResultForMovie sendData(@RequestParam(value="currpage") String currpage ){
		ResultForMovie resultmovie = new ResultForMovie();
		Datas datas = new Datas(); 
		try {
			Movies p = this.movieservice.getallMovie(currpage);
			List<Category> categorylist =this.cateGoryService.getCategoryList();
			resultmovie.setApiDesp("获取所有分类");
			
			datas.setCategory(categorylist);
			datas.setMovies(p);
			resultmovie.setDatas(datas);
			
			return resultmovie;
		} catch (Exception e) {
			e.printStackTrace();
			resultmovie.setStatus(Constants.RESULTMO_FAIL);
			resultmovie.setResultMsg("获取所有分类电影信息异常");
			return resultmovie;
		}
    }
	
	@RequestMapping(value = "/movie/getMovieByCate")
    public ResultForMovie sendData(@RequestParam(value="currpage") String currpage,@RequestParam(value="cateid") String cateid){
		ResultForMovie resultmovie = new ResultForMovie();
		Datas datas = new Datas(); 
		try {
			Movies p = this.movieservice.getMovieByCateid(currpage,cateid);
			List<Category> categorylist =this.cateGoryService.getCategoryListBycateid(cateid);
			resultmovie.setApiDesp("获取分类电影");
			
			datas.setCategory(categorylist);
			datas.setMovies(p);
			resultmovie.setDatas(datas);
			
			return resultmovie;
		} catch (Exception e) {
			e.printStackTrace();
			resultmovie.setStatus(Constants.RESULTMO_FAIL);
			resultmovie.setResultMsg("获取分类电影信息异常");
			return resultmovie;
		}
    }
	
	@RequestMapping(value = "/movie/getMovieDetail")
    public ResultForMovie sendMovieDate(@RequestParam(value="movieId") String movieId ){
		ResultForMovie resultmovie = new ResultForMovie();
		DatasForMoviedetail datas = new DatasForMoviedetail(); 
		try {
			Movie m = this.movieservice.getMovieDetail(movieId);
			MovieCopy m1 =new MovieCopy();
			m1.setCategoryId(m.getCategoryId());
			m1.setMovieId(movieId);
			List<MovieCopy> movielist =this.movieservice.getRelatedMovie(m1);
			
			List<MovieCopy> movielistA = new ArrayList<>();
			int n=0;
			if(movielist.size()<10) {
				 n=movielist.size();
			}else if(movielist.size()>=10){
			     n =10;
			}
			for(int i=0;i<n;i++) {
				MovieCopy moviecopy=movielist.get(i);
				movielistA.add(moviecopy);
			}
			resultmovie.setApiDesp("获取电影详细");
			
			datas.setMoviedetail(m);
			datas.setRelateMovies(movielistA);
			resultmovie.setDatas(datas);
			
			return resultmovie;
		} catch (Exception e) {
			e.printStackTrace();
			resultmovie.setStatus(Constants.RESULTMO_FAIL);
			resultmovie.setResultMsg("获取电影详细异常");
			return resultmovie;
		}
    }
}
