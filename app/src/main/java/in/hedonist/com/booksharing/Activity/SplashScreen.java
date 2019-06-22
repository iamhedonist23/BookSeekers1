package in.hedonist.com.booksharing.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import in.hedonist.com.booksharing.R;

public class SplashScreen extends AppCompatActivity {

    ImageView bgapp, clover,home,upload,profile,search;
    LinearLayout textsplash, texthome, menus;
    Animation frombottom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash_screen );

        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);


        bgapp = (ImageView) findViewById(R.id.bgapp);
        clover = (ImageView) findViewById(R.id.clover);
        textsplash = (LinearLayout) findViewById(R.id.textsplash);
        texthome = (LinearLayout) findViewById(R.id.texthome);
        menus = (LinearLayout) findViewById(R.id.menus);

        home=(ImageView)findViewById( R.id.home );
        upload=(ImageView)findViewById( R.id.upload );
        search=(ImageView)findViewById( R.id.search );
        //   profile=(ImageView)findViewById( R.id.profile );

        bgapp.animate().translationY(-1900).setDuration(800).setStartDelay(300);
        clover.animate().alpha(0).setDuration(800).setStartDelay(600);
        textsplash.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(300);

        texthome.startAnimation(frombottom);
        menus.startAnimation(frombottom);


        home.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent( SplashScreen.this,HomeActivity.class );
                startActivity( intent );

            }
        } );

        upload.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent( SplashScreen.this,UploadActivity.class );
                startActivity( intent );

            }
        } );


//        search.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//            }
//        } );

//
//        profile.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent= new Intent( SplashScreen.this,ProfileActivity.class );
//                startActivity( intent );
//
//            }
//        } );
    }
}
