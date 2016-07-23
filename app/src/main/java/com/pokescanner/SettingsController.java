package com.pokescanner;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.SwitchCompat;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import com.pokescanner.helper.Settings;


public class SettingsController {

    public static final String KEY_BOUNDING_BOX = "boundingBoxEnabled";
    public static final String SERVER_REFRESH_RATE = "serverRefreshRate";
    public static final String MAP_REFRESH_RATE = "mapRefreshRate";


    //
    // THIS IS NOT FINISHED!!!
    //
    
    public static void showSettingDialog(final Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_settings);

        SwitchCompat showRange = (SwitchCompat) dialog.findViewById(R.id.showRange);

        SeekBar seekMap = (SeekBar) dialog.findViewById(R.id.seekMap);
        SeekBar seekServer = (SeekBar) dialog.findViewById(R.id.seekServer);

        showRange.setChecked(getSettings(context).isBoundingBoxEnabled());
        showRange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, final boolean b) {
                saveSettings(context, new Settings(b));
            }
        });
        seekMap.setMax(4);
        seekServer.setMax(4);
        seekServer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        dialog.show();
    }

    public static Settings getSettings(Context context) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(
            context.getString(R.string.shared_key),
            Context.MODE_PRIVATE
        );
        return new Settings(
            sharedPrefs.getBoolean(KEY_BOUNDING_BOX, false)
        );
    }

    public static void saveSettings(Context context, Settings settings) {
        context.getSharedPreferences(context.getString(R.string.shared_key), Context.MODE_PRIVATE)
            .edit()
            .putBoolean(KEY_BOUNDING_BOX, settings.isBoundingBoxEnabled())
            .commit();
    }
}
