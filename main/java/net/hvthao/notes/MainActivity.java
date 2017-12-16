package net.hvthao.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private static final int ACTIVITY_NEW_NOTE = 1;
    private static final int ACTIVITY_EDIT_NOTE = 2;
    private static final String EXTRA_TITLE = "Title";

    private List<String> noteList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private int editPosition;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_note:
                Intent intent = new Intent(this, NoteActivity.class);
                this.editPosition = -1;
                startActivityForResult(intent, ACTIVITY_NEW_NOTE);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void makeNoteSamples() {
        for(int i = 0; i < 20; i++)
            this.noteList.add("Note " + (i+1));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeNoteSamples();
        this.adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, this.noteList);


        ListView listviewNotes = (ListView) findViewById(R.id.listviewNotes);
        listviewNotes.setAdapter(adapter);

        listviewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editPosition = position;
                Intent intent = new Intent(getApplicationContext(),
                        NoteActivity.class);
                intent.putExtra(EXTRA_TITLE, noteList.get(position));
                startActivityForResult(intent, ACTIVITY_EDIT_NOTE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == ACTIVITY_NEW_NOTE) {
            if (resultCode == RESULT_OK) {
                String title = data.getStringExtra(EXTRA_TITLE);
                this.noteList.add(title);
                this.adapter.notifyDataSetChanged();
            }
        } else if (requestCode == ACTIVITY_EDIT_NOTE) {
            if (resultCode == RESULT_OK) {
                String title = data.getStringExtra(EXTRA_TITLE);
                this.noteList.set(this.editPosition, title);
                this.adapter.notifyDataSetChanged();
            }
        }
    }
}
