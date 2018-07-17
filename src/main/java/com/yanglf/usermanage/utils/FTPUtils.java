package com.yanglf.usermanage.utils;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.io.InputStream;

public class FTPUtils {
   static FTPClient ftpClient = new FTPClient();

    public static void upload(InputStream inputStream,String fileName,String path){



        try {
            if (!ftpClient.isConnected()){
                ftpClient.connect("132.232.14.175");
                ftpClient.login("root", "iamperfact1");
            }

        ftpClient.changeWorkingDirectory(path);
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        Thumbnails.of(inputStream).size(400,500);
        ftpClient.storeFile(fileName, inputStream);
        //ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
