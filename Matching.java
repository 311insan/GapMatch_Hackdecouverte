import java.util.ArrayList;

public class Matching {

  public static boolean isMatch()

  public static ArrayList<Students> findMatches (Student s, ArrayList<Students>  allStudents){

    ArrayList<Students> matches = new ArrayList<>();

    for (Student other : allStudents){

      if (other.user.equals(s.user)){
        continue;
      }
      if (other.program.equalsIgnoreCase(s.program)){
        matches.add(other);
      }
    }
    return matches;
  }

}

