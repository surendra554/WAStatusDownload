package com.app.statussaver_videodownload;

import android.content.Context;
import android.net.Uri;

import java.util.ArrayList;

public class ModelClass {
    String path, filename;
    Uri uri;


    public ModelClass(String path, String filename, Uri uri) {
        this.path = path;
        this.filename = filename;
        this.uri = uri;
    }

    public ModelClass() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
