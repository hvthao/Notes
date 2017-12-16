package net.hvthao.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends Activity {
    private static final String EXTRA_TITLE = "Title";
    private EditText editTextTitle;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(EXTRA_TITLE, editTextTitle.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        String title = getIntent().getStringExtra(EXTRA_TITLE);
        this.editTextTitle = (EditText) findViewById(R.id.edittextTitle);
        this.editTextTitle.setText(title);
    }
}
