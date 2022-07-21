package service;

import enums.TripStatus;
import model.Rider;
import model.Trip;

public interface TripService {

    String bookCab(Rider rider, int xSrclocation, int ySrcLocation, int xDestlocation, int yDestlocation);

    TripStatus endTrip(String tripId);

}
