package com.cmfs.resgenerator;

import com.cmfs.resgenerator.internal.Density;
import com.cmfs.resgenerator.internal.GenerateType;
import com.cmfs.resgenerator.internal.Request;

import java.io.IOException;

public class ResGenerator {

    public static void main(String[] args) throws IOException {
        // 设计图为1920*1080
        // 如果使用标注的数字，说明是mdpi
        Request.create()
                .support(1920, 1080, Density.HDPI)
                .support(1920, 1080, Density.MDPI)
                .support(1280, 720, Density.HDPI)
                .support(1280, 720, Density.MDPI)
                .support(1280, 672, Density.MDPI)
                .defaultSupport(1920, 1080, Density.MDPI)
                .build()
                .destDir("example/src/main/res") // app/src/main/res
                .run(GenerateType.X);

    }

}
