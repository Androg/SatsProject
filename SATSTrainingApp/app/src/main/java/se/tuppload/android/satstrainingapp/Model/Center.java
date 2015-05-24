package se.tuppload.android.satstrainingapp.model;

public class Center {

    public boolean availableForOnlineBooking, isElixia;
    public String description;
    public String name;
    public String url;
    public String filterId;
    public String centerId;
    public String latitude;
    public String longitude;
    public String regionId;

    public Center(boolean availableForOnlineBooking, boolean isElixia, String description, String name,
                  String url, String filterId, String centerId, String latitude, String longitude, String regionId) {

        this.availableForOnlineBooking = availableForOnlineBooking;
        this.isElixia = isElixia;
        this.description = description;
        this.name = name;
        this.url = url;
        this.filterId = filterId;
        this.centerId = centerId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.regionId = regionId;
    }
}
