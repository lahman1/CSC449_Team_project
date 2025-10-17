class StatisticsTracker {
    private long totalTimeUsed = 0;

    public void addUsedTime(long seconds) {
        this.totalTimeUsed += seconds;
    }

    public long getTotalTimeUsed() {
        return totalTimeUsed;
    }
}