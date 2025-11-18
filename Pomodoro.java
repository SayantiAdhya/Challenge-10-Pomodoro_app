import java.util.*;

//--------------------------------next class Session---------------------------
class Session {
    private int duration;
    private String note;
    private boolean isCompleted;

    public Session(int duration, String note) {
        this.duration = duration;
        this.note = note;
        this.isCompleted = false;
    }

    public int getDuration() {
        return duration;
    }

    public String getNote() {
        return note;
    }

    public boolean isDone() {
        return isCompleted;
    }

    public void done() {
        isCompleted = true;
    }

    public void display() {
        System.out.println("---------------------------------------");
        System.out.println(" Pomodoro Duration (minutes): " + duration);
        System.out.println(" Note: " + note);
        System.out.println(" Completed: " + (isCompleted ? "Yes" : "No"));
        System.out.println("---------------------------------------");
    }
}

//--------------------------------next class Pomo-------------------------------
class Pomo {
    private ArrayList<Session> sessions = new ArrayList<>();

    public void addSession(Session s) {
        sessions.add(s);
        System.out.println("Session added successfully!");
    }

    public void endSession() {
        if (sessions.isEmpty()) {
            System.out.println("No session to end!");
            return;
        }

        Session last = sessions.get(sessions.size() - 1);

        if (last.isDone()) {
            System.out.println("The last session is already marked as completed!");
        } else {
            last.done();
            System.out.println("Session marked as completed!");
        }
    }

    public void viewAll() {
        if (sessions.isEmpty()) {
            System.out.println("No sessions are present.");
            return;
        }

        System.out.println("\nAll Present Sessions:");
        for (Session s : sessions) {
            s.display();
        }
    }

    public void totalFocusedTime() {
        int total = 0;

        for (Session s : sessions) {
            if (s.isDone()) {
                total += s.getDuration();
            }
        }

        System.out.println("Total Focused Time (Completed Sessions): " + total + " minutes");
    }

    public void pendingSessions() {
        boolean found = false;
        System.out.println("Pending Sessions:");

        for (Session s : sessions) {
            if (!s.isDone()) {
                s.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No pending sessions!");
        }
    }
}

//-------------------------------- main class Pomodoro -------------------------
public class Pomodoro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pomo pomo = new Pomo();

        System.out.println("------------------------------------------------");
        System.out.println("------ Welcome to Pomodoro Focus Time App -------");
        System.out.println("------------------------------------------------");

        while (true) {

            System.out.println("---------------------------------------------");
            System.out.println("1. Start Session");
            System.out.println("2. End Current Session");
            System.out.println("3. View All Sessions");
            System.out.println("4. Total Focused Time");
            System.out.println("5. Pending Sessions");
            System.out.println("6. Exit");
            System.out.println("---------------------------------------------");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter the Pomodoro session note: ");
                    String notes = sc.nextLine();

                    System.out.print("Enter the Pomodoro session duration (minutes): ");
                    int time = sc.nextInt();
                    sc.nextLine();

                    pomo.addSession(new Session(time, notes));
                    break;

                case 2:
                    pomo.endSession();
                    break;

                case 3:
                    pomo.viewAll();
                    break;

                case 4:
                    pomo.totalFocusedTime();
                    break;

                case 5:
                    pomo.pendingSessions();
                    break;

                case 6:
                    System.out.println("Thank you for using our Pomodoro app!");
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
