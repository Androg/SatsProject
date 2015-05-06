package se.tuppload.android.satstrainingapp;

public final class UpcomingWorkout {

    public final String mGymLocation;
    public final String mInstructorsName;
    public final String mWorkoutType;
//    public final int mBookedPersonCount;
    public final String mDurationInMinutes;
    public final int mMaxPersonCount;
    public final String mStartTime;

    public UpcomingWorkout(String mGymLocation, String mInstructorsName, String mWorkoutType, String mDurationInMinutes, int mMaxPersonCount, String mStartTime) {
        this.mGymLocation = mGymLocation;
        this.mInstructorsName = mInstructorsName;
        this.mWorkoutType = mWorkoutType;
        this.mDurationInMinutes = mDurationInMinutes;
        this.mMaxPersonCount = mMaxPersonCount;
        this.mStartTime = mStartTime;
    }

    //    public UpcomingWorkout( int BookedPersonCount, int gymLocation, String durationInMinutes, String instructorsName, int maxPersonCount, String workoutType, String time) {
//        this.mGymLocation = gymLocation;
//        this.mInstructorsName = instructorsName;
//        this.mWorkoutType = workoutType;
//        this.mTime = time;
//        this.mBookedPersonCount = BookedPersonCount;
//        this.mDurationInMinutes = durationInMinutes;
//        this.mMaxPersonCount = maxPersonCount;
//    }
}
