package com.cmfs.resgenerator.internal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @author cmfs
 */

public class Executor {

    private Map<Location, List<Support>> supportMap;
    private Support defSupport;
    private File destDir;

    public Executor(Map<Location, List<Support>> supportMap, Support defSupport) {
        this.supportMap = supportMap;
        this.defSupport = defSupport;
    }

    public Executor destDir(String path) {
        destDir = new File(path);
        return this;
    }

    public void run(GenerateType outputType) {
        Set<Location> locationSet = supportMap.keySet();
        List<Support> supportList;
        for (Location location : locationSet) {
            supportList = supportMap.get(location);
            for (Support support : supportList) {
                if (outputType != GenerateType.X) {
                    outputLayoutDimens(support, false, supportList.size() >= 1, true);
                }
                if (outputType != GenerateType.Y) {
                    outputLayoutDimens(support, true, supportList.size() >= 1, true);
                }
                outputTextDimens(support, true, true);
            }
        }
        if (outputType != GenerateType.X) {
            outputLayoutDimens(defSupport, false, false);
        }
        if (outputType != GenerateType.Y) {
            outputLayoutDimens(defSupport, true, false);
        }
        outputTextDimens(defSupport, false, false);
    }

    /**
     * @param addScreenParams 是否需要拼接尺寸
     */
    private void outputLayoutDimens(Support support, boolean isX, boolean addScreenParams) {
        outputLayoutDimens(support, isX, addScreenParams, false);
    }

    private void outputTextDimens(Support support, boolean addScreenParams, boolean addDpi) {
        if (support == null) {
            return;
        }
        int width = support.width;
        int height = support.height;

        Density density = support.getScaleDensity();
        String xmlName = "dimens_text.xml";
        String dirName = "values";

        if (addDpi) {
            dirName += ("-" + density.name().toLowerCase());
        }
        if (addScreenParams) {
            dirName += ("-" + width + "x" + height);
        }
        File dimenSpecificDir = new File(destDir, dirName);
        if (!dimenSpecificDir.exists()) {
            dimenSpecificDir.mkdirs();
        }
        try {
            File outputFile = new File(dimenSpecificDir, xmlName);
            Log.println(">>> %s", outputFile.getAbsolutePath());
            if (outputFile.exists()) {
                outputFile.delete();
            }
            outputFile.createNewFile();
            FileWriter writer = new FileWriter(outputFile, true);
            printlnToFile(writer, Constants.HEAD);
            printlnToFile(writer, "    <!-- 这是用于布局的dimen值 -->");

            for (int i = 1; i <= Constants.TEXT_SIZE_MAX; i++) {
                printlnToFile(writer, String.format(Locale.US
                        , "    <dimen name=\"text_%d\">%.2fsp</dimen>",
                        i, calculateDimens(defSupport, support, i)));
                writer.flush();
            }

            printlnRoot(writer);
            finishPrint(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param addScreenParams 是否需要拼接尺寸
     */
    private void outputLayoutDimens(Support support, boolean isX
            , boolean addScreenParams, boolean addDpi) {
        if (support == null) {
            return;
        }
        int width = support.width;
        int height = support.height;
        Density density = isX ? support.getWidthDensity() : support.getHeightDensity();

        String xmlName = isX ? "dimens_layout_x.xml" : "dimens_layout_y.xml";

        String dirName = "values";
        if (addDpi) {
            dirName += ("-" + density.name().toLowerCase());
        }
        if (addScreenParams) {
            dirName += ("-" + width + "x" + height);
        }
        File dimenSpecificDir = new File(destDir, dirName);
        if (!dimenSpecificDir.exists()) {
            dimenSpecificDir.mkdirs();
        }
        try {
            File outputFile = new File(dimenSpecificDir, xmlName);
            Log.println(">>> %s", outputFile.getAbsolutePath());
            if (outputFile.exists()) {
                outputFile.delete();
            }
            outputFile.createNewFile();
            FileWriter writer = new FileWriter(outputFile, true);
            printlnToFile(writer, Constants.HEAD);
            printlnToFile(writer, "    <!-- 这是用于布局的dimen值 -->");

            int maxCount = Math.max(support.width, support.height);
            for (int i = 1; i <= maxCount; i++) {
                printlnToFile(writer, String.format(Locale.US
                        , "    <dimen name=\"%s%d\">%.2fdp</dimen>"
                        , (isX ? "x" : "y"), i, calculateLayout(defSupport, support, i)));
                writer.flush();
            }

            printlnRoot(writer);
            finishPrint(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printlnToFile(Writer writer, String string) throws IOException {
        writer.write(string);
        writer.write('\n');
        writer.flush();
    }

    private void printlnRoot(Writer writer) throws IOException {
        writer.write(Constants.FOOT);
    }

    private void finishPrint(Writer writer) throws IOException {
        writer.flush();
        writer.close();
    }

    private double calculateLayout(Support defSupport, Support support, int value) {
        float defWidth = defSupport.width;
        float tarWidth = support.width;
        double result;
        if (defSupport.getDensity() == support.getDensity()) {
            result = tarWidth * value / defWidth;
        } else {
            double defDensity = defSupport.getDensity().getValue();
            double tarDensity = support.getDensity().getValue();
            result = tarWidth * value * defDensity / (defWidth * tarDensity);
        }
        return result;
    }

    private double calculateDimens(Support defSupport, Support support, int value) {
        float defWidth = defSupport.width;
        float tarWidth = support.width;
        double result;
        if (defSupport.getScaleDensity() == support.getScaleDensity()) {
            result = tarWidth * value / defWidth;
        } else {
            double defDensity = defSupport.getScaleDensity().getValue();
            double tarDensity = support.getScaleDensity().getValue();
            result = tarWidth * value * defDensity / (defWidth * tarDensity);
        }
        return result;
    }

}
