package com.example.crud.view;

import android.os.AsyncTask;
import android.util.Log;

import com.example.crud.entity.AppDatabase;
import com.example.crud.entity.DataPengguna;

import java.util.List;

public class MainPresenter implements MainContact.presenter {
    private MainContact.view view;

    public MainPresenter(MainContact.view view) {
        this.view = view;
    }

    class InsertData extends AsyncTask<Void,Void,Long>{
        private AppDatabase appDatabase;
        private DataPengguna dataPengguna;

        public InsertData(AppDatabase appDatabase, DataPengguna dataPengguna) {
            this.appDatabase = appDatabase;
            this.dataPengguna = dataPengguna;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return appDatabase.dao().insertData(dataPengguna);
        }

        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successAdd();
        }
    }
    @Override
    public void insertData(String name, String birthday, String address, String phone, String email, AppDatabase database) {
        final DataPengguna dataPengguna = new DataPengguna();
        dataPengguna.setName(name);
        dataPengguna.setBirthday(birthday);
        dataPengguna.setAddress(address);
        dataPengguna.setPhone(phone);
        dataPengguna.setEmail(email);
        new InsertData(database,dataPengguna).execute();
    }

    @Override
    public void readData(AppDatabase database) {
        List<DataPengguna> list;
        list = database.dao().getData();
        view.getData(list);
    }

    class EditData extends AsyncTask<Void,Void,Integer>{
        private AppDatabase appDatabase;
        private DataPengguna dataPengguna;

        public EditData(AppDatabase appDatabase, DataPengguna dataPenguna) {
            this.appDatabase = appDatabase;
            this.dataPengguna = dataPenguna;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return appDatabase.dao().updateData(dataPengguna);
        }

        protected void onPostExecute(Integer integer){
            super.onPostExecute(integer);
            Log.d("integer db","onPostExecute : "+integer);
            view.successAdd();
        }
    }
    @Override
    public void editData(String name, String birthday, String address, String phone, String email, int id, AppDatabase database) {
        final DataPengguna dataPengguna = new DataPengguna();
        dataPengguna.setName(name);
        dataPengguna.setBirthday(birthday);
        dataPengguna.setAddress(address);
        dataPengguna.setPhone(phone);
        dataPengguna.setEmail(email);
        dataPengguna.setId(id);
        new EditData(database,dataPengguna).execute();
    }

    class DeleteData extends AsyncTask<Void,Void,Long>{
        private AppDatabase appDatabase;
        private DataPengguna dataPengguna;

        public DeleteData(AppDatabase appDatabase, DataPengguna dataPengguna) {
            this.appDatabase = appDatabase;
            this.dataPengguna = dataPengguna;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            appDatabase.dao().deleteData(dataPengguna);
            return null;
        }

        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successDelete();
        }
    }
    @Override
    public void deleteData(DataPengguna dataPengguna, AppDatabase database) {
        new DeleteData(database, dataPengguna).execute();
    }
}

