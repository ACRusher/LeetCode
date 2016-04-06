package org.ACRusher.test;

import javax.tools.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhouxiliang on 2016/4/6.
 */
public class JavaCompilerTests {

    public static void main(String[] args) throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        JavaSourceFromString javaSourceFromString = new JavaSourceFromString(
                "CalculatorTest",
                "public class CalculatorTest {"
                        + " public int multiply(int multiplicand, int multiplier) {"
                        + " System.out.println(multiplicand);"
                        + " System.out.println(multiplier);"
                        + " return multiplicand * multiplier;" + " }" + "}");

        JavaCompiler.CompilationTask task = compiler.getTask(null, null,
                null, Arrays.asList("-d","f:/"),//class文件输出路径
                null, Arrays.asList(javaSourceFromString));
        Boolean result = task.call();
        System.out.println(result);
        if (result) {
            URL[] urls=new URL[]{new URL("file:/f:/")};
            URLClassLoader classLoader=new URLClassLoader(urls);
            Class clazz = classLoader.loadClass("CalculatorTest");
            Object instance = clazz.newInstance();
            Method m = clazz.getMethod("multiply", new Class[] { int.class,
                    int.class });
            Object[] o = new Object[] { 3, 2 };
            System.out.println(m.invoke(instance, o));
        }
    }

    public static class JavaSourceFromString extends SimpleJavaFileObject {

        private String code;

        /**
         * Construct a SimpleJavaFileObject of the given kind and with the
         * given URI.
         *
         * @param uri  the URI for this file object
         * @param kind the kind of this file object
         */
        protected JavaSourceFromString(URI uri, Kind kind) {
            super(uri, kind);
        }

        public JavaSourceFromString(String name, String code) {

            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);

            this.code = code;
        }

        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return code;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
