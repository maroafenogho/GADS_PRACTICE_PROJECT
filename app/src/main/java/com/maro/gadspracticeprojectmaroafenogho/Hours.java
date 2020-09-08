package com.maro.gadspracticeprojectmaroafenogho;

import com.google.gson.annotations.SerializedName;

public class Hours {
    @SerializedName("badgeUrl")
    private String badgeUrl;
    @SerializedName("name")
    private String name;
    @SerializedName("country")
    private String country;
    @SerializedName("hours")
    private String  hours;

    public Hours(String badgeUrl, String name, String country, String hours) {
        this.badgeUrl = badgeUrl;
        this.name = name;
        this.country = country;
        this.hours = hours;
    }

    public String getCountry() {
        return country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public String getName() {
        return name;
    }

    public String getHours() {
        return hours;
    }

}
