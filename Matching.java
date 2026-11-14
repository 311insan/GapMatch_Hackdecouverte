import java.util.ArrayList;

public class Matching {

  public static boolean isMatch(Student s, DaySchedule myDay, Student other, DaySchedule otherDay){

    if (other.user.equals(s.user)){

        return false;

    }

    if (!other.user.equalsIgnoreCase(s.program)){

        return false;

    }

    int overlapStart = Math.max(myDay.getStartHour(), otherDay.getStartHour());
    int overlapEnd = Math.min(myDay.getEndHour(), otherDay.getEndHour());

    return overlapStart < overlapEnd;
  }

  public static ArrayList<Students> findMatches (Student s, DaySchedule myday, ArrayList<Students>  allStudents, ArrayList<DaySchedule> allSchedules){

    ArrayList<Students> matches = new ArrayList<>();

    for (int i = 0; i < allStudents.size(); i++){

      Student other = allStudents.get(i);
      DaySchedule otherDay = allSchedules.get(i);

      if (isMatch(s, myday, other, otherDay)){
        matches.add(other);
      }

    }
    return matches;
  }

}

