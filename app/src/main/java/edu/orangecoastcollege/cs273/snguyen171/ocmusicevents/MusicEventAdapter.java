package edu.orangecoastcollege.cs273.snguyen171.ocmusicevents;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom adapter to bind an ArrayList of MusicEvents to a custom layout.
 *
 */

public class MusicEventAdapter extends ArrayAdapter<MusicEvent> {
    private Context mContext;
    private int mResourceId;
    private List<MusicEvent> mAllMusicEvents;

    private ImageView listItemImageView;
    private TextView listItemTitleTextView;
    private TextView listItemDateTextView;

    /**
     * Parameterized constructor for this custom adapter.
     * @param context The context from which the MusicEventAdapter was created.
     * @param resourceId The layout resource id (e.g. R.layout.music_event_list_item)
     * @param allMusicEvents The ArrayList containing all MusicEvent objects.
     */
    public MusicEventAdapter(Context context, int resourceId, ArrayList<MusicEvent> allMusicEvents)
    {
        super(context, resourceId, allMusicEvents);
        this.mResourceId = resourceId;
        this.mContext = context;
        this.mAllMusicEvents = allMusicEvents;
    }

    /**
     * Gets the view associated with the layout (sets ImageView and TextView content).
     * @param pos The position of the MusicEvent selected.
     * @param convertView The converted view.
     * @param parent The parent - ArrayAdapter
     * @return The new view with all content (ImageView and TextView) set.
     */
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        MusicEvent musicEvent = mAllMusicEvents.get(pos);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        listItemImageView = (ImageView) view.findViewById(R.id.listItemImageView);
        listItemTitleTextView = (TextView) view.findViewById(R.id.listItemTitleTextView);
        listItemDateTextView = (TextView) view.findViewById(R.id.listItemDateTextView);

        AssetManager am = mContext.getAssets();
        try {
            InputStream stream = am.open(musicEvent.getImageName());
            Drawable event = Drawable.createFromStream(stream, musicEvent.getTitle());
            listItemImageView.setImageDrawable(event);
        }
        catch (IOException ex)
        {
            Log.e("OC Music Events", "Error loading " + musicEvent.getImageName(), ex);
        }

        listItemTitleTextView.setText(musicEvent.getTitle());
        listItemDateTextView.setText(musicEvent.getDate());

        return view;
    }


}
