import java.util.*;
import java.util.Timer;
import javax.swing.*;

class Schedule extends Timer{
    static char response;

    Scanner sc = new Scanner(System.in);

    public int calMarks() {
        OnlineExam.marks = 0;

        for (int i = 0; i < OnlineExam.correctAnswers.size(); i++) {

            if(null== OnlineExam.answers.get(i))
            {
                OnlineExam.marks=OnlineExam.marks+0;

            }

            if (OnlineExam.answers.get(i).equals(OnlineExam.correctAnswers.get(i))) {
                OnlineExam.marks++;
            }
        }
        return OnlineExam.marks;
    }

    public void scheduleTask() {
        TimerTask task = new TimerTask() {

            @Override
            public void run() {

                for (String j : OnlineExam.questions) {
                    System.out.println(j);
                    System.out.print("Enter correct option: ");
                    String ans = sc.next();
                    System.out.println();
                    OnlineExam.answers.add(ans);
                }
                System.out.print("Submit: Y ( Yes ) or N ( No ):  ");
                response=sc.next().charAt(0);
                System.out.println();
                if(response=='Y'|| response=='y'){
                     cancel();
                     int finalMarks=calMarks();
                     System.out.println("Total Marks: "+finalMarks+"/5");
                     return;
                }

            }
        };

        schedule(task,5100);
   }
}

class PreExam extends TimerTask{

    int i=5;
    

    public void run() {
        System.out.println("The exam starts in " + i + " seconds...");
        i--;
        if (i == 0) {
            cancel();
                System.out.println("--------------------------------");
                System.out.println("|  QUESTIONS:                   |");
                System.out.println("--------------------------------");
                System.out.println();

                try {
                   Thread.sleep(1000);
                }  catch (InterruptedException e) {
                   e.printStackTrace();
                }
            
                Timing objTiming = new Timing();
                Thread timeThread = new Thread(objTiming);
                timeThread.start();

        }
    }

}

public class OnlineExam {

    static String user;
    static String pwd;
    int i = 5;
    static ArrayList<String> questions = new ArrayList<String>();
    static ArrayList<String> answers = new ArrayList<String>();
    static ArrayList<String> correctAnswers = new ArrayList<String>();
    static int marks;
    static Scanner sc = new Scanner(System.in);

    public static void login(){

       System.out.println();
       System.out.println("Enter login credentials: ");
       System.out.println("-------------------------");
       System.out.println();
       System.out.println("Enter user name: ");
       user=sc.next();
       System.out.println("Enter password ");
       pwd=sc.next();
       
       menu();
    }

    public static void update(){

    }
    public void calMarks() {
        OnlineExam.marks = 0;

        for (int i = 0; i < OnlineExam.correctAnswers.size(); i++) {

            if (OnlineExam.answers.get(i).equals(OnlineExam.correctAnswers.get(i))) {
                marks++;
            }
        }
        System.out.println(marks);
    }

    public static void menu() {
        System.out.println();
        System.out.println("------------");
        System.out.println("EXAM PORTAL");
        System.out.println("------------");

        System.out.println("1. Start Examination");
        System.out.println("2. Logout");
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter choice: ");
        int option = sc.nextInt();
        System.out.println();

        switch (option) {
            case 1:

                Timer timer = new Timer(); // Create a Timer object
                timer.schedule(new PreExam(), 200, 1000); 

                Schedule objSchedule = new Schedule();
                objSchedule.scheduleTask();
                break;
                
            case 2:
                return;
            default:
                System.out.println("Incorrect choice!");
        }
    }
    

    public static void main(String[] args) {


        // Exam Questions and Answers in Array Lists

        questions.add("1. Which of the following access modifiers provides the highest level of accessibility in Java?\n"
                + "a) private\n" + "b) protected\n" + "c) public\n" + "d) default (no modifier) \n");

        questions.add("2. Who invented Java Programming?\n" + "a) Guido van Rossum\n" + "b) James Gosling\n"
                + "c) Dennis Ritchie\n" + "d) Bjarne Stroustrup\n");

        questions.add("3. Which statement is true about Java?\n"
                + "a) Java is a sequence-dependent programming language\n"
                + "b) Java is a code dependent programming language\n"
                + "c) Java is a platform-dependent programming language\n"
                + "d) Java is a platform-independent programming language\n");

        questions.add("4. Which one of the following is not a Java feature?\n" + "a) Object-oriented\n"
                + "b) Use of pointers\n" + "c) Portable\n" + "d) Dynamic and Extensible\n");

        questions.add("5. What is the extension of java code files?\n" + "a) .js\n" + "b) .txt\n" + "c) .class\n"
                + "d) .java\n");

        correctAnswers.add("c");
        correctAnswers.add("b");
        correctAnswers.add("d");
        correctAnswers.add("b");
        correctAnswers.add("d");

        PreExam objPreExam= new PreExam();


        //Initial Login 
            System.out.println();
            System.out.println("Welcome!");
            System.out.println("1. Login");
            System.out.println("2. Update Profile & Password");
            System.out.println("3. Exit");
            System.out.println();
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter choice: ");
            int option = scan.nextInt();

            switch (option) {
                case 1:
                    login();
                    break;
                case 2:
                    update();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Incorrect choice!");
            }
        
    }
    
}

// Countdown Timer

class Timing implements Runnable {

    public volatile boolean stopFlag = false;
    int finalMarks;
    int time = 20;
    JFrame frame;
    JLabel timerLabel;

    public Timing() {
        frame = new JFrame("Exam Timer");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timerLabel = new JLabel();
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerLabel.setVerticalAlignment(JLabel.CENTER);
        timerLabel.setFont(timerLabel.getFont().deriveFont(32.0f));

        frame.add(timerLabel);
        frame.setVisible(true);
    }
    public void stopTimer() {
        stopFlag = true;
    }

    public int calculateMarks() {

        int finalMarks = 0;

        for(int i=0;i<OnlineExam.correctAnswers.size();i++) {

            if (i < OnlineExam.answers.size() && OnlineExam.answers.get(i).equals(OnlineExam.correctAnswers.get(i))) {
                finalMarks++;
            }
        }
        return finalMarks;
    }
    
    public void run() {

        Thread timerThread = new Thread(() -> {
            while (time >= 0) {
                timerLabel.setText(Integer.toString(time));
                time--;

                try {
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            frame.dispose(); // Close the timer window

            if (!stopFlag) {

                System.out.println();
                System.out.println("Time's Up!");

                if (Schedule.response != 'Y' && Schedule.response != 'y') {
                    int finalMarks = calculateMarks();
                    System.out.println("Total Marks: " + finalMarks + "/5");
                }
            }
        
        });

        timerThread.start();

        // Wait for the timer thread to finish
        try {
            timerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

