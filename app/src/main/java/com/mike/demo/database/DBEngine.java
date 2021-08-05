package com.mike.demo.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;


import java.util.List;

public class DBEngine {
    private static final String TAG = DBEngine.class.getSimpleName();
    private CommodityDao commodityDao;
    private DBQueryFinishListener queryFinishListener;

    public void setQueryFinishListener(DBQueryFinishListener queryFinishListener) {
        this.queryFinishListener = queryFinishListener;
    }

    public DBEngine(Context context) {
        CommodityDatabase database = CommodityDatabase.getInstance(context);
        commodityDao = database.getCommodityDao();
    }

    public void insertCommodities(Commodity...commodities) {
        new InsertAsyncTask(commodityDao).execute(commodities);
    }

    private class InsertAsyncTask extends AsyncTask<Commodity, Void, Void> {
        private CommodityDao dao;
        public InsertAsyncTask(CommodityDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Commodity... commodities) {
            dao.insertCommodities(commodities);
            return null;
        }
    }

    public void updateCommodities(Commodity...commodities) {
        new UpdateAsyncTask(commodityDao).execute(commodities);
    }

    private class UpdateAsyncTask extends AsyncTask<Commodity, Void, Void> {
        private CommodityDao dao;

        public UpdateAsyncTask(CommodityDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Commodity... commodities) {
            dao.updateCommodities(commodities);
            return null;
        }
    }

    public void queryCommodities() {
        new QueryAsyncTask(commodityDao).execute();
    }

    private class QueryAsyncTask extends AsyncTask<Void, Void, List<Commodity>> {
        private CommodityDao dao;

        public QueryAsyncTask(CommodityDao dao) {
            this.dao = dao;
        }

        @Override
        protected List<Commodity> doInBackground(Void... voids) {
            List<Commodity> commodities = dao.getAllCommodities();
            Log.d(TAG, "commodities size: " + commodities.size());
            for (Commodity commodity : commodities) {
                Log.d(TAG, commodity.toString());
            }

            return commodities;
        }

        @Override
        protected void onPostExecute(List<Commodity> commodities) {
            super.onPostExecute(commodities);
            if (queryFinishListener != null) {
                queryFinishListener.queryFinishSuccessfully(commodities);
            }
        }
    }

    public void deleteCommodities(Commodity...commodities) {
        new DeleteAsyncTask(commodityDao).execute(commodities);
    }

    private class DeleteAsyncTask extends AsyncTask<Commodity, Void, Void> {
        private CommodityDao dao;

        public DeleteAsyncTask(CommodityDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Commodity... commodities) {
            dao.deleteCommodities(commodities);
            return null;
        }
    }

    public void deleteAllCommodities() {
        new DeleteAllAsyncTask(commodityDao).execute();
    }

    private class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private CommodityDao dao;

        public DeleteAllAsyncTask(CommodityDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllCommodities();
            return null;
        }
    }

}
