package es.uniovi.imovil.asignaturasconarquitectura;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NewCourseActivity extends AppCompatActivity implements Button.OnClickListener {

    public static final String NAME_REPLY = "es.uniovi.imovil.asignaturasconarquitectura.name";
    public static final String TEACHER_REPLY = "es.uniovi.imovil.asignaturasconarquitectura.teacher";
    public static final String DESC_REPLY = "es.uniovi.imovil.asignaturasconarquitectura.description";

    TextInputEditText name, teacher, description;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);
        name = findViewById(R.id.edit_name);
        teacher = findViewById(R.id.edit_teacher);
        description = findViewById(R.id.edit_description);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent replyIntent = new Intent();
        if (TextUtils.isEmpty(name.getText())) {
            warnEmpty();
            setResult(RESULT_CANCELED, replyIntent);
        }
        else {
            Bundle bundle = new Bundle();
            bundle.putString(NAME_REPLY, name.getText().toString());
            bundle.putString(TEACHER_REPLY, teacher.getText().toString());
            bundle.putString(DESC_REPLY,description.getText().toString());
            replyIntent.putExtras(bundle);
            setResult(RESULT_OK, replyIntent);
        }
        finish();

    }

    private void warnEmpty() {
        Toast.makeText(this, "El nombre de la asignatura no puede estar vacio", Toast.LENGTH_LONG).show();
    }
}
