package in.hedonist.com.booksharing.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.load.ImageHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import in.hedonist.com.booksharing.Helper.RequestHandler;
import in.hedonist.com.booksharing.Helper.SharedPrefManager;
import in.hedonist.com.booksharing.Helper.URLs;
import in.hedonist.com.booksharing.Helper.User;
import in.hedonist.com.booksharing.R;

public class RegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button login, register;
    ImageView imageView;
    EditText name, mobileno, email, password, repasseword;
    AdapterView spinnersemester,spinnercollege,spinnerdepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_registration );

        if (SharedPrefManager.getInstance( this ).isLoggedIn()) {
            finish();
            startActivity( new Intent( this, ProfileActivity.class ) );
            return;
        }

        spinnercollege=(AdapterView)findViewById( R.id.spinnercollege ) ;
        spinnersemester=(AdapterView)findViewById( R.id.spinnersemester );
        spinnerdepartment=(AdapterView)findViewById( R.id.spinnerdepartment );

        Spinner spinnersemester = (Spinner) findViewById( R.id.spinnersemester );
        Spinner spinnercollege = (Spinner) findViewById( R.id.spinnercollege );
        Spinner spinnerdepartment = (Spinner) findViewById( R.id.spinnerdepartment );
        // Creating ArrayAdapter using the string array and default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( this,
                R.array.Semester, android.R.layout.simple_spinner_item );
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource( this,
                R.array.College, android.R.layout.simple_spinner_item );
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource( this,
                R.array.Department, android.R.layout.simple_spinner_item );
        // Specify layout to be used when list of choices appears
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        // Applying the adapter to our spinner
        spinnersemester.setAdapter( adapter );
        spinnercollege.setAdapter( adapter1 );
        spinnerdepartment.setAdapter( adapter2 );

        imageView = (ImageView) findViewById( R.id.logoimage );

        name = (EditText) findViewById( R.id.fullname );
        mobileno = (EditText) findViewById( R.id.phone );
        email = (EditText) findViewById( R.id.email45 );
        password = (EditText) findViewById( R.id.password );
        repasseword = (EditText) findViewById( R.id.repassword );
        login = (Button) findViewById( R.id.btnlogin34 );
        register = (Button) findViewById( R.id.btnRegister34 );

        login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( RegistrationActivity.this, LoginActivity.class );
                startActivity( intent );
            }
        } );

        register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerUser();

            }
        } );


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void registerUser() {
        final String fullname = name.getText().toString().trim();
        final String email1 = email.getText().toString().trim();
        final String mobileno2 = mobileno.getText().toString().trim();
        final String Spinnercollege=spinnercollege.toString().trim();
        final String Spinnersemester=spinnersemester.toString().trim();
        final String Spinnerdepartment=spinnerdepartment.toString().trim();

        final String password2 = password.getText().toString().trim();


        if (TextUtils.isEmpty(fullname)) {
            name.setError("Please enter fullname");
            name.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email1)) {
            email.setError("Please enter your email");
            email.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(mobileno2)) {
            mobileno.setError("Please enter username");
            mobileno.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Spinnercollege)) {
          //  spinnercollege.setError("Please enter your email");
            spinnercollege.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Spinnersemester)) {
            //  spinnercollege.setError("Please enter your email");
            spinnersemester.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Spinnerdepartment)) {
            //  spinnercollege.setError("Please enter your email");
            spinnerdepartment.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
            email.setError("Enter a valid email");
            email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password2)) {
            password.setError("Enter a password");
            password.requestFocus();
            return;
        }

        //if it passes all the validations

        class RegisterUser extends AsyncTask<Void, Void, String> {

            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("username", fullname);
                params.put("email", email1);
                params.put("password", password2);
                params.put("mobileno", mobileno2);
                params.put("spinnercollege", Spinnercollege);
                params.put("spinnersemester", Spinnersemester);
                params.put("Spinnerdepartment", Spinnerdepartment);


                //returing the response
                return requestHandler.sendPostRequest( URLs.URL_REGISTER, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
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
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        //executing the async task
        RegisterUser ru = new RegisterUser();
        ru.execute();

    }
}
