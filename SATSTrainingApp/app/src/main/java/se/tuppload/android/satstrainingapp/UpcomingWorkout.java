package se.tuppload.android.satstrainingapp;

public final class UpcomingWorkout {

    public final String mGymLocation;
    public final String mInstructorsName;
    public final String mWorkoutType;
    public final String mDurationInMinutes;
    public final int mWaitinglistCount;
    public final String mStartTimeHour;
    public final String mStartTimeMinutes;

    public UpcomingWorkout(String mGymLocation, String mInstructorsName, String mWorkoutType, String mDurationInMinutes, int mWaitinglistCount, String mStartTimeHour, String mStartTimeMinutes) {
        this.mGymLocation = mGymLocation;
        this.mInstructorsName = mInstructorsName;
        this.mWorkoutType = mWorkoutType;
        this.mDurationInMinutes = mDurationInMinutes;
        this.mWaitinglistCount = mWaitinglistCount;
        this.mStartTimeHour = mStartTimeHour;
        this.mStartTimeMinutes = mStartTimeMinutes;
    }
}
