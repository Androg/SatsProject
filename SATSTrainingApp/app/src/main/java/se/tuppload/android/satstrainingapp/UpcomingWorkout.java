package se.tuppload.android.satstrainingapp;

public final class UpcomingWorkout {

    public final String mGymLocation;
    public final String mInstructorsName;
    public final String mSatsCore;

    public UpcomingWorkout(String gymLocation, String instructorsName, String satsCore) {
        this.mGymLocation = gymLocation;
        this.mInstructorsName = instructorsName;
        this.mSatsCore = satsCore;
    }
}
