package com.example.kmc.teamproject.server;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KMC on 2018-02-20.
 */

class DataJson {
    class Window {
        @SerializedName("temperature")
        public String temperature;

        @SerializedName("humidity")
        public String humidity;

        @SerializedName("finedust")
        public String finedust;
    }

    class forecast {
        @SerializedName("temp")
        public String temperature;

        @SerializedName("hudmity")
        public String hudmity;

        @SerializedName("weather")
        public String weather;

    }

}