package hu.grdg.projlab;

import java.util.*;

/**
 * Testing utility class for Skeleton
 * @author barrow099
 */
public class SkeletonTester {

    //The available tests
    private static HashMap<String, Runnable> skeletonTests = new HashMap<>();

    /**
     * Register a new test function to the system
     * @param name The name of the test, this will be displayed to the user
     * @param r The test function, a void parameterless function
     */
    public static void registerTest(String name, Runnable r) {
        skeletonTests.put(name, r);
    }

    /**
     * Starts the testing, loops until exit
     * Displays the test cases to the user to choose and executes the chosen test
     */
    public static void start() {
        while(true) {
            System.out.println("\n");
            System.out.println("\t0: Kilépés");

            String[] tests = skeletonTests.keySet().toArray(new String[skeletonTests.keySet().size()]);
            for(int i = 0; i < tests.length; i++) {
                System.out.printf("\t%d: %s\n", i + 1, tests[i]);
            }
            System.out.print("Válasszon tesztet: ");
            int tnum = SkeletonTester.readNumber();
            if(tnum >= 0  && tnum <= tests.length) {
                if(tnum == 0) {
                    //Exit
                    break;
                }else {
                    skeletonTests.get(tests[tnum - 1]).run();
                }
            }
        }
        System.out.println("Goodbye");
    }



    //<editor-fold defaultstate="collapsed" desc="Skeleton logic">

    /*
     * The following code is used for printing the function call tree
     * and provides other features such as numbed and yesno input
     * Please be careful when modifying :D
     */


    //Current skeleton test env
    private static SkeletonTester currentInstance = null;
    //Scanner for input functions
    private static Scanner scanner = new Scanner(System.in);

    //Instance variables
    private String testName;
    //Current named objects
    private HashMap<Object, String> nameMap;
    //Current call tree level
    private int fnLevel = 0;


    private SkeletonTester(String name) {
        this.testName = name;
        nameMap = new HashMap<>();
    }

    //Static methods

    /**
     * Registers a name for a Object
     * The name will be used when te tester prints this instance
     * @param o The object
     * @param name The name
     */
    public static void addNamedReference(Object o, String name) {
        checkInstance();
        currentInstance.nameMap.put(o, name);
    }

    /**
     * Signals a function call to the testing system
     * The function name will be retrieved from the call stack
     * @param instance The instance which method was called
     * @param args Additional arguments to the method
     */
    public static void call(Object instance, Object arg1, Object ... args) {
        checkInstance();
        currentInstance.fnLevel++;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        try {
            StackTraceElement ste = stackTrace[2];
            String method = ste.getMethodName();
            String clazz = ste.getClassName();
            printf("%s%s.%s(%s,%s)", padding(), objectInfo(instance),method, objectInfo(arg1), String.join(", ",objaInfo(args)));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void call(Object instance, Object arg) {
        checkInstance();
        currentInstance.fnLevel++;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        try {
            StackTraceElement ste = stackTrace[2];
            String method = ste.getMethodName();
            String clazz = ste.getClassName();
            printf("%s%s.%s(%s)", padding(), objectInfo(instance),method, objectInfo(arg));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void call(Object instance) {
        checkInstance();
        currentInstance.fnLevel++;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        try {
            StackTraceElement ste = stackTrace[2];
            String method = ste.getMethodName();
            String clazz = ste.getClassName();
            printf("%s%s.%s()", padding(), objectInfo(instance),method);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a padding string for the function call tree level
     * @return The padding string
     */
    private static String padding() {
        return " ".repeat(3).repeat(currentInstance.fnLevel);
    }


    /**
     * Gets every objects info in an object array
     * @param args The input object array
     * @return Array of strings, the values are made using objectInfo()
     */
    private static String[] objaInfo(Object[] args) {
        checkInstance();
        if(args == null)
            return new String[]{""};
        String[] adata = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            adata[i] = objectInfo(args[i]);
        }

        return adata;
    }

    /**
     * Gets the registered name of the object, if its not available
     * Objects.toString is used
     * @param obj The object
     * @return The name or toString value
     */
    private static String objectInfo(Object obj) {
        checkInstance();
        if(currentInstance.nameMap.containsKey(obj)) {
            return currentInstance.nameMap.get(obj);
        }else {
            return Objects.toString(obj);
        }
    }

    /**
     * Begins a new test, creates the required instance and sets up the testing
     * @param name The display name of the test
     */
    public static void beginTest(String name) {
        currentInstance = new SkeletonTester(name);
        printf("Skeleton testing started: %s", name);
    }

    /**
     * Ends the current test
     */
    public static void endTest() {
        checkInstance();
        currentInstance = null;
        printf("Testing ended");
    }

    /**
     * Signal return from method call
     */
    public static void creturn() {
        checkInstance();
        currentInstance.fnLevel--;
    }

    /**
     * Signal value return from the function
     * It can be used to signal return from method call
     * @param retVal The return value, if added as named reference it's name will be printed
     */
    public static void creturn(Object retVal) {
        checkInstance();
        currentInstance.fnLevel++;
        printf("%sreturn %s", padding(), objectInfo(retVal));
        currentInstance.fnLevel--;
        currentInstance.fnLevel--;
    }

    /**
     * Checks if in a test,
     * @throws IllegalStateException if not
     */
    private static void checkInstance() {
        if(currentInstance == null)
            throw new IllegalStateException("Instance is null");
    }

    //Other utility functions
    public static boolean askYesNo(String message) {
        System.out.print(message);
        System.out.print("[Y/N]: ");
        do {
            String line = scanner.nextLine();
            if(line.toLowerCase().equals("y")) {
                return true;
            }else if(line.toLowerCase().equals("n")) {
                return false;
            }
        }while (true);

    }

    public static int askNumber(String message) {
        System.out.print(message);
        return readNumber();
    }

    public static String askString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static int readNumber() {
        return scanner.nextInt();
    }

    public static void print(Object o) {
        if(o == null)
            System.out.println("null");
        else
            System.out.println(o.toString());
    }

    public static void printf(String fmt, Object ... args) {
        print(String.format(fmt, args));
    }
    //</editor-fold>
}
