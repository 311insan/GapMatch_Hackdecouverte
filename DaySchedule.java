//imports
import java.util.arrayList;

//code

/**
 * 
 * @author 311insan
 * @version 0.1
 * A schedule 
 */
public class DaySchedule {
    private int dsHours;
    private int dsStartHour;
    private int dsEndHour;

    /**
     * Creates a DaySchedule, with a given start time and end time
     * @param startHour
     * @param endHour
     */
    public DaySchedule(int startHour, int endHour) {
        dsStartHour = startHour;
        dsEndHour = endHour;
        dsHours = endHour - startHour;
    }

    /**
     * Sets the start hour of the day schedule
     * @param hour
     */
    public void setScheduleStartHour(int hour){
        dsStartHour = hour;
    }

    /**
     * Sets the end hour of the day schedule
     * @param hour
     */
    public void setScheduleEndHour(int hour){
        dsEndHour = hour;
    }

    /**
     * 
     * @return the start hour of the day schedule
     */
    public int getStartHour(){
        return dsStartHour;
    }

    /**
     * 
     * @return the end hour of the day schedule
     */
    public int getEndHour(){
        return dsEndHour;
    }

    /**
     * 
     * @return the length of the schedule
     */
    public int getSchoolDayLength(){
        return dsHours;
    }

}