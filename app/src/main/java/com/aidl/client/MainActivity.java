package com.aidl.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * Created by mzp on 2016/10/19.
 */

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "AIDLClient";

    //由AIDL文件生成的Java类
    private BookManager mBookManager;

    //当前与服务器的链接状态, false表示未连接, true表示连接
    private boolean mBind = false;

    //集合存储Book
    private List<Book> mBooks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addBook(View view)
    {
        if (mBookManager != null)
        {
            try
            {
                mBookManager.addBook(new Book("Java 编程思想", "皮特", 57));

            }catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void queryBooks(View view)
    {
        if (mBookManager != null)
        {
            try
            {
                mBooks = mBookManager.getBooks();

                Log.e(TAG, mBooks.toString());
            }catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void updateBooks(View view)
    {
        if (mBookManager != null)
        {
            try
            {
                mBookManager.updatePrice(10);

            }catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void attemptToBindService()
    {
        Intent intent = new Intent();
        intent.setAction("com.aidl.service.test");
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if (!mBind)
        {
            attemptToBindService();
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        if (mBind)
        {
            unbindService(mServiceConnection);
        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            Log.e(TAG, "service connected");
            mBookManager = BookManager.Stub.asInterface(service);
            mBind = true;

            if (mBookManager != null)
            {
                try
                {
                    mBooks = mBookManager.getBooks();

                }catch (RemoteException e)
                {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            Log.e(TAG, "service disconnected");
            mBind = false;
        }
    };
}
