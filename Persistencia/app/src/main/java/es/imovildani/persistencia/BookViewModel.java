package es.imovildani.persistencia;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BookViewModel extends AndroidViewModel {
    List<Book> bookList = new ArrayList<>(0);

    Context context;

    String filename = "fichero.txt";
    String Storage = "external";

    File file ;//= new File(context.getFilesDir(), filename);
    private static final String FOLDER_NAME = "Archivos_Persistencia";

    public BookViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }


    public int getListSize() {
        return this.bookList.size();
    }


    public Book getBook(int position) {
        return this.bookList.get(position);
    }

    public void addBook(Book book) {
        this.bookList.add(book);
    }



    /*
    // ALMACENAMIENTO INTERNO

    public void restoreBooksList() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        file = new File(context.getFilesDir(), FILENAME);
        if (file.exists()) {
            try{
                fis = context.openFileInput(file.getName());
                ois = new ObjectInputStream(fis);
                bookList = (List<Book>) ois.readObject();

                ois.close();
                fis.close();
            }catch(IOException e){
                Toast.makeText(getApplication().getApplicationContext(), "Se ha producido un error con el archivo", Toast.LENGTH_SHORT).show();
            }catch(ClassNotFoundException e){
                Toast.makeText(getApplication().getApplicationContext(), "Se ha producido un error con 'ois.readObject()'", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void saveBooksList() {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        file = new File(context.getFilesDir(), FILENAME);
        if (file.exists()) {
            try{
                fos = context.openFileOutput(file.getName(), Context.MODE_PRIVATE);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(bookList);

                oos.close();
                fos.close();
            }catch(IOException e){
                Toast.makeText(getApplication().getApplicationContext(), "Se ha producido un error con el archivo", Toast.LENGTH_SHORT).show();
            }
        }
    }
*/

    // ALMACENAMIENTO EXTERNO

    public void restoreBooksList() {

        String state = Environment.getExternalStorageState();

        if (state.equals(Environment.MEDIA_MOUNTED) ||
                state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            // Available to at least read


            FileInputStream fis = null;
            ObjectInputStream ois = null;

            if(Storage.equals("external"))
            {
                File sd = Environment.getExternalStorageDirectory(); // getting phone SD card path
                String backupPath = sd.getAbsolutePath() + FOLDER_NAME; // if you want to set backup in specific folder name
                file = new File(context.getExternalFilesDir(backupPath), filename);
            }
            else
            {
                file = new File(context.getFilesDir(), filename);
            }

            if (file.exists()) {
                try{
                    fis = context.openFileInput(file.getName());
                    ois = new ObjectInputStream(fis);
                    bookList = (List<Book>) ois.readObject();

                    ois.close();
                    fis.close();
                }catch(IOException e){
                    Toast.makeText(getApplication().getApplicationContext(), "Se ha producido un error con el archivo", Toast.LENGTH_SHORT).show();
                }catch(ClassNotFoundException e){
                    Toast.makeText(getApplication().getApplicationContext(), "Se ha producido un error con 'ois.readObject()'", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                file.mkdir();
            }
        }

    }


    public void saveBooksList() {

        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // Available to read and write

            FileOutputStream fos = null;
            ObjectOutputStream oos = null;

            if(Storage.equals("external"))
            {
                File sd = Environment.getExternalStorageDirectory(); // getting phone SD card path
                String backupPath = sd.getAbsolutePath() + FOLDER_NAME; // if you want to set backup in specific folder name
                file = new File(context.getExternalFilesDir(backupPath), filename);
            }
            else
            {
                file = new File(context.getFilesDir(), filename);
            }

            if (file.exists()) {
                try{
                    fos = context.openFileOutput(file.getName(), Context.MODE_PRIVATE);
                    oos = new ObjectOutputStream(fos);
                    oos.writeObject(bookList);

                    oos.close();
                    fos.close();
                }catch(IOException e){
                    Toast.makeText(getApplication().getApplicationContext(), "Se ha producido un error con el archivo", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                file.mkdir();
            }
        }
        if (state.equals(Environment.MEDIA_MOUNTED) ||
                state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            // Available to at least read
        }

    }


    public void setArchivoSharedPreferences(String archiveName)
    {
        this.filename = archiveName;
    }

    public void changeFilename(String newarchivename) {
        this.filename = newarchivename;
    }

    public void changeLocalization(String storageArea) {
        this.Storage = storageArea;
    }
}
