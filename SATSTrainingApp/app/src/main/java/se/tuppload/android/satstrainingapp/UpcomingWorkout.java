package se.tuppload.android.satstrainingapp;

public final class UpcomingWorkout {

    public final String mGymLocation;
    public final String mInstructorsName;
    public final String mWorkoutType;
//    public final int mBookedPersonCount;
    public final String mDurationInMinutes;
    public final int mWaitingListCount;
    public final String mStartTimeHour;
    public final String mStartTimeMinutes;

    public UpcomingWorkout(String mGymLocation, String mInstructorsName, String mWorkoutType, String mDurationInMinutes,
                           int mWaitingListCount, String mStartTimeHour, String mStartTimeMinutes) {
        this.mGymLocation = mGymLocation;
        this.mInstructorsName = mInstructorsName;
        this.mWorkoutType = mWorkoutType;
        this.mDurationInMinutes = mDurationInMinutes;
        this.mWaitingListCount = mWaitingListCount;
        this.mStartTimeHour = mStartTimeHour;
        this.mStartTimeMinutes = mStartTimeMinutes;
    }
}
