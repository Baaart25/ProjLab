package hu.grdg.projlab;

import java.util.*;

public class SkeletonTester {

    private static HashMap<String, Runnable> skeletonTests = new HashMap<>();
    public static void registerTest(String name, Runnable r) {
        skeletonTests.put(name, r);
    }

    public static void start() {
        while(true) {
            System.out.println("\n\n");
            System.out.println("\t0: Kilépés");

            String[] tests = skeletonTests.keySet().toArray(new String[skeletonTests.keySet().size()]);
            for(int i = 0; i < tests.length; i++) {
                System.out.printf("\t%d: %s\n", i + 1, tests[i]);
            }
            System.out.print("Válasszon tesztet: ");
            int tnum = SkeletonTester.readNumber();
            if(tnum >= 0  && tnum <= tests.length) {
                if(tnum == 0) {
                    break;
                }else {
                    skeletonTests.get(tests[tnum - 1]).run();
                }
            }
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Skeleton logic">
    //Current skeleton test env
    private static SkeletonTester currentInstance = null;

    //Instance variables
    private String name;
    private HashMap<Object, String> nameMap;
    private int fnLevel = 0;
    private static Scanner scanner = new Scanner(System.in);

    private SkeletonTester(String name) {
        this.name = name;
        nameMap = new HashMap<>();
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

    //Static methods
    public static void addNamedReference(Object o, String name) {
        checkInstance();
        currentInstance.nameMap.put(o, name);
    }

    public static void call(Object instance, Object ... args) {
        checkInstance();
        currentInstance.fnLevel++;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        try {
            StackTraceElement ste = stackTrace[2];
            String method = ste.getMethodName();
            String clazz = ste.getClassName();
            printf("%s%s.%s(%s)", padding(), objectInfo(instance),method, String.join(", ",objaInfo(args)));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String padding() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < currentInstance.fnLevel; i++) {
            for(int x = 0; x < 3; x++)
                sb.append(" ");
        }
        return sb.toString();
    }

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

    private static String objectInfo(Object obj) {
        checkInstance();
        if(currentInstance.nameMap.containsKey(obj)) {
            return currentInstance.nameMap.get(obj);
        }else {
            return Objects.toString(obj);
        }
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

    public static void beginTest(String name) {
        currentInstance = new SkeletonTester(name);
        printf("Skeleton testing started: %s", name);
    }

    public static void endTest() {
        checkInstance();
        currentInstance = null;
        printf("Testing ended");
    }

    public static void creturn() {
        checkInstance();
        currentInstance.fnLevel--;
    }

    private static void checkInstance() {
        if(currentInstance == null)
            throw new IllegalStateException("Instance is null");
    }
    //</editor-fold>
}
