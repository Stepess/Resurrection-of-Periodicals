package ua.training.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.Date;

public class Comment {
    private final Long id;
    private final Long userId;
    private final String message;
    private final Date date;
    private final Double latitude;
    private final Double longitude;

    public Comment(Long userId, String message, Date date) {
        this(userId, message, date, null, null);
    }

    public Comment(Long userId, String message, Date date, Double latitude, Double longitude) {
        this.id = null;
        this.userId = userId;
        this.message = message;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId(){ return userId;}

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "message='" + message + '\'' +
                ", date=" + date +
                '}';
    }

    //TODO investigate
    @Override
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that, new String[]{"id","date"});
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, new String[]{"id","date"});
    }
}
