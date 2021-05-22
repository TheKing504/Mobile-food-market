package vega.solutions.mobilefoodmarket2;

import android.app.Application;

import java.lang.reflect.Array;
import java.util.ArrayList;

import vega.solutions.mobilefoodmarket2.object.Farm;

public class MobileMarketApp extends Application {

    private static MobileMarketApp instance;
    private ArrayList<Farm> farms;

    public static MobileMarketApp getInstance() {
        return MobileMarketApp.instance;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public ArrayList<Farm> getFarms() {
        return farms;
    }

    public Farm getFarm(int id) {

        if (farms == null) {
            return null;
        }

        for (Farm farm : farms) {
            if (farm.getId() == id) {
                return farm;
            }
        }
        return null;
    }

    public boolean areFarmsLoaded() {
        return farms != null;
    }

    public void setFarms(ArrayList<Farm> farms) {
        this.farms = farms;
    }
}
