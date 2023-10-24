import dao.impl.ProductDAOImpl;
import model.Product;

import java.io.FilterOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTests {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Product product1 = new Product();
        product1.setProductCode("123 123");
        product1.setProductLine("Classic Cars");
        product1.setBuyPrice(123.676);
        Product product2 = new Product();
        //Root reflection Class
        Class<Product> pClass = Product.class;
        System.out.println(pClass == product1.getClass());
        System.out.println(pClass == product2.getClass());
        System.out.println("___________________Fields:");
        for (Field field : pClass.getDeclaredFields()) {
            String fieldName = field.getName();
            System.out.println(fieldName);
        }
        System.out.println("___________________Methods:");
        for (Method method : pClass.getDeclaredMethods()) {
            String methodName = method.getName();
            if (methodName.startsWith("set"))
                System.out.println(methodName);

            if (methodName.startsWith("get")) {
                Object o = method.invoke(product1, null);
                System.out.println(o);
            }

        }
        System.out.println( new ProductDAOImpl().findById("S10_4698"));
    }
}
