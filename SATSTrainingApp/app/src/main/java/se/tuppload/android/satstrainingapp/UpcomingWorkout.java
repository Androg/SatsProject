package se.tuppload.android.satstrainingapp;

public final class UpcomingWorkout {

    public final String mCenterName;
    public final String mInstructorsName;
    public final String mWorkoutType;
    public final String mDurationInMinutes;
    public final int mWaitingListCount;
    public final String mStartTime;

    public UpcomingWorkout(String mCenterName, String mInstructorsName, String mWorkoutType,
                           String mDurationInMinutes, int mWaitingListCount, String mStartTime) {
        this.mCenterName = mCenterName;
        this.mInstructorsName = mInstructorsName;
        this.mWorkoutType = mWorkoutType;
        this.mDurationInMinutes = mDurationInMinutes;
        this.mWaitingListCount = mWaitingListCount;
        this.mStartTime = mStartTime;
    }
}
