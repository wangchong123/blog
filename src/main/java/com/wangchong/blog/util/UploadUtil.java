package com.wangchong.blog.util;

import com.qiniu.util.Auth;

public class UploadUtil  {

    private static final String ACCESSKEY = "";
    private static final String SECRETKEY = "";
    private static final String BUCKET = "";

    public static void upload(){
        Auth auth = Auth.create(ACCESSKEY, SECRETKEY);
        String upToken = auth.uploadToken(BUCKET);

    }
}
