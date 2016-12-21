package ru.csu.profcom.retrofit;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class News {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("date")
    @Expose
    private String date;

    private Date testDate = new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd-MM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date tempDate = null;
        String result = null;

        try {
            tempDate = inputFormat.parse(date.toString());
            result = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.testDate = tempDate;
        this.date = result;
    }

    public Date getTestDate() {
        return testDate;
    }
}
