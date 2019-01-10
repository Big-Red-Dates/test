package com.msy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msy.mapper.MovieMapper;
import com.msy.model.Movie;
import com.msy.model.Movies;
import com.msy.model.PageInfo;
import com.msy.modol.copy.MovieCopy;
import com.msy.service.MovieService;
import com.msy.utils.ComForPage;
import com.msy.utils.PagerEntity;
@Service
@Transactional
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieMapper moviemapper;
	private int onepagedata=100;
	@Override
	public void add(Movie movie) throws Exception {
		// TODO Auto-generated method stub
		this.moviemapper.insert(movie);
	}

	@Override
	public Movie getMovieById(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.moviemapper.selectByPrimaryKey(id);
	}

	@Override
	public void delMovieById(String id) throws Exception {
		// TODO Auto-generated method stub
		this.moviemapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateMovieById(Movie movie) throws Exception {
		// TODO Auto-generated method stub
		this.moviemapper.updateByPrimaryKey(movie);
	}

	@Override
	public  List<Movie> getMovieList() throws Exception {
		// TODO Auto-generated method stub
		return this.moviemapper.selectList();
	}

	@Override
	public Movies getallMovie(String currentpage) throws Exception {
		// TODO Auto-generated method stub
		Movies movies = new Movies();
		PageInfo pageinfo = new PageInfo();
		
		if(currentpage==null){
			currentpage="1";
		}
		int count = this.moviemapper.countall();
		ComForPage<Movie> compage = new ComForPage<Movie>(onepagedata,count,Integer.parseInt(currentpage));
		
		PagerEntity pageentity = new PagerEntity(movies,compage.getStartdata(),compage.getShowPage(), currentpage, null, null, null, currentpage, currentpage, null, null, null, currentpage, null, null, currentpage, currentpage, null, count, currentpage, currentpage);
		
		List<Movie> movieinfo=moviemapper.searchallforpage(pageentity);
		
		pageinfo.setCurrPageNO(compage.getCurrpage());
		pageinfo.setTotalPages(compage.getTotalpage());
		pageinfo.setTotalResults(compage.getTotaldata());
		
		movies.setPageInfo(pageinfo);
		movies.setDatas(movieinfo);
		return movies;
	}

	@Override
	public Movies getMovieByCateid(String currentpage, String categoryid) throws Exception {
		// TODO Auto-generated method stub
		Movies movies = new Movies();
		PageInfo pageinfo = new PageInfo();
		Movie movie = new Movie();
		movie.setCategoryId(categoryid);
		if(currentpage==null){
			currentpage="1";
		}
		int count = this.moviemapper.countBycateid(categoryid);
		
		ComForPage<Movie> compage = new ComForPage<Movie>(onepagedata,count,Integer.parseInt(currentpage));
		
		PagerEntity pageentity = new PagerEntity(movie,compage.getStartdata(),compage.getShowPage(), categoryid, null, null, null, categoryid, categoryid, null, null, null, categoryid, null, null, categoryid, categoryid, null, count, categoryid, categoryid);
		
		List<Movie> movieinfo=moviemapper.searchBycateid(pageentity);
		
		pageinfo.setCurrPageNO(compage.getCurrpage());
		pageinfo.setTotalPages(compage.getTotalpage());
		pageinfo.setTotalResults(compage.getTotaldata());
		
		movies.setPageInfo(pageinfo);
		movies.setDatas(movieinfo);
		return movies;
	}

	@Override
	public Movie getMovieDetail(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.moviemapper.selectBymovieId(id);
	}

	@Override
	public List<MovieCopy> getRelatedMovie(MovieCopy moviecopy) throws Exception {
		// TODO Auto-generated method stub
		return this.moviemapper.selectListBymovieId(moviecopy);
	}

}
