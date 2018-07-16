package com.yanglf.usermanage;


import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TestFto {
    public static void main(String[] args) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect("132.232.14.175");
            ftpClient.login("root", "iamperfact1");
            ftpClient.changeWorkingDirectory("/root/myFile/images/avatar");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            File file = new File("F:\\F\\桌面文件\\常用jar\\love\\9.jpg");
            File file1 = new File("temp");
            Thumbnails.of(file).size(400,500).toFile(file);
            FileInputStream inputStream = new FileInputStream(file);
            ftpClient.storeFile("10.jpg", inputStream);
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
