package com.challenge.app.producer.dto;

import java.io.Serializable;



public class IoTDeviceMessage implements Serializable {

    private Data data;

    public IoTDeviceMessage() {
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public static class Data {
        private String deviceId;
        private int temperature;
        private Location location;
        private long time;

        public Data() {
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public int getTemperature() {
            return temperature;
        }

        public void setTemperature(int temperature) {
            this.temperature = temperature;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public static class Location {
            private double latitude;
            private double longitude;

            public Location() {
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }

        }
    }

}

