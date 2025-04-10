import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

public class Q4 {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void main(String[] args) throws ClassNotFoundException {
        Class c = Class.forName("Emp");
        Constructor[] cons = c.getDeclaredConstructors();
         
        for(Constructor con : cons) {
            System.out.println("Name of Constructor : " + con);
            System.out.println("Count of constructor parameter : " + con.getParameterCount());
             
            Parameter[] params = con.getParameters();
            for(Parameter param : params) {
                System.out.println("Constructor's parameter : " + param);
            } 
            System.out.println();
        }
      
        Method[] methods = c.getDeclaredMethods();
        System.out.println("Length of method : " + methods.length);
       
        for(Method method : methods){
            System.out.println("Method name: " + method);
            System.out.println("Method return type: "+method.getReturnType());
            System.out.println("Method parameter count: " + method.getParameterCount());
            System.out.println();
            Parameter[] params = method.getParameters();
            for(Parameter param : params) {
                System.out.println("Method's Parameter: " + param);
            }
            System.out.println();
        }
      
        Class[] classes = c.getDeclaredClasses();
        for(Class x : classes) {
            System.out.println("class: " + x);
            System.out.println("Name of class: " + x.getName());
        }
      
        // Annotation[] annos = c.getDeclaredAnnotations();
        // for(Annotation anno : annos) {
        //     System.out.println("Annotation: " + anno);
        // } 
    }
 
}
