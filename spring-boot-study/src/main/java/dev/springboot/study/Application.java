package dev.springboot.study;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class Application {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        // context 만들기
        Context context = tomcat.addContext("/", "/");

        // servlet 만들기
        HttpServlet servlet = new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws IOException {
                PrintWriter writer = resp.getWriter();
                writer.println("<html><head><title>");
                writer.println("Hey, Tomcat");
                writer.println("</title></head>");
                writer.println("<body><h1>Hello Tomcat</h1></body>");
                writer.println("</html>");
            }
        };

        // 만든 servlet을 servletName으로 이름지어 contextPath가 "/"인 tomcat에 등록
        String servletName = "HelloServlet";
        tomcat.addServlet("/", servletName, servlet);

        // 특정 url 요청이(/hello) 들어오면 만든 servlet을 보여준다.
        context.addServletMappingDecoded("/hello", servletName);

        tomcat.start();
        tomcat.getServer().await();
    }
}
