package com.VIT.vlibrary;

public class BookInfo
{
    private String Author;
    private String Title;
    private String Year;
    private String url;

    public BookInfo(String author,String title,String year,String url)
    {
        this.Author = author;
        this.Title = title;
        this.Year = year;
        this.url = url;
    }

    public String getTitle()
    {
        return Title;
    }
    public String getAuthor()
    {
        return Author;
    }
    public String getYear()
    {
        return Year;
    }

    public String getUrl() {
        return url;
    }
}























