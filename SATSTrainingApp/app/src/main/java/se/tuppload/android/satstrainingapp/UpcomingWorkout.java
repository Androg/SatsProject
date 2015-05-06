package se.tuppload.android.satstrainingapp;

public final class UpcomingWorkout {

    public final String mCenterName;
    public final String mInstructorsName;
    public final String mWorkoutType;
    public final String mDurationInMinutes;
    public final int mWaitingListCount;
    public final String mStartTimeHour;
    public final String mStartTimeMinutes;

    public UpcomingWorkout(String mCenterName, String mInstructorsName, String mWorkoutType, String mDurationInMinutes,
                           int mWaitingListCount, String mStartTimeHour, String mStartTimeMinutes) {
        this.mCenterName = mCenterName;
        this.mInstructorsName = mInstructorsName;
        this.mWorkoutType = mWorkoutType;
        this.mDurationInMinutes = mDurationInMinutes;
        this.mWaitingListCount = mWaitingListCount;
        this.mStartTimeHour = mStartTimeHour;
        this.mStartTimeMinutes = mStartTimeMinutes;
    }
}
