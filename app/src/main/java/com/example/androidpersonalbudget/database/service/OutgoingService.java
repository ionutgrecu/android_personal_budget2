package com.example.androidpersonalbudget.database.service;

import android.content.Context;

import com.example.androidpersonalbudget.asyncTask.AsyncTaskRunner;
import com.example.androidpersonalbudget.asyncTask.Callback;
import com.example.androidpersonalbudget.database.DatabaseManager;
import com.example.androidpersonalbudget.database.dao.OutgoingDao;
import com.example.androidpersonalbudget.database.models.Outgoing;

import java.util.List;
import java.util.concurrent.Callable;

public class OutgoingService {
    private OutgoingDao outgoingDao;
    private final AsyncTaskRunner asyncTask;

    public OutgoingService(Context context){
        outgoingDao= DatabaseManager.getInstance(context).getOutgoingDao();

        asyncTask=new AsyncTaskRunner();
    }

    public void getAll(Callback<List<Outgoing>> callback){
        Callable<List<Outgoing>> callable=new Callable<List<Outgoing>>() {
            @Override
            public List<Outgoing> call() throws Exception {
                return outgoingDao.getAll();
            }
        };
        asyncTask.executeAsync(callable,callback);
    }

    public void insert(final Outgoing outgoing, Callback<Outgoing> callback){
        Callable<Outgoing> callable=new Callable<Outgoing>() {
            @Override
            public Outgoing call() throws Exception {
                if(outgoing==null)return null;

                long id=outgoingDao.insert(outgoing);

                if(id==-1)return null;

                outgoing.setId(id);
                return outgoing;
            }
        };
        asyncTask.executeAsync(callable,callback);
    }

    public void update(final Outgoing outgoing, Callback<Outgoing> callback){
        Callable<Outgoing> callable=new Callable<Outgoing>() {
            @Override
            public Outgoing call() throws Exception {
                if(outgoing==null)return null;

                int count=outgoingDao.update(outgoing);
                if(count!=1)return null;

                return outgoing;
            }
        };
        asyncTask.executeAsync(callable,callback);
    }

    private void delete(final Outgoing outgoing, Callback<Integer> callback){
        Callable<Integer> callable=new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                if(outgoing==null)return -1;

                return outgoingDao.delete(outgoing);
            }
        };

        asyncTask.executeAsync(callable,callback);
    }
}
