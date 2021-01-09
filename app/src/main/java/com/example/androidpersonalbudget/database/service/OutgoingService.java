package com.example.androidpersonalbudget.database.service;

import android.content.Context;

import com.example.androidpersonalbudget.database.DatabaseManager;
import com.example.androidpersonalbudget.database.dao.OutgoingDao;
import com.example.androidpersonalbudget.database.models.Outgoing;

import java.util.List;
import java.util.concurrent.Callable;

import eu.ase.ro.seminar10.asyncTask.AsyncTaskRunner;

public class OutgoingService {
    private OutgoingDao outgoingDao;
    private final AsyncTaskRunner asyncTask;

    public OutgoingService(Context context){
        outgoingDao= DatabaseManager.getInstance(context).getOutgoingDao();

        asyncTask=new AsyncTaskRunner();
    }

    public void getAll(eu.ase.ro.seminar10.asyncTask.Callback<List<Outgoing>> callback){
        Callable<List<Outgoing>> callable=new Callable<List<Outgoing>>() {
            @Override
            public List<Outgoing> call() throws Exception {
                return outgoingDao.getAll();
            }
        };
        asyncTask.executeAsync(callable,callback);
    }

    public void insert(final Outgoing outgoing, eu.ase.ro.seminar10.asyncTask.Callback<Outgoing> callback){
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

    public void update(Outgoing outgoing){
        Callable<Outgoing> callable=new Callable<Outgoing>() {
            @Override
            public Outgoing call() throws Exception {
                
                return null;
            }
        }
        asyncTask.executeAsync(callable,callback);
    }
}
