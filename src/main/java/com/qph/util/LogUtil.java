package com.qph.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

public class LogUtil {


    public static void writeLog(String clase , Exception e ){
        Logger logger = Logger.getLogger(clase);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String sStackTrace = sw.toString(); // stack trace as a string
        logger.info(sStackTrace);
    }
}
