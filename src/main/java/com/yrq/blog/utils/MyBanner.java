package com.yrq.blog.utils;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.core.env.Environment;

import java.io.PrintStream;
/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-29 18:12
 **/
public class MyBanner implements Banner {
    private static final String BANNER = "\n" +
            "$$\\     $$\\ $$$$$$$\\   $$$$$$\\             $$$$$$$\\  $$\\      $$$$$$\\   $$$$$$\\  \n" +
            "\\$$\\   $$  |$$  __$$\\ $$  __$$\\            $$  __$$\\ $$ |    $$  __$$\\ $$  __$$\\ \n" +
            " \\$$\\ $$  / $$ |  $$ |$$ /  $$ |           $$ |  $$ |$$ |    $$ /  $$ |$$ /  \\__|\n" +
            "  \\$$$$  /  $$$$$$$  |$$ |  $$ |           $$$$$$$\\ |$$ |    $$ |  $$ |$$ |$$$$\\ \n" +
            "   \\$$  /   $$  __$$< $$ |  $$ |           $$  __$$\\ $$ |    $$ |  $$ |$$ |\\_$$ |\n" +
            "    $$ |    $$ |  $$ |$$ $$\\$$ |           $$ |  $$ |$$ |    $$ |  $$ |$$ |  $$ |\n" +
            "    $$ |    $$ |  $$ |\\$$$$$$ /            $$$$$$$  |$$$$$$$$\\$$$$$$  |\\$$$$$$  |\n" +
            "    \\__|    \\__|  \\__| \\___$$$\\            \\_______/ \\________\\______/  \\______/ \n" +
            "                           \\___|                                                 \n" +
            "                                                                                 \n" +
            "                                                                                 \n";

    private static final String SPRING_BOOT = " :: Spring Boot :: ";

    private static final int STRAP_LINE_SIZE = 42;



    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream printStream) {
        printStream.println(BANNER);
        String version = SpringBootVersion.getVersion();
        version = (version != null) ? " (v" + version + ")" : "";
        StringBuilder padding = new StringBuilder();
        while (padding.length() < STRAP_LINE_SIZE - (version.length() + SPRING_BOOT.length())) {
            padding.append(" ");
        }

        printStream.println(AnsiOutput.toString(AnsiColor.GREEN, SPRING_BOOT, AnsiColor.DEFAULT, padding.toString(),
                AnsiStyle.FAINT, version));
        printStream.println();
    }

}
