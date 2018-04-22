package com.example.kmc.teamproject.server;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KMC on 2018-02-20.
 */

class DataJson {
    class Window{
        @SerializedName("temparature")
        public String temparature;

        @SerializedName("moisture")
        public String moisture;

        @SerializedName("finedust")
        public String finedust;
    }

}