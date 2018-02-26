package com.wangchong.blog.util;

import com.qiniu.util.Auth;

import java.io.File;

public class UploadUtil  {

    private static final String ACCESSKEY = "hjgatNKQcJ5pWwOcnj-5KkgnBq3053TSi8f0DIpK";
    private static final String SECRETKEY = "cJiFBpCdqKqLNOXclI0kHNPajKSn-JHPbcEek32S";
    private static final String BUCKET = "images";

    public static void upload(){
        Auth auth = Auth.create(ACCESSKEY, SECRETKEY);
        String upToken = auth.uploadToken(BUCKET);
        System.out.println(upToken);




    }

    public static void main(String[] args) {
        upload();
    }
}
