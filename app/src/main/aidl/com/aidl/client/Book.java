package com.aidl.client;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mzp on 2016/10/19.
 */

public class Book implements Parcelable
{
    private String bookName;
    private String author;
    private int price;

    public Book(String bookName, String author, int price)
    {
        this.bookName = bookName;
        this.author = author;
        this.price = price;
    }

    protected Book(Parcel in)
    {
        bookName = in.readString();
        author = in.readString();
        price = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(bookName);
        dest.writeString(author);
        dest.writeInt(price);
    }

    public void readFromParcel(Parcel dest)
    {
        this.bookName = dest.readString();
        this.author = dest.readString();
        this.price = dest.readInt();
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>()
    {
        @Override
        public Book createFromParcel(Parcel in)
        {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size)
        {
            return new Book[size];
        }
    };

    public String getBookName()
    {
        return bookName;
    }

    public Book setBookName(String bookName)
    {
        this.bookName = bookName;
        return this;
    }

    public String getAuthor()
    {
        return author;
    }

    public Book setAuthor(String author)
    {
        this.author = author;
        return this;
    }

    public int getPrice()
    {
        return price;
    }

    public Book setPrice(int price)
    {
        this.price = price;
        return this;
    }

    @Override
    public String toString()
    {
        return "BookName:" + bookName + ";\n" + "author:" + author + ";\n" + "price:" + price;
    }
}
