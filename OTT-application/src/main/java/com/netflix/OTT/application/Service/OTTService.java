package com.netflix.OTT.application.Service;

import com.netflix.OTT.application.Entity.*;
import com.netflix.OTT.application.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OTTService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private WatchListRepository watchListRepository;

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Autowired
    private DevicesRepository devicesRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CastCrewRepository castCrewRepository;

    @Autowired
    private MovieCastCrewRepository movieCastCrewRepository;

    @Autowired
    private NotificationsRepository notificationsRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private TVShowRepository tvshowrepository;

    @Autowired
    private SeasonsRepository seasonsRepository;

    @Autowired
    private EpisodesRepository episodesRepository;

    @Autowired
    private TVshowCastCrewRepository tVshowCastCrewRepository;


    // User operations
    public List<User> findAllUsers() {
        return userRepository.getAllUSers();
    }

    public User findUserById(Integer id) {
        return userRepository.getUserById(id);
    }

    public void saveUser(User user) {
         userRepository.insertUser(user);
    }

    public void updateUser(User user) {
    userRepository.updateUser(user);
    }

    public void updateSubscriptionsforUser(User user) {
        userRepository.updateSubscriptionforUser(user.getUser_id(), user.getSubscriptions().getSubs_id());
    }

    public void updatePhoneforUser(User user) {
        userRepository.updatePhoneForUSers(user.getUser_id(), user.getPhone());
    }

    public void deleteUserById(Integer id) {
        userRepository.deleteUser(id);
    }


    //User profiles

    public List<UserProfile> findAllProfiles() {
        return userProfileRepository.getAllUserProfiles();

    }

    public UserProfile findProfileById(Integer id) {
        return userProfileRepository.getUserProfileById(id);
    }


    public void createUserProfile(UserProfile userProfile) {

        String name = userProfile.getName();
        int age = userProfile.getAge();
        String type = userProfile.getType().name();
        String prefLang = userProfile.getPref_lang();
        int userId = userProfile.getUser().getUser_id();
        String country= userProfile.getCountry();
        userProfileRepository.insertUserProfile(name, age, type, prefLang, userId, country);

    }

    public void updateUserProfile(UserProfile userProfile) {
        userProfileRepository.updateUserProfile(userProfile);

        userProfileRepository.deleteAllFavoriteMovies(userProfile.getProfile_id());
        userProfileRepository.deleteAllFavoriteShows(userProfile.getProfile_id());

        for (Movie movie : userProfile.getFavoriteMovies()) {
            userProfileRepository.insertFavoriteMovie(userProfile.getProfile_id(), movie.getMovieId());
        }

        for (TVShow show : userProfile.getFavoriteShows()) {
            userProfileRepository.insertFavoriteShow(userProfile.getProfile_id(), show.getShow_id());
        }
    }

    public void deleteUserProfile(Integer profileId) {
        userProfileRepository.deleteAllFavoriteMovies(profileId);
        userProfileRepository.deleteAllFavoriteShows(profileId);
        userProfileRepository.deleteUserProfile(profileId);
    }

    //user_fav_shows
    public List<String> getFavoriteShows(int profileId) {
        return userProfileRepository.findFavoriteShowsByProfileId(profileId);
    }

    //user_fav_movies
    public List<String> getFavoritemovies(int profileId) {
        return userProfileRepository.findFavoriteMoviesByProfileId(profileId);
    }

    // Movie operations
    public List<Movie> findAllMovies() {
        return movieRepository.getAllMovies();
    }

    public Movie findMovieById(Integer id) {
        return movieRepository.getMovieById(id);
    }

    public void saveMovie(Movie movie) {
         movieRepository.saveMovies(movie);
    }

    public void updateMovie(Movie movie) {
        movieRepository.updateMovie(movie);
    }

    public void deleteMovieById(Integer id) {
        movieRepository.deleteMovie(id);
    }


    // CastCrew operations
    public List<CastCrew> findAllCastCrew() {
        return castCrewRepository.getAllCastCrew();
    }

    public CastCrew findCastCrewById(Integer id) {
        return castCrewRepository.getCastCrewById(id);
    }

    public void saveCastCrew(CastCrew castCrew) {
         castCrewRepository.saveCastCrew(castCrew);
    }

    public void updatecastcrew(CastCrew castcrew) {
        castCrewRepository.updateCastCrew(castcrew);

    }

    public List<Movie> getUpcomingMovies() {
        return movieRepository.getUpcomingMovies(LocalDate.now());
    }

    public void deleteCastCrewById(Integer id) {
        castCrewRepository.deleteCastCrew(id);
    }


    //MovieCastCrew
    public List<MovieCastCrew> findallmoviecastcrew() {
        return movieCastCrewRepository.getAllMovieCastCrew();
    }

    public MovieCastCrew findmoviecastcrew(int id) {
        return movieCastCrewRepository.getMovieCastCrewById(id);
    }

    public void savecastcrewmovies(MovieCastCrew mcc) {
         movieCastCrewRepository.insertMovieCastCrew(mcc.getProfession(),mcc.getRole(),
                 mcc.getMovie().getMovieId(), mcc.getPerson().getPersonId());
    }

    public void updatemoviecastcrew(MovieCastCrew mcc) {
        movieCastCrewRepository.updateMovieCastCrew(mcc.getProfession(), mcc.getRole(), mcc.getMovie().getMovieId(),
                mcc.getPerson().getPersonId(), mcc.getId());

    }

    public void deletemoviecastcrew(Integer id) {
        movieCastCrewRepository.deleteMovieCastCrew(id);
    }


    //TVshows

    public List<TVShow> findAllTVShows(){
        return tvshowrepository.getAllTvShows();
    }

    public TVShow findShowsById(Integer id) {
        return tvshowrepository.getTvShowsById(id);
    }

    public void saveShow(TVShow show) {
         tvshowrepository.saveTVShow(show.getDescription(), show.getGenre(),
                 show.getShow_name(), show.getYear());
    }

    public List<TVShow> getLatestTvShows(Date createdat){
        return tvshowrepository.getLatestTVShows(createdat);
    }

    public List<Movie> getlatestMovies(Date date) {
        return movieRepository.getLatestMovies(date);
    }

    public void updateShow(TVShow show)
    {
        tvshowrepository.updateShow(show.getDescription(), show.getGenre(),
                show.getShow_name(), show.getYear(), show.getShow_id());
    }

    public void deleteShowById(Integer id) {
        tvshowrepository.deleteShowById(id);
    }


    //Seasons
    public List<Seasons> findAllSeasons(){
        return seasonsRepository.getAllSeasons();
    }

    public Seasons findSeasonsById(Integer id) {
        return seasonsRepository.getSeasonsById(id);
    }

    public void saveSeasons(Seasons seasons) {
         seasonsRepository.saveSeasons(seasons.getDirector(), seasons.getProducer(), seasons.getSeason_number(),
                seasons.getYear(), seasons.getShow().getShow_id());
    }

    public void updateSeasons(Seasons seasons)
    {
        seasonsRepository.updateSeasons(seasons.getDirector(), seasons.getProducer(), seasons.getSeason_number(),
                seasons.getYear(), seasons.getShow().getShow_id(), seasons.getSeason_id());
    }

    public void deleteSeasonById(Integer id) {
        seasonsRepository.deleteSeason(id);
    }

    //Episodes

    public List<Episodes> findAllEpisodes(){
        return episodesRepository.getAllEpisodes();
    }

    public Episodes findEpisodesById(Integer id) {
        return episodesRepository.getEpisodeById(id);
    }

    public void saveEpisodes(Episodes episodes) {
         episodesRepository.saveEpisode(episodes.getDescriptions(), episodes.getEpisode_name(), episodes.getRating(),
                 episodes.getRunningtime(), episodes.getSeasons().getSeason_id());
    }

    public void updateEpisodes(Episodes episodes)
    {
        episodesRepository.updateEpisode(episodes.getDescriptions(), episodes.getEpisode_name(), episodes.getRating(),
                episodes.getRunningtime(), episodes.getSeasons().getSeason_id(), episodes.getEpisode_id());
    }

    public void deleteEpisodesById(Integer id) {
        episodesRepository.deleteEpisode(id);
    }


    //TVShowCastCrew
    public List<TVCastCrew> findAllTVcastcrew(){
        return tVshowCastCrewRepository.getAllTVCastCrews();
    }

    public TVCastCrew findTvCastCrewById(Integer id) {
        return tVshowCastCrewRepository.getTVCastCrewById(id);
    }

    public void saveTvCastcrew(TVCastCrew tcc) {
         tVshowCastCrewRepository.insertTvCastCrew(tcc.getProfession(), tcc.getRole(), tcc.getEpisode().getEpisode_id(),
                tcc.getPerson().getPersonId(), tcc.getSeason().getSeason_id(), tcc.getShow().getShow_id());
    }

    public void updateTvcastcrew(TVCastCrew tcc)
    {
        tVshowCastCrewRepository.updateTVCastCrew(tcc.getProfession(), tcc.getRole(), tcc.getEpisode().getEpisode_id(),
                tcc.getPerson().getPersonId(), tcc.getSeason().getSeason_id(), tcc.getShow().getShow_id(), tcc.getId());
    }

    public void deleteTvCastCrewById(Integer id) {
        tVshowCastCrewRepository.deleteTVCastCrew(id);
    }


    //subscriptions
    public List<Subscriptions> findAllSubsc(){
        return subscriptionRepository.getAllSubscriptions();
    }

    public Subscriptions findSubsById(Integer id) {
        return subscriptionRepository.getSubscriptionsById(id);
    }

    public void saveSubs(Subscriptions tcc) {
        if(tcc.getType()==1)
        {
            subscriptionRepository.insertSubscription("One Month",1,199,LocalDateTime.now(),
                    LocalDateTime.now().plusMonths(1),1);
        }
        else if(tcc.getType()==2)
        {
            subscriptionRepository.insertSubscription("Six Month",2,399,LocalDateTime.now(),
                    LocalDateTime.now().plusMonths(6),2);
        }
        else if(tcc.getType()==3)
        {
            subscriptionRepository.insertSubscription("Yearly",3,799,LocalDateTime.now(),
                    LocalDateTime.now().plusYears(1),3);
        }

    }

    public void updateSubscriptions(Subscriptions tcc)
    {
        if(tcc.getType()==1)
        {
            subscriptionRepository.updateSubscription(tcc.getSubs_id(), "One Month",1,199,LocalDateTime.now(),
                    LocalDateTime.now().plusMonths(1),1);
        }
        else if(tcc.getType()==2)
        {
            subscriptionRepository.updateSubscription(tcc.getSubs_id(), "Six Month",2,399,LocalDateTime.now(),
                    LocalDateTime.now().plusMonths(6),2);
        }
        else if(tcc.getType()==3)
        {
            subscriptionRepository.updateSubscription(tcc.getSubs_id(),"Yearly",3,799,LocalDateTime.now(),
                    LocalDateTime.now().plusYears(1),3);
        }
    }

    public void deleteSubs(Integer id) {
        subscriptionRepository.deleteSubscriptions(id);
    }


    //watchlist

    public List<WatchList> getAllWatchList()
    {
        return watchListRepository.getAllWtchList();
    }

    public WatchList getAllWatchListById(Integer id) {
        return watchListRepository.getWatchListById(id);
    }


    public void addWatchList(WatchList wl) {

        watchListRepository.insertWatchList(wl.getUserProfile().getProfile_id(), wl.getStatus().name(),
                LocalDateTime.now(), wl.getWatched_time_stamp().toString());

        int id= watchListRepository.getWatchIdofLast();

        for(Episodes e: wl.getEpisodes()) {
            watchListRepository.insertwatchepisodes(id, e.getEpisode_id());
        }

        for(Seasons e: wl.getSeasons()) {
            watchListRepository.insertwatchseasons(id, e.getSeason_id());
        }

        for(TVShow e: wl.getTvShows()) {
            watchListRepository.insertwatchshows(id, e.getShow_id());
        }

        for(Movie e: wl.getMovies()) {
            watchListRepository.insertwatchmovies(id, e.getMovieId());
        }

    }

    public void deleteCompletedWatchLists() {

        for(WatchList wl: watchListRepository.getAllWtchList()) {
                watchListRepository.deleteepisodes(wl.getWatchId());
                watchListRepository.deletemovies(wl.getWatchId());
                watchListRepository.deleteseasons(wl.getWatchId());
                watchListRepository.deleteshows(wl.getWatchId());
                watchListRepository.deleteWatchList(wl.getWatchId());
        }
    }

    public void updateWatchList(WatchList wl) {
        watchListRepository.updateWatchList(wl.getWatchId(), wl.getStatus(), LocalDateTime.now(), wl.getWatched_time_stamp().toString());

    }

    public void deletewatchlist(Integer id) {
        watchListRepository.deleteWatchList(id);
    }



    //Notifications

    public List<Notifications> findallnotifiactions()
    {
        return notificationsRepository.getAllNotifications();
    }

    public Notifications findnotifbyid(Integer id)
    {
        return notificationsRepository.getNotificationById(id);
    }

    public void insertNotif(Notifications n)
    {
        notificationsRepository.notifcationinsert(n.getMessage(), n.getNotifprofile().getProfile_id());
    }

    public void updatenotifications(Notifications notifications)
    {
        if(!notifications.getIsWatched()) {
            notificationsRepository.updateNotifications("REMAINDER", LocalDateTime.now(), notifications.getNotif_id());
        }
        if(notifications.getIsWatched())
        {
            notificationsRepository.updateiswatched(true, LocalDateTime.now(), notifications.getNotif_id());
        }

    }
   public void deleteNotifications(Integer notif_id){
        notificationsRepository.deleteNotifications(notif_id);
   }


    //Payments

    public List<Payments> getallpayments()
    {
        return paymentsRepository.getAllPayments();
    }

    public Payments getpaymentsbyid(Integer id)
    {
        return paymentsRepository.getPaymentById(id);
    }

    public void insertpayment(Payments p)
    {
        paymentsRepository.insertPayment(p.getPay_type().name(), p.getAmount(), p.getStatus(), p.getUser_payments().getUser_id(),
                p.getSubscriptions().getSubs_id());
    }

    public void updatepayments(Payments p)
    {
        paymentsRepository.updatePayments(p.getPay_type().name(), p.getStatus(), p.getUser_payments().getUser_id(), p.getPayment_id());
    }

    public void deletepayments(Integer pid)
    {
        paymentsRepository.deletePayments(pid);
    }


    //Devices
    public void addDevice(int userId, Devices newDevice) {
        User user = userRepository.getUserById(newDevice.getDevice().getUser_id());
        Subscriptions subscription = user.getSubscriptions();
        int maxProfiles = subscription.getMax_profile();

        List<Devices> userDevices = user.getDevice();

        if (userDevices.size() >= maxProfiles) {
            userDevices.sort(Comparator.comparingInt(Devices::getDevice_id));
            Devices oldestDevice = userDevices.getFirst();
            devicesRepository.deleteDevice(oldestDevice.getDevice_id());
            userDevices.remove(oldestDevice);
        }
        newDevice.setDevice(user);
        devicesRepository.insertDevices(newDevice.getDevice_type().name(), true, newDevice.getDevice().getUser_id());
    }


    public List<Devices> getUserDevices(User user)
    {
        User u= userRepository.getUserById(user.getUser_id());
        return u.getDevice();
    }


    public List<Map<String, Object>> getTopMovies(String language,String country, String timeRange, int limit) {
        LocalDateTime[] dateRange = getDateRange(timeRange);
        if(Objects.equals(country, "global")){
            return watchListRepository.findTopMovies(language, dateRange[0], null, dateRange[1], limit);
        }
        else {
            return watchListRepository.findTopMovies(language, dateRange[0], country, dateRange[1], limit);
        }
    }

    public List<Map<String, Object>> getTopTVShows(String language, String country, String timeRange, int limit) {
        LocalDateTime[] dateRange = getDateRange(timeRange);
        if(Objects.equals(country, "global")) {
            return watchListRepository.findTopTVShows(language, dateRange[0], null, dateRange[1], limit);
        }
        else{
            return watchListRepository.findTopTVShows(language, dateRange[0], country, dateRange[1], limit);
        }
    }

    private LocalDateTime[] getDateRange(String timeRange) {
        LocalDateTime startDate;
        LocalDateTime endDate;

        switch (timeRange.toLowerCase()) {
            case "today":
                startDate = LocalDate.now().atStartOfDay();
                endDate = LocalDateTime.now();
                break;

            case "this month":
                startDate = LocalDate.now().withDayOfMonth(1).atStartOfDay();
                endDate = LocalDateTime.now();
                break;

            case "this year":
                startDate = LocalDate.now().withDayOfYear(1).atStartOfDay();
                endDate = LocalDateTime.now();
                break;

            default:
                throw new IllegalArgumentException("Invalid time range: " + timeRange);
        }

        return new LocalDateTime[]{startDate, endDate};
    }


}
