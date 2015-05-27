package se.tuppload.android.satstrainingapp.Model;

public class Center {

    public boolean availableForOnlineBooking, isElixia;
    public String description;
    public String name;
    public String url;
    public String filterId;
    public String centerId;
    public Double latitude;
    public Double longitude;
    public String regionId;

    public Center(boolean availableForOnlineBooking, boolean isElixia, String description, String name,
                  String url, String filterId, String centerId, Double latitude, Double longitude, String regionId) {

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
