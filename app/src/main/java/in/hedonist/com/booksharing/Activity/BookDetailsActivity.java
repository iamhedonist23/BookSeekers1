package in.hedonist.com.booksharing.Activity;

import android.content.Intent;
import android.media.Image;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import in.hedonist.com.booksharing.R;

public class BookDetailsActivity extends AppCompatActivity {

    TextView book_name,book_name1,author,author1,semester,semester1,edition,edition1,category,category1,price,price1;
    ImageView book_img;
    String getsemester,getName,getauthor,getedition,getcategory,getprice;
    Intent intent;
    Button chatbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_book_details );

        book_img=(ImageView)findViewById( R.id.bookimage ) ;
        book_name1=(TextView)findViewById( R.id.bookname1 );
        author1=(TextView)findViewById( R.id.author1);
        semester1=(TextView)findViewById( R.id.semester1 );
        edition1=(TextView)findViewById( R.id.edition1 );
        category1=(TextView)findViewById( R.id.category1 );
        price1=(TextView)findViewById( R.id.price1 );

        chatbtn=(Button)findViewById( R.id.chatbtn );

        chatbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( BookDetailsActivity.this, MessengerActivity.class );
                startActivity( intent );
            }
        } );


//        intent = getIntent();
////get the attached extras from the intent
////we should use the same key as we used to attach the data.
//        String book_name = intent.getStringExtra("book_name");


        intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
             //getName = (String) bd.get( String.valueOf( book_name ) );
           //  getauthor = ;

            book_name1.setText((String) bd.get("book_name")  );
            author1.setText((String) bd.get("author"));
            semester1.setText((String) bd.get("semester")  );
            edition1.setText((String) bd.get("edition")  );
            price1.setText((String) bd.get("price")  );
            category1.setText((String) bd.get("category")  );

            Glide.with(this)
                    .load(bd.get("book_img_url"))
                    .into(book_img);
          //  book_img.setText((String) bd.get("book_img")  );
            //getsemester = (String) bd.get( String.valueOf( semester ) );
             //getedition = (String) bd.get( String.valueOf( edition ) );
             //getcategory = (String) bd.get( String.valueOf( category ) );
             //getprice = (String) bd.get( String.valueOf( price ) );



        }

       // book_name1.setText(getName);
//        author1.setText( getauthor );
//        semester1.setText(getsemester);
//        edition1.setText( getedition );
//        category1.setText(getcategory);
//        price1.setText( getprice );

//Glide.



    }
}
