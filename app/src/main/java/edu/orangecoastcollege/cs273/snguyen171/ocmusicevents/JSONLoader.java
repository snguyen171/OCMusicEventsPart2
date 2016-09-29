package edu.orangecoastcollege.cs273.snguyen171.ocmusicevents;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Class loads MusicEvent data from a formatted JSON (JavaScript Object Notation) file.
 * Populates data model (MusicEvent) with data.
 */

public class JSONLoader {

    /**
     * Loads JSON data from a file in the assets directory.
     * @param context The activity from which the data is loaded.
     * @throws IOException If there is an error reading from the JSON file.
     */
    public static ArrayList<MusicEvent> loadJSONFromAsset(Context context) throws IOException {
        ArrayList<MusicEvent> allMusicEvents = new ArrayList<>();
        String json = null;
            InputStream is = context.getAssets().open("MusicEvents.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        try {
            JSONObject jsonRootObject = new JSONObject(json);
            JSONArray allMusicEventsJSON = jsonRootObject.getJSONArray("MusicEvents");
            int numberOfEvents = allMusicEventsJSON.length();

            for (int i = 0; i < numberOfEvents; i++) {
                JSONObject musicEventJSON = allMusicEventsJSON.getJSONObject(i);

                MusicEvent event = new MusicEvent();
                event.setTitle(musicEventJSON.getString("Title"));

                event.setDate(musicEventJSON.getString("Date"));
                event.setDay(musicEventJSON.getString("Day"));
                event.setTime(musicEventJSON.getString("Time"));
                event.setLocation(musicEventJSON.getString("Location"));
                event.setAddress1(musicEventJSON.getString("Address1"));
                event.setAddress2(musicEventJSON.getString("Address2"));
                event.setImageName(musicEventJSON.getString("ImageName"));

            allMusicEvents.add(event);
        }
        }
        catch (JSONException e)
        {
            Log.e("OC Music Events", e.getMessage());
        }

        return allMusicEvents;
    }
}
