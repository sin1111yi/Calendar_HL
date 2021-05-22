package com.winlin.roomdb.manager;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.winlin.roomdb.dao;
import com.winlin.roomdb.database;
import com.winlin.roomdb.entity;

import java.util.List;

//DB的引擎
public class DBEngine {
    private dao dao_1;
    public DBEngine(Context context){
        database database = com.winlin.roomdb.database.getInstance(context);
        dao_1 = database.getdao();
    }
    // insert

    public void insert(entity...entities){
        new InsertAsyncTask(dao_1).execute(entities);
    }
    //update
    public void update(entity...entities) {
        new UpdateAsyncTask(dao_1).execute(entities);
    }
    //delete 条件
    public void delete(entity...entities){
        new DeleteAsyncTask(dao_1).execute(entities);
    }
    //delete 所有
    public void deleteAll(entity...entities){
        new DeleteAllAsyncTask(dao_1).execute();
    }
    //查询全部
    public void queryAll(entity...entities){
        new QueryAllAsyncTask(dao_1).execute();
    }
    //异步操作
    //insert
    static class InsertAsyncTask extends AsyncTask< entity, Void, Void>{
        private dao S_dao;//接受dao
        public InsertAsyncTask(com.winlin.roomdb.dao dao_1) {
            S_dao = dao_1;
        }
        @Override
        protected Void doInBackground(entity... entities) {
            S_dao.insert(entities);
            return null;
        }
    }
    //update
    static class UpdateAsyncTask extends AsyncTask< entity, Void, Void>{
        private dao S_dao;//接受dao
        public UpdateAsyncTask(com.winlin.roomdb.dao dao_1) {
            S_dao = dao_1;
        }
        @Override
        protected Void doInBackground(entity... entities) {
            S_dao.update(entities);
            return null;
        }
    }
    //delete 条件删除
    static class DeleteAsyncTask extends AsyncTask< entity, Void, Void>{
        private dao S_dao;//接受dao
        public DeleteAsyncTask(com.winlin.roomdb.dao dao_1) {
            S_dao = dao_1;
        }
        @Override
        protected Void doInBackground(entity... entities) {
            S_dao.delete(entities);
            return null;
        }
    }
    //delete 所有删除
    static class DeleteAllAsyncTask extends AsyncTask< Void, Void, Void>{
        private dao S_dao;//接受dao
        public DeleteAllAsyncTask(com.winlin.roomdb.dao dao_1) {
            S_dao = dao_1;
        }
        @Override
        protected Void doInBackground(Void... Voids) {
            S_dao.deleteAll();
            return null;
        }
    }
    //全部查询
    static class QueryAllAsyncTask extends AsyncTask< Void, Void, Void>{
        private dao S_dao;//接受dao
        public QueryAllAsyncTask(com.winlin.roomdb.dao dao_1) {
            S_dao = dao_1;
        }
        @Override
        protected Void doInBackground(Void... Voids) {
            List<entity> all_result = S_dao.findAll();
            //遍历全部查询的结果
            for(entity entity : all_result){
               Log.e("ok","doInBackgroud:全部 查询 每一项 ："+entity.toString());
            }

            return null;
        }
    }
}
