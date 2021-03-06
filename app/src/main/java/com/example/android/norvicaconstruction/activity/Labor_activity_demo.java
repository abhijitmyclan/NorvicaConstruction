package com.example.android.norvicaconstruction.activity;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.norvicaconstruction.R;
import com.example.android.norvicaconstruction.adapter.CustomImageAdapter;
import com.example.android.norvicaconstruction.models.Constructor_Model;
import com.example.android.norvicaconstruction.models.MyImage;
import com.example.android.norvicaconstruction.util.ServerClass;
import com.example.android.norvicaconstruction.util.ServiceHandler;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static com.example.android.norvicaconstruction.util.appurl.APPURL.APP_URL;

public class Labor_activity_demo extends AppCompatActivity implements View.OnClickListener {
   EditText reportedt;
   EditText workplaceedt;
   TextView datetxtview;
   Spinner constructor_spinner;
   Spinner labor_spinner;
   Spinner size_spinner;
    EditText helper,fitter,mc,sc,fc,mason,area_edt,desc_edt;
LinearLayout dynamic_ln;
    String SpinnerValue;
    Bitmap image;
    int REQUEST_CAMERA=25;
    int SELECT_FILE=30;
    String userChoosenTask;
    ImageView imgview_capture;
    Button attach_button;
    FloatingActionButton fab;
    Button submit_button;
    String values="";
    private String serverResponse = "";
//////////////////////////// dynamic Content declaration///////////////////

EditText Carhelper;
EditText carpenter;

    ArrayList<EditText> civildatalist = new ArrayList<>();
    ArrayList<EditText> centringdatalist = new ArrayList<>();
    ArrayList<EditText> carpendingdatalist = new ArrayList<>();
    private String[] selValue;
    ///////////////////////////
private ProgressDialog progressDialog;
    private final String TAG = Labor_activity_demo.class.getName();
    Constructor_Model constructor_model;
    ArrayAdapter<String> adapter;
    ArrayList<String> constructor_modelArrayList= new ArrayList<String>();
    Button addImage;
    //An ArrayList for Spinner Items
    private ArrayList<String> constructor;
    //JSON Array
    private JSONArray result;

      // Temp save listItem position
    //For Image Attachment
      String constrvalue;
    private Bitmap bitmap;
    private String file_name;
    String m_sDate;
    Switch simpleSwitch;
    String switchvalue;
    String sizevalue;
    String[] SizeNames={"sqmt","sqm","Nos","mm","cm","Inch","Mtr","Feet","Foot","Km","Mile","sq ft","sq mi","M2","M3","MT"};


    //////////////

    private ArrayList<MyImage> images;
    private CustomImageAdapter imageAdapter;
    private ListView listView;
    private Uri mCapturedImageURI;
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);

        images = new ArrayList();
        imageAdapter = new CustomImageAdapter(Labor_activity_demo.this, images);

        reportedt = (EditText) findViewById(R.id.reportedt);
        area_edt = (EditText) findViewById(R.id.area_edt);
        desc_edt = (EditText) findViewById(R.id.desc_edt);
        constructor_spinner = (Spinner) findViewById(R.id.constructor_spinner);
        labor_spinner = (Spinner) findViewById(R.id.labor_spinner);
        size_spinner = (Spinner) findViewById(R.id.size_spinner);
        dynamic_ln = (LinearLayout) findViewById(R.id.dynamic_ln);
//        imgview_capture = (ImageView) findViewById(R.id.imgview_capture);
//        imgview_capture.setVisibility(View.GONE);
        attach_button = (Button) findViewById(R.id.attach_button);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        submit_button = (Button) findViewById(R.id.submit_button);
        simpleSwitch = (Switch) findViewById(R.id.simpleSwitch);
        //Initializing the ArrayList
        constructor = new ArrayList<String>();
        ////////////
        datetxtview = (TextView) findViewById(R.id.datetxtview);
//        datetxtview.setOnClickListener(this);
//        attach_button.setOnClickListener(this);
        submit_button.setOnClickListener(this);
//        fab.setOnClickListener(this);

        //////////////////Switch Code////////////////////////////////////////
//        simpleSwitch.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                if (simpleSwitch.isChecked()) {
//                    switchvalue = "1";
////                    Toast.makeText(Labor_activity.this, "Switch is on", Toast.LENGTH_LONG).show();
//                } else {
//                    switchvalue = "0";
////                    Toast.makeText(Labor_activity.this, "Switch is Off", Toast.LENGTH_LONG).show();
//                }
//
//            }
//        });
///////////////////////Construction spinner////////////////////////////////////
//        constructor_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> arg0, View arg1,
//                                       int arg2, long arg3) {
//                // On selecting a spinner item
//                constrvalue = arg0.getSelectedItem().toString();
//                // Showing selected spinner item
//                Toast.makeText(arg0.getContext(), "You selected: " + constrvalue, Toast.LENGTH_LONG).show();
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

///////////////Spinner Labor///////////
//        List<String> list = new ArrayList<String>();
//        list.add("Select Labor Type");
//        list.add("Civil");
//        list.add("Centring");
//        list.add("Carpending");
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_spinner_item,list);
//        dataAdapter.setDropDownViewResource
//                (android.R.layout.simple_spinner_dropdown_item);
//        labor_spinner.setAdapter(dataAdapter);
//        // Spinner item selection Listener
//        addListenerOnSpinnerItemSelection();
//        //Adding setOnItemSelectedListener method on spinner.
//        labor_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view,
//                                       int position, long id) {
//
//                dynamic_ln.removeAllViews();
//                String text = labor_spinner.getSelectedItem().toString();
//
//                switch(text){
//
//                    case "Civil":
//                      mc = new EditText(Labor_activity_demo.this);
//                        mc.setInputType(InputType.TYPE_CLASS_NUMBER);
//                        mc.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                         sc = new EditText(Labor_activity_demo.this);
//                        sc.setInputType(InputType.TYPE_CLASS_NUMBER);
//                        sc.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                         mason = new EditText(Labor_activity_demo.this);
//                        mason.setInputType(InputType.TYPE_CLASS_NUMBER);
//                        mason.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                         fc = new EditText(Labor_activity_demo.this);
//                        fc.setInputType(InputType.TYPE_CLASS_NUMBER);
//                        fc.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//
//                        mc.setHint("MC");
//                        sc.setHint("SC");
//                        mason.setHint("MASON");
//                        fc.setHint("FC");
//
//                        dynamic_ln.addView(mc);
//                        dynamic_ln.addView(sc);
//                        dynamic_ln.addView(mason);
//                        dynamic_ln.addView(fc);
//
//                        civildatalist.add(mc);
//                        civildatalist.add(sc);
//                        civildatalist.add(mason);
//                        civildatalist.add(fc);
//                        break;
//                    case "Centring":
//
//
//                         helper = new EditText(Labor_activity_demo.this);
//                        helper.setInputType(InputType.TYPE_CLASS_NUMBER);
//                        helper.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                         fitter = new EditText(Labor_activity_demo.this);
//                        fitter.setInputType(InputType.TYPE_CLASS_NUMBER);
//                        fitter.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//
//                        helper.setHint("Helper");
//                        fitter.setHint("Fitter");
//
//                        dynamic_ln.addView(helper);
//                        dynamic_ln.addView(fitter);
//
//                        centringdatalist.add(helper);
//                        centringdatalist.add(fitter);
//                        break;
//
//                    case "Carpending":
//
//
//                         Carhelper = new EditText(Labor_activity_demo.this);
//                        Carhelper.setInputType(InputType.TYPE_CLASS_NUMBER);
//                        Carhelper.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                        carpenter = new EditText(Labor_activity_demo.this);
//                        carpenter.setInputType(InputType.TYPE_CLASS_NUMBER);
//                        carpenter.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//
//                        Carhelper.setHint("Helper");
//                        carpenter.setHint("Carpenter");
//
//                        dynamic_ln.addView(Carhelper);
//                        dynamic_ln.addView(carpenter);
//
//                        carpendingdatalist.add(Carhelper);
//                        carpendingdatalist.add(carpenter);
//                        break;
//                }
//
//
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // TODO Auto-generated method stub
//
//            }
//        });
//        ////////////////////////////////
///////////////////Spinner Sizes //////////
//        //Creating the ArrayAdapter instance having the bank name list
//        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,SizeNames);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        size_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                sizevalue=size_spinner.getSelectedItem().toString();
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        // Construct the data source

        // Create the adapter to convert the array to views

        // Attach the adapter to a ListView
        listView = (ListView) findViewById(R.id.main_list_view);


//Setting the ArrayAdapter data on the Spinner
//        size_spinner.setAdapter(aa);
        //This method will fetch the data from the URL
        getConstructor();

    }


////////////////////////////////////////////
    // Add Labor spinner data

    public void addListenerOnSpinnerItemSelection(){
        labor_spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }



    //////////////////// Date code
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            SimpleDateFormat dateParser = new SimpleDateFormat("dd-MM-yyyy",
                    Locale.ENGLISH);
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            m_sDate = dateParser.format(c.getTime()).toString();
            DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
            dialog.getDatePicker().setMaxDate(c.getTimeInMillis());

            return  dialog;
        }
        public void onDateSet(DatePicker view, int year, int month, int day) {
            datetxtview.setText(m_sDate);
        }
    }
/////////////
    @Override
    public void onClick(View v) {
        if (v==datetxtview)
        {
            DatePickerFragment mDatePicker = new DatePickerFragment();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                mDatePicker.show(getFragmentManager(), "Select date");
            }

        }
        else if (v==fab)
        {
            Intent intent = new Intent(Labor_activity_demo.this, Labor_activity_demo.class);
            startActivity(intent);
        }
        else if(v==attach_button)
        {

        }
        else if (v==submit_button)
        {
            GetSendDataAsyncTask getSendDataAsyncTask=new GetSendDataAsyncTask();
            getSendDataAsyncTask.execute("");
        }

    }

////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
    /////////////camera code//////////

    public void btnAddOnClick(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_box);
        dialog.setTitle("Dialog View");
        Button btnExit = (Button) dialog.findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.btnChoosePath).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                activeGallery();

                listView.setAdapter(imageAdapter);
            }
        });
        dialog.findViewById(R.id.btnTakePhoto).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                activeTakePhoto();

                listView.setAdapter(imageAdapter);
            }
        });

        // show dialog on screen
        dialog.show();
    }

    /**
     * take a photo
     */
    private void activeTakePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            String fileName = "temp.jpg";
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, fileName);
            mCapturedImageURI = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    /**
     * to gallery
     */
    private void activeGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULT_LOAD_IMAGE);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageAdapter.notifyDataSetChanged();
        switch (requestCode) {
            case RESULT_LOAD_IMAGE:
                if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    MyImage image = new MyImage();
//                    image.setTitle("Test");

//                    image.setDescription("test choose a photo from gallery and add it to list view");
//                    image.setDatetime(System.currentTimeMillis());
                    image.setPath(picturePath);
                    images.add(image);
                }
            case REQUEST_IMAGE_CAPTURE:
                if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK ) {
                    imageAdapter.notifyDataSetChanged();
                    String[] projection = {MediaStore.Images.Media.DATA};
                    Cursor cursor = managedQuery(mCapturedImageURI, projection, null, null, null);
                    int column_index_data = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    String picturePath = cursor.getString(column_index_data);
                    MyImage image = new MyImage();
//                    image.setTitle("Test");
//                    image.setDescription("test take a photo and add it to list view");
//                    image.setDatetime(System.currentTimeMillis());
                    image.setPath(picturePath);
                    images.add(image);
                }
        }
    }



    ////////////////////Spinner select Contractor/////////////////
    /**
     * Show progress dialog.
     */
    private void showProgressDialog() {
        Log.v(TAG, String.format("showProgressDialog"));
        progressDialog = new ProgressDialog(Labor_activity_demo.this, DialogInterface.BUTTON_POSITIVE);
        progressDialog.setMessage(getResources().getString(R.string.pleasewait));
        progressDialog.show();
    }

    /**
     * Dismiss the Progress dialog.
     */

    private void dismissProgressDialog() {
        Log.v(TAG, String.format("dismissProgressDialog"));
        progressDialog.dismiss();

    }
    private void getConstructor() {
        ConsrtuctorClass ulc = new ConsrtuctorClass();
        ulc.execute("5");
    }
    /**
     * Perform the asyncTask sends data to server and gets response.
     */

    class ConsrtuctorClass extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            Log.v(TAG, "onPreExecute");
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            Log.v(TAG, String.format("onPostExecute :: response = %s", response));
            dismissProgressDialog();
//            showToastMessage(response);
            addNewConstructor(response);
        }

        @Override
        protected String doInBackground(String... params) {
            Log.v(TAG, String.format("doInBackground ::  params= %s", params));

            HashMap<String, String> loginData = new HashMap<>();
//            loginData.put("user_id","");
            loginData.put("project_id","1");
            loginData.put(ServerClass.ACTION,"get_labor_constructor");
            ServerClass ruc = new ServerClass();
            String loginResult = ruc.sendPostRequest(APP_URL, loginData);

            Log.v(TAG, String.format("doInBackground :: loginResult= %s", loginResult));
            return loginResult;

        }

        /**
         * Server response Operations.
         */

        private void addNewConstructor(String jsonResponse) {


            if (jsonResponse != null) {


                try {
                    Log.v(TAG, "JsonResponseOpeartion :: try");
                    JSONObject object = new JSONObject(jsonResponse);
                    if (object != null) {
                        JSONArray jsonArrayResult = object.getJSONArray("result");
//Log.d("json", String.valueOf(jsonArrayResult));
                        if (jsonArrayResult != null && jsonArrayResult.length() > 0)

                            for (int i = 0; i < jsonArrayResult.length(); i++) {
                                constructor_model=new Constructor_Model();
                                Log.v(TAG, "JsonResponseOpeartion ::");
                                JSONObject jsonObj = jsonArrayResult.getJSONObject(i);
                                if (jsonObj != null) {

                                    String labor_cont_id = jsonObj.getString("labor_cont_id");

                                    String labor_cont_company = jsonObj.getString("labor_cont_company");


                                    constructor_model.setLabor_cont_company(labor_cont_company);


                                    constructor_modelArrayList.add(labor_cont_company);

                                }
                            }

                    }
                    adapter = new ArrayAdapter<String>(Labor_activity_demo.this,
                            android.R.layout.simple_spinner_dropdown_item, constructor_modelArrayList);
//                    constructor_spinner.setAdapter(adapter);
                }

                catch (JSONException e) {
                    Log.v(TAG, "JsonResponseOpeartion :: catch");
                    e.printStackTrace();
                }

            }

        }

        }



    public Boolean getSendData() {
        String url = "http://myclanservices.com/pratik/Washing_New/try.php/format/json";
        try {
            ServiceHandler servicehandler = new ServiceHandler();
            JSONObject jobject1 = new JSONObject();


            JSONArray arry = new JSONArray();

                JSONObject jobject = new JSONObject();


            if(labor_spinner.getSelectedItem().equals("Civil")){
                 values=mc.getText().toString()+","+fc.getText().toString()+","+sc.getText().toString()+","+mason.getText().toString();
            }
            else if (labor_spinner.getSelectedItem().equals("Centring"))
            {
                values=fitter.getText().toString()+","+helper.getText().toString();
            }
            else{
                values=Carhelper.getText().toString()+","+carpenter.getText().toString();
            }
                    jobject.put("report_title",reportedt.getText().toString() );
                    jobject.put("labor_date", datetxtview.getText().toString());
                    jobject.put("labour_cont_id", constrvalue);
                    jobject.put("labour_type", labor_spinner.getSelectedItem().toString());
                    jobject.put("labour_values", values);
                    jobject.put("switchvalue",switchvalue);
                    jobject.put("size_value",area_edt.getText().toString());
                    jobject.put("desc",desc_edt.getText().toString());
                    jobject.put("measure_value",sizevalue);
                    jobject.put("image", getStringImage(bitmap));
                    arry.put(jobject);

            jobject1.put("work", arry);

            List<NameValuePair> para = new ArrayList<NameValuePair>();
            para.add(new BasicNameValuePair("obj", jobject1.toString()));


            serverResponse = servicehandler.makeServiceCall1(url, ServiceHandler.POST
                    , jobject1.toString());
            if (serverResponse != null || serverResponse != "") {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            serverResponse = e.toString();
            return false;
        }
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imageBytes = baos.toByteArray();

        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    public class GetSendDataAsyncTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            System.gc();
            Runtime.getRuntime().gc();
            pDialog = new ProgressDialog(Labor_activity_demo.this);
            pDialog.setMessage("Please wait..... ");
            pDialog.setCancelable(false);
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                return getSendData();
            } catch (Exception e) {

                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
            System.gc();
            Runtime.getRuntime().gc();
            if (success) {
                try {
                    JSONObject jsonObject = new JSONObject(serverResponse);
                    if (jsonObject.has("")) {
                        Toast.makeText(getApplicationContext(),
                                jsonObject.optString("message"),
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                jsonObject.optString("message"),
                                Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getApplicationContext(),
                        // getString(R.string.alert_err),
                        "parsing Error.....", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onCancelled() {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
        }
    }

}
