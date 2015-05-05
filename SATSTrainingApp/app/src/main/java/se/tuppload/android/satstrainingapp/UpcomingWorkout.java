package se.tuppload.android.satstrainingapp;

public final class UpcomingWorkout {

    public final int mGymLocation;
    public final String mInstructorsName;
    public final String mWorkoutType;
    public final String mTime;
    public final int mBookedPersonCount;
    public final String mDurationInMinutes;
    public final int mMaxPersonCount;

    public UpcomingWorkout( int BookedPersonCount, int gymLocation, String durationInMinutes, String instructorsName, int maxPersonCount, String workoutType, String time) {
        this.mGymLocation = gymLocation;
        this.mInstructorsName = instructorsName;
        this.mWorkoutType = workoutType;
        this.mTime = time;
        this.mBookedPersonCount = BookedPersonCount;
        this.mDurationInMinutes = durationInMinutes;
        this.mMaxPersonCount = maxPersonCount;
    }
}
