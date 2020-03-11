package hu.grdg.projlab;

import java.util.HashMap;
import java.util.Objects;

public class Skeleton {
    //Current skeleton test env
    private static Skeleton currentInstance = null;

    //Instance variables
    private String name;
    private HashMap<Object, String> nameMap;
    private int fnLevel = 0;

    private Skeleton(String name) {
        this.name = name;
        nameMap = new HashMap<>();
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
        currentInstance = new Skeleton(name);
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
}
