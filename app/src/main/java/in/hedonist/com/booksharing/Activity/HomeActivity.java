package in.hedonist.com.booksharing.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.load.engine.bitmap_recycle.IntegerArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import in.hedonist.com.booksharing.BookAdapter;
import in.hedonist.com.booksharing.Helper.DataAdapter;
import in.hedonist.com.booksharing.Helper.RecyclerViewAdapter;
import in.hedonist.com.booksharing.Helper.ViewPagerAdapter;
import in.hedonist.com.booksharing.R;

public class HomeActivity extends AppCompatActivity   {
    List<DataAdapter> ListOfdataAdapter;
    RecyclerView recyclerView;
    ViewPager viewPager;

    String HTTP_JSON_URL =  "https://bookseekers.000webhostapp.com/json.php";
   // String HTTP_JSON_URL= "https://api.myjson.com/bins/afji0";
    String book_name = "book_name";
    String ID="ID";
    String author_name = "author_name";
    String price="price";
    String book_img = "book_img";
    String id ="id";
    static int countpositon=0;
    String category="category";
    String semester="semester";
    String edition ="edition";
   // BookAdapter bookAdapter;
    JsonArrayRequest RequestOfJSonArray ;
    RequestQueue requestQueue ;
    private ProgressBar progressBar;
    View view ;
    int RecyclerViewItemPosition ;
    RecyclerView.LayoutManager layoutManagerOfrecyclerView;
    RecyclerView.Adapter recyclerViewadapter;
    ArrayList<String> ImageTitleNameArrayListForClick;
    FloatingActionButton searchitm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        searchitm=(FloatingActionButton)findViewById( R.id.search_item );
        searchitm.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( HomeActivity.this,SearchActivity.class );
                startActivity( intent );
            }
        } );


        recyclerView =(RecyclerView)findViewById( R.id.scrollableview );
        ImageTitleNameArrayListForClick = new ArrayList<>();
        ListOfdataAdapter = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.scrollableview);
        recyclerView.setHasFixedSize(true);
        layoutManagerOfrecyclerView = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManagerOfrecyclerView);
        JSON_HTTP_CALL();
        // Implementing Click Listener on RecyclerView.
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(HomeActivity.this, new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

                view = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                countpositon=0;
                if(view != null && gestureDetector.onTouchEvent(motionEvent)) {

                    //Getting RecyclerView Clicked Item value.
                    RecyclerViewItemPosition = Recyclerview.getChildAdapterPosition(view);

                    // Showing RecyclerView Clicked Item value using Toast.
                    // Toast.makeText(MainActivity.this, ImageTitleNameArrayListForClick.get(RecyclerViewItemPosition), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent( HomeActivity.this,BookDetailsActivity.class );


                    Iterator ite=ListOfdataAdapter.iterator();
                    System.out.println("Remove items from list");

                    while(ite.hasNext()) {

                        DataAdapter element = (DataAdapter) ite.next();

                        if(countpositon==RecyclerViewItemPosition) {


                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                          // intent.putExtra("id", element.getId());
                           intent.putExtra("book_name", element.getImageTitle());
                          intent.putExtra("author",element.getAuthor_name());
                          intent.putExtra("semester", element.getSemister());
                          intent.putExtra("category", element.getCategory());
                        intent.putExtra("edition", element.getEdition());
                           intent.putExtra("price", element.getPrice());
                          intent.putExtra("book_img_url", element.getImageUrl());

                           startActivity( intent );

                           break;
                          // finish();

                       }
                        countpositon++;

                    }  //

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


    }

    public void JSON_HTTP_CALL(){

        RequestOfJSonArray = new JsonArrayRequest(HTTP_JSON_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        ParseJSonResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(HomeActivity.this);

        requestQueue.add(RequestOfJSonArray);
    }

    public void ParseJSonResponse(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            DataAdapter GetDataAdapter2 = new DataAdapter();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);

                GetDataAdapter2.setImageTitle(json.getString(book_name));
               GetDataAdapter2.setAuthor_name(json.getString(author_name));
//              //  GetDataAdapter2.setId(json.getString("book_id"));
            GetDataAdapter2.setSemister(json.getString(semester));
////                GetDataAdapter2.setAuthor_name(json.getString("author_name"));
              GetDataAdapter2.setPrice(json.getString("price"));
               GetDataAdapter2.setCategory(json.getString("category"));
               GetDataAdapter2.setEdition(json.getString("edition"));
////                GetDataAdapter2.setSemister(json.getString("semester"));
//               GetDataAdapter2.setImageUrl(json.getString("book_img_url"));


                // Adding image title name in array to display on RecyclerView click event.
               // ImageTitleNameArrayListForClick.add(json.getString(book_name));


                GetDataAdapter2.setImageUrl(json.getString(book_img));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            ListOfdataAdapter.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerViewAdapter(ListOfdataAdapter, this);

        recyclerView.setAdapter(recyclerViewadapter);

    }
}