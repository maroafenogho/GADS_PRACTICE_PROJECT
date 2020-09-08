package com.maro.gadspracticeprojectmaroafenogho;

import com.google.gson.annotations.SerializedName;

public class SkillIq {
    @SerializedName("badgeUrl")
    private String badgeUrl;
    @SerializedName("name")
    private String name;
    @SerializedName("country")
    private String country;
    @SerializedName("score")
    private String  score;

    public SkillIq(String badgeUrl, String name, String country, String score) {
        this.badgeUrl = badgeUrl;
        this.name = name;
        this.country = country;
        this.score = score;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getScore() {
        return score;
    }
}
