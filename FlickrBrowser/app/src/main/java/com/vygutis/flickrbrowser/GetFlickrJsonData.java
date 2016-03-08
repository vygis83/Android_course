package com.vygutis.flickrbrowser;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luksyvyg on 08/03/2016.
 */
public class GetFlickrJsonData extends GetRawData {

    private String LOG_TAG = GetFlickrJsonData.class.getSimpleName();
    private List<Photo> mPhotos;
    private Uri mDestinationUri;

    public GetFlickrJsonData(String searchCriteria, boolean matchAll) {
        super(null);
        createAndUpdateUri(searchCriteria, matchAll);
        mPhotos = new ArrayList<>();
    }

    @Override
    public void execute() {
        super.setmRawUrl(mDestinationUri.toString());
        DownloadJsonData downloadJsonData = new DownloadJsonData();
        Log.v(LOG_TAG, "Built URI = " + mDestinationUri.toString());
        downloadJsonData.execute(mDestinationUri.toString());
    }

    public boolean createAndUpdateUri(String searchCriteria, boolean matchAll) {
        final String FLICKR_API_BASE_URL = "https://api.flickr.com/services/feeds/photos_public.gne";
        final String TAGS_PARAM = "tags";
        final String TAGMODE_PARAM = "tagmode";
        final String FORMAT_PARAM = "format";
        final String NO_JSONCALLBACK_PARAM = "nojsoncallback";

        mDestinationUri = Uri.parse(FLICKR_API_BASE_URL).buildUpon()
                .appendQueryParameter(TAGS_PARAM, searchCriteria)
                .appendQueryParameter(TAGMODE_PARAM, matchAll ? "ALL" : "ANY")
                .appendQueryParameter(FORMAT_PARAM, "json")
                .appendQueryParameter(NO_JSONCALLBACK_PARAM, "1")
                .build();

        return mDestinationUri != null;
    }

    public void processResult() {

        if(getmDownloadStatus() != DownloadStatus.OK) {
            Log.e(LOG_TAG, "Error downloading raw file");
        }

        final String FLICKR_ITEMS = "items";
        final String FLICKR_TITLE = "title";
        final String FLICKR_MEDIA = "media";
        final String FLICKR_PHOTO_URL = "m";
        final String FLICKR_AUTHOR = "author";
        final String FLICKR_AUTHOR_ID = "author_id";
        final String FLICKR_LINK = "link";
        final String FLICKR_TAGS = "tags";

        try {
            JSONObject jsonData = new JSONObject(getmData());
            JSONArray itemsArray = jsonData.getJSONArray(FLICKR_ITEMS);
            for(int i=0; i<itemsArray.length(); i++) {
                JSONObject jsonPhoto = itemsArray.getJSONObject(i);
                String title = jsonPhoto.getString(FLICKR_TITLE);
                String author = jsonPhoto.getString(FLICKR_AUTHOR);
                String authorId = jsonPhoto.getString(FLICKR_AUTHOR_ID);
                String link = jsonPhoto.getString(FLICKR_LINK);
                String tags = jsonPhoto.getString(FLICKR_TAGS);

                JSONObject jsonMedia = jsonPhoto.getJSONObject(FLICKR_MEDIA);
                String photoUrl = jsonMedia.getString(FLICKR_PHOTO_URL);

                Photo photo = new Photo(title, author, authorId, link, tags, photoUrl);
                this.mPhotos.add(photo);
            }

            for(Photo singlePhoto: mPhotos) {
                Log.v(LOG_TAG, singlePhoto.toString());
            }

        } catch (JSONException jsone) {
            jsone.printStackTrace();
            Log.e(LOG_TAG, "Error processing Json data");
        }
    }

    public class DownloadJsonData extends DownloadRawData {

        @Override
        protected void onPostExecute(String webData) {
            super.onPostExecute(webData);
            processResult();
        }

        @Override
        protected String doInBackground(String... params) {
            return super.doInBackground(params);
        }
    }
}
