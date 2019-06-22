package in.hedonist.com.booksharing.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import in.hedonist.com.booksharing.Helper.RequestHandler;
import in.hedonist.com.booksharing.Helper.SharedPrefManager;
import in.hedonist.com.booksharing.Helper.URLs;
import in.hedonist.com.booksharing.Helper.User;
import in.hedonist.com.booksharing.R;

public class LoginActivity extends AppCompatActivity {

    ImageView imageView;
    EditText editname,editpassword;
    Button login,regitration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        imageView =(ImageView)findViewById( R.id. logoimage);
        editname=(EditText)findViewById( R.id.editName );
        editpassword=(EditText)findViewById( R.id.editPassword );

        login=(Button)findViewById( R.id.btnSignIn );
        regitration=(Button)findViewById( R.id.btnRegister );

        login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( LoginActivity.this,SplashScreen.class );
                startActivity( intent );

                userLogin();

            }
        } );

        regitration.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( LoginActivity.this,RegistrationActivity.class );
                startActivity( intent );
            }
        } );
    }

   private void userLogin() {
        //first getting the values
        final String username = editname.getText().toString();
        final String password = editpassword.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(username)) {
            editname.setError("Please enter your email");
            editname.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editpassword.setError("Please enter your password");
            editpassword.requestFocus();
            return;
        }

        //if everything is fine

        class UserLogin extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressBar.setVisibility(View.GONE);


                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("user");

                        //creating a new user object
                        User user = new User(
                                userJson.getInt("id"),
                                userJson.getString("fullname"),
                                userJson.getString("email"),
                                userJson.getString("mobileno"),
                                userJson.getString("spinnercollege"),
                                userJson.getString("spinnersemester"),
                                userJson.getString("spinnerdepartment")
                        );

                        //storing the user in shared preferences
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                        //starting the profile activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), SplashScreen.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);

                //returing the response
                return requestHandler.sendPostRequest( URLs.URL_LOGIN, params);
            }
        }

        UserLogin ul = new UserLogin();
        ul.execute();
    }
}