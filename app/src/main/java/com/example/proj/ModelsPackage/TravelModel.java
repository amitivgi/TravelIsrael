package com.example.proj.ModelsPackage;

import java.io.Serializable;

public class TravelModel implements Serializable {

    private String id;
    private String description;
    private String equipmentList;
    private String googleMapsUrl;
    private String imageUrl;
    private String locationName;
    private double lat;
    private double lng;
    private boolean water;

    public TravelModel(String imageUrl, String locationName, String description,
                       String equipmentList, String googleMapsUrl,
                       double lat, double lng, boolean water) {
        this.imageUrl = imageUrl;
        this.locationName = locationName;
        this.description = description;
        this.equipmentList = equipmentList;
        this.googleMapsUrl = googleMapsUrl;
        this.lat = lat;
        this.lng = lng;
        this.water = water;
    }

    public TravelModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "TravelModel{" +
                "ImageUrl='" + imageUrl + '\'' +
                ", LocationName='" + locationName + '\'' +
                ", Describtion='" + description + '\'' +
                ", EquipmentList='" + equipmentList + '\'' +
                ", GoogleMapsUrl='" + googleMapsUrl + '\'' +
                ", Latitude='" + lat + '\'' +
                ", Longitude='" + lng + '\'' +
                ", Water='" + water + '\'' +
                '}';
    }
}
