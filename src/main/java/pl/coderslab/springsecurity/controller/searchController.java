package pl.coderslab.springsecurity.controller;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.springsecurity.model.Movie;
import pl.coderslab.springsecurity.model.UserName;
import pl.coderslab.springsecurity.repository.MovieRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static pl.coderslab.springsecurity.model.JsonReader.readJsonFromUrl;


@Controller
public class searchController {
    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/search")
    public String useSearch(){
    return "searchForm";
    }
    @PostMapping("/search")
    public String postSearch(){
        return "redirect:result";
    }
    @GetMapping("/result")
    public String seeResults(){
    return "";
    }

    @GetMapping("/movieSearch")
    public String useMovieSearch(){
        return "searchMovie";
    }
    @PostMapping("/movieSearch")
    public String postMovieSearch(HttpServletRequest request , Model model){
        String title = request.getParameter("filmName");
        System.out.println(title);
        model.addAttribute("filmName", title);
        String search = "https://api.themoviedb.org/3/search/movie?api_key=e5a1519d5698fa84e5d68ffd08f3a883&language=pl&query="+title;
        try {
            ArrayList<Movie> movieList = new ArrayList<Movie>();
            JSONObject json = readJsonFromUrl(search);
            System.out.println(json.toString());
            JSONArray jsonResult = json.getJSONArray("results");
            for (Object item : jsonResult) {
                JSONObject jsonItem = (JSONObject)item;
                System.out.println(jsonItem.toString());
                System.out.println(jsonItem.getString("title"));
                Movie currentMovie = new Movie(jsonItem);
                movieList.add(currentMovie);
            }
            model.addAttribute("movies", movieList);
            model.addAttribute("movieToAdd", new Movie());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "resultMovie";
    }
    @PostMapping("/addToWatch")
    public String addToWatchToDB(@Valid Movie movie, BindingResult result, Principal principal){
        movie.setToWatch(true);
        movie.setWatched(false);
        movie.setOverview("Sprawdz na stronie!");
        movie.setUsername(principal.getName());
        System.out.println(movie);
        movieRepository.save(movie);
        return "redirect:movieSearch";
    }

    @PostMapping("/addWatched")
    public String setWatchedToDB(@Valid Movie movie, BindingResult result, Principal principal){
        movie.setToWatch(false);
        movie.setWatched(true);
        movie.setOverview("Sprawdz na stronie!");
        movie.setUsername(principal.getName());
        System.out.println(movie);
        movieRepository.save(movie);
        return "redirect:movieSearch";
    }
    @GetMapping("/showWatched")
    public String showWatched(Model model, Principal principal){
        List<Movie> allMovies = movieRepository.findAll();
        List<Movie> movieList = null ;
        for (Movie movie: allMovies) {
            if (movie.getUsername() == principal.getName()){
                if (movie.getWatched())
                movieList.add(movie);
            }
        model.addAttribute("movies", movieList);
            model.addAttribute("movieToAdd", new Movie());
        }
        return "resultMovie";
    }
    @GetMapping("/showToWatch")
    public String showToWatch(Model model, Principal principal){
        List<Movie> allMovies = movieRepository.findAll();
        List<Movie> movieList = new ArrayList<Movie>() ;
        String currentUserName = principal.getName();
        for (Movie movie: allMovies) {
            if (currentUserName.equals(movie.getUsername())){
                if (movie.getToWatch())
                movieList.add(movie);
            }
        }
        model.addAttribute("movies", movieList);
        model.addAttribute("movieToAdd", new Movie());
        return "resultMovie";
    }

}
