//imports

//code

/**
 * 
 * Course
 * @author 311insan
 * @version 0.1
 * Objects of this class represent one lecture of a course.
 */
public class Course {
    //fields
    String cCode;
    String cName;
    int cStart;
    int cEnd;
    int cLength;

    //methods

    /**
     * Sets the course name.
     * @param courseName
     */
    public void setCourseName(String courseName){
        cName = courseName;
    }

    /**
     * Sets the course code.
     * @param courseCode
     */
    public void setCourseCode(String courseCode){
        cCode = courseCode;
    }

    /**
     * Sets the lecture length.
     * @param courseStart
     * @param courseEnd
     */
    public void setCourseLength(int courseStart, int courseEnd){
        cStart = courseStart;
        cEnd = courseEnd;
        cLength = cEnd - cStart;
    }

    /**
     * 
     * @return the name of the course associated with this lecture.
     */
    public String getCourseName(){
        return cName;
    }

    /**
     * 
     * @return the course code of the course associated with this lecture.
     */
    public String getCouseCode(){
        return cCode;
    }

    /**
     * 
     * @return the start time of the lecture
     */
    public int getCourseStart(){
        return cStart;
    }

    /**
     * 
     * @return the end time of the lecture
     */
    public int getCourseEnd(){
        return cEnd;
    }

    /**
     * 
     * @return the length of the lecture
     */
    public int getCourseLength(){
        return cLength;
    }
    
}