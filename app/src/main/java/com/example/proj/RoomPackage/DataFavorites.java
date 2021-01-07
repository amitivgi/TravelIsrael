package com.example.proj.RoomPackage;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "data_table_favorites")
public class DataFavorites implements Serializable {

    public DataFavorites(String id, String description, String equipmentList, String googleMapsUrl, String imageUrl,
                         String locationName, String weatherUrl, double lat, double lng, boolean water) {
        this.id = id;
        this.description = description;
        this.equipmentList = equipmentList;
        this.googleMapsUrl = googleMapsUrl;
        this.imageUrl = imageUrl;
        this.locationName = locationName;
        this.weatherUrl = weatherUrl;
        this.lat = lat;
        this.lng = lng;
        this.water = water;
    }

    public DataFavorites() {

    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private long ID;

    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "equipmentList")
    private String equipmentList;

    @ColumnInfo(name = "googleMapsUrl")
    private String googleMapsUrl;

    @ColumnInfo(name = "imageUrl")
    private String imageUrl;

    @ColumnInfo(name = "locationName")
    private String locationName;

    @ColumnInfo(name = "weatherUrl")
    private String weatherUrl;

    @ColumnInfo(name = "lat")
    private double lat;

    @ColumnInfo(name = "lng")
    private double lng;

    @ColumnInfo(name = "water")
    private boolean water;


    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(String equipmentList) {
        this.equipmentList = equipmentList;
    }

    public String getGoogleMapsUrl() {
        return googleMapsUrl;
    }

    public void setGoogleMapsUrl(String googleMapsUrl) {
        this.googleMapsUrl = googleMapsUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getWeatherUrl() {
        return weatherUrl;
    }

    public void setWeatherUrl(String weatherUrl) {
        this.weatherUrl = weatherUrl;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

}