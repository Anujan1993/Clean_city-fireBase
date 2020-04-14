package com.example.clean_city;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.params.HttpParams;

import java.util.ArrayList;

public class ServerRequest {
    ProgressDialog progressDialog;

    public static final int CONNECTION_TIMEOUT= 1000*15;
    public static final String SERVER_ADDRESS = "http://127.0.0.1/";

    public ServerRequest(Context context){
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please Wait...");
    }

    public void storeUserDataInBackground(User user, GetUserCallBack userCallBack){
        progressDialog.show();
        new StoreUserDataAsyncTask(user,userCallBack).execute();
    }
    public void fatchUserDataInBackground(User user,GetUserCallBack callBack){
        progressDialog.show();
    }

    public class StoreUserDataAsyncTask extends AsyncTask<Void,Void,Void>{

        User user;
        GetUserCallBack userCallBack;
        public StoreUserDataAsyncTask(User user,GetUserCallBack userCallBack){
            this.user=user;
            this.userCallBack=userCallBack;
        }

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<String> dataToSend = new ArrayList<>();

            dataToSend.add(Integer.parseInt("name"),user.username);
            dataToSend.add(Integer.parseInt("Address"),user.Address);
            dataToSend.add(Integer.parseInt("Phone"),user.Phone);
            dataToSend.add(Integer.parseInt("Password"),user.Password);

            HttpParams httpParams = new H


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            userCallBack.done(null);
            super.onPostExecute(aVoid);
        }
    }
}
