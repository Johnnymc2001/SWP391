/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author JohnnyMC
 */
public class RoadmapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        FileReader fr = null;
        String line = null;
        ServletContext context = sce.getServletContext();

        try {
            HashMap<String, String> roadmap = new HashMap<String, String>();

            String realPath = sce.getServletContext().getRealPath("/") + "WEB-INF/roadmap.txt";
            fr = new FileReader(realPath);
            BufferedReader br = new BufferedReader(fr);

            try {
                System.out.println("=== URL MAPPING TABLE ===");
                while ((line = br.readLine()) != null) {
                    if (line.trim().length() >= 0 || !line.contains("#")) {
                        String split[] = line.split("=");

                        String src = split[0];
                        String dst = split[1];

                        if (src.equals("")) {
                            src = "default";
                        }

                        roadmap.put(src, dst);
                        System.out.println(src + " ---> " + dst);
                    }

                }
            } catch (IOException ex) {
                context.log("RoadmapListener _ IO " + ex.getMessage());
            }
            if (br != null) {
                br.close();
            }

            if (fr != null) {
                fr.close();
            }

            sce.getServletContext().setAttribute("ROADMAP", roadmap);
        } catch (FileNotFoundException ex) {
            context.log("RoadmapListener _ FileNotFound " + ex.getMessage());

        } catch (IOException ex) {
            context.log("RoadmapListener _ IO " + ex.getMessage());
        } finally {
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
