package com.example.nwcemp01.medipract;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;

import java.util.ArrayList;
import java.util.UUID;

import Api.specializationapi;
import Api.specializationdata;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import utils.Function;

/**
 * Created by nwcemp01 on 26/7/16.
 */
public class BooklaterSpecialization extends Activity {

    int maxScrollX;
    /*  Toolbar mToolbars;
      TextView mToolTitle;
      GridView vGridfollowing;
      int flager = 0;
      int flag = 0;
      HashMap<String, String> getCount = new HashMap<String, String>();
      HashMap<String, String> mapdata = new HashMap<String, String>();

      ArrayList<HashMap<String, String>> arlist = new ArrayList<HashMap<String, String>>();
      public static final String category_name = "mCategory_name";
      public static final String category_image = "mCategory_imges";
      public static String mCategory_name, mCategory_id, mCategory_status,*/
    specializationdata categorydetaill;
    ArrayList<specializationdata> CategoryArryListt = new ArrayList<>();

    Button vNext;
    CustomProgressDialog oCustomProgressDialog;
    int specializationid;
    ArrayList<String> getcategoryimg;

    //  String categoryid,categoryimg;


    public static SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    public static SharedPreferences.Editor editor;
    public static final String EmailId = "emailId";
    public static final String HospitalId = "hospitalid";
    public static final String SpecialId = "specialId";
    public static String mEmail_sharedPref, mHospital_sharedpref, mSpecialpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following);
      /*  mToolbars = (Toolbar) findViewById(R.id.tb_header_view);
        mToolTitle = (TextView) findViewById(R.id.tv_toolbar_title);
        mToolTitle.setText(iv_label);*/
        TextView title = (TextView) findViewById(R.id.titleid);
        title.setText("Specialization");
        vNext = (Button) findViewById(R.id.AF_BT_next);
        ImageButton back_imgbtn = (ImageButton) findViewById(R.id.back_imgbtn);


        sharedpreferences = getApplicationContext().getSharedPreferences(
                MyPREFERENCES, MODE_PRIVATE);
        editor = sharedpreferences.edit();
        mHospital_sharedpref = sharedpreferences.getString(HospitalId, null);

        Log.v("Restal", "" + mHospital_sharedpref);
        boolean network = Function.isNetworkAvailable(BooklaterSpecialization.this);
        if (network == false) {
            Function.showToast(BooklaterSpecialization.this, "Please activate the network", Toast.LENGTH_LONG);
        } else {
            oCustomProgressDialog = CustomProgressDialog.getInstance();
            oCustomProgressDialog.show(BooklaterSpecialization.this, "", "please wait");
/*

            CategoryApi.getInstance().Callresponse(new Callback<CategoryApi.CategoryBaseResponse>() {
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

            specializationapi.getInstance().Callresponse(new Callback<specializationapi.ResponseBasespecialization>() {
                @Override
                public void success(specializationapi.ResponseBasespecialization responseBasespecialization, Response response) {
                    oCustomProgressDialog.dismiss();
                    //   Log.v("Submit form",responseBasespecialization.getMessage());

                    //  Toast.makeText(getApplicationContext(),responseBasespecialization.getMessage().getSuccess(),Toast.LENGTH_SHORT).show();
                    if (responseBasespecialization.getData().size() == 0) {
                        //   Toast.makeText(Category.this, responseBasespecialization.getMessage().getSuccess(), Toast.LENGTH_SHORT).show();
                    } else {
                        //   Toast.makeText(Category.this, responseBasespecialization.getMessage().getSuccess(), Toast.LENGTH_SHORT).show();

                        //     if(responseBasespecialization.getData().size()>0){
                        for (int j = 0; j < responseBasespecialization.getData().size(); j++) {
                            //  String mCategory = responseBasespecialization.getData().get(j).getSpeacialist_name();
                            categorydetaill = new specializationdata();
                            oCustomProgressDialog.dismiss();
                            categorydetaill.setSpecialist_name(responseBasespecialization.getData().get(j).getSpecialist_name());
                            categorydetaill.setImage(responseBasespecialization.getData().get(j).getImage());
                            categorydetaill.setSpecialist_id(responseBasespecialization.getData().get(j).getSpecialist_id());
                            CategoryArryListt.add(categorydetaill);
                        }

                    }
                    //  }

                    dynamicViewCategoryItems();
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(BooklaterSpecialization.this, error.getMessage(), Toast.LENGTH_SHORT).show();


                    oCustomProgressDialog.dismiss();
                }
            }, mHospital_sharedpref);

        }
    }


    private void dynamicViewCategoryItems() {


        final HorizontalScrollView hs = (HorizontalScrollView) findViewById(R.id.scrollView);

        ViewTreeObserver vto = hs.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                hs.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                maxScrollX = hs.getChildAt(0)
                        .getMeasuredWidth() - getWindowManager().getDefaultDisplay().getWidth();

            }
        });
        ImageButton homeMenu = (ImageButton) findViewById(R.id.arrowLeft);
        ImageButton homeMenu1 = (ImageButton) findViewById(R.id.arrowRight);
        homeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hs.scrollTo((int) hs.getScrollX() - 1000, (int) hs.getScrollY());
            }
        });


        homeMenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hs.scrollTo((int) hs.getScrollX() + 1000, (int) hs.getScrollY());


            }
        });

       /*


        if (homeMenu.getScrollX()==0) {
            hideLeftArrow();
        } else {
            showLeftArrow();
        }
        if (homeMenu.getDrawingRect().right == homeMenu.getMeasuredWidth()) {
            hideRightArrow();
        } else {
            showRightArrow();
        }*/
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

                llChid.addView(v);
                if (CategoryArryListt.size() > z) {
                    mCategoryImage.setOnClickListener(new OnclickSpeciality(z));

                 /*   mCategoryImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            editor.putString(SpecialId, String.valueOf(specializationid));


                            Log.v("speciaid",String.valueOf(specializationid));
                            editor.commit();

                            mSpecialpref = sharedpreferences.getString(SpecialId, null);


                            Intent intent = new Intent(Category.this, Doctor.class);
                            startActivity(intent);
                        }
                    });
*/

                    categorydetaill = CategoryArryListt.get(z);
                    specializationid = CategoryArryListt.get(z).getSpecialist_id();
                    final String categoryid = CategoryArryListt.get(z).getSpecialist_name();
                    final String categoryimg = CategoryArryListt.get(z).getImage();
                    //     Log.v("IMageurl",""+categoryimg);

                    //  final String categoryid = CategoryArryList.get(z).getCategory_id();
                    //final String categoryimg = CategoryArryList.get(z).getCategory_image();
                    /// Log.v("CATEGORYID", categoryid);
                    Glide.with(getApplicationContext()).load(categoryimg).placeholder(R.drawable.ic_loading).signature(new StringSignature(UUID.randomUUID().toString())).into(mCategoryImage);
                    mCategoryName.setText(categoryid);
                    String categoryNames = categorydetaill.getSpecialist_name();

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
                    mCategoryName.setText(categorydetaill.getSpecialist_name());

                    v.setVisibility(View.INVISIBLE);
                }
            }
            lParent.addView(llChid);


        }
    }

    /*private void getDynamicSpecialization() {
        LinearLayout llChid = null;
        LinearLayout lParent = (LinearLayout) findViewById(R.id.ll_parent);
        lParent.setGravity(Gravity.CENTER);

        mInflater = (LayoutInflater) getApplication().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //    Log.d("Size111", "" + CategoryArryList.size());
        double a = (float) CategoryArryList.size() / 2;
        double mCategorySize = Math.ceil(a);
        //    Log.d("Size", "" + mCategorySize);


        for (int i = 0; i < mCategorySize; i++) {
            llChid = new LinearLayout(getApplication());
            llChid.setOrientation(LinearLayout.HORIZONTAL);
            llChid.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            llChid.setGravity(Gravity.CENTER);

            for (int j = 0; j < 2; j++) {

                int z = j + (i * 2);

                v = mInflater.inflate(R.layout.activity_gridcontent, null);

                v.setId(100 + z);

                ImageView mCategoryImage = (ImageView) v.findViewById(R.id.RVCI_IV_category_image);
                final TextView mCategoryName = (TextView) v.findViewById(R.id.RVCI_TV_category_name);


                llChid.addView(v);
                if (CategoryArryList.size() > z) {

                    categorydetail = CategoryArryList.get(z);

                    final String categoryid = CategoryArryList.get(z).getSpeacialist_name();
                    final String categoryimg = CategoryArryList.get(z).getImageurl();
                    /// Log.v("CATEGORYID", categoryid);

                    mCategoryName.setText(categoryid);
                    Glide.with(getApplicationContext()).load(categoryimg).signature(new StringSignature(UUID.randomUUID().toString())).into(mCategoryImage);
                }
            }lParent.addView(llChid);

        }
    }
*/
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(BooklaterSpecialization.this, CalendarFragment.class);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /* @Override
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

    /*    //noinspection SimplifiableIfStatement
        if (id == R.id.nextbtn) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    private class OnclickSpeciality implements View.OnClickListener {
        int pos;

        public OnclickSpeciality(int position) {

            this.pos = position;

        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.AGC_categoryimage) {

                int sSpecialityid = CategoryArryListt.get(pos).getSpecialist_id();
                Log.v("speciaid", "" + CategoryArryListt.get(pos).getSpecialist_name());

                editor.putString(SpecialId, String.valueOf(sSpecialityid));


                editor.commit();

                mSpecialpref = sharedpreferences.getString(SpecialId, null);


                Intent intent = new Intent(BooklaterSpecialization.this, BooklaterDoctor.class);
                startActivity(intent);

            }
        }
    }
}
