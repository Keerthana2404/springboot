package com.netflix.OTT.application.Controller;

import com.netflix.OTT.application.Entity.*;
import com.netflix.OTT.application.Service.OTTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OTTController {

    @Autowired
    private OTTService applicationService;

    // User operations
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(applicationService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Integer id) {
       return applicationService.findUserById(id);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        applicationService.saveUser(user);
    }

    @PutMapping("/users")
    public void updateUser(@RequestBody User user) {
        applicationService.updateUser(user);

    }

    @PutMapping("/usersupdatesubscription")
    public void updateUserSubscription(@RequestBody User user) {
        applicationService.updateSubscriptionsforUser(user);

    }

    @PutMapping("/userphoneupdate")
    public void updateuserphone(@RequestBody User user) {
        applicationService.updatePhoneforUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void  deleteUser(@PathVariable Integer id) {
        applicationService.deleteUserById(id);
    }


    // Profile operations
    @GetMapping("/profiles")
    public List<UserProfile> findAllProfiles() {
         return applicationService.findAllProfiles();

    }

    @GetMapping("/profiles/{id}")
    public UserProfile findProfileById(@PathVariable Integer id) {
         return applicationService.findProfileById(id);

    }

    @PostMapping("/profiles")
    public void createUserProfile(@RequestBody UserProfile profile) {
            applicationService.createUserProfile(profile);

    }

    @PutMapping("/profiles")
    public void updateUserProfile( @RequestBody UserProfile profile) {
            profile.setProfile_id(profile.getProfile_id());
            applicationService.updateUserProfile(profile);

        }


    @DeleteMapping("/profiles/{id}")
    public void deleteProfileById(@PathVariable Integer id) {
        applicationService.deleteUserProfile(id);
    }

    //user_fav_shows
    @GetMapping("/{id}/userfavshow")
    public List<String> getallfavshow(@PathVariable Integer id) {
        return applicationService.getFavoriteShows(id);
    }

    //user_fav_movies
    @GetMapping("/{id}/userfavmovie")
    public List<String> getallfavmovie(@PathVariable Integer id) {
        return applicationService.getFavoritemovies(id);
    }


    // Movie operations
    @GetMapping("/movies")
    public List<Movie> findAllMovies() {
        return applicationService.findAllMovies();
    }

    @GetMapping("/movies/{id}")
    public Movie findMovieById(@PathVariable Integer id) {
        return applicationService.findMovieById(id);
    }

    @GetMapping("/latestmovies")
    public List<Movie> getLatestMovies (@RequestParam("date")
                                          @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {

        return applicationService.getlatestMovies(date);
    }

    @GetMapping("/upcomingmovies")
    public List<Movie> getUpcoomingMovies(){
        return applicationService.getUpcomingMovies();
    }

    @PostMapping("/movies")
    public void saveMovie(@RequestBody Movie movie) {
         applicationService.saveMovie(movie);
    }

    @PutMapping("/movies")
    public void updateMovie(@RequestBody Movie movie)
    {
        applicationService.updateMovie(movie);
    }

    @DeleteMapping("/movies/{id}")
    public void deleteMovieById(@PathVariable Integer id) {
        applicationService.deleteMovieById(id);
    }


    // CastCrew operations
    @GetMapping("/castcrew")
    public List<CastCrew> findAllCastCrew() {
        return applicationService.findAllCastCrew();
    }

    @GetMapping("/castcrew/{id}")
    public CastCrew findCastCrewById(@PathVariable Integer id) {
        return applicationService.findCastCrewById(id);
    }

    @PostMapping("/castcrew")
    public void saveCastCrew(@RequestBody CastCrew castCrew) {
         applicationService.saveCastCrew(castCrew);
    }


    @PutMapping("/castcrew")
    public void updatecastandcrew(@RequestBody CastCrew castcrew) {
        applicationService.updatecastcrew(castcrew);
    }

    @DeleteMapping("/castcrew/{id}")
    public void deleteCastCrewById(@PathVariable Integer id) {
        applicationService.deleteCastCrewById(id);
    }


    //MovieCastCrew

    @GetMapping("/moviecastcrew")
    public List<MovieCastCrew> getMoviecastcrew() {
        return applicationService.findallmoviecastcrew();
    }

    @GetMapping("/moviecastcrew/{id}")
    public MovieCastCrew getmoviecastcrew(@PathVariable int id) {
        return applicationService.findmoviecastcrew(id);
    }

    @PostMapping("/moviecastcrew")
    public void saveCastcrew(@RequestBody MovieCastCrew mcc) {
         applicationService.savecastcrewmovies(mcc);
    }

    @PutMapping("/moviecastcrew")
    public void updatemoviecastcrew(@RequestBody MovieCastCrew movie) {
        applicationService.updatemoviecastcrew(movie);
    }

    @DeleteMapping("/moviecastcrew/{id}")
    public void deletemoviecastcrew(@PathVariable Integer id) {
        applicationService.deletemoviecastcrew(id);
    }

    //tvshow
    @GetMapping("/tvshows")
    public List<TVShow> findAllshows() {
        return applicationService.findAllTVShows();
    }

    @GetMapping("/tvshows/{id}")
    public TVShow findShowById(@PathVariable Integer id) {
        return applicationService.findShowsById(id);
    }

    @GetMapping("/latesttvshows")
    public List<TVShow> getLatestTvShows (@RequestParam("date")
                                              @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {

        return applicationService.getLatestTvShows(date);
    }

    @PostMapping("/tvshows")
    public void saveShow(@RequestBody TVShow show) {
         applicationService.saveShow(show);
    }

    @PutMapping("/tvshows")
    public void updateShow(@RequestBody TVShow show)
    {
        applicationService.updateShow(show);
    }

    @DeleteMapping("/tvshows/{id}")
    public void deleteShowById(@PathVariable Integer id) {
        applicationService.deleteShowById(id);
    }


    //seasons
    @GetMapping("/seasons")
    public List<Seasons> findAllSeasons() {
        return applicationService.findAllSeasons();
    }

    @GetMapping("/seasons/{id}")
    public Seasons findSeasonsById(@PathVariable Integer id) {
        return applicationService.findSeasonsById(id);
    }

    @PostMapping("/seasons")
    public void saveSeason(@RequestBody Seasons show) {
         applicationService.saveSeasons(show);
    }

    @PutMapping("/seasons")
    public void updateSeason(@RequestBody Seasons show)
    {
        applicationService.updateSeasons(show);
    }

    @DeleteMapping("/seasons/{id}")
    public void deleteSeasonById(@PathVariable Integer id) {
        applicationService.deleteSeasonById(id);
    }


    //episodes
    @GetMapping("/episodes")
    public List<Episodes> findAllEpisodes() {
        return applicationService.findAllEpisodes();
    }

    @GetMapping("/episodes/{id}")
    public Episodes findEpisodeById(@PathVariable Integer id) {
         return applicationService.findEpisodesById(id);
    }

    @PostMapping("/episodes")
    public void saveEpisode(@RequestBody Episodes show) {
         applicationService.saveEpisodes(show);
    }

    @PutMapping("/episodes")
    public void updateEpisodes(@RequestBody Episodes show)
    {
        applicationService.updateEpisodes(show);
    }

    @DeleteMapping("/episodes/{id}")
    public void deleteEpisodesById(@PathVariable Integer id) {
        applicationService.deleteEpisodesById(id);
    }


    //tvcastcrew

    @GetMapping("/tvcastcrew")
    public List<TVCastCrew> findAlltvcastcrew() {
        return applicationService.findAllTVcastcrew();
    }

    @GetMapping("/tvcastcrew/{id}")
    public TVCastCrew findtvcastcrewById(@PathVariable Integer id) {
        return applicationService.findTvCastCrewById(id);
    }

    @PostMapping("/tvcastcrew")
    public void saveShow(@RequestBody TVCastCrew show) {
         applicationService.saveTvCastcrew(show);
    }

    @PutMapping("/tvcastcrew")
    public void updateShow(@RequestBody TVCastCrew show) {
        applicationService.updateTvcastcrew(show);
    }

    @DeleteMapping("/tvcastcrew/{id}")
    public void deletetvcastcrewyId(@PathVariable Integer id) {
        applicationService.deleteTvCastCrewById(id);
    }

    //subscriptions

    @GetMapping("/subscriptions")
    public List<Subscriptions> findAllsubs() {
        return applicationService.findAllSubsc();
    }

    @GetMapping("/subscriptions/{id}")
    public Subscriptions findsubsbyid(@PathVariable Integer id) {
        return applicationService.findSubsById(id);
    }

    @PostMapping("/subscriptions")
    public void saveSubs(@RequestBody Subscriptions show) {
         applicationService.saveSubs(show);
    }


    @PutMapping("/subscriptions")
    public void updateSubscription(@RequestBody Subscriptions show) {
        applicationService.updateSubscriptions(show);
    }

    @DeleteMapping("/subscriptions/{id}")
    public void deletesubsbyId(@PathVariable Integer id) {
        applicationService.deleteSubs(id);
    }

    @GetMapping("/watchlist")
    public List<WatchList> getAllwatchlist() {
        return applicationService.getAllWatchList();
    }

    @GetMapping("/watchlist/{id}")
    public WatchList getAllwatchlistbyid(@PathVariable Integer id) {
        return applicationService.getAllWatchListById(id);
    }

    @PostMapping("/watchlist")
    public void addwatchlist(@RequestBody WatchList wl) {
        applicationService.addWatchList(wl);
    }

    @PutMapping("/watchlist")
    public void updatewatchlist(@RequestBody WatchList wl) {
        applicationService.updateWatchList(wl);
    }


    @DeleteMapping("/watchlistdelete")
    public void deletecomleted(@PathVariable Integer id) {
        applicationService.deletewatchlist(id);
    }


    //Notifications
    @GetMapping("/notifications")
    public List<Notifications> getAllNotifictaion() {
        return applicationService.findallnotifiactions();
    }

    @GetMapping("/notifications/{id}")
    public Notifications getNotifById(@PathVariable Integer id) {
        return applicationService.findnotifbyid(id);
    }

    @PostMapping("/notifications")
    public void insertnotif(@RequestBody Notifications n) {
        applicationService.insertNotif(n);
    }

    @PutMapping("/notifications")
    public void updatenotifications(@RequestBody Notifications n) {
        applicationService.updatenotifications(n);
    }

    @DeleteMapping("/notification/[{id}")
    public void deletenotifications(@PathVariable Integer id) {
        applicationService.deleteNotifications(id);
    }

    //Payments
    @GetMapping("/payments")
    public List<Payments> getallpayements() {
        return applicationService.getallpayments();
    }

    @GetMapping("/payments/{id}")
    public Payments getpaymentbyid(@PathVariable Integer id) {
        return applicationService.getpaymentsbyid(id);
    }

    @PostMapping("/payments")
    public void insertpayments(@RequestBody Payments p) {
        applicationService.insertpayment(p);
    }

    @PutMapping("/payments")
    public void updatepayments(@RequestBody Payments p) {
        applicationService.updatepayments(p);
    }

    @DeleteMapping("/payments/{id}")
    public void deletepayments(@PathVariable Integer id) {
        applicationService.deletepayments(id);
    }


    //devices

    @PostMapping("/devices/{userId}")
    public void addDevice(@PathVariable int userId, @RequestBody Devices device) {
        applicationService.addDevice(userId, device);
    }

    @GetMapping("/userdevices")
    public  List<Devices> getUserdevices(@RequestBody User u) {
        return  applicationService.getUserDevices(u);
    }


    @GetMapping("/top-movies")
    public ResponseEntity<List<Map<String, Object>>> getTopMovies(
            @RequestParam String language,
            @RequestParam String country,
            @RequestParam String timeRange,
            @RequestParam int limit) {

        List<Map<String, Object>> topMovies = applicationService.getTopMovies(language, country, timeRange, limit);
        return ResponseEntity.ok(topMovies);
    }

    @GetMapping("/top-tvshows")
    public ResponseEntity<List<Map<String, Object>>> getTopTVShows(
            @RequestParam String language,
            @RequestParam String country,
            @RequestParam String timeRange,
            @RequestParam int limit) {

        List<Map<String, Object>> topTVShows = applicationService.getTopTVShows(language, country, timeRange, limit);
        return ResponseEntity.ok(topTVShows);
    }

}

