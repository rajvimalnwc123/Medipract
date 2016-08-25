package com.example.nwcemp01.medipract;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;

import java.util.ArrayList;
import java.util.UUID;

import Api.booklaterdoctorApi;
import Api.doctorapi;
import Api.doctordata;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import utils.Function;


public class BooklaterDoctor extends Activity {


    doctordata categorydetaill;
    ArrayList<doctordata> CategoryArryListt = new ArrayList<>();

    Button vNext;
    CustomProgressDialog oCustomProgressDialog;

    ArrayList<String> getcategoryimg;

    //  String categoryid,categoryimg;

    int doctorId;
    public static SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    public static SharedPreferences.Editor editor;
    public static final String DoctorId = "doctorid";
    public static final String HospitalId = "hospitalid";
    public static final String SpecialId = "specialId";
    public static final String Dateselect = "dateselect";

    public static String mDateselect_sharedPref, mSpecialpref, mHospital_sharedpref, mDoctorpref;
    ImageButton iv_leftarrow, iv_rightarrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following);
      /*  mToolbars = (Toolbar) findViewById(R.id.tb_header_view);
        mToolTitle = (TextView) findViewById(R.id.tv_toolbar_title);
        mToolTitle.setText(iv_label);
*/
        TextView title = (TextView) findViewById(R.id.titleid);
        title.setText("Doctor");
        vNext = (Button) findViewById(R.id.AF_BT_next);
        ImageButton back_imgbtn = (ImageButton) findViewById(R.id.back_imgbtn);

        iv_leftarrow = (ImageButton) findViewById(R.id.arrowLeft);
        iv_rightarrow = (ImageButton) findViewById(R.id.arrowRight);

        sharedpreferences = getApplicationContext().getSharedPreferences(
                MyPREFERENCES, MODE_PRIVATE);
        editor = sharedpreferences.edit();
        mHospital_sharedpref = sharedpreferences.getString(HospitalId, null);
        mSpecialpref = sharedpreferences.getString(SpecialId, null);
        mDateselect_sharedPref = sharedpreferences.getString(Dateselect, null);
        Log.v("Restal", "" + mHospital_sharedpref);
        boolean network = Function.isNetworkAvailable(BooklaterDoctor.this);
        if (network == false) {
            Function.showToast(BooklaterDoctor.this, "Please activate the network", Toast.LENGTH_LONG);
        } else {
            oCustomProgressDialog = CustomProgressDialog.getInstance();
            oCustomProgressDialog.show(BooklaterDoctor.this, "", "please wait");

        /*    CategoryApi.getInstance().Callresponse(new Callback<CategoryApi.CategoryBaseResponse>() {
                @Override
                public void success(CategoryApi.CategoryBaseResponse categoryBaseResponse, Response response) {

                    for (int i = 0; i < categoryBaseResponse.getCategory_details().size(); i++) {
                        categorydetail = new Categorydetail();
                        oCustomProgressDialog.dismiss();
                        categorydetail.setCategory_id(categoryBaseResponse.getCategory_details().get(i).getCategory_id());
                        categorydetail.setCategory_name(categoryBaseResponse.getCategory_details().get(i).getCategory_name());
                        categorydetail.setCategory_image(categoryBaseResponse.getCategory_details().get(i).getCategory_image());
                        CategoryArryList.add(categorydetail);

                    }

                    dynamicViewCategoryItems();
                }
*/

            booklaterdoctorApi.getInstance().

                    Callresponse(new Callback<booklaterdoctorApi.ResponseBasebooklaterdoctor>() {
                                     @Override
                                     public void success(booklaterdoctorApi.ResponseBasebooklaterdoctor
                                                                 responseBasebooklaterdoctor, Response response) {
                                         oCustomProgressDialog.dismiss();

                                         Toast.makeText(getApplicationContext(), responseBasebooklaterdoctor.getMessage(), Toast.LENGTH_SHORT).show();

                                         if (responseBasebooklaterdoctor.getData().size() > 0) {
                                             iv_leftarrow.setVisibility(View.VISIBLE);
                                             iv_rightarrow.setVisibility(View.VISIBLE);


                                             for (int j = 0; j < responseBasebooklaterdoctor.getData().size(); j++) {
                                                 //  String mCategory = responseBasespecialization.getData().get(j).getSpeacialist_name();
                                                 categorydetaill = new doctordata();
                                                 oCustomProgressDialog.dismiss();
                                                 categorydetaill.setName(responseBasebooklaterdoctor.getData().get(j).getName());
                                                 categorydetaill.setImage(responseBasebooklaterdoctor.getData().get(j).getImage());
                                                 categorydetaill.setId(responseBasebooklaterdoctor.getData().get(j).getId());
                                                 CategoryArryListt.add(categorydetaill);

                                                 Log.v("Res mcategoryname", "" + CategoryArryListt.size());

                                             }
                                         } else {
                                             iv_leftarrow.setVisibility(View.INVISIBLE);
                                             iv_rightarrow.setVisibility(View.INVISIBLE);
                                         }
                                         dynamicViewCategoryItems();
                                     }

                                     @Override
                                     public void failure(RetrofitError error) {
                                         Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                                     }
                                 }
                            , mHospital_sharedpref, mSpecialpref, mDateselect_sharedPref);


        }
    }


    private void dynamicViewCategoryItems() {


        //    ScrollView scroll = (ScrollView) findViewById(R.id.scrollView);

        LinearLayout llChid = null;
        LinearLayout lParent = (LinearLayout) findViewById(R.id.ll_parent);
        lParent.setGravity(Gravity.CENTER);
        LayoutInflater mInflater = null;
        mInflater = (LayoutInflater) getApplication().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        lParent.setOrientation(LinearLayout.HORIZONTAL);
        //    Log.d("Size111", "" + CategoryArryList.size());
        double mCategorySize = (float) CategoryArryListt.size() / 2;
        //  double mCategorySize = Math.ceil(a);
        //  Log.d("Size", "" + mCategorySize);
        ///    scroll.addView(lParent);

        for (int i = 0; i < mCategorySize; i++) {
            llChid = new LinearLayout(getApplication());
            llChid.setOrientation(LinearLayout.VERTICAL);
            llChid.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            llChid.setGravity(Gravity.CENTER);

            for (int j = 0; j < 2; j++) {

                int z = j + (i * 2);

                View v = mInflater.inflate(R.layout.activity_gridcontent, null);

                v.setId(100 + z);

                ImageView mCategoryImage = (ImageView) v.findViewById(R.id.AGC_categoryimage);
                final TextView mCategoryName = (TextView) v.findViewById(R.id.AGC_categoryname);
                mCategoryImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        editor.putString(SpecialId, String.valueOf(doctorId));
                        editor.commit();

                        mDoctorpref = sharedpreferences.getString(DoctorId, null);


                        Intent intent = new Intent(BooklaterDoctor
                                .this, Submitform.class);
                        startActivity(intent);
                    }
                });


                llChid.addView(v);
                if (CategoryArryListt.size() > z) {
                    mCategoryImage.setOnClickListener(new OnclickDoctor(z));
                    categorydetaill = CategoryArryListt.get(z);
                    final String categoryid = CategoryArryListt.get(z).getName();
                    final String categoryimg = CategoryArryListt.get(z).getImage();
                    //     Log.v("IMageurl",""+categoryimg);
                    doctorId = CategoryArryListt.get(z).getId();
                    //  final String categoryid = CategoryArryList.get(z).getCategory_id();
                    //final String categoryimg = CategoryArryList.get(z).getCategory_image();
                    /// Log.v("CATEGORYID", categoryid);
                    Glide.with(getApplicationContext()).load(categoryimg).placeholder(R.drawable.ic_loading).signature(new StringSignature(UUID.randomUUID().toString())).into(mCategoryImage);
                    mCategoryName.setText(categoryid);
                    String categoryNames = categorydetaill.getName();

                    //    String categoryId = categorydetaill.getSpeacialist_id();
                    int index = categoryNames.indexOf("&amp;");
                    if (index != -1) {
                        String firstChar = categoryNames.substring(0, index);
                        String secondChar = categoryNames.substring(index + 5);
                        categoryNames = firstChar + "&" + "\n" + secondChar;
                    }
                    //   mCategoryName.setTag(categoryNames);
                    mCategoryName.setText(categoryNames);

                    final int pos = CategoryArryListt.size();

                } else {
                    z--;
                    categorydetaill = CategoryArryListt.get(z);
                    Glide.with(getApplicationContext()).load(categorydetaill.getImage()).placeholder(R.drawable.ic_loading).signature(new StringSignature(UUID.randomUUID().toString())).into(mCategoryImage);
                    mCategoryName.setText(categorydetaill.getName());

                    v.setVisibility(View.INVISIBLE);
                }
            }
            lParent.addView(llChid);


        }
    }


    @Override
    public void onBackPressed() {
        Intent newIntent = new Intent(BooklaterDoctor.this, BooklaterSpecialization.class);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(newIntent); // TODO Auto-generated method stub
        super.onBackPressed();
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_following, menu);
        return true;
    }
*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    private class OnclickDoctor implements View.OnClickListener {

        int pos;

        public OnclickDoctor(int position) {
            this.pos = position;


        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.AGC_categoryimage) {
                int sDoctorid = CategoryArryListt.get(pos).getId();

                editor.putString(DoctorId, String.valueOf(sDoctorid));
                editor.commit();

                mDoctorpref = sharedpreferences.getString(DoctorId, null);
                mHospital_sharedpref = sharedpreferences.getString(HospitalId, null);

                Intent intent = new Intent(BooklaterDoctor
                        .this, BooklaterSubmitform.class);
                startActivity(intent);


            }
        }
    }
}
