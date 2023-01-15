package com.VIT.vlibrary;

public class Books
{
    String Author,Title,Year,url;

    public Books()
    {

    }

    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return Author;
    }

    public String getYear() {
        return Year;
    }

    public String getUrl() {
        return url;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setYear(String year) {
        Year = year;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
