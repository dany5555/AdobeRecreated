package com.kevin.adoberecreated;

import org.json.JSONException;
import org.json.JSONObject;

public class RecentModel {

    public String fileThumbnailUrl;
    public String fileName;
    public String fileType;
    public String fileOpened;
    public String fileSize;

    public RecentModel(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                fileThumbnailUrl = jsonObject.getString("fileThumbnailUrl");
                fileName = jsonObject.getString("fileName");
                fileType = jsonObject.getString("fileType");
                fileOpened = jsonObject.getString("fileOpened");
                fileSize = jsonObject.getString("fileSize");

            } catch (JSONException e) {

            }
        }
    }
}
